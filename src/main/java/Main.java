import commom.factory.DaoFactory;
import commom.factory.ServiceFactory;
import dao.AdministratorDao;
import service.UserService;

public class Main {
    public static void main(String[] args) {
//        UserService userService = ServiceFactory.getUserService();
//        System.out.println(userService.queryUser("yc"));
        //System.out.println(userService.login("191543132", "123"));
        String message = "啊";
        int m = message.length();
        int flag = 0;
        int[] ne = new int[m];
        ne[0] = -1;
        char[] p = message.toCharArray();
        System.out.println(p.length);
        for (int i = 1, j = -1; i < m; i++) {
            while (j >= 0 && p[j + 1] != p[i]) j = ne[j];
            if (p[j + 1] == p[i]) j++;
            ne[i] = j;
        }
        String k = "哈哈哈";

        if (flag == 0) System.out.println("没找到！");
    }

}
