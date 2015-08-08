package net.caspervg.lex4j.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class provides a list of dependencies for a certain file
 */
public class DependencyList {

    protected String status;
    protected int count;
    protected List<Dependency> list;

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
     * @deprecated use {@link #asList(boolean)} instead. Will be removed in v5.0
     * @return the dependencies
     */
    public List<Dependency> getList() {
        return asList(false);
    }

    /**
     * Returns the dependencies as a list, together with the child dependencies
     *
     * @return the dependencies
     */
    public List<Dependency> asList() {
        return asList(true);
    }

    /**
     * Returns the dependencies as a list
     *
     * @param withChildren also include child dependencies in the result
     * @return list of the dependencies
     */
    public List<Dependency> asList(boolean withChildren) {
        if (withChildren) {
            return new ArrayList<>(asSet());
        } else {
            if (list != null) {
                return new ArrayList<>(list);
            } else {
                return new ArrayList<>();
            }
        }
    }

    /**
     * Returns the dependencies as a set, together with the child dependencies
     *
     * @return the dependencies and all child dependencies
     */
    public Set<Dependency> asSet() {
        return asSet(true);
    }

    /**
     * Returns the dependencies as a set
     *
     * @param withChildren also include child dependencies in the result
     * @return set of the dependencies
     */
    public Set<Dependency> asSet(boolean withChildren) {
        if (withChildren) {
            Set<Dependency> dependencySet = new HashSet<>();
            List<Dependency> dependencyList = asList(false);

            dependencySet.addAll(dependencyList);
            for (Dependency dependency : dependencyList) {
                if (dependency.isInternal()) {
                    dependencySet.addAll(dependency.getDependencies().asSet());
                }
            }

            return dependencySet;
        } else {
            return new HashSet<>(list);
        }
    }

    /**
     * Returns the String representation of this dependency list
     *
     * @return the String representation of this dependency list
     */
    @Override
    public String toString() {
        return this.asList().toString();
    }
}
