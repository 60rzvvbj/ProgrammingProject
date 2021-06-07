package controller;

import commom.factory.ServiceFactory;
import service.PairingRequestService;
import util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/pairingRequest")
public class PairingRequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 添加配对
        String sno = req.getParameter("sno");
        String content = req.getParameter("content");

        Map<String, Object> data = new HashMap<>();
        data.put("request", content);

        PairingRequestService pairingRequestService = ServiceFactory.getPairingRequestService();

        String id = pairingRequestService.addPairingRequest(sno, data);
        System.out.println("id:" + id);
        Map<String, Object> map = new HashMap<>();
        boolean status = (id != null && !id.equals("学号不存在"));
        map.put("status", status);

        resp.getWriter().write(JsonUtil.mapToJson(map));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接受配对
        String sno = req.getParameter("sno");
        String id = req.getParameter("id");

        PairingRequestService pairingRequestService = ServiceFactory.getPairingRequestService();
        boolean status = false;
        try {
            status = pairingRequestService.acceptPairing(sno, id);
        } catch (Exception e) {
            e.toString();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("status", status);

        resp.getWriter().write(JsonUtil.mapToJson(map));
    }
}
