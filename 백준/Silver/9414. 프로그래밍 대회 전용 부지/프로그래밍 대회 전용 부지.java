import java.util.*;
import java.io.*;

class Main {

    static int ableMoney = 1000000;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {

            List<Integer> moneyList = new LinkedList<>();
            while (true) {
                int money = Integer.parseInt(br.readLine());
                if (money == 0)
                    break;
                moneyList.add(money);
            }

            int currentTime = 1;
            int totalMoney = 0;
            boolean isExit = false;
            Collections.sort(moneyList, Comparator.reverseOrder());
            for (int money : moneyList) {
                totalMoney += 2 * pow(money, currentTime++);
                if (totalMoney > ableMoney) {
                    bw.write("Too expensive\n");
                    isExit = true;
                    break;
                }
            }
            if (!isExit)
                bw.write(totalMoney + "\n");
        }
        bw.flush();
    }

    static int pow(int number, int count) {
        int result = 1;
        for (int i = 0; i < count; i++) {
            result*=number;
        }
        return result;
    }

}
