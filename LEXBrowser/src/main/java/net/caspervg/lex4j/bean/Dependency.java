package net.caspervg.lex4j.bean;

public class Dependency {

    private boolean internal;
    private String link;
    private String name;
    private int id;
    private DependencyStatus status;

    /**
     * Returns <code>true</code> if the dependency is hosted on the LEX
     *
     * @return <code>true</code> if the dependency is hosted on the LEX;
     *         <code>false</code> otherwise
     */
    public boolean isInternal() {
        return internal;
    }

    /**
     * Returns the link of this dependency
     *
     * @return the link to this dependency
     */
    public String getLink() {
        return link;
    }

    /**
     * Returns the name of this dependency
     *
     * @return the name of this dependency
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the ID of this dependency
     *
     * @return the ID of this dependency
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the status of this dependency
     *
     * @return the status of this dependency
     */
    public DependencyStatus getStatus() {
        return status;
    }
}
