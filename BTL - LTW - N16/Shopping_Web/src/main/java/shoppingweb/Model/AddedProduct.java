package shoppingweb.Model;

import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="AddedProduct")
@Data
public class AddedProduct {
	@Id
	private String Code;
	private int quantity;
	private double price;
	private String color;
	private String size;
	private String stt;
	@OneToOne(targetEntity = Product.class)
	private Product pd;
}
