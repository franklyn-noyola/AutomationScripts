package Tajikistan.Tajikistan;
import static org.junit.Assert.*;
import static SettingFiles.Tajikistan_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import static Tajikistan.Tajikistan.BOHost_accountCreationOnly.*;
import org.openqa.selenium.support.ui.Select;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class BOHost_accountCreationwithVehicle{
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
	public void vehicleCreatoinOnlyInit() throws Exception{
		configurationSection("Host",testNameSelected,testNameSelected);		
		testPlanReading(3,0,2,3);			
		Thread.sleep(1000);
		optionVehicle = "CreateVehicle";
		accountModeTaj = "New";
		//Steps from 1 to 7
		accountCreationOnly();		
		
		if (vehicleErr) {
			actualResults.set(11, "Unable to create an Vehicle with Plate No. "+matriNu+" in Account: "+accountNumbr+" due to: "+errorText);
			for (int i=12;i<actualResults.size();i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}
			driver.close();
			testLink();
			System.out.println("Unable to create an Vehicle with Plate No. "+matriNu+" in Account: "+accountNumbr+" due to: "+errorText);
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			return;					
		}
		
		Thread.sleep(1000);					
		vehicleCreation();
		Thread.sleep(1000);		
		takeScreenShot("E:\\Selenium\\","accountCreated"+timet+".jpeg");
		takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountCreated.jpeg");		
		actualResults.set(13, "Account created: "+accountNumbr+" correctly and vehicle with Plate No.: "+matriNu);
		driver.close();
		Thread.sleep(1000);
		testLink();
		System.out.println("Account created: "+accountNumbr+" correctly and vehicle with Plate No.: "+matriNu);		
		System.out.println("Tested in Version of: "+ getVersion(applevelTested));
		
	}
	
	public static void vehicleCreation() throws Exception{
		Thread.sleep(1000);
		borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
		try {
			
			if (optionVehicle.equals("CreateVehicle")){
				//Step 8.- After account is created, click on Edit button
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button"); //Click on Edit button
				Thread.sleep(2000);
				
				//Step 9.- Click on Vehicle button
				elementClick("ctl00_ContentZone_BtnVehicles");
				Thread.sleep(3000);
				takeScreenShot("E:\\Selenium\\","vehiclePage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehiclePage.jpeg");
				
				//Step 10.- Click on Create button
				elementClick("ctl00_ContentZone_BtnCreate");
				Thread.sleep(3000);
			}
			if (MPSTDFlow==true) {			
				new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_vehicle_type"))).selectByIndex(catSelected);			
			}else {
				Select optionsCat = new Select (driver.findElement(By.id("ctl00_ContentZone_cmb_vehicle_type")));		
				int catSelected = ranNumbr(0,optionsCat.getOptions().size()-1);
				if (catSelected >=optionsCat.getOptions().size()){
					catSelected =optionsCat.getOptions().size()-1;
				}
				
				new Select (driver.findElement(By.id("ctl00_ContentZone_cmb_vehicle_type"))).selectByIndex(catSelected);
				Thread.sleep(1000);			
			}
			
			//Step 11.- Fill all the data accordingly (mandatory or not) 
			int matNum = ranNumbr(5000,9999);
			int matlet = ranNumbr(0,matletT.length()-1);
			int matlet1 = ranNumbr(0,matletT.length()-1);
			int matlet2 = ranNumbr(0,matletT.length()-1);
			matriNu = String.valueOf(matNum+matletT.substring(matlet, matlet+1)+matletT.substring(matlet1, matlet1+1)+matletT.substring(matlet2, matlet2+1));
			Thread.sleep(1000);
			if (catSelected==0){
				carSel = ranNumbr(0,3);
				carModel = ranNumbr(1,2);
				if (cocheModels[0][carSel].equals("Seat")){
					carModelSel = 0;
				}
				if (cocheModels[0][carSel].equals("Volkswagen")){
					carModelSel = 1;
				}
				if (cocheModels[0][carSel].equals("Ford")){
					carModelSel = 2;
				}
				if (cocheModels[0][carSel].equals("Fiat")){
					carModelSel = 3;
				}
				vehtypeModel = String.valueOf(cocheModels[0][carSel]);
				vehtypeKind = String.valueOf(cocheModels[carModel][carModelSel]);	
			}
			else{
				carSel = ranNumbr(0,1);
				carModel = ranNumbr(1,2);
					if (camionModels[0][carSel].equals("Mercedes-Benz")){
						carModelSel = 0;
					}
					if (camionModels[0][carSel].equals("Scania")){
						carModelSel = 1;
					}
					vehtypeModel = String.valueOf(camionModels[0][carSel]);
					vehtypeKind = String.valueOf(camionModels[carModel][carModelSel]);
			}
			vehicleFieldsfill(matriNu,vehtypeModel,vehtypeKind,colorS[ranNumbr(0,colorS.length-1)]);
			Thread.sleep(1000);
			if (MPSTDFlow==true) {
				if (SDTOption==true) {
					elementClick("ctl00_ContentZone_discount_1");
				}
				if (MPOption=true) {
					elementClick("ctl00_ContentZone_discount_2");
				}
				Thread.sleep(3000);
			}
			
			if (optionVehicle.equals("ModifyVehicle")){
				SendKeys("ctl00_ContentZone_txt_comment", "This Vehicle has been modified");
			}else if (optionVehicle.equals("CreateVehicle") || MPSTDFlow) {		
				SendKeys("ctl00_ContentZone_txt_comment", "New Vehicle Created");		
			}
			takeScreenShot("E:\\Selenium\\","vehicleDataFilledPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\","vehicleDataFilledPage.jpeg");
			Thread.sleep(2000);					
			
			//Step 12.- Click on Submit button
			elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
			Thread.sleep(3000);
			String titleValidation = getText("ctl00_SectionZone_LblTitle");
			if (titleValidation.contains("Account vehicle")) {
				errorText = getText("ctl00_LblError");
				vehicleErr = true;
				return;
			}
			
			//Step 13.- Click on Back button
			elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
			Thread.sleep(3000);
			
			//Step 14.- Click on Save button
			elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
			Thread.sleep(2500);
			sectionTitle = getText("ctl00_SectionZone_LblTitle");
			if (sectionTitle.contains("Payment details") ){
				elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
				Thread.sleep(2500);
				takeScreenShot("E:\\Selenium\\","paymentCustomerPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","paymentCustomer.jpeg");
				Thread.sleep(1000);			
				if (ranNumbr(0,1)==0){
					elementClick("ctl00_ContentZone_CtType_radioButtonList_payBy_0");
					paymentType = "Cash";
				}else{
					elementClick("ctl00_ContentZone_CtType_radioButtonList_payBy_1");
					paymentType="Cheque";
				}
				if (ranNumbr(0,1)==1){
					elementClick("ctl00_ContentZone_CtType_chk_present");
				}
				Thread.sleep(1000);
				elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
				Thread.sleep(3000);		
				if (paymentType.equals("Cheque")){
					SendKeys("ctl00_ContentZone_CtbyCheque_txt_number_box_data", ranNumbr(10000,999999999)+"");
					
				}
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","paymentCustomerPayedPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","paymentCustomerPayed.jpeg");
				elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
				Thread.sleep(3000);
				sectionTitle = getText("ctl00_SectionZone_LblTitle");
				if (sectionTitle.contains("Receipt")){
					takeScreenShot("E:\\Selenium\\","ReceiptPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","Receipt.jpeg");
					elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
	}
	
	public static void vehicleFieldsfill(String Matricul, String vehtypeM, String vehtypeK, String ColorT) throws Exception{
			Thread.sleep(1000);
			SendKeys("ctl00_ContentZone_text_plate_number",Matricul);
			SendKeys("ctl00_ContentZone_text_make",vehtypeM);
			SendKeys("ctl00_ContentZone_text_model",vehtypeK);
			SendKeys("ctl00_ContentZone_text_colour",ColorT);			
	}

}