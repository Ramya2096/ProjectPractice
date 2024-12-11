package org.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	//==============launch browser method   [All method of base class should be public static
	
	public static WebDriver driver;               // driver varible we declare as global and static to use anywhere
	
	public static  WebDriver chromeBrowser() {      // datatype is webdriver becoz we have to return driver which is ref varible of webDriver
		WebDriverManager.chromedriver().setup();
		//WebDriver driver=new ChromeDriver();      // we declare as global varible 
		driver=new ChromeDriver();                   // so just we write like this 
		return driver;                             // driver in blue clr which indicates global variable
	}
	
//=========================== (or)  launch browser by if else=================================
//	public static WebDriver driver;
//	
//	public static WebDriver launchBrowser(String browser) {
//		if(browser.equalsIgnoreCase("chrome")) {
//			WebDriverManager.chromedriver().setup();
//			driver=new ChromeDriver();
//		}
//		
//		else if(browser.equalsIgnoreCase("edge")) {
//			WebDriverManager.edgedriver().setup();
//			driver=new EdgeDriver();
//		}
//		else if(browser.equalsIgnoreCase("firefox")) {
//			WebDriverManager.firefoxdriver().setup();
//			driver=new FirefoxDriver();
//		}
//		return driver;
//		
//	}
	
	
	//=======launch url 
	public static void urlLaunch(String url) {    // url will change so we give when we call this so pass as argument 
		driver.get(url);
		driver.manage().window().maximize();
	}

	//========wait
	public static  void implicitlyWait(int a) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(a));   //second is user choice may be 10 or 20 so pass in argument
	}
	
	//========sendkeys
	public static void sendKeys(WebElement e,String s) {   // becoz uname.sendkeys("rams) menas wenElemnt ref.sendkeys(value) both change so in argument
		e.sendKeys(s);
	}
	
	//======click
	public static void click(WebElement e ) {
		e.click();
	}
	
	//====quit
	public static void quit() {
		driver.quit();
	}
	
	//====get current url
	public static String currentUrl() {
		String urll=driver.getCurrentUrl();
		return urll;
	}
	
	//======== get text
	public static String getText(WebElement e) {
		String text=e.getText();
		return text;
	}
	
	//=========== get Attribute
	public static String getAttribute(WebElement e) {
	String text	=e.getAttribute("value");
		return text;
		
	}
	
	//=====dropdown select selectByindex
	public static void selectByIndex(WebElement e,int i) {
		Select s=new Select(e);
		s.selectByIndex(i);
	}
	
	//=====dropdown select selectby visibletext
		public static void selectByVisibletext(WebElement e,String s) {
			Select s1=new Select(e);
			s1.selectByVisibleText(s);
		}
		
	//=====dropdown select selectby value
		public static void selectByValue(WebElement e,String s) {
			Select s1=new Select(e);
			s1.selectByValue(s);
		
		}
		
	//====deselect by visible text
		public static void deSelectByVisibleText(WebElement e,String s) {
			Select s1=new Select(e);
			s1.deselectByVisibleText(s);
		}
	
	//======== mouse actions
	public static void moveToElement(WebElement target) {
		Actions a= new Actions(driver);
		a.moveToElement(target).perform();
	}
	
	//===drag and drop
	public static void draganddrop(WebElement source,WebElement target) {
		Actions a= new Actions(driver);
		a.dragAndDrop(source, target).perform();
	}
	
	// ====refresh page
	public static void refresh() {
		driver.navigate().refresh();
	}
	
	//=====frames by webelement
	public static void frameByWebelement(WebElement e) {
		driver.switchTo().frame(e);
	}
	
	//=====frames by idex
	public static void frameByIndex(int i) {
		driver.switchTo().frame(i);
			}
    
	//=====frames by name
		public static void frameByName(String s) {
			driver.switchTo().frame(s);
	
		}
		
	//============screenshot
	public static void screenshot(String location) throws IOException {
		TakesScreenshot ts= (TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		
		File des=new File(location);
		FileUtils.copyFile(src, des);
	
	}
	
	//======javascriptexecutor  scrolldown
	 public static void scrollDown(WebElement e) {
		 JavascriptExecutor js=(JavascriptExecutor)driver;
		 js.executeScript("arguments[0].scrollIntoView(true)",e);
		 
	 }	 
		 
	//======javascriptexecutor  scrollup
	public static void scrollUp(WebElement e) {
	   JavascriptExecutor js=(JavascriptExecutor)driver;
	   js.executeScript("arguments[0].scrollIntoView(false)",e);
			 
	 }	
	
	//==============javascriptexecutor setattribute
	public static void jsSetAttribute(WebElement e,String s) {
     JavascriptExecutor js=(JavascriptExecutor)driver;
	 js.executeScript("arguments[0].setAttribute('value',s)",e);
		 
	}	 
	//===========javascriptexecutor click
	public static void jsClick(WebElement e) {
	     JavascriptExecutor js=(JavascriptExecutor)driver;
	     js.executeScript("arguments[0].click()",e);
	}
	
   //==============javascriptexecutor getattribute
		public static String jsGetAttribute(WebElement e) {
	     JavascriptExecutor js=(JavascriptExecutor)driver;
		String text=(String) js.executeScript("return arguments[0].getAttribute('value')",e);
		return text;
	}	 
	
		
		
  // excel data reading   [NOTE-> see  in package org.exceldata class ExcelRead.java ]== same code here we pass
		
	public static String excelReaddata(String fname,String sheet,int rownum,int cellnum) throws IOException {
		
		File f= new File("C:\\java_selenium gt\\workspace-java-eclipse\\MavenProject\\src\\test\\resources\\TestDatas\\" +fname+ ".xlsx");
		FileInputStream fs= new FileInputStream(f);
		Workbook w=new XSSFWorkbook(fs);
		Sheet s=w.getSheet(sheet);
		Row r=s.getRow(rownum);
		Cell c=r.getCell(cellnum);
		
		//get type  cell kita getcelltype() method irku so cell ref is c so c.gerCellType()
		int type=c.getCellType();
		String value=null;    // initialy keep null based on return value it will assign to it
		
		if(type==1) {
			 value=c.getStringCellValue();   // no data type becoz in null we declare
		}
		else {
			if(DateUtil.isCellDateFormatted(c)) {
				Date celldatevalue=c.getDateCellValue();
				SimpleDateFormat sd=new SimpleDateFormat("dd-MMM-YYYY");
				value=sd.format(celldatevalue);
				
			}
			else {
				double cellnumericvalue=c.getNumericCellValue();
				long num=(long)cellnumericvalue;
				value=String.valueOf(num);
			}
	}
		return value;
		
	}
		
		
		
	//=======window handling
	//======find element ===>but we not use this 
	
		
		
		
}
