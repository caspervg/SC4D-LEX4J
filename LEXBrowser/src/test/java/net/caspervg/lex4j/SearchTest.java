package net.caspervg.lex4j;

import junit.framework.Assert;
import net.caspervg.lex4j.bean.BroadCategory;
import net.caspervg.lex4j.bean.CategoryOverview;
import net.caspervg.lex4j.bean.Lot;
import net.caspervg.lex4j.route.CategoryRoute;
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

    @Test
    public void searchBroadCategory() {
        // Manual filter
        SearchRoute searchRoute = new SearchRoute();
        searchRoute.addFilter(Filter.BROAD_CATEGORY, "250_MX_Civic.gif");
        List<Lot> lots = searchRoute.doSearch();
        Assert.assertNotNull(lots);
        Assert.assertNotSame(0, lots.size());

        // Get filter from categoryRoute.getBroadCategories()
        searchRoute = new SearchRoute();
        CategoryRoute categoryRoute = new CategoryRoute();
        List<BroadCategory> categories = categoryRoute.getBroadCategories();
        searchRoute.addFilter(Filter.BROAD_CATEGORY, categories.get(0).getImage());
        lots = searchRoute.doSearch();
        Assert.assertNotNull(lots);
        Assert.assertNotSame(0, lots.size());

        // Get filter from categoryRoute.getCategories()
        searchRoute = new SearchRoute();
        categoryRoute = new CategoryRoute();
        CategoryOverview overview = categoryRoute.getCategories();
        categories = overview.getBroadCategories();
        searchRoute.addFilter(Filter.BROAD_CATEGORY, categories.get(0).getImage());
        lots = searchRoute.doSearch();
        Assert.assertNotNull(lots);
        Assert.assertNotSame(0, lots.size());
    }

    // Invalid search test
    @Test(expected = FilterParameterException.class)
    public void searchInvalidTest() {
        SearchRoute route = new SearchRoute();
        route.addFilter(Filter.AMOUNT, "100");
    }

    @Test
    public void searchFullDependencyTest() {
        SearchRoute route = new SearchRoute();
        route.addFilter(Filter.CREATOR, 1);
        route.addFilter(Filter.DEPENDENCIES, "full");
        List<Lot> lots = route.doSearch();

        Assert.assertEquals("ok", lots.get(0).getDependencyList().getStatus());
        Assert.assertEquals("not-available", lots.get(lots.size() - 1).getDependencyList().getStatus());

        Lot depLot = null;
        for (Lot l : lots) {
            if (l.getDependencyList().getCount() > 0) {
                depLot = l;
            }
        }

        Assert.assertNotNull(depLot);
        Assert.assertEquals(1, depLot.getDependencyList().getCount());
        Assert.assertEquals(731, depLot.getDependencyList().getList().get(0).getId());
        Assert.assertEquals("SC4Terraformer v11", depLot.getDependencyList().getList().get(0).getName());
        Assert.assertNotNull(depLot.getDependencyList().getList().get(0).getStatus());
    }

    @Test
    public void searchConciseDependencyTest() {
        SearchRoute route = new SearchRoute();
        route.addFilter(Filter.CREATOR, 1);
        route.addFilter(Filter.DEPENDENCIES, "concise");
        List<Lot> lots = route.doSearch();

        Assert.assertEquals("ok", lots.get(0).getDependencyList().getStatus());
        Assert.assertEquals("not-available", lots.get(lots.size() - 1).getDependencyList().getStatus());

        Lot depLot = null;
        for (Lot l : lots) {
            if (l.getDependencyList().getCount() > 0) {
                depLot = l;
            }
        }

        Assert.assertNotNull(depLot);
        Assert.assertEquals(1, depLot.getDependencyList().getCount());
        Assert.assertEquals(731, depLot.getDependencyList().getList().get(0).getId());
        Assert.assertEquals("N/A", depLot.getDependencyList().getList().get(0).getName());
        Assert.assertNull(depLot.getDependencyList().getList().get(0).getStatus());
    }

}
