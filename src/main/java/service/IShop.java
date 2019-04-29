package service;

import entity.Ware;
import repository.DatabaseService;

import java.sql.SQLException;
import java.util.List;

public interface IShop {

    Integer addWare(DatabaseService databaseService, String tableName, String category, String title, Integer price, String status) throws SQLException;

    Integer addWares(DatabaseService databaseService, String tableName, List<Ware> waresList) throws SQLException;

    Integer setStatusAbsent(DatabaseService databaseService, String category) throws SQLException;

    Integer setStatusExpected(DatabaseService databaseService) throws SQLException;

    Integer changePrice(DatabaseService databaseService, Double priceUpdateCoefficient) throws SQLException;
}
