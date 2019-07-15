package auth

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)

class Usuario implements Serializable{

    transient springSecurityService

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    Date dateCreated
    Date lastUpdated

    Set<Rol> getAuthorities() {
        UsuarioRol.findAllByUsuario(this)*.rol
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (this.isDirty('password')) { //TODO: verificar en en foro.
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }


    static transients = ['springSecurityService']

    static constraints = {
        password blank: false, password: true
        username blank: false, unique: true
    }

    static mapping = {
        password column: '`password`'

    }
}
