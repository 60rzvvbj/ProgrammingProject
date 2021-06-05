import commom.factory.DaoFactory;
import commom.factory.ServiceFactory;
import dao.AdministratorDao;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import service.UserService;
import util.JsonUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        UserService userService = ServiceFactory.getUserService();
        UserDao userDao = DaoFactory.getUserDao();
        System.out.println(userDao.queryAllUser());

    }

}
