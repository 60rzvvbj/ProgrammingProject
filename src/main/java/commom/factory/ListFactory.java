package commom.factory;

import pojo.User;

import java.util.List;

public class ListFactory {
    private static final List<User> userList;

    static {
        userList = DaoFactory.getUserDao().queryAllUser();
    }

    public static List<User> getUserList() {
        return userList;
    }
}
