package commom.factory;

import dao.*;
import dao.impl.*;

public class DaoFactory {

    private static final AdministratorDao administratorDao;
    private static final FeedbackDao feedbackDao;
    private static final FriendRequestDao friendRequestDao;
    private static final PairingRequestDao pairingRequestDao;
    private static final UserDao userDao;

    static {
        administratorDao = new AdministratorDaoImpl();
        feedbackDao = new FeedbackDaoImpl();
        friendRequestDao = new FriendRequestDaoImpl();
        pairingRequestDao = new PairingRequestDaoImpl();
        userDao = new UserDaoImpl();
    }

    public static AdministratorDao getAdministratorDao() {
        return administratorDao;
    }

    public static FeedbackDao getFeedbackDao() {
        return feedbackDao;
    }

    public static FriendRequestDao getFriendRequestDao() {
        return friendRequestDao;
    }

    public static PairingRequestDao getPairingRequestDao() {
        return pairingRequestDao;
    }

    public static UserDao getUserDao() {
        return userDao;
    }
}
