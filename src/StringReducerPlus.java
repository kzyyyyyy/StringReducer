import java.util.stream.Stream;

public class StringReducerPlus {

    // 主函数，用于处理字符串并打印每一步的操作
    public static String reduceString(String input) {
        return Stream.iterate(input, StringReducerPlus::reduceOnce)
                .distinct()
                .dropWhile(s -> s.length() != reduceOnce(s).length())
                .peek(System.out::println) // 打印每次替换后的字符串
                .findFirst()
                .orElse(input);
    }

    // 处理每一次字符串的缩减并打印替换信息
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
                // 将连续相同的字符替换为前一个字母
                char replacement = (char) (current - 1);
                String replacedPart = sb.substring(i, j);
                sb.replace(i, j, String.valueOf(replacement));

                // 打印替换前后的信息
                System.out.println("-> " + sb.toString() + ", " + replacedPart + " is replaced by " + replacement);

                break;  // 处理一次后退出循环，维持单步替换
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    // 测试代码
    public static void main(String[] args) {
        String input = "abcccbad";
        String result = reduceString(input);
        System.out.println("Final result: " + result); // 输出最终结果
    }
}
