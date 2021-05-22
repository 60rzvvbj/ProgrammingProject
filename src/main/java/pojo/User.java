package pojo;
/**
 * <p><b>类名：</b>{@code User}</p>
 * <p><b>功能：</b></p><br>用户的java bean
 * <p><b>方法：</b></p>
 *
 * @author 24LJ
 * @date 2021/5/22
 */
import java.util.Date;

public class User {
    private String username;
    private String password;
    private String sex;
    private int studentNumber;
    public int getStudentNumber(){
        return studentNumber;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getSex(){
        return sex;
    }
    @Override
    public String toString(){
        return "User{" +
                "studentNumber='" + studentNumber + '\'' +
                ", username='" + username + '\''+
                "password='" + password + '\'' +
                "sex ='" + sex + '\''+
                '}';
    }
}
