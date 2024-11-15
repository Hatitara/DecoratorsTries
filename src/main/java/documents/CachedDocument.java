package documents;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CachedDocument implements Document {

    private Document document;

    @Override
    public String parse() throws InterruptedException {
        String cached = DBConnection.getInstance().getDocument(document.getGcsPath());
        if (cached != null) {
            return cached;
        }
        String parsedInfo = document.parse();
        DBConnection.getInstance().createDocument(document.getGcsPath(), parsedInfo);
        return parsedInfo;
    }

    @Override
    public String getGcsPath() {
        return document.getGcsPath();
    }
}
