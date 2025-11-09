package sec05;

import common.ExternalServiceClient;
import common.Util;
import sec05.assignment.ProductService;
import sec05.assignment.ProductServiceImpl;

public class Lec11Assignment {
    public static void main(String[] args) {
        ProductService productService = new ProductServiceImpl(new ExternalServiceClient());

        productService.getProductName(3)
                .subscribe(Util.subscriber());

        Util.sleep(3);
    }
}
