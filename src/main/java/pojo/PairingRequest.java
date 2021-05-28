package pojo;
/**
 * <p><b>类名：</b>{@code PairingRequest}</p>
 * <p><b>功能：</b></p><br>配对的java bean
 * <p><b>方法：</b></p>
 *
 * @author 24LJ
 * @date 2021/5/22
 */


public class PairingRequest {
    private String studentNumber;
    private String request;
    private long startTime;
    private String recipientNumber;
    private String ID;//设置一个配对ID，用来记录是第几个发起配对

    public PairingRequest() {
    }

    //创建一个两个参数的构造方法，创建一个有配对要求的学生
    public PairingRequest(String studentNumber, String request) {
        this.studentNumber = studentNumber;
        this.request = request;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setRecipientNumber(String recipientNumber) {
        this.recipientNumber = recipientNumber;
    }

    public String getRecipientNumber() {
        return recipientNumber;
    }

    public void setStartTime(long startTime) {
        this.startTime = System.currentTimeMillis();
    }

    public long getStartTime() {
        return startTime;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    @Override
    public String toString() {
        return "ParingRequest{" +
                "studentNumber='" + studentNumber + '\'' +
                ", request='" + request + '\'' +
                "startTime='" + startTime + '\'' +
                "recipientNumber ='" + recipientNumber + '\'' +
                '}';
    }
}
