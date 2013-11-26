package net.caspervg.lex4j.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 13:40
 */
public class DownloadListItem {

    private DownloadListItemRecord record;
    private DownloadListItemLot lot;

    public DownloadListItemRecord getRecord() {
        return record;
    }

    public DownloadListItemLot getLot() {
        return lot;
    }

    public class DownloadListItemRecord {
        private int id;

        public int getId() {
            return id;
        }
    }

    public class DownloadListItemLot {
        private int id;
        private String name;
        @SerializedName("update_date")
        private Date updated;
        private String version;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Date getUpdated() {
            return updated;
        }

        public String getVersion() {
            return version;
        }
    }
}
