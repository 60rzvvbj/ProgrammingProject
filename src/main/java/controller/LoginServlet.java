package controller;

import commom.factory.ServiceFactory;
import service.UserService;
import service.impl.UserServiceImpl;
import util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Login", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = ServiceFactory.getUserService();

        String sno = req.getParameter("sno");
        String pwd = req.getParameter("pwd");

        System.out.println(sno);
        System.out.println(pwd);

        boolean status = userService.login(sno, pwd);

        Map<String, Object> data = new HashMap<>();
        data.put("status", status);

        resp.getWriter().write(JsonUtil.mapToJson(data));
        resp.getWriter().close();
    }
}
