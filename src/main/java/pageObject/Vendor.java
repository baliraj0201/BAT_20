package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Vendor {

	public WebDriver ldriver;

	public Vendor (WebDriver rdriver) {
		 ldriver=rdriver;
		 PageFactory.initElements(rdriver,this);		
	}
	
	//web element on vendor page
	
	@FindBy(xpath = "//p[text()=' Vendors']")
    WebElement linkVendors;
	
	@FindBy(xpath = "//input[@id='SearchName']")
	WebElement searchVendorName;
	
	@FindBy(xpath = "//input[@id='SearchEmail']")
	WebElement searchVendorEmail;
	
	@FindBy(xpath = "//button[@id='search-vendors']")
	WebElement btnSearch;
	
	//methods
	
			public String getPageTitle() {
				return ldriver.getTitle();
			}
			
			public void clickOnVendors() {
				linkVendors.click();
			}
			
			public void searchEmail(String vemail) {
				searchVendorEmail.sendKeys(vemail);
			}
				
			public void searchName(String vname) {
				searchVendorName.sendKeys(vname);
			}
			
			
			public void clickOnSearchButton() {
				btnSearch.click();
			}
}
