<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>

</head>

<body>
<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
    <div class="card">
        <h5 class="card-header">Responsive Table</h5>

        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Nombre</th>
                        <th scope="col">Usuario</th>
                        <th scope="col">Telefono</th>
                        <th scope="col">Movil</th>
                        <th scope="col">Puesto</th>
                        <th scope="col">Categorias</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${usuarios}" var="usuario">

                        <td>${usuario.nombre} ${usuario.apellido}</td>
                        <td>${usuario.usuario}</td>
                        <td>${usuario.telefono}</td>
                        <td>${usuario.movil}</td>
                        <td>${usuario.puesto}</td>
                        <td>

                            <g:each in="${usuario.categoria}" var="categoria">
                                <span class="badge badge-pill badge-info">${categoria.nombre}</span>
                            </g:each>

                        </td>

                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>