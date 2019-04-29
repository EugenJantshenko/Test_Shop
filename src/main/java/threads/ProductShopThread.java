package threads;

import entity.Ware;
import repository.DatabaseService;
import repository.Query;
import service.IShopFactory;
import service.ProductShop;
import service.ShopFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductShopThread extends Thread {
    public void run() {

        IShopFactory ShopFactory = new ShopFactory();
        ProductShop productShop = (ProductShop) ShopFactory.createShop("Product");
        List<Ware> wares = createProductWare();
        try {
            if (!wares.isEmpty()) {
                try (DatabaseService databaseService = DatabaseService.getInstance()) {
                    databaseService.dropTable(Query.DROP_SHOP1);
                    databaseService.createTable(Query.CREATE_SHOP1);
                    productShop.addWares(databaseService, "shop1", wares);
                    productShop.setStatusAbsent(databaseService, "fruits");
                    productShop.setStatusExpected(databaseService);
                    productShop.changePrice(databaseService, 1.2d);
                    databaseService.showTable("shop1");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static List<Ware> createProductWare() {
        List<Ware> wares = new ArrayList<>();
        wares.add(new Ware("fruits", "apple", 25, "Available"));
        wares.add(new Ware("fruits", "pear", 18, "Absent"));
        wares.add(new Ware("fruits", "apricot", 26, "Expected"));
        wares.add(new Ware("vegetables", "potato", 35, "Available"));
        wares.add(new Ware("vegetables", "beet", 33, "Absent"));
        wares.add(new Ware("vegetables", "carrot", 28, "Expected"));
        wares.add(new Ware("vegetables", "tomato", 24, "Available"));
        wares.add(new Ware("berries", "Raspberries", 45, "Available"));
        wares.add(new Ware("berries", "Strawberry", 32, "Absent"));
        wares.add(new Ware("berries", "currant", 62, "Expected"));
        wares.add(new Ware("fruits", "apple", 44, "Available"));
        wares.add(new Ware("fruits", "pear", 88, "Absent"));
        wares.add(new Ware("fruits", "apricot", 36, "Expected"));
        wares.add(new Ware("vegetables", "potato", 69, "Available"));
        wares.add(new Ware("vegetables", "beet", 125, "Absent"));
        wares.add(new Ware("vegetables", "carrot", 228, "Expected"));
        wares.add(new Ware("vegetables", "tomato", 224, "Available"));
        wares.add(new Ware("berries", "Raspberries", 425, "Available"));
        wares.add(new Ware("berries", "Strawberry", 322, "Absent"));
        wares.add(new Ware("berries", "currant", 162, "Expected"));
        return wares;
    }
}
