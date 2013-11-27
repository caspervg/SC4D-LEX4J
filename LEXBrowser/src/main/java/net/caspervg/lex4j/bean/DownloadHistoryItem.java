package net.caspervg.lex4j.bean;

import com.google.gson.annotations.SerializedName;

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
        private String lastDownloaded;
        @SerializedName("last_version")
        private String lastVersion;
        @SerializedName("download_count")
        private int timesDownloaded;

        public int getId() {
            return id;
        }

        public String getLastDownloaded() {
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
        private String updated;
        private String version;
        private String author;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getUpdated() {
            return updated;
        }

        public String getVersion() {
            return version;
        }

        public String getAuthor() {
            return author;
        }
    }
}
