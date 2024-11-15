package documents;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class TestDocuments {

    private static final String TEST_DB_PATH = "src/test/resources/test_cache.db";

    @Before
    public void setUp() throws Exception {
        DBConnection.getInstance();
    }

    @Test
    public void testMockedDocument() throws InterruptedException {
        Document document = new MockedDocument("");
        assertEquals("Mocked doc parse was called", document.parse());
    }

    @Test
    public void testCachedDocument() throws InterruptedException {
        Document document = new MockedDocument("test_path");
        CachedDocument cachedDocument = new CachedDocument(document);
        assertEquals("Mocked doc parse was called", cachedDocument.parse());
        assertEquals("Mocked doc parse was called", cachedDocument.parse());
    }

    @Test
    public void testTimedDocument() throws InterruptedException {
        Document document = new MockedDocument("");
        TimedDocument timedDocument = new TimedDocument(document);
        assertEquals("Mocked doc parse was called", timedDocument.parse());
    }

    @After
    public void tearDown() throws Exception {
        Thread.sleep(500);
        Files.deleteIfExists(Path.of(TEST_DB_PATH));
    }
}
