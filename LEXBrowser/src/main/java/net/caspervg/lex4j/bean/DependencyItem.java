package net.caspervg.lex4j.bean;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 16:02
 */
public class DependencyItem {
    private boolean internal;
    private String link;
    private String name;
    private int id;
    private DependencyStatus status;

    public boolean isInternal() {
        return internal;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public DependencyStatus getStatus() {
        return status;
    }
}
