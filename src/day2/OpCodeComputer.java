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
    }

    public static int[] parse(Path path) throws IOException {
        try (Stream<String> stream = Files.lines(path)) {
            return stream
                    .flatMap(s -> DELIMITER.splitAsStream(s))
                    .mapToInt(Integer::parseInt)
                    .toArray();

        }
    }

}
