package Tajikistan.Tajikistan;
import static org.junit.Assert.*;
import static SettingFiles.Tajikistan_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import static Tajikistan.Tajikistan.BOHost_accountCreationOnly.*;
import static Tajikistan.Tajikistan.BOHost_accountCreationwithVehicle.*;
import org.openqa.selenium.support.ui.Select;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BOHost_accountCreationwithVehicleandTag{
	private String testNameSelected = this.getClass().getSimpleName();
	
	@Before
	public void setup() throws Exception{
			setUp();
	}
	
	@After
	public void teardown() throws Exception{
			tearDown();
	}
	
	
	@Test
	public void accountCreationAssigningTagInit() throws Exception{
		configurationSection("Host",testNameSelected,testNameSelected);		
		testPlanReading(4,0,2,3);			
		Thread.sleep(1000);
		optionVehicle = "CreateVehicle";
		borrarArchivosTemp("E:\\Selenium\\"+projectNamePath+testClassName+"\\attachments\\");
		accountModeTaj = "New";
		//Steps from 1-to 7 
		accountCreationOnly();		
				
		Thread.sleep(3000);
		optionVehicle = "CreateVehicle";
		//Steps from 8 to 14
		vehicleCreation();
		Thread.sleep(1000);
		accountCreationAssigningTag();
		if (errorTagAssignment){
			actualResults.set(18, "Unable to assign Tag to Vehicle with Plate No.: "+matriNu+" becuase of "+errorMessage);
			Thread.sleep(1000);
			driver.close();
			testLink();
			System.out.println("Unable to assign Tag to Vehicle with Plate No.: "+matriNu+" becuase of "+errorMessage);
			System.out.println("Tested in Version of: "+ getVersion(applevelTested));			
			return;
		}
		takeScreenShot("E:\\Selenium\\","accountCreated"+timet+".jpeg");
		takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\","accountCreated.jpeg");				
		actualResults.set(18, "Account has been created: "+accountNumbr+" with Vehicle Plate "+matriNu+" and Assigned Tag No.: "+ tagIdNmbr);
		Thread.sleep(1000);
		driver.close();
		testLink();
		System.out.println("Account has been created: "+accountNumbr+" with Vehicle Plate "+matriNu+" and Assigned Tag No.: "+ tagIdNmbr);		
		System.out.println("Tested in Version of: "+ getVersion(applevelTested));
	}
	
	public static void accountCreationAssigningTag() throws Exception{		
		Thread.sleep(2000);
		try {
			takeScreenShot("E:\\Selenium\\","tagAssignmentMainPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentMainPage.jpeg");
			WebElement paymentType = new Select (driver.findElement(By.id("ctl00_ContentZone_type_payment_cmb_dropdown"))).getFirstSelectedOption();
			String paymentyp = paymentType.getText();
			
			//Step 15.- Once Account is saved with just created Vehicle, click on either Electronic Toll button for Tag Assignment or Smart cards button for Smart Card assigment
			if (paymentyp.equals("Smart Card")){
				elementClick("ctl00_ContentZone_BtnSmarts");
			}else{
				elementClick("ctl00_ContentZone_BtnTags");
			}
			Thread.sleep(500);
			
			//Step 16.- Click on just created vehicle
			elementClick("ctl00_ContentZone_chk_0");
			Thread.sleep(500);
			
			//Step 17.- Click on Assignment button
			elementClick("ctl00_ContentZone_btn_token_assignment");
			Thread.sleep(500);
			
			//Step 18.- Type a valid PAN into PAN field 
			SendKeys("ctl00_ContentZone_txt_pan_token_txt_token_box_data",String.valueOf(ranNumbr(1,99999)));
			Thread.sleep(500);
			
			//Step 19.- Click on Assign button
			elementClick("ctl00_ContentZone_btn_init_tag");
			Thread.sleep(500);
			confirmationMessage = getText("ctl00_ContentZone_lbl_operation");
			confirmationMessage2 = getText("ctl00_ContentZone_lbl_information");
			if (confirmationMessage.contains("Error: This token is already assigned") || confirmationMessage.contains("Este tag no está operativo") || confirmationMessage.contains("Este tag ya está asignado") || confirmationMessage.contains("Luhn incorrecto")){
					errorMessage = getText("ctl00_ContentZone_lbl_operation");
					errorTagAssignment = true;
					takeScreenShot("E:\\Selenium\\","tagAssignmentPageErr"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPageErr.jpeg");
					return;
				}
				if (confirmationMessage2.contains("has already an assigned token")){
					errorMessage = getText("ctl00_ContentZone_lbl_information");
					errorTagAssignment = true;
					takeScreenShot("E:\\Selenium\\","tagAssignmentPageErr"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPageErr.jpeg");
					return;
				}
				tagIdNmbr = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr[2]/td[6]");
				takeScreenShot("E:\\Selenium\\","tagAssignmentPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPage.jpeg");
				Thread.sleep(1000);
				
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
	}
	
}
