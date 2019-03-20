package bot;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import exception.NotSupportOSException;
import model.BotUser;

public class BotPromUa {
	
	private Logger logger = Logger.getLogger(BotPromUa.class.getName()); 
	
	private String path;
	private BotUser user;
	private WebDriver driver;
	
	public BotPromUa(BotUser user) {
		this.user = user;
	}
	
	private void init() {
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--headless");
		try {
			path = getPath();
		} catch (NotSupportOSException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
		System.setProperty("webdriver.chrome.driver", path);
		//driver = new ChromeDriver(options);
		driver = new ChromeDriver();
	}
	
	private String getPath() throws NotSupportOSException{
		if(SystemUtils.IS_OS_WINDOWS) {
			return System.getProperty("user.dir") + File.separator + "chromedriver_win32" + File.separator + "chromedriver.exe";
		}
		else if(SystemUtils.IS_OS_LINUX) {
			return System.getProperty("user.dir") + File.separator + "chromedriver_linux64" + File.separator + "chromedriver.exe";
		}
		else if(SystemUtils.IS_OS_MAC) {
			return System.getProperty("user.dir") + File.separator + "chromedriver_mac64" + File.separator + "chromedriver.exe";
		}
		else throw new NotSupportOSException();
	}
	
	public void getRegistration() {
		init();		
		driver.get("https://prom.ua/join-customer?source_id=txt.register.customer&locale=ru");
		
		WebElement elementName = driver.findElement(By.name("vertical-name"));
		System.out.println(elementName.toString());
		elementName.sendKeys(user.getName());
		WebElement elementEmail = driver.findElement(By.name("vertical-email"));
		elementEmail.sendKeys(user.getEmail());
		WebElement elementPassword = driver.findElement(By.name("vertical-password"));
		elementPassword.sendKeys(user.getPassword());
		WebElement elementButton = driver.findElement(By.className("lp-button__text"));
		elementButton.click();
		try {
			Thread.sleep(60000);
		} catch (InterruptedException ex) {
			logger.log(Level.SEVERE, null, ex);
		}	
		driver.close();
	}
}
