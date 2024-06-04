package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil
{
  public static boolean AdminLogin(String user , String pass) throws Throwable
  {
	 driver.get(prop.getProperty("Url"));
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	 driver.findElement(By.xpath("//button[@id='btnreset']")).click();
	 driver.findElement(By.xpath("//input[@id='username']")).sendKeys(user);
	 driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pass);
	 driver.findElement(By.xpath("//button[@id='btnsubmit']")).click();
	 Thread.sleep(3000);
	 String Expected = "dashboard";
	 String Actual = driver.getCurrentUrl();
	 if(Actual.contains(Expected))
	 {
		 Reporter.log("Valid username and password" , true);
		 driver.findElement(By.xpath("//a[@id='logout']")).click();
		 driver.findElement(By.xpath("//button[contains(.,'OK!')]")).click();
		 return true;
	 }
	 else
	 {
		 String message = driver.findElement(By.xpath("//div[@class='alert alert-danger ewError']")).getText();
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//button[@class='ajs-button btn btn-primary']")).click();
		 Reporter.log("Invalid username and password"+"  "+message , true);
		 return false;
	 }
  }
}
