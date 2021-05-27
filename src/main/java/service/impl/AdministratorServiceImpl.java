package service.impl;

import service.AdministratorService;

public class AdministratorServiceImpl implements AdministratorService {
    @Override
    public boolean login(String accountNumber, String password) {
        return false;
    }

    @Override
    public boolean blockedUser(String studentNumber) {
        return false;
    }

    @Override
    public boolean unlock(String studentNumber) {
        return false;
    }
}
