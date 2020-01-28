package R3R5Ciralsa.R3R5Ciralsa;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import static SettingFiles.Generic_Settingsfields_File.*;
import static SettingFiles.R3R5Ciralsa_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import static R3R5Ciralsa.R3R5Ciralsa.BOHost_accountCreationWithVehicleWithoutPAN.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


public class BOHost_assignVehicleandPANToExistingAccount{
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
		public void assignVehicleandPANToExistingAccount() throws Exception {
			action = new Actions(driver);
			testPlanReading(4,0,2,3);
			configurationSection("Host", testNameSelected, testNameSelected);
			borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");			
			try{
				//Paso 1.- Entrar a la página de Login del BO de R3R5Ciralsa
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOPage.jpeg");
				loginPageErr();
				if (pageSelectedErr==true) {
					testLink();
					System.err.println("Un error ha ocurrido en la Página de Inicio");
					fail("Un error ha ocurrido en la Página de Inicio");
				}				
			
				//Paspo 2.- Loguearse con el usuario 00001/00001
				loginPage("00001","00001");
				takeScreenShot("E:\\Selenium\\","homeBOHostPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOHostPage.jpeg");
				applicationVer = getText("ctl00_statusRight");
				Thread.sleep(1000);					
				
				//Paso 3.- Ir a la pantalla Seleccionar Cuenta (Click en Gestión Cuentas y luego Seleccionar Cuenta)
				action.clickAndHold(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
				Thread.sleep(500);
				pageSelected = "Seleccionar cuenta";
				elementClick(pageSelected);
				Thread.sleep(500);
				pageSelectedErr(2);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				}
				
				//Paso 4.- Hacer click en el botón Buscar
				elementClick(searchButton);
				Thread.sleep(500);
				
				//Paso 5.- Hacer click en cualquier cuenta
				selectAccount();
				Thread.sleep(1000);
				accountNumbr = getText("ctl00_SectionZone_LblTitle");
				accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
				accountType = getText("ctl00_ContentZone_ctrlAccountData_lbl_accountType");
				takeScreenShot("E:\\Selenium\\","accountMainPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountMainPage.jpeg");
				
				//Paso 6.-Hacer click en el botón Editar
				if (driver.getPageSource().contains("CUENTA CERRADA")){
					takeScreenShot("E:\\Selenium\\","closedAccount"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","closedAccount.jpeg");
					actualResults.set(5, "No se puede asignar un Vehículo y Pan debido a que la Cuenta: "+accountNumbr+" porque está cerrada");
					for (int i=6;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println ("No se puede asignar un Vehículo y Pan debido a que la Cuenta: "+accountNumbr+" porque está cerrada");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;
				}
				
				//Paso 7.- Hacer click en el botón Editar
				elementClick(confirmButton);
				Thread.sleep(1000);
				
				//Paso del 8 al 13
				vehicleCreation();
				Thread.sleep(1000);
				if (panExist == true) {
					actualResults.set(11, "No se puede crear un vehículo con PAN debido a un error: "+errorText);
						actualResults.set(12, "N/A");
						executionResults.set(12, "");
					driver.close();
					testLink();
					System.out.println("No se puede crear un vehículo con PAN debido a un error: "+errorText);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;
				}
				actualResults.set(12,"Se ha asignado un vehículo con la matricula: "+matriNu+" a la cuenta: "+accountNumbr+" correctamente con el PAN: "+PAN);
				driver.close();
				testLink();
				System.out.println("Se ha asignado un vehículo con la matricula: "+matriNu+" a la cuenta: "+accountNumbr+" correctamente con el PAN: "+PAN);			
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}
		
}
