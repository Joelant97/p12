package p12

import grails.converters.JSON
import grails.validation.ValidationException

class CategoriaController {

    CategoriaService categoriaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        [categorias: Categoria.findAll()]
    }

    def show(Long id) {
        respond categoriaService.get(id)
    }

    def create() {

    }

    def save() {

        try {

            def categoria = new Categoria(params)
            categoria.save(flush: true, failOnError: true)

        } catch (ValidationException e) {
            println e
        }

        redirect(uri: '/')


    }

    def editar(Long id) {
        def categoria = categoriaService.get(id)

        render categoria as JSON
    }

    def update(Integer id, String nombre) {


        def categoria = Categoria.findById(id)

        categoria.setNombre(nombre)
        categoria.save(flush: true, failOnError: true)

        redirect(uri: '/categoria/index')

    }

    def delete(Long id) {
        println "id: " + id
        def categoria = Categoria.findById(id)
        println(""+categoria)
        println "nombre " +categoria.getNombre()
        //def u = Contacto.findAllByCategoriasInList([categoria])
        def u  = Contacto.where {categoria.id in [categoria.id]}
//        def u  = Contacto.where {categorias in [categoria]}
//        def u = Contacto.findAll("from Contacto as u where u.categorias in (:categorias)", [categorias: [categoria]])

        println u.size()
        def usuarios = ((u) as List<Contacto>)

        usuarios.each {
            it.removeFromCategorias(categoria)
            it.save(flush: true, failOnError: true)
        }

       categoria.delete(flush: true, failOnError: true)

        redirect action: 'index'
    }


}
