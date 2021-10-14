public class ArrayString{
    public static void main(String[] args){
        String[] macGroup = {"Brayden", "Kim", "Alex", "Gerardo", "Albert"};
        String[] reversed = reverseMacGroup(macGroup);
        for(int j =  0; j < macGroup.length; j++){
            System.out.println("The names of the reversed group are: " + reversed[j]);
        }
    }
    public static String[] reverseMacGroup(String[] a){
        int i = a.length;
        int y = 0;
        String[] reversed = new String[i];
        
        while(i > 0 )
        {
            reversed[y] = a[i-1];
            i--;
            y++;
        }
        return reversed;
    }
}