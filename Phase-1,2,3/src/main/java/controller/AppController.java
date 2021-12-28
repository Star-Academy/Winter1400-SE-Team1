package controller;

import classes.DocumentScanner;
import classes.SearchEngine;
import exception.BaseDirectoryInvalidException;
import exception.SearchException;
import model.Document;
import model.SearchQuery;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

public class AppController {

    final String BASE_DIRECTORY_URL = "./src/SampleEnglishData";

    private SearchEngine engine;

    public void init() throws BaseDirectoryInvalidException, IOException {
        Path baseDirectory = Path.of(BASE_DIRECTORY_URL);
        DocumentScanner documentScanner = DocumentScanner.getInstance(baseDirectory);
        engine = SearchEngine.getInstance(documentScanner.getIndex());
    }

    public Set<Document> search(String input) throws SearchException {
        if (engine == null) {
            throw new SearchException("indexes not initialized");
        }
        return getInputResults(input);
    }

    private Set<Document> getInputResults(String input) throws SearchException {
        SearchQuery query = SearchQuery.getInstance(input);
        return engine.search(query);
    }

    public void printResults(Set<Document> results) {
        for (Document result : results) {
            System.out.println(result.getDocumentPath().getFileName());
        }
    }

}