@Grab('org.apache.poi:poi:3.8')
@Grab('org.apache.poi:poi-ooxml:3.8')
import org.apache.poi.ss.usermodel.*
import org.apache.poi.hssf.usermodel.*
import org.apache.poi.xssf.usermodel.*
import org.apache.poi.ss.util.*
import org.apache.poi.ss.usermodel.*
import java.io.*

class LoadExcel {
    static void main(String[] args){
        println "test file " + args[0]

        InputStream inp = new FileInputStream(args[0])
        Workbook wb = WorkbookFactory.create(inp)
        Sheet sheet = wb.getSheetAt(0)
    }
}
