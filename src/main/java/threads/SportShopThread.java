package threads;

import entity.Ware;
import repository.DatabaseService;
import repository.Query;
import service.IShopFactory;
import service.ShopFactory;
import service.SportShop;

import java.util.ArrayList;
import java.util.List;

public class SportShopThread extends Thread {
    public void run() {
        IShopFactory ShopFactory = new ShopFactory();
        SportShop sportShop = (SportShop) ShopFactory.createShop("Sport");
        List<Ware> wares = createSportWare();
        try {
            if (!wares.isEmpty()) {
                try (DatabaseService databaseService = DatabaseService.getInstance()) {
                    databaseService.dropTable(Query.DROP_SHOP2);
                    databaseService.createTable(Query.CREATE_SHOP2);
                    sportShop.addWares(databaseService, "shop2", wares);
                    sportShop.setStatusAbsent(databaseService, "balls");
                    sportShop.setStatusExpected(databaseService);
                    sportShop.changePrice(databaseService, 1.2d);
                    databaseService.showTable("shop2");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Ware> createSportWare() {
        List<Ware> wares = new ArrayList<>();
        wares.add(new Ware("shoes", "sneakers", 25, "Available"));
        wares.add(new Ware("shoes", "boots", 18, "Absent"));
        wares.add(new Ware("shoes", "sandals", 26, "Expected"));
        wares.add(new Ware("tshirt", "shirt", 35, "Available"));
        wares.add(new Ware("tshirt", "T-shirt", 33, "Absent"));
        wares.add(new Ware("tshirt", "raglan", 28, "Expected"));
        wares.add(new Ware("balls", "soccer ball", 24, "Available"));
        wares.add(new Ware("balls", "volleyball", 45, "Available"));
        wares.add(new Ware("balls", "basketball", 32, "Absent"));
        wares.add(new Ware("balls", "tennis ball", 62, "Expected"));
        wares.add(new Ware("shoes", "sneakers", 235, "Available"));
        wares.add(new Ware("shoes", "boots", 138, "Absent"));
        wares.add(new Ware("shoes", "sandals", 236, "Expected"));
        wares.add(new Ware("tshirt", "shirt", 335, "Available"));
        wares.add(new Ware("tshirt", "T-shirt", 333, "Absent"));
        wares.add(new Ware("tshirt", "raglan", 238, "Expected"));
        wares.add(new Ware("balls", "soccer ball", 234, "Available"));
        wares.add(new Ware("balls", "volleyball", 435, "Available"));
        wares.add(new Ware("balls", "basketball", 332, "Absent"));
        wares.add(new Ware("balls", "tennis ball", 362, "Expected"));
        return wares;
    }
}
