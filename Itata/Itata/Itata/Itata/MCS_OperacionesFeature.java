package Itata;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static SettingFiles.Itata_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;

public class MCS_OperacionesFeature{	 
			private static String [] operOptions = {"CambiarModo","AbrirVia","CerrarVia","CambiarSenal","CambiarBarEntrada","CambiarBarSalida","ReiniciarVia","SimulacionPaso","ReiniciarControLogico","CambiarClase","PagoRemoto","LiquidacionParcial"};
			private static int selOption;		
			private static String mcsVer;
			private static String operationWindow;
			private static String senal;
			private String testNameSelected=this.getClass().getSimpleName();
			
			@Before
			public void setup() throws Exception{
					setUp();
			}

			@After
			public void teardown() throws Exception{			  
				    tearDown();
			}

			@Test
			public void OperacionesFeature() throws Exception {
				configurationSection("MCS",testNameSelected,testNameSelected);				
				borrarArchivosTemp("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\");
				selOption = ranNumbr(0,operOptions.length-1);				
				try {
					driver.get(MCSUrlIta);
					Thread.sleep(100);
					takeScreenShot("E:\\Selenium\\","loginMCSItataPage"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","loginMCSItataPage.jpeg");
					loginPageMCS("00001","00001");
					mcsVer = getText(mcsVersion);
					Thread.sleep(2000);
					elementClick("lane_name_link_1");
					Thread.sleep(1000);					
					takeScreenShot("E:\\Selenium\\","operationsPage"+timet+".jpeg");
					takeScreenShot("E:\\Selenium\\"+projectNamePath+testClassName+"\\attachments\\","operationsPage.jpeg");
					elementClick("//*[@id='lyr_menu']/div[2]");
					Thread.sleep(1500);
					switch (operOptions[selOption]) {
						case "CambiarModo":					cambiarModo();
															break;															
						case "AbrirVia":					abrirVia();
															break;
						case "CerrarVia":					cerrarVia();
															break;
						case "CambiarSenal":				cambiarSenal();
															break;
						case "CambiarBarEntrada":			cambiarBarEntrada();
															break;
						case "CambiarBarSalida":			cambiarBarSalida();
															break;
						case "ReiniciarVia":				reiniciarVia();
															break;
						case "SimulacionPaso":				simulacionPaso();
															break;
						case "ReiniciarControLogico":		reiniciarControLogico();
															break;
						case "CambiarClase":				cambiarClase();
															break;
						case "PagoRemoto":					pagoRemoto();
															break;
						case "LiquidacionParcial":			liquidacionParcial();
															break;
					}					
					Thread.sleep(3000);
					takeScreenShot("E:\\Selenium\\","operationsAction"+timet+".jpeg");
					takeScreenShot("E:\\Workspace\\"+projectNamePath+testClassName+"\\attachments\\","operationsAction.jpeg");
					System.out.println("Probado en la Versión de MCS: "+mcsVer);
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
	
			}
			public static void cambiarModo() throws Exception{
				try {
					elementClick("//*[@id='dropmenudiv']/a[1]");
					Thread.sleep(1000);									
					if(ranNumbr(0,1)==0){
						senal = getText("btn_showChangeLaneModeWindow_0");
						elementClick("btn_showChangeLaneModeWindow_0");
						
					}else {
						senal = getText("btn_showChangeLaneModeWindow_1");
						elementClick("btn_showChangeLaneModeWindow_1");
						
					}
					Thread.sleep(1000);
					operationWindow = getText("titlebar");//driver.findElement(By.xpath("//*[@id='lbl_alert_title']")).getText();
					operationWindow = operationWindow.trim();
					errorText = getText("lbl_message");
					if (errorText.contains("La vía no está cerrada")) {
						System.out.println(operationWindow+": "+ errorText);
						fail(operationWindow+": "+ errorText);
					}
					System.out.println(operationWindow+": "+errorText+" a: "+senal);
					Thread.sleep(1000);
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
			
			public static void abrirVia() throws Exception{
				try {
					elementClick("//*[@id='dropmenudiv']/a[2]");
					Thread.sleep(1000);
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					Thread.sleep(2000);
					operationWindow = getText("titlebar");//driver.findElement(By.xpath("//*[@id='lbl_alert_title']")).getText();
					operationWindow = operationWindow.trim();
					errorText = getText("lbl_message");
					if (errorText.contains("La vía no está cerrada")) {
						System.out.println(operationWindow+": "+ errorText);
						fail(operationWindow+": "+ errorText);
					}
					System.out.println(operationWindow+": "+errorText);
					Thread.sleep(1000);
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
			public static void cerrarVia() throws Exception{
				try {
					elementClick("//*[@id='dropmenudiv']/a[3]");
					Thread.sleep(1000);
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					Thread.sleep(2000);					
					SendKeys("sipt_fieldform_fieldpart_pwduser","00001");
					SendKeys("sipt_fieldform_fieldpart_pwdpassword","00001");
					elementClick("btn_sipt_fieldform_okpart_pwd");
					Thread.sleep(1000);
					operationWindow = getText("titlebar");
					operationWindow = operationWindow.trim();
					errorText = getText("lbl_message");
					if (operationWindow.contains("Error")) {
						System.out.println(operationWindow+": "+ errorText);
						fail(operationWindow+": "+ errorText);
					}					
					System.out.println(operationWindow+": "+errorText);
					Thread.sleep(1000);
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
			public static void cambiarBarEntrada() throws Exception{
				try {
					elementClick("//*[@id='dropmenudiv']/a[5]");
					Thread.sleep(1000);					
					if(ranNumbr(0,1)==0){	
						senal = "Abierta";
						elementClick("btn_showChangeBarrierWindow_open");
						
					}else {					
						senal = "Cerrada";
						elementClick("btn_showChangeBarrierWindow_close");						
					}
					Thread.sleep(1000);
					operationWindow = getText("titlebar");
					operationWindow = operationWindow.trim();
					errorText = getText("lbl_message");
					if (operationWindow.contains("Error")) {
						System.out.println(operationWindow+": "+ errorText);
						fail(operationWindow+": "+ errorText);
					}
					System.out.println(operationWindow+": "+errorText+" a: "+senal);
					Thread.sleep(1000);
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
			public static void cambiarSenal() throws Exception{
				try {
					elementClick("//*[@id='dropmenudiv']/a[4]");
					Thread.sleep(1000);					
					if(ranNumbr(0,1)==0){
						senal = getText("btn_showChangeLaneSignWindow_cross");
						elementClick("btn_showChangeLaneSignWindow_cross");
						
					}else {
						senal = getText("btn_showChangeLaneSignWindow_arrow");
						elementClick("btn_showChangeLaneSignWindow_arrow");						
					}
					Thread.sleep(1000);
					operationWindow = getText("titlebar");//driver.findElement(By.xpath("//*[@id='lbl_alert_title']")).getText();
					operationWindow = operationWindow.trim();
					errorText = getText("lbl_message");
					if (operationWindow.contains("Error")) {
						System.out.println(operationWindow+": "+ errorText);
						fail(operationWindow+": "+ errorText);
					}
					System.out.println(operationWindow+": "+errorText+" a: "+senal);
					Thread.sleep(1000);
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
			public static void cambiarBarSalida() throws Exception{
				try {
					elementClick("//*[@id='dropmenudiv']/a[6]");
					Thread.sleep(1000);					
					if(ranNumbr(0,1)==0){	
						senal = "Abierta";
						elementClick("btn_showChangeBarrierWindow_open");
						
					}else {					
						senal = "Cerrada";
						elementClick("btn_showChangeBarrierWindow_close");						
					}
					Thread.sleep(1000);
					operationWindow = getText("titlebar");
					operationWindow = operationWindow.trim();
					errorText = getText("lbl_message");
					if (operationWindow.contains("Error")) {
						System.out.println(operationWindow+": "+ errorText);
						fail(operationWindow+": "+ errorText);
					}
					System.out.println(operationWindow+": "+errorText+" a: "+senal);
					Thread.sleep(1000);
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
			
			public static void reiniciarVia() throws Exception{
				try {
					elementClick("//*[@id='dropmenudiv']/a[7]");
					Thread.sleep(1000);
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					Thread.sleep(2000);					
					operationWindow = getText("titlebar");
					operationWindow = operationWindow.trim();
					errorText = getText("lbl_message");
					if (operationWindow.contains("Error")) {
						System.out.println(operationWindow+": "+ errorText);
						fail(operationWindow+": "+ errorText);
					}					
					System.out.println(operationWindow+": "+errorText);
					Thread.sleep(1000);
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
			
			public static void simulacionPaso() throws Exception{
				try {
					elementClick("//*[@id='dropmenudiv']/a[8]");
					Thread.sleep(1000);
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					Thread.sleep(2000);					
					operationWindow = getText("titlebar");
					operationWindow = operationWindow.trim();
					errorText = getText("lbl_message");
					if (operationWindow.contains("Error")) {
						System.out.println(operationWindow+": "+ errorText);
						fail(operationWindow+": "+ errorText);
					}					
					System.out.println(operationWindow+": "+errorText);
					Thread.sleep(1000);
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
			
			public static void reiniciarControLogico() throws Exception{
				try {
					elementClick("//*[@id='dropmenudiv']/a[9]");
					Thread.sleep(1000);
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					Thread.sleep(2000);					
					operationWindow = getText("titlebar");
					operationWindow = operationWindow.trim();
					errorText = getText("lbl_message");
					if (operationWindow.contains("Error")) {
						System.out.println(operationWindow+": "+ errorText);
						fail(operationWindow+": "+ errorText);
					}					
					System.out.println(operationWindow+": "+errorText);
					Thread.sleep(1000);
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
			
			public static void cambiarClase() throws Exception{
				try {
					elementClick("//*[@id='dropmenudiv']/a[10]");
					Thread.sleep(1000);
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					Thread.sleep(2000);		
					selectDropDown("sipt_fieldform_fieldchangeclassvehicle_class");
					elementClick("btn_sipt_fieldform_okchangeclass");
					Thread.sleep(500);
					operationWindow = getText("titlebar");
					operationWindow = operationWindow.trim();
					errorText = getText("lbl_message");
					if (operationWindow.contains("Error")) {
						System.out.println(operationWindow+": "+ errorText);
						fail(operationWindow+": "+ errorText);
					}					
					System.out.println(operationWindow+": "+errorText);
					Thread.sleep(1000);
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
			public static void pagoRemoto() throws Exception{
				try {
					elementClick("//*[@id='dropmenudiv']/a[11]");
					Thread.sleep(1000);
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					Thread.sleep(2000);
					elementClick("sipt_fieldform_fieldrpaymentradioExempt");					
					selectDropDown("sipt_fieldform_fieldrpaymentexempt_num");
					elementClick("btn_sipt_fieldform_okrpayment");
					Thread.sleep(1000);
					operationWindow = getText("titlebar");
					operationWindow = operationWindow.trim();
					errorText = getText("lbl_message");
					if (operationWindow.contains("Error")) {
						System.out.println(operationWindow+": "+ errorText);
						fail(operationWindow+": "+ errorText);
					}					
					System.out.println(operationWindow+": "+errorText);
					Thread.sleep(1000);
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
			public static void liquidacionParcial() throws Exception{
				try {
					elementClick("//*[@id='dropmenudiv']/a[12]");
					Thread.sleep(1000);
					if (isAlertPresent()) {
						driver.switchTo().alert().accept();
					}
					Thread.sleep(2000);							
					operationWindow = getText("titlebar");
					operationWindow = operationWindow.trim();
					errorText = getText("lbl_message");
					if (operationWindow.contains("Error")) {
						System.out.println(operationWindow+": "+ errorText);
						fail(operationWindow+": "+ errorText);
					}					
					System.out.println(operationWindow+": "+errorText);
					Thread.sleep(1000);
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
			}
			

}