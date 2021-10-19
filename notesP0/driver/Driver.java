package notesP0.driver;

import notesP0.models.Book;

public class Driver{
    public static void main(String[] args){
        String title = "A Bugs Life"; 
        String genre = "Historical";
        boolean forSale = true;
        double price = 20.99;

        // Scanner sc = new Scanner(System.in);
        // int i = sc.nextInt();

        Book test =  new Book("Wonderfull World", "Fantasy", false, 19.99);
        System.out.println("test2");
        System.out.println("title: " + test.title + " genre: " + test.genre + " For Sale: " +
                             test.forSale + " Price: $" + test.price);
    }
}