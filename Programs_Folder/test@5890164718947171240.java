import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class test {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n % 2 == 0) {
            System.out.print(n + " is even");
        } else {
            System.out.print(n + " is odd");
        }
    }
}