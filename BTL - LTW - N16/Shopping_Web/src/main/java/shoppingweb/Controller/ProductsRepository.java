package shoppingweb.Controller;

import org.springframework.data.jpa.repository.JpaRepository;

import shoppingweb.Model.AddedProduct;
import shoppingweb.Model.Product;

public interface ProductsRepository extends JpaRepository<Product, String> {

}
