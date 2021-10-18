package kart.shopping.paymentservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "addressId")
	@NotNull
	private int addressId;

	@Column(name = "location")
	@NotNull
	private String location;

	@Column(name = "pincode")
	@NotNull
	private String pincode;
	
	public Address() {
	}
	
	public Address(int addressId, String location, String pincode) {
		super();
		this.addressId = addressId;
		this.location = location;
		this.pincode = pincode;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
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

	@Override
	public String toString() {
		return "Address [id=" + id + ", addressId=" + addressId + ", location=" + location + ", pincode=" + pincode
				+ "]";
	}

}
