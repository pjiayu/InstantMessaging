package util;

import Handler.Handler;
import Handler.HandlerImpl;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * @auther 齿轮
 * @create 2023-05-20-9:07
 */
public class TCPTransmit implements Transmit, Runnable {
    private Socket ClientSocket;
    private String username;
    private RollBack rollBack;
    private Handler handler;

    public TCPTransmit(Socket clientSocket, String username, RollBack rollBack) {
        ClientSocket = clientSocket;
        this.username = username;
        this.rollBack = rollBack;
        handler = new HandlerImpl(ClientSocket, username, rollBack);
    }

    @Override
    public void run() {
        ReadInfo();
    }


    @Override
    public void ReadInfo() {
        //监听消息
        try {
            handler.handler();
        } catch (IOException e) {//报错说明退出了，从UsersIPMap中删除
            Utils.LoginOutUser(username);
            Utils.TempString.remove(username);
        }
    }


}
