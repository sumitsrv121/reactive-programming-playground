package sec10.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class OrderProcessingService {
    private static final Map<String, UnaryOperator<Flux<PurchaseOrder>>> processor
            = Map.of(
            "Kids", processKidsPurchaseOrder(),
            "Automotive", processAutomotivePurchaseOrder()
    );

    private static UnaryOperator<Flux<PurchaseOrder>> processAutomotivePurchaseOrder() {
        return flux -> flux
                .map(po -> new PurchaseOrder(po.item(), po.category(), po.price() + 100));
    }

    private static UnaryOperator<Flux<PurchaseOrder>> processKidsPurchaseOrder() {
        return flux -> flux
                .flatMap(po -> getFreePurchaseOrderForKid(po).flux().startWith(po));
    }

    private static Mono<PurchaseOrder> getFreePurchaseOrderForKid(PurchaseOrder purchaseOrder) {
        return Mono.fromSupplier(() -> PurchaseOrder.createFreeOrder(purchaseOrder.category()));
    }

    public static Predicate<PurchaseOrder> canProcess() {
        return po -> processor.containsKey(po.category());
    }

    public static UnaryOperator<Flux<PurchaseOrder>> getProcessor(String category) {
        return processor.get(category);
    }

}
