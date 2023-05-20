package Friend;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther 齿轮
 * @create 2023-05-20-14:34
 */
public class infoImpl implements info{
    @Override
    public List<String> getFriends(String name) {
        List<String> friends = new ArrayList<>();
        friends.add("chi1");
        friends.add("chi2");
        return friends;
    }
}
