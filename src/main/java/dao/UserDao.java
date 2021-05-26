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

    List<User> queryAllUser();
}
