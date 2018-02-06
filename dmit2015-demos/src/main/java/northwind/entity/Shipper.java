package northwind.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Shippers database table.
 * 
 */
@Entity
@Table(name="Shippers")
@NamedQuery(name="Shipper.findAll", query="SELECT s FROM Shipper s")
public class Shipper implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ShipperID")
	private int shipperID;

	@Column(name="CompanyName")
	private String companyName;

	@Column(name="Phone")
	private String phone;

	public Shipper() {
	}

	public int getShipperID() {
		return this.shipperID;
	}

	public void setShipperID(int shipperID) {
		this.shipperID = shipperID;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}