package commom.factory;

import service.*;
import service.impl.*;

public class ServiceFactory {

    private static final AdministratorService administratorService;
    private static final FeedbackService feedbackService;
    private static final FriendRequestService friendRequestService;
    private static final PairingRequestService pairingRequestService;
    private static final UserService userService;
    private static final InformationService informationService;

    static {
        administratorService = new AdministratorServiceImpl();
        feedbackService = new FeedbackServiceImpl();
        friendRequestService = new FriendRequestServiceImpl();
        pairingRequestService = new PairingRequestServiceCHTImpl();
        userService = new UserServiceImpl();
        informationService = new InformationServiceImpl();
    }

    public static AdministratorService getAdministratorService() {
        return administratorService;
    }

    public static FeedbackService getFeedbackService() {
        return feedbackService;
    }

    public static FriendRequestService getFriendRequestService() {
        return friendRequestService;
    }

    public static PairingRequestService getPairingRequestService() {
        return pairingRequestService;
    }

    public static UserService getUserService() {
        return userService;
    }

    public static InformationService getInformationService() {
        return informationService;
    }
}
