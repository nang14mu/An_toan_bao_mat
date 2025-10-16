import java.util.BitSet;

public class DES {

    private static int[] PC1 = {
            57, 49, 41, 33, 25, 17, 9,
            1,  58, 50, 42, 34, 26, 18,
            10, 2,  59, 51, 43, 35, 27,
            19, 11, 3,  60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7,  62, 54, 46, 38, 30, 22,
            14, 6,  61, 53, 45, 37, 29,
            21, 13, 5,  28, 20, 12, 4
    };

    private static int[] shiftTable = {
            1, 1, 2, 2, 2, 2, 2, 2,
            1, 2, 2, 2, 2, 2, 2, 1
    };

    private static int[] PC2 = {
            14, 17, 11, 24, 1, 5, 3, 28,
            15, 6, 21, 10, 23, 19, 12,
            4, 26, 8, 16, 7, 27, 20, 13,
            2, 41, 52, 31, 37, 47, 55, 30,
            40, 51, 45, 33, 48, 44, 49, 39,
            56, 34, 53, 46, 42, 50, 36, 29, 32
    };

    private static int[] IP = {
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    };

    private static int[] E = {
            32 , 1 , 2 , 3 , 4 , 5, 4 , 5 ,
            6 , 7 , 8 , 9, 8 , 9 , 10 , 11 ,
            12 , 13, 12 , 13 , 14 , 15 , 16 , 17,
            16 , 17 , 18 , 19 , 20 , 21, 20 , 21 ,
            22 , 23 , 24 , 25, 24 , 25 , 26 , 27 ,
            28 , 29, 28 , 29 , 30 , 31 , 32 , 1
    };

    private static int[] P = {
            16, 7, 20, 21, 29, 12, 28,
            17, 1, 15, 23, 26, 5, 18, 31,
            10, 2, 8, 24, 14, 32, 27, 3, 9,
            19, 13, 30, 6, 22, 11, 4, 25
    };

    private static int[] IP1 = {
            40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41, 9, 49, 17, 57, 25
    };

    private static int[][][] S = {
            {
                    {14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
                    {0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
                    {4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
                    {15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}
            },
            {
                    {15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},
                    {3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
                    {0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},
                    {13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}
            },
            {
                    {10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},
                    {13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
                    {13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},
                    {1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}
            },
            {
                    {7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},
                    {13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},
                    {10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},
                    {3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}
            },
            {
                    {2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},
                    {14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},
                    {4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},
                    {11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}
            },
            {
                    {12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},
                    {10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},
                    {9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},
                    {4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}
            },
            {
                    {4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},
                    {13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
                    {1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},
                    {6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}
            },
            {
                    {13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},
                    {1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
                    {7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},
                    {2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}
            }
    };

    public static String bitsetToString(BitSet bits, int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = n - 1; i >= 0; i--) {
            sb.append(bits.get(i) ? '1' : '0');
        }
        return sb.toString();
    }

    public static BitSet stringToBitset(String s) {
        BitSet bs = new BitSet(s.length());
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(s.length() - 1 - i) == '1') bs.set(i);  // FIX: thêm bs.set(i)
        }
        return bs;
    }

    public static String binaryToHex(String bin) {
        int len = bin.length();
        int pad = (4 - len % 4) % 4;
        bin = "0".repeat(pad) + bin;
        StringBuilder hex = new StringBuilder();
        for(int i = 0; i < bin.length(); i+= 4) {
            int val = Integer.parseInt(bin.substring(i, i + 4), 2);
            hex.append("0123456789ABCDEF".charAt(val));
        }
        return hex.toString();
    }

    public static String fmt(BitSet b, int n, int group) {
        String s = bitsetToString(b, n);
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            out.append(s.charAt(i));
            int fromLeft = i + 1;
            if (fromLeft % group == 0 && fromLeft != s.length()) out.append(' ');
        }
        return out.toString();
    }

    public static BitSet leftShift(BitSet a, int k, int size) {
        BitSet r = new BitSet(size);
        for (int i = 0; i < size; i++) if (a.get((i + k) % size)) r.set(i);
        return r;
    }

    public static BitSet permute(int[] table, BitSet input, int n) {
        BitSet result = new BitSet(table.length);
        for(int i = 0; i < table.length; i++) {
            if(input.get(n - table[i])) result.set(table.length - 1 - i);
        }
        return result;
    }

    public static String xor(String a, String b) {
        StringBuilder sb = new StringBuilder(a.length());
        for(int i = 0; i < a.length(); i++)
            sb.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        return sb.toString();
    }

    public static BitSet xor(BitSet a, BitSet b, int n) {
        BitSet r = new BitSet(n);
        for(int i = 0; i < n; i++)
            if(a.get(i) ^ b.get(i)) r.set(i);
        return r;
    }

    static String sBox(String in48) {
        StringBuilder out = new StringBuilder(32);
        for (int i = 0; i < 8; i++) {
            String block = in48.substring(i * 6, (i + 1) * 6);
            BitSet b = stringToBitset(block);

            int row = ((b.get(5) ? 1 : 0) << 1) | (b.get(0) ? 1 : 0);
            int col = ((b.get(4) ? 1 : 0) << 3) | ((b.get(3) ? 1 : 0) << 2)
                    | ((b.get(2) ? 1 : 0) << 1) | (b.get(1) ? 1 : 0);

            int val = S[i][row][col];
            out.append(String.format("%4s", Integer.toBinaryString(val)).replace(' ', '0'));
        }
        return out.toString();
    }

    static String group(String bin, int grp) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < bin.length(); i++) {
            out.append(bin.charAt(i));
            int fromLeft = i + 1;
            if (fromLeft % grp == 0 && fromLeft != bin.length()) out.append(' ');
        }
        return out.toString();
    }

    public static void main(String[] args) {  // FIX: thay static void main thành public static void main
        String K_hex = "B35F59255E3BCB54";
        String M_hex = "32D604E6C4504149";
        long k64 = Long.parseUnsignedLong(K_hex, 16);
        long m64 = Long.parseUnsignedLong(M_hex, 16);
        BitSet K = BitSet.valueOf(new long[]{k64});
        BitSet M = BitSet.valueOf(new long[]{m64});

        BitSet key56 = permute(PC1, K, 64);
        BitSet C0 = key56.get(28, 56);
        BitSet D0 = key56.get(0, 28);
        System.out.println("Cau 1:");
        System.out.println("C0= " + fmt(C0, 28, 7) + "\tD0= " + fmt(D0, 28, 7));
        System.out.println();

        BitSet[] C = new BitSet[17];
        BitSet[] D = new BitSet[17];
        C[0] = C0; D[0] = D0;
        System.out.println("Cau 2:");
        for (int i = 1; i <= 16; i++) {
            C[i] = leftShift(C[i - 1], shiftTable[i - 1], 28);
            D[i] = leftShift(D[i - 1], shiftTable[i - 1], 28);
            System.out.println("C" + i + "\t=" + fmt(C[i], 28, 7) +
                    "\tD" + i + "\t=" + fmt(D[i], 28, 7));
        }
        System.out.println();

        System.out.println("Cau 3:");
        BitSet[] Ksub = new BitSet[17];
        for (int i = 1; i <= 16; i++) {
            BitSet comb = new BitSet(56);
            for (int j = 0; j < 28; j++) {
                if (D[i].get(j)) comb.set(j);
                if (C[i].get(j)) comb.set(j + 28);
            }
            Ksub[i] = permute(PC2, comb, 56);
            System.out.println("K" + i + "\t=" + fmt(Ksub[i], 48, 6));
        }
        System.out.println();

        BitSet afterIP = permute(IP, M, 64);
        BitSet L0 = afterIP.get(32, 64);
        BitSet R0 = afterIP.get(0, 32);
        System.out.println("Cau 4:");
        System.out.println("L0= " + fmt(L0, 32, 4) + "\tR0= " + fmt(R0, 32, 4));
        System.out.println();

        BitSet ER0 = permute(E, R0, 32);
        System.out.println("Cau 5:");
        System.out.println("ER0\t=" + fmt(ER0, 48, 6));
        System.out.println();

        String A = xor(bitsetToString(ER0, 48), bitsetToString(Ksub[1], 48));
        System.out.println("Cau 6:");
        System.out.println("A\t=" + group(A, 6));
        System.out.println();

        String SA_str = sBox(A);
        BitSet SA = stringToBitset(SA_str);
        System.out.println("Cau 7:");
        System.out.println(fmt(SA, 32, 4));
        System.out.println();

        BitSet F = permute(P, SA, 32);
        System.out.println("Cau 8:");
        System.out.println(fmt(F, 32, 4));
        System.out.println();

        BitSet L1 = R0;
        BitSet R1 = xor(L0, F, 32);
        System.out.println("Cau 9:");
        System.out.println("L1=" + fmt(L1, 32, 4) + "\tR1=" + fmt(R1, 32, 4));
        System.out.println();

        BitSet[] L = new BitSet[17];
        BitSet[] R = new BitSet[17];
        L[1] = L1; R[1] = R1;
        System.out.println("Cau 10:");
        for (int i = 2; i <= 16; i++) {
            L[i] = R[i - 1];
            BitSet ER = permute(E, R[i - 1], 32);
            String x = xor(bitsetToString(ER, 48), bitsetToString(Ksub[i], 48));
            BitSet sOut = stringToBitset(sBox(x));
            BitSet f = permute(P, sOut, 32);
            R[i] = xor(L[i - 1], f, 32);
            System.out.println("L" + i + "\t=" + fmt(L[i], 32, 4) +
                    "\tR" + i + "\t=" + fmt(R[i], 32, 4));
        }
        System.out.println();

        String RL = bitsetToString(R[16], 32) + bitsetToString(L[16], 32);
        BitSet preOut = stringToBitset(RL);
        BitSet Ccipher = permute(IP1, preOut, 64);
        String C_hex = binaryToHex(bitsetToString(Ccipher, 64));
        System.out.println("Cau 11:");
        System.out.println("C=" + C_hex);
    }
}