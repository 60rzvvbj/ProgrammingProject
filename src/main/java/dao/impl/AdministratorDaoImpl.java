package dao.impl;

import dao.AdministratorDao;
import pojo.Administrator;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * <p><b>类名：</b>{@code AdministratorDaoImpl}</p>
 * <p><b>功能：</b></p><br>AdministratorDao的实现类
 *
 * @author 60rzvvbj
 * @date 2021/5/22
 */
public class AdministratorDaoImpl implements AdministratorDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public boolean addAdministrator(Administrator administrator) {
        boolean res = true;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into admin values(?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, administrator.getAccountNumber());
            preparedStatement.setString(2, administrator.getPassword());
            int line = preparedStatement.executeUpdate();
            res = line > 0;
        } catch (Exception e) {
            res = false;
        } finally {
            close();
        }
        return res;
    }

    @Override
    public List<Administrator> queryAllAdministrator() {
        List<Administrator> res = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from admin";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            res = new LinkedList<>();
            Administrator administrator;
            while (resultSet.next()) {
                administrator = new Administrator();
                administrator.setAccountNumber(resultSet.getString("num"));
                administrator.setPassword(resultSet.getString("password"));
                res.add(administrator);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            close();
        }
        return res;
    }

    private void close() {
        JDBCUtil.close(preparedStatement, connection);
    }
}
