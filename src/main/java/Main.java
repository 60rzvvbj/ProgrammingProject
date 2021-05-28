import commom.factory.DaoFactory;
import commom.factory.ServiceFactory;
import dao.AdministratorDao;
import service.UserService;

public class Main {
    public static void main(String[] args) {
        UserService userService = ServiceFactory.getUserService();
        System.out.println(userService.queryUser("yc"));
        System.out.println(userService.login("191543132", "123"));
    }

}
