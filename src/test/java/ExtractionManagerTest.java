//import com.gargoylesoftware.htmlunit.BrowserVersion;
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
//import org.junit.Test;
//
//import java.util.List;
//
//public class ExtractionManagerTest {
//
//
//    @Test
//    public void homePage() throws Exception {
//        final WebClient webClient = new WebClient(BrowserVersion.CHROME);
//        webClient.getOptions().setThrowExceptionOnScriptError(false);
//        final HtmlPage page = webClient.getPage("http://www.isimarsivi.com/isimler");
//        webClient.waitForBackgroundJavaScript(5000);
//
//        List<HtmlTableRow> rows = (List<HtmlTableRow>) page.getByXPath("//table[@id='example2']/tbody/tr");
//        for(HtmlTableRow row : rows) {
//            System.out.println(((HtmlAnchor) row.getFirstByXPath("td[2]/span[2]/a")).getTextContent());
//        }
//    }
//
//}
