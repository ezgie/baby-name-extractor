package extractors;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ExtractorImpl implements Extractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtractorImpl.class);

    private WebClient webClient;

    @Override
    public List<String> getNames() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String getPageTitle() {
        String pageTitle = "";
        try {
            pageTitle = getHtmlPage().getTitleText();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return pageTitle;
    }

    private HtmlPage getHtmlPage() throws IOException {
        final WebClient webClient = getWebClient();
        return webClient.getPage(getUrl());
    }

    private WebClient getWebClient() {
        if(webClient == null) {
            webClient = new WebClient();
        }

        return webClient;
    }
    private String getUrl(){
        return "http://www.w3schools.com/";
    }
}
