def validate(project) {
    def response = httpRequest url:"http://100.126.0.13:7004/ecm/ecm/CatalogManagement/v2/project/${project}/validate", 
        httpMode: 'POST', customHeaders: [[name: 'OnBehalfOf', value: 'upadmin']]
        
    [response.status, response.content]
}

def publicar(project) {

}