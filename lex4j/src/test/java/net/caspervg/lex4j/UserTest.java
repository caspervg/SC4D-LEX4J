package net.caspervg.lex4j;

import net.caspervg.lex4j.auth.Auth;
import net.caspervg.lex4j.bean.DownloadHistoryItem;
import net.caspervg.lex4j.bean.DownloadListItem;
import net.caspervg.lex4j.bean.User;
import net.caspervg.lex4j.route.UserRoute;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.resource.ResourceException;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class UserTest {

    private static Properties prop = new Properties();

    @BeforeClass
    public static void setUpClass() {
        try {
            // If you want to run these tests, place a auth.properties file with your username and password in a directory.
            prop.load(ClassLoader.getSystemResourceAsStream("auth.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void userTest() {
        Auth auth = new Auth(prop.getProperty("username"),prop.getProperty("password"));
        UserRoute route = new UserRoute(auth);
        try {
            User user = route.getUser();
            Assert.assertEquals(user.getUsername(), "caspervg");
        } catch (ResourceException ex) {
            System.out.println(ex.getStatus());
        }
    }

    /**
     * This test will only work if you have Administrator access to the LEX
     */
    @Test
    public void userOtherTest() {
        Auth auth = new Auth(prop.getProperty("username"), prop.getProperty("password"));
        UserRoute route = new UserRoute(auth);
        try {
            User user = route.getUser(1);
            Assert.assertEquals(user.getUsername(), "ADMIN");
        } catch (ResourceException ex) {
            System.out.println(ex.getStatus());
        }
    }

    /**
     * This test will only work if you have Administrator access to the LEX
     */
    @Test
    public void userAllTest() {
        Auth auth = new Auth(prop.getProperty("username"), prop.getProperty("password"));
        UserRoute route = new UserRoute(auth);
        try {
            List<User> users = route.getUserList(false,10,30);
            Assert.assertEquals(users.get(0).getUsername(), "barbyw");
        } catch (ResourceException ex) {
            System.out.println(ex.getStatus());
        }
    }

    @Test
    public void downloadListTest() {
        Auth auth = new Auth(prop.getProperty("username"), prop.getProperty("password"));
        UserRoute route = new UserRoute(auth);
        try {
            List<DownloadListItem> dlList = route.getDownloadList();
            Assert.assertTrue(dlList.size() > 0);
        } catch (ResourceException ex) {
            System.out.println(ex.getStatus());
        }
    }

    @Test
    public void downloadHistoryTest() {
        Auth auth = new Auth(prop.getProperty("username"), prop.getProperty("password"));
        UserRoute route = new UserRoute(auth);
        try {
            List<DownloadHistoryItem> dlList = route.getDownloadHistory();
            Assert.assertEquals(dlList.get(0).getRecord().getId(), 12850364);
        } catch (ResourceException ex) {
            System.out.println(ex.getStatus());
        }
    }
}
