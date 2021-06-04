package dao.impl;

import dao.FriendRequestDao;
import pojo.Feedback;
import pojo.FriendRequest;
import pojo.User;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * <p><b>类名：</b>{@code FriendRequestDaoImpl}</p>
 * <p><b>功能：</b></p><br>FriendRequestDao的实现类
 *
 * @author 60rzvvbj, iamcht
 * @date 2021/5/22
 */
public class FriendRequestDaoImpl implements FriendRequestDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public String addFriendRequest(FriendRequest friendRequest) {
        return null;
    }

    @Override
    public boolean removeFriendRequest(FriendRequest friendRequest) {
        int id = Integer.parseInt(friendRequest.getRequestID());
        boolean res;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from friendreq where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
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
    public List<FriendRequest> queryAllFriendRequest() {
        List<FriendRequest> res = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from friendreq";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            res = new LinkedList<>();
            FriendRequest friendRequest;
            while (resultSet.next()) {
                friendRequest = new FriendRequest();
                friendRequest.setRequestID(resultSet.getInt("id") + "");
                friendRequest.setApplicant(resultSet.getString("reqno"));
                friendRequest.setRequested(resultSet.getString("resno"));
                friendRequest.setTime(resultSet.getLong("reqtime"));
                friendRequest.setStatus(resultSet.getInt("status"));
                res.add(friendRequest);
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
