package sec06;

import common.Util;
import reactor.core.publisher.Flux;
import sec06.assignment.InventoryService;
import sec06.assignment.Order;
import sec06.assignment.OrderService;
import sec06.assignment.RevenueService;

public class Lec06Assignment {
    public static void main(String[] args) {
        var orderService = new OrderService();

        Flux<Order> orderStream = orderService.getOrderStream();

        RevenueService revenueService = new RevenueService();
        InventoryService inventoryService = new InventoryService();

        orderStream.subscribe(revenueService::processOrder);
        orderStream.subscribe(inventoryService::processOrder);

        revenueService.generateRevenueStatPerCategory()
                        .subscribe(Util.subscriber("revenue-service"));

        inventoryService.getInventoryStats()
                        .subscribe(Util.subscriber("inventory-service"));

        Util.sleep(20);
    }
}
