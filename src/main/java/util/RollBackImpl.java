package util;

import java.io.*;
import java.net.Socket;

/**
 * @auther 齿轮
 * @create 2023-05-20-10:11
 */
public class RollBackImpl implements RollBack {
    @Override
    public void UserLoginBack(String user) {
        System.out.println(user + "——" + Utils.getUserIP(user));
    }

    @Override
    public void UserTransmitBegin(String username, String goalName) {

    }

    @Override
    public void SendOneLineMessage(String username, String goalName) {
        //从username的缓存区发送字符串到goalName
        Socket goal = Utils.getUserIP(goalName);
        PrintWriter pw = null;
        try {
            OutputStream outputStream = goal.getOutputStream();
            pw = new PrintWriter(outputStream, true);
            pw.println("OneLineMessage");
            pw.println(username);
            pw.println(Utils.TempString.get(username).toString());
            pw.println("bye");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void SendFile(String username, String goalName, String FilePath) {

    }
}
