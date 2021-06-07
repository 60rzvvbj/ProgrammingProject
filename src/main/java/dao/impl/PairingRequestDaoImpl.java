package dao.impl;

import dao.PairingRequestDao;
import pojo.PairingRequest;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * <p><b>类名：</b>{@code PairingRequestDaoImpl}</p>
 * <p><b>功能：</b></p><br>PairingRequestDao的实现类
 *
 * @author 60rzvvbj, iamcht
 * @date 2021/5/22
 */
public class PairingRequestDaoImpl implements PairingRequestDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public String addPairingRequest(PairingRequest pairingRequest) {
        String res = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into pairingreq(reqsno, reqtime, content, status) values(?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, pairingRequest.getStudentNumber());
            preparedStatement.setLong(2, pairingRequest.getStartTime());
            preparedStatement.setString(3, pairingRequest.getRequest());
            preparedStatement.setInt(4, pairingRequest.getStatus());
            int line = preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                res = resultSet.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            close();
        }
        return res;
    }

    @Override
    public boolean removePairingRequestByID(String id) {
        int pairingRequestId = Integer.parseInt(id);
        boolean res;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from friendreq where id = ?";
            preparedStatement.setInt(1, pairingRequestId);
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
    public boolean modifyPairingRequest(PairingRequest pairingRequest) {
        boolean res;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "update pairingreq set reqsno = ?, ressno = ?, reqtime = ?, content = ?, status = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pairingRequest.getStudentNumber());
            preparedStatement.setString(2, pairingRequest.getRecipientNumber());
            preparedStatement.setLong(3, pairingRequest.getStartTime());
            preparedStatement.setString(4, pairingRequest.getRequest());
            preparedStatement.setInt(5, pairingRequest.getStatus());
            preparedStatement.setInt(6, Integer.parseInt(pairingRequest.getID()));
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
    public List<PairingRequest> queryAllPairingRequest() {
        List<PairingRequest> res = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from pairingreq";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            res = new LinkedList<>();
            PairingRequest pairingRequest;
            while (resultSet.next()) {
                pairingRequest = new PairingRequest();
                pairingRequest.setID(resultSet.getInt("id") + "");
                pairingRequest.setStudentNumber(resultSet.getString("reqsno"));
                pairingRequest.setRecipientNumber(resultSet.getString("ressno"));
                pairingRequest.setStartTime(resultSet.getLong("reqtime"));
                pairingRequest.setRequest(resultSet.getString("content"));
                pairingRequest.setStatus(resultSet.getInt("status"));
                res.add(pairingRequest);
            }
        } catch (Exception e) {
        } finally {
            close();
        }
        return res;
    }

    private void close() {
        JDBCUtil.close(preparedStatement, connection);
    }
}
