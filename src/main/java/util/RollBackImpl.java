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
        Socket goal = Utils.getUserIP(goalName);
        //获得文件名
        int index = 0;
        for (int i = FilePath.length() - 1; i >= 0; i--) {
            if (FilePath.charAt(i) == '+') {
                index = i;
                break;
            }
        }
        //获得文件长度并创建流资源
        File file = null;
        FileInputStream fis = null;
        PrintWriter pw = null;
        OutputStream os = null;
        try {
            file = new File(FilePath);
            fis = new FileInputStream(file);
            pw = new PrintWriter(goal.getOutputStream(), true);
            os = goal.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String FileName = FilePath.substring(index + 1);
        pw.println("File");
        pw.println(username);//第二行为发送方用户
        pw.println(FileName);//第三行为文件名
        pw.println(file.length());//第四行为文件长度
        System.out.println(file.length());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //开始发送
        try {
            byte[] buffer = new byte[1024];
            long totalLen = 0;
            int len;
            while ((len = fis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
                totalLen += len;
            }
            System.out.println(username + " send to " + goalName + "：" + totalLen);
//            Thread.sleep(2000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println("bye");//本次会话结束

        try {
            fis.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void addFriend(String username, String friendName) {
        //从username的缓存区发送好友请求到friendName
        Socket goal = Utils.getUserIP(friendName);
        PrintWriter pw = null;
        try {
            OutputStream outputStream = goal.getOutputStream();
            pw = new PrintWriter(outputStream, true);
            pw.println("addFriendMessage");
            pw.println(username);
            pw.println(Utils.TempString.get(username).toString());
            pw.println("bye");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
