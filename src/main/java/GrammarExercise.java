import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = "";
        String secondWordList = "";

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        Pattern pattern = Pattern.compile("[^a-zA-Z,]");
        Matcher firstMatcher = pattern.matcher(firstWordList);
        Matcher secondMatcher = pattern.matcher(secondWordList);
        if (firstMatcher.find() || secondMatcher.find()) {
            throw new IllegalArgumentException("input not valid");
        }

        List<String> firstStringWords = new ArrayList<>(Arrays.asList(firstWordList.split(",")));
        List<String> secondStringWords = new ArrayList<>(Arrays.asList(secondWordList.split(",")));

        if (firstStringWords.contains("") || secondStringWords.contains("")) {
            throw new IllegalArgumentException("input not valid");
        }

        List<String> upperSecondStringWords = secondStringWords.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        return firstStringWords.stream()
                .map(String::toUpperCase)
                .filter(upperSecondStringWords::contains)
                .distinct()
                .sorted()
                .map(word -> word.replace("", " ").trim())
                .collect(Collectors.toList());
    }
}
