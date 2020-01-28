package Copexa.Copexa;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static SettingFiles.Copexa_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import static Copexa.Copexa.BOHost_accountCreationWithVehicle.*;

public class BOHost_assignVehicleFeature{
		private static boolean accountClosed = false;
		private static boolean noVehicle = false;
		private static String [] optionsVeh = {"Crear", "Modificar", "Eliminar"};
		private static int selOption;
		private static String matriNum2;
		private static boolean tagAssigned;
		private static String tagSelected;
		private static String optionSelected;
		private static String errorText;
		private static int electVeh;
		public static int NumbVeh;
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
		public void assignVehicleFeatureInit() throws Exception {
			configurationSection("Host", testNameSelected, testNameSelected);			
			Thread.sleep(1000);							
			testPlanReading(9,0,2,3);
			selOption = ranNumbr(0,2);
			borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");
			assignVehicleFeature();
			Thread.sleep(200);
			if (accountClosed){
				actualResults.set(4, "No se puede "+optionsVeh[selOption]+" un Vehículo a la cuenta "+accountNumbr+" porque está cerrada");
				for (int i=5;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("No se puede "+optionsVeh[selOption]+" un Vehículo a la cuenta "+accountNumbr+" porque está cerrada");
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;
			}
			Thread.sleep(500);
			if (noVehicle==true) {
				actualResults.set(7, "No se puede "+optionsVeh[selOption]+" un Vehiculo debido a que hay "+NumbVeh+" vehículo/s asignado/s a la cuenta No. "+accountNumbr);
				for (int i=5;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("No se puede "+optionsVeh[selOption]+" un Vehiculo debido a que hay "+NumbVeh+" vehículo/s asignado/s a la cuenta No. "+accountNumbr);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;
			}
			if (noVehAllowed==true) {
				actualResults.set(7, "No se puede crear Vehiculo debido a: "+errorText);
				for (int i=5;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("No se puede crear Vehiculo debido a: "+errorText);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;	
			}
			if (tagAssigned==true) {
				actualResults.set(7, "No se puede eliminar el Vehiculo con matrincula No. "+matriNu+"debido a "+errorText);
				for (int i=5;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("No se puede eliminar el Vehiculo con matrincula No. "+matriNu+"debido a "+errorText);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;
			}
			
			if (!optionSelected.equals("Modificado")) {
				actualResults.set(10, "Se ha "+optionSelected+ " el vehículo con la matrícula " +matriNu+" en la cuenta "+accountNumbr+" correctamente");
				System.out.println("Se ha "+optionSelected+ " el vehículo con la matrícula " +matriNu+" en la cuenta "+accountNumbr+" correctamente");
			}else {
				actualResults.set(10, "Se ha "+optionSelected+ " el vehículo con la matrícula " +matriNum2+" en la cuenta "+accountNumbr+" a la nueva matricula "+matriNu+" correctamente");
				System.out.println("Se ha "+optionSelected+ " el vehículo con la matrícula " +matriNum2+" en la cuenta "+accountNumbr+" a la nueva matricula "+matriNu+" correctamente");
			}
			driver.close();
			testLink();
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
		}

		public static void assignVehicleFeature() throws Exception {
				action = new Actions(driver);
			try {
				//Paso 1.- Entrar a la página de Login del BackOffice de Copexa
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginCopexaPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginCopexaPage.jpeg");
				loginPageErr();
				if (pageSelectedErr==true) {
					testLink();
					System.err.println("Un error ha ocurrido en la Página de Inicio");
					fail("Un error ha ocurrido en la Página de Inicio");
				}
				//Paso 2.- 'Loguearse con el usuario 00001/00001
				loginPage("00001","00001");		
				takeScreenShot("E:\\Selenium\\","homeCopexaHomePage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeCopexaHomePage.jpeg");
				applicationVer = getText("ctl00_statusRight");
				Thread.sleep(2000);		
				
				//Paso 3.- Ir al Menú Gestión de Cuentas y seleccionar la opción Seleccionar Cuenta
				action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
				Thread.sleep(1000);
				pageSelected = "Seleccionar cuenta";
				elementClick(pageSelected);
				Thread.sleep(1000);
				pageSelectedErr(2);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				}
				
				//Paso 4.- Hacer click en el botón Búsqueda
				elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
				
				//Paso 5.- Buscar y hacer click en cualquier cuenta
				selectAccount();
				accountNumbr = getText("ctl00_SectionZone_LblTitle");
				accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
				accountType =  getText("ctl00_ContentZone_ctrlAccountData_lbl_exempt");
				if(driver.getPageSource().contains("CUENTA CERRADA")){
					accountClosed = true;
					return;
				}
				String numberVehicles = getText("ctl00_ContentZone_lbl_vehicles");
				NumbVeh = Integer.parseInt(numberVehicles);
				
				//Paso 6.- Hacer click en el botón Editar
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				Thread.sleep(500);
				
				//Paso 7.- Hacer click en el botón Vehículos
				elementClick("ctl00_ContentZone_BtnVehicles");
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","vehiclePage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehiclePage.jpeg");
				
				//Paso 8.- Hacer click en el botón correspondiente (si es Modificar o Eliminar, seleccionar primero el vehículo que esté en la lista)
				if (optionsVeh[selOption].equals("Modificar")||optionsVeh[selOption].equals("Eliminar")) {
					if (NumbVeh==0){
						noVehicle = true;
						return;
					}				
					int selVeh = ranNumbr(0,NumbVeh-1);
					if (selVeh<0) {
						selVeh =0;
					}
					electVeh = selVeh+2;				
					matriNum2 = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+electVeh+"]/td[3]");
					tagSelected = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+electVeh+"]/td[7]");
					elementClick("ctl00_ContentZone_radio"+selVeh);
				}	
				if (optionsVeh[selOption].equals("Modificar")||optionsVeh[selOption].equals("Crear")) {
					createModifyVehicle();
					if (noVehAllowed==true) {
						return;
					}
				}
				
				/*Paso 9.- Si se ha elegido Eliminar (y se ha elegido el vehículo correspondiente), aparecerá un popup si desea eliminar el vehículo. 
				 * Si has elegido Crear irá a la pantalla de creación de vehículo, ahi llenar todos los datos correspondientes y dar click al botón Confirmar
				 * Si has elegido Modificar, y a seleccionado el vehículo correspondientes aparecerá la pantalla de edición del vehículo y una vez ahí modificar
				  modificar los datos correspondientes y dar click el botón Confirmar*/
				if (optionsVeh[selOption].equals("Eliminar")) {
					
					optionSelected = "Eliminado";
					matriNu = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+electVeh+"]/td[3]");
					Thread.sleep(100);
					elementClick("ctl00_ContentZone_BtnDelete");
					Thread.sleep(100);
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					if (driver.getPageSource().contains("No se puede eliminar")) {
						errorText = getText("lblError");
						tagAssigned = true;
						return;
					}
					actualResults.set(8, "Se ha seleccionado el vehículo: "+matriNu+" y se ha dado click al botón Eliminar y luego al botón Aceptar");
					Thread.sleep(1000);
				}
				//Paso 10.- Una vez hecho la acción correspondiente hacer click en el botón Atrás
				elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
				Thread.sleep(1000);
				
				//Paso 11.- Una vez en la pantalla principal de edición de la cuenta, hacer click en el Botón Guardar
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","accountMaind"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountMaindPage.jpeg");
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw (e);
			}
		}
		public static void createModifyVehicle() throws Exception {
			try {
				Thread.sleep(1000);		
				if (optionsVeh[selOption].equals("Crear")) {
					optionSelected = "Creado";
					elementClick("ctl00_ContentZone_BtnCreate");
					Thread.sleep(1000);
					actualResults.set(8, "Se ha dado click al botón Crear y se ha llenado todos los campos correspondientes en la pantalla de creación de vehículo y luego se le ha dado al botón Confirmar");
				}
				if (optionsVeh[selOption].equals("Modificar")) {
					optionSelected = "Modificado";
					elementClick("ctl00_ContentZone_BtnModify");
					if (tagSelected.equals("")) {
						Thread.sleep(1000);
						elementClick("ctl00_ContentZone_btn_edit_assigned");
							if (isAlertPresent()) {
								driver.switchTo().alert().accept();
							}
							Thread.sleep(100);
							SendKeys("ctl00_ContentZone_txt_comment","El Vehiculo se ha modificado");
					}
					actualResults.set(8, "Se ha seleccionado el vehículo: "+matriNum2+" y se ha hecho click el botón modificar y se han modificado todos los datos correspondientes y luego se ha dado al botón Confirmar");
				}
			
				int matNum = ranNumbr(5000,9999);
				int matlet = ranNumbr(0,matletT.length()-1);
				int matlet1 = ranNumbr(0,matletT.length()-1);
				int matlet2 = ranNumbr(0,matletT.length()-1);
				matriNu = String.valueOf(matNum+matletT.substring(matlet, matlet+1)+matletT.substring(matlet1, matlet1+1)+matletT.substring(matlet2, matlet2+1));
				selectDropDown("ctl00_ContentZone_cmb_vehicle_type");
				Thread.sleep(1000);
				WebElement vehtype = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_vehicle_type"))).getFirstSelectedOption();
				String  vehtypeT = vehtype.getText();
				if (vehtypeT.equals("AUTO")){
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
				}else{						
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
				takeScreenShot("E:\\Selenium\\","vehicleDataFilledPage"+timet+".jpg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehicleDataFilledPage.jpeg");
				Thread.sleep(1000);												
				elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");	
				Thread.sleep(3000);		
				if (driver.getPageSource().contains("Clase de vehículo no permitida")) {
					errorText = getText("ctl00_LblError");
					noVehAllowed = true;
					return;
				}					
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}

}				