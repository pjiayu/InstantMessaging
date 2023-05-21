package util;

/**
 * @auther 齿轮
 * @create 2023-05-20-8:58
 */
public interface RollBack {
    void UserLoginBack(String user);
    void UserTransmitBegin(String username,String goalName);
    void SendOneLineMessage(String username,String goalName);
}
