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
import util.AlgorithmUtil;

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
            FriendRequest u = new FriendRequest();
            u.setRequestID(ID);
            int index = AlgorithmUtil.binarySearch(friendRequestList, u);
            if (index != -1) {
                FriendRequest i = friendRequestList.get(index);
                boolean p = friendRequestDao.removeFriendRequest(i);
                if (p) {
                    friendRequestList.remove(i);
                }
                return p;
            }
        }
        if (type.equals("PairingRequest")) {
            PairingRequest u = new PairingRequest();
            u.setID(ID);
            int index = AlgorithmUtil.binarySearch(pairingRequestList, u);
            if (index != -1) {
                PairingRequest i = pairingRequestList.get(index);
                boolean p = pairingRequestDao.removePairingRequestByID(ID);
                if (p) {
                    pairingRequestList.remove(i);
                }
                return p;
            }
        }
        return false;
    }
}
