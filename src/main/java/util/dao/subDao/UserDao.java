package util.dao.subDao;

import Pojo.User;
import util.dao.BaseDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 皮皮皮
 * @date 2023/5/26 16:34
 */
public class UserDao extends BaseDAO<User> {
    public User getUserByAcc(String acc){
        return super.load("select * from user where username = ?",acc);
    }
    public List<User> getFriends(String username){
        User user=super.load("select * from user where username=?",username);
        //System.out.println(user);
        return super.executeQuery("select *from user where id IN(select friend_uid from friendlist where owner_uid=?)",user.getId());
    }
    public void addFriend(String owner_username,String friend_username){
        User owner=super.load("select * from user where username=?",owner_username);
//        System.out.println(owner);
        User friend=super.load("select * from user where username=?",friend_username);
//        System.out.println(friend);
        super.executeUpdate("insert into friendlist value(0,?,?,?,?)",owner.getId(),owner_username,friend.getId(),friend_username);
        super.executeUpdate("insert into friendlist value(0,?,?,?,?)",friend.getId(),friend_username,owner.getId(),owner_username);
    }
}
