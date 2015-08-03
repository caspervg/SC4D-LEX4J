package net.caspervg.lex4j.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * This class provides a download history item
 */
public class DownloadHistoryItem {

    protected DownloadHistoryItemRecord record;
    protected DownloadHistoryItemLot lot;

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
     * This class provides the Record associated with a {@link DownloadHistoryItem}.
     * It contains data about the download list item itself, but not about the file
     * it refers to.
     */
    public class DownloadHistoryItemRecord {

        protected int id;
        @JsonProperty("last_downloaded")
        protected Date lastDownloaded;
        @JsonProperty("last_version")
        protected String lastVersion;
        @JsonProperty("download_count")
        protected int timesDownloaded;

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

        /**
         * Returns the String representation of this record
         *
         * @return the String representation of this record
         */
        @Override
        public String toString() {
            return String.valueOf(this.getId());
        }
    }

    /**
     * This class provides the File associated with a {@link DownloadHistoryItem}.
     * It contains some data about the file/lot itself, but if you want more data, you will have to
     * execute a separate {@link net.caspervg.lex4j.route.LotRoute#getLot} call.
     */
    public class DownloadHistoryItemLot {

        protected int id;
        protected String name;
        @JsonProperty("update_date")
        protected Date updated;
        protected String version;
        protected String author;

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

        /**
         * Returns the String representation of this file
         *
         * @return the String representation of this file
         */
        @Override
        public String toString() {
            return this.getName() + " (" + this.getId() + ")";
        }
    }

    /**
    * Returns the String representation of this download history item
    *
    * @return the String representation of this download history item
    */
    @Override
    public String toString() {
        return this.getRecord() + " -> " + this.getLot();
    }
}
