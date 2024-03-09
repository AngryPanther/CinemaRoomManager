import java.util.Scanner;

public class Main {
    private static String checkDivisible(int n) {
        if (n < 0) {
            return "INVALID";
        } else if (n % 7 == 0) {
                return "YES";
            } else {
                return "NO";
            }
        }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(checkDivisible(n));
    }
    }

