package net.caspervg.lex4j.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * This class provides a download list item
 */
public class DownloadListItem {

    protected DownloadListItemRecord record;
    protected DownloadListItemLot lot;

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
     * This class provides the Record associated with a {@link DownloadListItem}.
     * It contains data about the download list item itself, but not about the file
     * it refers to.
     */
    public class DownloadListItemRecord {

        protected int id;

        /**
         * Returns the ID of this record
         *
         * @return the ID of this record
         */
        public int getId() {
            return id;
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
     * This class provides the File associated with a {@link DownloadListItem}.
     * It contains some data about the file/lot itself, but if you want more data, you will have to
     * execute a separate {@link net.caspervg.lex4j.route.LotRoute#getLot} call.
     */
    public class DownloadListItemLot {

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
     * Returns the String representation of this download list item
     *
     * @return the String representation of this download list item
     */
    @Override
    public String toString() {
        return this.getRecord() + " -> " + this.getLot();
    }
}
