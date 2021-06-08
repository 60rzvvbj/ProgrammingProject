package controller;

import commom.factory.ServiceFactory;
import pojo.User;
import service.FriendRequestService;
import service.UserService;
import service.impl.FriendRequestServiceImpl;
import util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@WebServlet("/friendList")
public class FriendListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取好友列表
        String sno = req.getParameter("sno");
        String message = req.getParameter("message");

        UserService userService = ServiceFactory.getUserService();
        List<User> friendList = userService.queryFriendList(sno, message);
        Map<String, Object> map = new HashMap<>();
        List<Object> list = new LinkedList<>();
        for (User user: friendList){
            Map<String, Object> u = new HashMap<>();
            u.put("sno", user.getStudentNumber());
            u.put("username", user.getUsername());
            list.add(u);
        }
        map.put("friendList", list);
        resp.getWriter().write(JsonUtil.mapToJson(map));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqSno = req.getParameter("reqSno");
        String resSno = req.getParameter("resSno");

        UserService userService = ServiceFactory.getUserService();
        boolean status = userService.removeFriend(reqSno, resSno);

        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        resp.getWriter().write(JsonUtil.mapToJson(map));
    }
}
