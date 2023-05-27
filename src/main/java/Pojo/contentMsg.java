package Pojo;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @Title:
 * @Description:
 * @author: anthor
 * @date:2023/5/2720:15
 */
public class contentMsg {
    private int id;
    private int send_id;
    private int target_id;
    private String content_msg;
    private LocalDateTime send_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSend_id() {
        return send_id;
    }

    public void setSend_id(int send_id) {
        this.send_id = send_id;
    }

    public int getTarget_id() {
        return target_id;
    }

    public void setTarget_id(int target_id) {
        this.target_id = target_id;
    }

    public String getContent_msg() {
        return content_msg;
    }

    public void setContent_msg(String content_msg) {
        this.content_msg = content_msg;
    }

    public LocalDateTime getSend_time() {
        return send_time;
    }

    public void setSend_time(LocalDateTime send_time) {
        this.send_time = send_time;
    }

    public contentMsg() {
    }

    public contentMsg(int id, int send_id, int target_id, String content_msg, LocalDateTime send_time) {
        this.id = id;
        this.send_id = send_id;
        this.target_id = target_id;
        this.content_msg = content_msg;
        this.send_time = send_time;
    }
}
