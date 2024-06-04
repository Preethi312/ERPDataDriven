package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil
{
   String Inputpath = "./FileInput/DataOfLogin.xlsx";
   String Outputpath = "./FileOutput/DataDrivenResults.xlsx";
   ExtentReports report;
   ExtentTest logger;
   @Test
   public void adminLogin() throws Throwable
   {
	   report = new ExtentReports("./target/Reports/LoginReports.html");
	   ExcelFileUtil xl = new ExcelFileUtil(Inputpath);
	   int rc = xl.rowcount("Login");
	   Reporter.log("No Of rows ::" + rc, true);
	   for (int i = 1; i<=rc; i++)
	   {
		   logger = report.startTest("Validate Login");
		   logger.assignAuthor("Preethi");
		   String uname = xl.getCellData("Login", i, 0);
		   String pword = xl.getCellData("Login", i, 1);
		   logger.log(LogStatus.INFO, uname +"    "+ pword);
		   boolean res = FunctionLibrary.AdminLogin(uname, pword);
		   if(res)
			   {
			      xl.setCellData("Login", i, 2, "Login Success", Outputpath);
			      xl.setCellData("Login", i, 3, "Pass", Outputpath);
			      logger.log(LogStatus.PASS, "Username and password are valid");
			   }
		   else
		       {
			      xl.setCellData("Login", i, 2, "Login Fail", Outputpath);
			      xl.setCellData("Login", i, 3, "Fail", Outputpath);
			      logger.log(LogStatus.FAIL, "Username and password are not valid");
		       }
       }
	   report.endTest(logger);
	   report.flush();
   }
}
