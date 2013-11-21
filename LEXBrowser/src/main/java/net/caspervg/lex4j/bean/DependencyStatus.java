package net.caspervg.lex4j.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 16:05
 */
public class DependencyStatus {
    private boolean ok;
    private boolean deleted;
    private boolean superceded;
    @SerializedName("superceded_by")
    private int supercededBy;
    private boolean locked;

    public boolean isOk() {
        return ok;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isSuperceded() {
        return superceded;
    }

    public int getSupercededBy() {
        return supercededBy;
    }

    public boolean isLocked() {
        return locked;
    }
}
