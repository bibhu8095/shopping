
package kart.shopping.orderservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long addressId;

	@Column(name = "location")
	@NotNull
	private String location;

	@Column(name = "pin_code")
	@NotNull
	private String pinCode;

	@Column(name = "address_type")
	@NotNull
	private String addressType;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Address() {
		super();
	}

	public Address(Long addressId, String location, String pinCode, String addressType) {
		super();
		this.addressId = addressId;
		this.location = location;
		this.pinCode = pinCode;
		this.addressType = addressType;
		
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", location=" + location + ", pinCode=" + pinCode + ", addressType="
				+ addressType + ", user=" + user + "]";
	}	
}
