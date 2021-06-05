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
import java.util.Map;

@WebServlet("/userInformation")
public class UserInformationServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取用户信息
        UserService userService = ServiceFactory.getUserService();

        String sno = req.getParameter("sno");
        String type = req.getParameter("type");

        User user = userService.queryUserByStudentNumber(sno);

        Map<String, Object> map = new HashMap<>();
        if (user == null) {
            map.put("status", false);
        } else {
            map.put("status", true);
            map.put("username", user.getUsername());
            map.put("sex", user.getSex());
            if (type.equals("complete")) {
                map.put("height", user.getHeight());
                map.put("weight", user.getWeight());
                map.put("personalProfile", user.getPersonalProfile());
                map.put("contactInformation", user.getContactInformation());
                map.put("age", user.getAge());
            }
        }

        resp.getWriter().write(JsonUtil.mapToJson(map));
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 修改用户信息
        UserService userService = ServiceFactory.getUserService();

        String sno = req.getParameter("sno");
        Map<String, Object> map = new HashMap<>();
        map.put("username", req.getParameter("username"));
        map.put("sex", req.getParameter("sex"));
        map.put("age", req.getParameter("age"));
        map.put("height", req.getParameter("height"));
        map.put("weight", req.getParameter("weight"));
        map.put("personalProfile", req.getParameter("personalProfile"));
        map.put("contactInformation", req.getParameter("contactInformation"));
        boolean status = userService.editInformation(sno, map);

        Map<String, Object> res = new HashMap<>();
        res.put("status", status);

        resp.getWriter().write(JsonUtil.mapToJson(res));

    }
}
