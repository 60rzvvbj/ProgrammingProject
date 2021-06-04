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
 * @author 60rzvvbj
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
        return false;
    }

    @Override
    public boolean modifyPairingRequest(PairingRequest pairingRequest) {
        return false;
    }

    @Override
    public List<PairingRequest> queryAllPairingRequest() {
        List<PairingRequest> res = null;
        try{
            connection = JDBCUtil.getConnection();
            String sql = "select * from pairingreq";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            res = new LinkedList<>();
            PairingRequest pairingRequest;
            while(resultSet.next()){
                pairingRequest = new PairingRequest();
                pairingRequest.setID(resultSet.getInt("id") + "");
                pairingRequest.setStudentNumber(resultSet.getString("reqsno"));
                pairingRequest.setRecipientNumber(resultSet.getString("ressno"));
                pairingRequest.setStartTime(resultSet.getLong("reqtime"));
                pairingRequest.setRequest(resultSet.getString("content"));

            }
        } catch (Exception e) {
        } finally {
            close();
        }
        return null;
    }

    private void close() { JDBCUtil.close(preparedStatement, connection); }
}
