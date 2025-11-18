package sec09;

import common.Util;
import sec09.helper.Kayak;

public class Lec06MergeUseCase {
    public static void main(String[] args) {
        Kayak.getFlights()
                .subscribe(Util.subscriber());

        Util.sleep(3);
    }
}
