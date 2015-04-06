package extractors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(BlockJUnit4ClassRunner.class)
public class ExtractorImplTest {

    private Extractor extractor = new ExtractorImpl();

    @Test
    public void testGetNames() throws Exception {
        List<String> names = extractor.getNames();
        assertTrue(names.isEmpty());
    }
}
