package service;

import pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * <p><b>方法名：</b>{@code register}</p>
     * <p><b>功能：</b></p><br>注册
     *
     * @param studentNumber 学号
     * @param password      密码
     * @param username      用户名
     * @return 是否成功
     * @author 60rzvvbj
     * @date 2021/5/23
     */
    boolean register(String studentNumber, String password, String username);

    /**
     * <p><b>方法名：</b>{@code login}</p>
     * <p><b>功能：</b></p><br>登录
     *
     * @param studentNumber 学号
     * @param password      密码
     * @return 是否成功
     * @author 60rzvvbj
     * @date 2021/5/23
     */
    boolean login(String studentNumber, String password);

    /**
     * <p><b>方法名：</b>{@code changePassword}</p>
     * <p><b>功能：</b></p><br>修改密码
     *
     * @param studentNumber 学号
     * @param oldPassword   旧密码
     * @param newPassword   新密码
     * @return 是否成功
     * @author 60rzvvbj
     * @date 2021/5/23
     */
    boolean changePassword(String studentNumber, String oldPassword, String newPassword);

    /**
     * <p><b>方法名：</b>{@code editInformation}</p>
     * <p><b>功能：</b></p><br>修改个人信息
     *
     * @param studentNumber 学号
     * @param map           信息
     * @return 是否成功
     * @author 60rzvvbj
     * @date 2021/5/24
     */
    boolean editInformation(String studentNumber, Map<String, Object> map);

    /**
     * <p><b>方法名：</b>{@code queryUser}</p>
     * <p><b>功能：</b></p><br>通过学号或用户名查找用户
     *
     * @param message 学号或用户名
     * @return 符合条件的用户列表
     * @author 60rzvvbj
     * @date 2021/5/24
     */
    List<User> queryUser(String message);

    /**
     * <p><b>方法名：</b>{@code queryFriendList}</p>
     * <p><b>功能：</b></p><br>查看好友列表
     *
     * @param studentNumber 学好
     * @return 好有列表
     * @author 60rzvvbj
     * @date 2021/5/24
     */
    List<User> queryFriendList(String studentNumber);
}
