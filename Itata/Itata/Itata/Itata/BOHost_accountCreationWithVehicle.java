package Itata;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static SettingFiles.Generic_Settingsfields_File.*;
import static SettingFiles.Itata_Settingsfields_File.*;
import static Itata.BOHost_accountCreationOnly.*;
import static TestLink.TestLinkExecution.*;

public class BOHost_accountCreationWithVehicle{	
			public String testNameSelected = this.getClass().getSimpleName();
	
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
				configurationSection("Host",testNameSelected,testNameSelected);			
				testPlanReading(2,0,2,3);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");				
				//Paso del 1 al 6
				creaciondeCuenta();			
				Thread.sleep(1000);
				
				//Paso 7.- Hacer Click en el botón Editar
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				accountCreationWithVehicle();
				Thread.sleep(500);
				if (noVehAllowed==true) {
					actualResults.set(10, "No se puede crear Vehiculo debido a: "+errorText);					
					actualResults.set(11, "N/A");
					executionResults.set(11, "");
					driver.close();
					testLink();
					System.out.println("No se puede crear Vehiculo debido a: "+errorText);					
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;						
				}
				takeScreenShot("E:\\Selenium\\","VehiclewithAccountaccountCreated"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\Itata\\BOHost_accountCreationWithVehicle\\attachments\\","VehiclewithAccountaccountCreated.jpeg");
				actualResults.set(11, "Se ha creado la cuenta: "+accountNumbr+" correctamente y el vehículo con la matricula: "+matriNu);
				Thread.sleep(500);
				driver.close();
				testLink();
				System.out.println("Se ha creado la cuenta: "+accountNumbr+" correctamente y el vehículo con la matricula: "+matriNu);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			}

			public static void accountCreationWithVehicle() throws Exception {
				try {
					
					//Paso 8.- Hacer Click en el botón Vehículos
					elementClick("ctl00_ContentZone_BtnVehicles");
					Thread.sleep(1000);		
					takeScreenShot("E:\\Selenium\\","vehiclePage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehiclePage.jpeg");
					
					//Paso 9.- Hacer click en el botón Crear
					elementClick("ctl00_ContentZone_BtnCreate");
					Thread.sleep(1000);
					int matNum = ranNumbr(5000,9999);
					int matlet = ranNumbr(0,matletT.length()-1);
					int matlet1 = ranNumbr(0,matletT.length()-1);
					int matlet2 = ranNumbr(0,matletT.length()-1);
					
					//Paso 10.- Llenar todos los campos correspondientes
					matriNu = String.valueOf(matNum+matletT.substring(matlet, matlet+1)+matletT.substring(matlet1, matlet1+1)+matletT.substring(matlet2, matlet2+1));
					selectDropDown("ctl00_ContentZone_cmb_vehicle_type");
					Thread.sleep(1000);
					WebElement vehtype = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_vehicle_type"))).getFirstSelectedOption();
					String  vehtypeT = vehtype.getText();
					if (vehtypeT.equals("Autos")){
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
					if (vehtypeT.equals("Motos")){
						carSel = ranNumbr(0,1);
						carModel = ranNumbr(1,2);
						if (cicloModels[0][carSel].equals("Yamaha")){
							carModelSel = 0;
						}
						if (cicloModels[0][carSel].equals("Honda")){
							carModelSel = 1;
						}			
						vehtypeModel = String.valueOf(cicloModels[0][carSel]);
						vehtypeKind = String.valueOf(cicloModels[carModel][carModelSel]);
					}
					if (vehtypeT.contains("Buses")){
						carSel = ranNumbr(0,1);
						carModel = ranNumbr(1,2);
						if (autoBusModels[0][carSel].equals("DAIMLER-BENZ")){
							carModelSel = 0;
						}
						if (autoBusModels[0][carSel].equals("VOLVO")){
							carModelSel = 1;
						}
						vehtypeModel = String.valueOf(autoBusModels[0][carSel]);
						vehtypeKind = String.valueOf(autoBusModels[carModel][carModelSel]);
					}
					if (vehtypeT.contains("Camiones")){
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
					if (vehtypeT.contains("amionetas")){
						carSel = ranNumbr(0,1);
						carModel = ranNumbr(1,2);
						if (furgonetaModels[0][carSel].equals("Mercedes-Benz")){
							carModelSel = 0;
						}
						if (furgonetaModels[0][carSel].equals("Fiat")){
							carModelSel = 1;
						}
						vehtypeModel = String.valueOf(furgonetaModels[0][carSel]);
						vehtypeKind = String.valueOf(furgonetaModels[carModel][carModelSel]);
					}
					vehicleFieldsfill(matriNu,vehtypeModel,vehtypeKind,colorS[ranNumbr(0,colorS.length-1)]);
					if (accountType.equals("Exenta")) {
						if (ranNumbr(0,1)==0) {
							elementClick("ctl00_ContentZone_CtrlExemptData_RadioExpDate_0");
						}else {
							elementClick("ctl00_ContentZone_CtrlExemptData_RadioExpDate_1");
							SendKeys("ctl00_ContentZone_CtrlExemptData_BoxExpDate_box_date",dateSel(2019,2021));
							SendKeys("ctl00_ContentZone_CtrlExemptData_BoxExpDateWarn",String.valueOf(ranNumbr(5,20)));
						}
					}
					takeScreenShot("E:\\Selenium\\","vehicleDataFilledPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehicleDataFilledPage.jpeg");
					Thread.sleep(1000);
					
					//Paso 11.- Hacer click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");	
					Thread.sleep(1500);		
					if (driver.getPageSource().contains("Clase de vehículo no permitida")) {
						errorText = getText("ctl00_LblError");
						noVehAllowed = true;
						return;
					}
					
					//Paso 12.- Hacer click en el botón Back y hacer Click en el botón Confirmar
					elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
					Thread.sleep(1500);
					elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
					Thread.sleep(2500);
					String nextPage = getText("ctl00_SectionZone_LblTitle");
					if (nextPage.contains("Detalles del pago")) {
						elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
						paymentfromCustomerEsp();
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