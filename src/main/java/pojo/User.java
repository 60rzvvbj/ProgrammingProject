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
import java.util.List;

public class User {
    private String username;
    private String password;
    private String sex;
    private String studentNumber;
    private int age;
    private double height;
    private double weight;
    private List<String> friendList;//好友列表
    private int status = 1;//账号状态，0为异常，1为正常

    public User() {
    }

    //两个参数的构造方法，用来创建固定用户名和密码的的用户
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setFriendList(List<String> list) {
        this.friendList = list;
    }

    public List<String> getFriendList() {
        return friendList;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "User{" +
                "studentNumber='" + studentNumber + '\'' +
                ", username='" + username + '\'' +
                "password='" + password + '\'' +
                "sex ='" + sex + '\'' + "height ='" + height + '\'' +
                "weight ='" + weight + '\'' + "status ='" + status + '\'' +
                "friendList ='" + friendList + '\'' +
                '}';
    }
}
