import commom.factory.DaoFactory;
import dao.AdministratorDao;

public class Main {
    public static void main(String[] args) {
        AdministratorDao administratorDao = DaoFactory.getAdministratorDao();
        System.out.println(administratorDao.queryAllAdministrator());
    }
}
