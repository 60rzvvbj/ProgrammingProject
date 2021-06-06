import commom.factory.DaoFactory;
import commom.factory.ServiceFactory;
import dao.AdministratorDao;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import service.FriendRequestService;
import service.InformationService;
import service.UserService;
import util.JsonUtil;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        UserService userService = ServiceFactory.getUserService();
//        userService.addFriend("191543132", "191543107");
//        userService.addFriend("191543132", "191543108");
//        userService.addFriend("191543132", "191543109");
        InformationService informationService = ServiceFactory.getInformationService();
        System.out.println(informationService.queryAllInformation("191543107"));
    }

}
