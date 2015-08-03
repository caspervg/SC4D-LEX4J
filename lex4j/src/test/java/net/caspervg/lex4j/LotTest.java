package net.caspervg.lex4j;

import net.caspervg.lex4j.auth.Auth;
import net.caspervg.lex4j.bean.Comment;
import net.caspervg.lex4j.bean.DependencyList;
import net.caspervg.lex4j.bean.Lot;
import net.caspervg.lex4j.route.LotRoute;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class LotTest {

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
    public void lotTest() {
        Auth auth = new Auth(prop.getProperty("username"),prop.getProperty("password"));
        LotRoute route = new LotRoute(auth);
        try {
            Lot lot = route.getLot(950);
            Assert.assertEquals(lot.getName(), "CAM Commercial Offices BSC");

            lot = route.getLot(3129);
            Assert.assertEquals(lot.getFilesize(), "1.30 MB");
        } catch (ResourceException ex) {
            Status stat = ex.getStatus();
            System.out.println(stat.getDescription());
            System.out.println(stat.getUri());
            System.out.println(stat.getCode());
            System.out.println(stat.getReasonPhrase());
            System.out.println(stat.isClientError());
        }
    }

    @Test
    public void lotDependencyNullTest() {
        Auth auth = new Auth(prop.getProperty("username"),prop.getProperty("password"));
        LotRoute route = new LotRoute(auth);
        try {
            Lot lot = route.getLot(2987);
            Assert.assertNotNull(lot.getDependencyList());
            Assert.assertNotNull(lot.getDependencyList().getList());
            Assert.assertNotEquals(0, lot.getDependencyList().getCount());
            Assert.assertNotEquals(0, lot.getDependencyList().getList().size());
        } catch (ResourceException ex) {
            System.err.println(ex);
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
    @Ignore
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
            Assert.assertEquals(35538, list.get(0).getId());
        } catch (ResourceException ex) {
            System.out.println(ex.getStatus());
        }
    }

}
