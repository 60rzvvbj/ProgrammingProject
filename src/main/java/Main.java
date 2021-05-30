import commom.factory.DaoFactory;
import commom.factory.ServiceFactory;
import dao.AdministratorDao;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import service.UserService;

public class Main {
    public static void main(String[] args) {
        UserService userService = ServiceFactory.getUserService();
        UserDao userDao = DaoFactory.getUserDao();
        System.out.println(userDao.queryAllUser());

    }

}
