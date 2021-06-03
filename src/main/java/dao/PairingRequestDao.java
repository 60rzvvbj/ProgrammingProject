package dao;

import pojo.PairingRequest;

import java.util.List;

/**
 * <p><b>接口名：</b>{@code PairingRequestDao}</p>
 *
 * @author 60rzvvbj
 * @date 2021/5/22
 */
public interface PairingRequestDao {
    String addPairingRequest(PairingRequest pairingRequest);

    boolean removePairingRequestByID(String id);

    boolean modifyPairingRequest(PairingRequest pairingRequest);

    List<PairingRequest> queryAllPairingRequest();
}
