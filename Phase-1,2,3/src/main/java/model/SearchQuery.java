package model;

import classes.DocumentProcessor;
import lombok.Getter;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class SearchQuery {

    private static SearchQuery instance;
    private final String REQUIRED_WORDS_REGEX = "(?<=\\s|^)([^+-]\\S+)(?=\\s|$)";
    private final String OPTIONAL_WORDS_REGEX = "(?<=\\s|^)\\+(\\S+)(?=\\s|$)";
    private final String BANNED_WORDS_REGEX = "(?<=\\s|^)-(\\S+)(?=\\s|$)";
    private final String[] requiredWords;
    private final String[] optionalWords;
    private final String[] bannedWords;
    private SearchQuery(String input) {
        this.optionalWords = getStemmedWordsFromInputByRegex(input, OPTIONAL_WORDS_REGEX);
        this.bannedWords = getStemmedWordsFromInputByRegex(input, BANNED_WORDS_REGEX);
        this.requiredWords = getStemmedWordsFromInputByRegex(input, REQUIRED_WORDS_REGEX);
    }

    public static SearchQuery getInstance(String input) {
        if (instance == null) {
            instance = new SearchQuery(input);
        }
        return instance;
    }

    private String[] getStemmedWordsFromInputByRegex(String input, String pattern) {
        Set<String> wordsSet = new HashSet<>();
        Matcher wordsMatcher = getMatcher(input, pattern);
        while (wordsMatcher.find()) {
            wordsSet.add(wordsMatcher.group(1));
        }
        return new DocumentProcessor(wordsSet).getNormalizedWords();
    }

    private Matcher getMatcher(String command, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(command);
    }
}