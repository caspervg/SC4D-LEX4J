package net.caspervg.lex4j;

import net.caspervg.lex4j.auth.Auth;
import net.caspervg.lex4j.bean.DownloadHistoryItem;
import net.caspervg.lex4j.bean.DownloadListItem;
import net.caspervg.lex4j.bean.User;
import net.caspervg.lex4j.route.UserRoute;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.restlet.resource.ResourceException;

import java.io.File;
import java.io.FileInputStream;
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

    private static Properties prop = new Properties();

    @BeforeClass
    public static void setUpClass() {
        try {
            /*
             * If you want to run these tests, place a auth.properties file with your username and password in a directory.
             */
            prop.load(new FileInputStream(new File("E:\\auth.properties")));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
            Assert.assertEquals(dlList.get(0).getRecord().getId(), 13099621);
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
