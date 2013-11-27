package net.caspervg.lex4j;

import net.caspervg.lex4j.auth.Auth;
import net.caspervg.lex4j.bean.Comment;
import net.caspervg.lex4j.bean.DependencyList;
import net.caspervg.lex4j.bean.Lot;
import net.caspervg.lex4j.route.LotRoute;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

import java.io.*;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 12:33
 */
public class LotTest {

    private static Properties prop = new Properties();

    @BeforeClass
    public static void setUpClass() {
        try {
            /*
             * If you want to run these tests, place a auth.properties file with your username and password in a directory.
             */
            prop.load(new FileInputStream(new File("C:\\Users\\Casper\\IdeaProjects\\SC4Devotion-LEXBrowser\\LEXBrowser\\src\\test\\java\\net\\caspervg\\lex4j\\auth.properties")));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void lotTest() {
        LotRoute route = new LotRoute();
        try {
            Lot lot = route.getLot(950);
            Assert.assertEquals(lot.getName(), "CAM Commercial Offices BSC");
        } catch (ResourceException ex) {
            Status stat = ex.getStatus();
            System.out.println(stat.getDescription());
            System.out.println(stat.getUri());
            System.out.println(stat.getCode());
            System.out.println(stat.getName());
            System.out.println(stat.isClientError());
        }
    }

    @Test
    public void lotAllTest() {
        LotRoute route = new LotRoute();
        List<Lot> lots = route.getLotList();

        Assert.assertEquals(2, lots.get(0).getId());
        Assert.assertEquals("CSX Farm SF - Veronique", lots.get(0).getName());
        Assert.assertNull(lots.get(0).getLink());
    }

    @Test
    public void lotDownloadTest() {
        Auth auth = new Auth(prop.getProperty("username"), prop.getProperty("password"));
        LotRoute route = new LotRoute(auth);

        try {
            File f = new File("E:\\Binary");
            route.getLotDownload(50, f);
        } catch (ResourceException ex) {
            System.out.println(ex.getStatus());
        }
    }

    @Test
    public void lotDependencyTest() {
        LotRoute route = new LotRoute();
        try {
            DependencyList list = route.getDependency(950);
            Assert.assertEquals(76, list.getCount());
        } catch (ResourceException ex) {
            System.out.println(ex.getStatus());
        }
    }

    @Test
    public void lotCommentTest() {
        LotRoute route = new LotRoute();
        try {
            List<Comment> list = route.getComment(900);
            System.out.println(list.get(0).getDate());
            //Assert.assertEquals(34299, list.get(0).getId());
        } catch (ResourceException ex) {
            System.out.println(ex.getStatus());
        }
    }

}
