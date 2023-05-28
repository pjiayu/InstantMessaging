package util;

import Friend.info;
import Friend.infoImpl;
import login.LoginJudge;
import login.LoginJudgeImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther 齿轮
 * @create 2023-05-20-9:46
 */
public class Utils {
    public static ServerSocket serverSocket;
    private static Map<String, Socket> UsersIPMap;
    public static Map<String, StringBuilder> TempString;//中转消息时用于存储消息。
    public static info friend;
    public static LoginJudge loginJudge;
    public static String storePath = "D:\\tempFile\\";

    public static void init() {
        UsersIPMap = new HashMap<>();
        TempString = new HashMap<>();
        try {
            serverSocket = new ServerSocket(20001);
        } catch (IOException e) {
            e.printStackTrace();
        }
        friend = new infoImpl();
        loginJudge = new LoginJudgeImpl();
    }

    public static void LoginUser(String name, Socket socket) {
        UsersIPMap.put(name, socket);
    }

    public static void LoginOutUser(String name) {
        UsersIPMap.remove(name);
    }

    public static Socket getUserIP(String name) {
        return UsersIPMap.get(name);
    }
}
