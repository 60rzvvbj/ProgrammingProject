package service;

public interface FeedbackService {

    /**
     * <p><b>方法名：</b>{@code addFeedback}</p>
     * <p><b>功能：</b></p><br>发起反馈
     *
     * @param studentNumber 学号
     * @param type          类型
     * @param content       内容
     * @return 反馈ID
     * @author 60rzvvbj
     * @date 2021/5/27
     */
    String addFeedback(String studentNumber, int type, String content);

    /**
     * <p><b>方法名：</b>{@code removeFeedback}</p>
     * <p><b>功能：</b></p><br>删除反馈
     *
     * @param feedbackID 反馈ID
     * @return 是否成功
     * @author 60rzvvbj
     * @date 2021/5/27
     */
    boolean removeFeedback(String feedbackID);
}
