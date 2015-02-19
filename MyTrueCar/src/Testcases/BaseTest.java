package Testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

	public static Properties config =null;
	public static Properties locators = null;
	public static WebDriver driver =null;
	
	
	
	@BeforeSuite
	public void initiliaze() throws IOException{
		
		// loading configuration file
		config = new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//Config//config.properties");
		config.load(fs);
		
		// loading locators file
		locators = new Properties();
		fs = new FileInputStream(System.getProperty("user.dir")+"//src//Config//locators.properties");
		locators.load(fs);
		
		//creating driver for browser
		if(config.getProperty("browser").equals("FireFox")){
			driver = new FirefoxDriver();
		}
		else{
			//add more browser type in config file
		}
		
		// Adding Implicit wait of 20 sec to each element
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(config.getProperty("base_url"));
		
	}
	
	public static WebElement getElement(String xpathKey){
		
		try{
		return driver.findElement(By.xpath(locators.getProperty(xpathKey)));
		}catch(Throwable t){
			//report error
		return null;
		}
	}
}
