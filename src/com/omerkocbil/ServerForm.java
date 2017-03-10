package com.omerkocbil;

/**
 * Created by jan on 10.03.2017.
 */

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

@WebServlet(name = "serverForm", urlPatterns = {"/server", "/server/start", "/server/stop"})
public class ServerForm extends HttpServlet {

    Server server;

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        if(httpServletRequest.getServletPath().equals("/server/start")){
            server = new Server(Integer.parseInt(httpServletRequest.getParameter("port")));
            server.start();
        }
        else if(httpServletRequest.getServletPath().equals("/server/stop")){
            server.stop();
        }

        String nextJSP = "/server.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(httpServletRequest,httpServletResponse);
    }

    public Socket socket;
    public ObjectInputStream sInput;
    public ObjectOutputStream sOutput;
    public int id;
    public String username;
    public Date ConDate;
    public ClientThread ListenThread;

    public ServerForm(){

    }

    ServerForm(Socket socket) {
        this.id = ++Server.uniqueId;
        this.socket = socket;

        try {
            this.sOutput = new ObjectOutputStream(socket.getOutputStream());
            this.sInput = new ObjectInputStream(socket.getInputStream());

            this.username = (String) sInput.readObject();

            this.ConDate = new Date();
            this.ListenThread = new ClientThread(this);

        } catch (IOException e) {
            Server.display("Exception .... " + e);
            return;
        } catch (ClassNotFoundException e) {

        }

    }

    public void start() {
        this.ListenThread = new ClientThread(this);
        this.ListenThread.start();

    }

    public void close() {
        try {
            if (this.ListenThread != null) {
                this.ListenThread.interrupt();
            }
            if (this.sOutput != null) {
                this.sOutput.close();

            }

            if (this.sInput != null) {
                this.sInput.close();

            }

            if (this.socket != null) {
                this.socket.close();

            }

        } catch (Exception e) {
        }

    }

    public boolean writeMsg(Object msg) {

        if (!this.socket.isConnected()) {
            close();
            return false;
        }

        try {
            this.sOutput.writeObject(msg);
        } catch (Exception e) {
            Server.display("Error sending message to " + username);
            Server.display(e.toString());
        }
        return true;

    }


    public class ClientThread extends Thread {

        ServerForm TheClient;

        public ClientThread(ServerForm TheClient) {
            this.TheClient = TheClient;
        }

        public void run() {

            while (TheClient.socket.isConnected()) {

                try {

                    String message = (String) this.TheClient.sInput.readObject();
                    System.out.println(message);

                } catch (IOException e) {
                    Server.display(this.TheClient.username + "exception reading Streams :" + e);
                    break;

                } catch (ClassNotFoundException ex) {

                    Server.display(this.TheClient.username + "Exception reading Streams " + ex);
                }

            }
            Server.remove(this.TheClient.id);

        }

    }

}
