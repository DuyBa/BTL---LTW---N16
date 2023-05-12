package shoppingweb.Controller;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import shoppingweb.Model.AddedProduct;


public interface AddedProductRepository extends JpaRepository<AddedProduct, String> {

}
