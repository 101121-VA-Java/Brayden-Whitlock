package com.revature.models;

public class BooksToCustomer {
	private int books_to_customer_id;
	private int b_id;
	private int c_id;
	private double b_price;
	private boolean b_price_accepted;

	public BooksToCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BooksToCustomer(int books_to_customer_id, int b_id, int c_id, double b_price, boolean b_price_accepted) {
		super();
		this.books_to_customer_id = books_to_customer_id;
		this.b_id = b_id;
		this.c_id = c_id;
		this.b_price = b_price;
		this.b_price_accepted = b_price_accepted;
	}

	public int getbooks_to_customer_id() {
		return books_to_customer_id;
	}

	public void setbooks_to_customer_id(int books_to_customer_id) {
		this.books_to_customer_id = books_to_customer_id;
	}

	public int getB_id() {
		return b_id;
	}

	public void setB_id(int b_id) {
		this.b_id = b_id;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public double getB_price() {
		return b_price;
	}

	public void setB_price(double b_price) {
		this.b_price = b_price;
	}

	public boolean isB_price_accepted() {
		return b_price_accepted;
	}

	public void setB_price_accepted(boolean b_price_accepted) {
		this.b_price_accepted = b_price_accepted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + b_id;
		long temp;
		temp = Double.doubleToLongBits(b_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (b_price_accepted ? 1231 : 1237);
		result = prime * result + books_to_customer_id;
		result = prime * result + c_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BooksToCustomer other = (BooksToCustomer) obj;
		if (b_id != other.b_id)
			return false;
		if (Double.doubleToLongBits(b_price) != Double.doubleToLongBits(other.b_price))
			return false;
		if (b_price_accepted != other.b_price_accepted)
			return false;
		if (books_to_customer_id != other.books_to_customer_id)
			return false;
		if (c_id != other.c_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BooksToCustomer [books_to_customer_id=" + books_to_customer_id + ", b_id=" + b_id + ", c_id=" + c_id
				+ ", b_price=" + b_price + ", b_price_accepted=" + b_price_accepted + "]";
	}

}
