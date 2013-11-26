package net.caspervg.lex4j.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 13:40
 */
public class DownloadHistoryItem {

    private DownloadHistoryItemRecord record;
    private DownloadHistoryItemLot lot;

    public DownloadHistoryItemRecord getRecord() {
        return record;
    }

    public DownloadHistoryItemLot getLot() {
        return lot;
    }

    public class DownloadHistoryItemRecord {
        private int id;
        @SerializedName("last_downloaded")
        private Date lastDownloaded;
        @SerializedName("last_version")
        private String lastVersion;
        @SerializedName("download_count")
        private int timesDownloaded;

        public int getId() {
            return id;
        }

        public Date getLastDownloaded() {
            return lastDownloaded;
        }

        public String getLastVersion() {
            return lastVersion;
        }

        public int getTimesDownloaded() {
            return timesDownloaded;
        }
    }

    public class DownloadHistoryItemLot {
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
