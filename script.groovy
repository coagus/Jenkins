import jxl.*
import jxl.write.*

def readExcel(path) {
    Workbook workbook1 = Workbook.getWorkbook(new File(path))
    Sheet sheet1 = workbook1.getSheet(0)
    Cell a1 = sheet1.getCell(0,2)
    Cell b2 = sheet1.getCell(2,2)
    Cell c2 = sheet1.getCell(2,1)
     
    println a1.getContents();
    println b2.getContents();
    println c2.getContents();  
     
    workbook1.close()
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