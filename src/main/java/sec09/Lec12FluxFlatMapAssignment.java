package sec09;

import common.Util;
import reactor.core.publisher.Flux;
import sec09.assignment.ExternalServiceClient;

public class Lec12FluxFlatMapAssignment {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        Flux.range(1, 10)
                .flatMap(client::getProductDetails)
                .subscribe(Util.subscriber());

        Util.sleep(4);
    }
}
