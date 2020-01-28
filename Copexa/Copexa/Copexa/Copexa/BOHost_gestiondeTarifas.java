package Copexa.Copexa;

import static org.junit.Assert.*;
import static SettingFiles.Copexa_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BOHost_gestiondeTarifas {	
	private static String [] maingestionOptions = {"Crear", "Crear copia", "Editar", "Eliminar"};
	private static String [] secondOptions = {"Crear", "Modificar", "Eliminar"};
	private static int selOpt;
	private static boolean noAble = false;
	private static String tarifaSelected;
	private static int selOpt2;
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
	public void gestiondeTarifasInit() throws Exception{
		configurationSection("Host", testNameSelected, testNameSelected);
		testPlanReading(10,0,2,3);
		selOpt = ranNumbr(0,maingestionOptions.length-1);
		if (selOpt<0) {
			selOpt=0;
		}
		selOpt2 = ranNumbr(0,2);
		if (selOpt2<0) {
			selOpt2=0;
		}
		Thread.sleep(1000);	
		gestiondeTarifas();
		Thread.sleep(1000);
		if (noAble ==true) {
			actualResults.set(4, "No se puede "+maingestionOptions[selOpt] +" la tarifa con fecha de activación: "+tarifaSelected+" debido a: "+errorText);
			for (int i=5;i<actualResults.size();i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}
			driver.close();
			testLink();
			System.out.println("No se puede "+maingestionOptions[selOpt] +" la tarifa con fecha de activación: "+tarifaSelected+" debido a: "+errorText);
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			return;		
		}
		if (maingestionOptions[selOpt].equals("Eliminar")) {			
			actualResults.set(4, "Aparece un popup indicando si estás seguro que quiere borrar la tarifa seleccionada? y al hacer click en el botón Aceptar se ha eliminado la tarifa con fecha de Activación: "+tarifaSelected+" correctamente");
			for (int i=5;i<actualResults.size();i++) {
				actualResults.set(i, "N/A");
				executionResults.set(i, "");
				
			}
			driver.close();
			testLink();
			System.out.println("Se ha eliminado la tarifa con fecha de Activación: "+tarifaSelected+" correctamente");
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			return;
		}
		if (maingestionOptions[selOpt].equals("Crear")) {
			actualResults.set(6, "Se ha creado la tarifa con fecha de Activación: "+tarifaSelected+" correctamente");
			actualResults.set(7, "N/A");
			executionResults.set(7, "");
			driver.close();
			testLink();
			System.out.println("Se ha creado la tarifa con fecha de Activación: "+tarifaSelected+" correctamente");
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			return;
			
		}
		if (maingestionOptions[selOpt].equals("Crear copia") || maingestionOptions[selOpt].equals("Editar")) {
			if (secondOptions[selOpt2].equals("Crear")) {
				actualResults.set(7,"Se ha creado una tarifa desde: "+maingestionOptions[selOpt]+" en la tarifa con fecha de Activación: "+tarifaSelected+" correctamente");
				System.out.println("Se ha creado una tarifa desde: "+maingestionOptions[selOpt]+" en la tarifa con fecha de Activación: "+tarifaSelected+" correctamente");
			}
			if (secondOptions[selOpt2].equals("Modificar")) {
				actualResults.set(7,"Se ha Modificado una tarifa desde: "+maingestionOptions[selOpt]+" en la tarifa con fecha de Activación: "+tarifaSelected+" correctamente");
				System.out.println("Se ha Modificado una tarifa desde: "+maingestionOptions[selOpt]+" en la tarifa con fecha de Activación: "+tarifaSelected+" correctamente");
			}
			if (secondOptions[selOpt2].equals("Eliminar")) {
				actualResults.set(7,"Se ha Eliminado una tarifa desde: "+maingestionOptions[selOpt]+" en la tarifa con fecha de Activación: "+tarifaSelected+" correctamente");
				actualResults.set(6, "N/A");
				executionResults.set(6, "");
				System.out.println("Se ha Eliminado una tarifa desde: "+maingestionOptions[selOpt]+" en la tarifa con fecha de Activación: "+tarifaSelected+" correctamente");
			}			
		}
		driver.close();
		testLink();
		System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
		
	}
	
	public static void gestiondeTarifas() throws Exception{
		action = new Actions(driver);			
		borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
		try{
			//Paso 1.- Entrar a la página de Login del BackOffice de Copexa
			driver.get(BoHostUrl);
			takeScreenShot("E:\\Selenium\\","loginBOCopexaPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOCopexaPage.jpeg");
			loginPageErr();
			if (pageSelectedErr==true) {
				testLink();
				System.err.println("Un error ha ocurrido en la Página de Inicio");
				fail("Un error ha ocurrido en la Página de Inicio");
			}
			
			//Paso 2.- Loguearse con el usuario 00001/00001	
			loginPage("00001","00001");
			takeScreenShot("E:\\Selenium\\","homeBOCopexaPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOCopexaPage.jpeg");
			applicationVer = getText("ctl00_statusRight");
			Thread.sleep(500);
			
			//Paso 3.- Ir a la pantalla Gestión de Tarifas
			action.moveToElement(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
			Thread.sleep(500);
			action.moveToElement(driver.findElement(By.linkText("Tarifas & Moneda"))).build().perform();
			pageSelected = "Gestión de tarifas";
			elementClick(pageSelected);
			Thread.sleep(1000);
			pageSelectedErr(2);
			if (pageSelectedErr==true) {
				driver.close();
				testLink();
				System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
			}
			
			takeScreenShot("E:\\Selenium\\","gestionTarifasMainPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","gestionTarifasMainPage.jpeg");
			
			//Paso 4.- Elegir o Hacer click en el botón correspondiente. Si ha elegido Eliminar, Editar o Crear Copia debe elegir el registro correspondiente en la lista desplegable Activación, si has elegido Crear: debes poner una fecha y hora en el capo Creación de Tarifas
			actualResults.set(3, "Se ha hecho click en el botón "+maingestionOptions[selOpt]);
			switch (maingestionOptions[selOpt]) {
			
				case "Crear":					//Paso 5.- Si ha elegido o ha hecho click Crear, aparecerá en la pantalla de creación de una nueva tarifa.
												actualResults.set(4, "Aparece la pantalla creación de una nueva tarifa");
												String dateSelected = dateSel(2018,2021);
												String hourSelected = hourFormat(00,23,00,59);
												tarifaSelected = dateSelected+" "+hourSelected;
												SendKeys("ctl00_ContentZone_dt_newTime_box_date",dateSelected);
												SendKeys("ctl00_ContentZone_dt_newTime_box_hour",hourSelected);
												Thread.sleep(500);
												elementClick("ctl00_ContentZone_BtnCreate");
												Thread.sleep(1000);
												if (driver.getPageSource().contains("introduzca una fecha futura") || driver.getPageSource().contains("Ya existe un grupo de tarifa")) {
													errorText = getText("ctl00_LblError");
													noAble = true;
													takeScreenShot("E:\\Selenium\\","tarifaNoCreated"+timet+".jpeg");
													takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tarifaNoCreated.jpeg");
													return;
												}
												crearTarifas();				
												break;												
				case "Crear copia":				//Paso 5.- Si ha elegido o ha hecho click "Crear Copia" o Editar. aparecerá la siguiente pantalla de creación/edición de Gestión de Tarifas, con tres el listado de tarifas actuales y tres botones Crear Modificar y Eliminar
												actualResults.set(4, "Aparece la siguiente pantalla de creación/edición de Gestión de Tarifas, con el listado de tarifas actuales y tres botones Crear Modificar y Eliminar");
												selectDropDown("ctl00_ContentZone_CmbDates");												
												dateSelected = dateSel(2018,2021);
												hourSelected = hourFormat(00,23,00,59);
												tarifaSelected = dateSelected+" "+hourSelected;
												SendKeys("ctl00_ContentZone_dt_newTime_box_date",dateSelected);
												SendKeys("ctl00_ContentZone_dt_newTime_box_hour",hourSelected);
												Thread.sleep(500);
												elementClick("ctl00_ContentZone_ButtonClone");
												Thread.sleep(1000);
												if (driver.getPageSource().contains("introduzca una fecha futura") || driver.getPageSource().contains("Ya existe un grupo de tarifa")) {
													errorText = getText("ctl00_LblError");
													noAble = true;
													takeScreenShot("E:\\Selenium\\","tarifaNoCreated"+timet+".jpeg");
													takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tarifaNoCreated.jpeg");
													return;
												}
												crearEditarTarifas();
												break;												
				case "Editar":					//Paso 5.- Si ha elegido o ha hecho click "Crear Copia", Crear o Editar. aparecerá la siguiente pantalla de creación/edición de Gestión de Tarifas, con tres el listado de tarifas actuales y tres botones Crear Modificar y Eliminar
												actualResults.set(4, "Aparece la siguiente pantalla de creación/edición de Gestión de Tarifas, con el listado de tarifas actuales y tres botones Crear Modificar y Eliminar");
												selectDropDown("ctl00_ContentZone_CmbDates");
												elementClick("ctl00_ContentZone_BtnEdit");
												crearEditarTarifas();
												break;
				case "Eliminar":				//Paso 5.- Si ha hecho click en el boton Eliminar, aparecerá el popup indicando si quiere borrar la tarifa seleccionada, hacer click en el botón Aceptar
												selectDropDown("ctl00_ContentZone_CmbDates");
												WebElement tarifaSel = new Select(driver.findElement(By.id("ctl00_ContentZone_CmbDates"))).getFirstSelectedOption();
												tarifaSelected = tarifaSel.getText();
												elementClick("ctl00_ContentZone_BtnDelete");
												if (isAlertPresent()) {
													driver.switchTo().alert().accept();
												}
												if (driver.getPageSource().contains("Solo se pueden eliminar tarifas futuras")) {
													errorText = getText("ctl00_LblError");
													noAble = true;
													takeScreenShot("E:\\Selenium\\","tarifaNoDeleted"+timet+".jpeg");
													takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tarifaNoDeleted.jpeg");
													return;
												}
												takeScreenShot("E:\\Selenium\\","tarifaDeleted"+timet+".jpeg");
												takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","tarifaDeleted.jpeg");
												Thread.sleep(3000);												
												break;
												
												
			}			
			Thread.sleep(1000);
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}				
		
	}
	
	public static void crearTarifas() throws Exception{
		try {	
			//Paso 6.- Si estás en la pantalla de creación de tarifas, llenar todos los campos correspondientes y hacer click en el botón Confirmar, si estás en la segunda pantalla gestión de tarifas, hacer click en Crear, Modificar o Eliminar (primero se elige una tarifa existente en el listado), si has elegidido Crear o Modificar aparecer la pantalla de creación/modificación de tarifas, llenar o modificar los campos correspondientes y hacer click en el botón Confirmar, si has elegido eliminar, aparecerá un popup y hacer click en el botón Aceptar
			actualResults.set(5, "Se ha completado todos los campos correspondientes");
			takeScreenShot("E:\\Selenium\\","crearTarifaPage"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","crearTarifaPage.jpeg");
			String tarifaSelect = getText("ctl00_ContentZone_LblActivationTime");
			tarifaSelected = tarifaSelect.substring(tarifaSelect.indexOf("n:")+3);
			if (ranNumbr(0,1)==0) {
				elementClick("ctl00_ContentZone_ChkCompany");
			}
			if (ranNumbr(0,1)==0) {
				elementClick("ctl00_ContentZone_ChkPlaza");
			}
			if (ranNumbr(0,1)==0) {
				elementClick("ctl00_ContentZone_ChkLaneDir");
				selectDropDown("ctl00_ContentZone_CmbLaneDir");
			}
			if (ranNumbr(0,1)==0) {
				elementClick("ctl00_ContentZone_ChkLaneType");
				selectDropDown("ctl00_ContentZone_CmbLaneType");
			}
			if (ranNumbr(0,1)==0) {
				elementClick("ctl00_ContentZone_ChkWeekDay");
				selectDropDown("ctl00_ContentZone_CmbWeekDay");
			}
			if (ranNumbr(0,1)==0) {
				elementClick("ctl00_ContentZone_ChkDayType");
			}
			if (ranNumbr(0,1)==0) {
				elementClick("ctl00_ContentZone_ChkInitialHour");
				SendKeys("ctl00_ContentZone_BoxInitialHour",hourFormat2(0,23,0,59,0,59));
			}
			if (ranNumbr(0,1)==0) {
				elementClick("ctl00_ContentZone_ChkEndHour");
				SendKeys("ctl00_ContentZone_BoxEndHour",hourFormat2(0,23,0,59,0,59));
			}
			if (ranNumbr(0,1)==0) {
				elementClick("ctl00_ContentZone_ChkClass");
				selectDropDown("ctl00_ContentZone_CmbClass");
			}
			double tar = 254;ranNumbr(100,700);
			int percentage = 10;ranNumbr(10,20);
			double res = tar * percentage / 100;
			int result = (int)tar+(int)res;
			String result2 = String.valueOf(result)+"00";		
			String percent = String.valueOf(res);
			if (percent.substring(percent.indexOf(".")+1).length()<2) {			
				percent = percent.concat("0");
			}
			percent = percent.replace(".","");
			SendKeys("ctl00_ContentZone_BoxAmount",result2);
			SendKeys("ctl00_ContentZone_BoxVat",percent);
			SendKeys("ctl00_ContentZone_BoxVatPct",percentage+"00");
			takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataFilled.jpeg");
			
			//Paso 7.- Hacer click en el botón Confirmar
			elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
			Thread.sleep(5000);
			
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}				
		
	}
	public static void crearEditarTarifas() throws Exception{
		try {
			//Paso 6.- Si estás en la pantalla de creación de tarifas, llenar todos los campos correspondientes y hacer click en el botón Confirmar, si estás en la segunda pantalla gestión de tarifas, hacer click en Crear, Modificar o Eliminar (primero se elige una tarifa existente en el listado), si has elegidido Crear o Modificar aparecer la pantalla de creación/modificación de tarifas, llenar o modificar los campos correspondientes y hacer click en el botón Confirmar, si has elegido eliminar, aparecerá un popup y hacer click en el botón Aceptar			
			Thread.sleep(2000);
			takeScreenShot("E:\\Selenium\\","cloneEditTarifa"+timet+".jpeg");
			takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","cloneEditTarifa.jpeg");
			if (secondOptions[selOpt2].equals("Crear")) {
				actualResults.set(5,"En la segunda pantalla de gestión de tarifas, se ha hecho click en el botón Crear y estamos en la creación de una tarifa y se llena todos los campos correctamente");
				elementClick("ctl00_ContentZone_BtnCreate");
				crearTarifas();
				elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
				return;
			}			 
			tarifaSelected = driver.findElement(By.id("ctl00_ContentZone_txt_ActivationTime_box_data")).getAttribute("value");			
			itemSearchandSelect();
			if (secondOptions[selOpt2].equals("Eliminar")) {
				actualResults.set(5,"En la segunda pantalla de gestión de tarifas, se ha hecho click en el botón Eliminar, aparece un popup y se ha dado click al botón aceptar");
				elementClick("ctl00_ContentZone_BtnDelete");
				if(isAlertPresent()) {
					driver.switchTo().alert().accept();
				}
				Thread.sleep(4000);
				
			//Paso 8.- Hacer click en el botón Volver si estás en la segunda pantalla de gestión de tarifas
				elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
				return;
			}

			if (secondOptions[selOpt2].equals("Modificar")){
				actualResults.set(5,"En la segunda pantalla de gestión de tarifas, se ha hecho click en el botón Modificar y estamos en la modificación de una tarifa y se modifica todos los campos correctamente");
				elementClick("ctl00_ContentZone_BtnModify");
				Thread.sleep(1000);
				if (driver.findElement(By.id("ctl00_ContentZone_ChkCompany")).isSelected()) {
					elementClick("ctl00_ContentZone_ChkCompany");
				}else {
					elementClick("ctl00_ContentZone_ChkCompany");
				}
				if (driver.findElement(By.id("ctl00_ContentZone_ChkPlaza")).isSelected()) {
					elementClick("ctl00_ContentZone_ChkPlaza");
				}else {
					elementClick("ctl00_ContentZone_ChkPlaza");
				}
				if (driver.findElement(By.id("ctl00_ContentZone_ChkLaneDir")).isSelected()) {
					elementClick("ctl00_ContentZone_ChkLaneDir");
				}else {
					elementClick("ctl00_ContentZone_ChkLaneDir");
					selectDropDown("ctl00_ContentZone_CmbLaneDir");
				}
				if (driver.findElement(By.id("ctl00_ContentZone_ChkLaneType")).isSelected()) {
					elementClick("ctl00_ContentZone_ChkLaneType");
				}else {
					elementClick("ctl00_ContentZone_ChkLaneType");
					selectDropDown("ctl00_ContentZone_CmbLaneType");
				}
				if (driver.findElement(By.id("ctl00_ContentZone_ChkWeekDay")).isSelected()) {
					elementClick("ctl00_ContentZone_ChkWeekDay");
				}else {
					elementClick("ctl00_ContentZone_ChkWeekDay");
					selectDropDown("ctl00_ContentZone_CmbWeekDay");
				}
				if (driver.findElement(By.id("ctl00_ContentZone_ChkDayType")).isSelected()) {
					elementClick("ctl00_ContentZone_ChkDayType");
				}else {
					elementClick("ctl00_ContentZone_ChkDayType");				
				}
				if (driver.findElement(By.id("ctl00_ContentZone_ChkInitialHour")).isSelected()) {
					elementClick("ctl00_ContentZone_ChkInitialHour");
				}else {
					elementClick("ctl00_ContentZone_ChkInitialHour");	
					SendKeys("ctl00_ContentZone_BoxInitialHour",hourFormat2(0,23,0,59,0,59));
				}
				if (driver.findElement(By.id("ctl00_ContentZone_ChkEndHour")).isSelected()) {
					elementClick("ctl00_ContentZone_ChkEndHour");
				}else {
					elementClick("ctl00_ContentZone_ChkEndHour");	
					SendKeys("ctl00_ContentZone_BoxEndHour",hourFormat2(0,23,0,59,0,59));
				}
				if (driver.findElement(By.id("ctl00_ContentZone_ChkClass")).isSelected()) {
					elementClick("ctl00_ContentZone_ChkClass");
				}else {
					elementClick("ctl00_ContentZone_ChkClass");
					selectDropDown("ctl00_ContentZone_CmbClass");
				}
				double tar = ranNumbr(100,700);
				int percentage = ranNumbr(10,20);
				double res = tar * percentage / 100;
				int result = (int)tar+(int)res;
				String result2 = String.valueOf(result)+"00";		
				String percent = String.valueOf(res);
				if (percent.substring(percent.indexOf(".")+1).length()<2) {			
					percent = percent.concat("0");
				}
				percent = percent.replace(".","");
				SendKeys("ctl00_ContentZone_BoxAmount",result2);
				SendKeys("ctl00_ContentZone_BoxVat",percent);
				SendKeys("ctl00_ContentZone_BoxVatPct",percentage+"00");
				takeScreenShot("E:\\Selenium\\","dataModified"+timet+".jpeg");
				takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataModified.jpeg");
				
				//Paso 7.- Hacer click en el botón Confirmar
				elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
				Thread.sleep(5000);
				
				//Paso 8.- Hacer click en el botón Volver si estás en la segunda pantalla de gestión de tarifas
				elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
				return;
			}
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
	}
	

}