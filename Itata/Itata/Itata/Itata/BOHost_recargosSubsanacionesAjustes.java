package Itata;

import static org.junit.Assert.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static SettingFiles.Itata_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static TestLink.TestLinkExecution.*;

public class BOHost_recargosSubsanacionesAjustes{
			 private static boolean accountClosed = false; 
			 private static String [] options = {"Recargas","Subsanaciones","Ajustes","Cancelación de Recargas"};
			 public static boolean noReloadFound = false;
			 private static int selOption;
			 private static String currentAmount;
			 private static String valueField;
			 private static String newAmount;
			 private static int addedAmount;
			 private static boolean errorAdjust = false;
			 public static int a;
			 private static String [] cuentaTipo = {"Estándar", "Comercial"};
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
			public void recargosSubsanacionesAjustesInit() throws Exception {
				Thread.sleep(1000);
				configurationSection("Host",testNameSelected,testNameSelected);
				testPlanReading(12,0,2,3);					
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				recargosSubsanacionesAjustes();
				Thread.sleep(200);
				if (noItemFound==true) {
					actualResults.set(16, "No se encontraron recargas para cancelar");									
					for (int i=17;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se encontraron recargas para cancelar");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;
				}
				if (recordFoundDisabled==true) {
					actualResults.set(16, "Recarga: "+optionSelectedId+" no está disponible para cancelar");									
					for (int i=17;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("Recarga: "+optionSelectedId+" no está disponible para cancelar");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
					return;
				}
				
				if (accountClosed==true){
					actualResults.set(5, "No se puede hacer "+options[selOption]+" a la cuenta "+accountNumbr+" porque está cerrada");									
					for (int i=6;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println("No se puede hacer "+options[selOption]+" a la cuenta "+accountNumbr+" porque está cerrada");
					System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));												
					return;
										
					
				}		
				if (errorAdjust == true) {
					actualResults.set(11, errorText+" en la cuenta "+accountNumbr);									
					for (int i=12;i<actualResults.size();i++) {
						actualResults.set(i, "N/A");
						executionResults.set(i, "");
						
					}
					driver.close();
					testLink();
					System.out.println(errorText+" en la cuenta "+accountNumbr);					
					return;															
				}
				if (selOption<3) {
					actualResults.set(20, "Se ha hecho "+options[selOption]+" a la cuenta "+accountNumbr+" correctamente que tenia un saldo "+currentAmount+" y ha agregado/quitado/ajustado un nuevo saldo "+addedAmount+" y tiene un nuevo Saldo "+newAmount);
					System.out.println("Se ha hecho "+options[selOption]+" a la cuenta "+accountNumbr+" correctamente que tenia un saldo "+currentAmount+" y ha agregado/quitado/ajustado un nuevo saldo "+addedAmount+" y tiene un nuevo Saldo "+newAmount);
				}else {
					actualResults.set(20, "Se ha cancelado la recarga: "+optionSelectedId+" con el un valor de: "+valueField+" a la cuneta No. "+accountNumbr+ "con un nuevo Saldo: "+newAmount);
					System.out.println("Se ha cancelado la recarga: "+optionSelectedId+" con el un valor de: "+valueField+" a la cuneta No. "+accountNumbr+ "con un nuevo Saldo: "+newAmount);
				}
				driver.close();
				testLink();
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
			}

			public static void recargosSubsanacionesAjustes() throws Exception {
				action = new Actions(driver);
				selOption = ranNumbr(0,3);
				if (selOption<0) {
					selOption=0;
				}
				try {
					//Paso 1.- Entrar a la página de Login del BO de Itata
					driver.get(BoHostUrl);
					takeScreenShot("E:\\Selenium\\","loginBOItajPage"+timet+".jpg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOItaPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.err.println("Un error ha ocurrido en la Página de Inicio");
						fail("Un error ha ocurrido en la Página de Inicio");
					}
					
					//Paso 2.- Loguearse con el usuario 00001/00001
					loginPage("00001","00001");		
					pageSelected = "BackOffice Host";
					mainPage(1);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					takeScreenShot("E:\\Selenium\\","homeBOItaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOItaPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);					
					
					//Paso 3.- Ir al Menú Gestión de Cuentas y seleccionar la opción Seleccionar Cuenta 
					action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
					Thread.sleep(1000);
					pageSelected = "Seleccionar cuenta";
					elementClick(pageSelected);
					Thread.sleep(1000);
					pageSelectedErr(2);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					
					int accountT = ranNumbr(0,1);
					if (accountT<0) {
						accountT=0;
					}

					new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_typeAccount_cmb_dropdown"))).selectByVisibleText(cuentaTipo[accountT]);					
					
					//Pasp 4.- Hacer click en el botón Búsqueda
					elementClick("ctl00_ButtonsZone_BtnSearch_IB_Button");
					
					//Paso 5.- Buscar y hacer click en cualquier cuenta
					selectAccount();
					accountNumbr = getText("ctl00_SectionZone_LblTitle");
					accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
					accountType =  getText("ctl00_ContentZone_ctrlAccountData_lbl_exempt");
					if(driver.getPageSource().contains("CUENTA CERRADA")){
						accountClosed = true;
						return;
					}
					takeScreenShot("E:\\Selenium\\","accountItaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountItaPage.jpeg");
					currentAmount = getText("ctl00_ContentZone_ctrlAccountNotes_label_balance_pounds");					
					int adjustAmount;
					if (currentAmount.contains(".")){
						adjustAmount = Integer.parseInt(currentAmount.replace("$","").replace(" ","").replace(".",""));
					}else {
						adjustAmount = Integer.parseInt(currentAmount.replace("$","").replace(" ",""));
					}				
					if (adjustAmount<0) {
						adjustAmount=1;
					}
					
					//Paso 6.- Hacer click en cualquier botón deseado: Subsanaciones, Recargas, Ajustes o Cancelar Recargas
					if (options[selOption].equals("Cancelación de Recargas")) {
						elementClick("ctl00_ContentZone_BtnCancelRecharge");
						actualResults.set(5, "Se ha cliqueado el botón 'Cancelar Recargas', se ha entrado a la página de Cancelación de Recargas");
						for (int i=6;i<16;i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
							
						}
						takeScreenShot("E:\\Selenium\\","cancelRecargaItaPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","cancelRecargaItaPage.jpeg");
						Thread.sleep(1000);
						
						//Paso 17.- Si se ha cliqueado Cancelar Recarga, ya en la pantalla Cancelar Recarga, seleccionar una recarga existente
						recargasSearch();						
						if (noItemFound==true) {
							return;
						}
						if (recordFoundDisabled==true) {
							return;
						}
						actualResults.set(16, "Se ha seleccionado la Recarga: "+optionSelectedId);
						valueField = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+a+"]/td[4]");
						
						//Paso 18.- Hacer click en el botón Cancelar
						elementClick("ctl00_ContentZone_BtnCancel");
						
						//Paso 19.- Llenar todos los campos pertinentes y hacer click en el botón Confirmar
						SendKeys("ctl00_ContentZone_BoxDescription","Se va a cancelar una recarga con valor de: "+valueField);						
						takeScreenShot("E:\\Selenium\\","cancelRecargaDataItaPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","cancelRecargaDataItaPage.jpeg");
						elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
						Thread.sleep(1000);
						
						//Paso 20.- Hacer click en el botón Aceptar 
						if(isAlertPresent()) {
							driver.switchTo().alert().accept();
						}
						Thread.sleep(4000);
						takeScreenShot("E:\\Selenium\\","PDFcancelRecargaItaPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","PDFcancelRecargaItaPage.jpeg");
						Thread.sleep(1000);			
						
						//Paso 21.- Hacer click en el botón Finalizar o botón Volver (si es Cancelar Recarga)
						elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
						Thread.sleep(1000);
						newAmount = getText("ctl00_ContentZone_ctrlAccountNotes_label_balance_pounds");
						Thread.sleep(2000);
						return;
					}
					
					addedAmount = ranNumbr(100,10000);
					if (options[selOption].equals("Recargas") || options[selOption].equals("Subsanaciones")) {												
						if (options[selOption].equals("Recargas")) {
							elementClick("ctl00_ContentZone_BtnLoads");		
							actualResults.set(5, "Se ha cliqueado el botón 'Recargas', se ha entrado a la página de Pago del Cliente");	
						}
						if (options[selOption].equals("Subsanaciones")) {
							if (adjustAmount<=0) {
								addedAmount=1;
							}else {
								addedAmount = ranNumbr(1,adjustAmount);
							}
							
							elementClick("ctl00_ContentZone_BtnRetrievals");	
							actualResults.set(5, "Se ha cliqueado el botón 'Subsanaciones', se ha entrado a la página de Pago del Cliente");
						}
						
						for (int i=11;i<20;i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
							
						}
					}
					
					if (options[selOption].equals("Ajustes")) {
						for (int i=6;i<11;i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
							
						}
						for (int i=16;i<19;i++) {
							actualResults.set(i, "N/A");
							executionResults.set(i, "");
							
						}
						
						if (adjustAmount<=0) {
							errorAdjust =true;
							errorText = getText("ctl00_LblError");
							return;
						}
						addedAmount = ranNumbr(1,adjustAmount);						
						elementClick("ctl00_ContentZone_BtnAdjustments");
						actualResults.set(5, "Se ha cliqueado el botón 'Ajustes', se ha entrado a la página de Ajustes");
						
						//Paso 12.- Si se ha cliqueado Ajustes, ya en la pantalla Ajustes, llenar todos los campos correspondientes y hacer click en el botón Confirmar
						action.click(driver.findElement(By.id("ctl00_ContentZone_txt_amount_txt_formated"))).build().perform();
						action.sendKeys(String.valueOf(addedAmount)).build().perform();						
						elementClick("ctl00_ContentZone_RadioFrom_"+ranNumbr(0,1));
						SendKeys ("ctl00_ContentZone_BoxDescription", "Nuevo saldo para Ajustes");
						takeScreenShot("E:\\Selenium\\","adjustmentItaPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","adjustmentItaPage.jpeg");
						elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Button");
					}
					//Pasos 8 y 13
					WebElement PayMeansL = driver.findElement(By.id("ctl00_ContentZone_CtType_radioButtonList_payBy"));
					List<WebElement> payMeans = PayMeansL.findElements(By.tagName("tr"));
					int selPay = ranNumbr(0,payMeans.size()-1);
					if (selPay<0) {
						selPay = 0;
					}
					int getPayrow = selPay+1;
					elementClick("ctl00_ContentZone_CtType_radioButtonList_payBy_"+selPay);
					String getPayLabel = getText("//*[@id='ctl00_ContentZone_CtType_radioButtonList_payBy']/tbody/tr["+getPayrow+"]/td/label");
					if (ranNumbr(0,1)==0) {
						elementClick("ctl00_ContentZone_CtType_chk_present");
					}
					if (getPayLabel.equals("cargo en cuenta de peaje")){
							actualResults.set(14, "Se ha seleccionado el medio de pago 'cargo en cuenta de peaje'");						
							actualResults.set(15, "N/A");
							executionResults.set(15, "");
					}
					
					if (options[selOption].equals("Recargas")||options[selOption].equals("Subsanaciones")) {
						action.click(driver.findElement(By.id("ctl00_ContentZone_CtType_text_total_txt_formated"))).build().perform();
						action.sendKeys(String.valueOf(addedAmount)).build().perform();;
					}
					takeScreenShot("E:\\Selenium\\","CustomerPaymentItaPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","CustomerPaymentItaPage.jpeg");
					
					//Pasos 9 y 14
					elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
					Thread.sleep(1000);
					
					//Pasos 10 y 15 
					if (getPayLabel.equals("efectivo")||getPayLabel.equals("cheque")){
						if (getPayLabel.equals("efectivo")){
							if (ranNumbr(0,1)==0) {
								action.click(driver.findElement(By.id("ctl00_ContentZone_CtbyCash_txt_received_txt_formated"))).build().perform();
								action.sendKeys(String.valueOf(ranNumbr(100,10000))).build().perform();													
								elementClick("ctl00_ContentZone_CtbyCash_BtnCalculate");
							}
						}
						if (getPayLabel.equals("cheque")){
							SendKeys("ctl00_ContentZone_CtbyCheque_txt_number_box_data",String.valueOf(ranNumbr(10000,99999999)));
						}
						
						Thread.sleep(500);
						takeScreenShot("E:\\Selenium\\","CustomerPaymentItaPage2"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","CustomerPaymentItaPage2.jpeg");
						
						//Pasos 11 y 16
						elementClick("ctl00_ButtonsZone_BtnExecute_IB_Button");
						Thread.sleep(8000);
						takeScreenShot("E:\\Selenium\\","ReceiptPage"+timet+".jpeg");
						takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","ReceiptaPage.jpeg");
						
						//Paso 21.- Hacer click en el botón Finalizar o botón Volver (si es Cancelar Recarga)
						elementClick("ctl00_ButtonsZone_BtnBack_IB_Button");
					}
						Thread.sleep(1000);
						newAmount = getText("ctl00_ContentZone_ctrlAccountNotes_label_balance_pounds");
						Thread.sleep(2000);
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
			
			public static void recargasSearch() throws Exception{
		  		String recordDis = getText("ctl00_ContentZone_tablePager_LblCounter");
		  		int selRecord;		  		
		  			selRecord = Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)); 
		  		
				if (selRecord>0) {
					selRecord=ranNumbr(1,Integer.parseInt(recordDis.substring(recordDis.indexOf("de ")+3)));										
					WebElement tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
					List <WebElement> tablerows = tableres.findElements(By.tagName("tr"));			
					a = 2;
					int countRow = 1;
					if (tablerows.size()<2) {
						noItemFound = true;
						return;
					}
					int radio = 0;
					for (int i =1; i<=selRecord;i++) {						
						tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
						tablerows = tableres.findElements(By.tagName("tr"));						
						if (selRecord>=tablerows.size()) {
							if (driver.findElement(By.id("ctl00_ContentZone_tablePager_BtnNext")).isEnabled()){
								elementClick("ctl00_ContentZone_tablePager_BtnNext");
							}else {
								noItemFound = true;
								return;
							}
							a=2;
							countRow = countRow+tablerows.size()-2;							
						}	
						boolean recordDisabled= driver.findElement(By.id("ctl00_ContentZone_radio"+radio)).isEnabled();
						
						optionSelectedId = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+a+"]/td[2]");	
						if (recordDisabled==true) {
							recordFound = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+a+"]/td[1]/input")).getAttribute("id");
						}
							for (a=2;a<=tablerows.size();a++) {															
								if (countRow == selRecord) {
									if (recordDisabled==false) {
										recordFoundDisabled=true;
										return;
									}
									elementClick(recordFound);					
									i = selRecord+1;
									break;									
								}else {
									countRow=countRow+1;
									radio = radio +1;
								}
								if (countRow>selRecord) {
									a=2;
									countRow = countRow-5;
								}
								optionSelectedId = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+a+"]/td[2]");
								recordDisabled= driver.findElement(By.id("ctl00_ContentZone_radio"+radio)).isEnabled();
								if (recordDisabled==true) {
									recordFound = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+a+"]/td[1]/input")).getAttribute("id");
								}					
								Thread.sleep(500);						
							}
							Thread.sleep(100);				
					}
				}else {
					noItemFound = true;
					return;
				}
			}
}