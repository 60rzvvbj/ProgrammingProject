package service.impl;

/**
 * <p><b>方法名：</b>{@code FeedbackServiceImpl}</p>
 * <p><b>功能：</b></p><br>
 *
 * @return 是否成功
 * @author iamcht
 * @date 2021/6/3
 */

import commom.factory.DaoFactory;
import commom.factory.ListFactory;
import dao.AdministratorDao;
import dao.FeedbackDao;
import dao.UserDao;
import pojo.Administrator;
import pojo.Feedback;
import pojo.User;
import service.FeedbackService;

import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackDao feedbackDao;
    private final UserDao userDao;
    private final List<Feedback> feedbackList;
    private final List<User> userList;

    public FeedbackServiceImpl() {
        this.feedbackDao = DaoFactory.getFeedbackDao();
        this.userDao = DaoFactory.getUserDao();
        this.feedbackList = ListFactory.getFeedbackList();
        this.userList = ListFactory.getUserList();
    }

    @Override
    public String addFeedback(String studentNumber, int type, String content) { //在数据库添加反馈
        Long time = System.currentTimeMillis();
        Feedback feedback = new Feedback(studentNumber, type, content, time);
        feedbackList.add(feedback);
        return feedbackDao.addFeedback(feedback);
    }

    @Override
    public boolean removeFeedback(String feedbackID) {  //在数据库移除反馈
        for (Feedback i : feedbackList) {
            if (i.getFeedbackID().equals(feedbackID)) {
                feedbackDao.removeFeedbackByID(i.getFeedbackID());
                return feedbackList.remove(i);
            }
        }
        return false;
    }
}
