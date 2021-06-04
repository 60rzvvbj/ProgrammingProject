package service.impl;
/**
 * <p><b>方法名：</b>{@code UserServicelmpl}</p>
 * <p><b>功能：</b></p><br>
 *
 * @return 是否成功
 * @author iamcht
 * @date 2021/5/31
 */

import commom.factory.DaoFactory;
import dao.UserDao;
import pojo.User;
import service.UserService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final List<User> list;

    public UserServiceImpl() {
        this.userDao = DaoFactory.getUserDao();
        this.list = userDao.queryAllUser();
    }

    @Override
    public boolean register(String studentNumber, String password, String username) { //注册
        User user = new User();
        if (studentNumber == null || password == null || username == null) {
            return false;
        }
        for (User i : list) {
            if (studentNumber.equals(i.getStudentNumber())) {
                return false;
            }
        }
        user.setStudentNumber(studentNumber);
        user.setPassword(password);
        user.setUsername(username);
        user.setSex("?");
        user.setStatus(1);
        user.setPersonalProfile(User.DEFAULT_PERSONAL_PROFILE);
        user.setContactInformation(User.DEFAULT_CONTACT_INFORMATION);
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
                i.setAge(Integer.parseInt((String) map.get("age")));
                i.setHeight(Double.parseDouble((String) map.get("height")));
                i.setWeight(Double.parseDouble((String) map.get("weight")));
                if(map.get("personalProfile") == null || map.get("personalProfile").equals("")){
                    i.setPersonalProfile(User.DEFAULT_PERSONAL_PROFILE);
                } else {
                    i.setPersonalProfile((String) map.get("personalProfile"));
                }
                if(map.get("contactInformation") == null || map.get("contactInformation").equals("")){
                    i.setContactInformation(User.DEFAULT_CONTACT_INFORMATION);
                } else {
                    i.setContactInformation((String) map.get("contactInformation"));
                }
                userDao.modifyUser(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public User queryUserByStudentNumber(String studentNumber) {
        for (User i : list) {
            if (i.getStudentNumber().equals(studentNumber)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public List<User> queryUser(String message) { // 查询用户，运用KMP算法
        List<User> res = new ArrayList<>(); //创建一个集合，存放符合条件的用户
        int m = message.length();
        int[] ne = new int[m];
        ne[0] = -1;
        char[] k = message.toCharArray();
        for (int i = 1, j = -1; i < m; i++) { //求next数组
            while (j >= 0 && k[j + 1] != k[i]) j = ne[j];
            if (k[j + 1] == k[i]) j++;
            ne[i] = j;
        }
        for (User u : list) { //遍历所有的用户
            String p = u.getUsername();
            int n = p.length();
            char[] s = new char[n + 1];
            for (int i = 0; i < n; i++) {
                s[i] = p.charAt(i);
            }
            for (int i = 0, j = -1; i < n; i++) {
                while (j != -1 && s[i] != k[j + 1]) j = ne[j];
                if (s[i] == k[j + 1]) j++;
                if (j == m - 1) {
                    res.add(u);
                    break;
                }
            }
            if (u.getStudentNumber().equals(message)) {
                res.add(u);
            }
        }
        return res;
    }

    @Override
    public List<User> queryFriendList(String studentNumber) { //查询某用户的好友列表
        List<User> userList = new LinkedList<>();
        for (User i : list) {
            if (i.getStudentNumber().equals(studentNumber)) {
                List<String> stringList = i.getFriendList();
                for (String j : stringList) {
                    for (User k : list) {
                        if (k.getStudentNumber().equals(j)) {
                            userList.add(k);
                        }
                    }
                }
                return userList;
            }
        }
        return null;
    }
}
