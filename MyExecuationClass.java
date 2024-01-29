package Execuation;

import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Opretions.MyOpretions;
import ReadExcel.ReadExcelFile;
import ReadObject.MyReadObject;

public class MyExecuationClass {
	
	  WebDriver driver;
	   @BeforeMethod
	   public void beforeMethod() {
	   System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
	   driver = new ChromeDriver();
	   }
	   @Test
	   public void f() throws Exception {
		   
		   ReadExcelFile file = new ReadExcelFile();
		   MyReadObject object = new MyReadObject();
		   Properties allObjects =  object.getObjectRepository();
		   MyOpretions operation = new MyOpretions(driver);
		   Sheet guru99Sheet = file.readExcel(System.getProperty("user.dir")+"\\","TestCase.xlsx" , "KeywordFramework");
		   int rowCount = guru99Sheet.getLastRowNum()-guru99Sheet.getFirstRowNum();
		   for (int i = 1; i < rowCount+1; i++) {
			   Row row = guru99Sheet.getRow(i);
			   if(row.getCell(0).toString().length()==0){
				   System.out.println(row.getCell(1).toString()+"----"+ row.getCell(2).toString()+"----"+row.getCell(3).toString()+"----"+ row.getCell(4).toString());
				   operation.perform(allObjects, row.getCell(1).toString(),row.getCell(2).toString(),row.getCell(3).toString(), row.getCell(4).toString());
			   }
			   else
			   {
				   Thread.sleep(2000);
				   driver.switchTo().alert().accept();
				   System.out.println("New Testcase->"+row.getCell(0).toString() +" Started");
			   }
		   }
	}
	   @AfterMethod
	   public void afterMethod() {
		  driver.navigate().refresh();
		  }
}
