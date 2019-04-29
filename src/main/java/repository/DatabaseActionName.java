package repository;

public enum DatabaseActionName {
    CREATE("create"),
    DROP("drop");

    private final String action;

    DatabaseActionName(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}