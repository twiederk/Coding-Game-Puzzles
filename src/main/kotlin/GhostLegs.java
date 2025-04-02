import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class GhostLegs {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt();
        int H = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<String> input = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            input.add(in.nextLine());
        }

        List<String> answer = solve(W, H, input);


        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        answer.forEach(System.out::println);
    }

    private static List<String> solve(int w, int h, List<String> input) {
        List<String> result = new ArrayList<>();
        for (String line : input) {
            result.add(line.replace("|--|", "+  -").replace("  ", ""));
        }
        return result;
    }

    private void walker(List<String> karte) {
        for(int spalte=0; spalte<karte.get(0).length(); spalte++) {
            String bezeichner="";

            if(spalte==0) {
                bezeichner=karte.get(spalte);
            } else {
                karte.get(spalte).charAt(0);
            }


        }

    }


}


