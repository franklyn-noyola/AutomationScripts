package Tajikistan.Tajikistan;
import static SettingFiles.Tajikistan_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import static Tajikistan.Tajikistan.BOHost_accountCreationOnly.*;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;




public class BOHost_accountCreationwithAuthorisedPerson{
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
	public void accountCreationwithAuthorisedPerson() throws Exception{
		try {
			accountModeTaj = "New";
			configurationSection("Host",testNameSelected,testNameSelected);		
			testPlanReading(2,0,2,3);
			testLinkTestCase=testNameSelected;	
			borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
		
			//Steps from 1 to 7		
			accountCreationOnly();
			Thread.sleep(1000);
			int selOp2 = ranNumbr(0,9);
			while (selOp == selOp2){
				selOp = ranNumbr (0,9);
				selOp = ranNumbr (0,9);
			}
		
			//Step 8.- After account is created, click on Edit button
			elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
			Thread.sleep(1000);
			
			//Step 9.- Click on Auth. Persons button
			elementClick("ctl00_ContentZone_BtnAuthPers");
			takeScreenShot("E:\\Selenium\\","AuthorisedPerdonPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\Tajikistan\\"+projectNamePath+testClassName+"\\attachments\\","AuthorisedPersonPage.jpeg");
			Thread.sleep(1000);
			
			//Step 10.- Click on Create button
			elementClick("ctl00_ContentZone_BtnCreate");
		
			//Step 11.- Fill all the data accordingly (mandatory or not)
			SendKeys("ctl00_ContentZone_tbAuthPersTitle", sexSelcEn[selOp2]);
			Thread.sleep(500);
			SendKeys("ctl00_ContentZone_tbForename", nameOp[selOp2]);
			Thread.sleep(500);
			SendKeys("ctl00_ContentZone_tbSurname", lastNameOp[selOp2]);
			Thread.sleep(500);
			SendKeys("ctl00_ContentZone_tbRelationship", "Work Collage");
			Thread.sleep(500);
			takeScreenShot("E:\\Selenium\\","AuthorisedPerdonFilledPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\Tajikistan\\"+projectNamePath+testClassName+"\\attachments\\","AuthorisedPersonFilledPage.jpeg");
			
			//Step 12.- Click on submit button
			elementClick("ctl00_ContentZone_BtnSubmit");
			Thread.sleep(1000);
			takeScreenShot("E:\\Selenium\\","AuthorisedPerdonCreatedPage"+timet+".jepg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","AuthorisedPersonCreatedPage.jpeg");
			
			//Step 13.- Click on Back Button
			elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
			Thread.sleep(1000);
			
			//Step 14.- Click on Save Button
			elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");						
			Thread.sleep(1000);
			takeScreenShot("E:\\Selenium\\","AccountAuthorisedPerdonCreatedPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\Tajikistan\\"+projectNamePath+testClassName+"\\attachments\\","AccountAuthorisedPersonCreatedPage.jpeg");
			Thread.sleep(3000);
			actualResults.set(13, "Account created "+accountSelected+" "+accountNumbr+" with Authorised Person correctly");
			driver.close();
			testLink();;
			System.out.println("Account created "+accountSelected+" "+accountNumbr+" with Authorised Person correctly");
			System.out.println("Tested in Version of: "+ getVersion(applevelTested));  
			Thread.sleep(1000);		
		}catch (Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
	}	

}