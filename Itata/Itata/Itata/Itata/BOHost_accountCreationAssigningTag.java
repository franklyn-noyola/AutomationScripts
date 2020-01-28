package Itata;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static SettingFiles.Itata_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static Itata.BOHost_accountCreationOnly.*;
import static Itata.BOHost_accountCreationWithVehicle.*;
import static TestLink.TestLinkExecution.*;

public class BOHost_accountCreationAssigningTag{	 
			public static int selaccount;
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
			public void accountCreationAssigningTagInit() throws Exception {
				configurationSection("Host",testNameSelected,testNameSelected);				
				testPlanReading(1,0,2,3);
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				
				//Pasos Desde el 1 hasta el 6
				creaciondeCuenta();
				Thread.sleep(200);
				
				//Paso 7.- Hacer Click en el botón Editar
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");// Guardar Cuenta con el botón
				Thread.sleep(200);
				
				//Paso Desde el 8 hasta 12
				accountCreationWithVehicle();
				actualResults.set(11, "Se ha creado el Vehiculo con matrícula: "+matriNu+" correctamente");
				Thread.sleep(500);		
				if (noVehAllowed==true) {
					actualResults.set(10, "No se puede crear Vehiculo debido a: "+errorText);
					for (int i=11;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede crear Vehiculo debido a: "+errorText);					
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;				
				}
				accountCreationAssigningTag();
				Thread.sleep(1000);
				if (errorTagAssignment){
					actualResults.set(16, "ERROR AL ASIGNAR TARJETAS PREPAGO a la cuenta: "+accountNumbr+", "+confirmationMessage);
					Thread.sleep(500);
					driver.close();
					testLink();
					System.out.println("ERROR AL ASIGNAR TARJETAS PREPAGO a la cuenta: "+accountNumbr+", "+confirmationMessage);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;
				}
				actualResults.set(16, "Se ha creado la cuenta "+accountSelectedIta[selaccount]+" con el No. "+accountNumbr+" con un Vehiculo con la matricula "+matriNu+" y la tarjeta prepago asignada No.: "+ tagIdNmbr);
				Thread.sleep(500);
				driver.close();
				testLink();
				System.out.println("Se ha creado la cuenta "+accountSelectedIta[selaccount]+" con el No. "+accountNumbr+" con un Vehiculo con la matricula "+matriNu+" y la tarjeta prepago asignada No.: "+ tagIdNmbr);				
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			}

			public static void accountCreationAssigningTag() throws Exception {
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","tagAssignmentMainPage"+timet+".jpg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentMainPage.jpeg");
				
				//Paso 13.- Hacer Click en el botón Tarjetas prepago
				elementClick("ctl00_ContentZone_BtnSmarts");
				Thread.sleep(500);
				
				//Paso 14.- Seleccionar el Vehículo recién creado
				elementClick("ctl00_ContentZone_chk_0");
				Thread.sleep(500);
				
				//Paso 15.- Hacer click en el botón Asignación
				elementClick("ctl00_ContentZone_btn_token_assignment");
				Thread.sleep(500);
				//Paso 16.- Escribir una tarjeta de prepago válido
				SendKeys("ctl00_ContentZone_txt_pan_token_txt_token_box_data",String.valueOf(ranNumbr(1,99999)));
				Thread.sleep(500);
				
				//Paso 17.- Hacer click en el botón Asignar
				elementClick("ctl00_ContentZone_btn_init_tag");
				Thread.sleep(500);
				confirmationMessage = getText("ctl00_ContentZone_lbl_operation");
				if (confirmationMessage.contains("ya tiene un tag asignado") || confirmationMessage.contains("Este tag no está operativo") || confirmationMessage.contains("Este tag ya está asignado") || confirmationMessage.contains("Luhn incorrecto")){
					errorTagAssignment = true;
					takeScreenShot("E:\\Selenium\\","tagAssignmentPageErr"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPageErr.jpeg");
				
				}else{
					tagIdNmbr = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr[2]/td[6]");
					takeScreenShot("E:\\Selenium\\","tagAssignmentPage"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPage.jpeg");
				}
				Thread.sleep(1000);
	
			}

	}		
	




