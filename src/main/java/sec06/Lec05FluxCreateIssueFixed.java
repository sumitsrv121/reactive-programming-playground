package sec06;

import common.Util;
import reactor.core.publisher.Flux;
import sec04.helper.NameGenerator;

public class Lec05FluxCreateIssueFixed {
    public static void main(String[] args) {
        var generator = new NameGenerator();
        Flux<String> flux = Flux.create(generator).share();

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));

        for (int i = 0; i < 10; i++) {
            generator.generateName();
        }
    }
}