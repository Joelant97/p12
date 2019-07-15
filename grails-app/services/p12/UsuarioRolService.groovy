package p12

import auth.UsuarioRol
import grails.gorm.services.Service

@Service(UsuarioRol)
interface UsuarioRolService {

    UsuarioRol get(Serializable id)

    List<UsuarioRol> list(Map args)

    Long count()

    void delete(Serializable id)

    UsuarioRol save(UsuarioRol usuarioRol)

}