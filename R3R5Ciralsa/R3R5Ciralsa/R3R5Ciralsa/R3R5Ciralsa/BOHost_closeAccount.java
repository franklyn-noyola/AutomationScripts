package R3R5Ciralsa.R3R5Ciralsa;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import static SettingFiles.Generic_Settingsfields_File.*;
import static SettingFiles.R3R5Ciralsa_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


public class BOHost_closeAccount{
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
		public void closeAccount() throws Exception {
			configurationSection("Host", testNameSelected, testNameSelected);
			testPlanReading(5,0,2,3);
			action = new Actions(driver);
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
				//Paso 3.- Ir al Menú Gestión de Cuentas y hacer click en Seleccionar Cuenta
				action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
				Thread.sleep(1000);
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
				takeScreenShot("E:\\Selenium\\","selectAccounttPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","selectAccounttPage.jpeg");

				//Paso 4.- Hacer click en el botón Búsqueda
				elementClick(searchButton);
				Thread.sleep(500);
				
				//Paso 5.- Buscar y hacer click en cualquier cuenta
				selectAccount();
				Thread.sleep(1000);
				accountNumbr = getText("ctl00_SectionZone_LblTitle");
				accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
				takeScreenShot("E:\\Selenium\\","accountMainPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountMainPage.jpeg");
				
				//Paso 6.- Hacer click en el botón Cerrar Cuenta
				if (driver.getPageSource().contains("CUENTA CERRADA")){
					takeScreenShot("E:\\Selenium\\","closedAccount"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","closedAccount.jpeg");
					actualResults.set(5,"No se puede cerrar la Cuenta: "+accountNumbr+" porque está cerrada");
					for (int i=6;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede cerrar la Cuenta: "+accountNumbr+" porque está cerrada");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;										
				}
				String vehicleNum = getText("ctl00_ContentZone_lbl_vehicles");
				int vehicleNumbr = Integer.parseInt(vehicleNum);
				
				//Paso 7.- Ya en la pantalla Cierre de cuenta, verificar si se puede cerrar la cuenta
				if (vehicleNumbr>0){
					takeScreenShot("E:\\Selenium\\","closedAccount"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","closedAccount.jpeg");
					actualResults.set(6,"No se puede cerrar la Cuenta: "+accountNumbr+" porque tiene "+vehicleNumbr+" vehículos asignado/s");
					for (int i=7;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede cerrar la Cuenta: "+accountNumbr+" porque tiene "+vehicleNumbr+" vehículos asignado/s");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;					
				}
				elementClick("ctl00_ContentZone_BtnCloseAccount");
				Thread.sleep(1000);
				
				//Paso 8.- Entrar un comentario en el campo Comentario y hacer click al botón Cerrar Cuenta
				SendKeys("ctl00_ContentZone_txtComment","Se ha cerrado la cuenta No. "+accountNumbr);
				takeScreenShot("E:\\Selenium\\","accountClosePage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountClosePage.jpeg");
				elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
				Thread.sleep(3000);
				
				//Paso 9.- Hacer click en el botón Volver
				elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
				Thread.sleep(4000);
				takeScreenShot("E:\\Selenium\\","accountClosed"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountClosed.jpeg");
				actualResults.set(8,"Se ha cerrado la cuenta: "+accountNumbr+" correctamente");
				driver.close();
				testLink();
				System.out.println("Se ha cerrado la cuenta: "+accountNumbr+" correctamente ");				
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}
				
		
}