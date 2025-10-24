import java.math.BigInteger;

public class Main {

    public static long powerMod(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp % 2) == 1)
                result = (result * base) % mod;
            exp >>= 1;
            base = (base * base) % mod;
        }
        return result;
    }

    public static long modInverse(long a, long mod) {
        return BigInteger.valueOf(a).modInverse(BigInteger.valueOf(mod)).longValue();
    }

    public static void main(String[] args) {

        long p = 47, q = 23, h = 25;
        long xA = 2;
        long k = 3;
        long H = 2;

        long g = powerMod(h, (p - 1) / q, p);

        long yA = powerMod(g, xA, p);

        long r = powerMod(g, k, p) % q;

        long s = (modInverse(k, q) * (H + xA * r)) % q;

        System.out.println("Cau 5: Chu ky dien tu DSA");
        System.out.println("a) Khoa cong khai cua An: yA = " + yA);
        System.out.println("b) Chu ky so (r, s) = (" + r + ", " + s + ")");

        long w = modInverse(s, q) % q;
        long u1 = (H * w) % q;
        long u2 = (r * w) % q;

        long v = ((powerMod(g, u1, p) * powerMod(yA, u2, p)) % p) % q;

        System.out.print("c) Xac minh: v = " + v);
        System.out.println(v == r ? " = r" : " != r");
        System.out.println("=> Chu ky " + (v == r ? "hop le" : "khong hop le"));
    }
}
