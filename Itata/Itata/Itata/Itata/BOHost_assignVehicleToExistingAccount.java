package Itata;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import static SettingFiles.Itata_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;

public class BOHost_assignVehicleToExistingAccount{
			 private static boolean accountClosed = false;
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
			public void assigningVehcleToExistingAccountInit() throws Exception {
				Thread.sleep(1000);
				configurationSection("Host",testNameSelected,testNameSelected);	
				testPlanReading(7,0,2,3);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				assigningVehcleToExistingAccount();
				Thread.sleep(200);
				if (accountClosed){
					actualResults.set(5, "No se puede asignar un Vehículo a la cuenta "+accountNumbr+" porque está cerrada");
					for (int i=6;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede asignar un Vehículo a la cuenta "+accountNumbr+" porque está cerrada");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;

				}
				Thread.sleep(500);
				
				//Paso 6.- Hacer click en el botón Editar	
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				
				//Desde el paso 7 al paso 11
				BOHost_accountCreationWithVehicle.accountCreationWithVehicle();		
				Thread.sleep(500);
				if (noVehAllowed==true) {
					actualResults.set(9, "No se puede crear Vehiculo debido a: "+errorText);					
						actualResults.set(10, "N/A");
						executionResults.set(10, "");
					driver.close();
					testLink();
					System.out.println("No se puede crear Vehiculo debido a: "+errorText);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;
				}
				actualResults.set(10, "Se le asignado el vehículo con la matrícula " +matriNu+" a la cuenta "+accountNumbr+" correctamente");
				Thread.sleep(500);
				driver.close();
				testLink();
				System.out.println("Se le asignado el vehículo con la matrícula " +matriNu+" a la cuenta "+accountNumbr+" correctamente");
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			}

			public static void assigningVehcleToExistingAccount() throws Exception {
				action = new Actions(driver);
				try {
					//Paso 1.- Entrar a la página de Login del BO de Itata
					driver.get(BoHostUrl);
					takeScreenShot("E:\\Selenium\\","loginBOItajPage"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOItaPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.err.println("Un error ha ocurrido en la Página de Inicio");
						fail("Un error ha ocurrido en la Página de Inicio");
					}
					
					//Paso 2.- Loguearse con el usuario 00001/00001
					loginPage("00001","00001");		
					pageSelected = "BackOffice Host";
					mainPage(1);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					takeScreenShot("E:\\Selenium\\","homeBOItaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOItaPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);
					
					//Paso 3.- Ir al Menú Gestión de Cuentas y hacer click la opción Seleccionar Cuenta
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
					
					
					//Step 4.- Hacer Click en el botón Buscar
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
					
					//Step 5.- Buscar y hacer click en cualquier cuenta
					selectAccount();
					accountNumbr = getText("ctl00_SectionZone_LblTitle");
					accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
					accountType =  getText("ctl00_ContentZone_ctrlAccountData_lbl_exempt");
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}
					Thread.sleep(500);
				
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);

			}	
		}
}



