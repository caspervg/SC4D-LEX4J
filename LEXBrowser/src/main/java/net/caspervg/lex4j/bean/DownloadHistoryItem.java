package net.caspervg.lex4j.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * This class provides a download history item
 */
public class DownloadHistoryItem {

    private DownloadHistoryItemRecord record;
    private DownloadHistoryItemLot lot;

    /**
     * Returns the record of this download history item
     *
     * @return the record of this download history item
     */
    public DownloadHistoryItemRecord getRecord() {
        return record;
    }

    /**
     * Returns the lot of this download history item
     *
     * @return the lot of this download history item
     */
    public DownloadHistoryItemLot getLot() {
        return lot;
    }

    /**
     * Record associated with a {@link DownloadHistoryItem}
     */
    public class DownloadHistoryItemRecord {

        private int id;
        @SerializedName("last_downloaded")
        private Date lastDownloaded;
        @SerializedName("last_version")
        private String lastVersion;
        @SerializedName("download_count")
        private int timesDownloaded;

        /**
         * Returns the ID of this record
         *
         * @return the ID of this record
         */
        public int getId() {
            return id;
        }

        /**
         * Returns the date when this record was last downloaded by the user
         *
         * @return the date of this record
         */
        public Date getLastDownloaded() {
            return lastDownloaded;
        }

        /**
         * Returns the version when this record was last downloaded by the user
         *
         * @return the version of this record
         */
        public String getLastVersion() {
            return lastVersion;
        }

        /**
         * Returns the amount of times this file was downloaded by the user
         *
         * @return the amount of times of this record
         */
        public int getTimesDownloaded() {
            return timesDownloaded;
        }
    }

    /**
     * File associated with a {@link DownloadHistoryItem}
     */
    public class DownloadHistoryItemLot {

        private int id;
        private String name;
        @SerializedName("update_date")
        private Date updated;
        private String version;
        private String author;

        /**
         * Returns the ID of this file
         *
         * @return the ID of this file
         */
        public int getId() {
            return id;
        }

        /**
         * Returns the name of this file
         *
         * @return the name of this file
         */
        public String getName() {
            return name;
        }

        /**
         * Returns the date when this file was last updated
         *
         * @return the date when this file was last updated
         */
        public Date getUpdated() {
            return updated;
        }

        /**
         * Returns the current version of this file
         *
         * @return the current version of this file
         */
        public String getVersion() {
            return version;
        }

        /**
         * Returns the username of the author of this file
         *
         * @return the username of the author of this file
         */
        public String getAuthor() {
            return author;
        }
    }
}
