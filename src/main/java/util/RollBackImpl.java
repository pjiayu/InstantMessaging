package util;

import Pojo.contentMsg;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import util.dao.subDao.contentMsgDao;
import com.alibaba.fastjson.JSON;
import java.io.*;
import java.net.Socket;
import java.util.List;

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
        if (goal==null){
            return;
        }
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
        System.out.println("**********************"+file.length());
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
        Socket own=Utils.getUserIP(username);
        if(goal==null){
            return;
        }
        PrintWriter pw = null;
        PrintWriter pw_own=null;
        try {
            //给目标好友返回加好友结果
            OutputStream outputStream = goal.getOutputStream();
            pw = new PrintWriter(outputStream, true);
            pw.println("addFriendMessage");
            pw.println(username);
            //预期是 目标好友那显示：xx给你发送了好友请求
            pw.println(Utils.TempString.get(username).toString());
            pw.println("bye");

            //给自己返回结果
            OutputStream outputStream_own=own.getOutputStream();
            pw_own=new PrintWriter(outputStream_own,true);
            pw_own.println("addFriendMessage");
            pw_own.println(friendName);
            pw_own.println(Utils.TempString.get(friendName).toString());
            pw_own.println("bye");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addContentMsg(String send_user, String target_user, String content_msg) {
        try {
            new contentMsgDao().addcontentMsg(send_user,target_user,content_msg);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void getAllContentMsg(String send_user, String target_user) {
        try {
            Socket goal = Utils.getUserIP(target_user);
            List<contentMsg> tmpcontentMsg=new contentMsgDao().getcontentMsg(send_user,target_user);
            JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(tmpcontentMsg));
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                switch (jsonObject.getIntValue("send_id")){
                    case 1:
                        jsonObject.put("send_id","ppp");
                        break;
                    case 2:
                        jsonObject.put("send_id","pjy");
                        break;
                    case 3:
                        jsonObject.put("send_id","chilun");
                        break;
                    case 4:
                        jsonObject.put("send_id","hzl");
                        break;
                    case 5:
                        jsonObject.put("send_id","cqy");
                        break;
                }
            }
            String jsonString = JSON.toJSONString(jsonArray);
            PrintWriter pw = null;
            try {
                OutputStream outputStream = goal.getOutputStream();
                pw = new PrintWriter(outputStream, true);
                pw.println("getAllContentMsg");
                pw.println(jsonString);
                pw.println("bye");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RollBackImpl().getAllContentMsg("hzl","pjy");
    }
}
