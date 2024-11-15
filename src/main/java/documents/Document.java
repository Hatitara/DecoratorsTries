package documents;

public interface Document {
    String parse() throws InterruptedException;
    String getGcsPath();
}
