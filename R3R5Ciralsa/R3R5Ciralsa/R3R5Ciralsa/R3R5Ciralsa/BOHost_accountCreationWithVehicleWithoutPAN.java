package R3R5Ciralsa.R3R5Ciralsa;
import org.openqa.selenium.support.ui.Select;
import static SettingFiles.Generic_Settingsfields_File.*;
import static SettingFiles.R3R5Ciralsa_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import static R3R5Ciralsa.R3R5Ciralsa.BOHost_accountCreationOnly.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BOHost_accountCreationWithVehicleWithoutPAN{		
		public static int titleType;
		public static String dispositivoSelected;		
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
		public void accountCreationwithVehicleawithoutPANInit() throws Exception {
				noPan = true;
				configurationSection("Host", testNameSelected, testNameSelected);
				testPlanReading(2,0,2,3);
				borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				
				//Pasos 1 al 6
				accountCreation();
				Thread.sleep(1000);
				
				//Paso 7.- Hacer Click en el botón Editar
				elementClick(confirmButton);// Editar Cuenta con el botón
				Thread.sleep(1000);
				vehicleCreation();
				Thread.sleep(2000);
				if (panExist == true) {
					actualResults.set(11, "No se ha podido crear el vehículo con el Dispositivo: "+BOHost_accountCreationWithVehicleWithoutPAN.dispositivoSelected+" causa de: "+errorText);
					actualResults.set(12, "N/A");
					executionResults.set(12, "");
					driver.close();
					testLink();
					System.out.println("No se ha podido crear el vehículo con el Dispositivo: "+BOHost_accountCreationWithVehicleWithoutPAN.dispositivoSelected+" causa de: "+errorText);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;
				}
				actualResults.set(12, "Se ha creado la cuenta: "+accountNumbr+" correctamente con el vehículo con matricula: "+matriNu+" sin Dispositivo ni PAN");
				driver.close();
				testLink();
				System.out.println("Se ha creado la cuenta: "+accountNumbr+" correctamente con el vehículo con matricula: "+matriNu+" sin Dispositivo ni PAN");
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));				
		}

		public static void vehicleCreation() throws Exception {
				if (vehicleFeature=="Crear") {
					//TC-BOHost_accountCreationWithVehicleandPAN: Paso 8.- Hacer Click en el botón Dispositivos
					//TC-BOHost_accountCreationWithVehiclewithoutPAN: Paso 8.- Hacer Click en el botón Dispositivos
					//TC-BOHost_assignVehicleandPANToExistingAccount: Paso 7.- Hacer click en el boton Dispositivos
					//BOHost_assignVehicleFeature - Crear;
					//Paso 7.- Hacer click en el boton Dispositivos
					elementClick("ctl00_ContentZone_BtnVehicles");
				}
				
				Thread.sleep(1000);		
				takeScreenShot("E:\\Selenium\\","vehiclePage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehiclePage.jpeg");
				if (vehicleFeature=="Crear") {
					//TC-BOHost_accountCreationWithVehicleandPAN: Paso 9.- Hacer click en el botón Crear
					//TC-BOHost_accountCreationWithVehiclewithoutPAN: Paso 9.- Hacer click en el botón Crear
					//TC-BOHost_assignVehicleandPANToExistingAccount: Paso 8.- Hacer click en el botón Crear
					//TCBOHost_assignVehicleFeature - Crear: Paso 8.- Hacer click en el botón Crear
					elementClick("ctl00_ContentZone_BtnCreate");
				}
				
				//TC-BOHost_accountCreationWithVehicleandPAN: Paso 10.- Llenar todos los campos correspondientes
				//TC-BOHost_accountCreationWithVehiclewithoutPAN: Paso 10.- Llenar todos los campos correspondientes
				//TC-BOHost_assignVehicleandPANToExistingAccount: Paso 9.- Llenar todos los campos correspondientes
				//TC-BOHost_assignVehicleFeature - Crear: Paso 9.- Llenar todos los campos correspondientes
				//TC-BOHost_assignVehicleFeature - Modificar: Paso 10.- Llenar todos los campos correspondientes
				Thread.sleep(1000);
				int matNum = ranNumbr(5000,9999);
				int matlet = ranNumbr(1,matletT.length()-1);
				int matlet1 = ranNumbr(1,matletT.length()-1);
				int matlet2 = ranNumbr(1,matletT.length())-1;
				matriNu = String.valueOf(matNum+matletT.substring(matlet, matlet+1)+matletT.substring(matlet1, matlet1+1)+matletT.substring(matlet2, matlet2+1));
				selectDropDownV("ctl00_ContentZone_cmb_vehicle_type");
				Thread.sleep(1000);
				WebElement vehtype = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_vehicle_type"))).getFirstSelectedOption();				
				String  vehtypeT = vehtype.getText();
				if (vehtypeT.equals("Ligero")){
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
				if (vehicleFeature.equals("Modificar")) {
					SendKeys("ctl00_ContentZone_txt_comment","Vehículo Modificado para la cuenta: "+accountNumbr);
				}else {
					SendKeys("ctl00_ContentZone_txt_comment","Vehículo creado para la cuenta: "+accountNumbr);
				}
				Thread.sleep(100);
				titleType=ranNumbr(0,2);				
				if (noPan==false) {
					//TC-BOHost_accountCreationWithVehicleandPAN: Paso 11.- Hacer Click en la lista desplegable Dispositivo y seleccionar cualquier opción que sea: OBE, Tarjeta de Proximidad o Tarjeta Magenética
					//TC-BOHost_accountCreationWithVehicleandPAN: Paso 12.- Una vez seleccionado el dispositivo correspondiente, introducir el PAN correspondiente en el campo PAN
					//TC-BOHost_assignVehicleandPANToExistingAccount: Paso 11.- Una vez seleccionado el dispositivo correspondiente, introducir el PAN correspondiente en el campo PAN
					if (titleType==0) {
						dispositivoSelected = "OBE";
						new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_tipo_titulo"))).selectByIndex(1);
						PAN = String.valueOf(ranNumbr(1,9000000))+String.valueOf(ranNumbr(1,9000000))+String.valueOf(ranNumbr(1,9000000))+String.valueOf(ranNumbr(1,9000));
						SendKeys("ctl00_ContentZone_memberPANControl_txt_pan",PAN);
					}
					if (titleType==1) {
						dispositivoSelected = "Tarjeta de proximidad";
						new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_tipo_titulo"))).selectByIndex(2);
						PAN = String.valueOf(ranNumbr(1000000,9999999))+String.valueOf(ranNumbr(11111,9999999));
						SendKeys("ctl00_ContentZone_memberPANControl_txt_pan",PAN);
					}
					if (titleType==2) {
						dispositivoSelected = "Tarjeta magnética";
						new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_tipo_titulo"))).selectByIndex(3);
						PAN = String.valueOf(ranNumbr(1,9000000))+String.valueOf(ranNumbr(1,9000000))+String.valueOf(ranNumbr(1,9000000))+String.valueOf(ranNumbr(1,9000));
						SendKeys("ctl00_ContentZone_memberPANControl_txt_pan",PAN);
					}
				}				
				Thread.sleep(100);
				if (accountType.equals("CUENTA EXENTA")) {
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_CtrlExemptData_RadioExpDate_1");
						Thread.sleep(100);					
						SendKeys("ctl00_ContentZone_CtrlExemptData_BoxExpDate_box_date",dateSel(2018,2019));
						Thread.sleep(100);
						SendKeys("ctl00_ContentZone_CtrlExemptData_BoxExpDateWarn",String.valueOf(ranNumbr(1,15)+""));
					}
				}
				Thread.sleep(100);								
				takeScreenShot("E:\\Selenium\\","vehicleDataFilledPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehicleDataFilledPage.jpeg");
				
				//TC-BOHost_accountCreationWithVehicleandPAN: 13.- Hacer click en el botón Confirmar
				//TC-BOHost_accountCreationWithVehiclewithoutPAN: 12.- Hacer click en el botón Confirmar
				//TC-BOHost_assignVehicleandPANToExistingAccount: 12.- Hacer click en el botón Confirmar
				//TC-BOHost_assignVehicleFeature - Crear: 10.- Hacer clck en el botón Confirmar
				//TC-BOHost_assignVehicleFeature - Modificar: 11.- Hacer click en el botón Confirmar
				elementClick("ctl00_ButtonsZone_lbl_btnSubmit");
				Thread.sleep(1500);
				String validateTitle = getText("ctl00_SectionZone_LblTitle");
				if (validateTitle.equals("Vehículo en cuenta")){
					errorText = getText("//*[@id='toast-container']/div/div");
					if (errorText.equals("El Pan ya está asignado") || errorText.contains("EL PAN debe tener")){
						panExist = true;
						return;
					}
				}					
				Thread.sleep(500);
				takeScreenShot("E:\\Selenium\\","vehiclecreatedPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehiclecreatedPage.jpeg");
				
				//TC-BOHost_accountCreationWithVehicleandPAN: Paso 14.- Hacer click en el botón Back y hacer Click en el botón Confirmar
				//TC-BOHost_accountCreationWithVehiclewithoutPAN: Paso 13.- Hacer click en el botón Back y hacer Click en el botón Confirmar
				//TC-BOHost_assignVehicleandPANToExistingAccount: Paso 13.- Hacer click en el botón Back y hacer Click en el botón Confirmar
				//TC-BOHost_assignVehicleFeature - Modificar: Paso 12.- Hacer click en el botón Back y hacer Click en el botón Confirmar
				//TC-BOHost_assignVehicleFeature - Crar: Paso 11.- Hacer click en el botón Back y hacer Click en el botón Confirmar
				elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
				Thread.sleep(1500);
				elementClick(confirmButton);
				Thread.sleep(3500);
			}
		
		public static void vehicleFieldsfill(String Matricul, String vehtypeM, String vehtypeK, String ColorT) throws Exception{
				Thread.sleep(1000);
				SendKeys("ctl00_ContentZone_text_plate_number",Matricul);
				SendKeys("ctl00_ContentZone_text_make",vehtypeM);
				SendKeys("ctl00_ContentZone_text_model",vehtypeK);
				SendKeys("ctl00_ContentZone_text_colour",ColorT);			
		}
		
	
			
}

