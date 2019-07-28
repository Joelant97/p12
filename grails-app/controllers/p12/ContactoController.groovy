package p12

import auth.Usuario
import grails.converters.JSON
import grails.validation.ValidationException

class ContactoController {

    ContactoService contactoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {

        [usuarios: Contacto.findAll(), categorias: Categoria.findAll(), departamentos: Departamento.findAll()]

    }

    def show() {

    }

    def create() {
        def categorias = Categoria.findAll()
        def usuario = (Usuario) getAuthenticatedUser()
        println categorias.size()
        render(view: "create", model: [categorias: categorias, usuario: usuario])
    }

    def reportes() {

        def contactosCategoriaCriteria = Contacto.createCriteria()
        def contactosCategoria = contactosCategoriaCriteria.list {
            categorias {
                projections {

                    groupProperty("nombre")
                }
                count()
            }

        }
        def contactosDepartamentoCriteria = Contacto.createCriteria()
        def contactosDepartamento = contactosDepartamentoCriteria.list {
            departamentos {
                projections {

                    groupProperty("nombre")
                }
                count()
            }
        }
        def res = [categorias: contactosCategoria, departamentos: contactosDepartamento]

        render res as JSON


    }

    def save() {
        def existe = 1, errores

        try {
            def contacto = new Contacto(params)
            if (params.categoria != null && (params.categoria as String).isNumber()) {
                def categoria = Categoria.findById(params.categoria as Integer)
                contacto.addToCategorias(categoria)
            }
            if (params.departamentos != null) {
                println "departamentos: " + params.departamentos as String
            }
            contacto.save(flush: true, failOnError: true)

        } catch (ValidationException e) {
            existe = -1
            errores = e.getErrors().getAllErrors()
        }

        def res = [valido: existe, errores: errores]
            render res as JSON
    }

    def existe() {

        def email = params.email
        def movil = params.movil

        Contacto contacto
        if (email != null && !email.equalsIgnoreCase("")){
            contacto = Contacto.findByEmail(email)
        }
        else if (movil != null && !movil.equalsIgnoreCase("")) {
            contacto = Contacto.findByMovil(movil)
        }

        if (contacto != null) {
            params.departamentos.split(",").each {it ->
                def departamento = Departamento.findById(it)
                if(departamento)
                    contacto.getDepartamentos().add(departamento)
            }


        } else {
            println "contacto nulo"
        }

        contacto.save(flush: true, failOnError: true)
        redirect(uri: '/contacto/index')
    }

    def editar(Long id) {
        def contacto = contactoService.get(id)

        render contacto as JSON
    }

    def update(Integer id, String nombre, String apellido, String telefono, String movil, String puesto, String email, String categoria, String departamento) {

        def contacto = Contacto.findById(id)
        def category =  Categoria.findById((categoria as Long))
        Set categorias = [category]

        def departament =  Departamento.findById((departamento as Long))
        Set departamentos = [departament]

        contacto.setCategorias(categorias)
        contacto.setDepartamentos(departamentos)
        contacto.setNombre(nombre)
        contacto.setApellido(apellido)
        contacto.setTelefono(telefono)
        contacto.setMovil(movil)
        contacto.setPuesto(puesto)
        contacto.setEmail(email)
        contacto.save(flush: true, failOnError: true)

        redirect(uri: '/contacto/index')

    }

    def delete(Long id) {
        def contacto = Contacto.findById(id)

        contacto.delete(flush: true, failOnError: true)
        render(view: 'index')

    }

}
