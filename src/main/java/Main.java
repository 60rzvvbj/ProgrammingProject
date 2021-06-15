import commom.factory.DaoFactory;
import commom.factory.ListFactory;
import commom.factory.ServiceFactory;
import dao.AdministratorDao;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import pojo.FriendRequest;
import pojo.PairingRequest;
import pojo.User;
import service.FriendRequestService;
import service.InformationService;
import service.PairingRequestService;
import service.UserService;
import util.JsonUtil;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        FriendRequestService friendRequestService = ServiceFactory.getFriendRequestService();
        PairingRequestService pairingRequestService = ServiceFactory.getPairingRequestService();
        UserService userService = ServiceFactory.getUserService();
        List<FriendRequest> friendRequestList = ListFactory.getFriendRequestList();
        List<PairingRequest> pairingRequestList = ListFactory.getPairingRequestList();
        for(FriendRequest i : friendRequestList){
            System.out.println(i.getRequestID());
        }
    }
}
