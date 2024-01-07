package stepdefinations;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.AddNewCustomerPage;
import pageObject.AdminPage;
import pageObject.Vendor;
import utilities.ReadConfig;

public class StepDefination extends Base {

	//It Hookes concepts in BDD cucumber
	//@Before will execute before every scenario
	@Before
	public void setUp() throws Exception {
		System.out.println("Set up method execution");
		
		readConfig=new ReadConfig();//creating object of ReadConfig class
		
		String browser=readConfig.getBrowser();//chrome
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();//launching application on chrome browser
			driver.manage().window().maximize();
			Thread.sleep(2000);
		}else if(browser.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
			Thread.sleep(2000);

			driver.manage().window().maximize();
			Thread.sleep(2000);
		}else if (browser.equalsIgnoreCase("IE")) {
			driver=new InternetExplorerDriver();
			driver.manage().window().maximize();
			Thread.sleep(2000);
		}
		
	}
	
	@Given("User Lanch Chrome Browser")
	public void user_lanch_chrome_browser() {
	
		//driver=new ChromeDriver();
		
		ad=new AdminPage(driver);//create Object of AdminPage java class
		
	}

	@When("User open url {string}")
	public void user_open_url(String url) {
	  driver.get(url);//launch application
	}

	@When("User enter Email as {string} and password as {string}")
	public void user_enter_email_as_and_password_as(String email, String password) {
	
		ad.setUserName(email);
		ad.setPassword(password);
	}

	@When("User click on Login button")
	public void user_click_on_login_button() {
	 
		ad.clickOnLogin();
	}

	@Then("User verify page title should be {string}")
	public void user_verify_page_title_should_be(String tital) {
	   
		Assert.assertEquals(driver.getTitle(), tital);
	}

	@Then("close browser")
	public void close_browser() {
	  driver.close();
	}
	
	//vendors

	
	@When("User click on vendor item")
	public void user_click_on_vendor_item() throws Exception {
		vendor=new Vendor(driver);
	    vendor.clickOnVendors();
	    Thread.sleep(2000);
	}

	@Then("User can view vendor page")
	public void user_can_view_vendor_page() throws Exception {
		   Assert.assertEquals("Vendors / nopCommerce administration",vendor.getPageTitle());
		    Thread.sleep(2000);
	}

	@When("User enter Vendor name as {string} and password as {string}")
	public void user_enter_vendor_name_as_and_password_as(String venName, String venEmail) throws Exception {
		vendor.searchName(venName);
		vendor.searchEmail(venEmail);
	    Thread.sleep(2000);
	}

	@When("User click on Search button")
	public void user_click_on_search_button() throws Exception {
		vendor.clickOnSearchButton();
	    Thread.sleep(2000);
	}
	
	//@After will execute after every scenario
	@After
	public void tearDown(Scenario sc) throws Exception {
		System.out.println("Tear down method execute after every scenario");
		
		if(sc.isFailed()==true) {
		String filePath="C:\\Users\\Prashant\\eclipse-workspace\\07Oct2023CucumberMavenProject\\captureScreenshot\\failedScrenshot.png";
		
		//capture screenshot of failed scenarios
		
		//convert WebDriver object into TakeScrenshot
		TakesScreenshot scrShot=(TakesScreenshot)driver;
		
		//call getScreenshotAs()
		
		File scrFile=scrShot.getScreenshotAs(OutputType.FILE);
		
		File destFile=new File(filePath);
		
		FileUtils.copyFile(scrFile, destFile);
		
		Thread.sleep(2000);
		}	
	
		driver.quit();
	}
}
