package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{

	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException 
	{
		String path=".\\testData\\Opencart_LoginData.xlsx";//taking xl file from testData folder
		
		ExcelUtility xlutil=new ExcelUtility(path);//creating an object of xlutility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcells=xlutil.getCellCount("Sheet1",1);
		
		String logindata[][]=new String[totalrows][totalcells];//created for two dimension array which can store xlfile data
		
		for(int i=1;i<=totalrows;i++) {//1    read the data from xl storing into two dimension array
			for(int j=0;j<totalcells;j++) {//0  i is row and j is column
				logindata[i=1][j]=xlutil.getCellData("Sheet1", i, j);//1,0
			}
		}
		
		return logindata;//returning two dimension array
	}
	
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
}
