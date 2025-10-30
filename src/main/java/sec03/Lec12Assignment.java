package sec03;

import common.Util;
import sec03.assignment.Instrument;
import sec03.assignment.Stock;
import sec03.assignment.StockSubscriber;

public class Lec12Assignment {
    public static void main(String[] args) {
        Instrument equityInstrument = new Stock();
        equityInstrument.getLatestPriceUpdate(500)
                .subscribe(new StockSubscriber());

        Util.sleep(21);
    }
}
