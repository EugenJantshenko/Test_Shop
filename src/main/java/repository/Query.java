package repository;

public class Query {

    private Query() {
    }

    public static final String TABLE_SHOP1 = "shop1";
    public static final String TABLE_SHOP2 = "shop2";

    public static final String DROP_SHOP1 = "DROP TABLE IF EXISTS " + TABLE_SHOP1;
    public static final String DROP_SHOP2 = "DROP TABLE IF EXISTS " + TABLE_SHOP2;

    public static final String CREATE_SHOP1 =
            "CREATE TABLE IF NOT EXISTS " + TABLE_SHOP1
                    + "("
                    + "	ID BIGINT(11) NOT NULL,"
                    + "	CATEGORY VARCHAR(60),"
                    + "	TITLE VARCHAR(60),"
                    + "	PRICE BIGINT(11),"
                    + "	STATUS VARCHAR(128),"
                    + "PRIMARY KEY (ID)"
                    + ")"
                    + "ENGINE=InnoDB DEFAULT CHARSET=utf8";

    public static final String CREATE_SHOP2 =
            "CREATE TABLE IF NOT EXISTS " + TABLE_SHOP2
                    + "("
                    + "	ID BIGINT(11) NOT NULL,"
                    + "	CATEGORY VARCHAR(60),"
                    + "	TITLE VARCHAR(60),"
                    + "	PRICE BIGINT(11),"
                    + "	STATUS VARCHAR(128),"
                    + "PRIMARY KEY (ID)"
                    + ")"
                    + "ENGINE=InnoDB DEFAULT CHARSET=utf8";


    public static final String SELECT_ALL_WARE_SHOP1 =
            "SELECT *"
                    + "FROM "
                    + TABLE_SHOP1
                    + ";";

    public static final String INSERT_ROWS_TABLE1 =
            "INSERT INTO "
                    + TABLE_SHOP1
                    + " (ID, CATEGORY, TITLE, PRICE, STATUS)"
                    + " VALUES"
                    + " (?, ?, ?, ?, ?);";

    public static final String INSERT_ROWS_TABLE2 =
            "INSERT INTO "
                    + TABLE_SHOP2
                    + " (ID, CATEGORY, TITLE, PRICE, STATUS)"
                    + " VALUES"
                    + " (?, ?, ?, ?, ?);";

    public static final String UPDATE_IS_ABSENT_TABLE1 =
            "UPDATE "
                    + TABLE_SHOP1
                    + " SET STATUS = ?"
                    + " WHERE CATEGORY = ?;";

    public static final String UPDATE_IS_EXPECTED_TABLE1 =
            "UPDATE "
                    + TABLE_SHOP1
                    + " SET STATUS = ?"
                    + " WHERE ID % 2 = 0"
                    + " AND STATUS NOT LIKE ?;";

    public static final String UPDATE_PRICE_TABLE1 =
            "UPDATE "
                    + TABLE_SHOP1
                    + " SET PRICE = (PRICE * ?)"
                    + " WHERE STATUS = ?;";

    public static final String UPDATE_IS_ABSENT_TABLE2 =
            "UPDATE "
                    + TABLE_SHOP2
                    + " SET STATUS = ?"
                    + " WHERE CATEGORY = ?;";

    public static final String UPDATE_IS_EXPECTED_TABLE2 =
            "UPDATE "
                    + TABLE_SHOP2
                    + " SET STATUS = ?"
                    + " WHERE ID % 2 = 0"
                    + " AND STATUS NOT LIKE ?;";

    public static final String UPDATE_PRICE_TABLE2 =
            "UPDATE "
                    + TABLE_SHOP1
                    + " SET PRICE = (PRICE * ?)"
                    + " WHERE STATUS = ?;";
}