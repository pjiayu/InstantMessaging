package util;

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

    public TCPTransmit(Socket clientSocket, String username, RollBack rollBack) {
        ClientSocket = clientSocket;
        this.username = username;
        this.rollBack = rollBack;
    }

    @Override
    public void run() {
        ReadInfo();
    }


    @Override
    public void ReadInfo() {
        //监听消息流程：第一行为目的用户，后续为内容（以*开头），最后一行为bye
        BufferedReader br = null;
        try {
            InputStream inputStream = ClientSocket.getInputStream();
            OutputStream outputStream = ClientSocket.getOutputStream();
            br = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter pw = new PrintWriter(outputStream, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try  {
            String line;
            String name = null;
            Utils.TempString.put(username, new StringBuilder());
            while ((line = br.readLine()) != null) {
                if (line.charAt(0) != '*') {
                    if (line.equals("bye")) {
                        //接收完毕，开始发送
                        rollBack.UserTransmitBegin(username, name);
                        //发送完毕，清空缓存
                        Utils.TempString.put(username, new StringBuilder());
                    } else {
                        name = line;//获得目的用户名
                        System.out.println(name);
                    }
                } else {//存储
                    StringBuilder stringBuilder = Utils.TempString.get(username);//将信息存储在源用户名中
                    System.out.println(line);
                    stringBuilder.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
