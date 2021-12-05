package dz8.product;

import org.springframework.integration.annotation.Gateway;

public interface ProductGateway {
    @Gateway(replyChannel = "errorChannel")
    Product getProduct();
}
