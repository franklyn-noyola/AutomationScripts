package Tajikistan.Tajikistan;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import static SettingFiles.Generic_Settingsfields_File.*;
import static SettingFiles.Tajikistan_Settingsfields_File.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static Tajikistan.Tajikistan.BOHost_VerTransacciones.*;




public class verConfirmación_TransitosSubidos{
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
				tearDown();
		}
		

		
		@Test		
		public void verConfirmacion_Transitos() throws Exception{
				configurationSection("Host",testNameSelected,testNameSelected);				
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				DateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();			
				dateverTransacciones = dateF.format(date);
				transSearch = dateFormat.format(date);
				String connectionUrlPlaza = "jdbc:sqlserver://172.18.130.46:1433;DataBaseName=TAJIKISTAN_QA_TOLLPLAZA"; //+ "user=sa; password=lediscet";//" + "user=SENEGAL_QA_TOLLHOST; password=USRTOLLHOST";
				String connectionUrlHost = "jdbc:sqlserver://172.18.130.46:1433;DataBaseName=TAJIKISTAN_QA_TOLLHOST"; //+ "user=sa; password=lediscet";//" + "user=SENEGAL_QA_TOLLHOST; password=USRTOLLHOST";
			    stmt = null;
			    rs = null;
			    try {
			    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			    		Connection conn = DriverManager.getConnection(connectionUrlPlaza, "Fgarcia", "Demo.1234");
			    		stmt = conn.createStatement();
			    		queryString = "select passagetime, transactionid from dbo.atransaction where passagetime like '"+transSearch+"%' ORDER BY passagetime ASC";
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
			    			System.out.println("No transit has been uploaded to Plaza today: "+dateverTransacciones);
			    			fail("No transit has been uploaded to Plaza today: "+dateverTransacciones);
			    		}else{
			    			System.out.println("En Plaza han subido hoy: "+transactionsPIds.size()/2);
			    			Connection conn2 = DriverManager.getConnection(connectionUrlHost, "Fgarcia", "Demo.1234");
			    			stmt = conn2.createStatement();
				    		queryString = "select passagetime, transactionid from dbo.atransaction where passagetime like '"+transSearch+"%' ORDER BY passagetime ASC";
				    		rs = stmt.executeQuery(queryString);
				    		while (rs.next()) {
				    			for (i = 0; i < transactions.length;i++){
				    				transactions[0]= rs.getString("passagetime");
				    				transactions[1] = rs.getString("transactionid");
				    				transactionsHIds.add(transactions[i]);
				    			}				    			
				    		}
				    		if (transactions[0]==null&&transactions[1]==null){
				    			System.out.println("No transit has been uploaded to Host today: "+dateverTransacciones);
				    			fail("No transit has been uploaded to Host today: "+dateverTransacciones);
				    		}else{
				    			Hour1 = transactionsHIds.get(0).substring(8,10);
								Min1 = transactionsHIds.get(0).substring(10,12);
								Sec1 = transactionsHIds.get(0).substring(12,14);
				    			System.out.println("There are: "+transactionsPIds.size()/2+" transactions in Host Today");
				    			Thread.sleep(1000);
				    			verTransacciones();
				    			Thread.sleep(1000);
				    			WebElement tablResult = driver.findElement(By.id("ctl00_ContentZone_tbl_logs"));
				    			List<WebElement> transResult = tablResult.findElements(By.tagName("tr"));				    			
				    			if (transResult.size()<3){
				    				System.out.println("No Transactions in BackOffice Web Today");
					    			fail("No Transactions in BackOffice Web Today");
					    			return;
				    			}
				    			String elementsFound = getText("ctl00_ContentZone_tablePager_LblCounter");				    			
				    			int elementBg=Integer.parseInt(elementsFound.substring(elementsFound.indexOf("of ")+3));				    			
				    			if (transResult.size()>elementBg){			    				
				    				String transRes = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_tbl_logs']/tbody/tr["+transResult.size()+"]/td[1]/a")).getText();				    				
				    				if (transRes.equals(transactionsHIds.get(1))){
				    					System.out.println("Threa is/are "+elementBg+" transactions and the last transaction: "+transactionsHIds.get(1)+" has been uploaded today successfully "+dateverTransacciones+" "+Hour1+":"+Min1+":"+Sec1);
				    					return;
				    				}else{
				    					System.out.println("No last transit has been  uploaded in BackOffice today");
						    			fail("No last transit has been  uploaded in BackOffice today");
						    			return;
				    				}
				    			}else{
				    				elementClick("ctl00_ContentZone_tablePager_BtnLast");
				    				tablResult = driver.findElement(By.id("ctl00_ContentZone_tbl_logs"));
					    			transResult = tablResult.findElements(By.tagName("tr"));
					    			String transRes = getText("//*[@id='ctl00_ContentZone_tbl_logs']/tbody/tr["+transResult.size()+"]/td[1]/a");
				    				if (transRes.equals(transactionsHIds.get(1))){
				    					System.out.println("There is/are "+elementsFound.substring(elementBg)+" transactions and the last one: "+transactionsHIds.get(1)+" has been uploaded today successfully "+dateverTransacciones+" "+Hour1+":"+Min1+":"+Sec1);
				    					return;
				    				}else{
				    					System.out.println("No last transit has been uploaded today in BackOffice");
						    			fail("No last transit has been uploaded today in BackOffice");
						    			return;
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
