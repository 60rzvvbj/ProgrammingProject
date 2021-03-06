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
import commom.factory.ListFactory;
import dao.UserDao;
import pojo.User;
import service.UserService;
import util.AlgorithmUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final List<User> list;

    public UserServiceImpl() {
        this.userDao = DaoFactory.getUserDao();
        this.list = ListFactory.getUserList();
    }

    @Override
    public boolean register(String studentNumber, String password, String username) { //注册
        if (studentNumber == null || password == null || username == null) {
            return false;
        }
        User u = new User();
        u.setStudentNumber(studentNumber);
        int index = AlgorithmUtil.binarySearch(list, u);
        if (index != -1) {
            return false;
        }
        User user = new User();
        user.setStudentNumber(studentNumber);
        user.setPassword(password);
        user.setUsername(username);
        user.setSex("?");
        user.setStatus(1);
        user.setPersonalProfile(User.DEFAULT_PERSONAL_PROFILE);
        user.setContactInformation(User.DEFAULT_CONTACT_INFORMATION);
        user.setFriendList(new LinkedList<>());
        //list.add(user);
        int indexx = AlgorithmUtil.bisectionInsert(list, user);
        list.add(indexx, user);
        userDao.addUser(user);
        return true;
    }

    @Override
    public boolean login(String studentNumber, String password) { //登录
//        for (User i : list) {
//            if (studentNumber.equals(i.getStudentNumber()) && password.equals(i.getPassword())) {
//                return true;
//            }
//        }
        User u = new User();
        u.setStudentNumber(studentNumber);
        int index = AlgorithmUtil.binarySearch(list, u);
        if (index != -1) {
            if (list.get(index).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean changePassword(String studentNumber, String oldPassword, String newPassword) { //修改密码
        User u = new User();
        u.setStudentNumber(studentNumber);
        int index = AlgorithmUtil.binarySearch(list, u);
        if (index != -1) {
            if (oldPassword.equals(list.get(index).getPassword())) {
                list.get(index).setPassword(newPassword);
                userDao.modifyUser(list.get(index));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean editInformation(String studentNumber, Map<String, Object> map) { //编辑个人信息
        User u = new User();
        u.setStudentNumber(studentNumber);
        int index = AlgorithmUtil.binarySearch(list, u);
        if (index != -1) {
            User i = list.get(index);
            i.setUsername((String) map.get("username"));
            i.setSex((String) map.get("sex"));
            i.setAge(Integer.parseInt((String) map.get("age")));
            i.setHeight(Double.parseDouble((String) map.get("height")));
            i.setWeight(Double.parseDouble((String) map.get("weight")));
            if (map.get("personalProfile") == null || map.get("personalProfile").equals("")) {
                i.setPersonalProfile(User.DEFAULT_PERSONAL_PROFILE);
            } else {
                i.setPersonalProfile((String) map.get("personalProfile"));
            }
            if (map.get("contactInformation") == null || map.get("contactInformation").equals("")) {
                i.setContactInformation(User.DEFAULT_CONTACT_INFORMATION);
            } else {
                i.setContactInformation((String) map.get("contactInformation"));
            }
            userDao.modifyUser(i);
            return true;
        }
        return false;
    }

    @Override
    public User queryUserByStudentNumber(String studentNumber) {
        User u = new User();
        u.setStudentNumber(studentNumber);
        int index = AlgorithmUtil.binarySearch(list, u);
        if (index != -1) {
            return list.get(index);
        }
        return null;
    }

    @Override
    public List<User> queryUser(String message) { // 查询用户，运用KMP算法
        if (message == "" || message == null) {
            return list;
        }
        List<User> res = new ArrayList<>(); //创建一个集合，存放符合条件的用户
        for (User u : list) { //遍历所有的用户
            String p = u.getUsername();
            if (AlgorithmUtil.KMP(message, p)) {
                res.add(u);
            }
            if (u.getStudentNumber().equals(message)) {
                res.add(u);
            }
        }
        return res;
    }

    @Override
    public List<User> queryFriendList(String studentNumber, String message) { //查询某用户的好友列表
        List<User> userList = new LinkedList<>();
        User u = new User();
        u.setStudentNumber(studentNumber);
        int index = AlgorithmUtil.binarySearch(list, u);
        if (index != -1) {
            User i = list.get(index);
            List<String> stringList = i.getFriendList();    //获取好友列表
            for (User k : list) {   //遍历所有的用户
                if (i.getStudentNumber().equals(k.getStudentNumber())) continue;
                int flag = 0;
                if (message != null) {
                    if (k.getStudentNumber().equals(message) || message.equals("") || AlgorithmUtil.KMP(message, k.getUsername())) {
                        flag = 1;
                    }
                }
                for (String j : stringList) {
                    if (message == null && k.getStudentNumber().equals(j)) {    //如果传入的是空，就找他所有的好友
                        userList.add(k);
                        break;
                    } else if (flag == 1 && k.getStudentNumber().equals(j)) {
                        flag = 0;
                        break;
                    }
                }
                if (flag == 1) {
                    userList.add(k);
                }
            }
            return userList;
        }
        return null;
    }

    @Override
    public boolean addFriend(String sno1, String sno2) {
        if (sno1.equals(sno2)) {
            return false;
        }
        int flag1 = 0, flag2 = 0;
        User p = new User();
        User q = new User(); //记录添加好友的两个用户分别是谁
        for (User i : list) {
            if (i.getStudentNumber().equals(sno1)) {
                p = i;
                flag1 = 1;
            }
            if (i.getStudentNumber().equals(sno2)) {
                q = i;
                flag2 = 1;
            }
            if (flag1 == 1 && flag2 == 1) {
                break;
            }
        }
        if (flag1 == 1 && flag2 == 1) {
            boolean u = userDao.addFriend(sno1, sno2, System.currentTimeMillis());
            if (u) {
                List<String> plist = p.getFriendList();
                List<String> qlist = q.getFriendList();
                plist.add(sno2);
                qlist.add(sno1);
                p.setFriendList(plist);
                q.setFriendList(qlist);
            }
            return u;
        }
        return false;
    }

    @Override
    public boolean removeFriend(String sno1, String sno2) {
        if (sno1.equals(sno2)) {
            return false;
        }
        int flag1 = 0, flag2 = 0;
        User p = new User();
        User q = new User(); //记录删除好友的两个用户分别是谁
        for (User i : list) {
            if (i.getStudentNumber().equals(sno1)) {
                p = i;
                flag1 = 1;
            }
            if (i.getStudentNumber().equals(sno2)) {
                q = i;
                flag2 = 1;
            }
            if (flag1 == 1 && flag2 == 1) {
                break;
            }
        }
        if (flag1 == 1 && flag2 == 1) {
            boolean u = userDao.removeFriend(sno1, sno2);
            if (u) {
                List<String> plist = p.getFriendList();
                List<String> qlist = q.getFriendList();
                plist.remove(sno2);
                qlist.remove(sno1);
                p.setFriendList(plist);
                q.setFriendList(qlist);
            }
            return u;
        }
        return false;
    }
}
