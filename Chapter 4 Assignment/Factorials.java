public class Factorials {
    public static void main(String[] args) {

        long factorial = 1;

        System.out.printf("%-5s%-20s%n", "n", "n!");

        for (int n = 1; n <= 20; n++) {
            factorial *= n;
            System.out.printf("%-5d%-20d%n", n, factorial);
        }
    }
}

//Why can't we easily calculate 100! using long?

// A long can store values only up to:

// 9,223,372,036,854,775,807

//(about 9.22×10
//18
//)

//But:

//100! ≈ 9.33 × 10^157

//which is far larger than the maximum value of long, causing overflow.

//For 100!, use:

// import java.math.BigInteger;