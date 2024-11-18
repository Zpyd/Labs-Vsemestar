public class Zad3_LABS3 {
    public static final int black = 0;
    public static final int white = 1;

    public static void main(String[] args) {
        int[] Balls = {black, black, white, black, white, white, white, black};
        // int[] Balls = {white, white, white, white, black, black, black, black};
        // int[] Balls = {black, white, black, black, white, white, black, black, white, white};

        System.out.println("The min amount of wire to connect them is: " + getMinWire(Balls));
    }

    public static int getMinWire(int[] Balls) {
        int[] BlackBalls = new int[Balls.length/2];
        int[] WhiteBalls = new int[Balls.length/2];

        // Split balls into two arrays, black and white:
        for (int i = 0, bi = 0, wi = 0; i < Balls.length; i++) {
            if (Balls[i] == black) {
                BlackBalls[bi] = i;
                bi++;
            } else {
                WhiteBalls[wi] = i;
                wi++;
            }
        }


        boolean[] connected = new boolean[Balls.length]; // By default, the values are false so no need to set them.
        int[] answer = {Integer.MAX_VALUE}; // It's an array so it's passed by reference.
        connectBalls(BlackBalls, WhiteBalls, connected, 0, answer);
        return answer[0];
    }

    public static void connectBalls(int[] BlackBalls, int[] WhiteBalls, boolean[] connected, int sum, int[] answer) {
        for (int black_ball : BlackBalls) {
            if (!connected[black_ball]) {
                for (int white_ball : WhiteBalls) {
                    if (!connected[white_ball]) {
                        // If found unconnected black - white pair, connect them, try the possibility, and undo connection.
                        connected[black_ball] = true;
                        connected[white_ball] = true;
                        connectBalls(BlackBalls, WhiteBalls, connected, sum + Math.abs(white_ball - black_ball), answer);
                        connected[black_ball] = false;
                        connected[white_ball] = false;
                    }
                }
            }
        }

        // If they are not all connected, don't save the value.
        for (boolean is_connected : connected)
            if (!is_connected)
                return;
        answer[0] = Math.min(answer[0], sum);
    }
}
