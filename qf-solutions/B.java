import java.util.Scanner;

public class B {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int init = -710*25000;
        int end = 710*25000;
        int cnt = 0;
        for (int i = init; i<end ; i=i+710){
            System.out.println(i);
            cnt++;
            if(cnt>=n){
                break;
            }
        }
        sc.close();
    }
    
}
