import model.WebPage;
import model.WebPages;
import service.GetInformationFromUrl;
import service.WebPageToXml;

public class MainApp {

	public static void main(String[] args) {
		GetInformationFromUrl info1 = new GetInformationFromUrl("https://www.amazon.com/LG-gram-Thin-Light-Laptop/dp/B078WRSHV4/ref=sr_1_82_sspa");
		WebPage page1 = new WebPage(info1.getUrl(), info1.getArticleId(), info1.getName(), info1.getPrice(), info1.getDescription(), info1.getAvailability());
		
		GetInformationFromUrl info2 = new GetInformationFromUrl("https://www.amazon.com/MSI-Stealth-093-Gaming-i7-8750H-Diamond/dp/B07MQBN2X1");
		WebPage page2 = new WebPage(info2.getUrl(), info2.getArticleId(), info2.getName(), info2.getPrice(), info2.getDescription(), info2.getAvailability());
		
		WebPages pages = new WebPages();
		pages.webpages.add(page1);
		pages.webpages.add(page2);
		WebPageToXml.writeToXml(pages);
	}
	
}
