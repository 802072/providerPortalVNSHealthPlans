package providerPortalVNSHealthPlans;

import java.io.IOException;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import dataDrivenPP.dataDrivenPP;
import extentReport.BaseTest;
import org.openqa.selenium.support.ui.Select;

public class verifyAllLinks extends BaseTest {
	String myhomePage = "https://providerportal.vnshealthplans.org/login";
	String excelPath = "C:\\Users\\802072\\git\\brokenLinkTestCRM\\src\\test\\resources\\testData\\testData.xlsx";
	String sheetName = "loginInfo";
	JavascriptExecutor js = (JavascriptExecutor) driver;
	// Patients

	@Test(priority = -1)
	public void logIn() throws InterruptedException, IOException {
		driver.get(myhomePage);

		//testStepExtentTest = extentTest.createNode("TSLI001: Navigate to Login Page")
				//.log(Status.PASS, "Navigate to Login Page").addScreenCaptureFromPath(captureScreenshot("L1.jpg"));
		//extentTest.log(Status.PASS, "Navigate to Login Page"+MediaEntityBuilder.
				//createScreenCaptureFromPath(captureScreenshot("L1.jpg")).build());
		extentTest.log(Status.PASS, "TSLI001: Navigate to Login Page", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("L1.jpg")).build());
		
		// captureScreenshot("L1.jpg");

		dataDrivenPP d = new dataDrivenPP();
		ArrayList data = d.getData("User2", "loginInfo");
		String username = (String) data.get(1);
		String password = (String) data.get(2);

		WebElement enter = driver.findElement(By.xpath("//button[contains(text(), 'Enter')]"));
		enter.click();
		//testStepExtentTest = extentTest.createNode("TSLI002: Click Enter").log(Status.PASS, "Click Enter")
			//	.addScreenCaptureFromPath(captureScreenshot("L2.jpg"));
		extentTest.log(Status.PASS, "TSLI002: Click Enter", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("L2.jpg")).build());
		//extentTest.generateLog(Status.PASS, "Click Enter").addScreenCaptureFromPath(captureScreenshot("L2.jpg"));
		// WebElement ok = driver.findElement(By.xpath("//button[contains(text(),
		// 'OK')]"));
		// ok.click();

		// enter username
		WebElement uname = driver.findElement(By.xpath("//input[@id='username']"));
		uname.sendKeys(username);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//testStepExtentTest = extentTest.createNode("TSLI003: Enter Username").log(Status.PASS, "Enter Username")
				//.addScreenCaptureFromPath(captureScreenshot("L3.jpg"));
		//extentTest.generateLog(Status.PASS, "TSLI003: Enter Username")
				//.addScreenCaptureFromPath(captureScreenshot("L3.jpg"));
		extentTest.log(Status.PASS, "TSLI003: Enter Username", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("L3.jpg")).build());
		// enter password
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		pwd.sendKeys(password);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//testStepExtentTest = extentTest.createNode("TSLI004: Enter Password").log(Status.PASS, "Enter Password")
				//.addScreenCaptureFromPath(captureScreenshot("L4.jpg"));
		extentTest.log(Status.PASS, "TSLI004: Enter Password", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("L4.jpg")).build());
		// login
		WebElement signOn = driver.findElement(By.xpath("//button[contains(text(), 'Sign On')]"));
		signOn.click();

		Thread.sleep(5000);
		//testStepExtentTest = extentTest.createNode("TSLI005: Sign in").log(Status.PASS, "Sign in")
				//.addScreenCaptureFromPath(captureScreenshot("L5.jpg"));
		// extentTest.log(Status.PASS, "Open application login page : " +myhomePage);
		// extentTest.log(Status.PASS, "Log in as : " +username);
		extentTest.log(Status.PASS, "TSLI005: Sign in", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("L5.jpg")).build());
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Home']")).getText(), "Home");

		//testStepExtentTest = extentTest.createNode("TSLI006: Open Homepage").log(Status.PASS, "Open Homepage")
				//.addScreenCaptureFromPath(captureScreenshot("TSLI005.jpg"));
		extentTest.log(Status.PASS, "TSLI006: Open Homepage", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("L6.jpg")).build());
	}

	@Test(priority = 1)
	public void verifyRosters() throws InterruptedException {
		// Patients
		Thread.sleep(5000);
		WebElement patients = driver.findElement(By.xpath("//a[contains(text(),'Patients')]"));
		Actions action = new Actions(driver);
		action.moveToElement(patients).perform();
		// extentTest.log(Status.INFO, "SC"+
		// extentTest.addScreenCaptureFromPath(captureScreenshot("test.jpg")));
		//testStepExtentTest = extentTest.createNode("TSVR001: Hover on Patients").log(Status.PASS, "Hover on Patients")
				//.addScreenCaptureFromPath(captureScreenshot("VR1.jpg"));
		extentTest.log(Status.PASS, "TSVR001: Hover on Patients", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("VR1.jpg")).build());

		// extentTest.log(Status.PASS, "Hover on Patients element");
		// extentTest.log(Status.INFO, "SC"+
		// extentTest.addScreenCaptureFromPath(captureScreenshot("test1.jpg")));

		// Rosters
		Thread.sleep(5000);
		WebElement rosters = driver.findElement(By.xpath("//a[normalize-space()='Rosters']"));
		try {
			rosters.click();
		} catch (StaleElementReferenceException e) {
			rosters = driver.findElement(By.xpath("//a[normalize-space()='Rosters']"));
			rosters.click();
		}
		Thread.sleep(5000);
		// LOG MESSAGES
		Assert.assertEquals(
				driver.findElement(By.xpath("//strong[normalize-space()='Membership Roster & PCP Panel']")).getText(),
				"Membership Roster & PCP Panel");
		//testStepExtentTest = extentTest.createNode("TSVR002: Open Rosters Link").log(Status.PASS, "Open Rosters Link")
				//.addScreenCaptureFromPath(captureScreenshot("VR2.jpg"));
		extentTest.log(Status.PASS, "TSVR002: Open Rosters Link", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("VR2.jpg")).build());
		// Provider Practice*********************************************
		Thread.sleep(5000);
		WebElement provPractice = driver.findElement(By.xpath("//span[contains(text(),'Select a Practice')]"));
		provPractice.click();
		Thread.sleep(5000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//strong[normalize-space()='Membership Roster & PCP Panel']")).getText(),
				"Membership Roster & PCP Panel");
		//testStepExtentTest = extentTest.createNode("TSVR003: Select a Provider Practice")
				//.log(Status.PASS, "Select a Provider Practice").addScreenCaptureFromPath(captureScreenshot("VR3.jpg"));
		extentTest.log(Status.PASS, "TSVR003: Select a Provider Practice", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("VR3.jpg")).build());
		
		WebElement internalMed = driver.findElement(By.xpath(
				"(//span[@title='Curtis Cave - Internal Medicine'][normalize-space()='Curtis Cave - Internal Medicine'])[1]"));
		internalMed.click();
		//testStepExtentTest = extentTest.createNode("TSVR004: Select Curtis Cave - Internal Medicine")
				//.log(Status.PASS, " Select Curtis Cave - Internal Medicine")
				//.addScreenCaptureFromPath(captureScreenshot("VR4.jpg"));
		extentTest.log(Status.PASS, "TSVR004: Select Curtis Cave - Internal Medicine", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("VR4.jpg")).build());
		// extentTest.log(Status.PASS, "Open Rosters Link");
	}

	// Eligibility Search
	@Test(priority = 2)
	public void verifyEligibilitySearch() throws InterruptedException {
		// Patients
		Thread.sleep(5000);
		WebElement patients = driver.findElement(By.xpath("//a[contains(text(),'Patients')]"));
		Actions action = new Actions(driver);
		action.moveToElement(patients).perform();

		//testStepExtentTest = extentTest.createNode("TSVES001: Hover on Patients").log(Status.PASS, "Hover on Patients")
			//	.addScreenCaptureFromPath(captureScreenshot("hoverpatient.jpg"));
		extentTest.log(Status.PASS, "TSVES001: Hover on Patients", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("ES1.jpg")).build());
		// extentTest.log(Status.PASS, "Hover on Patients element");

		Thread.sleep(5000);
		WebElement eligSearch = driver.findElement(By.xpath("//a[normalize-space()='Eligibility Search']"));
		Thread.sleep(5000);
		try {
			eligSearch.click();
		} catch (StaleElementReferenceException e) {
			eligSearch = driver.findElement(By.xpath("//a[normalize-space()='Eligibility Search']"));
			eligSearch.click();
		}
		Thread.sleep(7000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//strong[normalize-space()='Patient Eligibility Search']")).getText(),
				"Patient Eligibility Search");
		//testStepExtentTest = extentTest.createNode("TSVES002: Open Eligibility Search Link")
			//	.log(Status.PASS, "Open Eligibility Search Link")
				//.addScreenCaptureFromPath(captureScreenshot("eligibilitySearch.jpg"));
		extentTest.log(Status.PASS, "TSVES002: Open Eligibility Search Link", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("ES2.jpg")).build());
		// extentTest.log(Status.PASS, "Open Eligibility Search Link");

		// Field Filters
		WebElement fName = driver.findElement(By.xpath("//input[@name='MEME_FIRST_NAME']"));
		fName.click();
		fName.sendKeys("Rahsaan");

		WebElement lName = driver.findElement(By.xpath("//input[@name='MEME_LAST_NAME']"));
		lName.sendKeys("Smith");

		WebElement memberID = driver.findElement(By.xpath("//input[@name='TMG_V_NUMBER']"));
		memberID.click();
		memberID.sendKeys("V70102027");

		WebElement medicareID = driver.findElement(By.xpath("//input[@name='HICN_MEDICARE_NO']"));
		medicareID.sendKeys("5A57HF9KT60");

		WebElement medicaidID = driver.findElement(By.xpath("//input[@name='MEDICAID_NO']"));
		medicaidID.sendKeys("YZ72336H");
		//testStepExtentTest = extentTest.createNode("TSVES003: Enter Field Filters")
				//.log(Status.PASS, "Enter Field Filters").addScreenCaptureFromPath(captureScreenshot("TSVES003.jpg"));
		extentTest.log(Status.PASS, "TSVES003: Enter Field Filters", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("ES3.jpg")).build());
		WebElement search = driver.findElement(By.xpath("//button[normalize-space()='Search']"));
		search.click();
		//testStepExtentTest = extentTest.createNode("TSVES004: Search and Display Results")
				//.log(Status.PASS, "Search and Display Results")
				//.addScreenCaptureFromPath(captureScreenshot("TSVES004.jpg"));
		extentTest.log(Status.PASS, "TSVES004: Search and Display Results", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("ES4.jpg")).build());
	}

	// Enrollment Referrals
	@Test(priority = 3)
	public void verifyEnrollmentRef() throws InterruptedException {
		// Patients
		Thread.sleep(5000);
		WebElement patients = driver.findElement(By.xpath("//a[contains(text(),'Patients')]"));
		Actions action = new Actions(driver);
		action.moveToElement(patients).perform();
		// extentTest.log(Status.PASS, "Hover on Patients element");
		//testStepExtentTest = extentTest.createNode("TSVE001: Hover on Patients").log(Status.PASS, "Hover on Patients")
			//	.addScreenCaptureFromPath(captureScreenshot("TSVE001.jpg"));
		extentTest.log(Status.PASS, "TSVE001: Hover on Patients", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSVE001.jpg")).build());

		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement enrollmentRef = driver.findElement(By.xpath("//a[normalize-space()='Enrollment Referrals']"));
		Thread.sleep(5000);
		try {
			enrollmentRef.click();
		} catch (StaleElementReferenceException e) {
			enrollmentRef = driver.findElement(By.xpath("//a[normalize-space()='Enrollment Referrals']"));
			enrollmentRef.click();
		}
		Thread.sleep(5000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//strong[normalize-space()='Patient Enrollment Referrals']")).getText(),
				"Patient Enrollment Referrals");
		// extentTest.log(Status.PASS, "Open Enrollment Referrals Link");
		//testStepExtentTest = extentTest.createNode("TSVE002: Open Enrollment Referrals Link")
				//.log(Status.PASS, "Open Enrollment Referrals Link")
				//.addScreenCaptureFromPath(captureScreenshot("TSVE002.jpg"));
		extentTest.log(Status.PASS, "TSVE002: Open Enrollment Referrals Link", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSVE002.jpg")).build());

		Thread.sleep(5000);
		// SUBMIT REF*********************************************
		WebElement submitRef = driver.findElement(By.xpath("//a[contains(text(),'Submit Referral')]"));
		submitRef.click();
		// extentTest.log(Status.PASS, "Enter Submit Referral");
		//testStepExtentTest = extentTest.createNode("TSVE003: Enter Submit Referral")
				//.log(Status.PASS, "Enter Submit Referral").addScreenCaptureFromPath(captureScreenshot("TSVE003.jpg"));
		extentTest.log(Status.PASS, "TSVE003: Enter Submit Referral", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSVE003.jpg")).build());
		Thread.sleep(5000);
		// String parentHandle = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		Assert.assertEquals(
				driver.findElement(By.xpath("(//b[normalize-space()='Referrer Information'])[1]")).getText(),
				"Referrer Information");
		// extentTest.log(Status.PASS, "Open MLTC Referral Form Page Link from different
		// domain");
		//testStepExtentTest = extentTest.createNode("TSVE004: Open MLTC Referral Form Page Link from another domain")
				//.log(Status.PASS, "Open MLTC Referral Form Page Link from another domain")
				//.addScreenCaptureFromPath(captureScreenshot("TSVE004.jpg"));
		extentTest.log(Status.PASS, "TSVE004: Open MLTC Referral Form Page Link from another domain",
				MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("TSVE004.jpg")).build());
	}

	// Pending
	@Test(priority = 4)
	public void verifyClaimsAndPayments() throws InterruptedException {
		Thread.sleep(5000);
		WebElement claims = driver.findElement(By.xpath("//a[@href='/claims']"));
		Thread.sleep(5000);
		try {
			claims.click();
		} catch (StaleElementReferenceException e) {
			claims = driver.findElement(By.xpath("//a[@href='/claims']"));
			claims.click();
		}
		Assert.assertEquals(driver.findElement(By.xpath("//strong[normalize-space()='Claims']")).getText(), "Claims");
		// extentTest.log(Status.PASS, "Open Claims & Payments Link");
		//testStepExtentTest = extentTest.createNode("TSCP001: Open Claims & Payments Link")
				//.log(Status.PASS, "Open Claims & Payments Link")
				//.addScreenCaptureFromPath(captureScreenshot("TSCP001.jpg"));
		extentTest.log(Status.PASS, "TSCP001: Open Claims & Payments Link", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSCP001.jpg")).build());

		WebElement memberID = driver.findElement(By.xpath("//input[@name='TMG_V_NUMBER']"));
		memberID.click();
		memberID.sendKeys("V70102027");

		WebElement fName = driver.findElement(By.xpath("//input[@name='MEME_FIRST_NAME']"));
		fName.click();
		fName.sendKeys("Rahsaan");

		WebElement lName = driver.findElement(By.xpath("//input[@name='MEME_LAST_NAME']"));
		lName.sendKeys("Smith");

		WebElement claimID = driver.findElement(By.xpath("//input[@name='TMG_CLCL_ID']"));
		claimID.sendKeys("230800012400");

		WebElement medicareID = driver.findElement(By.xpath("//input[@name='HICN_MEDICARE_NO']"));
		medicareID.sendKeys("5A57HF9KT60");

		WebElement medicaidID = driver.findElement(By.xpath("//input[@name='MEDICAID_NO']"));
		medicaidID.sendKeys("YZ72336H");

		WebElement claimType = driver.findElement(
				By.xpath("(//span[@class='slds-truncate'][normalize-space()='Select a filter value'])[1]"));
		claimType.click();
		WebElement medical = driver.findElement(By.xpath("//span[@title= \"MEDICAL\"]"));
		medical.click();

		WebElement status = driver.findElement(By.xpath("//span[normalize-space()='Select a filter value']"));
		status.click();

		WebElement paid = driver.findElement(By.xpath("//span[@title= \"PAID\"]"));
		paid.click();

		// extentTest.log(Status.PASS, "Enter Member Details");
		//testStepExtentTest = extentTest.createNode("TSCP002: Enter Member Details")
				//.log(Status.PASS, "Enter Member Details").addScreenCaptureFromPath(captureScreenshot("TSCP002.jpg"));
		extentTest.log(Status.PASS, "TSCP002: Enter Member Details", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSCP002.jpg")).build());
		WebElement search = driver.findElement(By.xpath("//button[normalize-space()='Search']"));
		search.click();

		Thread.sleep(7000);
		Assert.assertEquals(driver.findElement(By.xpath("//button[normalize-space()='Export']")).getText(), "Export");
		// extentTest.log(Status.PASS, "Search for claim and Payment Details and Show
		// Result");
		//testStepExtentTest = extentTest.createNode("TSCP003: Search for claim and Payment Details and Show Result")
				//.log(Status.PASS, "Search for claim and Payment Details and Show Result")
				//.addScreenCaptureFromPath(captureScreenshot("TSCP003.jpg"));
		extentTest.log(Status.PASS, "TSCP003: Search for claim and Payment Details and Show Result", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSCP003.jpg")).build());
	}

	// Pending
	@Test(priority = 5)
	public void verifyAuthorizations() throws InterruptedException {
		Thread.sleep(5000);
		WebElement auth = driver.findElement(By.xpath("//a[@href='/authorizations']"));

		try {
			auth.click();
		} catch (StaleElementReferenceException e) {
			auth = driver.findElement(By.xpath("//a[@href='/authorizations']"));
			auth.click();
		}

		Thread.sleep(5000);
		// extentTest.log(Status.PASS, "Open Authorizations Link");
		//testStepExtentTest = extentTest.createNode("TSAU001: Open Authorizations Link")
			//	.log(Status.PASS, "Open Authorizations Link")
			//	.addScreenCaptureFromPath(captureScreenshot("TSAU001.jpg"));
		extentTest.log(Status.PASS, "TSAU001: Open Authorizations Link", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSAU001.jpg")).build());

		Thread.sleep(5000);
		/*
		 * // SUBMIT A NEW AUTH*********************************************
		 * 
		 * WebElement submitNewAuthBtn = driver .findElement(By.
		 * xpath("//button[contains(text(),'Submit a New Authorization Request')]"));
		 * submitNewAuthBtn.click(); WebElement fName =
		 * driver.findElement(By.xpath("//input[@name='MemberFirstNameSearchText']"));
		 * fName.sendKeys("JUANA");
		 * 
		 * WebElement lName =
		 * driver.findElement(By.xpath("//input[@name='MemberLastNameSearchText']"));
		 * lName.sendKeys("AZCONA");
		 * 
		 * WebElement memID = driver.findElement(By.xpath("//input[@name='MemberId']"));
		 * memID.sendKeys("V70015095");
		 * 
		 * WebElement nextBtn =
		 * driver.findElement(By.xpath("//button[normalize-space()='Next']"));
		 * nextBtn.click();
		 * 
		 * Thread.sleep(5000);
		 * 
		 * WebElement yesBtn =
		 * driver.findElement(By.xpath("(//span[@class='slds-radio_faux'])[1]"));
		 * yesBtn.click();
		 * 
		 * WebElement next =
		 * driver.findElement(By.xpath("//button[normalize-space()='Next']"));
		 * next.click(); //extentTest.log(Status.PASS, "Submit a New Authorization");
		 * testStepExtentTest =
		 * extentTest.createNode("TSAU002: Submit a New Authorization")
		 * .log(Status.PASS, "Submit a New Authorization")
		 * .addScreenCaptureFromPath(captureScreenshot("TSAU002.jpg"));
		 * Thread.sleep(5000);
		 */

		// Search

		/*
		 * try { auth.click(); } catch (StaleElementReferenceException e) { auth =
		 * driver.findElement(By.xpath("//a[@href='/authorizations']")); auth.click(); }
		 * //extentTest.log(Status.PASS, "Open Authorizations Link"); testStepExtentTest
		 * = extentTest.createNode("TSAU003: Open Authorizations Link")
		 * .log(Status.PASS, "Open Authorizations Link")
		 * .addScreenCaptureFromPath(captureScreenshot("TSAU003.jpg"));
		 */
		// Thread.sleep(5000);
		// SEARCH
		WebElement vnsmemID = driver.findElement(By.xpath("//input[@name='GC_PATIENT_ID']"));
		vnsmemID.sendKeys("V70015095");

		WebElement firstName = driver.findElement(By.xpath("//input[@name='FIRST_NAME']"));
		firstName.sendKeys("JUANA");

		WebElement lastName = driver.findElement(By.xpath("//input[@name='LAST_NAME']"));
		lastName.sendKeys("AZCONA");

		WebElement authType = driver.findElement(
				By.xpath("(//span[@class='slds-truncate'][normalize-space()='Select a filter value'])[1]"));
		authType.click();

		WebElement profServ = driver.findElement(By.xpath("//span[contains(text(),'Professional Services')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", profServ);
		profServ.click();

		WebElement decStatus = driver.findElement(By.xpath("//span[normalize-space()='Select a filter value']"));
		decStatus.click();

		WebElement approved = driver.findElement(By.xpath("//span[@title='Approved']"));
		approved.click();

		WebElement authNum = driver.findElement(By.xpath("//input[@name='GC_AUTH_NO']"));
		authNum.sendKeys("0722FUG7R");

		WebElement search = driver.findElement(By.xpath("//button[contains(text(), 'Search')]"));

		// extentTest.log(Status.PASS, "Enter Member Details");
		//testStepExtentTest = extentTest.createNode("TSAU002: Enter Member Details")
			//	.log(Status.PASS, "Enter Member Details").addScreenCaptureFromPath(captureScreenshot("TSAU002.jpg"));
		extentTest.log(Status.PASS, "TSAU002: Enter Member Details", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSAU002.jpg")).build());
		
		try {
			search.click();
		} catch (StaleElementReferenceException e) {
			search = driver.findElement(By.xpath("//button[normalize-space()='Search']"));
			search.click();
		}
		Thread.sleep(10000);
		Assert.assertEquals(driver.findElement(By.xpath("//button[normalize-space()='Export']")).getText(), "Export");
		// extentTest.log(Status.PASS, "Search for Authorization Details and Show
		// Result");
		//testStepExtentTest = extentTest.createNode("TSAU003: Search for Authorization Details and Show Result")
				//.log(Status.PASS, "Search for Authorization Details and Show Result")
				//.addScreenCaptureFromPath(captureScreenshot("TSAU003.jpg"));
		extentTest.log(Status.PASS, "TSAU003: Search for Authorization Details and Show Result", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSAU003.jpg")).build());
	}

	@Test(priority = 6)
	public void verifyProviderDirectory() throws InterruptedException {
		Thread.sleep(5000);
		WebElement provDir = new WebDriverWait(driver, 10).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Provider Directory ')]")));
		provDir.click();

		// extentTest.log(Status.PASS, "Open Provider Directory Link");
//		testStepExtentTest = extentTest.createNode("TSPD001: Open Provider Directory Link")
//				.log(Status.PASS, "Open Provider Directory Link")
//				.addScreenCaptureFromPath(captureScreenshot("TSPD001.jpg"));

		// String winHandleBefore = driver.getWindowHandle();
		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		Thread.sleep(5000);
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[contains(text(),'Provider and Pharmacy Online Search')]")).getText(),
				"Provider and Pharmacy Online Search");
		// extentTest.log(Status.PASS, "Open Provider and Pharmacy Online Search URL
		// from different domain");
		//testStepExtentTest = extentTest.createNode("TSPD001: Open Provider and Pharmacy Online Search URL from another domain")
				//.log(Status.PASS, "Open Provider and Pharmacy Online Search URL from another domain")
				//.addScreenCaptureFromPath(captureScreenshot("TSPD001.jpg"));
		extentTest.log(Status.PASS, "TSPD001: Open Provider and Pharmacy Online Search URL from another domain", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSPD001.jpg")).build());
		
		// driver.close();
		// driver.switchTo().window(winHandleBefore);
		Thread.sleep(5000);
	}

	@Test(priority = 7)
	public void verifyFormularySearch() throws InterruptedException {
		Thread.sleep(5000);
		WebElement formuSearch = driver.findElement(By.xpath("//a[contains(text(),'Formulary Search')]"));

		try {
			formuSearch.click();
		} catch (StaleElementReferenceException e) {
			formuSearch = driver.findElement(By.xpath("//a[contains(text(),'Formulary Search')]"));
			formuSearch.click();
		}
		// extentTest.log(Status.PASS, "Open Formulary Search Link");
//		testStepExtentTest = extentTest.createNode("TSFS001: Open Formulary Search Link")
//				.log(Status.PASS, "Open Formulary Search Link")
//				.addScreenCaptureFromPath(captureScreenshot("TSFS001.jpg"));
		extentTest.log(Status.PASS, "TSFS001: Open Formulary Search Link", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSFS001.jpg")).build());
		Thread.sleep(5000);
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		Thread.sleep(5000);
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Formulary Search')]")).getText(),
				"Formulary Search");
		// extentTest.log(Status.PASS, "Open Formulary Search Page URL from another
		// domain");
		//testStepExtentTest = extentTest.createNode("TSFS001: Open Formulary Search Page URL from another domain")
			//	.log(Status.PASS, "Open Formulary Search Link")
			//	.addScreenCaptureFromPath(captureScreenshot("TSFS001.jpg"));
	}

	@Test(priority = 8)
	public void verifyProviderToolkit() throws InterruptedException {
		Thread.sleep(5000);
		WebElement providerTool = driver.findElement(By.xpath("//a[contains(text(),'Provider Toolkit')]"));
		try {
			providerTool.click();
		} catch (StaleElementReferenceException e) {
			providerTool = driver.findElement(By.xpath("//a[contains(text(),'Provider Toolkit')]"));
			providerTool.click();
		}
		// extentTest.log(Status.PASS, "Open Provider Toolkit Link");
//		testStepExtentTest = extentTest.createNode("TSPT001: Open Provider Toolkit Link")
//				.log(Status.PASS, "Open Provider Toolkit Link")
//				.addScreenCaptureFromPath(captureScreenshot("TSPT001.jpg"));
		Thread.sleep(5000);
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		Thread.sleep(5000);
		Assert.assertEquals(driver.findElement(By.xpath("(//span[contains(text(),'Provider Toolkit')])[3]")).getText(),
				"Provider Toolkit");
		// extentTest.log(Status.PASS, "Open Provider Education Page from another
		// domain");
		//testStepExtentTest = extentTest.createNode("TSPT001: Open Provider Education Page from another domain")
				//.log(Status.PASS, "Open Provider Education Page from another domain")
				//.addScreenCaptureFromPath(captureScreenshot("TSPT001.jpg"));
		extentTest.log(Status.PASS, "TSPT001: Open Provider Education Page from another domain", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSPT001.jpg")).build());
	}

	@Test(priority = 9)
	public void verifyAppealsDisputes() throws InterruptedException {
		Thread.sleep(5000);
		WebElement appealsNDisp = new WebDriverWait(driver, 10)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Appeals & Disputes')]")));
		appealsNDisp.click();
		Thread.sleep(5000);
		Assert.assertEquals(driver.findElement(By.xpath("//strong[normalize-space()='Appeals & Disputes']")).getText(),
				"Appeals & Disputes");
		// extentTest.log(Status.PASS, "Open Appeals & Disputes Link");
		//testStepExtentTest = extentTest.createNode("TSAD001: Open Appeals & Disputes Link")
			//	.log(Status.PASS, "Open Appeals & Disputes Link")
			//	.addScreenCaptureFromPath(captureScreenshot("TSAD001.jpg"));
		extentTest.log(Status.PASS, "TSAD001: Open Appeals & Disputes Link", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSAD001.jpg")).build());
		Thread.sleep(5000);

		// SUBMIT CLAIM DISP*********************************************

		WebElement submitClaim = driver.findElement(By.xpath("//a[normalize-space()='Submit a Claim Dispute']"));
		submitClaim.click();
		// extentTest.log(Status.PASS, "Submit Claim");
		//testStepExtentTest = extentTest.createNode("TSAD002: Submit Claim").log(Status.PASS, "Submit Claim")
				//.addScreenCaptureFromPath(captureScreenshot("TSAD002.jpg"));
		extentTest.log(Status.PASS, "TSAD002: Submit Claim", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSAD002.jpg")).build());
		Thread.sleep(5000);
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		Assert.assertEquals(driver
				.findElement(By.xpath("(//label[normalize-space()='Provider Claims Dispute Form'])[1]")).getText(),
				"Provider Claims Dispute Form");
		// extentTest.log(Status.PASS, "Open Provider Claims Dispute Form from another
		// domain");
		//testStepExtentTest = extentTest.createNode("TSAD003: Open Provider Claims Dispute Form from another domain")
			//	.log(Status.PASS, "Open Provider Claims Dispute Form from another domain")
			//	.addScreenCaptureFromPath(captureScreenshot("TSAD003.jpg"));
		extentTest.log(Status.PASS, "TSAD003: Open Provider Claims Dispute Form from another domain", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSAD003.jpg")).build());
	}

	@Test(priority = 10)
	public void verifyCommunicationCenter() throws InterruptedException {
		Thread.sleep(5000);
		WebElement communiCenter = driver.findElement(By.xpath("//a[contains(text(),'Communication Center')]"));

		try {
			communiCenter.click();
		} catch (StaleElementReferenceException e) {
			communiCenter = driver.findElement(By.xpath("//a[contains(text(),'Communication Center')]"));
			communiCenter.click();
		}
		Assert.assertEquals(driver.findElement(By.xpath("//h1[normalize-space()='Communication Center']")).getText(),
				"Communication Center");
		// extentTest.log(Status.PASS, "Open Communication Center Link");
		//testStepExtentTest = extentTest.createNode("TSCC001: Open Communication Center Link")
				//.log(Status.PASS, "Open Communication Center Link")
				//.addScreenCaptureFromPath(captureScreenshot("TSCC001.jpg"));
		extentTest.log(Status.PASS, "TSCC001: Open Communication Center Link", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSCC001.jpg")).build());
		Thread.sleep(5000);

		// SEND MESSAGE
		WebElement sendBtn = driver.findElement(By.xpath("//button[normalize-space()='Send a Message']"));
		sendBtn.click();
		// extentTest.log(Status.PASS, "Open New Message");
		//testStepExtentTest = extentTest.createNode("TSCC002: Open New Message").log(Status.PASS, "Open New Message")
			//	.addScreenCaptureFromPath(captureScreenshot("TSCC002.jpg"));
		extentTest.log(Status.PASS, "TSCC002: Open New Message", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSCC002.jpg")).build());

		WebElement drpSub = driver.findElement(By.xpath("//select[@name='ProviderSubject']"));
		drpSub.click();
		WebElement claimsInq = driver.findElement(By.xpath("//option[@value='Subject_Claims_Inquiry']"));
		claimsInq.click();
		//testStepExtentTest = extentTest.createNode("TSCC003: Select Subject").log(Status.PASS, "Select Subject")
			//	.addScreenCaptureFromPath(captureScreenshot("TSCC003.jpg"));
		extentTest.log(Status.PASS, "TSCC003: Select Subject", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSCC003.jpg")).build());
		// extentTest.log(Status.PASS, "Select Subject");

		WebElement msgBox = driver.findElement(By.xpath("//textarea"));
		msgBox.sendKeys("Test");
		// extentTest.log(Status.PASS, "Enter Message");
		//testStepExtentTest = extentTest.createNode("TSCC004: Type Message").log(Status.PASS, "Type Message")
			//	.addScreenCaptureFromPath(captureScreenshot("TSCC004.jpg"));
		extentTest.log(Status.PASS, "TSCC004: Type Message", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSCC004.jpg")).build());

		WebElement nextBtn = driver.findElement(By.xpath("//button[normalize-space()='Next']"));
		nextBtn.click();
		// extentTest.log(Status.PASS, "Click Next Button");
		Thread.sleep(5000);

		//testStepExtentTest = extentTest.createNode("TSCC005: Click Next Button and open Upload Attachment Page")
				//.log(Status.PASS, "Click Next Button and open Upload Attachment Page")
				//.addScreenCaptureFromPath(captureScreenshot("TSCC005.jpg"));
		extentTest.log(Status.PASS, "TSCC005: Click Next Button and open Upload Attachment Page", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSCC005.jpg")).build());
		Thread.sleep(5000);

		WebElement nextBtn1 = driver.findElement(By.xpath("//button[normalize-space()='Next']"));
		nextBtn1.click();
		Thread.sleep(5000);
		// extentTest.log(Status.PASS, "Open Upload Attachment Page and Click Next
		// Button");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[contains(text(),'We have captured your information. ')]")).getText(),
				"We have captured your information.");
		// extentTest.log(Status.PASS, "Verify Message Captured");
		//testStepExtentTest = extentTest.createNode("TSCC006: Click Next Button and Verify Message Captured")
				//.log(Status.PASS, "Click Next Button and Verify Message Captured")
				//.addScreenCaptureFromPath(captureScreenshot("TSCC006.jpg"));
		extentTest.log(Status.PASS, "TSCC006: Click Next Button and Verify Message Captured", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSCC006.jpg")).build());
		

		WebElement finishBtn = driver.findElement(By.xpath("//button[normalize-space()='Finish']"));
		finishBtn.click();
		Thread.sleep(5000);
		//testStepExtentTest = extentTest.createNode("TSCC007: Click Finish").log(Status.PASS, "Click Finish")
			//	.addScreenCaptureFromPath(captureScreenshot("TSCC007.jpg"));
		
		extentTest.log(Status.PASS, "TSCC007: Click Finish", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSCC007.jpg")).build());

		Thread.sleep(5000);
		// VERIFY MSG SENT
		WebElement back = driver.findElement(By.xpath("(//span[@class='slds-m-left_xx-small'])[1]"));
		back.click();
		Thread.sleep(5000);
		//testStepExtentTest = extentTest.createNode("TSCC008: Verify Message Sent")
				//.log(Status.PASS, "Verify Message Sent").addScreenCaptureFromPath(captureScreenshot("TSCC008.jpg"));
		extentTest.log(Status.PASS, "TSCC008: Verify Message Sent", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSCC008.jpg")).build());
	}

	@Test(priority = 11)
	public void verifyResources() throws InterruptedException {
		Thread.sleep(5000);
		WebElement resources = driver.findElement(By.xpath("//a[contains(text(),'Resources')]"));

		try {
			resources.click();
		} catch (StaleElementReferenceException e) {
			resources = driver.findElement(By.xpath("//a[contains(text(),'Resources')]"));
			resources.click();
		}
		Thread.sleep(5000);
		Assert.assertEquals(driver.findElement(By.xpath("//strong[normalize-space()='Resources']")).getText(),
				"Resources");
		// extentTest.log(Status.PASS, "Open Resources Link");
		//testStepExtentTest = extentTest.createNode("TSRE001: Open Resources Link")
			//	.log(Status.PASS, "Open Resources Link").addScreenCaptureFromPath(captureScreenshot("TSRE001.jpg"));
		extentTest.log(Status.PASS, "TSRE001: Open Resources Link", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSRE001.jpg")).build());
	
	}

	@Test(priority = 12)
	public void verifyMyAccount() throws InterruptedException {
		Thread.sleep(5000);
		WebElement myAcc = driver.findElement(By.xpath("//a[contains(text(),'My Account')]"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		try {
			myAcc.click();
		} catch (StaleElementReferenceException e) {
			myAcc = driver.findElement(By.xpath("//a[normalize-space()='My Account']"));
			myAcc.click();
		}
		Thread.sleep(5000);
		Assert.assertEquals(driver.findElement(By.xpath("//h1[normalize-space()='Account Information']")).getText(),
				"Account Information");
		// extentTest.log(Status.PASS, "Open My Account Link");
		//testStepExtentTest = extentTest.createNode("TSMA001: Open My Account Link")
			//	.log(Status.PASS, "Open My Account Link").addScreenCaptureFromPath(captureScreenshot("TSMA001.jpg"));
		extentTest.log(Status.PASS, "TSMA001: Open My Account Link", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSMA001.jpg")).build());
		
		// PROVIDER PRAC
		WebElement provPractice = driver.findElement(By.xpath("//span[contains(text(),'Select a Practice')]"));
		provPractice.click();
		Thread.sleep(5000);
		// extentTest.log(Status.PASS, "Open Provider Practice List");
		//testStepExtentTest = extentTest.createNode("TSMA002: Open Provider Practice List")
				//.log(Status.PASS, "Open Provider Practice List")
				//.addScreenCaptureFromPath(captureScreenshot("TSMA002.jpg"));
		extentTest.log(Status.PASS, "TSMA002: Open Provider Practice List", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSMA002.jpg")).build());
		Thread.sleep(5000);

		WebElement internalMed = driver.findElement(By.xpath(
				"(//span[@title='Curtis Cave - Internal Medicine'][normalize-space()='Curtis Cave - Internal Medicine'])[1]"));
		internalMed.click();
		// extentTest.log(Status.PASS, "Select Curtis Cave - Internal Medicine Option");
		//testStepExtentTest = extentTest.createNode("TSMA003: Select Curtis Cave - Internal Medicine Option")
				//.log(Status.PASS, "Select Curtis Cave - Internal Medicine Option")
				//.addScreenCaptureFromPath(captureScreenshot("TSMA003.jpg"));
		extentTest.log(Status.PASS, "TSMA003: Select Curtis Cave - Internal Medicine Option", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSMA003.jpg")).build());
	}

	@Test(priority = 13)
	public void verifyLogOut() throws InterruptedException {
		Thread.sleep(5000);
		WebElement logout = driver.findElement(By.xpath("//div[contains(text(),'Log Out')]"));
		logout.click();
		Thread.sleep(5000);
		// extentTest.log(Status.PASS, "Log out");
		//testStepExtentTest = extentTest.createNode("TSLO001: Log Out").log(Status.PASS, "Log Out")
				//.addScreenCaptureFromPath(captureScreenshot("TSLO001.jpg"));
		
		extentTest.log(Status.PASS, "TSLO001: Log Out", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSLO001.jpg")).build());
	}

	@Test(priority = 14)
	public void verifyTermsOfUse() throws InterruptedException {
		Thread.sleep(5000);
		WebElement termsofuse = driver.findElement(By.xpath("//a[contains(text(),'Terms of Use')]"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		termsofuse.click();

		Thread.sleep(5000);

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		Thread.sleep(5000);
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Terms of Use')]")).getText(),
				"Terms of Use");
		// extentTest.log(Status.PASS, "Open Terms of Use URL from another domain");
		//testStepExtentTest = extentTest.createNode("TSTU001: Open Terms of Use URL from another domain")
				//.log(Status.PASS, "Open Terms of Use URL from another domain")
				//.addScreenCaptureFromPath(captureScreenshot("TSTU001.jpg"));
		extentTest.log(Status.PASS, "TSTU001: Open Terms of Use URL from another domain", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSTU001.jpg")).build());
	}

	@Test(priority = 15)
	public void verifyPrivacyPolicy() throws InterruptedException {
		Thread.sleep(5000);
		WebElement privacyPolicy = driver.findElement(By.xpath("//a[contains(text(),'Privacy Policy')]"));

		try {
			privacyPolicy.click();
		} catch (StaleElementReferenceException e) {
			privacyPolicy = driver.findElement(By.xpath("//a[contains(text(),'Privacy Policy')]"));
			privacyPolicy.click();
		}
		Thread.sleep(5000);
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		Thread.sleep(5000);
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Privacy Policy')]")).getText(),
				"Privacy Policy");
		// extentTest.log(Status.PASS, "Open Privacy Policy URL from another domain");
		//testStepExtentTest = extentTest.createNode("TSPP001: Open Privacy Policy URL from another domain")
				//.log(Status.PASS, "Open Privacy Policy URL from another domain")
				//.addScreenCaptureFromPath(captureScreenshot("TSPP001.jpg"));
		extentTest.log(Status.PASS, "TSPP001: Open Privacy Policy URL from another domain", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSPP001.jpg")).build());
	}

	@Test(priority = 16)
	public void verifyTechSupp() throws InterruptedException {
		Thread.sleep(5000);
		WebElement tectSupp = driver.findElement(By.xpath("//a[contains(text(),'Technical Support')]"));

		try {
			tectSupp.click();
		} catch (StaleElementReferenceException e) {
			tectSupp = driver.findElement(By.xpath("//a[contains(text(),'Technical Support')]"));
			tectSupp.click();
		}
		Thread.sleep(5000);
		// extentTest.log(Status.PASS, "Open Tech Support Link");
		//testStepExtentTest = extentTest.createNode("TSTS001: Open Tech Support Link")
			//	.log(Status.PASS, "Open Tech Support Link").addScreenCaptureFromPath(captureScreenshot("TSTS001.jpg"));
		extentTest.log(Status.PASS, "TSTS001: Open Tech Support Link", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSTS001.jpg")).build());
		// SUPPORT REQUEST
		WebElement subject = driver.findElement(By.xpath("//input[@name='SubjectText']"));
		subject.sendKeys("Test");
		// extentTest.log(Status.PASS, "Enter Subject");
		//testStepExtentTest = extentTest.createNode("TSTS002: Enter Subject").log(Status.PASS, "Enter Subject")
				//.addScreenCaptureFromPath(captureScreenshot("TSTS002.jpg"));
		extentTest.log(Status.PASS, "TSTS002: Enter Subject", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSTS002.jpg")).build());
		
		
		WebElement textBox = driver.findElement(By.xpath("//textarea"));
		textBox.sendKeys("Test Message");
		// extentTest.log(Status.PASS, "Enter Message");
		//testStepExtentTest = extentTest.createNode("TSTS003: Enter Message").log(Status.PASS, "Enter Message")
				//.addScreenCaptureFromPath(captureScreenshot("TSTS003.jpg"));
		extentTest.log(Status.PASS, "TSTS003: Enter Message", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSTS003.jpg")).build());
		
		WebElement nextBtn = driver.findElement(By.xpath("//button[normalize-space()='Next']"));
		nextBtn.click();
		Thread.sleep(5000);
		// extentTest.log(Status.PASS, "Click Next Button and Open Upload Attachments
		// Page");
		//testStepExtentTest = extentTest.createNode("TSTS004: Click Next Button and Open Upload Attachments Page")
			//	.log(Status.PASS, "Click Next Button and Open Upload Attachments Page")
			//	.addScreenCaptureFromPath(captureScreenshot("TSTS004.jpg"));
		extentTest.log(Status.PASS, "TSTS004: Click Next Button and Open Upload Attachments Page", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSTS004.jpg")).build());
		
		Thread.sleep(5000);
		WebElement nextBtn1 = driver.findElement(By.xpath("//button[normalize-space()='Next']"));
		nextBtn1.click();
		Thread.sleep(5000);
		// extentTest.log(Status.PASS, "Click Next Button");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[contains(text(),'We have captured your information. ')]")).getText(),
				"We have captured your information.");
		Thread.sleep(5000);
		// extentTest.log(Status.PASS, "Verify Message Captured and Click Finish");
		//testStepExtentTest = extentTest.createNode("TSTS005: Click Next and Verify Message Captured")
			//	.log(Status.PASS, "Click Next and Verify Message Captured")
			//	.addScreenCaptureFromPath(captureScreenshot("TSTS005.jpg"));
		extentTest.log(Status.PASS, "TSTS005: Click Next and Verify Message Captured", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSTS005.jpg")).build());
		
		
		WebElement finishBtn = driver.findElement(By.xpath("//button[normalize-space()='Finish']"));
		finishBtn.click();
		Thread.sleep(5000);
		//testStepExtentTest = extentTest.createNode("TSTS006: Click Finish Button")
				//.log(Status.PASS, "Click Finish Button").addScreenCaptureFromPath(captureScreenshot("TSTS006.jpg"));
		extentTest.log(Status.PASS, "TSTS006: Click Finish Buttond", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSTS006.jpg")).build());
	
	}

	@Test(priority = 17)
	public void verifyContactUs() throws InterruptedException {
		Thread.sleep(5000);
		WebElement contactUs = driver.findElement(By.xpath("(//a[normalize-space()='Contact Us'])[1]"));
		try {
			contactUs.click();
		} catch (StaleElementReferenceException e) {
			contactUs = driver.findElement(By.xpath("(//a[normalize-space()='Contact Us'])[1]"));
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			contactUs.click();
		}
		Thread.sleep(7000);
		// extentTest.log(Status.PASS, "Click Contact Us Link and Open Communication
		// Center Page");
		//testStepExtentTest = extentTest.createNode("TSCU001: Click Contact Us Link and Open Communication Center Page")
				//.log(Status.PASS, "Click Contact Us Link and Open Communication Center Page")
				//.addScreenCaptureFromPath(captureScreenshot("TSCU001.jpg"));
		
		extentTest.log(Status.PASS, "TSCU001: Click Contact Us Link and Open Communication Center Page", MediaEntityBuilder.
				createScreenCaptureFromPath(captureScreenshot("TSCU001.jpg")).build());
	}

	@AfterTest
	public void close() {
		driver.quit();
	}

}