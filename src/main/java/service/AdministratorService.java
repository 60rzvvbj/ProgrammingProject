package service;

public interface AdministratorService {

    /**
     * <p><b>方法名：</b>{@code login}</p>
     * <p><b>功能：</b></p><br>管理员登录
     *
     * @param accountNumber 管理员账号
     * @param password      管理员密码
     * @author 60rzvvbj
     * @date 2021/5/27
     */
    boolean login(String accountNumber, String password);

    /**
     * <p><b>方法名：</b>{@code blockeduser}</p>
     * <p><b>功能：</b></p><br>封号
     *
     * @param studentNumber 学号
     * @return 是否成功
     * @author 60rzvvbj
     * @date 2021/5/27
     */
    boolean blockedUser(String studentNumber);

    /**
     * <p><b>方法名：</b>{@code unlock}</p>
     * <p><b>功能：</b></p><br>解封
     *
     * @param studentNumber 学号
     * @return 是否成功
     * @author 60rzvvbj
     * @date 2021/5/27
     */
    boolean unlock(String studentNumber);

    /**
     * <p><b>方法名：</b>{@code removeUser}</p>
     * <p><b>功能：</b></p><br>删除用户
     *
     * @param studentNumber 学号
     * @author 60rzvvbj
     * @date 2021/5/28
     */
    boolean removeUser(String studentNumber);
}
