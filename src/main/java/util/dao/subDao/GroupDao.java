package util.dao.subDao;

import Pojo.Group;
import Pojo.User;
import util.dao.BaseDAO;

import java.util.List;

/**
 * @author 皮皮皮
 * @date 2023/5/26 16:34
 */
public class GroupDao extends BaseDAO<Group> {
    //加入群聊
    public void joinGroup(String username,String groupName){
        Group group=super.load("select id,groupname,create_uid from `group` where groupname=?",groupName);
        //System.out.println(group);
        super.executeUpdate("insert into group_and_user (group_id,user_id)values(?,(select id from user where username=?))",group.getId(),username);
    }
    public List<Group> getAllGroup(String username){
        return super.executeQuery("select id,groupname,create_uid from `group` where id in (select group_id from group_and_user where user_id=(select id from user where username=?))",username);
    }
}
