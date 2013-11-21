package net.caspervg.lex4j.bean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 16:01
 */
public class DependencyList {
    private String status;
    private int count;
    private List<DependencyItem> list;

    public String getStatus() {
        return status;
    }

    public int getCount() {
        return count;
    }

    public List<DependencyItem> getList() {
        return list;
    }
}
