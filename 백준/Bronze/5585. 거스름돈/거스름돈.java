import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int money = 1000 - Integer.parseInt(br.readLine());
        int[] moneyArr = { 500, 100, 50, 10, 5, 1 };
        int count = 0;
        while (money > 0) {
            for (int i = 0; i < moneyArr.length; i++) {
                if(money >= moneyArr[i]){
                    money-=moneyArr[i];
                    break;
                }
            }
            count++;
        }
        System.out.println(count);
    }

}
