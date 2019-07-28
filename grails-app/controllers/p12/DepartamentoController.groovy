package p12

import auth.Usuario
import grails.converters.JSON
import grails.validation.ValidationException

class DepartamentoController {

    DepartamentoService departamentoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {

        [departamentos: Departamento.findAll()]
    }

    def show(Long id) {
        respond departamentoService.get(id)
    }

    def create() {
        respond new Departamento(params)
    }

    def todos(){
        def departamentos = Departamento.findAll()
        render departamentos as JSON
    }

    def save() {
        def usuario = (Usuario) getAuthenticatedUser()
        try {

            def departamento = new Departamento(params)
            departamento.setUsuario(usuario)
            departamento.save(flush: true, failOnError: true)

        } catch (ValidationException e) {
            println e
        }
        redirect(uri: '/')
    }

    def editar(Long id) {
        def departamento = departamentoService.get(id)
        render departamento as JSON
    }

    def update(Integer id, String nombre) {
        def departamento = Departamento.findById(id)

        departamento.setNombre(nombre)
        departamento.save(flush: true, failOnError: true)

        redirect(uri: '/departamento/index')
    }

    def delete(Long id) {
        println "id: " + id
        def departamento = Departamento.findById(id)
        println(""+departamento)
        println "nombre " +departamento.getNombre()
        //def u = Contacto.findAllByCategoriasInList([categoria])
        def u  = Contacto.where {departamento.id in [departamento.id]}
//        def u  = Contacto.where {categorias in [categoria]}
//        def u = Contacto.findAll("from Contacto as u where u.categorias in (:categorias)", [categorias: [categoria]])

        println u.size()
        def usuarios = ((u) as List<Contacto>)

        usuarios.each {
            it.removeFromDepartamentos(departamento)
            it.save(flush: true, failOnError: true)
        }

        departamento.delete(flush: true, failOnError: true)

        redirect action: 'index'
    }


}