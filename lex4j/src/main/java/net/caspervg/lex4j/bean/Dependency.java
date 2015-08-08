package net.caspervg.lex4j.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * This class provides a dependency
 */
public class Dependency {

    protected boolean internal;
    protected String link;
    protected String name;
    protected int id;
    protected DependencyStatus status;
    protected DependencyList dependencies;
    @JsonProperty("last_downloaded")
    protected Date lastDownloaded;

    /**
     * Default constructor. Initializes no fields.
     */
    public Dependency() {
        // Default constructor for Jackson (de)serialization
    }

    /**
     * Creates a new Dependency
     *
     * @param internal Internal dependency or not
     * @param id Identifier of the dependency (if internal)
     * @param link Link to the dependency (if external)
     * @param name Name of the dependency
     * @param status Status of the dependency
     * @param list List of child dependencies
     * @param lastDownloaded Last download date for the authenticated user
     */
    public Dependency(boolean internal,
                      int id, String link,
                      String name,
                      DependencyStatus status,
                      DependencyList list,
                      Date lastDownloaded) {
        this.internal = internal;
        this.link = link;
        this.name = name;
        this.id = id;
        this.status = status;
        this.dependencies = list;
        this.lastDownloaded = lastDownloaded;
    }
    /**
     * Copy constructor that copies the child dependencies as well
     *
     * @param dependency Dependency to copy
     */
    Dependency(Dependency dependency) {
        this(dependency, true);
    }

    /**
     * Copy constructor that can copy the child dependencies if required
     *
     * @param dependency Dependency to copy
     * @param withChildren Keep children or not
     */
    Dependency(Dependency dependency, boolean withChildren) {
        this.internal = dependency.isInternal();
        this.link = dependency.getLink();
        this.name = dependency.getName();
        this.id = dependency.getId();
        this.status = dependency.getStatus();

        if (withChildren) {
            this.dependencies = dependency.getDependencies();
        }
    }

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

    /**
     * Returns the dependencies of this dependency
     *
     * @return the dependencies of this dependency
     */
    public DependencyList getDependencies() {
        return dependencies;
    }

    /**
     * Returns the date on which the user (if authenticated) last downloaded this file
     *
     * @return last download date for the authenticated user
     */
    public Date getLastDownloaded() {
        return lastDownloaded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dependency that = (Dependency) o;

        if (isInternal() != that.isInternal()) return false;

        if (isInternal()) {
            return getId() == that.getId();
        } else {
            return !(getLink() != null ? !getLink().equals(that.getLink()) : that.getLink() != null);
        }
    }

    @Override
    public int hashCode() {
        int result = (isInternal() ? 1 : 0);
        if (isInternal()) {
            result = 31 * result + getId();
        } else {
            result = 31 * result + (getLink() != null ? getLink().hashCode() : 0);
        }
        return result;
    }

    /**
     * Returns the String representation of this dependency
     *
     * @return the String representation of this dependency
     */
    @Override
    public String toString() {
        return this.getName() + " (" + this.getId() + ")";
    }
}
