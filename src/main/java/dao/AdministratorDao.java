package dao;

import pojo.Administrator;

import java.util.List;

/**
 * <p><b>接口名：</b>{@code AdministratorDao}</p>
 *
 * @author 60rzvvbj
 * @date 2021/5/22
 */
public interface AdministratorDao {
    boolean addAdministrator(Administrator administrator);

    List<Administrator> queryAllAdministrator();
}
