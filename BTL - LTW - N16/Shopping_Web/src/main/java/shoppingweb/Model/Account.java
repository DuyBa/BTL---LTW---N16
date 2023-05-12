package shoppingweb.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Account")
public class Account {
	@Id
	private String gmail;
	private String password;
	private String name;
	private String address;
	private String phonenumber;
	private String role;
	@OneToMany(targetEntity = AddedProduct.class)
	private List<AddedProduct> addedProducts;
	@OneToMany(targetEntity = Bill.class)
	private List<Bill> listbill;
}
