package Friend;

import Pojo.Group;
import Pojo.User;

import java.util.List;

/**
 * @auther 齿轮
 * @create 2023-05-20-14:32
 *
 * 这个接口用于得知有哪些朋友，输入本人的昵称，返回朋友的昵称
 */
public interface info {
//    List<String> getFriends(String name);
    List<User> getFriends(String name);
    List<Group> getGroups(String name);
}
