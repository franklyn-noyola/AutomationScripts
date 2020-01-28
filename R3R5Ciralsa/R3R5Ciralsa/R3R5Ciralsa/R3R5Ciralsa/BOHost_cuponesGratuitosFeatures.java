package R3R5Ciralsa.R3R5Ciralsa;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
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


public class BOHost_cuponesGratuitosFeatures{
		private String testNameSelected = this.getClass().getSimpleName();
		private static String [] cuponesOption = {"Crear", "Ver", "Anular"};				
		private static int opSel;
		private static List <String> ligeroVoucherCat;
		private static List <String> pesado1VoucherCat;
		private static List <String> pesado2VoucherCat;		
		public static String header="";
		public static String header1="";
		public static String header2="";
		private static List <String> catSelected;
		private static String ligeroCatView="";
		private static String pesado1CatView="";
		private static String pesado2CatView="";
		private static String voucherConsumed;
		private static String ligeroCatConsumed;
		private static String ligeroVoucherCount;
		private static String pesado1CatConsumed;
		private static String pesado1VoucherCount;
		private static String pesado2CatConsumed;
		private static String pesado2VoucherCount;		
		private static String cat1="";
		private static String cat2="";
		private static String cat3="";
		private static String voucherCount;
		private static boolean noCreated=false;
		private static int ligeroCat;
		private static int pesado1Cat;
		private static int pesado2Cat;
		private static String catNumbers; 
		private static String createdDate;
		private static int totalVouchers;
		
		
		@Before
		public void setup() throws Exception{
			setUp();
		}
		
		@After
		public void teardown() throws Exception{			  
			 tearDown();
		}

		@Test
		public void cuponesGratuitosFeaturesInit() throws Exception {				
			borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");
			opSel = ranNumbr(0,2);
			if (opSel==0) {
				configurationSection("Host", testNameSelected+" - Crear", testNameSelected);
				testPlanReading(6,0,2,3);
			}
			if (opSel==1) {
				configurationSection("Host", testNameSelected+" - Ver", testNameSelected);
				testPlanReading(6,5,7,8);
			}
			if (opSel==2) {
				configurationSection("Host", testNameSelected+" - Anular", testNameSelected);
				testPlanReading(6,10,12,13);
			}
			
			cuponesGratuitosFeatures();
			
			if (accountClosed==true) {
				actualResults.set(6, "No se puede "+cuponesOption[opSel]+" ningún cupon/es gratuitos en la cuenta No. "+accountNumbr+" porque la cuenta está cerrada");
				for (int i=7;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("No se puede "+cuponesOption[opSel]+" ningún cupon/es gratuitos en la cuenta No. "+accountNumbr+" porque la cuenta está cerrada");
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;
			}
			if (noCreated==true) {
				actualResults.set(13,"No se puede crear Cupones Gratuitos para la cuenta No.: "+accountNumbr+" debido a que: "+errorText);
				driver.close();
				testLink();
				System.out.println("No se puede crear Cupones Gratuitos para la cuenta No.: "+accountNumbr+" debido a que: "+errorText);
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));
				return;
			}
			
			if (noItemFound==true) {
				actualResults.set(7, "No se puede "+cuponesOption[opSel]+" un Paquete de Cupones debido a que no hay Paquete de Cupones creados para la cuenta: "+accountNumbr);
				for (int i=8;i<actualResults.size();i++) {
					actualResults.set(i, "N/A");
					executionResults.set(i, "");
					
				}
				driver.close();
				testLink();
				System.out.println("No se puede "+cuponesOption[opSel]+" un Paquete de Cupones debido a que no hay Paquete de Cupones creados para la cuenta: "+accountNumbr);
				return;
			}
			
			if (cuponesOption[opSel].equals("Crear")) {
				int cat = 0;
				for (int i=1;i<=Integer.parseInt(catNumbers);i++) {
					if (catSelected.get(cat).contains("01 - Ligero")) {
						cat1 = "Categoria: 01 - Ligero: contiene: "+ligeroCat+" cupon/es gratuito/s creado/s con el/los IDs/: "+ligeroVoucherCat.toString()+"\n";
					}
					if (catSelected.get(cat).contains("02 - Pesado 1")) {
						cat2 = "Categoria: 02 - Pesado 1: contiene: "+pesado1Cat+" cupon/es gratuito/s creado/s con el/los ID/s: "+pesado1VoucherCat.toString()+"\n";
					}
					if (catSelected.get(cat).contains("03 - Pesado 2")) {
						cat3 = "Categoria: 03 - Pesado 2: contiene: "+pesado2Cat+" cupon/es gratuito/s creado/s con el/los ID/s: "+pesado2VoucherCat.toString();
					}
					cat = cat+1;
				}
				actualResults.set(13,"Se ha creado un Paquete de Cupones con un total de "+totalVouchers+" Cupon/es gratuito/s en la cuenta No.: "+accountNumbr+" para "+catNumbers+" categoria/s, en fecha: "+createdDate+" de las cuales fueron: "+'\n'+cat1+cat2+cat3);
				System.out.println("Se ha creado un Paquete de Cupones con un total de "+totalVouchers+" Cupon/es gratuito/s en la cuenta No.: "+accountNumbr+" para "+catNumbers+" categoria/s, en fecha: "+createdDate+" de las cuales fueron: "+'\n'+cat1+cat2+cat3);
			}
			
			if (cuponesOption[opSel].equals("Anular")) {
				actualResults.set(9,"Se ha anulado un Paquete de Cupones de fecha: "+createdDate+" con un total de "+voucherCount+" cupon/es en la cuenta No.: "+accountNumbr);
				System.out.println("Se ha anulado un Paquete de Cupones de fecha: "+createdDate+" con un total de "+voucherCount+" cupon/es en la cuenta No.: "+accountNumbr);
			}
			if (cuponesOption[opSel].equals("Ver")) {
				
				int cat = 0;				
				for (int i=1;i<=Integer.parseInt(catNumbers);i++) {
					if (catSelected.get(cat).contains("01 - Ligero")) {
						header = String.format("%15s", "Abodo Id")+String.format("%28s", "Consumido")+String.format("%30s", "Fecha de Consumido")+"\n";
						cat1 = "Categoria: 01 - Ligero: contiene: "+ligeroVoucherCount+" cupon/es gratuito/s creado/s con: "+ligeroCatConsumed+" cupon/es consumido/s"+"\n";
						ligeroCatView = ligeroVoucherCat.toString().replaceAll("[\\[\\]]", "").replaceAll(", ", "\n")+"\n"+"\n";						
					}
					if (catSelected.get(cat).contains("02 - Pesado 1")) {
						header1 = String.format("%15s", "Abodo Id")+String.format("%28s", "Consumido")+String.format("%30s", "Fecha de Consumido")+"\n";
						cat2 = "Categoria: 02 - Pesado 1: contiene: "+pesado1VoucherCount+" cupon/es gratuito/s creado/s con: "+pesado1CatConsumed+" cupon/es consumido/s"+"\n";
						pesado1CatView = pesado1VoucherCat.toString().replaceAll("[\\[\\]]", "").replaceAll(", ", "\n")+"\n"+"\n";						
					}
					if (catSelected.get(cat).contains("03 - Pesado 2")) {
						header2 = String.format("%15s", "Abodo Id")+String.format("%28s", "Consumido")+String.format("%30s", "Fecha de Consumido")+"\n";
						cat3 = "Categoria: 03 - Pesado 2: contiene: "+pesado2VoucherCount+" cupon/es gratuito/s creado/s con: "+pesado2CatConsumed+" cupon/es consumido/s"+"\n";
						pesado2CatView = pesado2VoucherCat.toString().replaceAll("[\\[\\]]", "").replaceAll(", ", "\n");
					}
					cat = cat+1;
				}
				actualResults.set(9,"La cuenta seleccioada: "+accountNumbr+" contiene: "+selRecord+" paquetes  de cupones y se ha Visualizado un Paquete de Cupones de fecha: "+createdDate+" con un total de "+voucherCount+" cupon/es creado/s de los cuales se han consumido: "+voucherConsumed+" cupon/es, de la cuales los cupones se han creado en: "+catNumbers+" categorías, de las cuales"+'\n'+cat1+header+ligeroCatView+cat2+header1+pesado1CatView+cat3+header2+pesado2CatView);
				System.out.println("La cuenta seleccioada: "+accountNumbr+" contiene: "+selRecord+" paquetes  de cupones y se ha Visualizado un Paquete de Cupones de fecha: "+createdDate+" con un total de "+voucherCount+" cupon/es creado/s de los cuales se han consumido: "+voucherConsumed+" cupon/es, de la cuales los cupones se han creado en: "+catNumbers+" categorías, de las cuales"+'\n'+cat1+header+ligeroCatView+cat2+header1+pesado1CatView+cat3+header2+pesado2CatView);
			}
			driver.close();
			testLink();
			System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));

		}
		
		public static void cuponesGratuitosFeatures() throws Exception{
			action = new Actions(driver);			
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
				Thread.sleep(500);
				
				//Paso 3.- Hacer click en Gestión de cuentas y luego Seleccionar cuentas
				action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
				Thread.sleep(500);
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
				takeScreenShot("E:\\Selenium\\","selectAccounttPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","selectAccounttPage.jpeg");
				//Paso 4.- Ir a la lista desplegable Tipo de Cuenta y seleccionar la opción Exenta
				new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_typeAccount_cmb_dropdown"))).selectByVisibleText("Exenta");
				Thread.sleep(500);
				
				//Paso 5.- Hacer click en el botón Buscar
				elementClick(searchButton);
				Thread.sleep(500);
				
				//Paso 6.- Hacer click en la cuenta deseada
				selectAccount();
				Thread.sleep(1000);
				accountNumbr = getText("ctl00_SectionZone_LblTitle");
				accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
				takeScreenShot("E:\\Selenium\\","accountMainPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountMainPage.jpeg");
				
				//Paso 7.-Si la cuenta no está cerrada, hacer click en el botón Cupones Gratuitos
				if (driver.getPageSource().contains("CUENTA CERRADA")){
					takeScreenShot("E:\\Selenium\\","accountClosed"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountClosed.jpeg");
					accountClosed = true;
					return;					
				}
				elementClick("ctl00_ContentZone_BtnVouchersPrepay");
				Thread.sleep(500);
				
				takeScreenShot("E:\\Selenium\\","vouchesMainPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","vouchesMainPage.jpeg");
				if (cuponesOption[opSel].equals("Crear")){
					createVoucherFeature();
					return;
				}
				if (cuponesOption[opSel].equals("Ver") || cuponesOption[opSel].equals("Anular")){
					viewCancelVoucherFeature();
					return;
				}
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}
		
		public static void createVoucherFeature() throws Exception{
			try {
				elementClick("ctl00_ContentZone_BtnCreate");
				Thread.sleep(500);
				takeScreenShot("E:\\Selenium\\","vouchesMainPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","vouchesMainPage.jpeg");				
				selectDropDown("ctl00_ContentZone_companyPlazaPlaza_cmb_tollCompany_cmb_dropdown");
				WebElement companySel = new Select(driver.findElement(By.id("ctl00_ContentZone_companyPlazaPlaza_cmb_tollCompany_cmb_dropdown"))).getFirstSelectedOption();
				String companySelected = companySel.getText();
				actualResults.set(8,"Se ha seleccionado la Autopista: "+companySelected);
				selectDropDown("ctl00_ContentZone_companyPlazaPlaza_cmb_plazaOrigin_cmb_dropdown");
				WebElement originPlazaSel = new Select(driver.findElement(By.id("ctl00_ContentZone_companyPlazaPlaza_cmb_plazaOrigin_cmb_dropdown"))).getFirstSelectedOption();
				String originPlazaSelected = originPlazaSel.getText();
				actualResults.set(9,"Se ha seleccionado la Estación Origen: "+originPlazaSelected);
				selectDropDown("ctl00_ContentZone_companyPlazaPlaza_cmb_plazaDestiny_cmb_dropdown");
				WebElement destinyPlazaSel = new Select(driver.findElement(By.id("ctl00_ContentZone_companyPlazaPlaza_cmb_plazaDestiny_cmb_dropdown"))).getFirstSelectedOption();
				String destinyPlazaSelected = destinyPlazaSel.getText();
				actualResults.set(10,"Se ha seleccionado la Estación Origen: "+destinyPlazaSelected);
				ligeroCat = ranNumbr (0,5);
				pesado1Cat = ranNumbr(0,5);
				pesado2Cat = ranNumbr (0,5);
				totalVouchers = ligeroCat+pesado1Cat+pesado2Cat;
				SendKeys("ctl00_ContentZone_txt_NumberVouchers_1",String.valueOf(ligeroCat));
				SendKeys("ctl00_ContentZone_txt_NumberVouchers_2",String.valueOf(pesado1Cat));
				SendKeys("ctl00_ContentZone_txt_NumberVouchers_3",String.valueOf(pesado2Cat));
				actualResults.set(11, "Se ha puesto a la categoría Ligero: "+ligeroCat+" cupones; categoría Pesado1: "+pesado1Cat+" cupones; categoría Pesado2: "+pesado2Cat+" cupones");
				Thread.sleep(500);
				takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","dataFilled.jpeg");
				elementClick(confirmarButton);
				Thread.sleep(1000);
				elementClick("popup_ok");
				if (driver.getPageSource().contains("La estación de origen debe ser distinta a la estación destino") || driver.getPageSource().contains("No se ha incluido ningún cupón")) {
					errorText = getText("//*[@id='toast-container']/div/div");
					takeScreenShot("E:\\Selenium\\","noVoucherCreated"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","noVoucherCreated.jpeg");
					noCreated=true;
					return;
				}
				
				Thread.sleep(2000);
				elementClick("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr[2]/td[1]/input");
				createdDate = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr[2]/td[2]");
				elementClick("ctl00_ContentZone_BtnView");
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","voucherCreatedPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","voucherCreatedPage.jpeg");
				String catNumber = getText("ctl00_ContentZone_tablePager_LblCounter");
				catNumbers = catNumber.substring(catNumber.indexOf("de ")+3);
				int a = 2;				
				int catSel = 0;
				catSelected = new ArrayList<String>();
				for (int i = 1;i<=Integer.parseInt(catNumbers);i++) {
					elementClick("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+a+"]/td[1]/input");
					catSelected.add(getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+a+"]/td[2]"));					
					elementClick("ctl00_ContentZone_BtnView");
					Thread.sleep(1000);
					catNumber = getText("ctl00_ContentZone_tablePagerDetail_LblCounter");
					String catDetail = catNumber.substring(catNumber.indexOf("de ")+3);
					int x = 2;
					if (catSelected.get(catSel).contains("01 - Ligero")) {
						takeScreenShot("E:\\Selenium\\","catLigeroVoucherDetails"+timet+".jpeg");
						takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","catLigeroVoucherDetails.jpeg");
						ligeroVoucherCat = new ArrayList<String>();
						for (int cat = 1; cat<=Integer.parseInt(catDetail);cat++) {
							ligeroVoucherCat.add(getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[1]"));
							Thread.sleep(100);
							x=x+1;
						}
					}
					if (catSelected.get(catSel).contains("02 - Pesado 1")) {
						takeScreenShot("E:\\Selenium\\","catPesado1VoucherDetails"+timet+".jpeg");
						takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","catPesado1VoucherDetails.jpeg");
						pesado1VoucherCat = new ArrayList<String>();
						for (int cat = 1; cat<=Integer.parseInt(catDetail);cat++) {
							pesado1VoucherCat.add(getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[1]"));
							Thread.sleep(100);
							x=x+1;
						}
					}
					if (catSelected.get(catSel).contains("03 - Pesado 2")) {
						takeScreenShot("E:\\Selenium\\","catPesado2VoucherDetails"+timet+".jpeg");
						takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","catPesado2VoucherDetails.jpeg");
						pesado2VoucherCat = new ArrayList<String>();
						for (int cat = 1; cat<=Integer.parseInt(catDetail);cat++) {
							pesado2VoucherCat.add(getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[1]"));
							Thread.sleep(100);
							x=x+1;
						}
					}
					a = a+1;
					catSel = catSel+1;
					Thread.sleep(1000);
					elementClick("ctl00_ContentZone_Button_ClosePopup");
					Thread.sleep(1500);
				}
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}
		
		public static void viewCancelVoucherFeature() throws Exception{
			try {
				//Paso 8.- Si hay cupones disponibles, hacer click en cualquiera de los cupones  
				itemSearchandSelect();
				if (noItemFound==true) {
					return;
				}
				createdDate = optionSelectedId;
				voucherCount = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectedRow+"]/td[6]");
				voucherConsumed = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectedRow+"]/td[8]");
				
				if (cuponesOption[opSel].equals("Anular")) {
					//TC BOHost_cuponesGratuitosFeatures - Anular: Paso 9.- Hacer click en el botón Anular
					elementClick("ctl00_ContentZone_BtnCancel");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","canceloVoucherpopup"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","canceloVoucherpopup.jpeg");
					//TC BOHost_cuponesGratuitosFeatures - Anular: Paso 10.- Hacer click en el botón Confirmar
					elementClick("popup_ok");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","cancelledVoucher"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","cancelledVoucher.jpeg");
					return;
				}
				if  (cuponesOption[opSel].equals("Ver")){
					//TC BOHost_cuponesGratuitosFeatures - Ver: Paso 9.-  Hacer click en el botón Ver
					elementClick("ctl00_ContentZone_BtnView");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","viewVoucherPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","viewVoucherPage.jpeg");
					catSelected = new ArrayList<String>();
					rowSelectedCi = 2;					
					String catNumber = getText("ctl00_ContentZone_tablePager_LblCounter");
					catNumbers = catNumber.substring(catNumber.indexOf("de ")+3);					
					int cat = 0;
						for (int i=1;i<=Integer.parseInt(catNumbers);i++ ) {
							elementClick("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowSelectedCi+"]/td[1]/input");							
							catSelected.add(getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowSelectedCi+"]/td[2]"));														
							Thread.sleep(1000);							
							String catDetail; 
							int x=2;			
							//TC BOHost_cuponesGratuitosFeatures - Ver: Paso 10.-  Hacer click cada una de las categorías que contengan cupones gratuitos y hacer click en el botón Ver
							if (catSelected.get(cat).contains("01 - Ligero")) {								
								ligeroVoucherCat = new ArrayList<String>();
								ligeroVoucherCount = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowSelectedCi+"]/td[3]");
								ligeroCatConsumed = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowSelectedCi+"]/td[4]");
								elementClick("ctl00_ContentZone_BtnView");
								catNumber = getText("ctl00_ContentZone_tablePagerDetail_LblCounter");
								Thread.sleep(500);
								takeScreenShot("E:\\Selenium\\","catLigeroVoucherDetails"+timet+".jpeg");
								takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","catLigeroVoucherDetails.jpeg");
								catDetail = catNumber.substring(catNumber.indexOf("de ")+3);								
								for (int catSel = 1;catSel<=Integer.parseInt(catDetail);catSel++) {
									if (getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[4]").equals(" ")) {
										ligeroVoucherCat.add(getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[1]")+"              "+getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[3]"));
									}else {
										ligeroVoucherCat.add(getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[1]")+"              "+getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[3]")+"              "+getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[4]"));
									}
									Thread.sleep(500);									
									x = x+1;
								}
																
							}
							if (catSelected.get(cat).contains("02 - Pesado 1")) {								
								pesado1VoucherCat = new ArrayList<String>();
								pesado1VoucherCount = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowSelectedCi+"]/td[3]");
								pesado1CatConsumed = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowSelectedCi+"]/td[4]");
								elementClick("ctl00_ContentZone_BtnView");
								Thread.sleep(500);
								catNumber = getText("ctl00_ContentZone_tablePagerDetail_LblCounter");
								takeScreenShot("E:\\Selenium\\","catpesado1VoucherDetails"+timet+".jpeg");
								takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","catpesado1VoucherDetails.jpeg");
								catDetail = catNumber.substring(catNumber.indexOf("de ")+3);
								for (int catSel = 1;catSel<=Integer.parseInt(catDetail);catSel++) {
									if (getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[4]").equals(" ")) {
										pesado1VoucherCat.add(getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[1]")+"              "+getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[3]"));
									}else {
										pesado1VoucherCat.add(getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[1]")+"              "+getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[3]")+"               "+getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[4]"));	
									}
									Thread.sleep(1000);
									x = x+1;
								}								
								
							}
							if (catSelected.get(cat).contains("03 - Pesado 2")) {								
								pesado2VoucherCat = new ArrayList<String>();
								pesado2VoucherCount = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowSelectedCi+"]/td[3]");
								pesado2CatConsumed = getText("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+rowSelectedCi+"]/td[4]");
								elementClick("ctl00_ContentZone_BtnView");
								Thread.sleep(500);
								catNumber = getText("ctl00_ContentZone_tablePagerDetail_LblCounter");
								takeScreenShot("E:\\Selenium\\","catpesado2VoucherDetails"+timet+".jpeg");
								takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","catpesado2VoucherDetails.jpeg");
								catDetail = catNumber.substring(catNumber.indexOf("de ")+3);
								for (int catSel = 1;catSel<=Integer.parseInt(catDetail);catSel++) {
									if (getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[4]").equals(" ")) {
										pesado2VoucherCat.add(getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[1]")+"              "+getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[3]"));
									}else {
										pesado2VoucherCat.add(getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[1]")+"              "+getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[3]")+"               "+getText("//*[@id='ctl00_ContentZone_TblDetails']/tbody/tr["+x+"]/td[4]"));	
									}
																		
									Thread.sleep(500);
									x = x+1;
								}
								
								
							}
							rowSelectedCi = rowSelectedCi+1;
							cat = cat+1;		
							Thread.sleep(1000);
							elementClick("ctl00_ContentZone_Button_ClosePopup");
							Thread.sleep(1500);
							
						}
				}
			}catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getMessage());
				fail (e.getMessage());
				throw(e);
			}
		}
		
		
			
}