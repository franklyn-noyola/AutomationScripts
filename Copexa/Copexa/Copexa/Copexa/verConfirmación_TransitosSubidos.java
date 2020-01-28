package Copexa.Copexa;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;

import static SettingFiles.Copexa_Settingsfields_File.*;
import static SettingFiles.Generic_Settingsfields_File.*;
import static Copexa.Copexa.BOHost_VerTransacciones.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


//import static SettingFiles.Generic_Settingsfields_File.tearDown;




public class verConfirmación_TransitosSubidos {
		private static Statement stmt;
		private static String transSearch;
		private static ResultSet rs;
		public static int i;
		private static String queryString;
		private static ArrayList<String> transactionsHIds = new ArrayList<String>();
		private static ArrayList<String> transactionsPIds = new ArrayList<String>();	
		private static String Hour1;
		private static String Min1;
		private static String Sec1;
		private String testNameSelected = this.getClass().getSimpleName(); 
 

		@Before
		public void setup() throws Exception{
				setUp();
    			
    			
		}
		
		@After 
		public void teardown() throws Exception{
				teardown();
		}
		
		
		@Test		
			public void verConfirmacion_Transitos() throws Exception{
				configurationSection("Host", testNameSelected, testNameSelected);				
				//borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\conexion_BBDDSenac\\attachments\\");
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				DateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();			
				dateverTransacciones = dateF.format(date);
				transSearch = dateFormat.format(date);
				String connectionUrlPlaza = "jdbc:sqlserver://172.18.130.46:1433;DataBaseName=COPEXA_QA_TOLLPLAZA"; //+ "user=sa; password=lediscet";//" + "user=SENEGAL_QA_TOLLHOST; password=USRTOLLHOST";
				String connectionUrlHost = "jdbc:sqlserver://172.18.130.46:1433;DataBaseName=COPEXA_QA_TOLLHOST"; //+ "user=sa; password=lediscet";//" + "user=SENEGAL_QA_TOLLHOST; password=USRTOLLHOST";
			    stmt = null;
			    rs = null;
			    try {
			    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			    		Connection conn = DriverManager.getConnection(connectionUrlPlaza, "Fgarcia", "Demo.1234");
			    		stmt = conn.createStatement();
			    		queryString = "select passagetime, transactionid from dbo.atransaction where passagetime like '"+transSearch+"%' ORDER BY passagetime DESC";
			    		rs = stmt.executeQuery(queryString);
			    		String [] transactions = new String[2]; 			   
			    		while (rs.next()) {
			    			for (i = 0; i < transactions.length;i++){
			    				transactions[0]= rs.getString("passagetime");
			    				transactions[1] = rs.getString("transactionid");
			    				transactionsPIds.add(transactions[i]);
			    			}
			    		}
			    		if (transactions[0]==null&&transactions[1]==null){
			    			System.err.println("No han subido tránsitos a Plaza con fecha de hoy "+dateverTransacciones);
			    			fail("No han subido tránsitos a Plaza con fecha de hoy "+dateverTransacciones);
			    		}else{
			    			System.out.println("En Plaza han subido hoy: "+transactionsPIds.size()/2);
			    			Connection conn2 = DriverManager.getConnection(connectionUrlHost, "Fgarcia", "Demo.1234");
			    			stmt = conn2.createStatement();
				    		queryString = "select passagetime, transactionid from dbo.atransaction where passagetime like '"+transSearch+"%' ORDER BY passagetime DESC";
				    		rs = stmt.executeQuery(queryString);
				    		while (rs.next()) {
				    			for (i = 0; i < transactions.length;i++){
				    				transactions[0]= rs.getString("passagetime");
				    				transactions[1] = rs.getString("transactionid");
				    				transactionsHIds.add(transactions[i]);
				    			}				    			
				    		}
				    		if (transactions[0]==null&&transactions[1]==null){
				    			System.err.println("No han subido tránsitos a Host con fecha de hoy "+dateverTransacciones);
				    			fail("No han subido tránsitos a Host con fecha de hoy "+dateverTransacciones);
				    		}else{
				    			Hour1 = transactionsHIds.get(0).substring(8,10);
								Min1 = transactionsHIds.get(0).substring(10,12);
								Sec1 = transactionsHIds.get(0).substring(12,14);
				    			System.out.println("En Host han subido hoy: "+transactionsPIds.size()/2);
				    			Thread.sleep(1000);
				    			verTransacciones();
				    			Thread.sleep(1000);
				    			String elementsFound = getText("ctl00_ContentZone_tablePager_LblCounter");
				    			int recordCount = Integer.parseInt(elementsFound.substring(elementsFound.indexOf("de ")+3));				    			
				    			if (recordCount==0){
				    				System.err.println("No hay Transacciones en el BackOffice Web con fecha de hoy");
					    			fail("No hay Transacciones en el BackOffice Web con fecha de hoy");
					    			return;
				    			}else {
				    				for (int i=1;i<=recordCount;i++) {
				    					WebElement tablResult = driver.findElement(By.id("ctl00_ContentZone_tbl_logs"));
						    			List<WebElement> transResult = tablResult.findElements(By.tagName("tr"));
						    			int a = i+2;
						    			String transRes = getText("//*[@id='ctl00_ContentZone_tbl_logs']/tbody/tr["+a+"]/td[1]/a");				    				
						    			if (transRes.equals(transactionsHIds.get(1))){
						    				System.out.println("Hay "+recordCount+" transacciones y el último tránsito: "+transactionsHIds.get(1)+" ha subido satisfactoriamente el dia de hoy "+dateverTransacciones+" "+Hour1+":"+Min1+":"+Sec1);
						    				return;
						    			}
						    			if (i==recordCount) {
						    				System.err.println("No se ha subido el último tránsito a BackOffice Web desde HostManage de hoy");
							    			fail("No se ha subido el último tránsito a BackOffice Web HostManage con fecha de hoy");
							    			return;
						    			}
						    			if (i>transResult.size()-2 && driver.findElement(By.id("ctl00_ContentZone_tablePager_BtnNext")).isEnabled()) {
						    				elementClick("ctl00_ContentZone_tablePager_BtnNext");
						    			}
						    										    			
				    				}
				    			}
				    		}
				    	}
										    	
				}catch(Exception e){
					e.printStackTrace();
					System.err.println(e.getMessage());
					fail(e.getMessage());
					throw(e);
				}
		}

	      	
}