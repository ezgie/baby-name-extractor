import extractors.Extractor;
import extractors.ExtractorImpl;

public class ExtractionManager {

    public static void main(String[] args) {
        Extractor extractor = new ExtractorImpl();
        System.out.println(extractor.getPageTitle());
    }

}
