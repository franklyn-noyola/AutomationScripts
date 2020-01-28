package R3R5Ciralsa.R3R5Ciralsa;

import static org.junit.Assert.*;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import static SettingFiles.Generic_Settingsfields_File.*;
import static SettingFiles.R3R5Ciralsa_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class BOHost_gestionDescuento{		
		private static String [] gestionDescOption = {"Crear", "Modificar", "Eliminar"};
		private static String OptionSel;
		private static String descuentoNombre;
		private static int opSel;
		public static boolean noDeleted = false;
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
		public void gestionDescuento() throws Exception {		
			action = new Actions(driver);
			borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");
			opSel = ranNumbr(0,2);
			if (opSel==0) {
				configurationSection("Host", testNameSelected+" - Crear", testNameSelected);
				testPlanReading(7,0,2,3);
			}
			if (opSel==1) {
				configurationSection("Host", testNameSelected+" - Modificar", testNameSelected);
				testPlanReading(7,5,7,8);
			}
			if (opSel==2) {
				configurationSection("Host", testNameSelected+" - Eliminar", testNameSelected);
				testPlanReading(7,10,12,13);
			}
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
			
			//Paso 3.- Hacer click en Configuración Sistema y luego Gestión de descuentos
			action.moveToElement(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
			Thread.sleep(1000);		
			pageSelected = "Gestión de descuentos";
			elementClick(pageSelected);
			Thread.sleep(3000);
			pageSelectedErr(2);
			if (pageSelectedErr==true) {
				driver.close();
				testLink();
				System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
			}
			takeScreenShot("E:\\Selenium\\","gestionDescuentopage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","gestionDescuentopage.jpeg");
			Thread.sleep(3000);
			if (gestionDescOption[opSel].equals("Modificar") || gestionDescOption[opSel].equals("Eliminar")) {
				//TC BOHost_gestionDescuento - Modificar y TC BOHost_gestionDescuento - Eliminar:
				//Paso 4.- Ya en pantalla de Gestión de descuentos, si hay decuentos disponibles, seleccionar cualquier descuento en la lista 
				discountSearch();
				if (noItemFound==true) {
					takeScreenShot("E:\\Selenium\\"," noDiscounts"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","noDiscounts.jpeg");
					actualResults.set(3, "No se puede "+gestionDescOption[opSel]+" ningún descuento, ya que no hay descuentos disponibles");
					for (int i=4;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede "+gestionDescOption[opSel]+" ningún descuento, ya que no hay descuentos disponibles");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;
				}else {
					actualResults.set(3,"Se ha seleccionado el Descuento: "+optionSelectedId);
				}
				
				
				
			}
			
			switch (gestionDescOption[opSel]){
				case "Crear":		OptionSel = "ctl00_ContentZone_BtnCreate";
									crear_modificarDescuento();
									actualResults.set(5,"Se ha Creado el Descuento: "+descuentoNombre+" correctamente");
									System.out.println("Se ha Creado el Descuento: "+descuentoNombre+" correctamente");
									Thread.sleep(1000);
									break;
				case "Modificar":	OptionSel = "ctl00_ContentZone_BtnModify";
									crear_modificarDescuento();
									actualResults.set(6,"Se ha Modificado el Descuento: "+optionSelectedId+" correctamente");
									System.out.println("Se ha Modificado el Descuento: "+optionSelectedId+" correctamente");
									break;
				case "Eliminar": 	OptionSel = "ctl00_ContentZone_BtnDelete";
									eliminarDescuento();
									if (noDeleted==true) {
										actualResults.set(5,"No se puede eliminar el Descuento debido a: "+errorText);
										driver.close();
										testLink();
										System.out.println("No se puede eliminar el Descuento debido a: "+errorText);
										System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
										return;
									}
									actualResults.set(5, "Se ha Eliminado el Descuento: "+optionSelectedId+" correctamente");
									System.out.println("Se ha Eliminado el Descuento: "+optionSelectedId+" correctamente");
									break;								
			}
			driver.close();
			testLink();
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			
		}
		
		public static void crear_modificarDescuento() throws Exception {
			action = new Actions(driver);			
			try{				
				if (gestionDescOption[opSel].equals("Modificar")){
					//TC BOHost_gestionDescuento - Modificar: Paso 5.-  Una vez, seleccionado el descuento, hacer click en el botón Modificar
					elementClick(OptionSel);
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","modifyDescuentoPagina"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\",",modifyDescuentoPagina.jpeg");
				}			
							
				if (gestionDescOption[opSel]=="Crear"){
					
					//TC BOHost_gestionDescuento - Crear: Paso 4.- Hacer click en el botón Crear
					elementClick(OptionSel);
					takeScreenShot("E:\\Selenium\\","crearDescuentoPagina"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","crearDescuentoPagina.jpeg");
					int opSel = ranNumbr(0,nameOp.length-1);
					descuentoNombre = "DESCUENTO_".concat(nameOp[opSel]);
					SendKeys("//*[@id='discountDialog']/div/div[1]/input[1]",descuentoNombre); //Nombre
					Thread.sleep(100);
				}
				
				//TC BOHost_gestionDescuento - Crear: Paso 5.- Llenar todos los campos correspondientes
				//TC BOHost_gestionDescuento - Modificar: Paso 6.- Una vez en la pantalla de edición de Descuento, modificar los datos correspondientes
				SendKeys("//*[@id='discountDialog']//div/div[1]/input[2]","Descuento hecho para: "+descuentoNombre); //Descripción
				Thread.sleep(100);
				selectDropDownV("//*[@id='discountDialog']/div/div[2]/div[1]/select[1]"); //Tipo Valor Lista Desplegable
				Thread.sleep(100);
				WebElement optionSelectedD = new Select (driver.findElement(By.xpath("//*[@id='discountDialog']/div/div[2]/div[1]/select[1]"))).getFirstSelectedOption();
				String optionSelectedDropDown = optionSelectedD.getText();
				if (optionSelectedDropDown.equals("Porcentaje")) {
					action.click(driver.findElement(By.id("ctl00_ContentZone_valuePercent_txt_formated"))).build().perform();
					action.sendKeys(String.valueOf(ranNumbr(100,10000))).build().perform();
				}else {
					action.click(driver.findElement(By.id("ctl00_ContentZone_moneyPercent_txt_formated"))).build().perform();
					action.sendKeys(String.valueOf(ranNumbr(1000,100000))).build().perform();
				}
				Thread.sleep(200);
				selectDropDownV("//*[@id='discountDialog']/div/div[2]/div[1]/select[2]"); //Base de Aplicación lista desplegable
				Thread.sleep(200);				
				selectDropDownV("//*[@id='discountDialog']/div/div[2]/div[2]/select"); //Momento de aplicación
				Thread.sleep(100);
				selectDropDown("cmbDlgDiscountType"); //Tipo lista desplegable
				optionSelectedD = new Select (driver.findElement(By.id("cmbDlgDiscountType"))).getFirstSelectedOption();
				optionSelectedDropDown = optionSelectedD.getText();
				Thread.sleep(1000);
				if (optionSelectedDropDown.equals("Por recurrencia")){
					SendKeys("txtDlgvehicleNumMin",String.valueOf(ranNumbr(1,20)));
					Thread.sleep(100);
					SendKeys("txtDlgvehicleNumMax",String.valueOf(ranNumbr(21,100)));
					Thread.sleep(100);
					selectDropDownV("//*[@id='discountType']/div[3]/select");
					Thread.sleep(100);
					selectDropDownV("//*[@id='discountType']/div[4]/select");					
					}
				Thread.sleep(100);
				if (ranNumbr(0,1)==1) {
					elementClick("//*[@id='discountDialog']/div/div[2]/div[3]/input");
				}
				if (ranNumbr(0,1)==1) {
					elementClick("//*[@id='discountDialog']/div/div[2]/div[4]/input");
				}
				selectDropDownV("//*[@id='discountDialog']/div/div[2]/div[3]/select");
				optionSelectedD = new Select (driver.findElement(By.xpath("//*[@id='discountDialog']/div/div[2]/div[3]/select"))).getFirstSelectedOption();
				optionSelectedDropDown = optionSelectedD.getText();
				if (!optionSelectedDropDown.equals("Indiferente")){;
					selectDropDown("selectedIdCombo");
				}
				Thread.sleep(200);
				selectDropDownV("//*[@id='discountDialog']/div/div[3]/div[1]/select[1]");
				Thread.sleep(100);
				selectDropDownV("//*[@id='discountDialog']/div/div[3]/div[2]/select[1]");
				Thread.sleep(100);
				selectDropDownV("//*[@id='discountDialog']/div/div[3]/div[1]/select[2]");
				Thread.sleep(100);
				selectDropDownV("//*[@id='discountDialog']/div/div[3]/div[2]/select[2]");
				Thread.sleep(100);
				selectDropDownV("//*[@id='discountDialog']/div/div[3]/div[1]/select[3]");
				Thread.sleep(100);
				selectDropDownV("//*[@id='discountDialog']/div/div[3]/div[2]/select[3]");
				Thread.sleep(100);
				char [] dayWeek = {'L','M','X','J','V','S','D'};
				for (int i = 0; i<= 6;i++){		
					if (ranNumbr(0,1)>0){						
						if (!driver.findElement(By.xpath("//*[@id='divWeekDaysInDiscount_chkWeekDayComponent"+dayWeek[i]+"']")).isSelected()){							
							elementClick("//*[@id='divWeekDaysInDiscount_chkWeekDayComponent"+dayWeek[i]+"']");
						}
					}
					Thread.sleep(300);				
				}				
				Thread.sleep(300);
				selectDropDownV("//*[@id='discountDialog']/div/div[5]/div[1]/select[1]");
				Thread.sleep(300);
				selectDropDownV("//*[@id='discountDialog']/div/div[5]/div[2]/select[1]");
				Thread.sleep(300);
				selectDropDownV("//*[@id='discountDialog']/div/div[5]/div[3]/select[1]");
				Thread.sleep(300);
				selectDropDownV("//*[@id='discountDialog']/div/div[5]/div[1]/select[2]");
				Thread.sleep(300);
				selectDropDownV("//*[@id='discountDialog']/div/div[5]/div[2]/select[2]");
				Thread.sleep(300);
				selectDropDownV("//*[@id='discountDialog']/div/div[5]/div[3]/select[2]");
				Thread.sleep(300);
				if (ranNumbr(0,1)>0){
					elementClick("//*[@id='discountDialog']/div/div[6]/div[1]/div[1]/input");
					Thread.sleep(500);					
					SendKeys("ctl00_ContentZone_dateSelector_dt_from_box_date",dateSel(2018,2018));
					Thread.sleep(500);					
					SendKeys("ctl00_ContentZone_dateSelector_dt_to_box_date",dateSel(2019,2020));				
				}
				Thread.sleep(300);
				if (ranNumbr(0,1)>0){
					elementClick("//*[@id='discountDialog']/div/div[6]/div[2]/div[1]/input");
					Thread.sleep(1000);
					SendKeys("//*[@id='discountDialog']/div/div[6]/div[2]/div[2]/div[1]/input",hourFormat(0,23,0,59));
					Thread.sleep(1000);
					SendKeys("//*[@id='discountDialog']/div/div[6]/div[2]/div[2]/div[2]/input",hourFormat(0,23,0,59));
				}
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","crearDescuentoFilled"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","crearDescuentoFilled.jpeg");
				
				//TC BOHost_gestionDescuento - Crear: Paso 6.- Hacer click en el botón Aceptar
				//TC BOHost_gestionDescuento - Modificar: Paso 7.- Hacer click en el botón Aceptar
				elementClick("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1)");
				Thread.sleep(5000);
				
				
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
		
		public static void eliminarDescuento() throws Exception {			
			try{
				//TC BOHost_gestionDescuento - Eliminar: Paso 5.- Una vez, seleccionado el descuento, hacer click en el botón Eliminar
				elementClick(OptionSel);
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","eliminarDescuentoPagina"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","eliminarDescuentoPagina.jpeg");
				
				
				elementClick("popup_ok");
				Thread.sleep(2000);
				if (driver.getPageSource().contains("No se puede eliminar el descuento")){
					errorText = getText("//*[@id='toast-container']/div/div");
					noDeleted = true;
					
					
				}
				
				
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
		
		
	

}