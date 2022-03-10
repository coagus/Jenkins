@Grab('org.apache.poi:poi:3.8')
@Grab('org.apache.poi:poi-ooxml:3.8')
import org.apache.poi.ss.usermodel.*
import org.apache.poi.hssf.usermodel.*
import org.apache.poi.xssf.usermodel.*
import org.apache.poi.ss.util.*
import org.apache.poi.ss.usermodel.*
import java.io.*

class Publish {
    static void main(String[] args){
        InputStream inp = new FileInputStream(args[0])
        Workbook wb = WorkbookFactory.create(inp)
        Sheet sheet = wb.getSheetAt(0)
        Iterator<Row> rowIt = sheet.rowIterator()

        while(rowIt.hasNext()) {
            Row row = rowIt.next()
            Cell cell = row.getCell(0)
            if (cell != null && cell.getRichStringCellValue() != null) {
                println cell.getRichStringCellValue().getString()
            }            
        }
    }
}
