package aoc2021;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static aoc.Utils.fileToStringArray;

public class Problem16 {

    private static class BinContainer {
        int[] bin;

        int index = 0;
        private int[] get(int l) {
            index += l;
            return Arrays.copyOfRange(bin, index-l, index);
        }
    }

    private static void solve(String[] input1) {
        String hex = input1[0];
        BinContainer bin = new BinContainer();
        bin.bin = hixToBin(hex);
        Packet p = readPacket(bin);

        System.out.println(versionsTotal(p));
        System.out.println(evaluate(p));
    }

    private static int versionsTotal(Packet p) {
        int t = 0;
        t += p.v;
        if (p.oper != null && p.oper.packets != null ) {
            for (Packet sp : p.oper.packets) {
                t += versionsTotal(sp);
            }
        }
        return t;
    }

    private static long evaluate(Packet p) {
        switch (p.t) {
            case 0: return p.oper.packets.stream().map(Problem16::evaluate).reduce(0L, Long::sum);
            case 1: return p.oper.packets.stream().map(Problem16::evaluate).reduce(1L, (a, b) -> a * b);
            case 2: return p.oper.packets.stream().map(Problem16::evaluate).reduce(Long.MAX_VALUE, Math::min);
            case 3: return p.oper.packets.stream().map(Problem16::evaluate).reduce(Long.MIN_VALUE, Math::max);
            case 4: return p.lv;
            case 5: return evaluate(p.oper.packets.get(0)) > evaluate(p.oper.packets.get(1)) ? 1L : 0L;
            case 6: return evaluate(p.oper.packets.get(0)) < evaluate(p.oper.packets.get(1)) ? 1L : 0L;
            case 7: return evaluate(p.oper.packets.get(0)) == evaluate(p.oper.packets.get(1)) ? 1L : 0L;
            default: return p.lv;
        }
    }

    private static Packet readPacket(BinContainer bin) {
        int v = binToDec(bin.get(3));
        int t = binToDec(bin.get(3));

        if (t == 4) {
            long lv = readLiteralValue(bin);
            return Packet.of(v, t, lv, null);
        } else {
            int lt = binToDec(bin.get(1));
            if (lt == 0) {
                int lb = binToDec(bin.get(15));
                List<Packet> packets = readPacketsLB(bin, lb);
                return Packet.of(v, t, 0, Oper.of(lt, packets));
            } else {
                int lsp = binToDec(bin.get(11));
                List<Packet> packets = readPacketsLSP(bin, lsp);
                return Packet.of(v, t, 0, Oper.of(lt, packets));
            }
        }
    }

    private static List<Packet> readPacketsLSP(BinContainer bin, int lsp) {
        List<Packet> list = new ArrayList<>();
        for (int i = 0; i < lsp; i++) {
            list.add(readPacket(bin));
        }
        return list;
    }

    private static List<Packet> readPacketsLB(BinContainer bin, int lb) {
        List<Packet> list = new ArrayList<>();
        int i1 = bin.index;
        while (bin.index < i1 + lb) {
            list.add(readPacket(bin));
        }
        return list;
    }

    private static long readLiteralValue(BinContainer bin) {
        int[] totalBin = new int[0];
        int[] tBin = bin.get(5);
        while (tBin[0] == 1) {
            totalBin = append(totalBin, Arrays.copyOfRange(tBin, 1, 5));
            tBin = bin.get(5);
        }
        totalBin = append(totalBin, Arrays.copyOfRange(tBin, 1, 5));
        return binToDecLong(totalBin);
    }

    private static int[] append(int[] a1, int[] a2) {
        int[] a3 = new int[a1.length + a2.length];
        System.arraycopy(a1, 0, a3, 0, a1.length);
        System.arraycopy(a2, 0, a3, a1.length, a2.length);
        return a3;
    }

    private static int binToDec(int... bin) {
        int dec = 0;
        for (int i: bin) {
            dec = dec * 2 + i;
        }
        return dec;
    }

    private static long binToDecLong(int... bin) {
        long dec = 0;
        for (int i: bin) {
            dec = dec * 2 + i;
        }
        return dec;
    }

    private static int[] hixToBin(String hex) {
        int[] bin = new int[hex.length() * 4];
        for (int i = 0; i < hex.length(); i++) {
            switch (hex.charAt(i)) {
                case '0': bin[i*4] = 0; bin[i*4+1] = 0; bin[i*4+2] = 0; bin[i*4+3] = 0; break;
                case '1': bin[i*4] = 0; bin[i*4+1] = 0; bin[i*4+2] = 0; bin[i*4+3] = 1; break;
                case '2': bin[i*4] = 0; bin[i*4+1] = 0; bin[i*4+2] = 1; bin[i*4+3] = 0; break;
                case '3': bin[i*4] = 0; bin[i*4+1] = 0; bin[i*4+2] = 1; bin[i*4+3] = 1; break;
                case '4': bin[i*4] = 0; bin[i*4+1] = 1; bin[i*4+2] = 0; bin[i*4+3] = 0; break;
                case '5': bin[i*4] = 0; bin[i*4+1] = 1; bin[i*4+2] = 0; bin[i*4+3] = 1; break;
                case '6': bin[i*4] = 0; bin[i*4+1] = 1; bin[i*4+2] = 1; bin[i*4+3] = 0; break;
                case '7': bin[i*4] = 0; bin[i*4+1] = 1; bin[i*4+2] = 1; bin[i*4+3] = 1; break;
                case '8': bin[i*4] = 1; bin[i*4+1] = 0; bin[i*4+2] = 0; bin[i*4+3] = 0; break;
                case '9': bin[i*4] = 1; bin[i*4+1] = 0; bin[i*4+2] = 0; bin[i*4+3] = 1; break;
                case 'A': bin[i*4] = 1; bin[i*4+1] = 0; bin[i*4+2] = 1; bin[i*4+3] = 0; break;
                case 'B': bin[i*4] = 1; bin[i*4+1] = 0; bin[i*4+2] = 1; bin[i*4+3] = 1; break;
                case 'C': bin[i*4] = 1; bin[i*4+1] = 1; bin[i*4+2] = 0; bin[i*4+3] = 0; break;
                case 'D': bin[i*4] = 1; bin[i*4+1] = 1; bin[i*4+2] = 0; bin[i*4+3] = 1; break;
                case 'E': bin[i*4] = 1; bin[i*4+1] = 1; bin[i*4+2] = 1; bin[i*4+3] = 0; break;
                case 'F': bin[i*4] = 1; bin[i*4+1] = 1; bin[i*4+2] = 1; bin[i*4+3] = 1; break;
            }
        }
        return bin;
    }


    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem16.class, "Problem16Input1.txt");
        String[] input2 = fileToStringArray(Problem16.class, "Problem16Input2.txt");
        String[] input3 = fileToStringArray(Problem16.class, "Problem16Input3.txt");
        String[] input4 = fileToStringArray(Problem16.class, "Problem16Input4.txt");
        String[] input5 = fileToStringArray(Problem16.class, "Problem16Input5.txt");

        solve(input1);
        solve(input2);
        solve(input3);
        solve(input4);
        solve(input5);
    }

    @Value
    @AllArgsConstructor(staticName = "of")
    private static class Packet {
        int v;
        int t;
        long lv;
        Oper oper;
    }

    @Value
    @AllArgsConstructor(staticName = "of")
    private static class Oper {
        int lt;
        List<Packet> packets;
    }

}
