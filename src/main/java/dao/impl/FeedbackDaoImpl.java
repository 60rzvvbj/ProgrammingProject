package dao.impl;

import dao.FeedbackDao;
import pojo.Feedback;

import java.util.List;

/**
 * <p><b>类名：</b>{@code FeedbackDaoImpl}</p>
 * <p><b>功能：</b></p><br>FeedbackDao的实现类
 *
 * @author 60rzvvbj
 * @date 2021/5/22
 */
public class FeedbackDaoImpl implements FeedbackDao {
    @Override
    public boolean addFeedback(Feedback feedback) {
        return false;
    }

    @Override
    public boolean removeFeedbackByID(String id) {
        return false;
    }

    @Override
    public List<Feedback> queryAllFeedback() {
        return null;
    }
}
