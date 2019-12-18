package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class OpCodeComputer {

    /**
     * Using the "Pattern" gives us access to "splitAsStream", see the Parse Method.
     * The Pattern "//s*,//s*" matches any number of white space, followed by a comma fallowed by any number of white space.
     */
    private static final Pattern DELIMITER = Pattern.compile("\\s*,\\s*");
    private static final Path path = Path.of("src/day2/day2.txt");
    public static final int TARGET = 19690720;
    public static final int BOUND = 100;
    public static final int verb = 0;
    public static final int noun = 0;


    public static void main(String[] args) throws IOException {
        int[] code = parse(path);
        System.out.println(run(code));
        System.out.println(search(code, BOUND, TARGET));
    }

    public static int[] parse(Path path) throws IOException {
        try (Stream<String> stream = Files.lines(path)) {
            return stream
                    .flatMap(s -> DELIMITER.splitAsStream(s))
                    .mapToInt(Integer::parseInt)
                    .toArray();

        }
    }


    public static int search(int[] code, int bound, int target) {
        boolean found = false;
        int[] working = null;
        int itCOunt = 0;
        while (!found) {
            for (int noun = 0; noun < bound; noun++) {
                for (int verb = 0; verb < bound; verb++) {
                    working = Arrays.copyOf(code, code.length);
                    working[1] = noun;
                    working[2] = verb;
                    int out = run(working);
                    if (out == target) {
                        found = true;
                        System.out.println("Noun = " + noun);
                        System.out.println("Verb = " + verb);
                        System.out.println("Output = " + out);
                        int answer = (100 * noun) + verb;
                        System.out.println("Answer = " + answer);
                    }

                }
            }
        }
        return working[0];
    }

    public static int run(int[] code) {
        int[] working = Arrays.copyOf(code, code.length);
        boolean halt = false;
        for (int i = 0; i < working.length; i += 4) {
            if (!halt) {
                switch (working[i]) {
                    case 1:
                        opAdd(working, i);
                        break;
                    case 2:
                        opMult(working, i);
                        break;
                    case 99:
                        halt = true;
                        break;
                    default:
                        System.out.println("ERROR");
                        halt = true;
                }
            } else break;

        }
        return working[0];
    }

    private static void opMult(int[] code, int i) {
        int x = code[code[i + 1]];
        int y = code[code[i + 2]];
        code[code[i + 3]] = x * y;
    }

    private static void opAdd(int[] code, int i) {
        int x = code[code[i + 1]];
        int y = code[code[i + 2]];
        code[code[i + 3]] = x + y;
    }

}
