package service;

import entity.Ware;
import repository.DatabaseLogic;
import repository.DatabaseService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SportShop implements IShop {

    private static SportShop instance;

    private SportShop() {
    }

    public static SportShop getInstance() {
        if (instance == null) {
            instance = new SportShop();
        }
        return instance;
    }

    @Override
    public boolean getAllWare(DatabaseService databaseService) throws SQLException {
        List<Ware> wareList = DatabaseLogic.getAllWare(databaseService);
        if (!wareList.isEmpty()) {
            for (Ware ware : wareList) {
                System.out.println(ware.toString());
            }
            return true;
        }
        return false;
    }

    @Override
    public Integer addWare(DatabaseService databaseService, String tableName, String category, String title, Integer price, String status) throws SQLException {
        List<Ware> wareList = new ArrayList<>();
        wareList.add(new Ware(category, title, price, status));
        return DatabaseLogic.insertRows(databaseService, wareList, tableName);
    }

    @Override
    public Integer addWares(DatabaseService databaseService, String tableName, List<Ware> waresList) throws SQLException {
        return DatabaseLogic.insertRows(databaseService, waresList, tableName);
    }


    @Override
    public Integer setStatusAbsent(DatabaseService databaseService, String category) throws SQLException {
        return DatabaseLogic.updateAbsent(databaseService, category, "shop2");
    }

    @Override
    public Integer setStatusExpected(DatabaseService databaseService) throws SQLException {
        return DatabaseLogic.updateExpected(databaseService, "shop2");
    }

    @Override
    public Integer changePrice(DatabaseService databaseService, Double priceUpdateCoefficient) throws SQLException {
        return DatabaseLogic.updatePrice(databaseService, priceUpdateCoefficient, "shop2");
    }
}
