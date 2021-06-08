import commom.factory.DaoFactory;
import commom.factory.ServiceFactory;
import dao.AdministratorDao;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import service.FriendRequestService;
import service.InformationService;
import service.PairingRequestService;
import service.UserService;
import util.JsonUtil;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        UserService userService = ServiceFactory.getUserService();
        System.out.println(userService.queryFriendList("191543132", null));
    }

}
