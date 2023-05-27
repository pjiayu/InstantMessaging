package Friend;

import Pojo.User;
import util.dao.subDao.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther 齿轮
 * @create 2023-05-20-14:34
 */
public class infoImpl implements info{
    UserDao userDao=new UserDao();
    @Override
    public List<User> getFriends(String name) {
        return  userDao.getFriends(name);
    }
//    @Override
//    public List<String> getFriends(String name) {
//        List<String> friends = new ArrayList<>();
//        friends.add("chi1");
//        friends.add("chi2");
//        return friends;
//    }


}
