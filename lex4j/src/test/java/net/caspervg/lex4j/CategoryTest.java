package net.caspervg.lex4j;

import net.caspervg.lex4j.bean.BroadCategory;
import net.caspervg.lex4j.bean.Category;
import net.caspervg.lex4j.bean.CategoryOverview;
import net.caspervg.lex4j.bean.TypeCategory;
import net.caspervg.lex4j.route.CategoryRoute;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CategoryTest {

    @Test
    public void testAllCategories() {
        CategoryRoute route = new CategoryRoute();
        CategoryOverview view = route.getCategories();

        Assert.assertNotNull(view.getBroadCategories());
        Assert.assertNotNull(view.getLEXCategories());
        Assert.assertNotNull(view.getLEXTypes());
        Assert.assertNotNull(view.getLotAuthors());
        Assert.assertNotNull(view.getLotGroups());

        Assert.assertEquals(1, view.getBroadCategories().get(0).getId());
        Assert.assertEquals(66, view.getLEXCategories().get(0).getId());
        Assert.assertEquals(5, view.getLEXTypes().get(0).getId());
        Assert.assertEquals(1, view.getLotAuthors().get(0).getId());
        Assert.assertEquals(4, view.getLotGroups().get(0).getId());
    }

    @Test
    public void testBroadCategories() {
        CategoryRoute route = new CategoryRoute();
        List<BroadCategory> list = route.getBroadCategories();

        Assert.assertNotNull(list);

        BroadCategory first = list.get(0);

        Assert.assertEquals(1, first.getId());
        Assert.assertEquals("Agriculture", first.getName());
        Assert.assertEquals("250_MX_Agric.gif", first.getImage());
    }

    @Test
         public void testLEXCategories() {
        CategoryRoute route = new CategoryRoute();
        List<Category> list = route.getLEXCategories();

        Assert.assertNotNull(list);

        Category first = list.get(0);

        Assert.assertEquals(66, first.getId());
        Assert.assertEquals("00 Locked", first.getName());
    }

    @Test
    public void testLEXTypes() {
        CategoryRoute route = new CategoryRoute();
        List<TypeCategory> list = route.getLEXTypes();

        Assert.assertNotNull(list);

        TypeCategory first = list.get(0);

        Assert.assertEquals(5, first.getId());
        Assert.assertEquals("BTE", first.getName());
        Assert.assertEquals("Use for lots that depend or contribute to BSC Tracking Enabled Rewards.", first.getDescription());
    }

    @Test
    public void testLotGroups() {
        CategoryRoute route = new CategoryRoute();
        List<Category> list = route.getLotGroups();

        Assert.assertNotNull(list);

        Category first = list.get(0);

        Assert.assertEquals(4, first.getId());
        Assert.assertEquals("BSC - VIP girafe flora", first.getName());
    }

    @Test
    public void testAuthors() {
        CategoryRoute route = new CategoryRoute();
        List<Category> list = route.getLotAuthors();

        Assert.assertNotNull(list);

        Category first = list.get(0);

        Assert.assertEquals(1, first.getId());
        Assert.assertEquals("ADMIN", first.getName());
    }
}
