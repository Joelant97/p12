package p12

import grails.gorm.services.Service

@Service(Contacto)
interface ContactoService {

    Contacto get(Serializable id)

    List<Contacto> list(Map args)

    Long count()

    void delete(Serializable id)

    Contacto save(Contacto contacto)

}