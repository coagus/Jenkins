@Grab('org.apache.poi:poi:3.8')
@Grab('org.apache.poi:poi-ooxml:3.8')
import org.apache.poi.ss.usermodel.*
import org.apache.poi.hssf.usermodel.*
import org.apache.poi.xssf.usermodel.*
import org.apache.poi.ss.util.*
import org.apache.poi.ss.usermodel.*
import java.io.*
import groovy.json.JsonSlurper

class Publish {
    final VALIDATE = "validate"
    final PUBLISH = "publish"

    def publishFromXlsx(path) {
        InputStream inp = new FileInputStream(path)
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
            if (cell != null && cell.getRichStringCellValue() != null && cell.getRichStringCellValue().getString().trim() != "") {
                def project = cell.getRichStringCellValue().getString()
                
                if (postProject(project, VALIDATE) && postProject(project, PUBLISH)) {
                    println " ${project} published!\n"                           
                }
            }
        }
    }

    def postProject(project, task) {
        println "${task} ${project}:"
        def result = false
        def code = 500
        def response = ""
        def jsonSlurper = new JsonSlurper()
        def post = new URL("http://100.126.0.13:7004/ecm/ecm/CatalogManagement/v2/project/${project}/${task}").openConnection()
        try {
            post.setRequestProperty("OnBehalfOf", "upadmin")
            post.setRequestMethod("POST")
            code = post.getResponseCode()
            response = post.getInputStream()?.getText()
        } catch (Exception e) {
             response = e.getMessage()
        }
        println code
        println response
        if (code == 200) {
            def jsResp = jsonSlurper.parseText(response)
            result = jsResp.status != null && jsResp.status[0] == 200
        }
        result
    }

    static void main(String[] args){
        Publish publish = new Publish()
        publish.publishFromXlsx(args[0])
    }
}