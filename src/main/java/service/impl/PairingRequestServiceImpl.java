package service.impl;

import pojo.PairingRequest;
import service.PairingRequestService;

import java.util.List;

public class PairingRequestServiceImpl implements PairingRequestService {
    @Override
    public String addPairingRequest(String studentNumber) {
        return null;
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
        return false;
    }

    @Override
    public boolean acceptPairing(String acceptNumber, String ID) {
        return false;
    }
}
