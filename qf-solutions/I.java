import java.util.Scanner;

public class I {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[] xi = new int[n];
        int[] yi = new int[n];
        int[] hi = new int[n];

        for (int j = 0; j < n; j++) {
            String str = sc.nextLine();
            String[] splitted = str.split(" ");

            xi[j] = Integer.parseInt(splitted[0]);
            yi[j] = Integer.parseInt(splitted[1]);
            hi[j] = Integer.parseInt(splitted[2]);
        }
        int xl = Integer.MAX_VALUE;
        int xr = Integer.MIN_VALUE;
        int yl = Integer.MAX_VALUE;
        int yr = Integer.MIN_VALUE;
        for (int k = 0; k < n; k++) {
            xl = Math.min(xl, xi[k] - hi[k]);
            xr = Math.max(xr, xi[k] + hi[k]);
            yl = Math.min(yl, yi[k] - hi[k]);
            yr = Math.max(yr, yi[k] + hi[k]);
        }
        int h = (int) Math.ceil((double) (Math.max(xr - xl, yr - yl)) / 2);
        int x = (xl + xr) / 2;
        int y = (yl + yr) / 2;
        System.out.println(x + " " + y + " " + h);
        sc.close();
    }
}
