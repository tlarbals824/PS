import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] aInput = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] cInput = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        var a= new Dessert(aInput[0],aInput[1],aInput[2]);
        var c= new Dessert(cInput[0],cInput[1],cInput[2]);

        System.out.print((c.x-a.z)+" ");
        System.out.print((c.y/a.y)+" ");
        System.out.print((c.z-a.x)+" ");
        System.out.println();
    }

    static class Dessert {
        int x, y, z;

        public Dessert(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

}
