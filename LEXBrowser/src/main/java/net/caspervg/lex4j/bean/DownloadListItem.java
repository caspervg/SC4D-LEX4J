package net.caspervg.lex4j.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * This class provides a download list item
 */
public class DownloadListItem {

    private DownloadListItemRecord record;
    private DownloadListItemLot lot;

    /**
     * Returns the record of this download list item
     *
     * @return the record of this download list item
     */
    public DownloadListItemRecord getRecord() {
        return record;
    }

    /**
     * Returns the file of this download list item
     *
     * @return the file of this download list item
     */
    public DownloadListItemLot getLot() {
        return lot;
    }

    /**
     * Record associated with a {@link DownloadListItem}
     */
    public class DownloadListItemRecord {

        private int id;

        /**
         * Returns the ID of this record
         *
         * @return the ID of this record
         */
        public int getId() {
            return id;
        }
    }

    /**
     * File associated with a {@link DownloadListItem}
     */
    public class DownloadListItemLot {

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
         * Returns the version of this file
         *
         * @return the version of this file
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
