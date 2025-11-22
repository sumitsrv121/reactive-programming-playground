package sec09;

import common.Util;
import reactor.core.publisher.Mono;
import sec09.applications.*;

import java.util.List;

public class Lec16Assignment {

    record UserInformation(Integer userId, String username, Integer balance,
                           List<Order> orders) {

    }

    public static void main(String[] args) {
        UserService.getAllUsers()
                .flatMap(Lec16Assignment::getUserInformation)
                .subscribe(Util.subscriber());

        Util.sleep(10);

    }

    private static Mono<UserInformation> getUserInformation(User user) {
        return Mono.zip(
                PaymentService.getUserBalance(user.id()),
                OrderService.getUserOrders(user.id()).collectList()
        ).map(tuple ->
                new UserInformation(
                        user.id(), user.username(),
                        tuple.getT1(),
                        tuple.getT2()
                )
        );
    }
}
