package net.caspervg.lex4j;

import junit.framework.Assert;
import net.caspervg.lex4j.bean.Lot;
import net.caspervg.lex4j.route.Filter;
import net.caspervg.lex4j.route.FilterParameterException;
import net.caspervg.lex4j.route.SearchRoute;
import org.junit.Test;

import java.util.List;

public class SearchTest {

    // Valid search test
    @Test
    public void searchTest() {

        SearchRoute route = new SearchRoute();
        route.addFilter(Filter.AMOUNT, 100);
        route.addFilter(Filter.CONCISE, false);
        route.addFilter(Filter.START, 0);
        route.addFilter(Filter.CREATOR, 1);
        route.addFilter(Filter.TITLE, "concorde");
        List<Lot> lots = route.doSearch();
        Assert.assertEquals(1007, lots.get(0).getId());
    }

    // Invalid search test
    @Test(expected = FilterParameterException.class)
    public void searchInvalidTest() {
        SearchRoute route = new SearchRoute();
        route.addFilter(Filter.AMOUNT, "100");
    }

}
