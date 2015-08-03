package net.caspervg.lex4j.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class provides the status of a certain dependency
 */
public class DependencyStatus {

    protected boolean ok;
    protected boolean deleted;
    @JsonProperty("superseded")
    protected boolean superseded;
    @JsonProperty("superseded_by")
    protected int supersededBy;
    protected boolean locked;

    /**
     * Returns <code>true</code> if this dependency is not deleted, not superseded and not locked
     *
     * @return <code>true</code> if this dependency is not deleted, not superseded and not locked;
     *         <code>false</code> otherwise
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * Returns <code>true</code> if this dependency is deleted
     *
     * @return <code>true</code> if this dependency is deleted;
     *         <code>false</code> otherwise
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Returns <code>true</code> if this dependency is superseded
     *
     * @return <code>true</code> if this dependency is superseded;
     *         <code>false</code> otherwise
     */
    public boolean isSuperseded() {
        return superseded;
    }

    /**
     * Returns the ID of the file this dependency was superseded by, or <code>-1</code> if it has not been superseded
     *
     * @return ID of the file this dependency was superseded by
     */
    public int getSupersededBy() {
        return supersededBy;
    }

    /**
     * Returns <code>true</code> if this dependency is locked
     *
     * @return <code>true</code> if this dependency is locked;
     *         <code>false</code> otherwise
     */
    public boolean isLocked() {
        return locked;
    }
}
