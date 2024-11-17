import java.util.*;

public class Zad2_LABS2_Solved {

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();


        // Generate Stack Randomly:
        Random random_generator = new Random();
        for (int i = 0; i < 10; i++)
            s.push(random_generator.nextInt(10) + 1); // Random Numbers from 1 to 10, inclusive;


        printStack(s);

        s = reorderStack(s);

        System.out.println();
        printStack(s);
    }


    /*
     * Idea:
     *
     * We keep 3 piles: Original Pile, Sorted Pile, Temporary Pile
     *
     * In the secnario where Oriinal Pile is 4 5 2 3 7
     *
     * At the start:
     * OP: 4 5 2 3 7
     * temp:
     * SP:
     *
     * The first one will move 7 to the sorted pile:
     * OP: 4 5 2 3
     * temp:
     * SP: 7
     *
     * Next, we move all numbers smaller than 3 to the temp file, so we can add 3 and then move them back again
     * (In this case we move only 7):
     * OP: 4 5 2 3
     * temp: 7
     * SP:
     *
     * OP: 4 5 2
     * temp: 7
     * SP: 3
     *
     * OP: 4 5 2
     * temp:
     * SP: 3 7
     *
     * We follow this algorithm until we move all elements:
     * OP: 4 5 2
     * temp : 7 3
     * SP:
     *
     * OP: 4 5
     * temp: 7 3
     * SP: 2
     *
     * OP: 4 5
     * temp:
     * SP: 2 3 7
     *
     * etc.
     *
     */
    private static Stack<Integer> reorderStack(Stack<Integer> s){
        Stack<Integer> temp = new Stack<>(); //yes ik the names mean nofin
        Stack<Integer> sorted = new Stack<>();

        while(!s.empty()){
            while (!sorted.empty() && s.peek() < sorted.peek()) {
                temp.push(sorted.pop());
            }

            sorted.push(s.pop());

            while (!temp.empty()) {
                sorted.push(temp.pop());
            }
        }

        return sorted;
    }

    public static void printStack(Stack<Integer> s){
        for (Integer e : s){
            System.out.println(e);
        }
    }
}
