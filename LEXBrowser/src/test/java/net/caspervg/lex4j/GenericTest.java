package net.caspervg.lex4j;

import junit.framework.Assert;
import net.caspervg.lex4j.auth.Auth;
import net.caspervg.lex4j.bean.*;
import net.caspervg.lex4j.route.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.resource.ResourceException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class GenericTest {

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
    public void genericLotTest() {
        LotRoute route = new LotRoute();
        try {
            ExtendedLot lot = route.getLot(950, ExtendedLot.class);
            Assert.assertEquals("CAM Commercial Offices BSC", lot.getName());
            Assert.assertEquals("This is an extended lot", lot.showMessage());
        } catch (ResourceException ex) {
            System.err.println(ex.getStatus());
        }
    }

    @Test
    public void genericLotListTest() {
        LotRoute route = new LotRoute();
        try {
            List<ExtendedLot> lots = route.getLotList(ExtendedLot.class);

            Assert.assertEquals(2, lots.get(0).getId());
            Assert.assertEquals("CSX Farm SF - Veronique", lots.get(0).getName());
            Assert.assertEquals("This is an extended lot", lots.get(0).showMessage());
        } catch (ResourceException ex) {
            System.err.println(ex.getStatus());
        }
    }

    @Test
    public void genericDepListTest() {
        LotRoute route = new LotRoute();
        try {
            ExtendedDependencyList list = route.getDependencyList(950, ExtendedDependencyList.class);
            Assert.assertEquals(76, list.getCount());
            Assert.assertEquals("This is an extended dependency list", list.showMessage());
        } catch (ResourceException ex) {
            System.out.println(ex.getStatus());
        }
    }

    @Test
    public void genericSearchTest() {

        SearchRoute route = new SearchRoute();
        route.addFilter(Filter.AMOUNT, 100);
        route.addFilter(Filter.CONCISE, false);
        route.addFilter(Filter.START, 0);
        route.addFilter(Filter.CREATOR, 1);
        route.addFilter(Filter.TITLE, "concorde");

        List<ExtendedLot> lots = route.doSearch(ExtendedLot.class);
        Assert.assertEquals(1007, lots.get(0).getId());
        Assert.assertEquals("This is an extended lot", lots.get(0).showMessage());
    }

    @Test
    public void genericUserTest() {
        Auth auth = new Auth(prop.getProperty("username"), prop.getProperty("password"));
        UserRoute route = new UserRoute(auth);

        ExtendedUser user = route.getUser(ExtendedUser.class);
        Assert.assertEquals("caspervg", user.getUsername());
        Assert.assertEquals("This is an extended user", user.showMessage());

        user = route.getUser(1, ExtendedUser.class);
        Assert.assertEquals("ADMIN", user.getUsername());
        Assert.assertEquals("This is an extended user", user.showMessage());
    }

    @Test
    public void genericDownloadHistoryListTest() {
        Auth auth = new Auth(prop.getProperty("username"), prop.getProperty("password"));
        UserRoute route = new UserRoute(auth);

        List<ExtendedDownloadHistoryItem> historyItems = route.getDownloadHistory(ExtendedDownloadHistoryItem.class);
        Assert.assertEquals("This is an extended download history item", historyItems.get(0).showMessage());

        List<ExtendedDownloadListItem> listItems = route.getDownloadList(ExtendedDownloadListItem.class);
        Assert.assertEquals("This is an extended download list item", listItems.get(0).showMessage());
    }

    @Test
    public void genericCategoryTest() {
        CategoryRoute route = new CategoryRoute();

        List<ExtendedCategory> categories = route.getBroadCategories(ExtendedCategory.class);
        Assert.assertEquals("This is an extended category", categories.get(0).showMessage());

        categories = route.getLEXCategories(ExtendedCategory.class);
        Assert.assertEquals("This is an extended category", categories.get(0).showMessage());

        categories = route.getLEXTypes(ExtendedCategory.class);
        Assert.assertEquals("This is an extended category", categories.get(0).showMessage());

        categories = route.getLotAuthors(ExtendedCategory.class);
        Assert.assertEquals("This is an extended category", categories.get(0).showMessage());

        categories = route.getLotGroups(ExtendedCategory.class);
        Assert.assertEquals("This is an extended category", categories.get(0).showMessage());

        ExtendedCategoryOverview overview = route.getCategories(ExtendedCategoryOverview.class);
        Assert.assertEquals("This is an extended category overview", overview.showMessage());
    }

    private class ExtendedLot extends Lot {
        public String showMessage() {
            return "This is an extended lot";
        }
    }

    private class ExtendedDependencyList extends DependencyList {
        public String showMessage() {
            return "This is an extended dependency list";
        }
    }

    private class ExtendedUser extends User {
        public String showMessage() {
            return "This is an extended user";
        }
    }

    private class ExtendedDownloadHistoryItem extends DownloadHistoryItem {
        public String showMessage() {
            return "This is an extended download history item";
        }
    }

    private class ExtendedDownloadListItem extends DownloadListItem {
        public String showMessage() {
            return "This is an extended download list item";
        }
    }

    private class ExtendedCategory extends Category {
        public String showMessage() {
            return "This is an extended category" ;
        }
    }

    private class ExtendedCategoryOverview extends CategoryOverview {
        public String showMessage() {
            return "This is an extended category overview" ;
        }
    }
}
