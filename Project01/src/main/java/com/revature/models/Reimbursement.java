package com.revature.models;

import java.sql.Timestamp;

public class Reimbursement {
	private int reimId;
	private double reimAmount;
	private Timestamp submit;
	private Timestamp resolve;
	private String descrip;
//	private blob picture
	private User author;
	private User resolver;
	private Status status;
	private Type type;

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int reimId, double reimAmount, Timestamp submit, String descrip, User author, Status status,
			Type type) {
		super();
		this.reimId = reimId;
		this.reimAmount = reimAmount;
		this.submit = submit;
		this.descrip = descrip;
		this.author = author;
		this.status = status;
		this.type = type;
	}

	public Reimbursement(int reimId, double reimAmount, Timestamp submit, Timestamp resolve, String descrip,
			User author, User resolver, Status status, Type type) {
		super();
		this.reimId = reimId;
		this.reimAmount = reimAmount;
		this.submit = submit;
		this.resolve = resolve;
		this.descrip = descrip;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public int getReimId() {
		return reimId;
	}

	public void setReimId(int reimId) {
		this.reimId = reimId;
	}

	public double getReimAmount() {
		return reimAmount;
	}

	public void setReimAmount(double reimAmount) {
		this.reimAmount = reimAmount;
	}

	public Timestamp getSubmit() {
		return submit;
	}

	public void setSubmit(Timestamp submit) {
		this.submit = submit;
	}

	public Timestamp getResolve() {
		return resolve;
	}

	public void setResolve(Timestamp resolve) {
		this.resolve = resolve;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getResolver() {
		return resolver;
	}

	public void setResolver(User resolver) {
		this.resolver = resolver;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((descrip == null) ? 0 : descrip.hashCode());
		long temp;
		temp = Double.doubleToLongBits(reimAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + reimId;
		result = prime * result + ((resolve == null) ? 0 : resolve.hashCode());
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((submit == null) ? 0 : submit.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (descrip == null) {
			if (other.descrip != null)
				return false;
		} else if (!descrip.equals(other.descrip))
			return false;
		if (Double.doubleToLongBits(reimAmount) != Double.doubleToLongBits(other.reimAmount))
			return false;
		if (reimId != other.reimId)
			return false;
		if (resolve == null) {
			if (other.resolve != null)
				return false;
		} else if (!resolve.equals(other.resolve))
			return false;
		if (resolver == null) {
			if (other.resolver != null)
				return false;
		} else if (!resolver.equals(other.resolver))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (submit == null) {
			if (other.submit != null)
				return false;
		} else if (!submit.equals(other.submit))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimId=" + reimId + ", reimAmount=" + reimAmount + ", submit=" + submit + ", resolve="
				+ resolve + ", descrip=" + descrip + ", author=" + author + ", resolver=" + resolver + ", status="
				+ status + ", type=" + type + "]";
	}
}
