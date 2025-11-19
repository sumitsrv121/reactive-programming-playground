package sec09;

import common.Util;
import sec09.applications.OrderService;
import sec09.applications.User;
import sec09.applications.UserService;

public class Lec11FluxFlatMap {
    public static void main(String[] args) {
        UserService.getAllUsers()
                .map(User::id)
                .flatMap(OrderService::getUserOrders)
                .subscribe(Util.subscriber());


        Util.sleep(4);
    }
}
