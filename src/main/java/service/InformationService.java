package service;

import java.util.List;

public interface InformationService {

    /**
     * <p><b>方法名：</b>{@code queryAllInformation}</p>
     * <p><b>功能：</b></p><br>查询某用户的所有消息
     *
     * @param sno 学号
     * @return Object[FriendRequest, PairingRequest]
     * @author 60rzvvbj
     * @date 2021/6/6
     */
    public List<Object> queryAllInformation(String sno);

    /**
     * <p><b>方法名：</b>{@code removeInformationService}</p>
     * <p><b>功能：</b></p><br>删除消息
     *
     * @param ID   消息ID
     * @param type 消息类型
     * @return 是否成功
     * @author 60rzvvbj
     * @date 2021/6/6
     */
    public boolean removeInformationService(String ID, String type);

}
