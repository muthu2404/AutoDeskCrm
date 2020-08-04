package genericLib;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * 
 * @author Muthu Manick
 *
 */
public class FileLib {
	
	/**
	 * 
	 * @param key
	 * @return value
	 * @throws IOException
	 * This method is used to fetch the data from Properties File
	 */
	public String getDataFromPropery(String key) throws IOException
	{
		/*create a java representation of file*/
		FileInputStream fis = new FileInputStream("./resourse/commondata.properties");
		/* create prop obj and load file*/
		Properties pobj = new Properties();
		pobj.load(fis);
		/*fetch the data*/
		String value = pobj.getProperty(key);
		
		return value;
		
		
	}
	
	/**
	 * 
	 * @param sheetname
	 * @param rownum
	 * @param cellnum
	 * @return value
	 * This method is used to fetch the data from Excel File
	 */
	public String getDataFromExcel(String sheetname,int rownum,int cellnum) throws EncryptedDocumentException, IOException
	{
		/*create a java representation of file*/
		FileInputStream fis = new FileInputStream("C:\\Users\\Muthu Manick\\git\\SDET_\\SDET\\src\\test\\resources\\testcaseData.xlsx");
		/* create workbook*/
		Workbook book = WorkbookFactory.create(fis);
		/*get sheet */
		Sheet sheet = book.getSheet(sheetname);
		/* get row*/
		Row row = sheet.getRow(rownum);
		/*get cell */
		Cell cell = row.getCell(cellnum);
		/* get cellvalue*/
		String value = cell.getStringCellValue();
		book.close();
		return value;
		
	}
}
