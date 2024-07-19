import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] xy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int xs = xy[0];
        int ys = xy[1];

        int[] xydxdy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int xe = xydxdy[0], ye = xydxdy[1], dx = xydxdy[2], dy = xydxdy[3];

        if (dx == 0 && dy != 0) {
            dy = 1;
        } else if (dy == 0 && dx != 0) {
            dx = 1;
        } else {
            int gcd = gcd(dx, dy);
            dx /= gcd;
            dy /= gcd;
        }

        int result = Integer.MAX_VALUE;
        int[] resultPos = new int[2];

        while (true) {
            int distance = (xs - xe) * (xs - xe) + (ys - ye) * (ys - ye);
            if (result > distance) {
                result = distance;
                resultPos[0] = xe;
                resultPos[1] = ye;
            } else {
                break;
            }
            xe += dx;
            ye += dy;
        }
        System.out.println(resultPos[0] + " " + resultPos[1]);
    }

    private static int gcd(int x, int y) {

        if (x % y == 0) {
            return y;
        }
        return gcd(y, x % y);
    }

}
