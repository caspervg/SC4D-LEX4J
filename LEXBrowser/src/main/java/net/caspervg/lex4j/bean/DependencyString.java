package net.caspervg.lex4j.bean;

/**
 * Convenience class that provides a String representation of the dependencies for a certain file.
 * This should only be used internally.
 */
public class DependencyString {

    private String dependency;

    /**
     * Returns the dependency string
     *
     * @return the dependency string
     */
    public String getDependency() {
        return dependency;
    }

    /**
     * Returns the String representation of this dependency string
     *
     * @return the String representation of this dependency string
     */
    @Override
    public String toString() {
        return dependency;
    }
}
