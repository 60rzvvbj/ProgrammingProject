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
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> child = new HashMap<>();
        List<Object> list = new LinkedList<>();
        child.put("name", "张三");
        child.put("age", 18);
        map.put("token", "asdfghjkl");
        map.put("status", true);
        map.put("user", child);
        list.add(1.4);
        list.add(3.5);
        list.add(8.123);
        map.put("numbers", list);
        System.out.println(JsonUtil.mapToJson(map));

    }

}
