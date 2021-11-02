package com.revature.models;

public class Book {
	private int id;
	private int vinNumber;
	private String title;
	private Genre genre;
	private boolean isSoftCover;
	private boolean isAvailable;
	private String author;
	private double price;

	public Book() {
		super();
	}

	public Book(int vinNumber, String title, Genre genre, boolean isSoftCover, String author, double price) {
		super();
		this.vinNumber = vinNumber;
		this.title = title;
		this.genre = genre;
		this.isSoftCover = isSoftCover;
		this.author = author;
		this.price = price;
	}

	public Book(int id, int vinNumber, String title, Genre genre, boolean isSoftCover, boolean isAvailable,
			String author, double price) {
		super();
		this.id = id;
		this.vinNumber = vinNumber;
		this.title = title;
		this.genre = genre;
		this.isSoftCover = isSoftCover;
		this.isAvailable = isAvailable;
		this.author = author;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVinNumber() {
		return vinNumber;
	}

	public void setVinNumber(int vinNumber) {
		this.vinNumber = vinNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public boolean isSoftCover() {
		return isSoftCover;
	}

	public void setSoftCover(boolean isSoftCover) {
		this.isSoftCover = isSoftCover;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + id;
		result = prime * result + (isAvailable ? 1231 : 1237);
		result = prime * result + (isSoftCover ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + vinNumber;
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
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (genre != other.genre)
			return false;
		if (id != other.id)
			return false;
		if (isAvailable != other.isAvailable)
			return false;
		if (isSoftCover != other.isSoftCover)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (vinNumber != other.vinNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", vinNumber=" + vinNumber + ", title=" + title + ", genre=" + genre
				+ ", isSoftCover=" + isSoftCover + ", isAvailable=" + isAvailable + ", author=" + author + ", price="
				+ price + "]";
	}

}
