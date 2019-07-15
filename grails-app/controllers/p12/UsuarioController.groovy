package p12

import auth.Usuario
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UsuarioController {

    UsuarioService usuarioService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {



    }

    def show(Long id) {
        respond usuarioService.get(id)
    }

    def create() {

    }

    def save() {



    }

    def edit(Long id) {
        respond usuarioService.get(id)
    }

    def update(Contacto usuario) {
        if (usuario == null) {
            notFound()
            return
        }

        try {
            usuarioService.save(usuario)
        } catch (ValidationException e) {
            respond usuario.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'contacto.label', default: 'Contacto'), usuario.id])
                redirect usuario
            }
            '*' { respond usuario, [status: OK] }
        }
    }

    def iniciarSesion(String username, String password) {
        def user = Usuario.findByUsername(username)
        if (!user) {

        }
        redirect(uri: "/index")
    }




}
