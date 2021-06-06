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
        String applicant = req.getParameter("applicant");
        String requested = req.getParameter("requested");

        FriendRequestService friendRequestService = ServiceFactory.getFriendRequestService();

        String ID = friendRequestService.addFriend(applicant, requested);

        Map<String, Object> map = new HashMap<>();
        map.put("status", ID != null);
        resp.getWriter().write(JsonUtil.mapToJson(map));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqSno = req.getParameter("reqSno");
        String resSno = req.getParameter("resSno");

        UserService userService = ServiceFactory.getUserService();



        Map<String, Object> map = new HashMap<>();
        map.put("status", true);
        resp.getWriter().write(JsonUtil.mapToJson(map));
    }
}
