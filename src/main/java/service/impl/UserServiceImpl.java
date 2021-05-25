package service.impl;

import commom.factory.DaoFactory;
import dao.UserDao;
import pojo.User;
import service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl() {
        this.userDao = DaoFactory.getUserDao();
    }

    @Override
    public boolean register(String studentNumber, String password, String username) {
        return false;
    }

    @Override
    public boolean login(String studentNumber, String password) {
        return false;
    }

    @Override
    public boolean changePassword(String studentNumber, String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public boolean editInformation(String studentNumber, Map<String, Object> map) {
        return false;
    }

    @Override
    public List<User> queryUser(String message) {
        return null;
    }

    @Override
    public List<User> queryFriendList(String studentNumber) {
        return null;
    }
}
