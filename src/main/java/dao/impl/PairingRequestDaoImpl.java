package dao.impl;

import dao.PairingRequestDao;
import pojo.PairingRequest;

import java.util.List;

/**
 * <p><b>类名：</b>{@code PairingRequestDaoImpl}</p>
 * <p><b>功能：</b></p><br>PairingRequestDao的实现类
 *
 * @author 60rzvvbj
 * @date 2021/5/22
 */
public class PairingRequestDaoImpl implements PairingRequestDao {
    @Override
    public boolean addPairingRequest(PairingRequest pairingRequest) {
        return false;
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
        return null;
    }
}
