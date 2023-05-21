package Handler;

import util.RollBack;
import util.Utils;

import java.io.*;
import java.net.Socket;

/**
 * @auther 齿轮
 * @create 2023-05-21-22:54
 */
public class HandlerImpl implements Handler {
    private Socket ClientSocket;
    private String username;
    private RollBack rollBack;

    @Override
    public void handler() throws IOException {
        InputStream inputStream = ClientSocket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        Utils.TempString.put(username, new StringBuilder());
        while ((line = br.readLine()) != null) {
            if (line.equals("OneLineMessage")) {
                OneLineMessage(br);
            }
        }
    }

    public void OneLineMessage(BufferedReader br) throws IOException {
        String line;
        String name = null;
        int count = 0;
        while ((line = br.readLine()) != null) {
            if (count == 0){
                count++;
                name = line;//获得目的用户名
                System.out.println(name);
            }else if(count==1) {
                count++;
                StringBuilder stringBuilder = Utils.TempString.get(username);//将信息存储在源用户名中
                System.out.println(line);
                stringBuilder.append(line);
            }else if(count == 2&&line.equals("bye")){
                //接收完毕，开始发送
                rollBack.SendOneLineMessage(username, name);
                //发送完毕，清空缓存
                Utils.TempString.put(username, new StringBuilder());
                return;
            }
        }
    }

    public HandlerImpl(Socket clientSocket, String username, RollBack rollBack) {
        ClientSocket = clientSocket;
        this.username = username;
        this.rollBack = rollBack;
    }
}
