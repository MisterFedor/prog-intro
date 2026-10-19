import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class M {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t>0) {
            int n = sc.nextInt();
            List<Integer> temp = new ArrayList<>();
            int cntK = 0;
            for (int i = 0; i < n; i++) {
                temp.add(sc.nextInt());
            }
            Map<Integer, Integer> C = new HashMap<>();
            for (int j = n - 1; j > 0; j--) {
                for (int i = 0; i < j; i++) {
                    cntK = cntK + C.getOrDefault(2 * temp.get(j) - temp.get(i), 0);
                }
                C.put(temp.get(j), C.getOrDefault(temp.get(j), 0) + 1);
            } 
            System.out.println(cntK);
            t--;
        }
        sc.close();
    }
}
