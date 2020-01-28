package CoviHonduras.CoviHonduras;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static SettingFiles.CoviHonduras_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import static CoviHonduras.CoviHonduras.CAC_accountCreationOnly.*;
import static CoviHonduras.CoviHonduras.CAC_accountCreationWithVehicle.*;

public class CAC_accountCreationAssigningTag{
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
		public void accountCreationAssigningTagInit() throws Exception {
			configurationSection("CAC",testNameSelected,testNameSelected);			
			testPlanReading(3,0,2,3);
			borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");
			accountCreation();
			Thread.sleep(200);
			elementClick("ctl00_ButtonsZone_BtnValidate_IB_Button");// Guardar Cuenta con el botón
			Thread.sleep(200);
			accountCreationWithVehicle();
			Thread.sleep(500);
			
			accountCreationAssigningTag();
			Thread.sleep(1000);
			if (errorTagAssignment){
				actualResults.set(15, "ERROR AL ASIGNAR TAG a la cuenta: "+accountNumbr+", "+confirmationMessage);			
				System.out.println("ERROR AL ASIGNAR TAG a la cuenta: "+accountNumbr+", "+confirmationMessage);			
			}else {
				actualResults.set(15, "Se ha creado la cuenta: "+accountNumbr+" con un Vehiculo con la matricula "+matriNu+" y el tag asignado No.: "+ tagIdNmbr);
				System.out.println("Se ha creado la cuenta: "+accountNumbr+" con un Vehiculo con la matricula "+matriNu+" y el tag asignado No.: "+ tagIdNmbr);
			}
			driver.close();
			testLink();
			Thread.sleep(3000);
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
		}

		public static void accountCreationAssigningTag() throws Exception {
			try {
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","tagAssignmentMainPage"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentMainPage.jpeg");
			
				//Paso 12.- Hacer Click en el botón Telepeajes
				elementClick("ctl00_ContentZone_BtnTags");
				Thread.sleep(500);
			
				//Paso 13.- Seleccionar el Vehículo recién creado
				elementClick("ctl00_ContentZone_chk_0");
				Thread.sleep(500);
			
				//Paso 14.- Hacer click en el botón Asignación
				elementClick("ctl00_ContentZone_btn_token_assignment");
				Thread.sleep(500);
				
				//Paso 15.- Escribir un tag válido
				SendKeys("ctl00_ContentZone_txt_pan_token_txt_token_box_data",String.valueOf(ranNumbr(1,99999)));
				Thread.sleep(1000);
				
				//Paso 16.- Hacer click en el botón Asignar
				elementClick("ctl00_ContentZone_btn_init_tag");
				Thread.sleep(500);
				confirmationMessage = getText("ctl00_ContentZone_lbl_operation");
				if (confirmationMessage.contains("ya tiene un tag asignado") || confirmationMessage.contains("Este tag no está operativo") || confirmationMessage.contains("Este tag ya está asignado") || confirmationMessage.contains("Luhn incorrecto")){
						errorTagAssignment = true;
						takeScreenShot("E:\\Selenium\\","tagAssignmentPageErr"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPageErr.jpeg");
						return;
					}else{
						tagIdNmbr = getText("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr[2]/td[6]");
						takeScreenShot("E:\\Selenium\\","tagAssignmentPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tagAssignmentPage.jpeg");
					}
				Thread.sleep(1000);
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}


}