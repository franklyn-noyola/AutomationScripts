package Itata;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static SettingFiles.Itata_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import static Itata.BOHost_accountCreationWithVehicle.*;

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
			 public static int NumbVeh;
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
			public void assignVehicleFeatureInit() throws Exception {
				configurationSection("Host",testNameSelected,testNameSelected);
				testPlanReading(6,0,2,3);
				Thread.sleep(1000);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				assignVehicleFeature();
				Thread.sleep(200);
				if (accountClosed){
					actualResults.set(5, "No se puede "+optionsVeh[selOption]+" un Veh�culo a la cuenta "+accountNumbr+" porque est� cerrada");
					for (int i=6;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede "+optionsVeh[selOption]+" un Veh�culo a la cuenta "+accountNumbr+" porque est� cerrada");
					System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));												
					return;	
				}
				Thread.sleep(500);
				if (noVehicle==true) {
					actualResults.set(7, "No se puede "+optionsVeh[selOption]+" un Vehiculo debido a que hay "+NumbVeh+" asignado a la cuenta No. "+accountNumbr);
					for (int i=8;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede "+optionsVeh[selOption]+" un Vehiculo debido a que hay "+NumbVeh+" asignado a la cuenta No. "+accountNumbr);
					System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));												
					return;	
			
				}
				if (noVehAllowed==true) {
					actualResults.set(8, "No se puede crear/modificar Vehiculo en la cuenta No.: "+accountNumbr+" debido a: "+errorText );
					for (int i=9;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede crear/modificar Vehiculo en la cuenta No.: "+accountNumbr+" debido a: "+errorText );
					System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));												
					return;	
				}
				if (tagAssigned==true) {
					actualResults.set(8, "No se puede eliminar el Vehiculo con matrincula No. "+matriNu+"debido a "+errorText);
					for (int i=9;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede eliminar el Vehiculo con matrincula No. "+matriNu+"debido a "+errorText);
					System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));												
					return;										
				}
				
				if (!optionSelected.equals("Modificado")) {
					actualResults.set(10,"Se ha "+optionSelected+ " el veh�culo con la matr�cula " +matriNu+" en la cuenta "+accountNumbr+" correctamente");
					System.out.println("Se ha "+optionSelected+ " el veh�culo con la matr�cula " +matriNu+" en la cuenta "+accountNumbr+" correctamente");
				}else {
					actualResults.set(10,"Se ha "+optionSelected+ " el veh�culo con la matr�cula " +matriNum2+" en la cuenta "+accountNumbr+" a la nueva matricula "+matriNu+" correctamente");
					System.out.println("Se ha "+optionSelected+ " el veh�culo con la matr�cula " +matriNum2+" en la cuenta "+accountNumbr+" a la nueva matricula "+matriNu+" correctamente");
				}
				driver.close();
				testLink();
				System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));
			}

			public static void assignVehicleFeature() throws Exception {
				action = new Actions(driver);
				selOption = ranNumbr(0,2);
				try {
					//Paso 1.- Entrar a la p�gina de Login del BO de Itata
					driver.get(BoHostUrl);
					takeScreenShot("E:\\Selenium\\","loginBOItajPage"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOItaPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.err.println("Un error ha ocurrido en la P�gina de Inicio");
						fail("Un error ha ocurrido en la P�gina de Inicio");
					}
					
					//Paso 2.- Loguearse con el usuario 00001/00001
					loginPage("00001","00001");		
					pageSelected = "BackOffice Host";
					mainPage(1);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la P�gina "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la P�gina "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					takeScreenShot("E:\\Selenium\\","homeBOItaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOItaPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);			
					
					//Paso 3.- Ir al Men� Gesti�n de Cuentas y hacer click la opci�n Seleccionar Cuenta
					action.moveToElement(driver.findElement(By.linkText("Gesti�n de cuentas"))).build().perform();
					Thread.sleep(1000);
					pageSelected = "Seleccionar cuenta";
					elementClick(pageSelected);
					Thread.sleep(1000);		
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la P�gina "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la P�gina "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					
					//Step 4.- Hacer Click en el bot�n Buscar
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
					
					//Step 5.- Buscar y hacer click en cualquier cuenta
					selectAccount();
					accountNumbr = getText("ctl00_SectionZone_LblTitle");
					accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
					accountType =  getText("ctl00_ContentZone_ctrlAccountData_lbl_exempt");
					
					//Paso 6.- Hacer click en el bot�n Editar					
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}
					String numberVehicles = getText("ctl00_ContentZone_lbl_vehicles");
					NumbVeh = Integer.parseInt(numberVehicles);
					if (optionsVeh[selOption].equals("Modificar")||optionsVeh[selOption].equals("Eliminar")) {
						if (NumbVeh==0){
							noVehicle = true;
							return;
						}
					}
					
					elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
					Thread.sleep(500);
					
					//Paso 7.- Ya en la cuenta en modo edici�n, hacer click en el bot�n Vehiculo
					elementClick("ctl00_ContentZone_BtnVehicles");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","vehiclePage"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehiclePage.jpg");
					int selVeh = ranNumbr(0,NumbVeh-1);
					if (selVeh<0) {
						selVeh =0;
					}
					int electVeh = selVeh+2;
					
					//Paso 8.- Hacer click en el bot�n correspondiente (si es Modificar o Eliminar, seleccionar primero el veh�culo que est� en la lista)
					matriNum2 = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+electVeh+"]/td[3]");
					tagSelected = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+electVeh+"]/td[8]");
					if (optionsVeh[selOption].equals("Modificar")||optionsVeh[selOption].equals("Eliminar")) {
						elementClick("ctl00_ContentZone_radio"+selVeh);
					}
					if (optionsVeh[selOption].equals("Modificar")||optionsVeh[selOption].equals("Crear")) {
						createModifyVehicle();
						if (noVehAllowed==true) {
							return;
						}
					}
				
					if (optionsVeh[selOption].equals("Eliminar")) {
						optionSelected = "Eliminado";
						matriNu = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+electVeh+"]/td[3]");
						Thread.sleep(100);
						elementClick("ctl00_ContentZone_BtnDelete");
						actualResults.set(7, "Se ha hecho click en el bot�n Eliminar, aparece un popup indicando que se vaa borrar el veh�culo seleccionado");
						Thread.sleep(100);
						
						//Paso 9.- Si se ha elegido Eliminar (y se ha elegido el veh�culo correspondiente), aparecer� un popup si desea eliminar el veh�cul,  si has elegido Crear ir� a la pantalla de creaci�n de veh�culo, ahi llenar todos los datos correspondientes y dar click al bot�n Confirmar, si has elegido Modificar, y a seleccionado el veh�culo correspondientes aparecer� la pantalla de edici�n del veh�culo y una vez ah� modificar  los datos correspondientes y dar click el bot�n Confirmar
						if (isAlertPresent()) {
							driver.switchTo().alert().accept();
						}
												
						if (driver.getPageSource().contains("No se puede eliminar")) {
							errorText = getText("lblError");
							tagAssigned = true;
							return;
						}
						Thread.sleep(1000);
					}
					
					//Paso 10.- Una vez hecho la acci�n correspondiente hacer click en el bot�n Atr�s
					elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
					Thread.sleep(1000);
					
					//Pasp 11.- Hacer click el el bot�n Guardar
					elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","accountMaind"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountMaindPage.jpeg");
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
			
			public static void createModifyVehicle() throws Exception {
				try {
					Thread.sleep(1000);		
					if (optionsVeh[selOption].equals("Crear")) {
						optionSelected = "Creado";
						elementClick("ctl00_ContentZone_BtnCreate");
						actualResults.set(7, "Se ha hecho click en el bot�n Crear, aparece la pantalla de creaci�n de veh�culo");
						Thread.sleep(1000);
					}
					if (optionsVeh[selOption].equals("Modificar")) {
						optionSelected = "Modificado";
						elementClick("ctl00_ContentZone_BtnModify");
						actualResults.set(7, "Se ha hecho click en el bot�n Modificar, aparece la pantalla de modificaci�n de veh�culo");
						if (tagSelected.equals("")) {
							Thread.sleep(1000);
							elementClick("ctl00_ContentZone_btn_edit_assigned");
								if (isAlertPresent()) {
									driver.switchTo().alert().accept();
								}
								Thread.sleep(100);
								SendKeys("ctl00_ContentZone_txt_comment","El Vehiculo se ha modificado");
						}
					}
					
					//Paso 9.- Si se ha elegido Eliminar (y se ha elegido el veh�culo correspondiente), aparecer� un popup si desea eliminar el veh�cul,  si has elegido Crear ir� a la pantalla de creaci�n de veh�culo, ahi llenar todos los datos correspondientes y dar click al bot�n Confirmar, si has elegido Modificar, y a seleccionado el veh�culo correspondientes aparecer� la pantalla de edici�n del veh�culo y una vez ah� modificar  los datos correspondientes y dar click el bot�n Confirmar
					int matNum = ranNumbr(5000,9999);
					int matlet = ranNumbr(0,matletT.length()-1);
					int matlet1 = ranNumbr(0,matletT.length()-1);
					int matlet2 = ranNumbr(0,matletT.length()-1);
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
					takeScreenShot("E:\\Selenium\\","vehicleDataFilledPage"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehicleDataFilledPage.jpg");
					Thread.sleep(1000);												
					elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");	
					Thread.sleep(3000);		
					if (driver.getPageSource().contains("Clase de veh�culo no permitida")) {
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