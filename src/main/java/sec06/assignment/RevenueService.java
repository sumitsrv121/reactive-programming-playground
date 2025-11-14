package sec06.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RevenueService implements OrderProcessor {
    private static final Map<String, Integer> categoryToRevenue = new HashMap<>();

    public Flux<Map<String, Integer>> generateRevenueStatPerCategory() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> categoryToRevenue);
    }

    @Override
    public void processOrder(Order order) {
        Integer currentRevenue = categoryToRevenue.getOrDefault(order.category(), 0);
        int updatedRevenue = order.price() + currentRevenue;
        categoryToRevenue.put(order.category(), updatedRevenue);
    }
}
