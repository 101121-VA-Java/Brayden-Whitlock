public class newCalculator{
    public static void main(String[] args){
        double w = java.lang.Math.random()*8;
        int sum = add(1,2);
        int sub = subtract(1,2);
        double mul = multiply(2,2);
        int rem = remander(5,4); 
        // String day = weekday("Monday");
        // String day2 = weekday("Tusday");
        // String day3 = weekday("Wednesday");
        // String day4 = weekday("Thursday");
        // String day5 = weekday("Friday");
        // String day6 = weekday("Saterday");
        // String day7 = weekday("Sunday");
        // String day8 = weekday("asdfd");
        String gre = greeting("Bob");
        String[] dayColection = {"Monday", "Tusday", "Wednesday", "Thursday",
                                "Friday", "Saterday","Sunday", "asdfd"};
        
        countdown(10);
        System.out.println(sum);
        System.out.println(sub);
        System.out.println(mul);
        System.out.println(rem);
        System.out.println(gre);
        System.out.println(weekday(dayColection[(int) w]));

        // for (int i = 0; i < 7; i++)
        // { 
        //     if(i == 0){
        //         System.out.println(day8);
        //     }
        //     if(i == 1){
        //         System.out.println(day);
        //     }
        //     if(i == 2){
        //         System.out.println(day2);
        //     }
        //     if(i == 3){
        //         System.out.println(day3);
        //     }
        //     if(i == 4){
        //         System.out.println(day4);
        //     }
        //     if(i == 5){
        //         System.out.println(day5);
        //     }
        //     if(i == 6){
        //         System.out.println(day6);
        //     }
        //     if(i == 7){
        //         System.out.println(day7);
        //     }

        // }

    }
    public static int add(int a, int b){
        return a + b;
    }
    public static int subtract(int a, int b){
        return a - b;
    }
    public static double multiply(double a, double b){
        return a * b;
    }
    public static int remander(int a, int b){
        return a % b;
    }
    public static String weekday(String weekday){
        String day = ""; 
        switch (weekday){
                case "Monday":
                    day = "It is Monday to bad...";
                    break;
                case "Tusday":
                    day = "It is Tusday its ok";
                    break;
                case "Wednesday":
                    day = "It is Wednesday almost half way there";
                    break;
                case "Thursday":
                    day = "It is Tursday one work day left";
                    break;
                case "Friday":
                    day = "It is Friday Yay";
                    break;
                case "Saterday":
                    day = "It is Saterday time to chill";
                    break;
                case "Sunday":
                    day = "It is Sunday awsome";
                    break;
                default:
                    day= "You dont know what day it is do you?";
                    break;
        }
        return day;
    }
    public static void countdown(int startValue) { 
       while (startValue >= 0){
           System.out.println(startValue);
           startValue--;
       }
    }
    public static String greeting(String name){
        String names = "I hope you are having a good day " + name;
        return names;
    }
}