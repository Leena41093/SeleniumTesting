package Utility;

	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.text.DateFormat;
	import java.text.SimpleDateFormat;
	import java.time.LocalDateTime;
	import java.util.Date;
	import org.apache.commons.compress.archivers.dump.InvalidFormatException;
    import org.apache.commons.math3.analysis.function.Constant;
    import org.apache.poi.hssf.usermodel.HSSFDateUtil;
	import org.apache.poi.ss.usermodel.CellType;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.testng.annotations.DataProvider;

//import com.cleverground.qa.util.TestUtil;


	public class TestUtils
	{
		public static long PAGE_LOAD_TIMEOUT= 30L;

		public static long IMPLICIT_WAIT = 100;
		
	    public static DataFormatter formatter = new DataFormatter();

		static Workbook book;
		
		public FileInputStream fis = null;
		
		public XSSFWorkbook workbook = null;
		
		public XSSFSheet sheet = null;
		
		public XSSFRow row = null;
		
		public XSSFCell cell = null;
		 
	 

		public static String TESTDATA_SHEET_PATH = "/home/buzzybrains/my_workspace/VopaAPITestFramework/src/main/java/TestData/testData.xlsx";
		
		public TestUtils() {
//			
			}

		@DataProvider
		public static Object[][] readExcelFile(String sheetName) throws InvalidFormatException, IOException {
		    FileInputStream fis = new FileInputStream(TESTDATA_SHEET_PATH);
		    XSSFWorkbook wb = new XSSFWorkbook(fis);
		    XSSFSheet sh = wb.getSheet(sheetName);

//		    System.out.println(sh.getPhysicalNumberOfRows());
//		    System.out.println(sh.getRow(0).getPhysicalNumberOfCells());
		    int RowNum = sh.getPhysicalNumberOfRows();
		    int ColNum = sh.getRow(0).getPhysicalNumberOfCells();

		    String[][] xlData = new String[RowNum-1][ColNum];

		    for (int i = 0; i < RowNum - 1; i++) 
		    {
		        XSSFRow row = sh.getRow(i + 1);
		        for (int j = 0; j < ColNum; j++) 
		        {
		            if (row == null)
		                xlData[i][j] = "";
		            else {
		                XSSFCell cell = row.getCell(j);                 
		                if (cell == null)
		                    xlData[i][j] = ""; 
		                else {
		                    String value = formatter.formatCellValue(cell);
		                    xlData[i][j] = value.trim();                        
		                }
		            }
		        }
		    }       
		    return xlData;
		}
	} 


