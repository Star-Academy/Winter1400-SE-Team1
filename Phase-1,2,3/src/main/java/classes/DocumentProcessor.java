package classes;

public class DocumentProcessor {

    private final PorterStemmer stemmer;
    {
        stemmer = PorterStemmer.getInstance();
    }

    private String data;

    public DocumentProcessor(String data) {
        this.data = data;
    }

    public DocumentProcessor(Iterable<? extends CharSequence> data) {
        if (data.iterator().hasNext()) {
            this.data = String.join(" ", data);
        }
    }

    public String[] getNormalizedWords() {
        toLowerCase();
        removeSigns();
        return toStemmedSplit();
    }

    private void toLowerCase() {
        if (data == null) return;
        data = data.toLowerCase();
    }

    private void removeSigns() {
        if (data == null) return;
        data = data.replaceAll("[-?]+", " ").replaceAll("[^a-zA-Z0-9\\s]+", "");
    }

    private String[] toStemmedSplit() {
        if (data == null) return new String[0];
        String[] dataSplit = data.split("\\s+");
        for (int i = 0; i < dataSplit.length; i++) {
            dataSplit[i] = stemmer.stemWord(dataSplit[i]);
        }
        return dataSplit;
    }

}