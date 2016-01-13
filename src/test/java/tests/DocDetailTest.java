package tests;

import omelet.data.IProperty;
import omelet.data.driverconf.IBrowserConf;
import omelet.exception.FrameworkException;
import omelet.testng.support.SAssert;

import org.testng.annotations.Test;

import pageobjects.DataEnum;
import pageobjects.PageObjectFactory;


public class DocDetailTest {
	
	SAssert sassert = new SAssert();

	@Test(description = "verify if more button in Doc detials page is working as expected", enabled = true,dataProvider="XmlData")
	public void verifyMoreButtonFunctionality(IBrowserConf browserConf, IProperty prop) throws InterruptedException {
		PageObjectFactory pof = new PageObjectFactory(browserConf, prop);
		
		pof.landingPage().load()
		.isLoaded()
		.selectCity(prop.getValue(DataEnum.LandingPage_country))
		.searchSpeciality(prop.getValue(DataEnum.LandingPage_speciality));
		
		pof.docListPage().isLoaded().selectDoc(prop.getValue(DataEnum.Doclist_name));
		pof.docDetails().isLoaded().verifyMoreButton();
		sassert.assertAll();
	}
	
	@Test(description = "verify if more button in Doc detials page is working as expected", enabled = true,dataProvider="XmlData")
	public void sampleTestCaseToShowCaseScreenShot(IBrowserConf browserConf, IProperty prop) throws InterruptedException {
		PageObjectFactory pof = new PageObjectFactory(browserConf, prop);
		
		pof.landingPage().load()
		.isLoaded()
		.selectCity(prop.getValue(DataEnum.LandingPage_country))
		.searchSpeciality(prop.getValue(DataEnum.LandingPage_speciality));
		sassert.assertTrue(false,"Intentional failure to show screen short for assertion");
		sassert.assertAll();
	}
	

}
