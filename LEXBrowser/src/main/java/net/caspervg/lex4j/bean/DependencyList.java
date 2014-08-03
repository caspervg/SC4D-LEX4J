package net.caspervg.lex4j.bean;

import java.util.List;

/**
 * This class provides a list of dependencies for a certain file
 */
public class DependencyList {

    private String status;
    private int count;
    private List<Dependency> list;

    /**
     * Returns <code>"ok"</code> if the dependency status is available
     *
     * @return <code>"ok"</code> if the dependency status is available;
     *         <code>"not-available"</code> otherwise (e.g. if the file does not support the dependency tracker)
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the number of dependencies in this list
     *
     * @return the number of dependencies in this list
     */
    public int getCount() {
        return count;
    }

    /**
     * Returns the dependencies
     *
     * @return the dependencies
     */
    public List<Dependency> getList() {
        return list;
    }
}
