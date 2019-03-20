import bot.BotPromUa;
import model.BotUser;
import model.WebPage;
import model.WebPages;
import service.GetInformationFromUrl;
import service.WebPageToXml;

public class MainApp {

	public static void main(String[] args) {
		GetInformationFromUrl info1 = new GetInformationFromUrl("https://prom.ua/p895411456-igrovoj-noutbu-omen.html");
		WebPage page1 = new WebPage(info1.getUrl(), info1.getArticleId(), info1.getName(), info1.getPrice(), info1.getSpecifications(), info1.getAvailability(), info1.getDescription(), info1.getImgUrl());
		 
		GetInformationFromUrl info2 = new GetInformationFromUrl("https://prom.ua/p920308269-noutbuk-gaming-cx0056wm.html");
		WebPage page2 = new WebPage();
		page2.setUrl(info2.getUrl());
		page2.setArticleId(info2.getArticleId());
		page2.setName(info2.getName());
		page2.setPrice(info2.getPrice());
		page2.setSpecifications(info2.getSpecifications());
		page2.setAvailability(info2.getAvailability());
		page2.setDescription(info2.getDescription());
		page2.setImgUrl(info2.getImgUrl());
		
		WebPages pages = new WebPages();
		pages.webpages.add(page1);
		pages.webpages.add(page2);
		WebPageToXml.writeToXml(pages);
		
		BotUser user = new BotUser("John2996666", "jhon3234889@fastmail.com", "QwErTy123456");
		BotPromUa botAmazon = new BotPromUa(user);
		botAmazon.getRegistration();
	}
}
