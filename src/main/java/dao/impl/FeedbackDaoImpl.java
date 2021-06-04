package dao.impl;

import dao.FeedbackDao;
import pojo.Feedback;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * <p><b>类名：</b>{@code FeedbackDaoImpl}</p>
 * <p><b>功能：</b></p><br>FeedbackDao的实现类
 *
 * @author 60rzvvbj, iamcht
 * @date 2021/5/22
 */
public class FeedbackDaoImpl implements FeedbackDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public String addFeedback(Feedback feedback) {
        return null;
    }

    @Override
    public boolean removeFeedbackByID(String id) {
        boolean res;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from feedback where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));
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
    public List<Feedback> queryAllFeedback() {
        List<Feedback> res = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from feedback";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            res = new LinkedList<>();
            Feedback feedback;
            while (resultSet.next()) {
                feedback = new Feedback();
                feedback.setFeedbackID(resultSet.getInt("id") + "");
                feedback.setAccountNumber(resultSet.getString("sno"));
                feedback.setContent(resultSet.getString("content"));
                feedback.setType(resultSet.getInt("type"));
                feedback.setTime(resultSet.getLong("ftime"));
                res.add(feedback);
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
