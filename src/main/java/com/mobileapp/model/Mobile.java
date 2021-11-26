package com.mobileapp.model;

public class Mobile {

	private Integer mobileId;
	private String brand;
	private String model;
	private double price;

	public Mobile() {

	}

	public Mobile(Integer mobileId, String brand, String model, double price) {
		super();
		this.mobileId = mobileId;
		this.brand = brand;
		this.model = model;
		this.price = price;
	}

	public Mobile(String brand, String model, double price) {
		super();
		this.brand = brand;
		this.model = model;
		this.price = price;
	}

	public Integer getMobileId() {
		return mobileId;
	}

	public void setMobileId(Integer mobileId) {
		this.mobileId = mobileId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Mobile [mobileId=" + mobileId + ", brand=" + brand + ", model=" + model + ", price=" + price + "]";
	}

}
