package service.impl;

/**
 * <p><b>方法名：</b>{@code FriendRequestServiceImpl}</p>
 * <p><b>功能：</b></p><br>
 *
 * @return 是否成功
 * @author iamcht
 * @date 2021/6/3
 */

import commom.factory.DaoFactory;
import dao.FriendRequestDao;
import dao.UserDao;
import pojo.FriendRequest;
import pojo.User;
import service.FriendRequestService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FriendRequestServiceImpl implements FriendRequestService {
    private final FriendRequestDao friendRequestDao;
    private final UserDao userDao;
    private final List<FriendRequest> friendRequestList;
    private final List<User> userList;

    public FriendRequestServiceImpl() {
        this.friendRequestDao = DaoFactory.getFriendRequestDao();
        this.userDao = DaoFactory.getUserDao();
        this.friendRequestList = friendRequestDao.queryAllFriendRequest();
        this.userList = userDao.queryAllUser();
    }

    @Override
    public String addFriend(String applicant, String requested) {   //在数据库添加好友请求
        FriendRequest friendRequest = new FriendRequest(applicant, requested, 1, System.currentTimeMillis());
        return friendRequestDao.addFriendRequest(friendRequest);
    }

    @Override
    public boolean acceptRequest(String friendRequestID) {  //接受好友请求
        for (FriendRequest i : friendRequestList) {
            if (i.getRequestID().equals(friendRequestID)) {
                if (i.getStatus() == 2 || i.getStatus() == 3) {
                    return false;
                }
                i.setStatus(3);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean refuseRequest(String friendRequestID) {  //拒绝好友请求
        for (FriendRequest i : friendRequestList) {
            if (i.getRequestID().equals(friendRequestID)) {
                if (i.getStatus() == 2 || i.getStatus() == 3) {
                    return false;
                }
                i.setStatus(2);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<FriendRequest> queryRequest(String requested) { //查看某用户的好友请求
        List<FriendRequest> list = new LinkedList<>();
        for (FriendRequest i : friendRequestList) {
            if (i.getRequested().equals(requested)) {
                list.add(i);
            }
        }
        return list;
    }
}
