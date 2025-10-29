package sec03;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sec03.helper.NameGenerator;

import java.util.List;

public class Lec07FluxVsList {
    private static final Logger log = LoggerFactory.getLogger(Lec07FluxVsList.class);
    public static void main(String[] args) {
        List<String> namesList = NameGenerator.getNamesList(10);
        log.info("names {}", namesList);

        NameGenerator.getNameFlux(10)
                .subscribe(Util.subscriber());
    }
}
