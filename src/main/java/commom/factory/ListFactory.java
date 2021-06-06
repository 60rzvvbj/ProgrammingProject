package commom.factory;

import pojo.Feedback;
import pojo.FriendRequest;
import pojo.PairingRequest;
import pojo.User;

import java.util.List;

public class ListFactory {
    private static final List<User> userList;
    private static final List<Feedback> feedbackList;
    private static final List<FriendRequest> friendRequestList;
    private static final List<PairingRequest> pairingRequestList;

    static {
        userList = DaoFactory.getUserDao().queryAllUser();
        feedbackList = DaoFactory.getFeedbackDao().queryAllFeedback();
        friendRequestList = DaoFactory.getFriendRequestDao().queryAllFriendRequest();
        pairingRequestList = DaoFactory.getPairingRequestDao().queryAllPairingRequest();
    }

    public static List<User> getUserList() {
        return userList;
    }

    public static List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public static List<FriendRequest> getFriendRequestList() {
        return friendRequestList;
    }

    public static List<PairingRequest> getPairingRequestList() {
        return pairingRequestList;
    }
}
