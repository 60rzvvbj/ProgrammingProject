package controller;

import commom.factory.ServiceFactory;
import service.FriendRequestService;
import service.UserService;
import util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/friendRequest")
public class FriendRequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 发起好友请求
        String applicant = req.getParameter("applicant");
        String requested = req.getParameter("requested");

        FriendRequestService friendRequestService = ServiceFactory.getFriendRequestService();

        String ID = friendRequestService.addFriend(applicant, requested);

        Map<String, Object> map = new HashMap<>();
        map.put("status", ID != null);
        resp.getWriter().write(JsonUtil.mapToJson(map));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接受好友请求
        String id = req.getParameter("id");

        FriendRequestService friendRequestService = ServiceFactory.getFriendRequestService();

        boolean status = friendRequestService.acceptRequest(id);

        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        resp.getWriter().write(JsonUtil.mapToJson(map));
    }
}
