public class User {
    public String name;
    public static int numberOfUser;

    public User(){
        numberOfUser++;
    }
    public User(String name){
        this.name = name;
        numberOfUser++;
    }
    public String sayMyName(){
        return name;
    }
    public static void printNumberOfUser(){
        System.out.println("this is how manny users there are: " + numberOfUser );
    }
}
