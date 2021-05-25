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
    private String studentNumber;
    private int age;
    private int height;
    private int weight;

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

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "studentNumber='" + studentNumber + '\'' +
                ", username='" + username + '\'' +
                "password='" + password + '\'' +
                "sex ='" + sex + '\'' +
                '}';
    }
}
