package tacos;


import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class Order {
	
	private Long id;
	private Date placedAt;

	@NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Street is required")
	private String street;
	@NotBlank(message = "City is required")
	private String city;
	@NotBlank(message = "State is required")
	private String state;
	@NotBlank(message = "Zip code is required")
	private String zip;
	// @CreditCardNumber:校验此字段的值必须是合法的信用卡号（通过Luhn算法检查）
	@CreditCardNumber(message = "Not a valid credit card number")
	private String ccNumber;
	// @Pattern:使用各种模式来匹配字段的值是否正确
	@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
	private String ccExpiration;
	// @Digits:校验字段的值是否为数字，以及数字长度、数字的小数位数
	@Digits(integer = 3, fraction = 0, message = "Invalid CVV")
	private String ccCVV;
	
	private List<Taco> tacos;
	
	public void addDesign(Taco design) {
		this.tacos.add(design);
	}
}
