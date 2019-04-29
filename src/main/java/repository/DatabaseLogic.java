package repository;

import entity.Ware;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseLogic {

    private enum STATUS {
        Absent,
        Available,
        Expected
    }

    private DatabaseLogic() {
    }

    private static int getMaxId(DatabaseService databaseService, String tableName) throws SQLException {
        try (Statement statement = databaseService.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT + MAX(ID) AS id FROM " + tableName + " ;")) {
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    public static List<Ware> getAllWare(DatabaseService databaseService) throws SQLException {
        List<Ware> wares = new ArrayList<>();
        try (PreparedStatement preparedStatement = databaseService.getConnection()
                .prepareStatement(Query.SELECT_ALL_WARE_SHOP1)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    wares.add(createWareEntity(resultSet));
                }
            }
        }
        return wares;
    }

    public static int insertRows(DatabaseService databaseService, List<Ware> wares, String tableName) throws SQLException {
        int count = 0;
        String query = null;
        if (tableName.equals("shop1")) {
            query = Query.INSERT_ROWS_TABLE1;
        } else if (tableName.equals("shop2")) {
            query = Query.INSERT_ROWS_TABLE2;
        }
        int maxId = getMaxId(databaseService, tableName);
        try (PreparedStatement preparedStatement = databaseService.getConnection()
                .prepareStatement(query)) {
            int i = 0;
            int maxBathchSize = 1000;
            for (Ware ware : wares) {
                ++maxId;
                preparedStatement.setLong(1, maxId);
                preparedStatement.setString(2, ware.getCategory());
                preparedStatement.setString(3, ware.getTitle());
                preparedStatement.setInt(4, ware.getPrice());
                preparedStatement.setString(5, ware.getStatus());
                preparedStatement.addBatch();
                i++;
                if (i % maxBathchSize == 0 || i == wares.size()) {
                    int[] updateCounts = preparedStatement.executeBatch();
                    for (int updateCount : updateCounts) {
                        if (updateCount != Statement.SUCCESS_NO_INFO && updateCount != Statement.EXECUTE_FAILED) {
                            count += updateCount;
                        }
                    }
                }
            }
        }
        return count;
    }

    public static int updateAbsent(DatabaseService databaseService, String param, String tableName) throws SQLException {
        int count;
        String query = null;
        if (tableName.equals("shop1")) {
            query = Query.UPDATE_IS_ABSENT_TABLE1;
        } else if (tableName.equals("shop2")) {
            query = Query.UPDATE_IS_ABSENT_TABLE2;
        }
        try (PreparedStatement preparedStatement = databaseService.getConnection()
                .prepareStatement(query)) {
            preparedStatement.setString(1, String.valueOf(STATUS.Absent));
            preparedStatement.setString(2, param);
            count = preparedStatement.executeUpdate();
        }
        System.out.println("update absent " + count);
        return count;
    }

    public static int updateExpected(DatabaseService databaseService, String tableName) throws SQLException {
        int count;
        String query = null;
        if (tableName.equals("shop1")) {
            query = Query.UPDATE_IS_EXPECTED_TABLE1;
        } else if (tableName.equals("shop2")) {
            query = Query.UPDATE_IS_EXPECTED_TABLE2;
        }
        try (PreparedStatement preparedStatement = databaseService.getConnection()
                .prepareStatement(query)) {
            preparedStatement.setString(1, String.valueOf(STATUS.Expected));
            preparedStatement.setString(2, String.valueOf(STATUS.Absent));
            count = preparedStatement.executeUpdate();
            System.out.println("update expected " + count);
        }
        return count;
    }

    public static int updatePrice(DatabaseService databaseService, Double priceUpdateCoefficient, String tableName) throws SQLException {
        int count;
        String query = null;
        if (tableName.equals("shop1")) {
            query = Query.UPDATE_PRICE_TABLE1;
        } else if (tableName.equals("shop2")) {
            query = Query.UPDATE_PRICE_TABLE2;
        }
        try (PreparedStatement preparedStatement = databaseService.getConnection()
                .prepareStatement(query)) {
            preparedStatement.setString(2, String.valueOf(STATUS.Available));
            preparedStatement.setString(1, String.valueOf(priceUpdateCoefficient));
            count = preparedStatement.executeUpdate();
            System.out.println("update price " + count);
        }
        return count;
    }

    private static Ware createWareEntity(ResultSet resultSet) throws SQLException {
        return new Ware(resultSet.getLong("ID"), resultSet.getString("CATEGORY"),
                resultSet.getString("TITLE"), resultSet.getInt("PRICE"),
                resultSet.getString("STATUS"));
    }
}