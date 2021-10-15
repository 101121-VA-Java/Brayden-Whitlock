public class Driver {
    public static void main(String[] args){
        int p = 0;
        // for(int i = 0; i < 10; i++){
        //     System.out.println(i);
        //     p++;
        // }
        
        // System.out.println("i now equals: " + p);
        // saySomething();
        plusTwo(p);
        System.out.println(p);
        User u = new User("David");
        System.out.println(u.sayMyName());
        User b = new User("Beyonce");
        System.out.println(b.sayMyName());
        // System.out.println(u.name);
        // setNameToKevin(u);
        // System.out.println(u.name);
        // System.out.println(u);

        }
    public static void saySomething(){
        String s = "Hello";
        System.out.println(s);
    }

    public static void plusTwo(int p){
        p += 2;
        System.out.println(p);
    }
    public static void setNameToKevin(User u) {
        u.name = "Kevin";
        System.out.println(u.name);
    }
}
