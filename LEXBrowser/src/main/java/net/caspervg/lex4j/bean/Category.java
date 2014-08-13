package net.caspervg.lex4j.bean;

/**
 * This class provides a default category
 */
public class Category {

    protected int id;
    protected String name;

    /**
     * Returns the ID of this category
     *
     * @return the ID of this category
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of this category
     *
     * @return the name of this category
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the String representation of this category
     *
     * @return the String representation of this category
     */
    @Override
    public String toString() {
        return this.name + " (" + this.id + ")";
    }
}
