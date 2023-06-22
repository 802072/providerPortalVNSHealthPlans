package extentReport;

import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.WebDriverManagerException;
import dataDrivenPP.dataDrivenPP;

public class BaseTest {
	public static WebDriver driver;
	public static String screenshotsSubFolderName;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	public static ExtentTest testStepExtentTest;
	String myhomePage = "https://providerportal.vnshealthplans.org/login";
	String excelPath = "C:\\Users\\802072\\git\\brokenLinkTestCRM\\src\\test\\resources\\testData\\testData.xlsx";
	String sheetName = "loginInfo";

	@Parameters("browserName")
	@BeforeTest
	public void setup(ITestContext context, @Optional("chrome") String browserName) throws IOException {
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		}
		driver.manage().window().maximize();
		//Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		//String device = capabilities.getBrowserName() + " "
				//+ capabilities.getVersion().substring(0, capabilities.getVersion().indexOf("."));
		String author = context.getCurrentXmlTest().getParameter("author");

		extentTest = extentReports.createTest(context.getName());
		//extentTest.assignAuthor(author);
		//extentTest.assignDevice(device);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		/*
		 * driver.get(myhomePage); //extentTest.log(Status.PASS,
		 * MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath,
		 * "Screenshot").build());
		 * 
		 * dataDrivenPP d = new dataDrivenPP(); ArrayList data = d.getData("User2",
		 * "loginInfo"); String username = (String) data.get(1); String password =
		 * (String) data.get(2);
		 * 
		 * WebElement enter =
		 * driver.findElement(By.xpath("//button[contains(text(), 'Enter')]"));
		 * enter.click();
		 * 
		 * // WebElement ok = driver.findElement(By.xpath("//button[contains(text(), //
		 * 'OK')]")); // ok.click();
		 * 
		 * // enter username WebElement uname =
		 * driver.findElement(By.xpath("//input[@id='username']"));
		 * uname.sendKeys(username); try { Thread.sleep(5000); } catch
		 * (InterruptedException e) { e.printStackTrace(); }
		 * 
		 * // enter password WebElement pwd =
		 * driver.findElement(By.xpath("//input[@id='password']"));
		 * pwd.sendKeys(password); driver.manage().timeouts().implicitlyWait(10,
		 * TimeUnit.SECONDS);
		 * 
		 * // login WebElement signOn =
		 * driver.findElement(By.xpath("//button[contains(text(), 'Sign On')]"));
		 * signOn.click(); //
		 * extentReports.createTest("SignOn").info("SignOn").addScreenCaptureFromPath(
		 * captureScreenshot("SignOn", //
		 * driver)).addScreenCaptureFromBase64String("SignOn"); //LOG MESSAGES
		 * extentTest.log(Status.PASS, "Open Browser : " +browserName);
		 * extentTest.log(Status.PASS, "Open application login page : " +myhomePage);
		 * extentTest.log(Status.PASS, "Log in as : " +username);
		 */
		extentTest.log(Status.PASS, "Open Browser : " +browserName);
	}

	@BeforeSuite
	public void initialiseExtentReports() {
		ExtentSparkReporter sparkReporter_all = new ExtentSparkReporter("ProviderPortalTests.html");
		sparkReporter_all.config().setReportName("VNS Health Provider Portal Automation Test Report");
	
		// ExtentSparkReporter sparkReporter_failed = new
		// ExtentSparkReporter("FailedTests.html");
		// sparkReporter_failed.filter().statusFilter().as(new Status[]
		// {Status.FAIL}).apply();
		// sparkReporter_failed.config().setReportName("VNS Report");

		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter_all);

		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		

	}

	@AfterSuite
	public void generateExtentReports() throws Exception {
		extentReports.flush();
		Desktop.getDesktop().browse(new File("ProviderPortalTests.html").toURI());
		//Desktop.getDesktop().browse(new File("FailedTests.html").toURI());
	}

	@AfterMethod
	public void checkStatus(Method m, ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			
			//String screenshotPath = null;
			//screenshotPath = captureScreenshot(
					//result.getTestContext().getName() + "_" + result.getMethod().getMethodName() + ".jpg");
			//extentTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath, "Screenshot").build());
			//testStepExtentTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath, "Screenshot").build());
			
			//testStepExtentTest.fail(m.getName() + " has failed");
			//testStepExtentTest.fail(result.getThrowable());
			
			//extentTest.fail(m.getName() + " has failed");
			//extentTest.fail(result.getThrowable());
			
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			
			//String screenshotPath = null;
			//screenshotPath = captureScreenshot(
					//result.getTestContext().getName() + "_" + result.getMethod().getMethodName() + ".jpg");
			//extentTest.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath, "Screenshot").build());
			//testStepExtentTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath, "Screenshot").build());
			//extentTest.pass(m.getName() + " has passed");
			//testStepExtentTest.pass(m.getName() + " has passed");
		}

		extentTest.assignCategory(m.getAnnotation(Test.class).groups());
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}
	

	public String captureScreenshot(String fileName) {
		if (screenshotsSubFolderName == null) {
			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
			screenshotsSubFolderName = myDateObj.format(myFormatObj);
		}

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshots/" + screenshotsSubFolderName + "/" + fileName);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot saved successfully");
		return destFile.getAbsolutePath();
	}
}