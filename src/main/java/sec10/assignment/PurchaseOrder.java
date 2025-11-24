package sec10.assignment;

import com.github.javafaker.Commerce;
import common.Util;

public record PurchaseOrder(String item, String category, Integer price) {
    public static PurchaseOrder createPurchaseOrder() {
        Commerce commerce = Util.faker().commerce();
        return createOrder(
                commerce.productName(),
                commerce.department(),
                Util.faker().random().nextInt(10, 100)
        );
    }

    public static PurchaseOrder createFreeOrder(String category) {
        Commerce commerce = Util.faker().commerce();
        return createOrder(
                commerce.productName(),
                category,
                0
        );
    }

    private static PurchaseOrder createOrder(String itemName, String category, int price) {
        return new PurchaseOrder(itemName, category, price);
    }


}
