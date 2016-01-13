package pageobjects;

import java.util.List;

import omelet.data.IProperty;
import omelet.driver.DriverUtility;
import omelet.exception.FrameworkException;
import omelet.testng.support.SAssert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * List page for doctors and functions around it will here!
 * @author kapil
 *
 */
public class DocListPage {
	
	@FindBy(id = "resultsBar")
	private WebElement resultBar;
	@FindBys(@FindBy(css = ".link.doc-name.smokeliftDoctorLink"))
	private List<WebElement> doclist;
	SAssert sassert = new SAssert();
	
	private WebDriver driver;
	private IProperty prop;
	public DocListPage(WebDriver driver, IProperty prop){
		this.driver = driver;
		this.prop = prop;
		PageFactory.initElements(driver, this);
	}
	
	public DocListPage isLoaded(){
		if(null == DriverUtility.waitFor(ExpectedConditions.elementToBeClickable(resultBar), driver, 30)){
			throw new FrameworkException(this.toString()+" page is not loaded in 30 seconds!");
		}
		return this;
	}
	
	public DocListPage selectDoc(String docName){
		for(WebElement doc: doclist){
			if(docName.equalsIgnoreCase(doc.findElement(By.tagName("h2")).getText().trim())){
				doc.click();
				sassert.assertTrue(true,"Click on the doc name:"+docName+" to view the details");
				return this;
			}
		}		
		throw new FrameworkException("Doctor with name:"+docName+" is not present in the search List!, check screen shot");
	}
	
	
	
	

}
