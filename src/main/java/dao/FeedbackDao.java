package dao;

import pojo.Feedback;

import java.util.List;

/**
 * <p><b>接口名：</b>{@code FeedbackDao}</p>
 *
 * @author 60rzvvbj
 * @date 2021/5/22
 */
public interface FeedbackDao {
    boolean addFeedback(Feedback feedback);

    boolean removeFeedbackByID(String id);

    List<Feedback> queryAllFeedback();
}
