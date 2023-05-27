package Pojo;

/**
 * @author 皮皮皮
 * @date 2023/5/26 16:38
 */
public class Group {
    int id;
    String groupname;
    int create_uid;

    public Group() {
    }

    public Group(String groupname, int create_uid) {
        this.groupname = groupname;
        this.create_uid = create_uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public int getCreate_uid() {
        return create_uid;
    }

    public void setCreate_uid(int create_uid) {
        this.create_uid = create_uid;
    }
    @Override
    public String toString(){
        return "Group{" +
                "id=" + id+
                ", groupname='" + groupname + '\'' +
                ", create_uid='" + create_uid + '\'' +
                '}';
    }
}
