package aoc2021;

import static aoc.Utils.fileToStringArray;

public class Problem20 {

    private static void solve(String[] input, int steps) {
        String[] algo = input[0].split("");

        String[][] image = new String[input.length-2][];
        for (int i = 2; i < input.length; i++) {
            image[i-2] = input[i].split("");
        }

        String[][] image2 = expand(image);
        for (int k = 0; k < steps ; k++) {
            print(image2);
            String[][] image3 = new String[image2.length][image2[0].length];
            for (int i = 0; i < image2.length; i++) {
                for (int j = 0; j < image2[i].length; j++) {
                    int b = getBit(image2, i, j);
                    image3[i][j] = algo[b];
                }
            }
            image2 = image3;
        }
        print(image2);
        System.out.println(countLit(image2));
    }

    private static String[][] expand(String[][] image) {
        String[][] image2 = new String[image.length+200][image[0].length+200];
        for (int i = 0; i < image2[0].length; i++) {
            for (int j = 0; j < 100; j++) {
                image2[j][i] = ".";
            }
        }
        for (int i = 0; i < image2[0].length; i++) {
            for (int j = 0; j < 100; j++) {
                image2[image2.length-j-1][i] = ".";
            }
        }
        for (int i = 0; i < image2.length; i++) {
            for (int j = 0; j < 100; j++) {
                image2[i][j] = ".";
            }
        }
        for (int i = 0; i < image2[0].length; i++) {
            for (int j = 0; j < 100; j++) {
                image2[i][image2.length-j-1] = ".";
            }
        }
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                image2[i+100][j+100] = image[i][j];
            }
        }
        return image2;
    }

    private static void print(String[][] img) {
        System.out.println("Printing....");
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {
                System.out.print(img[i][j]);
            }
            System.out.println();
        }
    }

    private static int countLit(String[][] img) {
        int c = 0;
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {
                if (j > 49 && i > 49 && img[i][j].equals("#")) c++;
            }
        }
        return c;
    }

    private static int getBit(String[][] image, int i, int j) {
        int[] bits = new int[9];
        bits[0] = i==0 || j==0 ? 0 : bit(image[i-1][j-1]);
        bits[1] = i==0 ? 0 : bit(image[i-1][j]);
        bits[2] = i==0 || j==image.length-1 ? 0 : bit(image[i-1][j+1]);
        bits[3] = j==0 ? 0 : bit(image[i][j-1]);
        bits[4] = bit(image[i][j]);
        bits[5] = j==image.length-1 ? 0 : bit(image[i][j+1]);
        bits[6] = i==image.length-1 || j==0 ? 0 : bit(image[i+1][j-1]);
        bits[7] = i==image.length-1 ? 0 : bit(image[i+1][j]);
        bits[8] = i==image.length-1 || j==image.length-1 ? 0 : bit(image[i+1][j+1]);

        return binToDec(bits);
    }

    private static int binToDec(int[] bits) {
        int dec = 0;
        for (int b: bits) {
            dec = dec * 2 + b;
        }
        return dec;
    }

    private static int bit(String s) {
        return s.equals("#") ? 1 : 0;
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem20.class, "Problem20Input1.txt");
        String[] input2 = fileToStringArray(Problem20.class, "Problem20Input2.txt");

        solve(input1, 2);
        solve(input2, 2);

        solve(input1, 50);
        solve(input2, 50);
    }
}
