package service.impl;

import commom.factory.DaoFactory;
import dao.PairingRequestDao;
import dao.UserDao;
import pojo.PairingRequest;
import pojo.User;
import service.PairingRequestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PairingRequestServiceImpl implements PairingRequestService {
    private final PairingRequestDao pairingRequestDao;
    private final List<PairingRequest> list;
    //设置一个配对列表，用来存放发起的配对
    private List<PairingRequest> userPairingList = new ArrayList<>();

    public PairingRequestServiceImpl() {
        this.pairingRequestDao = DaoFactory.getPairingRequestDao();
        this.list = pairingRequestDao.queryAllPairingRequest();
    }

    @Override
    public String addPairingRequest(String studentNumber) {
        PairingRequest pairingRequest = new PairingRequest();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入配对要求：");
        String request = input.next();
        pairingRequest.setRequest(request);
        pairingRequest.setStudentNumber(studentNumber);
        if (pairingRequestDao.addPairingRequest(pairingRequest)){
        //每次发起一个配对，就往配对列表中添加该配对
        userPairingList.add(pairingRequest);}
        return pairingRequest.getID();
    }

    @Override
    public List<PairingRequest> queryPairingRequest() {
        return list;
    }

    @Override
    public List<PairingRequest> queryUserPairing(String studentNumber) {
        return userPairingList;
    }

    @Override
    public boolean removePairingRequest(String studentNumber, String ID) {
        PairingRequest pairingRequest = new PairingRequest();
        if (pairingRequestDao.removePairingRequestByID(ID)){
            //每次删除一个配对，就往配对列表中去除该配对
            userPairingList.remove(pairingRequest);
            return true;
        }
        else
            return false;

    }

    @Override
    public boolean acceptPairing(String acceptNumber, String ID) {
        PairingRequest pairingRequest = new PairingRequest();
        pairingRequest.setRecipientNumber(acceptNumber);
        pairingRequest.setID(ID);
        PairingRequest pairingRequest1 = new PairingRequest(acceptNumber);
        if (pairingRequest1.getRequest().equals(pairingRequest.getRequest())) {
            return true;
        } else
            return false;
    }
}
