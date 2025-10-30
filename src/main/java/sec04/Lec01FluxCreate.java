package sec04;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            var country = Util.faker().country();
            String countryName;
            do {
                countryName = country.name();
                fluxSink.next(countryName);
            } while (!countryName.equalsIgnoreCase("Canada"));
            fluxSink.complete();
        }).subscribe(Util.subscriber());
    }
}
