package sec04;

import common.Util;
import reactor.core.publisher.Flux;
import sec04.helper.NameGenerator;

public class Lec02FluxCreateRefactor {
    public static void main(String[] args) {
        var generator = new NameGenerator();
        Flux.create(generator)
                .subscribe(Util.subscriber());

        for (int i = 0; i < 10; i++) {
            generator.generateName();
        }
    }
}
