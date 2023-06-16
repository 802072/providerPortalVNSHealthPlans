package providerPortalVNSHealthPlans;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dataDrivenPP.dataDrivenPP;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
public class verifyAllLinks {
	String myhomePage = "https://providerportal.vnshealthplans.org/login";

	String myurl = "";
	HttpURLConnection myhuc = null;
	int responseCode = 200;

	String excelPath = "C:\\Users\\802072\\git\\brokenLinkTestCRM\\src\\test\\resources\\testData\\testData.xlsx";
	String sheetName = "loginInfo";
	WebDriver driver;

	@BeforeTest
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Home']")).getText(),
		// "Home");
		// log("The Login Page url is: " + myhomePage);
		// log("Login is successful with user name : " + data.get(1));
		
	}

	// Patients
	@Test (priority=1)
	public void verifyPatientsLink() throws InterruptedException {
		// Patients
		WebElement patients = driver.findElement(By.xpath("//a[contains(text(),'Patients')]"));
		Actions action = new Actions(driver);
		action.moveToElement(patients).perform();

		// Rosters
		WebElement rosters = new WebDriverWait(driver, 20)
		        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Rosters')]")));
		//WebElement rosters = driver.findElement(By.xpath("//a[contains(text(),'Rosters')]"));
		rosters.click();

		//new WebDriverWait(driver, 5000).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(patients));
		//driver.findElement((By) patients).click();
	}
	//Eligibility Search
	@Test (priority=2)
	public void verifyElgibilitySearch() throws InterruptedException {
		// Patients
		WebElement patients = driver.findElement(By.xpath("//a[contains(text(),'Patients')]"));
		Actions action = new Actions(driver);
		action.moveToElement(patients).perform();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//WebElement eligSearch = driver.findElement(By.xpath("//a[contains(text(),'Eligibility Search')]"));
		WebElement eligSearch = new WebDriverWait(driver, 10)
		        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Eligibility Search')]")));
		eligSearch.click();
		
	}
	
	// Enrollment Referrals	
	@Test (priority=3)
	public void verifyEnrollmentRef() {
		// Patients
		WebElement patients = driver.findElement(By.xpath("//a[contains(text(),'Patients')]"));
		Actions action = new Actions(driver);
		action.moveToElement(patients).perform();
		System.out.println("patients clicked");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//WebElement enrollmentRef = driver.findElement(By.xpath("//a[contains(text(),'Enrollment Referrals')]"));
		WebElement enrollmentRef = new WebDriverWait(driver, 10) 
		        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Enrollment Referrals')]")));
		enrollmentRef.click();
	}
	
	
	//Pending
	@Test (priority=4)
	public void verifyClaimsAndPayments() throws InterruptedException {
		//WebElement claims = driver.findElement(By.xpath("//a[@href='/claims']"));
		WebElement claims = new WebDriverWait(driver, 20)
		        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/claims']")));
		claims.click();
		/*
		 *     YZ72336H Medical Paid
		 */
		WebElement memberID = new WebDriverWait(driver, 20)
		        .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='TMG_V_NUMBER']")));
		memberID.click();
		memberID.sendKeys("V70102027");
		
		WebElement fName= driver.findElement(By.xpath("//input[@name='MEME_FIRST_NAME']"));
		fName.click();
		fName.sendKeys("Rahsaan");
		
		WebElement lName= driver.findElement(By.xpath("//input[@name='MEME_LAST_NAME']"));
		lName.sendKeys("Smith");
		
		WebElement claimID= driver.findElement(By.xpath("//input[@name='TMG_CLCL_ID']"));
		claimID.sendKeys("230800012400");
		
		WebElement medicareID= driver.findElement(By.xpath("//input[@name='HICN_MEDICARE_NO']"));
		medicareID.sendKeys("5A57HF9KT60");
		
		WebElement medicaidID= driver.findElement(By.xpath("//input[@name='MEDICAID_NO']"));
		medicaidID.sendKeys("YZ72336H");
		
		//WORKING HERE
		//WebElement claimType= driver.findElement(By.xpath("//button[@id='combobox-button-192']"));
		//claimType.click();
		
		//span[@title= "MEDICAL"]
	}

	// Pending
	@Test (priority=5)
	public void verifyAuthorizations() {
		//WebElement auth = driver.findElement(By.xpath("//a[@href='/authorizations']"));
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement auth = new WebDriverWait(driver, 10)
		        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/authorizations']")));
		auth.click();
	}

	@Test (priority=6)
	public void verifyProviderDirectory() {
		//WebElement provDir = driver.findElement(By.xpath("//a[contains(text(),'Provider Directory ')]"));
		WebElement provDir = new WebDriverWait(driver, 10)
		        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Provider Directory ')]")));
		provDir.click();
	}

	@Test (priority=7)
	public void verifyFormulatorySearch() {
		//WebElement formuSearch = driver.findElement(By.xpath("//a[contains(text(),'Formulary Search')]"));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement formuSearch = new WebDriverWait(driver, 20)
		        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Formulary Search')]")));
		formuSearch.click();
	}

	@Test (priority=8)
	public void verifyProviderToolkit() {
		//WebElement providerTool = driver.findElement(By.xpath("//a[contains(text(),'Provider Toolkit')]"));
		WebElement providerTool = new WebDriverWait(driver, 10)
		        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Provider Toolkit')]")));
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		providerTool.click();
	}

	@Test (priority=9)
	public void verifyAppealsDisputes() {
		//WebElement appealsNDisp = driver.findElement(By.xpath("//a[contains(text(),'Appeals & Disputes')]"));
		WebElement appealsNDisp = new WebDriverWait(driver, 10)
		        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Appeals & Disputes')]")));
		appealsNDisp.click();
	}

	@Test (priority=10)
	public void VerifyCommunicationCenter() {
		//WebElement communiCenter = driver.findElement(By.xpath("//a[contains(text(),'Communication Center')]"));
		WebElement communiCenter = new WebDriverWait(driver, 10)
		        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Communication Center')]")));
		communiCenter.click();
	}

	@Test (priority=11)
	public void VerifyResources() {
		//WebElement resources = driver.findElement(By.xpath("//a[contains(text(),'Resources')]"));
		WebElement resources = new WebDriverWait(driver, 10)
		        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Resources')]")));
		resources.click();
	}

	@Test (priority=12)
	public void VerifyMyAccount() {
		WebElement myAcc = driver.findElement(By.xpath("//a[contains(text(),'My Account')]"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		myAcc.click();
	}

	@Test (priority=13)
	public void VerifyLogOut() {
		WebElement logout = driver.findElement(By.xpath("//div[contains(text(),'Log Out')]"));
		logout.click();
	}

	@Test (priority=14)
	public void VerifyTermsOfUse() {
		WebElement termsofuse = driver.findElement(By.xpath("//a[contains(text(),'Terms of Use')]"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		termsofuse.click();
	}

	@Test (priority=15)
	public void VerifyPrivacyPolicy() {
		WebElement privacyPolicy = driver.findElement(By.xpath("//a[contains(text(),'Privacy Policy')]"));
		privacyPolicy.click();
	}

	@Test (priority=16)
	public void VerifyTechSupp() {
		WebElement tectSupp = driver.findElement(By.xpath("//a[contains(text(),'Technical Support')]"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		tectSupp.click();
	}

	@Test (priority=17)
	public void VerifyContactUs() {
		WebElement contactUs = driver.findElement(By.xpath("//a[contains(text(),'Contact Us')]"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		contactUs.click();
	}

	@AfterTest
	public void close() {
		driver.quit();
	}

}