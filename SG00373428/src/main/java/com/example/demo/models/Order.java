package com.example.demo.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.example.demo.models.validations.OrderGETValidations;
import com.example.demo.models.validations.OrderPOSTValidations;
import com.example.demo.models.validations.SalespersonPOSTValidations;
import com.example.demo.models.views.OrderViews;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

@Entity(name = "ordertable")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message = "oid must be provided", groups = OrderPOSTValidations.class)
	private String oid;
	@NotNull(message = "orderDate must be provided", groups = OrderPOSTValidations.class)
	private String orderDate;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "spid_FK")
	@NotNull(message = "Salesperson spid must be provided", groups = OrderPOSTValidations.class)
	private Salesperson orderSalesperson;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "pid_FK")
	@NotNull(message = "orderProduct must be provided", groups = OrderPOSTValidations.class)
	private Product orderProduct;
	@NotNull(message = "qty is not present", groups = OrderGETValidations.class)
	@NotNull(message = "orderQuantity must be provided", groups = OrderPOSTValidations.class)
	private int orderQuantity;
	
	public Order() {
		super();
	}
	
	public Order(int id, String oid, String orderDate, Salesperson orderSalesperson, Product orderProduct,
			int orderQuantity) {
		super();
		this.id = id;
		this.oid = oid;
		this.orderDate = orderDate;
		this.orderSalesperson = orderSalesperson;
		this.orderProduct = orderProduct;
		this.orderQuantity = orderQuantity;
	}

	public Order(String orderDate) {
		super();
		this.orderDate = orderDate;
	}
	public Order(String orderDate, Salesperson orderSalesperson) {
		super();
		this.orderDate = orderDate;
		this.orderSalesperson = orderSalesperson;
	}
	public Order(String orderDate, Salesperson orderSalesperson, Product orderProduct) {
		super();
		this.orderDate = orderDate;
		this.orderSalesperson = orderSalesperson;
		this.orderProduct = orderProduct;
	}
	public Order(String oid, String orderDate, Salesperson orderSalesperson) {
		super();
		this.oid = oid;
		this.orderDate = orderDate;
		this.orderSalesperson = orderSalesperson;
	}
	public Order(String oid, String orderDate, Salesperson orderSalesperson, Product orderProduct) {
		super();
		this.oid = oid;
		this.orderDate = orderDate;
		this.orderSalesperson = orderSalesperson;
		this.orderProduct = orderProduct;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public Salesperson getOrderSalesperson() {
		return orderSalesperson;
	}
	public void setOrderSalesperson(Salesperson orderSalesperson) {
		this.orderSalesperson = orderSalesperson;
	}
	public Product getorderProduct() {
		return orderProduct;
	}
	public void setOrderProduct(Product orderProduct) {
		this.orderProduct = orderProduct;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
}
