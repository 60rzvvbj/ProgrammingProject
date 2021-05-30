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
        boolean res;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into user(sno, username, password, sex, height, weight, status) values(?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getStudentNumber());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getSex());
            preparedStatement.setDouble(5, user.getHeight());
            preparedStatement.setDouble(6, user.getWeight());
            preparedStatement.setInt(7, user.getStatus());
            int i = preparedStatement.executeUpdate();
            res = i > 0;
        } catch (Exception e) {
            res = false;
        } finally {
            close();
        }
        return res;
    }

    @Override
    public boolean modifyUser(User user) {
        boolean res;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into user(username, password, sex, height, weight, status) values(?, ?, ?, ?, ?, ?) where sno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSex());
            preparedStatement.setDouble(4, user.getHeight());
            preparedStatement.setDouble(5, user.getWeight());
            preparedStatement.setInt(6, user.getStatus());
            preparedStatement.setString(7, user.getStudentNumber());
            int i = preparedStatement.executeUpdate();
            res = i > 0;
        } catch (Exception e) {
            res = false;
        } finally {
            close();
        }
        return res;
    }

    @Override
    public boolean removeUser(String sno) {
        boolean res;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from user where sno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sno);
            int i = preparedStatement.executeUpdate();
            res = i > 0;
        } catch (Exception e) {
            res = false;
        } finally {
            close();
        }
        return res;
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
                user.setList(queryFriend(user.getStudentNumber()));
                res.add(user);
            }
        } catch (Exception e) {
        } finally {
            close();
        }
        return res;
    }

    // 查询所有好友学号
    private List<String> queryFriend(String studentNumber) {
        List<String> res = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "\n" +
                    "select if(user1no = ?, user2no, user1no) as sno\n" +
                    "from friendlist\n" +
                    "where user1no = ? or user2no = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentNumber);
            preparedStatement.setString(2, studentNumber);
            preparedStatement.setString(3, studentNumber);
            resultSet = preparedStatement.executeQuery();
            res = new LinkedList<>();
            while (resultSet.next()) {
                res.add(resultSet.getString("sno"));
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.close(resultSet, preparedStatement, connection);
        }
        return res;
    }

    private void close() {
        JDBCUtil.close(resultSet, preparedStatement, connection);
    }
}
