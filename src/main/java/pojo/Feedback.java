package pojo;

/**
 * <p><b>类名：</b>{@code Feedback}</p>
 * <p><b>功能：</b></p><br>管理员的java bean
 *
 * @author iamcht
 * @date 2021/5/22
 */

public class Feedback {
    private String username; //反馈人
    private String accountNumber; //反馈人学号
    private int type;   //1是普通反馈，2是举报用户
    private String content; //反馈内容
    private long time; //反馈时间
    private String feedbackID;

    public Feedback(){
    }

    public Feedback(String accountNumber, int type, String content) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.content = content;
        this.time = System.currentTimeMillis();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getType() { return type; }

    public void setType(int type) { this.type = type; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) { this.content = content; }

    public long getTime() {
        return time;
    }

    public void setTime() {
        this.time = System.currentTimeMillis();
    }

    public void setFeedbackID(String feedbackID) { this.feedbackID = feedbackID; }

    public String getFeedbackID() { return feedbackID; }

    @Override
    public String toString() {
        return "Feedback{" +
                "accountNumber='" + accountNumber + '\'' +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
