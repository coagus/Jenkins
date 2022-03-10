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

        // Header
        Row row = rowIt.next()
        Cell cell = row.getCell(0)

        // Read the project list
        while(rowIt.hasNext()) {
            row = rowIt.next()
            cell = row.getCell(0)
            if (cell != null && cell.getRichStringCellValue() != null) {
                def project = cell.getRichStringCellValue().getString()

                // Validate
                def response = httpRequest url:"http://100.126.0.13:7004/ecm/ecm/CatalogManagement/v2/project/${project}/validate", 
                    httpMode: 'POST', customHeaders: [[name: 'OnBehalfOf', value: 'upadmin']]
                
                echo "status: ${response.status}"
                echo "status: ${response.content}"
            }            
        }
    }
}
