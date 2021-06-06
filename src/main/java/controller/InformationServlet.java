package controller;

import commom.factory.ServiceFactory;
import pojo.FriendRequest;
import pojo.PairingRequest;
import service.InformationService;
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

@WebServlet("/information")
public class InformationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sno = req.getParameter("sno");

        InformationService informationService = ServiceFactory.getInformationService();
        List<Object> infoList = informationService.queryAllInformation(sno);

        Map<String, Object> map = new HashMap<>();
        List<Object> list = new LinkedList<>();

        for (Object o : infoList) {
            if (o.getClass().equals(FriendRequest.class)) {
                FriendRequest friendRequest = (FriendRequest) o;
                Map<String, Object> info = new HashMap<>();
                info.put("id", friendRequest.getRequestID());
                if (friendRequest.getApplicant().equals(sno) && friendRequest.getStatus() != 1) {
                    info.put("type", 1); // 有人回应了我的请求
                } else if (friendRequest.getRequested().equals(sno) && friendRequest.getStatus() == 1){
                    info.put("type", 2); // 有人向我发起了请求
                } else {
                    continue;
                }
                info.put("req", friendRequest.getApplicant());
                info.put("res", friendRequest.getRequested());
                info.put("status", friendRequest.getStatus());
                list.add(info);
            } else if (o.getClass().equals(PairingRequest.class)) {
                PairingRequest pairingRequest = (PairingRequest) o;
                Map<String, Object> info = new HashMap<>();
                map.put("id", pairingRequest.getID());
                if (pairingRequest.getStudentNumber().equals(sno) && pairingRequest.getStatus() == 1) {
                    map.put("res", pairingRequest.getRecipientNumber());
                    map.put("status", pairingRequest.getStatus());
                }
                list.add(info);
            }
        }
        map.put("infoList", list);

        resp.getWriter().write(JsonUtil.mapToJson(map));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String type = req.getParameter("type");

        InformationService informationService = ServiceFactory.getInformationService();
        boolean status = informationService.removeInformationService(id, type);

        Map<String, Object> map = new HashMap<>();
        map.put("status", status);

        resp.getWriter().write(JsonUtil.mapToJson(map));
    }
}