package controller;

import commom.factory.ServiceFactory;
import pojo.User;
import service.UserService;
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

@WebServlet("/userList")
public class UserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");

        UserService userService = ServiceFactory.getUserService();
        List<User> list = userService.queryUser(message);

        Map<String, Object> map = new HashMap<>();
        List<Object> userList = new LinkedList<>();
        for (User user: list) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("sno", user.getStudentNumber());
            userMap.put("username", user.getUsername());
            userList.add(userMap);
        }
        map.put("userList", userList);
        resp.getWriter().write(JsonUtil.mapToJson(map));
    }

}
