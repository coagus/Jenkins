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
                
                println "Validate ${project}:"
                def [code, response] = postProject(project, "validate")
                if (code == '200') {
                    println "Response: ${response}"
                } else {
                    println "Error"
                }
            }            
        }
    }

    def postProject(project, task) {
        def post = new URL("http://100.126.0.13:7004/ecm/ecm/CatalogManagement/v2/project/${project}/${task}").openConnection()
        post.setRequestProperty("OnBehalfOf", "upadmin")
        post.setRequestMethod("POST")
        [post.getResponseCode(), post.getInputStream()?.getText()]
    }
}
