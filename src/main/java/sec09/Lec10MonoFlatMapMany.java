package sec09;

import common.Util;
import sec09.applications.OrderService;
import sec09.applications.UserService;

public class Lec10MonoFlatMapMany {
    public static void main(String[] args) {
        UserService.getUserId("mike")
                .flatMapMany(OrderService::getUserOrders)
                .subscribe(Util.subscriber("mike"));
        
        Util.sleep(4);
    }
}
