import java.util.stream.Stream;

public class StringReducer {



    public static String reduceString(String input) {
        return Stream.iterate(input, StringReducer::reduceOnce)
                .distinct()
                .dropWhile(s -> s.length() != reduceOnce(s).length())
                .findFirst()
                .orElse(input);
    }

    private static String reduceOnce(String input) {
        StringBuilder sb = new StringBuilder(input);
        int i = 0;

        while (i < sb.length() - 2) {
            char current = sb.charAt(i);
            if (sb.charAt(i + 1) == current && sb.charAt(i + 2) == current) {
                int j = i + 2;
                while (j < sb.length() && sb.charAt(j) == current) {
                    j++;
                }
                sb.delete(i, j);
                break;
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "aabcccbbad";
        String result = reduceString(input);
        System.out.println(result);
    }
}
