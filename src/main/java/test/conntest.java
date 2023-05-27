package test;

import Friend.infoImpl;
import Pojo.User;
import util.dao.ConnUtil;
import util.dao.subDao.UserDao;

import java.sql.Connection;
import java.util.List;

/**
 * @author 皮皮皮
 * @date 2023/5/26 16:14
 */
public class conntest {
    public static void main(String[] args) {
//        Connection connection= ConnUtil.getConn();
//        System.out.println(connection);
        UserDao userDao=new UserDao();
        infoImpl info=new infoImpl();
//        User user=userDao.getUserByAcc("pjy");
//        System.out.println(user);

        //获取好友列表
        List<User>friendlist=info.getFriends("ppp");
        System.out.println(friendlist);
        //添加好友
//        userDao.addFriend("pjy","chilun");
        //创建群聊
//        userDao.createGroup("pjy","1234");
    }
}
