package net.caspervg.lex4j;

import junit.framework.Assert;
import net.caspervg.lex4j.bean.Lot;
import net.caspervg.lex4j.route.Filter;
import net.caspervg.lex4j.route.SearchRoute;
import org.junit.Test;
import org.restlet.resource.ResourceException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 12:33
 */
public class SearchTest {

    @Test
    public void searchTest() {

        SearchRoute route = new SearchRoute();
        try {
            route.addFilter(Filter.AMOUNT, 100);
            route.addFilter(Filter.CONCISE, true);
            route.addFilter(Filter.START, 5);
            route.addFilter(Filter.CREATOR, 1);
            List<Lot> lots = route.doSearch();
            Assert.assertEquals(1007, lots.get(0).getId());
        } catch (ResourceException ex) {
            System.out.println(ex.getStatus());
        }
    }

}
