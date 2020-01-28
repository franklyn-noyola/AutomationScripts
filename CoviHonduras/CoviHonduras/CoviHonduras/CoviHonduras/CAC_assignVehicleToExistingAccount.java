package CoviHonduras.CoviHonduras;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import static SettingFiles.CoviHonduras_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import static CoviHonduras.CoviHonduras.CAC_accountCreationWithVehicle.*;

public class CAC_assignVehicleToExistingAccount{
			 private static boolean accountClosed = false;
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
			public void assigningVehcleToExistingAccountInit() throws Exception {
				configurationSection("CAC",testNameSelected,testNameSelected);
				testPlanReading(5,0,2,3);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				assigningVehcleToExistingAccount();
				
				//Paso 6.- Hacer click en el bot�n Editar
				Thread.sleep(200);				
				if (accountClosed){
					actualResults.set(5,"No se puede asignar un Veh�culo a la cuenta "+accountNumbr+" porque est� cerrada");
					for (int i=6;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
					}
					driver.close();
					testLink();
					System.out.println("No se puede asignar un Veh�culo a la cuenta "+accountNumbr+" porque est� cerrada");
					System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));					
					return;					
					
				}
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");
				Thread.sleep(1000);
				
				//Paso del 8 al 11
				accountCreationWithVehicle();		
				Thread.sleep(1000);
				actualResults.set(10,"Se le asignado el veh�culo con la matr�cula " +matriNu+" a la cuenta "+accountNumbr+" correctamente");
				driver.close();
				testLink();
				System.out.println("Se le asignado el veh�culo con la matr�cula " +matriNu+" a la cuenta "+accountNumbr+" correctamente");
				System.out.println("Se ha probado en la versi�n del: "+ getVersion(applevelTested));
			}

			public static void assigningVehcleToExistingAccount() throws Exception {				
				Actions action = new Actions(driver);
				try {
					//Paso 1.- Entrar a la p�gina de Login del CAC de CoviHonduras
					driver.get(CaCUrl);
					takeScreenShot("E:\\Selenium\\","loginCACCVHPage"+timet+".jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.err.println("Un error ha ocurrido en la P�gina de Inicio");
						fail("Un error ha ocurrido en la P�gina de Inicio");
					}
					
					
					//Paso 2.- Loguearse con el usuario 00001/00001
					loginPage("00001","00001");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","homeCACCVHPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeCACCVHPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					
					//Paso 3.- Ir al Men� Gesti�n de Cuentas y seleccionar la opci�n Seleccionar Cuenta
					Thread.sleep(2000);					
					action.moveToElement(driver.findElement(By.linkText("Gesti�n de cuentas"))).build().perform();
					Thread.sleep(1000);
					pageSelected = "Seleccionar cuenta";
					elementClick(pageSelected);
					Thread.sleep(2000);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la P�gina "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la P�gina "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					//Paso 4.- Hacer click en el bot�n B�squeda 
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
					
					//Paso 5.- Buscar y hacer click en cualquier cuenta					
					selectAccount();
					Thread.sleep(1000);
					
					accountNumbr = getText("ctl00_SectionZone_LblTitle");
					accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
					Thread.sleep(1000);					
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}	

}