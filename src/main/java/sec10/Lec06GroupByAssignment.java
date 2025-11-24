package sec10;

import common.Util;
import reactor.core.publisher.Flux;
import sec10.assignment.OrderProcessingService;
import sec10.assignment.PurchaseOrder;

import java.time.Duration;

public class Lec06GroupByAssignment {
    public static void main(String[] args) {

        purchaseOrderStream()
                .filter(OrderProcessingService.canProcess())
                .groupBy(PurchaseOrder::category)
                .flatMap(gf -> gf.transform(OrderProcessingService.getProcessor(gf.key())))
                .subscribe(Util.subscriber());

        Util.sleep(20);
    }



    private static Flux<PurchaseOrder> purchaseOrderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> PurchaseOrder.createPurchaseOrder());
    }
}
