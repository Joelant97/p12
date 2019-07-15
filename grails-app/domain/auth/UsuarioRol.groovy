package auth

import grails.gorm.DetachedCriteria
import groovy.transform.ToString
import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class UsuarioRol implements Serializable {

    Usuario usuario
    Rol rol

    Date dateCreated
    Date lastUpdated

    @Override
    boolean equals(other) {
        if (other instanceof UsuarioRol) {
            other.usuarioId == usuario?.id && other.rol.id == rol?.id
        }
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (usuario) builder.append(usuario.id)
        if (rol) builder.append(rol.id)
        builder.toHashCode()
    }

    static UsuarioRol get(long usuarioId, long rolesId) {
        criteriaFor(usuarioId, rolesId).get()
    }

    static boolean exists(long usuarioId, long rolesId) {
        criteriaFor(usuarioId, rolesId).count()
    }

    private static DetachedCriteria criteriaFor(long usuarioId, long rolesId) {
        where {
            usuario == Usuario.load(usuarioId) &&
                    rol == Rol.load(rolesId)
        }
    }

    static UsuarioRol create(Usuario usuario, Rol roles) {
        def instance = new UsuarioRol(usuario: usuario, rol: roles)
        instance.save()
        instance
    }

    static boolean remove(Usuario u, Rol r) {
        if (u != null && r != null) {
            where { usuario == u && rol == r }.deleteAll()
        }
    }

    static int removeAll(Usuario u) {
        u == null ? 0 : where { usuario == u }.deleteAll()
    }

    static int removeAll(Rol r) {
        r == null ? 0 : where { rol == r }.deleteAll()
    }

    static constraints = {

    }

    static mapping = {
        id composite: ['usuario', 'rol']
        version false
    }
}
