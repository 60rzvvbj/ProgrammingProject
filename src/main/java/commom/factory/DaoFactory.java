package commom.factory;

import dao.*;
import dao.impl.*;

public class DaoFactory {
    public static AdministratorDao getAdministratorDao() {
        return new AdministratorDaoImpl();
    }

    public static FeedbackDao getFeedbackDao() {
        return new FeedbackDaoImpl();
    }

    public static FriendRequestDao getFriendRequestDao() {
        return new FriendRequestDaoImpl();
    }

    public static PairingRequestDao getPairingRequestDao() {
        return new PairingRequestDaoImpl();
    }

    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }
}
