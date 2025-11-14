package sec06.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class InventoryService implements OrderProcessor {
    private static final int INITIAL_QUANTITY = 500;
    private static final Map<String, Integer> INVENTORY_STAT_PER_QUANTITY = new HashMap<>();


    public Flux<Map<String, Integer>> getInventoryStats() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> INVENTORY_STAT_PER_QUANTITY);
    }


    @Override
    public void processOrder(Order order) {
        Integer currentQuantity = INVENTORY_STAT_PER_QUANTITY
                .getOrDefault(order.category(), INITIAL_QUANTITY);
        int updatedQuantity = currentQuantity - order.quantity();
        INVENTORY_STAT_PER_QUANTITY.put(order.category(), updatedQuantity);
    }
}
