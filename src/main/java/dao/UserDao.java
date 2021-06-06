package dao;

import pojo.User;

import java.util.List;

/**
 * <p><b>接口名：</b>{@code UserDao}</p>
 *
 * @author 60rzvvbj
 * @date 2021/5/22
 */
public interface UserDao {
    boolean addUser(User user);

    boolean modifyUser(User user);

    boolean removeUser(String sno);

    List<User> queryAllUser();

    boolean addFriend(String sno1, String sno2, long time);

    boolean removeFriend(String sno1, String sno2);
}
