package northwind.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import java.math.BigDecimal;


/**
 * The persistent class for the `Order Details` database table.
 * 
 */
@Entity
@Table(name="`Order Details`")
@NamedQuery(name="OrderDetail.findAll", query="SELECT o FROM OrderDetail o")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderDetailPK id;

	@Column(name="Discount")
	private double discount;

	@Column(name="Quantity")
	private short quantity;

	@Column(name="UnitPrice")
	private BigDecimal unitPrice;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="OrderID", updatable=false, insertable=false)
	@XmlTransient
	private Order order;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="ProductID", updatable=false, insertable=false)
	private Product product;

	public OrderDetail() {
	}

	public OrderDetailPK getId() {
		return this.id;
	}

	public void setId(OrderDetailPK id) {
		this.id = id;
	}

	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public short getQuantity() {
		return this.quantity;
	}

	public void setQuantity(short quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}