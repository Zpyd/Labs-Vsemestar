import java.util.Arrays;
import java.util.Scanner;

public class Group1_Lab3_Exercise_3_DP {
    public static String[] dashes_DP;

    public static void main(String[] args) {
        Scanner user_input = new Scanner(System.in);

        System.out.print("Enter the number of dashes: ");
        int num_of_dashes = user_input.nextInt();
        dashes_DP = new String[num_of_dashes];
        Arrays.fill(dashes_DP, "");

        // Change this to change the number of intervals:
        int num_of_intervals = 2;
        for (int k = 1; k <= num_of_intervals; k++) {
            System.out.print(get_Interval(num_of_dashes));

            print_fraction_of_interval(num_of_dashes-1);
            System.out.print(dashes_DP[num_of_dashes-1]);

            if (k == num_of_intervals)
                System.out.print(get_Interval(num_of_dashes));
        }
    }


    public static void print_fraction_of_interval(int n) {
        if (!dashes_DP[n].isEmpty()) {
            return;
        }

        if (n == 0)
            return;


        print_fraction_of_interval(n-1);

        dashes_DP[n] = dashes_DP[n-1] + get_Interval(n) + dashes_DP[n-1];

        print_fraction_of_interval(n-1);
    }

    public static String get_Interval(int n) {
        String line = "";
        for (int i = 0; i < n; i++)
            line += "-";
        line += "\n";
        return line;
    }
}
