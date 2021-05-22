package dao.impl;

import dao.UserDao;
import pojo.User;

import java.util.List;

/**
 * <p><b>类名：</b>{@code UserDaoImpl}</p>
 * <p><b>功能：</b></p><br>UserDao的实现类
 *
 * @author 60rzvvbj
 * @date 2021/5/22
 */
public class UserDaoImpl implements UserDao {

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public boolean modifyUser(User user) {
        return false;
    }

    @Override
    public List<User> queryAllUser() {
        return null;
    }
}
