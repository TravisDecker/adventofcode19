package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class OpCodeComputer {

    /**
     * Using the "Pattern" gives us access to "splitAsStream", see the Parse Method.
     * The Pattern "//s*,//s*" matches any number of white space, followed by a comma fallowed by any number of white space.
     */
    private static final Pattern DELIMITER = Pattern.compile("\\s*,\\s*");
    private static final Path path = Path.of("src/day2/opcode.txt");


    public static void main(String[] args) throws IOException {
        int[] code = parse(path);
        run(code);
    }

    public static int[] parse(Path path) throws IOException {
        try (Stream<String> stream = Files.lines(path)) {
            return stream
                    .flatMap(s -> DELIMITER.splitAsStream(s))
                    .mapToInt(Integer::parseInt)
                    .toArray();

        }
    }

    public static void run(int[] code) {
        boolean halt = false;
        for (int i = 0; i < code.length; i += 4) {
            if (!halt) {
                switch (code[i]) {
                    case 1:
                        opAdd(code, i);
                        break;
                    case 2:
                        opMult(code, i);
                        break;
                    case 99:
                        halt = true;
                        break;
                }
            } else break;

        }
        System.out.println(code[0]);
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
