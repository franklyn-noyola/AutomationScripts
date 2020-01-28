package CoviHonduras.CoviHonduras;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static SettingFiles.CoviHonduras_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import static CoviHonduras.CoviHonduras.CAC_accountCreationOnly.*;

public class CAC_accountCreationWithVehicle{
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
			public void accountCreationWithVehicleInit() throws Exception {
				configurationSection("CAC",testNameSelected,testNameSelected);				
				testPlanReading(2,0,2,3);
				accountCreation();
				Thread.sleep(1000);
		
				//Paso 6.- Hacer Click en el botón Editar
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button"); 
				Thread.sleep(2000);
				accountCreationWithVehicle();
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","accountCreated"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountCreated.jpeg");
				actualResults.set(10, "Se ha creado la cuenta: "+accountNumbr+" correctamente y con el vehículo creado con la matricula: "+matriNu);
				driver.close();
				testLink();
				System.out.println("Se ha creado la cuenta: "+accountNumbr+" correctamente y con el vehículo creado con la matricula: "+matriNu);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
		
			}

			public static void accountCreationWithVehicle() throws Exception {
				try {
					//Paso 7.- Hacer Click en el botón Vehículos
					elementClick("ctl00_ContentZone_BtnVehicles");
					Thread.sleep(1000);		
					takeScreenShot("E:\\Selenium\\","vehiclePage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehiclePage.jpeg");
				
					//Paso 8.- Hacer click en el botón Crear
					elementClick("ctl00_ContentZone_BtnCreate");
					Thread.sleep(1000);
		
					//Paso 9.- Llenar todos los campos correspondientes
					int matNum = ranNumbr(5000,9999);
					int matlet = ranNumbr(0,matletT.length()-1);
					int matlet1 = ranNumbr(0,matletT.length()-1);
					int matlet2 = ranNumbr(0,matletT.length()-1);
					matriNu = String.valueOf(matNum+matletT.substring(matlet, matlet+1)+matletT.substring(matlet1, matlet1+1)+matletT.substring(matlet2, matlet2+1));
					selectDropDown("ctl00_ContentZone_cmb_vehicle_type");
					Thread.sleep(1000);
					WebElement vehtype = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_vehicle_type"))).getFirstSelectedOption();
					String  vehtypeT = vehtype.getText();
					if (vehtypeT.equals("Liviano")){
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
					takeScreenShot("E:\\Selenium\\","vehicleDataFilledPage"+timet+".jpeg");				
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehicleDataFilledPage.jpeg");				
					Thread.sleep(2000);							
					
					//Paso 10.- Hacer click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					Thread.sleep(1500);
					
					//Paso 11.- Hacer click en el botón Back y hacer Click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
					Thread.sleep(1500);
					elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
					Thread.sleep(2500);
					pageSelected = "Detalles de Pago";
					if (driver.getPageSource().contains(pageSelected)) {
						paymentfromCustomerEsp();
					}else {
						pageSelectedErr(4);
						if (pageSelectedErr==true) {
							driver.close();
							testLink();
							System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
							fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						}
					}
				}catch(Exception e){
					e.printStackTrace();
					System.err.println(e.getMessage());
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