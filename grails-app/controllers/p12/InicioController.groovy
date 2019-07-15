package p12

import grails.plugin.springsecurity.annotation.Secured

class InicioController {

    def springSecurityService

    @Secured("ROLE_ADMIN")
    def index() {

        def usuario = springSecurityService.currentUser


        [contactos: Contacto.count(), departamentos: Departamento.count(), categorias : Categoria.count(), usuario: usuario]
    }
}
