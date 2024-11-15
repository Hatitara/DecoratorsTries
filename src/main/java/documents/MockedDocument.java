package documents;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MockedDocument implements Document {

    String gcsPath;

    @Override
    public String parse() throws InterruptedException {
        Thread.sleep(2000);
        return "Mocked doc parse was called";
    }

    @Override
    public String getGcsPath() {
        return gcsPath;
    }
}
