package util;

import Pojo.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * @auther 齿轮
 * @create 2023-05-20-9:55
 * <p>
 * 监听登录端口，有信息则录入UserIPMap。
 * 默认登录端口为18000。
 * <p>
 * 登入输入格式为：
 * XXX(UserID)
 * bye
 * <p>
 * 登入返回为：
 * 成功：Success
 * 失败：Failure
 * <p>
 * 启动监听登录的流程：
 * 运行Utils.init()。
 * 创建一个AcceptLoginServerImpl，填入对应的RollBack。
 * 创建一个线程，运行AcceptLoginServerImpl的thread的start。
 */
public class AcceptLoginServerImpl implements AcceptLoginServer, Runnable {
    private ServerSocket serverSocket;
    private RollBack rollBack;

    public AcceptLoginServerImpl(RollBack rollBack) {
        this.rollBack = rollBack;
        serverSocket = Utils.serverSocket;
    }

    public AcceptLoginServerImpl(RollBack rollBack, ServerSocket serverSocket) {
        this.rollBack = rollBack;
        this.serverSocket = serverSocket;
    }

    @Override
    public void ListenLogin() {
        try {
            while (true) {
                Socket accept = serverSocket.accept();
                SubAcceptLoginServerImpl server = new SubAcceptLoginServerImpl(accept, rollBack);
                Thread thread = new Thread(server);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        ListenLogin();
    }
}

class SubAcceptLoginServerImpl implements Runnable {
    private Socket socket;
    private RollBack rollBack;

    public SubAcceptLoginServerImpl(Socket socket, RollBack rollBack) {
        this.socket = socket;
        this.rollBack = rollBack;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            br = new BufferedReader(new InputStreamReader(inputStream));
            pw = new PrintWriter(outputStream, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String line;
            String name = null;
            String password = null;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if ("bye".equals(line) && count == 2) {
                    if (!Utils.loginJudge.judge(name, password)) {
                        pw.println("Failure");
                        break;
                    }
                    pw.println("Success");
                    //登录成功就说明要开始监听这个socket并进行转发消息了
                    //需要建立一个线程专门用于接受这个socket发送的消息，前提是该User得知道可以向谁发送信息
                    //格式为：server发送：
                    //friends
                    //chilun1
                    //chilun2
                    //bye
                    //Client发送：
                    //friends:bye
                    while (true) {
                        List<User> friends = Utils.friend.getFriends(name);
                        pw.println("friends");
                        for(int i=0;i<friends.size();i++){
                            pw.println(friends.get(i).getUsername());
                        }
                        pw.println("bye");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //监听有无收到friends
                        String s1 = br.readLine();
                        if ("friends:bye".equals(s1)) {
                            break;
                        }
                    }
                    //然后启动一个线程进行监听消息
                    TCPTransmit tcpTransmit = new TCPTransmit(socket, name, rollBack);
                    Thread thread = new Thread(tcpTransmit);
                    thread.start();
                    //并使用一种方式将别人的往它发的消息通过这个socket发送回去
                    break;
                } else if (count == 0) {//单行用户名
                    count++;
                    name = line;
                    Utils.LoginUser(name, socket);
                    rollBack.UserLoginBack(name);
                } else if (count == 1) {//第二行是密码
                    count++;
                    password = line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
