package service;

public class ShopFactory implements IShopFactory {
    @Override
    public IShop createShop(String shop) {
        if (shop.equals("Product")) {
            return ProductShop.getInstance();
        } else if (shop.equals("Sport")) {
            return SportShop.getInstance();
        } else {
            System.out.println("No fabric for " + shop + " available.");
            throw new RuntimeException();
        }
    }
}
