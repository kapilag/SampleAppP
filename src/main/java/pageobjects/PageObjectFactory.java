package pageobjects;

import org.openqa.selenium.WebDriver;

import omelet.data.IProperty;
import omelet.data.driverconf.IBrowserConf;
import omelet.driver.Driver;

public class PageObjectFactory {
	
	private LandingPage landingPage;
	private DocDetails docDetails;
	private DocListPage docListPage;
	
	private WebDriver driver;
	private IProperty prop;
	
	public PageObjectFactory(IBrowserConf browserConf, IProperty prop){
		driver = Driver.getDriver(browserConf);
		this.prop = prop;
	}
	
	public LandingPage landingPage(){
		if(null == landingPage){
			landingPage = new LandingPage(driver, prop);
		}
		return landingPage;
	}
	
	public DocListPage docListPage(){
		if( null == docListPage){
			docListPage = new DocListPage(driver, prop);
		}
		return docListPage;
	}
	
	public DocDetails docDetails(){
		if(null == docDetails){
			docDetails = new DocDetails(driver, prop);
		}
		return docDetails;
	}
}
