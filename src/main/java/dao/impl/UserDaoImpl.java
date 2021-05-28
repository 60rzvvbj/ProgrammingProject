package dao.impl;

import dao.UserDao;
import pojo.User;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * <p><b>类名：</b>{@code UserDaoImpl}</p>
 * <p><b>功能：</b></p><br>UserDao的实现类
 *
 * @author 60rzvvbj
 * @date 2021/5/22
 */
public class UserDaoImpl implements UserDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

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
        List<User> res = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from user";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            res = new LinkedList<>();
            User user;
            while (resultSet.next()) {
                user = new User();
                user.setStudentNumber(resultSet.getString("sno"));
                user.setPassword(resultSet.getString("password"));
                user.setUsername(resultSet.getString("username"));
                user.setSex(resultSet.getString("sex"));
                user.setHeight(resultSet.getDouble("height"));
                user.setWeight(resultSet.getDouble("weight"));
                user.setStatus(resultSet.getInt("status"));
                res.add(user);
            }
        } catch (Exception e) {
        } finally {
            close();
        }
        return res;
    }

    private void close() {
        JDBCUtil.close(resultSet, preparedStatement, connection);
    }
}
