import java.util.*;

public class DES {

    private static final int[] PC1 = {
            57,49,41,33,25,17,9, 1,58,50,42,34,26,18,
            10,2,59,51,43,35,27, 19,11,3,60,52,44,36,
            63,55,47,39,31,23,15, 7,62,54,46,38,30,22,
            14,6,61,53,45,37,29, 21,13,5,28,20,12,4
    };
    private static final int[] PC2 = {
            14,17,11,24,1,5,3,28,15,6,21,10,23,19,12,4,
            26,8,16,7,27,20,13,2,41,52,31,37,47,55,30,40,
            51,45,33,48,44,49,39,56,34,53,46,42,50,36,29,32
    };
    private static final int[] shiftTable = {
            1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1
    };
    private static final int[] IP = {
            58,50,42,34,26,18,10,2,60,52,44,36,28,20,12,4,
            62,54,46,38,30,22,14,6,64,56,48,40,32,24,16,8,
            57,49,41,33,25,17,9,1,59,51,43,35,27,19,11,3,
            61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7
    };
    private static final int[] E = {
            32,1,2,3,4,5,4,5,6,7,8,9,8,9,10,11,12,13,
            12,13,14,15,16,17,16,17,18,19,20,21,20,21,22,23,24,25,
            24,25,26,27,28,29,28,29,30,31,32,1
    };
    private static final int[] P = {
            16,7,20,21,29,12,28,17,1,15,23,26,5,18,31,10,
            2,8,24,14,32,27,3,9,19,13,30,6,22,11,4,25
    };
    private static final int[] IP1 = {
            40,8,48,16,56,24,64,32,39,7,47,15,55,23,63,31,
            38,6,46,14,54,22,62,30,37,5,45,13,53,21,61,29,
            36,4,44,12,52,20,60,28,35,3,43,11,51,19,59,27,
            34,2,42,10,50,18,58,26,33,1,41,9,49,17,57,25
    };

    private static final int[][][] S = {
            { // S1
                    {14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
                    {0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
                    {4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
                    {15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}
            },
            { // S2
                    {15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},
                    {3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
                    {0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},
                    {13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}
            },
            { // S3
                    {10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},
                    {13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
                    {13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},
                    {1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}
            },
            { // S4
                    {7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},
                    {13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},
                    {10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},
                    {3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}
            },
            { // S5
                    {2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},
                    {14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},
                    {4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},
                    {11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}
            },
            { // S6
                    {12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},
                    {10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},
                    {9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},
                    {4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}
            },
            { // S7
                    {4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},
                    {13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
                    {1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},
                    {6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}
            },
            { // S8
                    {13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},
                    {1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
                    {7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},
                    {2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}
            }
    };

    private static String bitsetToString(BitSet bits, int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = n - 1; i >= 0; i--) sb.append(bits.get(i) ? '1' : '0');
        return sb.toString();
    }

    private static BitSet stringToBitset(String s) {
        BitSet b = new BitSet(s.length());
        int n = s.length();
        for (int i = 0; i < n; i++) if (s.charAt(n - 1 - i) == '1') b.set(i);
        return b;
    }

    private static String binaryToHex(String bin) {
        int pad = (4 - bin.length() % 4) % 4;
        if (pad > 0) bin = "0".repeat(pad) + bin;
        StringBuilder hex = new StringBuilder(bin.length() / 4);
        for (int i = 0; i < bin.length(); i += 4) {
            int val = Integer.parseInt(bin.substring(i, i + 4), 2);
            hex.append("0123456789ABCDEF".charAt(val));
        }
        return hex.toString();
    }

    private static String fmt(BitSet b, int n, int group) {
        String s = bitsetToString(b, n);
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            out.append(s.charAt(i));
            if ((n - 1 - i) % group == 0 && i != n - 1) out.append(' ');
        }
        return out.toString();
    }

    private static BitSet permute(int[] T, BitSet src, int inBits, int outBits) {
        BitSet dst = new BitSet(outBits);
        for (int i = 0; i < outBits; i++) {
            boolean bit = src.get(inBits - T[i]);
            if (bit) dst.set(outBits - 1 - i);
        }
        return dst;
    }

    private static BitSet shiftL(BitSet a, int shift) {
        BitSet out = new BitSet(28);
        for (int i = 0; i < 28; i++) {
            int from = (i - shift + 28) % 28;
            if (a.get(from)) out.set(i);
        }
        return out;
    }

    private static BitSet xor(BitSet A, BitSet B, int n) {
        BitSet r = (BitSet) A.clone();
        r.xor(B);
        for (int i = n; i < r.size(); i++) {
            r.clear(i);
        }
        return r;
    }


    private static BitSet sBox(BitSet in48) {
        String A = bitsetToString(in48, 48);
        StringBuilder out = new StringBuilder(32);
        for (int i = 0; i < 8; i++) {
            String blk = A.substring(i * 6, i * 6 + 6);
            int b0 = blk.charAt(0) - '0';
            int b1 = blk.charAt(1) - '0';
            int b2 = blk.charAt(2) - '0';
            int b3 = blk.charAt(3) - '0';
            int b4 = blk.charAt(4) - '0';
            int b5 = blk.charAt(5) - '0';
            int row = (b0 << 1) | b5;
            int col = (b1 << 3) | (b2 << 2) | (b3 << 1) | b4;
            int val = S[i][row][col];
            String bits4 = String.format("%4s", Integer.toBinaryString(val)).replace(' ', '0');
            out.append(bits4);
        }
        return stringToBitset(out.toString());
    }

    public static void main(String[] args) {
        
        String K_hex = "B35F59255E3BCB54";
        String M_hex = "32D604E6C4504149";

        String kbin = new java.math.BigInteger(K_hex, 16).toString(2);
        kbin = "0".repeat(64 - kbin.length()) + kbin;
        String mbin = new java.math.BigInteger(M_hex, 16).toString(2);
        mbin = "0".repeat(64 - mbin.length()) + mbin;

        BitSet K = stringToBitset(kbin);
        BitSet M = stringToBitset(mbin);

        // Câu 1
        BitSet key56 = permute(PC1, K, 64, 56);
        BitSet C0 = new BitSet(28), D0 = new BitSet(28);
        for (int i = 0; i < 28; i++) {
            if (key56.get(i)) D0.set(i);
            if (key56.get(i + 28)) C0.set(i);
        }
        System.out.println("Cau 1:");
        System.out.println("C0= " + fmt(C0, 28, 7) + "\tD0= " + fmt(D0, 28, 7));
        System.out.println();

        // Câu 2
        BitSet[] C = new BitSet[17], D = new BitSet[17];
        C[0] = C0; D[0] = D0;
        System.out.println("Cau 2:");
        for (int i = 1; i <= 16; i++) {
            C[i] = shiftL(C[i - 1], shiftTable[i - 1]);
            D[i] = shiftL(D[i - 1], shiftTable[i - 1]);
            System.out.println("C" + i + "\t=" + fmt(C[i], 28, 7) + "\tD" + i + "\t=" + fmt(D[i], 28, 7));
        }
        System.out.println();

        // Câu 3
        BitSet[] Ksub = new BitSet[17];
        System.out.println("Cau 3:");
        for (int i = 1; i <= 16; i++) {
            BitSet CD = new BitSet(56);
            for (int j = 0; j < 28; j++) {
                if (D[i].get(j)) CD.set(j);
                if (C[i].get(j)) CD.set(j + 28);
            }
            Ksub[i] = permute(PC2, CD, 56, 48);
            System.out.println("K" + i + "\t=" + fmt(Ksub[i], 48, 6));
        }
        System.out.println();

        // Câu 4
        BitSet IPM = permute(IP, M, 64, 64);
        BitSet L0 = new BitSet(32), R0 = new BitSet(32);
        for (int i = 0; i < 32; i++) {
            if (IPM.get(i)) R0.set(i);
            if (IPM.get(i + 32)) L0.set(i);
        }
        System.out.println("Cau 4:");
        System.out.println("L0= " + fmt(L0, 32, 4) + "\tR0= " + fmt(R0, 32, 4));
        System.out.println();

        // Câu 5
        System.out.println("Cau 5:");
        BitSet ER0 = permute(E, R0, 32, 48);
        System.out.println("ER0\t=" + fmt(ER0, 48, 6));
        System.out.println();

        // Câu 6
        System.out.println("Cau 6:");
        BitSet A = xor(ER0, Ksub[1], 48);
        System.out.println("A\t=" + fmt(A, 48, 6));
        System.out.println();

        // Câu 7
        System.out.println("Cau 7:");
        BitSet SA = sBox(A);
        System.out.println(fmt(SA, 32, 4));
        System.out.println();

        // Câu 8
        System.out.println("Cau 8:");
        BitSet F = permute(P, SA, 32, 32);
        System.out.println(fmt(F, 32, 4));
        System.out.println();

        // Câu 9
        System.out.println("Cau 9:");
        BitSet L1 = (BitSet) R0.clone();
        BitSet R1 = xor(L0, F, 32);
        System.out.println("L1=" + fmt(L1, 32, 4) + "\tR1=" + fmt(R1, 32, 4));
        System.out.println();

        // Câu 10
        System.out.println("Cau 10:");
        BitSet[] L = new BitSet[17], R = new BitSet[17];
        L[1] = L1; R[1] = R1;
        for (int i = 2; i <= 16; i++) {
            L[i] = (BitSet) R[i - 1].clone();
            BitSet e = permute(E, R[i - 1], 32, 48);
            BitSet x = xor(e, Ksub[i], 48);
            BitSet sOut = sBox(x);
            BitSet f = permute(P, sOut, 32, 32);
            R[i] = xor(L[i - 1], f, 32);
            System.out.println("L" + i + "\t=" + fmt(L[i], 32, 4) + "\tR" + i + "\t=" + fmt(R[i], 32, 4));
        }
        System.out.println();

        // Câu 11
        System.out.println("Cau 11:");
        String RL = bitsetToString(R[16], 32) + bitsetToString(L[16], 32);
        BitSet preOut = stringToBitset(RL);
        BitSet Ccipher = permute(IP1, preOut, 64, 64);
        String C_hex = binaryToHex(bitsetToString(Ccipher, 64));
        System.out.println("C=" + C_hex);
    }
}
