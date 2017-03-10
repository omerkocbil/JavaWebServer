package com.omerkocbil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jan on 10.03.2017.
 */

@WebServlet(name = "clientForm", urlPatterns = {"/client", "/client/start", "/client/stop", "/client/send"})
public class ClientForm extends HttpServlet {

    ClientPrev client;

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        if(httpServletRequest.getServletPath().equals("/client/start")){
            client = new ClientPrev(httpServletRequest.getParameter("ip"), Integer.parseInt(httpServletRequest.getParameter("port")), "omerkocbil");
            client.start();
        }
        else if(httpServletRequest.getServletPath().equals("/client/stop")){
            client.disconnect();
        }
        else if(httpServletRequest.getServletPath().equals("/client/send")){
            client.sendMessage(httpServletRequest.getParameter("message"));
        }

        String nextJSP = "/client.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(httpServletRequest,httpServletResponse);
    }

}
