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


public class BOHost_vehicleFeatures{		
		public static int opSel;
		public static String [] vehicleOptions = {"Crear","Modificar","Eliminar","Documento"};
		public static String vehicleSel;
		public static String DocumentOption;
		public static String alertError="No Error";
		public static String documentUpload;
		public static String matriNu2;
		public static boolean noDocument = false;
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
		public void vehicleFeaturesInit() throws Exception {						
			opSel = 3;ranNumbr(0,3);
			if (opSel==0) {
				configurationSection("Host", testNameSelected+" - Crear", testNameSelected);
				testPlanReading(15,0,2,3);
			}
			if (opSel==1) {
				configurationSection("Host", testNameSelected+" - Modificar", testNameSelected);
				testPlanReading(15,5,7,8);
			}
			if (opSel==2) {
				configurationSection("Host", testNameSelected+" - Eliminar", testNameSelected);
				testPlanReading(15,10,12,13);
			}
			if (opSel==3) {
				configurationSection("Host", testNameSelected+" - Documento", testNameSelected);
				testPlanReading(15,15,17,18);
			}
			
			borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");			
			vehicleFeatures();
			if (accountClosed == true) {
				takeScreenShot("E:\\Selenium\\","closedAccount"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","closedAccount.jpeg");
				actualResults.set(5,"No se puede Crear/Modificar/Eliminar un Vehiculo o Subir un Documento a un Dispositivo en la cuenta: "+accountNumbr+" porque la cuenta está cerrada");
				for (int i=6;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("No se puede Crear/Modificar/Eliminar un Vehiculo o Subir un Documento a un Dispositivo en la cuenta: "+accountNumbr+" porque la cuenta está cerrada");
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
				return;	
				
				
			}
			if (panExist == true) {
				takeScreenShot("E:\\Selenium\\","panExisted"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","panExisted.jpeg");
				int step;
				if (vehicleOptions[opSel].equals("Crear")) {
					step =9;
				}else {
					step = 11;
				}
				actualResults.set(step,"No se puede crear o modificar un Dispositivo con PAN en la cuenta: "+accountNumbr+" debido a un error: "+errorText);
				for (int i=step+1;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();				
				System.out.println("No se puede crear o modificar un Dispositivo con PAN en la cuenta: "+accountNumbr+" debido a un error: "+errorText);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;
			}			
			if (noItemFound == true) {
				takeScreenShot("E:\\Selenium\\","noVehicle"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","noVehicle.jpeg");
				actualResults.set(6,"No se puede modificar/borrar/crear o borrar un Documento a un Dispositivo debido a que no hay vehículo asignado a la Cuenta: "+accountNumbr);
				for (int i=6;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("No se puede modificar/borrar/crear o borrar un Documento a un Dispositivo debido a que no hay vehículo asignado a la Cuenta: "+accountNumbr);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				fail("No se puede modificar/borrar/crear o borrar un Documento a un Vehículo debido a que no hay vehículo asignado a la Cuenta: "+accountNumbr);
			}
			if (noDocument==true) {
				takeScreenShot("E:\\Selenium\\","noDocument"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","noDocument.jpeg");
				actualResults.set(9,"No se puede borrar un Documento del Dispositivo con matricula: "+vehicleSel+" en la cuenta No.: "+accountNumbr+" debido a que no hay Documento");
				for (int i=10;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();				
				System.out.println("No se puede borrar un Documento del Dispositivo con matricula: "+vehicleSel+" en la cuenta No.: "+accountNumbr+" debido a que no hay Documento");
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;
			}
			
			if (vehicleOptions[opSel].equals("Crear")) {
				if (noPan==false) {
					actualResults.set(10,"Se ha creado un Dispositivo con la Matricula: "+matriNu+" con el pan asignado: "+PAN+" en la cuenta No. "+accountNumbr);
					System.out.println("Se ha creado un Dispositivo con la Matricula: "+matriNu+" con el pan asignado: "+PAN+" en la cuenta No. "+accountNumbr);
				}else {
					actualResults.set(10,"Se ha creado un Dispositivo con la Matricula: "+matriNu+" sin pan asignado en la cuenta No. "+accountNumbr);
					System.out.println("Se ha creado un Dispositivo con la Matricula: "+matriNu+" sin pan asignado en la cuenta No. "+accountNumbr);
				}				
			}
			
			if  (vehicleOptions[opSel].equals("Eliminar")) {
				actualResults.set(11,"Se ha elminado el Dispositivo con la matrícula: "+vehicleSel+" de la cuenta No.: "+accountNumbr);
				System.out.println("Se ha elminado el Dispositivo con la matrícula: "+vehicleSel+" de la cuenta No.: "+accountNumbr);
			}
			if  (vehicleOptions[opSel].equals("Modificar")) {
				actualResults.set(13,"Se ha nodificado el Dispositivo con la matrícula: "+vehicleSel+" por otro vehículo con matrícula: "+matriNu+" en la cuenta No.: "+accountNumbr);
				System.out.println("Se ha nodificado el Dispositivo con la matrícula: "+vehicleSel+" por otro vehículo con matrícula: "+matriNu+" en la cuenta No.: "+accountNumbr);
			}
			if  (vehicleOptions[opSel].equals("Documento")) {
				if (alertError.equals("error salvando datos")) {
					errorText = alertError;					
					actualResults.set(13,"No se pudo crear/subir el Documento: "+documentUpload+" al Dispositivo con la matrícula: "+vehicleSel+" en la cuenta No.: "+accountNumbr+" debido a: "+errorText);
					executionResults.set(13,"Fallado");						
					summaryBug = "No se pudo crear/subir el Documento: "+documentUpload+" al Dispositivo con la matrícula: "+vehicleSel+" en la cuenta No.: "+accountNumbr+" debido a: "+errorText;
					erroTextBug = "No se pudo crear/subir el Documento: "+documentUpload+" al Dispositivo con la matrícula: "+vehicleSel+" en la cuenta No.: "+accountNumbr+" debido a: "+errorText;
					componentBug = "HM";
					severityBug = 1;
					priority = 4;
					testFailed = true;
					bugAttachment = true;
					pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\unabletoUploaddocument.jpeg";						
					driver.close();
					testLink();
					System.out.println("No se pudo crear/subir el Documento: "+documentUpload+" al Dispositivo con la matrícula: "+vehicleSel+" en la cuenta No.: "+accountNumbr+" debido a: "+errorText);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					fail("No se pudo crear/subir el Documento: "+documentUpload+" al vehículo con la matrícula: "+vehicleSel+" en la cuenta No.: "+accountNumbr+" debido a: "+errorText);
				}else {
					if (DocumentOption.equals("Crear")) {
						actualResults.set(13,"Se ha creado/subido el Documento: "+documentUpload+" al Dispositivo con la matrícula: "+vehicleSel+" en la cuenta No.: "+accountNumbr);
						System.out.println("Se ha creado/subido el Documento: "+documentUpload+" al Dispositivo con la matrícula: "+vehicleSel+" en la cuenta No.: "+accountNumbr);
					}
					if (DocumentOption.equals("Eliminar")) {
						actualResults.set(13,"Se ha borrado el Documento: "+documentUpload+" al Dispositivo con la matrícula: "+vehicleSel+" en la cuenta No.: "+accountNumbr);
						System.out.println("Se ha borrado el Documento: "+documentUpload+" al Dispositivo con la matrícula: "+vehicleSel+" en la cuenta No.: "+accountNumbr);
					}
				}
			}
				driver.close();
				testLink();
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			
		}
		
		public static void vehicleFeatures() throws Exception{
			action = new Actions (driver);
			if (ranNumbr(0,1)==0) {
				noPan=true;
			}
			try {
				//Paso 1.- Entrar a la página de Login del BO Host de R3R5Ciralsa
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOPage.jpeg");
				loginPageErr();
				if (pageSelectedErr==true) {
					testLink();
					System.err.println("Un error ha ocurrido en la Página de Inicio");
					fail("Un error ha ocurrido en la Página de Inicio");
				}
													
				//Paso 2.- Loguearse con el usuario 00001/00001
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
				accountType = getText("ctl00_ContentZone_ctrlAccountData_lbl_accountType");
				takeScreenShot("E:\\Selenium\\","accountMainPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountMainPage.jpeg");				
				if (driver.getPageSource().contains("CUENTA CERRADA")){
					accountClosed = true;
					return;
				}
				
				//Paso 6.- Hacer click en el botón Editar
				elementClick(confirmButton);
				Thread.sleep(1000);				
				switch (vehicleOptions[opSel]) {
					case "Crear":					Thread.sleep(1000);
													//TC BOHost_assignVehicleFeature - Crear: Paso del 7 al 11
													vehicleCreation();
													Thread.sleep(1000);
													break;													
					case "Modificar":				vehicleFeature = "Modificar";
													Thread.sleep(1000);
													//TC BOHost_assignVehicleFeature - Modificar: Paso del 7 al 12
													modificarEliminarVehicle();
													Thread.sleep(1000);
													break;
					case "Eliminar":				Thread.sleep(1000);
													//TC BOHost_assignVehicleFeature - Eliminar: Paso del 7 al 10													
													modificarEliminarVehicle();
													Thread.sleep(1000);
													break;
					case "Documento":				Thread.sleep(1000);
													vehicleDocument();
													Thread.sleep(1000);
													break;
				}
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}
		
		public static void modificarEliminarVehicle() throws Exception{
			try {
				Thread.sleep(500);
				
				//TC BOHost_assignVehicleFeature - Modificar; //TC BOHost_assignVehicleFeature - Eliminar; //TC BOHost_assignVehicleFeature - Documento
				//Paso 7.- Ya en la cuenta en modo edición, hacer click en el botón Vehiculo				
				elementClick("ctl00_ContentZone_BtnVehicles");
				Thread.sleep(1000);
				
				//TC BOHost_assignVehicleFeature - Modificar; //TC BOHost_assignVehicleFeature - Eliminar; //TC BOHost_assignVehicleFeature - Documento
				//Paso 8.- Ya en la pantalla principal de Vehículos, si hay vehiculos disponibles, seleccionar uno cualquiera
				itemSearchTable();
				if (noItemFound == true) {
					return;
				}
				vehicleSel = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowSel+"]/td[5]");
				if (vehicleOptions[opSel].equals("Eliminar")) {
					takeScreenShot("E:\\Selenium\\","vehicleMainPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehicleMainPage.jpeg");
					Thread.sleep(500);
					
					//TC BOHost_assignVehicleFeature - Eliminar: Paso 9.- Hacer click en el botón Eliminar
					elementClick("ctl00_ContentZone_BtnDelete");
					Thread.sleep(500);
					takeScreenShot("E:\\Selenium\\","deletionConfirmation"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","deletionConfirmation.jpeg");
					
					//TC BOHost_assignVehicleFeature - Eliminar: Paso 10.- Hacer click en el botón Confirmar
					elementClick("popup_ok");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","vehicleDeleted"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehicleDeleted.jpeg");
					elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
					Thread.sleep(1000);
					elementClick(confirmButton);
					Thread.sleep(3500);
					return;
				}
				if (vehicleOptions[opSel].equals("Modificar")) {
					Thread.sleep(500);
					
					//TC BOHost_assignVehicleFeature - Modificar: Paso 9.- Hacer click en el botón Modificar
					elementClick("ctl00_ContentZone_BtnModify");
					Thread.sleep(500);
					
					//TC BOHost_assignVehicleFeature - Modificar: Pasos del 10 al 12
					vehicleCreation();
					Thread.sleep(1000);
					return;
				}
				
				
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}
		public static void vehicleDocument() throws Exception{
			try {
				Thread.sleep(500);
				//TC BOHost_assignVehicleFeature - Documento: Paso 7.- Ya en la cuenta en modo edición, hacer click en el botón Vehiculo 
				elementClick("ctl00_ContentZone_BtnVehicles");				
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","vehicleMainPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","vehicleMainPage.jpeg");
				
				//TC BOHost_assignVehicleFeature - Documento: Paso 8.- Ya en la pantalla principal de Vehículos, si hay vehiculos disponibles, seleccionar uno cualquiera
				itemSearchTable();
				if (noItemFound == true) {
					return;
				}
				vehicleSel = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowSel+"]/td[5]");
				Thread.sleep(500);
				
				//TC BOHost_assignVehicleFeature - Documento: Paso 9.- Hacer click en el botón Documento
				elementClick("ctl00_ContentZone_BtnDocuments");
				Thread.sleep(500);
				takeScreenShot("E:\\Selenium\\","documentPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","documentPage.jpeg");
				int selOption = ranNumbr(0,1);
				if (selOption<0) {
					selOption =0;
				}
				
				//TC BOHost_assignVehicleFeature - Documento: Paso 10.- Hacer cualquier acción pertinente (Crear o Eliminar), Si hay documentos disponibles (elegir el documento cualquiera y hacer click en el botón Eliminar
				if (selOption ==0) {
					DocumentOption = "Crear";
					elementClick("ctl00_ContentZone_BtnCreate");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","documentCreatePage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","documentCreatePage.jpeg");
					documentUpload = "documentoPro.docx";
					driver.findElement(By.name("ctl00$ContentZone$FileUploadControl")).sendKeys("C:\\archivosPruebas\\"+documentUpload);					
					Thread.sleep(500);
					SendKeys("ctl00_ContentZone_txt_description_box_data","Se ha creado/subido el Documento: "+documentUpload);
					Thread.sleep(100);
					takeScreenShot("E:\\Selenium\\","documentCreateFillPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","documentCreateFillPage.jpeg");
					elementClick("ctl00_ContentZone_BtnSubmit");
					Thread.sleep(100);
					takeScreenShot("E:\\Selenium\\","documentCreated"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","documentCreated.jpeg");
				}
				if (selOption ==1) {
					itemSearchTable();
					if (noItemFound == true) {
						noDocument=true;
						return;
					}
					documentUpload = optionSelectedId;
					DocumentOption = "Eliminar";
					takeScreenShot("E:\\Selenium\\","documentSelected"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","documentSelected.jpeg");
					elementClick("ctl00_ContentZone_BtnDelete");
					Thread.sleep(1000);			
					takeScreenShot("E:\\Selenium\\","confirmationMessage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","confirmationMessage.jpeg");
					elementClick("popup_ok");
					Thread.sleep(100);
					takeScreenShot("E:\\Selenium\\","documentDeleted"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","documentDeleted.jpeg");
				}
				
				
				elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
				Thread.sleep(500);
				elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
				Thread.sleep(500);
				elementClick(confirmButton);
				Thread.sleep(2000);
				if (isAlertPresent()==true) {
					alertError = driver.switchTo().alert().getText();					
					driver.switchTo().alert().accept();
				}
				Thread.sleep(2000);
				
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}
}

