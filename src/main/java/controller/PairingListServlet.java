package controller;


import commom.factory.ServiceFactory;
import pojo.PairingRequest;
import service.PairingRequestService;
import service.UserService;
import service.impl.PairingRequestServiceImpl;
import service.impl.UserServiceImpl;
import util.AlgorithmUtil;
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

@WebServlet("/pairingList")
public class PairingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 查询配对
        PairingRequestService pairingRequestService = ServiceFactory.getPairingRequestService();
        UserService userService = ServiceFactory.getUserService();

        String sno = req.getParameter("sno");
        String type = req.getParameter("type");
        String search = req.getParameter("content");
        List<PairingRequest> list;

        if (sno == null) {
            list = pairingRequestService.queryPairingRequest();
        } else {
            list = pairingRequestService.queryUserPairing(sno, type, search);
        }

        AlgorithmUtil.mergeSort(list);

        Map<String, Object> map = new HashMap<>();
        List<Object> res = new LinkedList<>();
        for (PairingRequest pairingRequest : list) {
            if (pairingRequest.getRecipientNumber() != null) {
                continue;
            }
            Map<String, Object> pr = new HashMap<>();
            pr.put("id", pairingRequest.getID());
            pr.put("startTime", pairingRequest.getStartTime());
            pr.put("content", pairingRequest.getRequest());
            sno = pairingRequest.getStudentNumber();
            pr.put("sno", sno);
            String username = userService.queryUserByStudentNumber(pairingRequest.getStudentNumber()).getUsername();
            pr.put("username", username);
            res.add(pr);
        }

        map.put("list", res);
        resp.getWriter().write(JsonUtil.mapToJson(map));
    }

}
