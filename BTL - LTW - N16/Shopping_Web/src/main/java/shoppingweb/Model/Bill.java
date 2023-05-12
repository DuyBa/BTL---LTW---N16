package shoppingweb.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="bill")
@Data
public class Bill {
	@Id
	private String id;
	private String name;
	private String tel;
	private String email;
	private String address;
	private String des;
	private String stt;
	@OneToOne(targetEntity = Account.class)
	private Account acc;
	@OneToMany(targetEntity = AddedProduct.class)
	private List<AddedProduct> listapds;
}
