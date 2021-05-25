package service.impl;

import pojo.FriendRequest;
import service.FeedbackService;
import service.FriendRequestService;

import java.util.List;

public class FriendRequestImpl implements FriendRequestService {
    @Override
    public String addFriend(String applicant, String requested) {
        return null;
    }

    @Override
    public boolean acceptRequest(String friendRequestID) {
        return false;
    }

    @Override
    public boolean refuseRequest(String friendRequestID) {
        return false;
    }

    @Override
    public List<FriendRequest> queryRequest(String requested) {
        return null;
    }
}
