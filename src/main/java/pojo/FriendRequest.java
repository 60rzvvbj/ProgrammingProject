package pojo;

/**
 * <p><b>类名：</b>{@code Feedback}</p>
 * <p><b>功能：</b></p><br>管理员的java bean
 *
 * @author iamcht
 * @date 2021/5/22
 */

public class FriendRequest {
    private String requested; //被请求人学号
    private String applicant; //请求人学号
    private long time; //发起请求时间
    private int status; //状态：1代表等待中，2代表拒绝，3代表接受
    private String requestID;

    public FriendRequest() {
    }

    public FriendRequest(String requested, String applicant, int status) {
        this.requested = requested;
        this.applicant = applicant;
        this.time = System.currentTimeMillis();
        this.status = status;
    }

    public String getRequested() {
        return requested;
    }

    public void setRequested(String requested) {
        this.requested = requested;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public long getTime() {
        return time;
    }

    public void setTime() {
        this.time = System.currentTimeMillis();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "requested='" + requested + '\'' +
                ", applicant='" + applicant + '\'' +
                ", time=" + time +
                ", status=" + status +
                '}';
    }
}
