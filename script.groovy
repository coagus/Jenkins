@Grab('org.apache.poi:poi:3.8')
@Grab('org.apache.poi:poi-ooxml:3.8')
import org.apache.poi.ss.usermodel.*
import org.apache.poi.hssf.usermodel.*
import org.apache.poi.xssf.usermodel.*
import org.apache.poi.ss.util.*
import org.apache.poi.ss.usermodel.*
import java.io.*

def readExcel(path) {
    InputStream inp = new FileInputStream(args[0])
    Workbook wb = WorkbookFactory.create(inp)
    Sheet sheet = wb.getSheetAt(0)

    Iterator<Row> rowIt = sheet.rowIterator()
    Row row = rowIt.next()
    for (Cell cell : row) {
        println cell.getRichStringCellValue().getString()
    }
}

def validate(project) {
    echo "valideate${project}"
    def response = httpRequest url:"http://100.126.0.13:7004/ecm/ecm/CatalogManagement/v2/project/${project}/validate", 
        httpMode: 'POST', customHeaders: [[name: 'OnBehalfOf', value: 'upadmin']]
        
    echo "status: ${response.status}"
    echo "status: ${response.content}"
    [response.status, response.content]
}

def publicar(project) {

}

def hola() {
    echo "hola, si funciona!"
}

return this