package service.impl;

/**
 * <p><b>方法名：</b>{@code AdministratorServiceImpl}</p>
 * <p><b>功能：</b></p><br>
 *
 * @return 是否成功
 * @author iamcht
 * @date 2021/6/3
 */

import commom.factory.DaoFactory;
import commom.factory.ListFactory;
import dao.AdministratorDao;
import dao.UserDao;
import pojo.Administrator;
import pojo.User;
import service.AdministratorService;

import java.util.List;

public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorDao administratorDao;
    private final UserDao userDao;
    private final List<Administrator> administratorList;
    private final List<User> userList;

    public AdministratorServiceImpl() {
        this.administratorDao = DaoFactory.getAdministratorDao();
        this.userDao = DaoFactory.getUserDao();
        this.administratorList = administratorDao.queryAllAdministrator();
        this.userList = ListFactory.getUserList();
    }

    @Override
    public boolean login(String accountNumber, String password) {   //管理员登录
        for (Administrator i : administratorList) {
            if (accountNumber.equals(i.getAccountNumber()) && password.equals(i.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean blockedUser(String studentNumber) {  //封号
        for (User i : userList) {
            if (studentNumber.equals(i.getStudentNumber())) {
                if (i.getStatus() == 0) {
                    return true;
                } else {
                    i.setStatus(0);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean unlock(String studentNumber) {   //解封
        for (User i : userList) {
            if (studentNumber.equals(i.getStudentNumber())) {
                if (i.getStatus() == 1) {
                    return false;
                } else {
                    i.setStatus(1);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean removeUser(String studentNumber) {   //删除用户
        for (User i : userList) {
            if (studentNumber.equals(i.getStudentNumber())) {
                userDao.removeUser(studentNumber);
                return true;
            }
        }
        return false;
    }
}
