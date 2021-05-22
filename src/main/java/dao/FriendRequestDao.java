package dao;

import pojo.FriendRequest;

import java.util.List;

/**
 * <p><b>接口名：</b>{@code FriendRequestDao}</p>
 *
 * @author 60rzvvbj
 * @date 2021/5/22
 */
public interface FriendRequestDao {
    boolean addFriendRequest(FriendRequest friendRequest);

    boolean removeFriendRequest(FriendRequest friendRequest);

    List<FriendRequest> queryAllFriendRequest();
}
