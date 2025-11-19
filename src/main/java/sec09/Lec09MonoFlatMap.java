package sec09;

import common.Util;
import sec09.applications.PaymentService;
import sec09.applications.UserService;

public class Lec09MonoFlatMap {
    public static void main(String[] args) {
        UserService.getUserId("sam")
                .flatMap(PaymentService::getUserBalance)
                .subscribe(Util.subscriber("sam"));
    }
}
