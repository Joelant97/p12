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

        def existe = 1, errores = ""


        try {

            def contacto = new Contacto(params)
            if (params.categoria != null && (params.categoria as String).isNumber()) {
                def categoria = Categoria.findById(params.categoria as Integer)

                contacto.addToCategorias(categoria)
            }

            if (params.departamentos != null) {
//                for (Integer id in params.departamentos){
//                    def departamento = Departamento.findById(id)
//
////                    contacto.addToDepartamentos(departamento)
//                }
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

//        println params.error

        def s = params.error as String

        def arr = s.split(",")

        def email = ""
        def movil = ""
        def entro = false

        if (arr[0].startsWith("email")){
            email = arr[0].trim()
            entro = true
        }
        else if (arr.length > 1){
            email = arr[1].trim()
            entro = true
        }

        if (arr[0].startsWith("movil")){
            movil = arr[0].trim()
        }
        else if (arr.length > 1){
            movil = arr[1].trim()
        }


        def contacto = null

        if (email != null && !email.equalsIgnoreCase("")) {

//            println "email " + email.split(":")[1].trim()
            contacto = Contacto.findByEmail(email.split(":")[1].trim())
        } else if (movil != null && !movil.equalsIgnoreCase("")) {

//            println "movil " + movil.split(":")[1].trim()
            contacto = Contacto.findByMovil(movil.split(":")[1].trim())
        }

//        println "nombre: " + contacto.nombre

        if (contacto != null){


            Integer[] arreglo  = params.list('departamentos')
            println "ids: " + params.departamentos as String
            for (Integer id in arreglo) {
                def departamento = Departamento.findById(id)

//                println "departamento nombre: " + departamento.nombre
//                d.add(departamento)
//
                contacto.addToDepartamentos(departamento)

            }

//            contacto.setDepartamentos(d)
        }
        else {
            println "contacto nulo"
        }

        contacto.save(flush: true, failOnError: true)
        redirect(uri: '/contacto/index')

    }

    def editar(Long id) {
        def contacto = contactoService.get(id)

        render contacto as JSON
    }

    def update(Integer id, String nombre, String apellido, String telefono, String movil, String puesto, String email, String categoria, String departamento)
    {

        def contacto = Contacto.findById(id)

        contacto.setNombre(nombre)
        contacto.setApellido(apellido)
        contacto.setTelefono(telefono)
        contacto.setMovil(movil)
        contacto.setPuesto(puesto)
        contacto.setEmail(email)
        contacto.setCategorias(new Set<Categoria>())   //Pasar lista String todo
        contacto.setDepartamentos(departamento)
        contacto.save(flush: true, failOnError: true)

        redirect(uri: '/contacto/index')

    }


    def delete(Long id) {
        def contacto = Contacto.findById(id)

        contacto.delete(flush: true, failOnError: true)
        render(view: 'index')

    }

}
