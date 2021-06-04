package service.impl;

import commom.factory.DaoFactory;
import dao.PairingRequestDao;
import dao.UserDao;
import pojo.PairingRequest;
import pojo.User;
import service.PairingRequestService;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PairingRequestServiceCHTImpl implements PairingRequestService {
    private final PairingRequestDao pairingRequestDao;
    private final UserDao userDao;
    private final List<PairingRequest> pairingRequestList;
    private final List<User> userList;

    public PairingRequestServiceCHTImpl(){
        this.pairingRequestDao = DaoFactory.getPairingRequestDao();
        this.userDao = DaoFactory.getUserDao();
        this.pairingRequestList = pairingRequestDao.queryAllPairingRequest();
        this.userList = userDao.queryAllUser();
    }
    @Override
    public String addPairingRequest(String studentNumber, Map<String, Object> data) {   //添加配对请求
        PairingRequest pairingRequest = new PairingRequest();
        String request = (String)data.get("request");
        Long startTime = System.currentTimeMillis();
        pairingRequest.setStudentNumber(studentNumber);
        pairingRequest.setRequest(request);
        pairingRequest.setStartTime(startTime);
        pairingRequest.setStatus(0);
        String ID = pairingRequestDao.addPairingRequest(pairingRequest);
        pairingRequest.setID(ID);
        pairingRequestList.add(pairingRequest);
        return ID;
    }

    @Override
    public List<PairingRequest> queryPairingRequest() { //查询所有配对信息
        return pairingRequestList;
    }

    @Override
    public List<PairingRequest> queryUserPairing(String studentNumber) {    //查询某用户的请求配对信息
        List<PairingRequest> list = new LinkedList<>();
        for(PairingRequest i : pairingRequestList){
            if(i.getStudentNumber().equals(studentNumber)){
                list.add(i);
            }
        }
        return list;
    }

    @Override
    public boolean removePairingRequest(String ID) {    //在数据库移除配对请求
        for(PairingRequest i : pairingRequestList){
            if(i.getID().equals(ID)){
                pairingRequestDao.removePairingRequestByID(ID);
                pairingRequestList.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean acceptPairing(String acceptNumber, String ID) {  //某用户接受别人的配对请求，在数据库更新特定配对请求的接受人信息
        for(PairingRequest i : pairingRequestList){
            if(i.getID().equals(ID)){
                if(i.getStatus() == 1){
                    return false;
                }
                i.setStatus(1);
                i.setRecipientNumber(acceptNumber);
                pairingRequestDao.modifyPairingRequest(i);
                return true;
            }
        }
        return false;
    }
}