package Utility;

	import org.apache.poi.hssf.usermodel.HSSFDateUtil;
	import org.apache.poi.ss.usermodel.CellType;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	 
	import java.io.FileInputStream;
	import java.text.DateFormat;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	 
	public class ExcelUtils
	{
	    public FileInputStream fis = null;
	    public XSSFWorkbook workbook = null;
	    public XSSFSheet sheet = null;
	    public XSSFRow row = null;
	    public XSSFCell cell = null;
	    public static DataFormatter formatter = new DataFormatter();

	 
	    public ExcelUtils(String xlFilePath) throws Exception
	    {
	        fis = new FileInputStream(xlFilePath);
	        workbook = new XSSFWorkbook(fis);
	        fis.close();
	    }
	 
//	    @SuppressWarnings("deprecation")
		public String getCellData(String sheetName,int rowNum,int colNum)
	    {
	        try
	        {
	        	sheet = workbook.getSheet(sheetName);
	            row = sheet.getRow(rowNum);
	            cell = row.getCell(colNum);
	            if(cell.getCellType() == CellType.STRING)
	                return cell.getStringCellValue();
	            else if(cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA)
	            {
	                String cellValue  = String.valueOf(cell.getNumericCellValue()).trim();          

	                String value = formatter.formatCellValue(cell);
	                cellValue = value.trim();
	                
//	                cellValue.trim();
	                if (HSSFDateUtil.isCellDateFormatted(cell))
	                {
	                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
	                    Date date = cell.getDateCellValue();
	                    cellValue = df.format(date);
	                }
	                return cellValue;
	            }else if(cell.getCellType() == CellType.BLANK)
	                return "";
	            else
	                return String.valueOf(cell.getBooleanCellValue());

	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            return "row "+rowNum+" does not exist  in Excel";
	        }
	    }
}

