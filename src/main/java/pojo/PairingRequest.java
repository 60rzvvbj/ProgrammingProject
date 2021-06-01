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
    private String studentNumber;//创建对象时传递进来
    private String request;//通过用户输入获取
    private long startTime;//用户输入配对请求之后，就获取当前时间
    private String recipientNumber;//接单人学号，怎么获取？
    private String ID;//设置一个配对ID，用来记录是第几个发起配对
    //ID怎么获取？
    public PairingRequest() {
    }

    //创建一个两个参数的构造方法，创建一个有配对要求的学生
    public PairingRequest(String studentNumber) {
        this.studentNumber = studentNumber;

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
    public void setID(String ID){
        this.ID=ID;
    }
    public String getID(){
        return ID;
    }

    @Override
    public String toString() {
        return "ParingRequest{" +
                "studentNumber='" + studentNumber + '\'' +
                ", request='" + request + '\'' +
                "startTime='" + startTime + '\'' +
                "recipientNumber ='" + recipientNumber + '\'' + " ID='" + ID + '\'' +
                '}';
    }
}
