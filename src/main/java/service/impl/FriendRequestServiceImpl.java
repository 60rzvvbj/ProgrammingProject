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
import commom.factory.ListFactory;
import commom.factory.ServiceFactory;
import dao.FriendRequestDao;
import dao.UserDao;
import pojo.FriendRequest;
import pojo.User;
import service.FriendRequestService;
import service.UserService;

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
        this.friendRequestList = ListFactory.getFriendRequestList();
        this.userList = ListFactory.getUserList();
    }

    @Override
    public String addFriend(String applicant, String requested) {   //在数据库添加好友请求
        List<String> friendlist = new LinkedList<>();
        int flag1 = 0, flag2 = 0;
        for (User i : userList){    //判断两个用户是否存在
            if(i.getStudentNumber().equals(applicant)){
                friendlist = i.getFriendList(); //获取申请添加别人为好友的用户 自己的好友列表
                flag1 = 1;
            }
            if(i.getStudentNumber().equals(requested)){
                flag2 = 1;
            }
            if(flag1 == 1 && flag2 == 1){
                break;
            }
        }
        if(flag1 == 0 || flag2 == 0){
            return "学号不存在";
        }
        for(String i : friendlist){ //判断要添加的人是否已经是好友
            if(i.equals(requested)){
                return "两个用户已经是好友";
            }
        }
        for(FriendRequest i : friendRequestList){   //判断申请人之前是否已申请过，并且对面还没接受
            if(i.getApplicant().equals(applicant) && i.getRequested().equals(requested) && i.getStatus() == 1){
                return "申请人之前已申请过，对面还没接受";
            }
        }
        FriendRequest friendRequest = new FriendRequest(applicant, requested, 1, System.currentTimeMillis());
        String requestID = friendRequestDao.addFriendRequest(friendRequest);
        friendRequest.setRequestID(requestID);
        friendRequestList.add(friendRequest);
        return requestID;
    }

    @Override
    public boolean acceptRequest(String friendRequestID) {  //接受好友请求
        for (FriendRequest i : friendRequestList) {
            if (i.getRequestID().equals(friendRequestID)) {
                if (i.getStatus() == 2 || i.getStatus() == 3) {
                    return false;
                }
                i.setStatus(3);
                boolean u = friendRequestDao.modifyFriendRequest(i);
                if(u){
                    UserService userService = ServiceFactory.getUserService();
                    userService.addFriend(i.getApplicant(), i.getRequested());
                    return u;
                }
                return false;
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
                friendRequestDao.modifyFriendRequest(i);
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
