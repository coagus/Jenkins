def validate(project) {
    echo "valideate${project}"
    def response = httpRequest url:"http://100.126.0.13:7004/ecm/ecm/CatalogManagement/v2/project/${project}/validate", 
        httpMode: 'POST', customHeaders: [[name: 'OnBehalfOf', value: 'upadmin']]
        
    [response.status, response.content]
}

def publicar(project) {

}

def hola() {
    echo "hola, si funciona!"
}

return this