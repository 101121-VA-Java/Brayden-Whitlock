package notesP0.models;


public class Book{
    public String title;
    public String genre;
    public boolean forSale;
    public double price;

    public Book(String title, String genre, boolean forSale, double price){
        this.title = title;
        this.genre = genre;
        this.forSale = forSale;
        this.price = price;
    }
}