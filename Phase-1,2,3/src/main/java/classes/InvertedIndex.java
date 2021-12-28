package classes;

import model.Document;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class InvertedIndex {

    private static InvertedIndex instance;

    private final HashMap<String, HashSet<Document>> wordIndexes;

    private InvertedIndex() {
        wordIndexes = new HashMap<>();
    }
    public static InvertedIndex getInstance() {
        if (instance == null) {
            instance = new InvertedIndex();
        }
        return instance;
    }


    public void addWord(Document document, String word) {
        wordIndexes.computeIfAbsent(word, wordIndex -> new HashSet<>()).add(document);
    }

    public Set<Document> getWordIndexes(String word) {
        return wordIndexes.get(word);
    }
}