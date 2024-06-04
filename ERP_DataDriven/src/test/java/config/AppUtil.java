package config;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
public static WebDriver driver;
public static Properties prop;
@BeforeTest
public void setup() throws Throwable
{
	prop = new Properties();
	prop.load(new FileInputStream("./PropertyFile/Login.properities"));
	if(prop.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	else if (prop.getProperty("Browser").equalsIgnoreCase("Firefox"))
	{
		driver = new FirefoxDriver();
	}
	else
	{
		Reporter.log("Browser not compatible",true);
	}
}
@AfterTest
public void teardown()
{
	driver.quit();
}
}
