package net.caspervg.lex4j;

import net.caspervg.lex4j.auth.Auth;
import net.caspervg.lex4j.bean.Comment;
import net.caspervg.lex4j.bean.DependencyList;
import net.caspervg.lex4j.bean.Lot;
import net.caspervg.lex4j.error.LEX4JStatusException;
import net.caspervg.lex4j.route.LotRoute;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 12:33
 */
public class LotTest {

    @Test
    public void lotTest() {
        Properties prop = new Properties();

        try {
            prop.load(this.getClass().getResourceAsStream("/auth.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Auth auth = new Auth(prop.getProperty("auth.username"),prop.getProperty("auth.password"));
        LotRoute route = new LotRoute(auth);
        Lot lot = null;
        try {
            lot = route.getLot(950);
            Assert.assertEquals(lot.getName(), "CAM Commercial Offices BSC");
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void lotAllTest() {
        Properties prop = new Properties();

        try {
            prop.load(this.getClass().getResourceAsStream("/auth.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Auth auth = new Auth(prop.getProperty("auth.username"), prop.getProperty("auth.password"));
        LotRoute route = new LotRoute(auth);
        List<Lot> lots = null;
        try {
            lots = route.getLotList();
        } catch (Throwable throwable) {
            throwable.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Assert.assertEquals(lots.get(0).getName(), "CSX Farm SF - Veronique");
    }

    @Test
    public void lotDownloadTest() {
        Properties prop = new Properties();

        try {
            prop.load(this.getClass().getResourceAsStream("/auth.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Auth auth = new Auth(prop.getProperty("auth.username"), prop.getProperty("auth.password"));
        LotRoute route = new LotRoute(auth);
        try {
            route.getLotDownload(50, new FileOutputStream("C:\\Binary\\download4.zip"));
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Test
    public void lotDependencyTest() {
        Properties prop = new Properties();

        try {
            prop.load(this.getClass().getResourceAsStream("/auth.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Auth auth = new Auth(prop.getProperty("auth.username"), prop.getProperty("auth.password"));
        LotRoute route = new LotRoute(auth);
        try {
            DependencyList list = route.getDependency(950);
            Assert.assertEquals(76, list.getCount());
        } catch (LEX4JStatusException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void lotCommentTest() {
        Properties prop = new Properties();

        try {
            prop.load(this.getClass().getResourceAsStream("/auth.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Auth auth = new Auth(prop.getProperty("auth.username"), prop.getProperty("auth.password"));
        LotRoute route = new LotRoute(auth);
        try {
            List<Comment> list = route.getComment(950);
            Assert.assertEquals(34299, list.get(0).getId());
        } catch (LEX4JStatusException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
