package p12

class Contacto {

    boolean enabled = true

    String email
    String nombre
    String apellido
    String telefono
    String movil
    String puesto
    String direccion

    Date dateCreated
    Date lastUpdated

    static hasMany = [departamentos: Departamento, categorias: Categoria]

    static constraints = {
        email(email: true, unique: true, blank: false)
        movil blank: false, unique: true
        telefono blank: true
        categorias blank: true
    }


}
