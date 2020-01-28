package R3R5Ciralsa.R3R5Ciralsa;

import static org.junit.Assert.*;

import org.openqa.selenium.interactions.Actions;

import static SettingFiles.Generic_Settingsfields_File.*;
import static SettingFiles.R3R5Ciralsa_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BOHost_tarifasCalendario{		
		private static String [] calenSel = {"Crear","Modificar", "Eliminar", "Copiar"};
		private static String currentCalWindow;
		private static int yearSelect;
		private static boolean noCalendarItem =false;
		private static String dateDesde;
		private static String dateHasta=" ";
		private static String fromDateSelected;
		private static String untilDateSelected;
		private static String dateTypeSelected;
		private static boolean notAction = false;
		private static int opSel;
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
		public void calendarioInit() throws Exception {			
			action = new Actions(driver);		
			opSel = 3;ranNumbr(0,3);
			if (opSel==0) {
				configurationSection("Host", testNameSelected+" - Crear", testNameSelected);
				testPlanReading(14,0,2,3);
			}
			if (opSel==1) {
				configurationSection("Host", testNameSelected+" - Modificar", testNameSelected);
				testPlanReading(14,5,7,8);
			}
			if (opSel==2) {
				configurationSection("Host", testNameSelected+" - Eliminar", testNameSelected);
				testPlanReading(14,10,12,13);
			}
			if (opSel==3) {
				configurationSection("Host", testNameSelected+" - Copiar", testNameSelected);
				testPlanReading(14,15,17,18);
			}
			
			borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");
			try{
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
				Thread.sleep(2000);					
				action.moveToElement(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
				Thread.sleep(200);
				action.moveToElement(driver.findElement(By.linkText("Tarifas & moneda"))).build().perform();
				Thread.sleep(1000);
				pageSelected = "Calendario";
				
				//Paso 3.- Hacer click en Configuración sistema, luego Tarifas & moneda y luego hacer click en Calendario
				elementClick(pageSelected);
				Thread.sleep(1000);
				pageSelectedErr(2);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				}
				takeScreenShot("E:\\Selenium\\","CalendarioPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","CalendarioPage.jpeg");				
				switch (calenSel[opSel]){
					case "Crear":				gestionTarifa_Calendario();														
												if (notAction){
													actualResults.set(5, "No se puede crear Calendario para Tarifas debido a: "+errorText);
													System.out.println("No se puede crear Calendario para Tarifas debido a: "+errorText);													
												}else {
													if (dateHasta.equals(" ")){
															actualResults.set(5,"Se ha Creado el Calendario Festivo de Tarifas correctamente con fecha: "+dateDesde+" hasta "+dateDesde);
															System.out.println("Se ha Creado el Calendario Festivo de Tarifas correctamente con fecha: "+dateDesde+" hasta "+dateDesde);
														}else{
															actualResults.set(5,"Se ha Creado el Calendario Festivo de Tarifas correctamente con fecha desde: "+dateDesde+" hasta: "+dateHasta);
															System.out.println("Se ha Creado el Calendario Festivo de Tarifas correctamente con fecha desde: "+dateDesde+" hasta: "+dateHasta);
														}
												}
												break;
					case "Modificar":			gestionTarifa_Calendario();
												if (noCalendarItem==true) {
													actualResults.set(5,"No se puede modfificar ninguna fecha de calendario porque no existe ningún rango de fecha creado en este año: "+yearSelect);
													for (int i=6;i<actualResults.size();i++) {
														actualResults.set(i, "N/A");
														executionResults.set(i, "");
														
													}
													driver.close();
													testLink();
													System.out.println("No se puede modfificar ninguna fecha de calendario porque no existe ningún rango fecha creado en este año: "+yearSelect);
													System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
													return;
												}
												if (notAction){
													actualResults.set(7,"No se puede Modificar la fecha del Calendario: "+fromDateSelected+" debido a: "+errorText);
													System.out.println("No se puede Modificar la fecha del Calendario: "+fromDateSelected+" debido a: "+errorText);													
												}else {
													actualResults.set(7,"Se ha modificado la fecha del Calendario: "+fromDateSelected+" de fecha hasta: "+untilDateSelected+" con tipo de día: "+dateTypeSelected+" a esta nueva fecha:"+dateDesde+ "y con nuevas fechas Desde: "+dateDesde+" y fecha Hasta: "+dateHasta);
													System.out.println("Se ha modificado la fecha del Calendario: "+fromDateSelected+" de fecha hasta: "+untilDateSelected+" con tipo de día: "+dateTypeSelected+" a esta nueva fecha:"+dateDesde+ "y con nuevas fechas Desde: "+dateDesde+" y fecha Hasta: "+dateHasta);
												}
												break;												
					case "Eliminar":			gestionTarifa_Calendario();		
												if (noCalendarItem==true) {
													actualResults.set(5,"No se puede eliminar ninguna fecha de calendario porque no existe ningún rango de fecha creado en este año: "+yearSelect);
													for (int i=6;i<actualResults.size();i++) {
														actualResults.set(i, "N/A");
														executionResults.set(i, "");
														
													}
													driver.close();
													testLink();
													System.out.println("No se puede eliminar ninguna fecha de calendario porque no existe ningún rango de fecha creado en este año: "+yearSelect);
													System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
													return;
												}
												if (notAction){
													actualResults.set(6, "No se puede Eliminar la fecha del Calendario: "+fromDateSelected+" debido a: "+errorText);
													System.out.println("No se puede Eliminar la fecha del Calendario: "+fromDateSelected+" debido a: "+errorText);																								
												}else {
													actualResults.set(6,"Se ha eliminado la fecha del Calendario: "+fromDateSelected+" de fecha hasta: "+untilDateSelected+" con tipo de día: "+dateTypeSelected);
													System.out.println("Se ha eliminado la fecha del Calendario: "+fromDateSelected+" de fecha hasta: "+untilDateSelected+" con tipo de día: "+dateTypeSelected);
												}
												break;
					case "Copiar":				gestionTarifa_Calendario();
												if (notAction){
													actualResults.set(5,"No se ha podido copiar el Calendario del año anterior: "+(yearSelect-1) +" a este año "+yearSelect+" debido a: "+errorText);
													System.out.println("No se ha podido copiar el Calendario del año anterior: "+(yearSelect-1) +" a este año "+yearSelect+" debido a: "+errorText);
																										
												}else {
													actualResults.set(5,"Se han copiado las fechas del Calendario anterior: "+(yearSelect-1)+" a este año: "+yearSelect+" correctamente");
													System.out.println("Se han copiado las fechas del Calendario anterior: "+(yearSelect-1)+" a este año: "+yearSelect+" correctamente");	
												}												
												break;
				}
				driver.close();
				testLink();
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				
			}catch (Exception e){				
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
		
		public static void gestionTarifa_Calendario() throws Exception{
				Thread.sleep(1000);
				yearSelect = ranNumbr(2013,2040);
			try{
				//TC BOHost_tarifasCalendario - Modificar; BOHost_tarifasCalendario - Eliminar; BOHost_tarifasCalendario - Copiar
				//Paso 4.- Ya en la pantalla de Calendario, buscar un año deseado antes o después, haciendo click en el bón del año deseado
				if (calenSel[opSel].equals("Modificar") || calenSel[opSel].equals("Eliminar")  || calenSel[opSel].equals("Copiar")){
					currentCalWindow = getText("ctl00_ContentZone_LblYear");		
					do {
						
						if (yearSelect<Integer.parseInt(currentCalWindow)) {
							elementClick("ctl00_ContentZone_BtnPrev");
							currentCalWindow = getText("ctl00_ContentZone_LblYear");	
						}else {
							elementClick("ctl00_ContentZone_BtnNext");
							currentCalWindow = getText("ctl00_ContentZone_LblYear");
						}
						
					  if (Integer.parseInt(currentCalWindow)==yearSelect){							  	
					  		WebElement calcTable = driver.findElement(By.id("ctl00_ContentZone_TblResults"));					  	
					  		List <WebElement> caclTableItems = calcTable.findElements(By.tagName("tr"));
					  		Thread.sleep(1000);								  		
					  		//TC BOHost_tarifasCalendario - Modificar; BOHost_tarifasCalendario - Eliminar
					  		//Paso 5.- Si hay fechas disponibles del año seleccionado, seleccionar cualquier fecha
					  		if (caclTableItems.size()<2 && (calenSel[opSel].equals("Modificar") || calenSel[opSel].equals("Eliminar")) ) {					  			
					  			noCalendarItem = true;			
					  			return;
					  		}else {
					  			if (calenSel[opSel].equals("Modificar") || calenSel[opSel].equals("Eliminar")){					  			
					  				int rowSelected = ranNumbr(2,caclTableItems.size());
					  				for (int i=2;i<=rowSelected;i++) {
					  					if (i==rowSelected) {
					  						elementClick("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+i+"]/td[1]/input");
					  						fromDateSelected = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+i+"]/td[2]/");
					  						untilDateSelected = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+i+"]/td[3]/");
					  						dateTypeSelected = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+i+"]/td[4]/");
					  					}
					  				}									    		
					  			}
					  		}
					  		
					  }					  
					  Thread.sleep(500);
					  currentCalWindow = getText("ctl00_ContentZone_LblYear");							 
				} while (Integer.parseInt(currentCalWindow)<yearSelect || Integer.parseInt(currentCalWindow)>yearSelect);
					
			}	
						
				if (calenSel[opSel].equals("Eliminar")){										
					Thread.sleep(1000);
					//TC BOHost_tarifasCalendario - Eliminar: Paso 6.- Hacer click en el botón Eliminar
					elementClick("ctl00_ContentZone_BtnDelete");
					Thread.sleep(1000);
					if (driver.getPageSource().contains("Solo se pueden eliminar días futuros")){
						errorText = getText("toast-message");
						notAction = true;
						return;
					}else{
						//TC BOHost_tarifasCalendario - Eliminar: Paso 7.- Hacer click en el botón Confirmar
						elementClick("popup_ok");
						Thread.sleep(2000);
					}
					return;
				}
				
				if (calenSel[opSel].equals("Copiar")){
					
					//TC BOHost_tarifasCalendario - Copiar: Paso 5.- Hacer click en el botón Copiar del año anterior
					if (driver.findElement(By.id("ctl00_ContentZone_BtnCopyDays")).isEnabled()) {
						elementClick("ctl00_ContentZone_BtnCopyDays");
					}else {
						errorText = "No se puede copiar los días de rangos de fechas anteriores al año actual";
						notAction = true;
						return;
					}																		
					Thread.sleep(1000);
					//TC BOHost_tarifasCalendario - Copiar: Paso 5.- Hacer click en el Confirmar
					elementClick("popup_ok");
					Thread.sleep(2000);
					if (driver.getPageSource().contains("No se pueden copiar los días porque el año") || driver.getPageSource().contains("No existen días a copiar para el año")){
							errorText = getText("toast-container");
							notAction = true;
							return;
					}
				}
				
				if (calenSel[opSel].equals("Crear")|| calenSel[opSel].equals("Modificar")){
					if (calenSel[opSel].equals("Crear")){
						
						//TC BOHost_tarifasCalendario - Crear: Paso 4.- Hacer Click en el botón Crear
						elementClick("ctl00_ContentZone_BtnCreate");
						Thread.sleep(1000);
					}
					if (calenSel[opSel].equals("Modificar")){
						//TC BOHost_tarifasCalendario - Modificar: Paso 6.- Hacer click en el botón Modificar
						elementClick("ctl00_ContentZone_BtnModify");
						Thread.sleep(1000);
						if (driver.getPageSource().contains("Solo se pueden modificar días futuros") || driver.getPageSource().contains("El rango de fechas introducido se solapa con")){
							errorText = getText("toast-message");
							notAction = true;
							return;
						}						
					}
				
					//TC BOHost_tarifasCalendario - Crear: Paso 5.- Introducir los datos correspondietnes
					//TC BOHost_tarifasCalendario - Modificar: Paso 7.- Introducir los datos correspondientes
					SendKeys("ctl00_ContentZone_dt_from_box_date", dateSel(2013,2040));
					Thread.sleep(500);
					dateDesde = driver.findElement(By.id("ctl00_ContentZone_dt_from_box_date")).getAttribute("value"); 				
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ChkTo");
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dt_to_box_date", dateSel(2019,2040));
						Thread.sleep(500);
						dateHasta = driver.findElement(By.id("ctl00_ContentZone_dt_to_box_date")).getAttribute("value");
					}
					selectDropDown("ctl00_ContentZone_CmbType");
					
					takeScreenShot("E:\\Selenium\\","CalendarioFilledPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","CalendarioFilledPage.jpeg");
					Thread.sleep(500);
					
					//TC BOHost_tarifasCalendario - Crear: Paso 6.- Hacer Click en el botón Confirmar
					//TC BOHost_tarifasCalendario - Modificar: Paso 8.- Hacer click en el botón Confirmar
					elementClick("ctl00_ContentZone_BtnSubmit");
					Thread.sleep(1000);				
					if (driver.getPageSource().contains("Por favor, introduzca una fecha futura") || driver.getPageSource().contains("Fecha 'hasta' no puede ser anterior a 'desde'") || driver.getPageSource().contains("El rango de fecha introducido se solapa") || driver.getPageSource().contains("El rango de fechas introducido se solapa con")){
						notAction = true;
						errorText = getText("//*[@id='toast-container']/div/div");
						return;
					}
				}
				Thread.sleep(3000);
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());			
				throw(e);
		
			}
		
		}		
}		