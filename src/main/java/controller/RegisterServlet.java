package controller;

import commom.factory.ServiceFactory;
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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = ServiceFactory.getUserService();

        String sno = req.getParameter("sno");
        String username = req.getParameter("username");
        String pwd = req.getParameter("pwd");

        boolean status;
        String message = null;
        Map<String, Object> map = new HashMap<>();

        if (sno == null || username == null || pwd == null) {
            status = false;
            message = "信息有误";
        } else {
            status = userService.register(sno, pwd, username);
            if (!status) {
                message = "该学号已被注册";
            }
        }

        map.put("status", status);
        map.put("message", message);

        resp.getWriter().write(JsonUtil.mapToJson(map));
        resp.getWriter().close();
    }
}
