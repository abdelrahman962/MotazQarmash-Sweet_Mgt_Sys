package Sweet.system;

public class Provider extends ProductOwner {

    public Provider(String email, String password, String city) {
        super(email, password, city);
    }

    @Override
    public void addProduct(Product product) {

        product.setproviderEmail(this.email);
        super.addProduct(product);
    }


}
