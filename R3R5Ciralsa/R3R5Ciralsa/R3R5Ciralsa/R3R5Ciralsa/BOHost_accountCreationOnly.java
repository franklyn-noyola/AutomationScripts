package R3R5Ciralsa.R3R5Ciralsa;

import static org.junit.Assert.*;

import java.util.Calendar;
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

public class BOHost_accountCreationOnly{
			public static int accountCreatelink;
			public static int clickDisc;
			private String testNameSelected = this.getClass().getSimpleName();
			private static Calendar dateYear = Calendar.getInstance();
			private static int currentYear = dateYear.get(Calendar.YEAR);
	
			@Before
			public void setup() throws Exception{
				setUp();
			}
			
			@After
			public void teardown() throws Exception{			  
				    tearDown();
			}
			

			@Test
			public void accountCreationInit() throws Exception {
				configurationSection("Host", testNameSelected, testNameSelected);
				testPlanReading(0,0,2,3);
				accountCreation();				
				Thread.sleep(1000);
				actualResults.set(5,"Se ha creado la cuenta "+accountLink[accountCreatelink]+" No.: "+accountNumbr+" correctamente");
				driver.close();
				testLink();
				System.out.println("Se ha creado la cuenta "+accountLink[accountCreatelink]+" No.: "+accountNumbr+" correctamente");
				System.out.println("Se ha probado en la versión del: "+ getVersion(applevelTested));				
			}

			public static void accountCreation() throws Exception {
				action = new Actions(driver);
				accountCreatelink = ranNumbr(0,1);
				borrarArchivosTemp("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				try{
					//Paso 1.- Entrar a la página de Login del BO de R3R5Ciralsa
					driver.get(BoHostUrl);
					takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginBOPage.jpeg");
					loginPageErr();
					if (pageSelectedErr==true) {
						testLink();
						System.err.println("Un error ha ocurrido en la Página de Inicio");
						fail("Un error ha ocurrido en la Página de Inicio");
					}				
				
					//Paspo 2.- Loguearse con el usuario 00001/00001
					loginPage("00001","00001");
					takeScreenShot("E:\\Selenium\\","homeBOHostPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","homeBOHostPage.jpeg");
					applicationVer = getText("ctl00_statusRight");
					Thread.sleep(2000);					
				
					//Paso 3.- Hacer click en Gestión de cuentas y luego a Crear cuentas
					action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
					Thread.sleep(1000);
					action.moveToElement(driver.findElement(By.linkText("Crear cuenta"))).build().perform();
					Thread.sleep(500);
				
					//Paso 4.- Hacer click en la opción de cuenta que desee: Cliente o Exenta
					if (accountCreatelink==0){
						pageSelected = "Cuenta Cliente";
						elementClick(accountLink[0]);
						actualResults.set(3, "Se ha entrado a la página de creación de cuenta Cliente");
					}else{
						pageSelected = "Cuenta Exenta";
						elementClick(accountLink[1]);
						actualResults.set(3, "Se ha entrado a la página de creación de cuenta Exenta");
					}
					Thread.sleep(500);
					pageSelectedErr(3);
					if (pageSelectedErr==true) {
						driver.close();
						testLink();
						System.err.println("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
						fail("No se pude entrar a la Página "+pageSelected+" al error: "+errorText+" detectado en BackOffice "+applevelTested);
					}
					takeScreenShot("E:\\Selenium\\","accountMainPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountMainPage.jpeg");
					accountNumbr = getText("ctl00_SectionZone_LblTitle");
					accountNumbr = 	accountNumbr.substring(accountNumbr.indexOf(" ")+1,accountNumbr.indexOf("."));
					accountType = getText("ctl00_ContentZone_ctrlAccountData_lbl_accountType");
					switch (accountCreatelink){
					case 0:						postPagoAccount();
												break;
					case 1:						excentaAccount();
												break;
					}
				
				}catch (Exception e){
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
		
			public static void excentaAccount() throws Exception{
				try{
					//Paso 5.- Llenar todos los campos mandatorios o no mandatorios
					int selOp = ranNumbr(0,8);
					int selOp2 = ranNumbr(0,8);
					int accountSel = ranNumbr(0,1);				
					if (accountSel == 0){
						SendKeys("ctl00_ContentZone_ctrlAccountData_txt_ID_box_data",dniLetra("DNI",ranNumbr(10000000,50000000)));
						Thread.sleep(100);
						new Select (driver.findElement(By.id(titulofield))).selectByVisibleText(sexSelc[selOp]);
						Thread.sleep(100);
						SendKeys(namef,nameOp[selOp]);
						Thread.sleep(100);
						SendKeys(surnamef,lastNameOp[selOp]);
						Thread.sleep(100);
						SendKeys("ctl00_ContentZone_ctrlAccountData_txt_surname2_box_data",lastNameOp2[selOp]);
						Thread.sleep(100);
						SendKeys(addressf,addressTec[selOp]);
						Thread.sleep(100);										
						SendKeys(emailf,nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.es");
						Thread.sleep(100);
						SendKeys(phoneCel,String.valueOf(ranNumbr(600000000,699999999)));
						Thread.sleep(100);
						SendKeys(workPhone,workPhone1[selOp]);
						Thread.sleep(100);
						SendKeys(perPhone,String.valueOf(ranNumbr(900000000,999999999)));
						Thread.sleep(100);
						SendKeys(faxPhone,workPhone1[selOp]);	
						Thread.sleep(100);
						selectDropDown("ctl00_ContentZone_ctrlAccountExempt_combo_exempts_cmb_dropdown");
						Thread.sleep(100);
						if (ranNumbr(0,1)>0){
							elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_expiry_1");						
							SendKeys("ctl00_ContentZone_ctrlAccountExempt_cal_date_expiry_box_date",dateSel(currentYear,currentYear+1));
							Thread.sleep(100);
							SendKeys("ctl00_ContentZone_ctrlAccountExempt_txt_days_warning", String.valueOf(ranNumbr(10,15)));
							Thread.sleep(100);
						}
						if (ranNumbr(0,1)>0){
							elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_trips_1");
							SendKeys("ctl00_ContentZone_ctrlAccountExempt_txt_max_trips",String.valueOf(ranNumbr(1,10)));
							Thread.sleep(100);
							SendKeys("ctl00_ContentZone_ctrlAccountExempt_txt_trips_warning",String.valueOf(ranNumbr(1,6)));
							Thread.sleep(100);
						}
											
					}else{
						elementClick("ctl00_ContentZone_ctrlAccountData_radio_company_1");
						SendKeys("ctl00_ContentZone_ctrlAccountData_txt_ID_box_data",dniLetra("CIF",ranNumbr(10000000,19999999)));
						selectDropDown("ctl00_ContentZone_ctrlAccountData_cmb_companyType_cmb_dropdown");
						Thread.sleep(100);
						SendKeys(companyf,"Tecsidel, S.A");
						Thread.sleep(100);
						SendKeys(contactf,nameOp[selOp]+" "+lastNameOp[selOp]+", "+nameOp[selOp2]+" "+lastNameOp[selOp2]);
						Thread.sleep(100);
						SendKeys(addressf,addressTec[2]);
						Thread.sleep(100);						
						SendKeys(emailf,"info@tecsidel.es");
						Thread.sleep(100);
						SendKeys(phoneCel,String.valueOf(ranNumbr(600000000,699999999)));
						Thread.sleep(100);
						SendKeys(workPhone,workPhone1[2]);
						Thread.sleep(100);
						SendKeys(perPhone,String.valueOf(ranNumbr(900000000,999999999)));
						Thread.sleep(100);
						SendKeys(faxPhone,workPhone1[selOp]);					
						Thread.sleep(100);
						selectDropDown("ctl00_ContentZone_ctrlAccountExempt_combo_exempts_cmb_dropdown");
						Thread.sleep(100);
						if (ranNumbr(0,1)>0){
							elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_expiry_1");						
							SendKeys("ctl00_ContentZone_ctrlAccountExempt_cal_date_expiry_box_date",dateSel(currentYear,currentYear+1));
							Thread.sleep(100);
							SendKeys("ctl00_ContentZone_ctrlAccountExempt_txt_days_warning",String.valueOf(ranNumbr(10,15)));
							Thread.sleep(100);
						}
						if (ranNumbr(0,1)>0){
							elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_trips_1");
							SendKeys("ctl00_ContentZone_ctrlAccountExempt_txt_max_trips",String.valueOf(ranNumbr(1,10)));
							Thread.sleep(100);
							SendKeys("ctl00_ContentZone_ctrlAccountExempt_txt_trips_warning",String.valueOf(ranNumbr(1,6)));
							Thread.sleep(100);
						}
						
					}
					takeScreenShot("E:\\Selenium\\","accountdataFilled"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountdataFilled.jpeg");
					Thread.sleep(1000);
					//Paso 6.- Hacer Click en el boton Guardar y demás botones que asi lo requiera la pantalla para crear la cuenta
					elementClick("ctl00_ButtonsZone_BtnSave_IB_Button");
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","accountCreated"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountCreated.jpeg");
					
				}catch (Exception e){
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}

			public static void postPagoAccount() throws Exception{
				try{
					//Paso 5.- Llenar todos los campos mandatorios o no mandatorios
					int selOp = ranNumbr(0,8);
					int selOp2 = ranNumbr(0,8);
					int accountSel = ranNumbr(0,1);					
					if (accountSel == 0){
						SendKeys("ctl00_ContentZone_ctrlAccountData_txt_ID_box_data",dniLetra("DNI",ranNumbr(10000000,50000000)));
						Thread.sleep(100);
						new Select (driver.findElement(By.id(titulofield))).selectByVisibleText(sexSelc[selOp]);
						Thread.sleep(100);
						SendKeys(namef,nameOp[selOp]);
						Thread.sleep(100);
						SendKeys(surnamef,lastNameOp[selOp]);
						Thread.sleep(100);
						SendKeys("ctl00_ContentZone_ctrlAccountData_txt_surname2_box_data",lastNameOp2[selOp]);
						Thread.sleep(100);
						SendKeys(addressf,addressTec[selOp]);
						Thread.sleep(100);										
						SendKeys(emailf,nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.es");
						Thread.sleep(100);
						SendKeys(phoneCel,String.valueOf(ranNumbr(600000000,699999999)));
						Thread.sleep(100);
						SendKeys(workPhone,workPhone1[selOp]);
						Thread.sleep(100);
						SendKeys(perPhone,String.valueOf(ranNumbr(900000000,999999999)));
						Thread.sleep(100);
						SendKeys(faxPhone,workPhone1[selOp]);	
						Thread.sleep(100);					
						if (ranNumbr(0,1)>0){
							elementClick("ctl00_ContentZone_ctrlAccountData_chk_dtEnd");						
							SendKeys("ctl00_ContentZone_ctrlAccountData_dt_end_box_date",dateSel(currentYear+1,currentYear+15));
							Thread.sleep(100);
							SendKeys("ctl00_ContentZone_ctrlAccountData_txt_warningThreshold_box_data", String.valueOf(ranNumbr(10,15)));
							Thread.sleep(100);
						}					
					}else{
						elementClick("ctl00_ContentZone_ctrlAccountData_radio_company_1");
						driver.findElement(By.id("ctl00_ContentZone_ctrlAccountData_txt_ID_box_data")).sendKeys(dniLetra("CIF",ranNumbr(10000000,19999999)));
						selectDropDown("ctl00_ContentZone_ctrlAccountData_cmb_companyType_cmb_dropdown");
						Thread.sleep(100);
						SendKeys(companyf,"Tecsidel, S.A");
						Thread.sleep(100);
						SendKeys(contactf,nameOp[selOp]+" "+lastNameOp[selOp]+", "+nameOp[selOp2]+" "+lastNameOp[selOp2]);
						Thread.sleep(100);
						SendKeys(addressf,addressTec[2]);
						Thread.sleep(100);						
						SendKeys(emailf,"info@tecsidel.es");
						Thread.sleep(100);
						SendKeys(phoneCel,String.valueOf(ranNumbr(600000000,699999999)));
						Thread.sleep(100);
						SendKeys(workPhone,workPhone1[2]);
						Thread.sleep(100);
						SendKeys(perPhone,String.valueOf(ranNumbr(900000000,999999999)));
						Thread.sleep(100);
						SendKeys(faxPhone,workPhone1[selOp]);					
						Thread.sleep(100);										
						if (ranNumbr(0,1)>0){
							elementClick("ctl00_ContentZone_ctrlAccountData_chk_dtEnd");						
							SendKeys("ctl00_ContentZone_ctrlAccountData_dt_end_box_date",dateSel(currentYear,currentYear+1));
							Thread.sleep(100);						
							SendKeys("ctl00_ContentZone_ctrlAccountData_txt_warningThreshold_box_data",String.valueOf(ranNumbr(10,15)));
							Thread.sleep(100);
						}
						
					}
					elementClick("ctl00_ContentZone_ctrlAccountData_multi_discount_img_expand");
					WebElement discountsList = driver.findElement(By.id("ctl00_ContentZone_ctrlAccountData_multi_discount_div_list"));
					List <WebElement> discountOptions = discountsList.findElements(By.tagName("div"));
					clickDisc = ranNumbr(1,discountOptions.size());
					if (clickDisc==discountOptions.size()) {
						for (int i=1;i<=clickDisc;i++) {
							if (!driver.findElement(By.xpath("*//div[22]/div/table/tbody/tr/td[2]/div/div[2]/div["+i+"]/input")).isSelected()) {
								elementClick("*//div[22]/div/table/tbody/tr/td[2]/div/div[2]/div["+i+"]/input");
							}							
							Thread.sleep(500);
						}
					}else {	
						for (int i=1;i<=clickDisc;i++) {
							int click2 = ranNumbr(1,discountOptions.size());
							elementClick("*//div[22]/div/table/tbody/tr/td[2]/div/div[2]/div["+click2+"]/input");
							Thread.sleep(500);
						}
					}
					
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","accountdataFilled"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountdataFilled.jpeg");
					
					//paso 6.- Hacer Click en el boton Guardar y demás botones que asi lo requiera la pantalla para crear la cuenta
					elementClick("ctl00_ButtonsZone_BtnSave_IB_Button");
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","accountCreated"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\"+projectNamePath+testClassName+"\\attachments\\","accountCreated.jpeg");
				}catch (Exception e){
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
						
}