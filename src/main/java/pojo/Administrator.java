package pojo;

/**
 * <p><b>类名：</b>{@code Administrator}</p>
 * <p><b>功能：</b></p><br>管理员的java bean
 *
 * @author 60rzvvbj
 * @date 2021/5/22
 */
public class Administrator {
    private String accountNumber;
    private String password;

    public Administrator() {
    }

    public Administrator(String accountNumber, String password) {
        this.accountNumber = accountNumber;
        this.password = password;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "accountNumber='" + accountNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
