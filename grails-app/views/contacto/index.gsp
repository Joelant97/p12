<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>

</head>

<body>

<div class="dashboard-finance">
    <div class="container-fluid dashboard-content">

        <div class="row">

            <div class="col-xl-12 col-xl-6 col-lg-6 col-md-12 col-md-6 col-sm-12 col-12">
                <div class="card">
                    <h5 class="card-header"> <g:message code="lista.contactos"/> </h5>

                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th scope="col"> <g:message code="nombre"/> </th>
                                    %{--<th scope="col">Usuario</th>--}%
                                    <th scope="col"> <g:message code="telefono"/> </th>
                                    <th scope="col"> <g:message code="movil"/> </th>
                                    <th scope="col"> <g:message code="puesto"/> </th>
                                    <th scope="col"> <g:message code="correo"/> </th>
                                    <th scope="col"> <g:message code="categorias"/> </th>
                                    <th scope="col"> <g:message code="departamentos"/> </th>
                                    <th scope="col" style="text-align: center;"> <g:message code="acciones"/> </th>
                                </tr>
                                </thead>
                                <tbody>
                                <g:each in="${usuarios}" var="contacto">
                                    <tr>
                                        <td>${contacto.nombre} ${contacto.apellido}</td>
                                        %{--<td>${contacto.username}</td>--}%
                                        <td>${contacto.telefono}</td>
                                        <td>${contacto.movil}</td>
                                        <td>${contacto.puesto}</td>
                                        <td>${contacto.email}</td>
                                        <td>

                                            <g:each in="${contacto.categorias}" var="categoria">
                                                <span class="badge badge-pill badge-info">${categoria.nombre}</span>
                                            </g:each>

                                        </td>

                                        <td>

                                            <g:each in="${contacto.departamentos}" var="departamento">
                                                <span class="badge badge-pill badge-brand">${departamento.nombre}</span>
                                            </g:each>

                                        </td>

                                        <td align="center">
                                            <button class="btn btn-rounded btn-danger" onclick="eliminar(${contacto.id})"><i
                                                    class="fa fa-minus-square"></i> <g:message code="eliminar"/> </button>
                                            <button class="btn btn-rounded btn-primary"><i
                                                    class="fa fa-pencil-alt"></i> <g:message code="editar"/> </button>

                                        </td>

                                    </tr>

                                </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>

    function eliminar(id) {


        let request = new XMLHttpRequest();
        request.open('DELETE', '/contacto/delete/?id='+id, true);
        request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        request.send();

    }

</script>
</body>
</html>