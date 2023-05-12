package shoppingweb.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name= "Product")
@Data
public class Product {

	
	@Id
	private String code;
	private String name;
	private double price;
	private String imgurl;
	private String imgurl1;
	private String imgurl2;
	@OneToMany(targetEntity = AddedProduct.class)
	private List<AddedProduct> addedProducts;
}
