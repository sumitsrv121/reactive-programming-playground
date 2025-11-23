package sec10;

import common.Util;
import reactor.core.publisher.Flux;
import sec10.assignment.BookOrder;
import sec10.assignment.RevenueReport;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Lec02Assignment {
    private static final Set<String> genres = Set.of("Science fiction",
            "Fantasy",
            "Suspense/Thriller"
    );

    public static void main(String[] args) {
        bookStream()
                .buffer(Duration.ofSeconds(5))
                .map(Lec02Assignment::getRevenueReport)
                .subscribe(Util.subscriber());

        Util.sleep(60);
    }

    private static RevenueReport getRevenueReport(List<BookOrder> bookOrders) {
        Map<String, Integer> genreToRevenue = bookOrders.stream()
                .filter(bookOrder -> genres.contains(bookOrder.genre()))
                .collect(Collectors.groupingBy(
                                BookOrder::genre,
                                Collectors.reducing(0, BookOrder::price, Integer::sum)
                        )
                );

        return new RevenueReport(LocalTime.now(), genreToRevenue);
    }

    private static Flux<BookOrder> bookStream() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> BookOrder.create());
    }
}
