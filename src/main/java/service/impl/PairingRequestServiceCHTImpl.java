package service.impl;

import commom.factory.DaoFactory;
import commom.factory.ListFactory;
import commom.factory.ServiceFactory;
import dao.PairingRequestDao;
import dao.UserDao;
import pojo.FriendRequest;
import pojo.PairingRequest;
import pojo.User;
import service.FriendRequestService;
import service.PairingRequestService;
import util.AlgorithmUtil;

import java.util.*;

public class PairingRequestServiceCHTImpl implements PairingRequestService {
    private final PairingRequestDao pairingRequestDao;
    private final UserDao userDao;
    private final List<PairingRequest> pairingRequestList;
    private final List<User> userList;

    public PairingRequestServiceCHTImpl() {
        this.pairingRequestDao = DaoFactory.getPairingRequestDao();
        this.userDao = DaoFactory.getUserDao();
        this.pairingRequestList = ListFactory.getPairingRequestList();
        this.userList = ListFactory.getUserList();
    }

    @Override
    public PairingRequest addPairingRequest(String studentNumber, Map<String, Object> data) {   //添加配对请求
        User u = new User();
        u.setStudentNumber(studentNumber);
        int index = AlgorithmUtil.binarySearch(userList, u);
        if (index == -1) {
            PairingRequest pairingRequest = new PairingRequest();
            pairingRequest.setID("学号不存在");
            return pairingRequest;
        }
        PairingRequest pairingRequest = new PairingRequest();
        String request = (String) data.get("request");
        long startTime = System.currentTimeMillis();
        pairingRequest.setStudentNumber(studentNumber);
        pairingRequest.setRequest(request);
        pairingRequest.setStartTime(startTime);
        pairingRequest.setStatus(0);
        String ID = pairingRequestDao.addPairingRequest(pairingRequest);
        pairingRequest.setID(ID);
        int indexx = AlgorithmUtil.bisectionInsert(pairingRequestList, pairingRequest);
        pairingRequestList.add(indexx, pairingRequest);
//        pairingRequestList.add(pairingRequest);
        return pairingRequest;
    }

    @Override
    public List<PairingRequest> queryPairingRequest() { //查询所有配对信息
        return pairingRequestList;
    }

    @Override
    public List<PairingRequest> queryUserPairing(String studentNumber, String type, String search) {    //查询某用户的请求配对信息
        List<PairingRequest> includeList = new LinkedList<>();
        List<PairingRequest> excludeList = new LinkedList<>();
        if (type.equals("include")) { //自己发的配对请求
            for (PairingRequest i : pairingRequestList) {
                if (i.getStatus() == 1) continue;
                if (i.getStudentNumber().equals(studentNumber)) {
                    if (search.equals("")) {  //如果查询的内容为空，就把自己发的都加进去
                        includeList.add(i);
                    } else {
                        if (AlgorithmUtil.KMP(search, i.getRequest())) {  //如果查询的内容不为空，就把匹配的加进去
                            includeList.add(i);
                        }
                    }
                }
            }
            Set<PairingRequest> p = new HashSet<>();    //去重
            p.addAll(includeList);
            includeList.clear();
            includeList.addAll(p);
            return includeList;
        } else if (type.equals("exclude")) {    //别人发的配对请求
            for (PairingRequest i : pairingRequestList) {
                if (i.getStatus() == 1) continue;
                if (i.getStudentNumber().equals(studentNumber)) continue;
                if (search.equals("")) {  //如果查询的内容为空，就把别人发的都加进去
                    excludeList.add(i);
                } else {
                    if (AlgorithmUtil.KMP(search, i.getRequest())) {  //如果查询的内容不为空，就把匹配的加进去
                        excludeList.add(i);
                    }
                    if (i.getStudentNumber().equals(search)) {    //如果查询的内容是一个学号，就把这个学号发的配对加进去
                        excludeList.add(i);
                    }
                    User u = new User();    //修改
                    u.setStudentNumber(i.getStudentNumber());
                    int index = AlgorithmUtil.binarySearch(userList, u);
                    if (index != -1) {
                        User j = userList.get(index);
                        if (AlgorithmUtil.KMP(search, j.getUsername())) {
                            excludeList.add(i);
                        }
                    }
                }
            }
            Set<PairingRequest> q = new HashSet<>();    //去重
            q.addAll(excludeList);
            excludeList.clear();
            excludeList.addAll(q);
            return excludeList;
        }
        return null;
    }

    @Override
    public boolean removePairingRequest(String ID) {    //在数据库移除配对请求
        PairingRequest u = new PairingRequest();
        u.setID(ID);
        int index = AlgorithmUtil.binarySearch(pairingRequestList, u);
        if (index != -1) {
            PairingRequest i = pairingRequestList.get(index);
            boolean t = pairingRequestDao.removePairingRequestByID(ID);
            if (t) {
                pairingRequestList.remove(i);
            }
            return t;
        }
        return false;
    }

    @Override
    public boolean acceptPairing(String acceptNumber, String ID) throws Exception {  //某用户接受别人的配对请求，在数据库更新特定配对请求的接受人信息
        PairingRequest u = new PairingRequest();
        u.setID(ID);
        int index = AlgorithmUtil.binarySearch(pairingRequestList, u);
        if (index != -1) {
            PairingRequest i = pairingRequestList.get(index);
            for (User j : userList) {
                if (j.getStudentNumber().equals(i.getStudentNumber())) {
                    List<String> friendList = j.getFriendList();
                    for (String k : friendList) {
                        if (k.equals(acceptNumber)) {
                            throw new Exception("你们两个人已经是好友了");
                        }
                    }
                }
            }
            if (i.getStatus() == 1) {
                return false;
            }
            String studentNumber = i.getStudentNumber();
            i.setStatus(1);
            i.setRecipientNumber(acceptNumber);
            pairingRequestDao.modifyPairingRequest(i);
            FriendRequestService friendRequestService = ServiceFactory.getFriendRequestService();
            friendRequestService.addFriend(acceptNumber, studentNumber);
            return true;
        }
        return false;
    }
}