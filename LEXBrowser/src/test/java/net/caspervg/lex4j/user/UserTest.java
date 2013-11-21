package net.caspervg.lex4j.user;

import net.caspervg.lex4j.auth.Auth;
import net.caspervg.lex4j.bean.DownloadHistoryItem;
import net.caspervg.lex4j.bean.DownloadListItem;
import net.caspervg.lex4j.bean.User;
import net.caspervg.lex4j.route.UserRoute;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 12:33
 */
public class UserTest {

    @Test
    public void userTest() {
        Properties prop = new Properties();

        try {
            prop.load(this.getClass().getResourceAsStream("/auth.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Auth auth = new Auth(prop.getProperty("auth.username"),prop.getProperty("auth.password"));
        UserRoute route = new UserRoute(auth);
        User user = route.getUser();
        System.out.println(user.getFullname());
    }

    @Test
    public void userOtherTest() {
        Properties prop = new Properties();

        try {
            prop.load(this.getClass().getResourceAsStream("/auth.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Auth auth = new Auth(prop.getProperty("auth.username"), prop.getProperty("auth.password"));
        UserRoute route = new UserRoute(auth);
        User user = route.getUser(1);
        System.out.println(user.getUsername());
    }

    @Test
    public void userAllTest() {
        Properties prop = new Properties();

        try {
            prop.load(this.getClass().getResourceAsStream("/auth.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Auth auth = new Auth(prop.getProperty("auth.username"), prop.getProperty("auth.password"));
        UserRoute route = new UserRoute(auth);
        List<User> user = route.getUserList(false,10,30);
        System.out.println(user.get(0).getFullname());
    }

    //@Test
    public void userRegisterTest() {
        Properties prop = new Properties();

        try {
            prop.load(this.getClass().getResourceAsStream("/auth.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Auth auth = new Auth("lex4j_test", "test");
        UserRoute route = new UserRoute(auth);
        //route.postRegistration("lex4j_test", "test", "lolcode@lol", "LEX4J Testaccount");
        User user = route.getUser();
        System.out.println(user.getFullname());
    }

    @Test
    public void downloadListTest() {
        Properties prop = new Properties();

        try {
            prop.load(this.getClass().getResourceAsStream("/auth.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Auth auth = new Auth(prop.getProperty("auth.username"), prop.getProperty("auth.password"));
        UserRoute route = new UserRoute(auth);
        List<DownloadListItem> dlList = route.getDownloadList();
        System.out.println(dlList.get(0).getLot().getName());
    }

    @Test
    public void downloadHistoryTest() {
        Properties prop = new Properties();

        try {
            prop.load(this.getClass().getResourceAsStream("/auth.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Auth auth = new Auth(prop.getProperty("auth.username"), prop.getProperty("auth.password"));
        UserRoute route = new UserRoute(auth);
        List<DownloadHistoryItem> dlList = route.getDownloadHistory();
        System.out.println(dlList.get(0).getLot().getName());
    }
}
