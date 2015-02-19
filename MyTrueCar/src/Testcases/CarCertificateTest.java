package Testcases;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Testcases.BaseTest;


public class CarCertificateTest extends BaseTest {
	
	/*run a test on homepage to select Make and Model of the Car and
	Veirfy the success of the sleection from Next page*/
	
	@Test
	public void selectCar(){
		getElement("Make_button").click();
		getElement("Make_SelectList").click();
		getElement("Model_button").click();
		getElement("Model_SelectList").click();
		String zipcode = getElement("Home_zipcode").getText();
		if (zipcode.equals(null)){	
			getElement("Home_zipcode").sendKeys("91403");	
		}
		getElement("Home_gobutton").click();
		String selectcarsuccess = getElement("Selectcar_success").getText();
		if (selectcarsuccess != null){
			Assert.assertEquals("2015 Ford Expedition",selectcarsuccess);
		}
	
	}
	
	
	/*test is depended on selection of the car , it will only run if selection of the car is done successfully. 
	once car is selected it select the styles and procced to registartion.*/
	
	@Test(dependsOnMethods ={"selectCar"})
	public void selectPreferences(){
		getElement("style_button").click();
		getElement("style_list").click();
		getElement("Next_button").click();
		String registrationpagetitle = driver.getTitle();
		if ( registrationpagetitle != null){
			Assert.assertEquals("Autoblog | Registration",registrationpagetitle);
		}

	}
	
	// registration test with data provider
	@Test(dependsOnMethods ={"selectPreferences"}, dataProvider = "testData")
	public static void registration(String firstName,String lastName, String address,String city, String state,String zipcode,String phone,String email) throws InterruptedException{
		getElement("FristName_input").sendKeys(firstName);
		getElement("lstnm_input").sendKeys(lastName);
		getElement("reg_address").sendKeys(address);
		//checking to verify if auto populated filed has value and only send data if not present.
		String city_element = getElement("reg_city").getText();
		if (city_element.equals(null)){
			getElement("reg_city").sendKeys(city);
		}
		String state_element= getElement("reg_state").getText();
		if (state_element.equals(null)){
			getElement("reg_state").sendKeys(state);
		}
		String zipcode_element = getElement("reg_Zipcode").getText();
		if (zipcode_element.equals(null)){
			getElement("reg_Zipcode").sendKeys(zipcode);
		}
		getElement("reg_phone").sendKeys(phone);
		getElement("reg_email").sendKeys("eshah@testtruecar.com");
		getElement("reg_button").click();
		Thread.sleep(3000);
		//verify registartion is successfull. Can add more verification based on each input field in the form
		String registrationsuccess = driver.getTitle();
		Assert.assertEquals("Autoblog | Dealer Selection", registrationsuccess);
		
		
	}
	
	// test data for registartion 
	/* can add more validations and different test data to validate negative test scenarios*/
	@DataProvider
	public Object[][] testData(){
		Object[][] data = new Object[1][8];
		data[0][0] = "XXXXXXX";
		data[0][1] = "ZZZZZZZ";
		data[0][2] = "4706 kester ave";
		data[0][3] = "Sherman";
		data[0][4] = "CA";
		data[0][5] = "91403";
		data[0][6] = "2134225746";
		data[0][7] = "eshah+test@truecar.com";
	
		return data;
	}
	
	@Test(dependsOnMethods ={"registration"})
	public void verifydealersPage(){
		//verify right selection of car and if atleast one dealer is present.
		String carmodel = getElement("carmodel_text").getText();
		String cartrim = getElement("cartrim_text").getText();
		System.out.println("Car Selected is"+ carmodel + "and Car trim is :" + cartrim);
		Assert.assertEquals("2015 Ford Expedition", carmodel);
		Assert.assertEquals("2WD King Ranch", cartrim);
		
		// get counts of avalibale dealer for the selection and Completing Test if no dealer is avaliable.
		List<WebElement> dealerlist = driver.findElements(By.xpath(locators.getProperty("dealer_boxes")));
		System.out.println("Total Dealer present:" + dealerlist.size());
		if(dealerlist.size() == 0){
			System.out.println("No Dealer Found");
			Assert.fail("As no dealer is found test is completed here");
		}
	}
	
	@Test(dependsOnMethods ={"verifydealersPage"})
	public void getcertificatefromdealer(){
		List<WebElement> checkboxes = driver.findElements(By.xpath(locators.getProperty("certificate_check_boxes")));
		for ( WebElement checkbox : checkboxes ) {
		    if ( !checkbox.isSelected() ) {
		        checkbox.click();
		    }
		}
		getElement("Next_button_certificate").click();
		//asserting it gets to the certifcate page
		String certifcatepage= driver.getTitle();
		Assert.assertEquals("Autoblog | Certificate",  certifcatepage);		
	}
	
	@AfterSuite
	public void teardown(){
		driver.quit();
	}
	
}
