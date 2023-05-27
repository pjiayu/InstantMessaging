package util.dao.subDao;

import Pojo.User;
import Pojo.contentMsg;
import util.dao.BaseDAO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


/**
 * @Title:
 * @Description:
 * @author: anthor
 * @date:2023/5/2720:15
 */
public class contentMsgDao extends BaseDAO<contentMsg> {
    public void addcontentMsg(String send_username,String target_username,String content_msg){
        TimeZone time = TimeZone.getTimeZone("Etc/GMT-8");
        TimeZone.setDefault(time);
        User send_user=new UserDao().getUserByAcc(send_username);
        User target_user=new UserDao().getUserByAcc(target_username);
        super.executeUpdate("insert into content_msg (send_id, target_id, content_msg, send_time) values (?,?,?,?)",send_user.getId(),target_user.getId(),content_msg, LocalDateTime.now());
    }
    public List<contentMsg> getcontentMsg(String send_username,String target_username){
        User send_id=new UserDao().getUserByAcc(send_username);
        User target_id=new UserDao().getUserByAcc(target_username);
        return super.executeQuery("SELECT * FROM content_msg WHERE send_id = ? AND target_id = ? UNION SELECT * FROM content_msg WHERE send_id = ? AND target_id = ? ORDER BY send_time ASC", send_id.getId(), target_id.getId(), target_id.getId(), send_id.getId());
    }
    public static void main(String[] args) {
        System.out.println(new contentMsgDao().getcontentMsg("hzl","pjy"));
    }
}
