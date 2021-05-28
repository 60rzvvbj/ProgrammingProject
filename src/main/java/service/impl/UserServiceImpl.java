package service.impl;

import commom.factory.DaoFactory;
import dao.UserDao;
import pojo.User;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final List<User> list;
    User user = new User();

    public UserServiceImpl() {
        this.userDao = DaoFactory.getUserDao();
        this.list = userDao.queryAllUser();
    }

    @Override
    public boolean register(String studentNumber, String password, String username) { //注册
        for (User i : list) {
            if (studentNumber.equals(i.getStudentNumber())) {
                return false;
            }
        }
        user.setStudentNumber(studentNumber);
        user.setPassword(password);
        user.setUsername(username);
        list.add(user);
        userDao.addUser(user);
        return true;
    }

    @Override
    public boolean login(String studentNumber, String password) { //登录
        for (User i : list) {
            if (studentNumber.equals(i.getStudentNumber()) && password.equals(i.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean changePassword(String studentNumber, String oldPassword, String newPassword) { //修改密码
        for (User i : list) {
            if (studentNumber.equals(i.getStudentNumber()) && oldPassword.equals(i.getPassword())) {
                i.setPassword(newPassword);
                userDao.modifyUser(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean editInformation(String studentNumber, Map<String, Object> map) { //编辑个人信息
        for (User i : list) {
            if (studentNumber.equals(i.getStudentNumber())) {
                i.setSex((String) map.get("sex"));
                i.setAge((int) map.get("age"));
                i.setHeight((int) map.get("height"));
                i.setWeight((int) map.get("weight"));
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> queryUser(String message) { // 查询用户
        List<User> res = new ArrayList<>(); //
        int m = message.length();
        int[] ne = new int[m];
        ne[0] = -1;
        char[] k = message.toCharArray();
        for (int i = 1, j = -1; i < m; i++) {
            while (j >= 0 && k[j + 1] != k[i]) j = ne[j];
            if (k[j + 1] == k[i]) j++;
            ne[i] = j;
        }
        /////
        for (User u : list) { //遍历所有的用户
            int flag = 0; //标记某个用户是不是username就已经匹配了
            String p = u.getUsername();
            int n = p.length();
            char[] s = new char[n + 1];
            for(int i = 0; i < n; i++){
                s[i] = p.charAt(i);
            }
            for (int i = 0, j = -1; i < n; i++) {
                while (j != -1 && s[i] != k[j + 1]) j = ne[j];
                if (s[i] == k[j + 1]) j++;
                if (j == m - 1) {
                    flag = 1;
                    res.add(u);
                    break;
                }
            }
            if(flag == 1) continue;
            String q = u.getStudentNumber();
            int l = q.length();
            char[] t = new char[l + 1];
            for(int i = 0; i < l; i++){
                t[i] = q.charAt(i);
            }
            for (int i = 0, j = -1; i < l; i++) {
                while (j != -1 && t[i] != k[j + 1]) j = ne[j];
                if (t[i] == k[j + 1]) j++;
                if (j == m - 1) {
                    res.add(u);
                    break;
                }
            }
        }
        return res;
    }

    @Override
    public List<User> queryFriendList(String studentNumber) { //查询某用户的好友列表
        for(User i: list){
            if(i.getStudentNumber().equals(studentNumber)){
                return i.getList();
            }
        }
        return null;
    }
}
