package p12

import grails.gorm.services.Service

@Service(Categoria)
interface CategoriaService {

    Categoria get(Serializable id)

    List<Categoria> list(Map args)

    Long count()

    void delete(Serializable id)

    Categoria save(Categoria categoria)

}