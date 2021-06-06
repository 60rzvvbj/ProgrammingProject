package service.impl;

import commom.factory.DaoFactory;
import commom.factory.ListFactory;
import dao.FriendRequestDao;
import dao.PairingRequestDao;
import dao.UserDao;
import pojo.FriendRequest;
import pojo.PairingRequest;
import pojo.User;
import service.InformationService;

import java.util.LinkedList;
import java.util.List;

public class InformationServiceImpl implements InformationService {

    private final PairingRequestDao pairingRequestDao;
    private final List<PairingRequest> pairingRequestList;
    private final FriendRequestDao friendRequestDao;
    private final List<FriendRequest> friendRequestList;

    public InformationServiceImpl() {
        this.friendRequestDao = DaoFactory.getFriendRequestDao();
        this.pairingRequestDao = DaoFactory.getPairingRequestDao();
        this.friendRequestList = ListFactory.getFriendRequestList();
        this.pairingRequestList = ListFactory.getPairingRequestList();
    }

    @Override
    public List<Object> queryAllInformation(String sno) {
        List<Object> list = new LinkedList<>();
        List<FriendRequest> p = new LinkedList<>(); //存放有人给我发的好友请求
        List<PairingRequest> q = new LinkedList<>();//存放有人接受我的配对请求
        for (FriendRequest i : friendRequestList) {
            if (i.getApplicant().equals(sno) && (i.getStatus() == 2 || i.getStatus() == 3)) {   //有人响应了我给他发的好友请求
                list.add(i);
            }
            if (i.getRequested().equals(sno) && i.getStatus() == 1) {   //有人给我发好友请求
                p.add(i);
            }
        }
        for (PairingRequest j : pairingRequestList) {
            if (j.getStudentNumber().equals(sno)) {  //有人接受了我的配对
                q.add(j);
            }
        }

        for (FriendRequest i : p) {
            int flag = 0;
            for (PairingRequest j : q) {
                if (i.getApplicant().equals(j.getRecipientNumber()) && i.getRequested().equals(j.getStudentNumber())) {
                    flag = 1;
                    List<Object> u = new LinkedList<>();
                    u.add(j);
                    u.add(i.getRequestID());
                    list.add(u);
                    break;
                }
            }
            if(flag == 0){
                list.add(i);
            }
        }

        return list;
    }

    @Override
    public boolean removeInformationService(String ID, String type) {
        if (type.equals("FriendRequest")) {
            for (FriendRequest i : friendRequestList) {
                if (i.getRequestID().equals(ID)) {
                    boolean u = friendRequestDao.removeFriendRequest(i);
                    if (u) {
                        friendRequestList.remove(i);
                    }
                    return u;
                }
            }
        }
        if (type.equals("PairingRequest")) {
            for (PairingRequest i : pairingRequestList) {
                if (i.getID().equals(ID)) {
                    boolean u = pairingRequestDao.removePairingRequestByID(ID);
                    if (u) {
                        pairingRequestList.remove(i);
                    }
                    return u;
                }
            }
        }
        return false;
    }
}
