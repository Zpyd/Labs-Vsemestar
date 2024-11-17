import java.util.Scanner;

public class Group1_Lab3_Exercise_3_Commented {
    public static void main(String[] args) {
        Scanner user_input = new Scanner(System.in);

        System.out.print("Enter the number of dashes: ");
        int num_of_dashes = user_input.nextInt();


        // Change this to change the number of intervals:
        int num_of_intervals = 2;
        for (int k = 1; k <= num_of_intervals; k++) {
            // Print start of new interval (This would be the the part of the interval)
            print_interval(num_of_dashes);


            // Print the middle intervals recursively: (This would start the recursion with 1/2)
            print_fraction_of_interval(num_of_dashes-1);


            // If no new interval needs to be printed, close interval: (This would be the next whole number)
            if (k == num_of_intervals)
                print_interval(num_of_dashes);
        }
    }

    /*
     * Idea behind recurions:
     *
     * For an input of 3, the interval looks like: 0 1/8 1/4 1/8 1/2 1/8 1/4 1/8 1
     *
     * We can make this recursively. If we want to print 1/2, we need to print 1/4, then print 1/2, then print 1/4.
     * In order to print 1/4, we need to print 1/8, print 1/4, print 1/8, in order to print 1/8, we need to print 1/16 (We get a base case),
     * pritn 1/8 (we print it), and print 1/16 (we get a base case).
     *
     */
    public static void print_fraction_of_interval(int n) {
        if (n == 0)
            return;

        // Print half of the fraction.
        print_fraction_of_interval(n-1);

        // Print the current fraction.
        print_interval(n);

        // Print half of the fraction again.
        print_fraction_of_interval(n-1);
    }

    // Print the number of dashes and a newline.
    public static void print_interval(int n) {
        for (int i = 0; i < n; i++)
            System.out.print("-");
        System.out.println();
    }
}
