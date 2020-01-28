package R3R5Ciralsa.R3R5Ciralsa;

import static org.junit.Assert.*;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static SettingFiles.Generic_Settingsfields_File.*;
import static SettingFiles.R3R5Ciralsa_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class BOHost_gestionMerchant{
		private String testNameSelected = this.getClass().getSimpleName();
		private static String merchantName;
		private static int merchantCode;
		public static boolean wait;
		private static boolean noDeleted; 
		private static String merchantCodeS;		
		private static boolean merchantChanged = false;
		private static boolean merchantCodedChanged = false;
		private static boolean bothfields = false;
		private static int opSel; 		
		private static String [] optionSel = {"Crear","Modificar","Eliminar"};
		private static String [] merchantRange = {"Crear", "Modificar", "Eliminar"};
		private static int opSel1;
		private static boolean notCreated = false;
		
		
		
		@Before
		public void setup() throws Exception{
				setUp();
		}
		
		@After
		public void teardown() throws Exception{			  
			    tearDown();
		}

		@Test
		public void gestionMarchantInit() throws Exception {
			configurationSection("Host", testNameSelected, testNameSelected);
			opSel = ranNumbr(0,2);
			if (opSel==0) {
				configurationSection("Host", testNameSelected+" - Crear", testNameSelected);
				testPlanReading(9,0,2,3);
			}
			if (opSel==1) {
				configurationSection("Host", testNameSelected+" - Modificar", testNameSelected);
				testPlanReading(9,5,7,8);
			}
			if (opSel==2) {
				configurationSection("Host", testNameSelected+" - Eliminar", testNameSelected);
				testPlanReading(9,10,12,13);
			}
			action = new Actions(driver);
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
				Thread.sleep(1000);			
				
				//Paso 3.- Hacer click en Gestión de pagos y luego Gestión de merchants
				action.moveToElement(driver.findElement(By.linkText("Gestión de pagos"))).build().perform();
				Thread.sleep(1000);
				pageSelected = "Gestión de merchants";
				elementClick(pageSelected);
				Thread.sleep(500);
				pageSelectedErr(2);
				if (pageSelectedErr==true) {
					driver.close();
					testLink();
					System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
				}
				wait = (new WebDriverWait(driver,20))
						.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_WaitLabel")));
				
				takeScreenShot("E:\\Selenium\\","gestioinmerchantspage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","gestioinmerchantspage.jpeg");				
				switch(optionSel[opSel]){
					case "Crear":			//TC BOHost_gestionMerchant - Crear: Pasos del 4 al 10
											crear_Merchant();
											if (notCreated==true){
												actualResults.set(5, "No se ha posido crear el Merchant debido a: "+errorText);
												executionResults.set(5,"Fallado");
												summaryBug = "No se ha posido crear el Merchant debido a: "+errorText;
												erroTextBug = "No se ha posido crear el Merchant debido a: "+errorText;
												stepNotExecuted = executionNumber.size()-1;
												for (int i = stepNotExecuted;i>5;i--) {
													executionNumber.remove(i);
												}
												componentBug = "BO";
												severityBug = 1;
												priority = 4;
												testFailed = true;
												bugAttachment = true;
												pathAttachment = "E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\notCreatedErr.jpeg";						
												driver.close();
												testLink();
												System.err.println("No se ha posido crear el Merchant debido a: "+errorText);
												System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
												fail("No se ha posido crear el Merchant debido a: "+errorText);
											}else {
												//Paso 11.- Hacer click en el botón Aceptar
												elementClick("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1)");									
												Thread.sleep(5000);		
												actualResults.set(10,"Se ha creado el Merchant: " +merchantName+" con el Código: "+merchantCode+" correctamente");
												System.out.println("Se ha creado el Merchant: " +merchantName+" con el Código: "+merchantCode+" correctamente");
											}
											break;
					case "Modificar":		//TC BOHost_gestionMerchant - Modificar: Pasos del 5 al 6
											edit_deleteMerchant();									
											
											//TC BOHost_gestionMerchant - Modificar: Paso 7.- Hacer click en el botón Aceptar
											elementClick("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1)");								
											Thread.sleep(4000);
											wait = (new WebDriverWait(driver,30))
													.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_WaitLabel")));
											if (bothfields){
												actualResults.set(6,"Se modificado el Nombre del Merchant: "+optionSelectedId+" con otro nombre: "+merchantName+" y también su código "+merchantCodeS+" por el código "+merchantCode+" y todos los campos relevantes");
												System.out.println("Se modificado el Nombre del Merchant: "+optionSelectedId+" con otro nombre: "+merchantName+" y también su código "+merchantCodeS+" por el código "+merchantCode+" y todos los campos relevantes");
											}else{
												if (merchantChanged){
													actualResults.set(6,"Se modificado el Nombre del Merchant: "+optionSelectedId+" con otro nombre: "+merchantName+" pero tiene el mismo código "+merchantCodeS+" y todos los campos relevantes");
													System.out.println("Se modificado el Nombre del Merchant: "+optionSelectedId+" con otro nombre: "+merchantName+" pero tiene el mismo código "+merchantCodeS+" y todos los campos relevantes");
												}
												if (merchantCodedChanged){
													actualResults.set(6,"No se ha modificado el Nombre del Merchant: "+optionSelectedId+" con otro nombre, pero si se ha cambiado su código "+merchantCodeS+ " por otro código: "+merchantCodeS+" y todos los campos relevantes");
													System.out.println("No se ha modificado el Nombre del Merchant: "+optionSelectedId+" con otro nombre, pero si se ha cambiado su código "+merchantCodeS+ " por otro código: "+merchantCodeS+" y todos los campos relevantes");
												}
												if (!merchantChanged && !merchantCodedChanged){
													actualResults.set(6,"No se ha modificado ni el Nombre del Merchant: "+optionSelectedId+" ni tampoco su código: "+merchantCodeS+ ", pero si todos los campos relevantes");
													System.out.println("No se ha modificado ni el Nombre del Merchant: "+optionSelectedId+" ni tampoco su código: "+merchantCodeS+ ", pero si todos los campos relevantes");
												}
											}
											
											break;
					case "Eliminar":		//TC BOHost_gestionMerchant - Eliminar : Pasos del 4 al 6
											edit_deleteMerchant();
											if (noDeleted==true) {
												actualResults.set(5, "No se puede eliminar el Merchant "+optionSelectedId+" debido a: "+errorText);
												System.out.println("No se puede eliminar el Merchant "+optionSelectedId+" debido a: "+errorText);
											}else {
												actualResults.set(5,"Se ha borrado el Merchant: "+optionSelectedId+ " con el Código:" +merchantCodeS+" correctamente");
												System.out.println("Se ha borrado el Merchant: "+optionSelectedId+ " con el Código:" +merchantCodeS+" correctamente");
											}
											
											break;
				}				
				driver.close();
				testLink();
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				
			}catch (Exception e){
				e.printStackTrace();
				System.out.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
		
		public static void crear_Merchant() throws Exception{
				Thread.sleep(1000);
				try{
					//Paso 4.- Hacer click en el botón Crear
					elementClick("ctl00_ContentZone_BtnCreate");
					Thread.sleep(500);
					takeScreenShot("E:\\Selenium\\","createMerchantspage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","createMerchantspage.jpeg");
					
					//Paso 5.- Llenar todos los campos correspondientes
					merchantName = "MERCH_"+nameOp[ranNumbr(0,nameOp.length-1)];
					merchantCode = ranNumbr(1,99999999);
					SendKeys("merchantNameDlg",merchantName);
					Thread.sleep(100);
					SendKeys("merchantCodeDlg",String.valueOf(merchantCode));
					Thread.sleep(100);
					List <WebElement> typeOptions = new Select (driver.findElement(By.xpath("//*[@id='merchantDialog']/table/tbody/tr[1]/td[2]/div/select"))).getOptions();
					new Select (driver.findElement(By.xpath("//*[@id='merchantDialog']/table/tbody/tr[1]/td[2]/div/select"))).selectByIndex(ranNumbr(0,typeOptions.size()-1));
					selectDropDownV("//*[@id='merchantDialog']/table/tbody/tr[1]/td[2]/div[2]/select");
					Thread.sleep(100);
					if (ranNumbr(0,1)>0){
						elementClick("chkmerchantDlgAdmitCards");
					}
					if (ranNumbr(0,1)>0){
						elementClick("chkmerchantDlgOBE");
					}
					SendKeys("ctl00_ContentZone_commissionPercentDlg_box_data",String.valueOf(ranNumbr(1,10000)));
					takeScreenShot("E:\\Selenium\\","createMerchantspagedataFilled"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","createMerchantspagedataFilled.jpeg");
					Thread.sleep(1000);
					
					//Paso 6.- Hacer click en el botón Aceptar
					elementClick("//div[1]/div[3]/div/button[1]");
					Thread.sleep(2000);
					if (driver.getPageSource().contains("ERROR: La operación falló por un error interno")) {
						takeScreenShot("E:\\Selenium\\","notCreated"+timet+".jpeg");
						takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","notCreated.jpeg");
						errorText = getText("//*[@id='toast-container']/div/div");
						notCreated = true;
						return;
					}
						
					String titlePage = getText("ctl00_SectionZone_LblTitle");
					if (titlePage.equals("Merchants")){
						if (driver.getPageSource().contains("Guardado terminado correctamente")){
						elementClick("//*[@id='toast-container']/div/button");
						Thread.sleep(2000);
						}					
					}
					modifyMerchant();
					Thread.sleep(2000);
				
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail(e.getMessage());
				throw(e);
			}
		}
		
		public static void edit_deleteMerchant() throws Exception{
			Thread.sleep(1000);
			
			//TC BOHost_gestionMerchant - Modificar; BOHost_gestionMerchant - Eliminar
			//Paso 4.- Ya en la pantalla de Gestión de Merchants, si hay merchants disponibles, seleccionar cualquier merchats
			merchantTable();
			merchantCodeS = getText("//*[@id='content']/div[2]/div/div/div/div[1]/div[1]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+rowSelectedCi+"]/td[3]");
			if (optionSel[opSel].equals("Eliminar")){
				Thread.sleep(1000);
				
				//TC BOHost_gestionMerchant - Eliminar: Paso 5.- Hacer click en el botón Eliminar
				elementClick("ctl00_ContentZone_BtnDelete");
				Thread.sleep(1000);
				
				//TC BOHost_gestionMerchant - Eliminar: Paso 6.- Hacer click en el botón Aceptar
				elementClick("popup_ok");
				Thread.sleep(2000);
				if (driver.getPageSource().contains("No se puede eliminar")) {
					errorText = getText("//*[@id='toast-container']/div/div");
					noDeleted = true;
					return;
				}
				
				wait = (new WebDriverWait(driver,20))
						.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_WaitLabel")));				
				return;
			}
			if (optionSel[opSel].equals("Modificar")){
				Thread.sleep(1000);
				//TC BOHost_gestionMerchant - Modificar: Paso 5.- Hacer click en el botón Modificar
				elementClick("ctl00_ContentZone_BtnModify");
				Thread.sleep(1000);
				modifyMerchant();
				Thread.sleep(1000);				
			}
		}
		
		public static void modifyMerchant() throws Exception{
		try{
			Thread.sleep(1000);
			
			if (optionSel[opSel].equals("Modificar")){
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","modifymerchantPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","modifymerchantPage.jpeg");
				merchantName = "MERCH_"+nameOp[ranNumbr(0,nameOp.length-1)];
				merchantCode = ranNumbr(1,99999999);
				if (ranNumbr(0,1)>0){
					SendKeys("merchantNameDlg",merchantName);
					merchantChanged = true;
					Thread.sleep(1000);
				}
				if (ranNumbr(0,1)>0){
					SendKeys("merchantCodeDlg",merchantCode+"");
					merchantCodedChanged = true;
					Thread.sleep(1000);
				}
				if (merchantCodedChanged && merchantChanged){
					bothfields = true;
					Thread.sleep(1000);
				}
				selectDropDownV("//*[@id='merchantDialog']/table/tbody/tr[1]/td[2]/div[1]/select");
				Thread.sleep(200);
				selectDropDownV("//*[@id='merchantDialog']/table/tbody/tr[1]/td[2]/div[2]/select");
				Thread.sleep(200);
				if (ranNumbr(0,1)>0){
					elementClick("chkmerchantDlgAdmitCards");
					Thread.sleep(200);
				}
				if (ranNumbr(0,1)>0){
					elementClick("chkmerchantDlgOBE");
					Thread.sleep(200);
				}
				SendKeys("ctl00_ContentZone_commissionPercentDlg_box_data",String.valueOf(ranNumbr(1,10000)));
			}else{
				//TC BOHost_gestionMerchant - Crear: Paso 7.- Luego de haber creado el Merchant, buscar el merchant recién creado
				//TC BOHost_gestionMerchant - Modificar: Paso 6.- Vaya a cada sección correspondiente y modifique los campos correspondientes
				SendKeys("//*[@id='content']/div[2]/div/div/div/div[1]/table/tbody/tr/td[1]/div[1]/input",String.valueOf(merchantName));
				Thread.sleep(100);
				SendKeys("//*[@id='content']/div[2]/div/div/div/div[1]/table/tbody/tr/td[1]/div[2]/input",String.valueOf(merchantCode));
				Thread.sleep(100);
				elementClick("ctl00_ButtonsZone_BtnSearch");
				Thread.sleep(1000);
				//TC BOHost_gestionMerchant - Crear: Paso 8.- Seleccionar el merchant encontrado
				elementClick("merchantTable_radio_0");			
				Thread.sleep(100);
				//TC BOHost_gestionMerchant - Crear: Paso 9.- Hacer Click en el botón Modificar
				elementClick("ctl00_ContentZone_BtnModify");
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","modifymerchantPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","modifymerchantPage.jpeg");
			}						
			
			//TC BOHost_gestionMerchant - Crear: Paso 10.- Ir a cada sección y llenar todos los campos correspondientes
			//TC BOHost_gestionMerchant - Modificar: Paso 7.- Vaya a cada sección correspondiente y modifique los campos correspondientes
			elementClick("//*[@class='ng-binding ui-accordion-header ui-state-default ui-corner-all ui-accordion-icons']");
			Thread.sleep(3000);			

			String RecordsNumbr = getText("//*[@id='rangesZoneTable']/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[3]/span");
			int recordRange = Integer.parseInt(RecordsNumbr.substring(RecordsNumbr.indexOf("de ")+3));				
				if (recordRange==0){
					opSel1 =0;
				}else{
					opSel1= ranNumbr(0,2);
				}							
			if (optionSel[opSel].equals("Crear") || merchantRange[opSel1].equals("Crear") || recordRange==0){
				elementClick("//*[@id='ctl00_ContentZone_td1']/div/input[1]");			
				Thread.sleep(8000);
			}
			if (recordRange>0){
				if (merchantRange[opSel1].equals("Modificar") || merchantRange[opSel1].equals("Eliminar")){					
					driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);									
					rangeTable();
					if (noItemFound==true) {					
						System.out.println("No se ha encontrado ningún Rango disponible para: "+merchantRange[opSel1]);
						fail("No se ha encontrado ningún Rango disponible para: "+merchantRange[opSel1]);						
					}
				}
					if (merchantRange[opSel1].equals("Eliminar")){
						elementClick("ctl00$ContentZone$ctl04");
						Thread.sleep(1000);
						elementClick("popup_ok");
						if (driver.getPageSource().contains("Eliminación terminada con éxito")){
							elementClick("//*[@id='toast-container']/div/button");
						}
					}
					if (merchantRange[opSel1].equals("Modificar")){
						elementClick("ctl00$ContentZone$ctl03");
					}
				}
			
			if (optionSel[opSel].equals("Crear") || merchantRange[opSel1].equals("Crear") || merchantRange[opSel1].equals("Modificar")){		  							 
				int rangNumber = ranNumbr(1000,999999);
				String rangName = "Rango_"+rangNumber;
				SendKeys("txtRangeName",String.valueOf(rangName));
				Thread.sleep(100);
				SendKeys("txtRanges",String.valueOf(rangNumber));
				Thread.sleep(100);
				if (ranNumbr(0,2)>1){
					elementClick("chkRangeActive");
				}
				Thread.sleep(1000);
				if (ranNumbr(0,2)>1){
					elementClick("chkRangeOnline");
				}
				SendKeys("txtRangeMinLength",String.valueOf(ranNumbr(1,20)));
				Thread.sleep(100);
				SendKeys("txtRangeMaxLength",String.valueOf(ranNumbr(21,99)));
				Thread.sleep(100);
				List <WebElement> AccionGen = new Select(driver.findElement(By.xpath("//*[@id='rangesEditZone']/table[2]/tbody/tr[2]/td[1]/div[4]/select"))).getOptions();
				new Select(driver.findElement(By.xpath("//*[@id='rangesEditZone']/table[2]/tbody/tr[2]/td[1]/div[4]/select"))).selectByIndex(ranNumbr(0,AccionGen.size()-1));
				Thread.sleep(100);
				selectDropDown ("selectedDiscountIdCombo");
				Thread.sleep(100);
				List <WebElement> AccionListaNegra = new Select(driver.findElement(By.xpath("//*[@id='rangesEditZone']/table[2]/tbody/tr[2]/td[2]/div[4]/select"))).getOptions();
				new Select(driver.findElement(By.xpath("//*[@id='rangesEditZone']/table[2]/tbody/tr[2]/td[2]/div[4]/select"))).selectByIndex(ranNumbr(0,AccionListaNegra.size()-1));
				Thread.sleep(100);
				List <WebElement> AccionCaduca = new Select(driver.findElement(By.xpath("//*[@id='rangesEditZone']/table[2]/tbody/tr[2]/td[3]/div[4]/select"))).getOptions();
				new Select(driver.findElement(By.xpath("//*[@id='rangesEditZone']/table[2]/tbody/tr[2]/td[3]/div[4]/select"))).selectByIndex(ranNumbr(0,AccionCaduca.size()-1));
				Thread.sleep(100);
				SendKeys("txtServiceCode",String.valueOf(ranNumbr(0,3))+String.valueOf(ranNumbr(0,3))+String.valueOf(ranNumbr(0,3)));
				Thread.sleep(100);
				SendKeys("txtAmountMin",String.valueOf(ranNumbr(1,200)));
				Thread.sleep(100);
				SendKeys("txtAmountMax",String.valueOf(ranNumbr(201,999)));
				Thread.sleep(100);
				elementClick("ctl00$ContentZone$ctl01");
				Thread.sleep(1000);
				if (driver.getPageSource().contains("Guardado terminado correctamente")){
					elementClick("//*[@id='toast-container']/div/button");
				}
				
				
			}
			Thread.sleep(3000);
			takeScreenShot("E:\\Selenium\\","modifymerchantRangefilled"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","modifymerchantRangeFilled.jpeg");
			if (driver.findElement(By.xpath("//*[@id='merchantDialog']/table/tbody/tr[3]/td/div/h3[2]")).isDisplayed()){
				listaNegraConfiguration();
			}
						
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
	}
		
	public static void listaNegraConfiguration() throws Exception{
			elementClick("h3AccordionBlacklist");
			Actions action = new Actions(driver);
		try{
			takeScreenShot("E:\\Selenium\\","blackListsectionpage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","blackListsectionpage.jpeg");
			Thread.sleep(3000);
			boolean activeChecked = driver.findElement(By.id("chkBLactive")).isSelected();
			if (activeChecked==false) {
				elementClick("chkBLactive");
				activeChecked=true;		
			}
			if (optionSel[opSel].equals("Modificar")){				
				if (activeChecked){
					if (ranNumbr(0,1)>0){
						elementClick("chkBLactive");
						activeChecked=false;
					}
				}else{
					elementClick("chkBLactive");
					activeChecked=true;					
				}
			}
			
			if (optionSel[opSel].equals("Crear")){
				if (ranNumbr(0,1)>0){
					elementClick("chkBLactive");
					activeChecked=false;
					Thread.sleep(1000);
				}
			}
			
			boolean activeSharedChecked = driver.findElement(By.id("chkBLSharedBlacklist")).isSelected();				
				if (activeChecked){					
					if (optionSel[opSel].equals("Modificar")){				
						if (activeSharedChecked==true){
							if (ranNumbr(0,1)==0){
								elementClick("chkBLSharedBlacklist");
								Thread.sleep(1000);								
								activeSharedChecked=false;
							}else{																
									Thread.sleep(1000);
									selectDropDown("cmbSBLSharedBlacklist");
									activeSharedChecked=true;								
							}
						}else {
							if (ranNumbr(0,1)==0){
								elementClick("chkBLSharedBlacklist");
								Thread.sleep(1000);								
								selectDropDown("cmbSBLSharedBlacklist");
								activeSharedChecked=true;
							}else{																
									Thread.sleep(1000);									
									activeSharedChecked=false;								
							}
					}
				}
					
					if (optionSel[opSel].equals("Crear")){
						if (ranNumbr(0,1)>0){
							elementClick("chkBLSharedBlacklist");
							activeSharedChecked=true;
							Thread.sleep(1000);
							selectDropDown("cmbSBLSharedBlacklist");
						}else{
							activeSharedChecked=false;	
						}
				}
			}		
			if (activeChecked==true &&  activeSharedChecked==false){	
				Thread.sleep(1000);
				selectDropDown("cmbBLformatType");
				Thread.sleep(100);
				selectDropDown("cmbBLaccessType");
				Thread.sleep(100);				
				SendKeys("txtBLtime",hourFormat(0,23,0,59));
				Thread.sleep(100);				
				SendKeys("txtBLretryTime",hourFormat(0,23,0,59));
				Thread.sleep(100);
				SendKeys("txtBLlimitBlackList",String.valueOf(ranNumbr(1,100)));
				Thread.sleep(100);
				List <WebElement> caducenciaOp = new Select(driver.findElement(By.xpath("//*[@id='blacklistZone']/table/tbody/tr[3]/td[1]/div/select"))).getOptions();
				int caduCOpt = ranNumbr(0,caducenciaOp.size()-1);
				new Select(driver.findElement(By.xpath("//*[@id='blacklistZone']/table/tbody/tr[3]/td[1]/div/select"))).selectByIndex(caduCOpt);				
				WebElement weekSelected = new Select (driver.findElement(By.id("cmbBLCadence"))).getFirstSelectedOption();
				String weeSel = weekSelected.getText();				
					if (weeSel.equals("Seleccionar días")){
						char [] weekDay = {'L','M','X','J','V','S','D'};
						for (int i=0; i<weekDay.length;i++){
							if (ranNumbr(0,1)>0){
								elementClick("divWeekDaysInBL_chkWeekDayComponent"+weekDay[i]);
								Thread.sleep(500);
							}
						}
					}					
					Thread.sleep(100);
					SendKeys("txtBLserver",ranNumbr(170,195)+"."+ranNumbr(15,20)+"."+ranNumbr(130,230)+"."+ranNumbr(2,240));
					Thread.sleep(100);
					SendKeys("txtBLport","22");
					Thread.sleep(100);
					SendKeys("txtBLdirectory","/mechantsData");
					Thread.sleep(100);
					SendKeys("txtBLfileName","mechanDataV001.dat");
					Thread.sleep(100);
					SendKeys("txtBLftpUserName","root");
					Thread.sleep(100);
					SendKeys("txtBLftpPassword","00001");
					Thread.sleep(100);
				
					boolean noEmailSent=false;
					boolean emailNotification = driver.findElement(By.id("chkBLSendEmail")).isSelected();
					if (emailNotification==false) {
						elementClick("chkBLSendEmail");
					}
					if (emailNotification==true) {
						if (ranNumbr(0,1)==0) {
							elementClick("chkBLSendEmail");
							noEmailSent=true;
						}				
					}else {
						noEmailSent=false;
					}
					if (noEmailSent==false) {					
						if (optionSel[opSel].equals("Modificar") || optionSel[opSel].equals("Crear") ){											
							List <WebElement> optionsSelected = new Select(driver.findElement(By.xpath("//*[@id='divAlertNotificationInBL']/div/div[1]/table/tbody/tr[2]/td/select"))).getOptions();							
							if (optionsSelected.size()>0){
								int selO = ranNumbr(0,1);
								if (selO>0){									
									new Select(driver.findElement(By.xpath("//*[@id='divAlertNotificationInBL']/div/div[1]/table/tbody/tr[2]/td/select"))).selectByIndex(ranNumbr(0,optionsSelected.size()-1));
									Thread.sleep(1000);									
									action.click(driver.findElement(By.xpath("//*[@id='divAlertNotificationInBL']/div/div[1]/table/tbody/tr[3]/td/div/input"))).build().perform();
									Thread.sleep(1000);
								}else{
									selectDropDownV("//*[@id='divAlertNotificationInBL']/div/div[1]/table/tbody/tr[1]/td[1]/div/select");
									Thread.sleep(100);
									elementClick("//*[@id='divAlertNotificationInBL']/div/div[1]/table/tbody/tr[1]/td[2]/div/input");
									Thread.sleep(100);
								}
							}
							if (optionsSelected.size()==0){					
								selectDropDownV("//*[@id='divAlertNotificationInBL']/div/div[1]/table/tbody/tr[1]/td[1]/div/select");
								Thread.sleep(100);
								elementClick("//*[@id='divAlertNotificationInBL']/div/div[1]/table/tbody/tr[1]/td[2]/div/input");
								Thread.sleep(100);
							}
							WebElement tableR = driver.findElement(By.xpath("//*[@id='divAlertNotificationInBL']/div/div[2]/table/tbody/tr/td[2]/div/div/table[@class='generalTable']"));
							List <WebElement> tableResult = tableR.findElements(By.tagName("tr"));
							if (optionSel[opSel].equals("Crear")){
								elementClick("//*[@id='divAlertNotificationInBL']/div/div[2]/table/tbody/tr/td[1]/div[1]");
								Thread.sleep(1000);					
								int selOp = ranNumbr(0,nameOp.length-1);
								action.sendKeys(driver.findElement(By.xpath("(//input[@id='txtnameAlertNotification'])")),nameOp[selOp]+" "+lastNameOp[selOp]);; //campo Nombre						
								Thread.sleep(100);
								action.sendKeys(driver.findElement(By.xpath("(//input[@id='txtemailAlertNotification'])")),nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.com"); //campo email
								Thread.sleep(100);
								elementClick("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.extMailDialogShadow.ui-dialog-buttons.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1)");			
							}
							if (optionSel[opSel].equals("Modificar")){
								int recSel = ranNumbr(0,tableResult.size()-1);
								if (recSel == 1){
									recSel= 0;
							}
							int crearEditarOption = ranNumbr(0,1);
							int optionS = ranNumbr (0,1);
							if (tableResult.size()<2) {
								optionS = 0;
								crearEditarOption = 0;
							}
							switch (optionS){
								case 0:				Thread.sleep(2000);
													if (crearEditarOption == 0){
														elementClick("//*[@id='divAlertNotificationInBL']/div/div[2]/table/tbody/tr/td[1]/div[1]/input");
														Thread.sleep(4000);
													}else{
														elementClick("extMailTable_radio_"+recSel);
														Thread.sleep(300);
														elementClick("//*[@id='divAlertNotificationInBL']/div/div[2]/table/tbody/tr/td[1]/div[2]/input");
													}
													int selOp = ranNumbr(0,nameOp.length-1);
													action.sendKeys(driver.findElement(By.xpath("//div[1]/input[@id='txtnameAlertNotification']")),Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),nameOp[selOp]+" "+lastNameOp[selOp]).build().perform();													
													Thread.sleep(100);
													action.sendKeys(driver.findElement(By.xpath("(//input[@id='txtemailAlertNotification'])[3]")), Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.com").perform();
													Thread.sleep(300);											
													elementClick("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.extMailDialogShadow.ui-dialog-buttons.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1)");
													break;		
												
								case 1:				elementClick("extMailTable_radio_"+recSel);
													Thread.sleep(300);
													elementClick("//*[@id='divAlertNotificationInBL']/div/div[2]/table/tbody/tr/td[1]/div[3]/input");
													Thread.sleep(300);
													elementClick("popup_ok");
													break;
			
								}
							}
						}						
					}
				}
					
			takeScreenShot("E:\\Selenium\\","blackListsectionfilled"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","blackListsectionfilled.jpeg");
				Thread.sleep(5000);
			
				if (driver.findElement(By.xpath("//*[@id='merchantDialog']/table/tbody/tr[3]/td/div/h3[3]")).isDisplayed()){
					configuracionFicheroLotes();
				}
				
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
	}
						
	
	public static void configuracionFicheroLotes() throws Exception{
		Actions action = new Actions(driver); 
		Thread.sleep(2000);
		elementClick("h3AccordionBatchFiles");
		try{			
			takeScreenShot("E:\\Selenium\\","batchFilesectionPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","batchFilesectionPage.jpeg");
			Thread.sleep(3000);
			boolean activeChecked = driver.findElement(By.id("chkBatchfilesactive")).isSelected();
			if (optionSel[opSel].equals("Modificar")){				
				if (activeChecked==true){
					if (ranNumbr(0,1)>0){
						elementClick("chkBatchfilesactive");
						activeChecked=false;
					}
				}else{
					elementClick("chkBatchfilesactive");
					activeChecked=true;					
				}
			}		
			if (optionSel[opSel].equals("Crear")){
				if (ranNumbr(0,1)>0){
					elementClick("chkBatchfilesactive");
					activeChecked=false;
					Thread.sleep(1000);
				}
			}
			
			boolean activeSharedChecked = driver.findElement(By.id("chkBatchfilesSharedBatchfiles")).isSelected();
				if (activeChecked==true){					
					if (optionSel[opSel].equals("Modificar")){				
						if (activeSharedChecked==true){
							if (ranNumbr(0,1)==0){
								elementClick("chkBatchfilesSharedBatchfiles");
								Thread.sleep(1000);								
								activeSharedChecked=false;
							}else{																
									Thread.sleep(1000);
									selectDropDown("cmbSBatchfilesSharedBatchfiles");
									activeSharedChecked=true;								
							}
						}else {
							if (ranNumbr(0,1)==0){
								elementClick("chkBatchfilesSharedBatchfiles");
								Thread.sleep(1000);								
								selectDropDown("cmbSBatchfilesSharedBatchfiles");
								activeSharedChecked=true;
							}else{																
									Thread.sleep(1000);									
									activeSharedChecked=false;								
							}
					}
				}
					if (optionSel[opSel].equals("Crear")){
						if (ranNumbr(0,1)>0){
							elementClick("chkBatchfilesSharedBatchfiles");
							activeSharedChecked=true;
							Thread.sleep(1000);
							selectDropDown("cmbSBatchfilesSharedBatchfiles");
						}else{
							activeSharedChecked=false;	
						}
				}
			}			
			
			if (activeChecked==true && activeSharedChecked==false){					
					selectDropDown("cmbBatchfilesformatType");
					Thread.sleep(100);
					selectDropDown("cmbBatchfilesaccessType");
					Thread.sleep(1000);
					WebElement bachtFileT = new Select (driver.findElement(By.id("cmbBatchfilesaccessType"))).getFirstSelectedOption();
					String batchFileText = bachtFileT.getText();
					SendKeys("txtBatchfilestime",hourFormat(0,23,0,59));
					Thread.sleep(100);
					SendKeys("txtBatchfilesretryTime",hourFormat(0,23,0,59));
					Thread.sleep(1000);
					List <WebElement> dropD = new Select(driver.findElement(By.xpath("//*[@id='batchfilesZone']/table/tbody/tr[5]/td[1]/div/select"))).getOptions();					
					int dropSel = ranNumbr(0,dropD.size()-1);					
					new Select(driver.findElement(By.xpath("//*[@id='batchfilesZone']/table/tbody/tr[5]/td[1]/div/select"))).selectByIndex(dropSel);
					WebElement weekSelected = new Select (driver.findElement(By.id("cmbBatchfilesCadence"))).getFirstSelectedOption();
					String weeSel = weekSelected.getText();					
						if (weeSel.equals("Seleccionar días")){
							char dayWeek[] = {'L', 'M','X','J','V','S','D'};
							for (int i = 0; i< dayWeek.length;i++){
								if (ranNumbr(0,1)>0){
									elementClick("divWeekDaysInBatchfiles_chkWeekDayComponent"+dayWeek[i]);								
								}
								Thread.sleep(300);
							}
						}					
						if (batchFileText.equals("EMAIL")){
							Thread.sleep(100);
							SendKeys("txtBatchfilesserver","mavi.garrido@tecsidel.es");
							Thread.sleep(100);
							SendKeys("txtBatchfilesfileName","batchFileDataV001.dat");
							Thread.sleep(2000);
						}else{
							Thread.sleep(100);
							SendKeys("txtBatchfilesserver",ranNumbr(170,195)+"."+ranNumbr(15,20)+"."+ranNumbr(130,230)+"."+ranNumbr(2,240));
							Thread.sleep(100);
							SendKeys("txtBatchfilesport","22");
							Thread.sleep(100);
							SendKeys("txtBatchfilesdirectory","/BachFileData");
							Thread.sleep(100);
							SendKeys("txtBatchfilesfileName","batchFileDataV001.dat");
							Thread.sleep(100);
							SendKeys("txtBatchfilesftpUserName","mavi_test");
							Thread.sleep(100);
							SendKeys("txtBatchfilesftpPassword","00001");
							Thread.sleep(2000);
						}																	
						Thread.sleep(2000);
						boolean noEmailSent=false;
						boolean emailNotification = driver.findElement(By.id("chkBatchfilesSendEmail")).isSelected();
						if (emailNotification==false) {
							elementClick("chkBatchfilesSendEmail");
						}
						if (emailNotification==true) {
							if (ranNumbr(0,1)==0) {
								elementClick("chkBatchfilesSendEmail");
								noEmailSent=true;
							}else {
								noEmailSent=false;
							}
						}else {
							if (ranNumbr(0,1)==0) {
								elementClick("chkBatchfilesSendEmail");
								noEmailSent=false;
							}else {
								noEmailSent=true;
							}
							
						}
						if (noEmailSent==false) {																	
							List <WebElement> alertNo = new Select(driver.findElement(By.xpath("//*[@id='divAlertNotificationInBatchFiles_component']/div/div[1]/table/tbody/tr[2]/td/select"))).getOptions();					
							if (optionSel[opSel].equals("Modificar") || optionSel[opSel].equals("Crear")){
									if (alertNo.size()>0){
										if (ranNumbr(0,1)>0){
											new Select(driver.findElement(By.xpath("//*[@id='divAlertNotificationInBatchFiles_component']/div/div[1]/table/tbody/tr[2]/td/select"))).selectByIndex(ranNumbr(0,alertNo.size()-1));
											Thread.sleep(500);									
											elementClick("//*[@id='divAlertNotificationInBatchFiles_component']/div/div[1]/table/tbody/tr[3]/td/div/input");
											Thread.sleep(500);
										}else{											
											selectDropDownV("//*[@id='divAlertNotificationInBatchFiles_component']/div/div[1]/table/tbody/tr[1]/td[1]/div/select");								
											Thread.sleep(500);
											elementClick("//*[@id='divAlertNotificationInBatchFiles_component']/div/div[1]/table/tbody/tr[1]/td[2]/div/input");
											Thread.sleep(500);
										}
									}else {
										selectDropDownV("//*[@id='divAlertNotificationInBatchFiles_component']/div/div[1]/table/tbody/tr[1]/td[1]/div/select");							
										Thread.sleep(500);
										elementClick("//*[@id='divAlertNotificationInBatchFiles_component']/div/div[1]/table/tbody/tr[1]/td[2]/div/input");
										Thread.sleep(500);
									}
												
							}
							
							WebElement tableR = driver.findElement(By.xpath("//*[@id='divAlertNotificationInBatchFiles_component']/div/div[2]/table/tbody/tr/td[2]/div/div/table[@class='generalTable']"));
							List <WebElement> tableResult = tableR.findElements(By.tagName("tr"));
							int recSel = ranNumbr(0,tableResult.size()-1);
							if (optionSel[opSel].equals("Crear")){					
								elementClick("//*[@id='divAlertNotificationInBatchFiles_component']/div/div[2]/table/tbody/tr/td[1]/div[1]/input");
								Thread.sleep(1000);					
								int selOp = ranNumbr(0,nameOp.length-1);
								action.sendKeys(driver.findElement(By.xpath("//div[1]/input[@id='txtnameAlertNotification']")),nameOp[selOp]+" "+lastNameOp[selOp]).build().perform();								
								Thread.sleep(100);
								action.sendKeys(driver.findElement(By.xpath("(//input[@id='txtemailAlertNotification'])[3]")), nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.com").perform();													
								Thread.sleep(1000);
								
								elementClick("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.extMailDialogShadow.ui-dialog-buttons.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1) > span"); //Boton Aceptar			
							}
							if (optionSel[opSel].equals("Modificar")){						
								if (recSel == 1){
									recSel= 0;
								}	
								int crearEditarOption = ranNumbr(0,1);
								int optionS = ranNumbr (0,1);
								if (tableResult.size()<2) {
									optionS = 0;
									crearEditarOption = 0;
								}
								switch (optionS){
								case 0:				if (crearEditarOption == 0){
														Thread.sleep(1000);
														elementClick("//*[@id='divAlertNotificationInBatchFiles_component']/div/div[2]/table/tbody/tr/td[1]/div[1]/input");
														Thread.sleep(4000);
													}else{
														Thread.sleep(1000);
														elementClick("(//input[@id='extMailTable_radio_"+recSel+"'])[2]");
														Thread.sleep(300);
														elementClick("//*[@id='divAlertNotificationInBatchFiles_component']/div/div[2]/table/tbody/tr/td[1]/div[2]/input");
													}								
													int selOp = ranNumbr(0,nameOp.length-1);																										
													action.sendKeys(driver.findElement(By.xpath("//div[1]/input[@id='txtnameAlertNotification']")),Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),nameOp[selOp]+" "+lastNameOp[selOp]).build().perform();													
													Thread.sleep(1000);
													action.sendKeys(driver.findElement(By.xpath("(//input[@id='txtemailAlertNotification'])[3]")), Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.com").perform();
													Thread.sleep(1000);													
													elementClick("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.extMailDialogShadow.ui-dialog-buttons.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1) > span"); //Boton Aceptar
													Thread.sleep(1000);												
													break;							
								case 1:				elementClick("(//input[@id='extMailTable_radio_"+recSel+"'])[2]");
													Thread.sleep(3000);
													elementClick("//*[@id='divAlertNotificationInBatchFiles_component']/div/div[2]/table/tbody/tr/td[1]/div[3]/input");
													Thread.sleep(300);
													elementClick("popup_ok");
													break;
												
								}
							}							
						}
					}

				takeScreenShot("E:\\Selenium\\","batchFilesectionfilled"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","batchFilesectionfilled.jpeg");
				Thread.sleep(2000);
				if (driver.findElement(By.id("h3AccordionBatchInvoice")).isDisplayed()){
					configuraciondeFacturacion();
				}
				
				
		}catch (Exception e){
			e.printStackTrace();					
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw(e);
		}
	}
	
	public static void configuraciondeFacturacion() throws Exception{
		Thread.sleep(2000);
		elementClick("h3AccordionBatchInvoice");
		try{			
			takeScreenShot("E:\\Selenium\\","batchFilesectionPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","batchFilesectionPage.jpeg");
			Thread.sleep(3000);
			boolean activeChecked = driver.findElement(By.id("chkBIactive")).isSelected();
			if (optionSel[opSel].equals("Modificar")){				
				if (activeChecked==true){
					if (ranNumbr(0,1)>0){
						elementClick("chkBIactive");
						activeChecked=false;
						return;
					}
				}if (activeChecked==false) {
					elementClick("chkBIactive");
					activeChecked=true;		
					Thread.sleep(1000);					
				}
			}		
			if (optionSel[opSel].equals("Crear")){
				if (ranNumbr(0,1)>0){
					elementClick("chkBIactive");
					activeChecked=false;
					Thread.sleep(1000);
					SendKeys("txtInvoiceBussinessName", "Tecsidel, S.A.");
					Thread.sleep(100);
					SendKeys("txtInvoiceCif",dniLetra("DNI",ranNumbr(10000000,40000000)));
					Thread.sleep(100);
					SendKeys("txtInvoiceAddress","C/Castanyer No. 29, 08022, Barcelona, España");
					Thread.sleep(100);
					List <WebElement> accounts = new Select(driver.findElement(By.id("cmbBatchInvoiceAccounts"))).getOptions();
					new Select(driver.findElement(By.id("cmbBatchInvoiceAccounts"))).selectByIndex(ranNumbr(1,accounts.size()-1));
					Thread.sleep(1000);
					elementClick("chkBISendEmail");
					Thread.sleep(1000);
					elementClick("//*[@id='divAlertNotificationInvoice']/div/div[2]/table/tbody/tr/td[1]/div[1]/input");
					Thread.sleep(1000);					
					int selOp = ranNumbr(0,nameOp.length-1);					
					SendKeys("(//input[@id='txtnameAlertNotification'])[3]",nameOp[selOp]+" "+lastNameOp[selOp]);; //campo Nombre						
					Thread.sleep(100);
					SendKeys("(//input[@id='txtemailAlertNotification'])[3]",nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.com"); //campo email
					Thread.sleep(100);
					elementClick("(//button[@type='button'])[6]");
				}
			}	
			
				if (optionSel[opSel].equals("Modificar")){
					String bName = driver.findElement(By.id("txtInvoiceBussinessName")).getAttribute("value");
					String invoiceCif = driver.findElement(By.id("txtInvoiceCif")).getAttribute("value");
					String invAddress = driver.findElement(By.id("txtInvoiceAddress")).getAttribute("value");
					
					if (activeChecked==true){							
							if (bName.equals("")) {
								SendKeys("txtInvoiceBussinessName", "Tecsidel, S.A.");
							}
							if (invoiceCif.equals("")){
								SendKeys("txtInvoiceCif",dniLetra("DNI",ranNumbr(10000000,40000000)));
							}
							if (invAddress.equals("")){
								SendKeys("txtInvoiceAddress","C/Castanyer No. 29, 08022, Barcelona, España");
							}
					}
					if (!driver.findElement(By.id("chkBISendEmail")).isEnabled()){
						return;
					}
					if (driver.findElement(By.id("chkBISendEmail")).isSelected()){
						elementClick("chkBISendEmail");
						return;
					}
					if (!driver.findElement(By.id("chkBISendEmail")).isSelected()){
						elementClick("chkBISendEmail");						
					}
					
					
					List <WebElement> accounts = new Select(driver.findElement(By.id("cmbBatchInvoiceAccounts"))).getOptions();
					new Select(driver.findElement(By.id("cmbBatchInvoiceAccounts"))).selectByIndex(ranNumbr(1,accounts.size()-1));
					Thread.sleep(1000);
								
					Thread.sleep(1500);
					WebElement tableR = driver.findElement(By.id("tblExternalUsers"));
					List <WebElement> tableResult = tableR.findElements(By.tagName("tr"));
					int recSel = ranNumbr(0,tableResult.size()-1);
					if (recSel == 1){
							recSel= 0;
						}	
					int crearEditarOption = ranNumbr(0,1);
					int optionS = ranNumbr (0,1);
					if (tableResult.size()<2) {
						optionS = 0;
						crearEditarOption = 0;
					}
					
				switch (optionS){
					case 0:			if (crearEditarOption == 0){
										Thread.sleep(1000);												
										elementClick("//*[@id='divAlertNotificationInvoice']/div/div[2]/table/tbody/tr/td[1]/div[1]/input");
									}else{
										Thread.sleep(1000);
										elementClick("(//input[@id='extMailTable_radio_"+recSel+"'])[2]");
										Thread.sleep(300);										
										elementClick("//*[@id='divAlertNotificationInvoice']/div/div[2]/table/tbody/tr/td[1]/div[2]/input");
									}
									Thread.sleep(500);
									int selOp = ranNumbr(0,nameOp.length-1);
									SendKeys("(//input[@id='txtnameAlertNotification'])[3]",nameOp[selOp]+" "+lastNameOp[selOp]);; //campo Nombre
									Thread.sleep(500);
									SendKeys("(//input[@id='txtemailAlertNotification'])[3]",nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.com"); //campo email
									Thread.sleep(1000);
									elementClick("(//button[@type='button'])[6]");
									Thread.sleep(1000);										
									break;							
					case 1:			elementClick("(//input[@id='extMailTable_radio_"+recSel+"'])[2]");
									Thread.sleep(3000);
									elementClick("//*[@id='divAlertNotificationInvoice']/div/div[2]/table/tbody/tr/td[1]/div[3]/input");
									Thread.sleep(300);
									elementClick("popup_ok");
									break;										
				}
			}			
		
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			fail(e.getMessage());
			throw (e);
		}
	}
		
}
