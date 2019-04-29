import threads.ProductShopThread;
import threads.SportShopThread;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ProductShopThread productShopThread = new ProductShopThread();
        SportShopThread sportShopThread = new SportShopThread();
        productShopThread.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sportShopThread.start();
        sportShopThread.join();
        System.out.println("All threads is over");
    }
}

