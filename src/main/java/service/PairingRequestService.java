package service;

import pojo.PairingRequest;

import java.util.List;

public interface PairingRequestService {

    /**
     * <p><b>方法名：</b>{@code addPairingRequest}</p>
     * <p><b>功能：</b></p><br>发起配对
     *
     * @param studentNumber 发起人学号
     * @return 配对ID
     * @author 60rzvvbj
     * @date 2021/5/25
     */
    String addPairingRequest(String studentNumber);

    /**
     * <p><b>方法名：</b>{@code queryPairingRequest}</p>
     * <p><b>功能：</b></p><br>查看所有配对
     *
     * @return 所有配对列表
     * @author 60rzvvbj
     * @date 2021/5/25
     */
    List<PairingRequest> queryPairingRequest();

    /**
     * <p><b>方法名：</b>{@code queryUserPairing}</p>
     * <p><b>功能：</b></p><br>查询某个人的配对
     *
     * @param studentNumber 学号
     * @return 某个人的配对列表
     * @author 60rzvvbj
     * @date 2021/5/25
     */
    List<PairingRequest> queryUserPairing(String studentNumber);
}