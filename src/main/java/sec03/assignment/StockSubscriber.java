package sec03.assignment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StockSubscriber implements Subscriber<Integer> {
    private static final Logger log = LoggerFactory.getLogger(StockSubscriber.class);
    private Subscription subscription;
    private long portfolioPrice;
    private int stockCount = 0;
    private int currentPrice = 0;


    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(Long.MAX_VALUE);
        this.portfolioPrice = 1000;
    }

    @Override
    public void onNext(Integer currentPrice) {
        log.info("Current price of stock {}", currentPrice);
        this.currentPrice = currentPrice;
        if (currentPrice > 110 && stockCount > 0) {
            // sell all stocks
            subscription.cancel();
            portfolioPrice += ((long) stockCount * currentPrice);
            stockCount = 0;
            log.info("Portfolio value {} and profit made {}", portfolioPrice, (portfolioPrice - 1000));
        } else if (currentPrice < 90 && portfolioPrice >= currentPrice) {
            stockCount++;
            portfolioPrice -= currentPrice;
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error while subscribing to stock price: {} ", throwable.getMessage(), throwable);
    }

    @Override
    public void onComplete() {
        log.info("Completed!");
    }
}
