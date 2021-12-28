package classes;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

public interface DocumentScanning {

    public void indexDocumentWords(Path documentPath, String[] words);

}
