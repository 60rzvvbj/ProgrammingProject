package service;

import pojo.FriendRequest;

import java.util.List;

public interface FriendRequestService {

    /**
     * <p><b>方法名：</b>{@code addFriend}</p>
     * <p><b>功能：</b></p><br>发起好友请求
     *
     * @param applicant 请求人学号
     * @param requested 被请求人学号
     * @return 请求ID
     * @author 60rzvvbj
     * @date 2021/5/25
     */
    String addFriend(String applicant, String requested);

    /**
     * <p><b>方法名：</b>{@code acceptRequest}</p>
     * <p><b>功能：</b></p><br>接受请求
     *
     * @param friendRequestID 请求ID
     * @return 是否成功
     * @author 60rzvvbj
     * @date 2021/5/25
     */
    boolean acceptRequest(String friendRequestID);

    /**
     * <p><b>方法名：</b>{@code refuseRequest}</p>
     * <p><b>功能：</b></p><br>拒绝请求
     *
     * @param friendRequestID 请求ID
     * @return 是否成功
     * @author 60rzvvbj
     * @date 2021/5/25
     */
    boolean refuseRequest(String friendRequestID, String pairingRequestID);

    /**
     * <p><b>方法名：</b>{@code queryRequest}</p>
     * <p><b>功能：</b></p><br>查看收到的请求
     *
     * @param requested 被请求人学号
     * @return 请求列表
     * @author 60rzvvbj
     * @date 2021/5/25
     */
    List<FriendRequest> queryRequest(String requested);
}
