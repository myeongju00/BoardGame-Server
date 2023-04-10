package Model;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public static List<User> users = new ArrayList<>(); //Client 객체 리스트

    public static void add(User user) {
        users.add(user);
    }
}
