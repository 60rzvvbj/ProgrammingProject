package dao.impl;

import dao.PairingRequestDao;
import pojo.PairingRequest;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        return null;
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
