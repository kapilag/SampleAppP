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
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DocDetails {
	
	@FindBy(id = "reviewsNavLink")
	private WebElement feedback;
	@FindBy(css = ".give-feedback-button")
	private WebElement giveFeedback;
	@FindBys(@FindBy(css = ".doctor-review-card"))
	private List<WebElement> feedbackList;
	@FindBy(css = ".btn.btn-white.next-page.recommended-next-page")
	private WebElement moreButton;
	@FindBy(css = ".recommended-reviews .spinner")
	private WebElement spinner;
	private WebDriver driver;
	private IProperty prop;
	SAssert sassert = new SAssert();
	
	public DocDetails(WebDriver driver, IProperty prop){
		this.driver = driver;
		this.prop = prop;
		PageFactory.initElements(driver, this);
	}
	
	public DocDetails isLoaded(){
		if(null == DriverUtility.waitFor(ExpectedConditionExtended.elementsToBeClickable(giveFeedback), driver, 30)){
			throw new FrameworkException("Doc details page is not loaded in 30 seconds!");
		}
		return this;
	}
	
	
	public DocDetails verifyMoreButton(){
		feedback.click();
		if(null == DriverUtility.waitFor(ExpectedConditions.visibilityOf(moreButton), driver, 2)){
			sassert.assertFalse(true,"There seems to no more button , hence fix your test data, or test case!");
		}
		//list of the feedback before loading more button
		int initialFeedbackCount = feedbackList.size();
		//Click on the more button and wait for 5 seconds for feedback to be loaded
		moreButton.click();
		//wait for spinner to disappear
		if(null == DriverUtility.waitFor(ExpectedConditionExtended.invisibilityOfElementLocated(spinner), driver, 5)){
			sassert.assertTrue(false,"Are review loaded in 5 seconds after clicking on More button?");
		}
		int feedbackCountAfterLoad = feedbackList.size();
		//count of reviews should not be equal
		//I am not using assernotEqual , as just now found bug in this 
		if(initialFeedbackCount == feedbackCountAfterLoad){
			sassert.assertTrue(false,"Seems more button doesnot change the count of feedback!");
		}else{
			sassert.assertTrue(true,"Hurray! more button functionality is working as feedbacks are showing on clicking of button!");
		}
		return this;
	}
	
	
	
	

}
