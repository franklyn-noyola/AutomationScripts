package R3R5Ciralsa.R3R5Ciralsa;


import static SettingFiles.Generic_Settingsfields_File.*;
import static SettingFiles.R3R5Ciralsa_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import static R3R5Ciralsa.R3R5Ciralsa.BOHost_accountCreationOnly.*;
import static R3R5Ciralsa.R3R5Ciralsa.BOHost_accountCreationWithVehicleWithoutPAN.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BOHost_accountCreationWithVehicleandPAN {		
		public static int titleType;
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
		public void accountCreationWithVehicleandPANInit() throws Exception {				
				configurationSection("Host", testNameSelected, testNameSelected);
				borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				testPlanReading(1,0,2,3);
				//Pasos del 1 al 6
				accountCreation();
				Thread.sleep(1000);
				
				//Paso 7.- Hacer Click en el botón Editar
				elementClick(confirmButton);// Editar Cuenta con el botón
				Thread.sleep(1000);
				
				//Paso del 8 al 14
				vehicleCreation();
				Thread.sleep(1000);
				actualResults.set(10,"Se ha seleccionado el Dispositivo: "+BOHost_accountCreationWithVehicleWithoutPAN.dispositivoSelected);
				actualResults.set(11,"Se ha introducido el PAN No.: "+PAN+" en el campo PAN");
				if (panExist == true) {
					actualResults.set(12, "No se ha podido crear el vehículo con el Dispositivo: "+BOHost_accountCreationWithVehicleWithoutPAN.dispositivoSelected+" causa de: "+errorText);
					actualResults.set(13, "N/A");
					executionResults.set(13, "");
					driver.close();
					testLink();
					System.out.println("No se ha podido crear el vehículo con el Dispositivo: "+BOHost_accountCreationWithVehicleWithoutPAN.dispositivoSelected+" causa de: "+errorText);
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;
				}
				actualResults.set(13, "Se ha creado la cuenta: "+accountNumbr+" correctamente con el vehículo con matricula: "+matriNu+" con el Dispositivo seleccionado: "+BOHost_accountCreationWithVehicleWithoutPAN.dispositivoSelected+" y con el PAN: "+PAN);
				driver.close();
				testLink();
				System.out.println("Se ha creado la cuenta: "+accountNumbr+" correctamente con el vehículo con matricula: "+matriNu+" con el Dispositivo seleccionado: "+BOHost_accountCreationWithVehicleWithoutPAN.dispositivoSelected+" y con el PAN: "+PAN);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));				
		}

			
			
}

