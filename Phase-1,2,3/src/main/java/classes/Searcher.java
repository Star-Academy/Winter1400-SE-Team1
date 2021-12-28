package classes;

import exception.SearchException;
import model.Document;
import model.SearchQuery;

import java.util.Set;

public interface Searcher {
    Set<Document> search(SearchQuery query) throws SearchException;
}
