package p12

import auth.Usuario
import auth.UsuarioRol
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['permitAll'])
class UsuarioRolController {

    def usuarioRolService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond usuarioRolService.list(params), model:[usuarioRolCount: usuarioRolService.count()]
    }

    def show(Long id) {
        respond usuarioRolService.get(id)
    }

    def create() {
        respond new UsuarioRol(params)
    }

    def save(UsuarioRol usuarioRol) {
        if (usuarioRol == null) {
            notFound()
            return
        }

        try {
            usuarioRolService.save(usuarioRol)
        } catch (ValidationException e) {
            respond usuarioRol.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usuarioRol.label', default: 'UsuarioRol'), usuarioRol.id])
                redirect usuarioRol
            }
            '*' { respond usuarioRol, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond usuarioRolService.get(id)
    }

    def update(UsuarioRol usuarioRol) {
        if (usuarioRol == null) {
            notFound()
            return
        }

        try {
            usuarioRolService.save(usuarioRol)
        } catch (ValidationException e) {
            respond usuarioRol.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'usuarioRol.label', default: 'UsuarioRol'), usuarioRol.id])
                redirect usuarioRol
            }
            '*'{ respond usuarioRol, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        usuarioRolService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuarioRol.label', default: 'UsuarioRol'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

//    @Secured(['permitAll'])
//    def login() {
//        respond view: '/login/auth'
//    }



}
