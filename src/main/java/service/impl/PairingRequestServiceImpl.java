package service.impl;

import pojo.PairingRequest;
import pojo.User;
import service.PairingRequestService;

import java.util.List;
import java.util.Scanner;

public class PairingRequestServiceImpl implements PairingRequestService {
    //构造方法
    public PairingRequestServiceImpl() {
    }

    @Override
    public String addPairingRequest(String studentNumber) {
        PairingRequest pairingRequest = new PairingRequest();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入配对要求：");
        String request = input.next();
        pairingRequest.setRequest(request);
        pairingRequest.setStudentNumber(studentNumber);
        return pairingRequest.getID();
    }

    @Override
    public List<PairingRequest> queryPairingRequest() {
        return null;
    }

    @Override
    public List<PairingRequest> queryUserPairing(String studentNumber) {
        return null;
    }

    @Override
    public boolean removePairingRequest(String studentNumber, String ID) {
        PairingRequest pairingRequest = new PairingRequest();
        pairingRequest.setID(null);
        pairingRequest.setStudentNumber(null);
        return true;
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
