package babyNameExtractor.customExtractors.isimArsivi;

import org.junit.Test;
import babyNameExtractor.persister.model.FirstName;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class IsimArsiviExtractorImplTest {

    private String FIRST_NAME_0 = "Ecrin";
    private IsimArsiviExtractorImpl isimArsiviExtractor = new IsimArsiviExtractorImpl();

    @Test
    public void testParseJson() throws Exception {
        URL url  = getClass().getClassLoader().getResource("isimarsivi/response.json");
        String json = new String(Files.readAllBytes(Paths.get(url.toURI())));
        List<FirstName> firstNames = isimArsiviExtractor.parseJson(json);
        assertNotNull(firstNames);
        assertEquals(25, firstNames.size());
    }

    @Test
    public void testParseJsonNoContent() throws Exception {
        URL url  = getClass().getClassLoader().getResource("isimarsivi/responseNoContent.json");
        String json = new String(Files.readAllBytes(Paths.get(url.toURI())));
        List<FirstName> firstNames = isimArsiviExtractor.parseJson(json);
        assertNotNull(firstNames);
        assertTrue(firstNames.isEmpty());
    }

    @Test
    public void testExtractPage() throws IOException {
        List<FirstName> firstNames = isimArsiviExtractor.extractPage(1);
        assertNotNull(firstNames);
        assertEquals(25, firstNames.size());
        assertEquals(FIRST_NAME_0, firstNames.get(0).getFirstName());
    }
}