package dz8.product;

public class ProductGatewayImpl implements ProductGateway {
    public Product getProduct(){
        return new Product(1, "New Product");
    }
}
