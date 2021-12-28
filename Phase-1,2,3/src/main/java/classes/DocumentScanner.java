package classes;

import exception.BaseDirectoryInvalidException;
import model.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class DocumentScanner implements DocumentScanning {

    private static DocumentScanner instance;
    private final Path baseDirectory;
    private final InvertedIndex index;

    private DocumentScanner(Path baseDirectory) throws BaseDirectoryInvalidException, IOException {
        this.baseDirectory = baseDirectory;
        if (!isPathDirectory(baseDirectory)) throw new BaseDirectoryInvalidException();
        index = InvertedIndex.getInstance();
        scanAndIndex();
    }

    public static DocumentScanner getInstance(Path baseDirectory) throws BaseDirectoryInvalidException, IOException {
        if (instance == null) {
            instance = new DocumentScanner(baseDirectory);
        }
        return instance;
    }


    public Set<Path> getAllFilesInPath() throws IOException {
        return Files.walk(baseDirectory).filter(Files::isRegularFile).collect(Collectors.toSet());
    }

    public String getPathData(Path documentPath) throws IOException {
        return Files.readString(documentPath);
    }

    public void indexDocumentWords(Path documentPath, String[] words) {
        Document document = new Document(documentPath);
        for (String word : words) {
            index.addWord(document, word);
        }
    }

    public InvertedIndex getIndex() {
        return index;
    }

    private String[] getProcessedDocumentPathData(Path documentPath) throws IOException {
        String data = getPathData(documentPath);
        return processDocumentData(data);
    }

    private void scanAndIndex() throws IOException {
        Set<Path> documentPaths = getAllFilesInPath();
        for (Path documentPath : documentPaths) {
            String[] words = getProcessedDocumentPathData(documentPath);
            indexDocumentWords(documentPath, words);
        }
    }

    private String[] processDocumentData(String data) {
        return new DocumentProcessor(data).getNormalizedWords();
    }

    private boolean isPathDirectory(Path baseDirectory) {
        return Files.isDirectory(baseDirectory);
    }
}