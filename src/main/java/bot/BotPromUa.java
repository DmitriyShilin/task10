package bot;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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
			return System.getProperty("user.dir") + File.separator + "chromedriver_linux64" + File.separator + "chromedriver";
		}
		else if(SystemUtils.IS_OS_MAC) {
			return System.getProperty("user.dir") + File.separator + "chromedriver_mac64" + File.separator + "chromedriver";
		}
		else throw new NotSupportOSException();
	}
	
	public void getRegistration() {
		
		init();		
		
		driver.get("https://prom.ua/join-customer?source_id=txt.register.customer&locale=ru");
		
		WebElement elementName = driver.findElement(By.name("vertical-name"));
		elementName.sendKeys(user.getName());
		WebElement elementEmail = driver.findElement(By.name("vertical-email"));
		elementEmail.sendKeys(user.getEmail());
		WebElement elementPassword = driver.findElement(By.name("vertical-password"));
		elementPassword.sendKeys(user.getPassword());
		WebElement elementButton = driver.findElement(By.className("lp-button__text"));
		elementButton.click();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException ex) {
			logger.log(Level.SEVERE, null, ex);
		}	
		driver.quit();
	}
	
	public void addToCart(String articleId) {
		
		init();
		
		driver.get("https://my.prom.ua/cabinet/sign-in");
		WebElement elementEmail = driver.findElement(By.id("phone_email"));
		elementEmail.sendKeys(user.getEmail());
		WebElement elementPassword = driver.findElement(By.id("password"));
		elementPassword.sendKeys(user.getPassword());
		WebElement elementButton = driver.findElement(By.className("button__text--ujaS_"));
		elementButton.click();
		new WebDriverWait(driver, 20).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getCurrentUrl().equals("https://my.prom.ua/cabinet/user");
			}
		});	
		
		driver.get("https://prom.ua/"+ articleId + ".html");
		try {
			WebElement elementAddToCart = driver.findElement(By.linkText("Купить"));
			elementAddToCart.click();
		} catch (NoSuchElementException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException ex) {
			logger.log(Level.SEVERE, null, ex);
		}		
		driver.quit();
	}
}