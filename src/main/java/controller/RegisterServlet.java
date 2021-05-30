package controller;

import commom.factory.ServiceFactory;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = ServiceFactory.getUserService();

        String sno = req.getParameter("sno");
        String username = req.getParameter("username");
        String pwd = req.getParameter("pwd");

        boolean status = userService.register(sno, pwd, username);

        resp.getWriter().write("{\"status\": " + status + "}");
        resp.getWriter().close();
    }
}
