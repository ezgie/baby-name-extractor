package babyNameExtractor.customExtractors.common.service;

import java.io.IOException;

public interface Extractor {

    public void extractAndPersist() throws IOException;
}
