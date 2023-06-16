package providerPortalVNSHealthPlans;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dataDrivenPP.dataDrivenPP;
import io.github.bonigarcia.wdm.WebDriverManager;

public class verifyAllLinks {
	String myhomePage = "https://providerportal.vnshealthplans.org/login";

	String myurl = "";
	HttpURLConnection myhuc = null;
	int responseCode = 200;

	String excelPath = "C:\\Users\\802072\\git\\brokenLinkTestCRM\\src\\test\\resources\\testData\\testData.xlsx";
	String sheetName = "loginInfo";
	WebDriver driver;

	@BeforeMethod
	public void login() throws IOException, InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(myhomePage);

		dataDrivenPP d = new dataDrivenPP();
		ArrayList data = d.getData("User2", "loginInfo");
		String username = (String) data.get(1);
		String password = (String) data.get(2);

		WebElement enter = driver.findElement(By.xpath("//button[contains(text(), 'Enter')]"));
		enter.click();

		WebElement ok = driver.findElement(By.xpath("//button[contains(text(), 'OK')]"));
		ok.click();

		// enter username
		WebElement uname = driver.findElement(By.xpath("//input[@id='username']"));
		uname.sendKeys(username);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// enter password
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		pwd.sendKeys(password);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// login
		WebElement signOn = driver.findElement(By.xpath("//button[contains(text(), 'Sign On')]"));
		signOn.click();
		Thread.sleep(10000);
		// Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Home']")).getText(),
		// "Home");
		// log("The Login Page url is: " + myhomePage);
		// log("Login is successful with user name : " + data.get(1));
		Thread.sleep(5000);
	}

	@Test
	public void testPatientsLink() {
		WebElement patients = driver.findElement(By.xpath("//a[contains(text(),'Patients')]"));
		patients.click();

	}

	@Test
	public void testRosters() {
		WebElement patients = driver.findElement(By.xpath("//a[contains(text(),'Patients')]"));
		patients.click();
		
		WebElement rosters = driver.findElement(By.xpath("//a[contains(text(),'Rosters')]"));
		rosters.click();
	}
}