package service.impl;

import commom.factory.DaoFactory;
import dao.PairingRequestDao;
import dao.UserDao;
import pojo.PairingRequest;
import pojo.User;
import service.PairingRequestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PairingRequestServiceImpl implements PairingRequestService {
    private final PairingRequestDao pairingRequestDao;
    private final List<PairingRequest> list;

    public PairingRequestServiceImpl() {
        this.pairingRequestDao = DaoFactory.getPairingRequestDao();
        this.list = pairingRequestDao.queryAllPairingRequest();
    }

    @Override
    public String addPairingRequest(String studentNumber, Map<String, Object> data) {
        PairingRequest pairingRequest = new PairingRequest();
        //获取请求
        String request = (String) data.get("request");
        pairingRequest.setRequest(request);
        pairingRequest.setStudentNumber(studentNumber);
        //还要获取ID
        return pairingRequest.getID();
    }

    @Override
    public List<PairingRequest> queryPairingRequest() {
        return list;
    }

    @Override
    public List<PairingRequest> queryUserPairing(String studentNumber) {
        //建立一个列表，用来存放个人的配对信息
        List<PairingRequest> list1 = new ArrayList<>();
        for (PairingRequest p : list) {
            if (p.getStudentNumber().equals(studentNumber)) {
                //查找所有配对列表中的数据，如果是本人的配对，就加到个人配对列表中
                list1.add(p);
            }
        }
        //返回个人配对列表
        return list1;
    }

    @Override
    public boolean removePairingRequest(String ID) {
//        PairingRequest pairingRequest = new PairingRequest(studentNumber);
//        if (pairingRequestDao.removePairingRequestByID(ID)) {
//            list.remove(pairingRequest);
//            return true;
//        } else
            return false;

    }

    @Override
    public boolean acceptPairing(String acceptNumber, String ID) {
//        PairingRequest pairingRequest = new PairingRequest();
//        pairingRequest.setRecipientNumber(acceptNumber);
//        pairingRequest.setID(ID);
//        PairingRequest pairingRequest1 = new PairingRequest(acceptNumber);
//        if (pairingRequest1.getRequest().equals(pairingRequest.getRequest())) {
//            return true;
//        } else
            return false;
    }
}
