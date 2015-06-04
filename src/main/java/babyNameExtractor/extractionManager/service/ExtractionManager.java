package babyNameExtractor.extractionManager.service;

import babyNameExtractor.customExtractors.isimArsivi.IsimArsiviExtractorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ExtractionManager {

    private Logger LOGGER = LoggerFactory.getLogger(ExtractionManager.class);

    private IsimArsiviExtractorImpl isimArsiviExtractorImpl;

    public static void main(String[] args) throws IOException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");

        ExtractionManager extractionManager = applicationContext.getBean(ExtractionManager.class);
        extractionManager.extractNames();
    }
    public void extractNames() throws IOException {
        LOGGER.debug("IsimArsivi - Extraction begin");
        isimArsiviExtractorImpl.extractAndPersist();
        LOGGER.debug("IsimArsivi - Extraction end");
    }

    @Autowired
    public void setIsimArsiviExtractorImpl(IsimArsiviExtractorImpl isimArsiviExtractorImpl){
        this.isimArsiviExtractorImpl = isimArsiviExtractorImpl;
    }
}
