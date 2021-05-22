package pojo;
/**
 * <p><b>类名：</b>{@code PairingRequest}</p>
 * <p><b>功能：</b></p><br>配对的java bean
 * <p><b>方法：</b></p>
 *
 * @author 24LJ
 * @date 2021/5/22
 */
import java.util.Date;

public class PairingRequest {
    private int studentNumber;
    private String request;
    private Date startTime;
    private int recipientNumber;
    public PairingRequest(){}
    //创建一个两个参数的构造方法，获取学生的学号和发起配对的时间
    public PairingRequest(int studentNumber,Date startTime){
        this.studentNumber=studentNumber;
        this.startTime=startTime;
    }
    public int getStudentNumber(){
        return studentNumber;
    }
    public int getRecipientNumber(){
        return recipientNumber;
    }
    public Date getStartTime(){
        return startTime;
    }
    public String getRequest(){
        return request;
    }
    @Override
    public String toString(){
        return "ParingRequest{" +
                "studentNumber='" + studentNumber + '\'' +
                ", request='" + request + '\''+
                "startTime='" + startTime + '\'' +
                "recipientNumber ='" + recipientNumber + '\''+
                '}';
    }
}
