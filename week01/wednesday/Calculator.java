public class Calculator{
    public static void main (String[] args){
        // switch (args[0]){
        //     case "Kevin":
        //         System.out.println("Hello Kevin");
        //         break;
        //     case "Friday":
        //         System.out.println("cant waight for friday ");
        //         break;
        //     default:
        //     System.out.println("what?");
        // }

        int a = 5;
        int b = 7;

        printHi(a);
        System.out.println("b:");
        printHi(b);

        int sum = add(a,b);
        System.out.println(sum);
    }
    public static void printHi(int numberOfTimes){
        for(int i = 0; i < numberOfTimes; i++ ){
            System.out.println("hello");
        }
    }

    public static int add(int a, int b){
        return a + b;

    }
    public static int subtract(int a, int b){
        return a - b;
    }
}