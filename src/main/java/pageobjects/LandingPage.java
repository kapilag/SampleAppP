package pageobjects;

import java.util.List;

import omelet.common.ExpectedConditionExtended;
import omelet.data.IProperty;
import omelet.driver.DriverUtility;
import omelet.exception.FrameworkException;
import omelet.testng.support.SAssert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	@FindBy(id = "consult-city")
	private WebElement city;
	@FindBy(css = ".nav-login-link.consult-navbar-link.homeLoginLink.nav-link")
	private WebElement consultOption;
	@FindBys(@FindBy(css = ".city-dropdown-top-city-row a"))
	private List<WebElement> cityOptions;
	@FindBy(className = "location-placeholder")
	private WebElement locationSelected;
	@FindBy(id = "keywordsInput")
	private WebElement searchBox;
	@FindBy(css = ".search-button")
	private WebElement searchButton;
	@FindBy(xpath = "//*[@class='ui-menu-item'][1]")
	private WebElement firstElementSpeciality;
	private WebDriver driver;
	private IProperty prop;
	SAssert sassert = new SAssert();
	
	public LandingPage(WebDriver driver, IProperty prop){
		this.driver = driver;
		this.prop = prop;
		PageFactory.initElements(driver, this);
	}
	
	public LandingPage load(){
		driver.get(prop.getValue(DataEnum.LandingPage_url));
		sassert.assertTrue(true,"Navigate to:"+prop.getValue(DataEnum.LandingPage_url));
		return this;
	}
	
	public LandingPage isLoaded(){
		if(null == DriverUtility.waitFor(ExpectedConditionExtended.elementToBeClickable(consultOption), driver, 10)){
			throw new FrameworkException("Practo home page is not loaded in 30 seconds!");
		}	
		return this;
	}
	
	public LandingPage selectCity(String cityName){
		city.click();
		clickOnCity(cityName);
		sassert.assertEquals(city.getText().trim().toLowerCase(), cityName.toLowerCase(),"Change the country to:"+cityName);
		return this;
	}
	
	public LandingPage searchSpeciality(String speciality){
		searchBox.sendKeys(speciality);
		firstElementSpeciality.click();
		sassert.assertTrue(true,"Search for the speciality:"+speciality+" in the search box");
		return this;
	}
	
	private boolean clickOnCity(String cityName){
		for(WebElement city: cityOptions){
			if(cityName.equalsIgnoreCase(city.getText().trim())){
				city.click();
				return true;
			}
		}
		throw new FrameworkException("City:"+cityName+" is not present in drop down, Please check Screen shots");
	}

}
