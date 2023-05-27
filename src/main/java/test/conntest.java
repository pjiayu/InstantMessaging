package test;

import Friend.infoImpl;
import Pojo.Group;
import Pojo.User;
import util.dao.ConnUtil;
import util.dao.subDao.GroupDao;
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
        //根据群聊名获取所有users
//        List<User>userList=userDao.getAllUsersByGroup("四人帮");
//        System.out.println(userList);

        //加入群聊
//        GroupDao groupDao=new GroupDao();
//        groupDao.joinGroup("ppp","四人帮");
        //根据username获取群列表
//        List<Group> list=groupDao.getAllGroup("chilun");
//        System.out.println(list);

    }
}
