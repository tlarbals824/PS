import java.util.*;
import java.io.*;
import java.time.*;
import java.time.temporal.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String[] input = br.readLine().split(" ");

            String fullDateTime = input[0];
            String monthAndDay = input[1];
            var result = calculateRange(fullDateTime, monthAndDay);
            if (result.diff == 0) {
                bw.write("SAME DAY\n");
            } else if (Math.abs(result.diff) > 7) {
                bw.write("OUT OF RANGE\n");
            } else {
                bw.write(result.time + " IS " + Math.abs(result.diff) + " DAY" + (Math.abs(result.diff) > 1 ? "S " : " ")+ (result.diff > 0 ? "AFTER" : "PRIOR") + "\n");
            }
        }
        bw.flush();
    }

    private static Result calculateRange(String fullDateTime, String monthAndDay) {
        int[] firstDate = Arrays.stream(fullDateTime.split("/")).mapToInt(Integer::parseInt).toArray();
        int[] secondDate = Arrays.stream(monthAndDay.split("/")).mapToInt(Integer::parseInt).toArray();

        LocalDate first = LocalDate.of(firstDate[2], firstDate[0], firstDate[1]);
        int year = firstDate[2];
        if (Math.abs(firstDate[0] - secondDate[0]) == 11) {
            year += (firstDate[0] == 12) ? 1 : -1;
        }
        LocalDate second = LocalDate.of(year, secondDate[0], secondDate[1]);
        return new Result(monthAndDay + "/" + year, ChronoUnit.DAYS.between(first, second));
    }

    private static class Result {
        String time;
        long diff;

        public Result(String time, long diff) {
            this.time = time;
            this.diff = diff;
        }
    }

}