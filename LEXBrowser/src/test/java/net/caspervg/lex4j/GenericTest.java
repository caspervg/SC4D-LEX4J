package net.caspervg.lex4j;

import junit.framework.Assert;
import net.caspervg.lex4j.bean.DependencyList;
import net.caspervg.lex4j.bean.Lot;
import net.caspervg.lex4j.route.LotRoute;
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
            prop.load(new FileInputStream(new File("C:\\Users\\Gebruiker\\IdeaProjects\\SC4D-LEX4J\\LEXBrowser\\auth.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GenericLotTest() {
        LotRoute route = new LotRoute();
        try {
            ExtendedLot lot = route.getLot(950, ExtendedLot.class);
            Assert.assertEquals("CAM Commercial Offices BSC", lot.getName());
            Assert.assertEquals("This is an extended lot", lot.showMessage());
        } catch (ResourceException ex) {
            System.err.println(ex.getStatus());
        }
    }

    public void GenericLotListTest() {
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

    public void GenericDepListTest() {
        LotRoute route = new LotRoute();
        try {
            ExtendedDependencyList list = route.getDependencyList(950, ExtendedDependencyList.class);
            Assert.assertEquals(76, list.getCount());
            Assert.assertEquals("This is an extended dependency list", list.showMessage());
        } catch (ResourceException ex) {
            System.out.println(ex.getStatus());
        }
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
}
