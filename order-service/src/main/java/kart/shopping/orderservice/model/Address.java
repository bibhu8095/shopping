
package kart.shopping.orderservice.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "addresses")
public class Address implements Serializable {

	private static final long serialVersionUID = 821174118063899237L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;

	@Column(name = "location")
	@NotNull
	private String location;

	@Column(name = "pincode")
	@NotNull
	private String pincode;

	@Column(name = "address_type")
	@NotNull
	private String addressType;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;

	public Address() {
		super();
	}

	public Address(Long addressId, String location, String pincode, String addressType) {
		super();
		this.addressId = addressId;
		this.location = location;
		this.pincode = pincode;
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

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
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
		return "Address [addressId=" + addressId + ", location=" + location + ", pinCode=" + pincode + ", addressType="
				+ addressType + ", user=" + user + "]";
	}
}
