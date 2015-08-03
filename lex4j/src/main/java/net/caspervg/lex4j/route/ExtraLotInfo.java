package net.caspervg.lex4j.route;

public class ExtraLotInfo {

    private boolean withUser;
    private boolean withDependencies;
    private boolean withComments;
    private boolean withVotes;

    public ExtraLotInfo(boolean withUser, boolean withDependencies, boolean withComments, boolean withVotes) {
        this.withUser = withUser;
        this.withDependencies = withDependencies;
        this.withComments = withComments;
        this.withVotes = withVotes;
    }

    public boolean withUser() {
        return withUser;
    }

    public boolean withDependencies() {
        return withDependencies;
    }

    public boolean withComments() {
        return withComments;
    }

    public boolean withVotes() {
        return withVotes;
    }

    /**
     * Retrieve all extra information. User, dependencies, comments and votes.
     */
    public static class AllExtraInfo extends ExtraLotInfo {

        public AllExtraInfo() {
            super(true, true, true, true);
        }

    }

    /**
     * Retrieve no extra information.
     */
    public static class NoExtraInfo extends ExtraLotInfo {

        public NoExtraInfo() {
            super(false, false, false, false);
        }

    }

    /**
     * Retrieve only publicly available extra information. Dependencies, comments and votes.
     * This is the default used by LEX4J.
     */
    public static class PublicExtraInfo extends ExtraLotInfo {

        public PublicExtraInfo() {
            super(false, true, true, true);
        }

    }
}
