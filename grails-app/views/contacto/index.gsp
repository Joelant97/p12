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
                                    <th scope="col"> <g:message code="departamentos"/> </th>
                                    <th scope="col"> <g:message code="categorias"/> </th>

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
                                            <ul>
                                                <li>
                                                    <g:each in="${contacto.departamentos}" var="departamento">
                                                         ${departamento.nombre}
                                                    </g:each>
                                                </li>
                                            </ul>
                                        </td>
                                        <td>
                                            <ul>
                                                <li>
                                                    <g:each in="${contacto.categorias}" var="categoria">
                                                        ${categoria.nombre}
                                                    </g:each>
                                                </li>
                                            </ul>
                                        </td>



                                        <td align="center">
                                            <button class="btn btn-rounded btn-danger" onclick="eliminar(${contacto.id})"><i
                                                    class="fa fa-minus-square"></i> <g:message code="eliminar"/> </button>
                                            <button class="btn btn-rounded btn-primary"
                                                    onclick="editar(${contacto.id})"><i
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

<div class="modal" id="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"> <g:message code="editar.contacto"/> </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <g:form action="update" controller="contacto"  method="PUT">
                <div class="modal-body">
                    <div class="form-group">
                        <input hidden name="usuario" value="">
                        <label for="nombre" class="col-form-label"> <g:message code="nombre"/> </label>
                        <input id="nombre" type="text" class="form-control" name="nombre" required>
                        <label for="apellido" class="col-form-label"> <g:message code="apellido"/> </label>
                        <input id="apellido" type="text" class="form-control" name="apellido" required>
                        <label for="telefono" class="col-form-label"> <g:message code="telefono"/> </label>
                        <input id="telefono" type="text" class="form-control" name="telefono" required>
                        <label for="movil" class="col-form-label"> <g:message code="movil"/> </label>
                        <input id="movil" type="text" class="form-control" name="movil" required>
                        <label for="puesto" class="col-form-label"> <g:message code="puesto"/> </label>
                        <input id="puesto" type="text" class="form-control" name="puesto" required>
                        <label for="email" class="col-form-label"> <g:message code="email"/> </label>
                        <input id="email" type="text" class="form-control" name="email" required>
                        <label for="departamentos" class="col-form-label"> <g:message code="departamentos"/> </label>
                        <select id="departamentos" name="departamento">
                            <g:each in="${departamentos}" var="departamento">
                                <option value="${departamento.id}">${departamento.nombre}</option>
                            </g:each>
                        </select>
                        <label for="categorias" class="col-form-label"> <g:message code="categorias"/> </label>
                        <select id="categorias" name="categoria">
                            <g:each in="${categorias}" var="categoria">
                                <option value="${categoria.id}">${categoria.nombre}</option>
                            </g:each>
                        </select>

                        %{--<label for="categoria" class="col-form-label"> <g:message code="categoria"/> </label>--}%
                        %{--<input id="categoria" type="text" class="form-control" name="categoria" required>--}%
                        <input hidden id="id" name="id">
                    </div>
                </div>


                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"> <g:message code="cerrar"/> </button>
                    <button type="submit" class="btn btn-primary"> <g:message code="guardar"/> </button>
                </div>
            </g:form>
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


    function editar(id) {

        let request = new XMLHttpRequest();
        request.open('GET', '/contacto/editar/?id=' + id, true);

        request.onload = function () {
            if (request.status >= 200 && request.status < 400) {
                // Success!
                let resp = JSON.parse(request.responseText);

                // console.log(resp);
                document.getElementById('id').value = resp.id;
                document.getElementById('nombre').value = resp.nombre;
                document.getElementById('apellido').value = resp.apellido;
                document.getElementById('telefono').value = resp.telefono;
                document.getElementById('movil').value = resp.movil;
                document.getElementById('puesto').value = resp.puesto;
                document.getElementById('email').value = resp.email;
                // document.getElementById('departamentos').value = resp.departamentos.nombre;
                // document.getElementById('categorias').value = resp.categorias.nombre;



            //     let categoriaSelect = document.getElementById('categorias');
            //     var value = 0;
            //     resp.categorias.forEach(option =>
            //     categoriaSelect.add(
            //         new Option(option.nombre, option.id, option.selected)
            //     )
            // );


                $('#modal').modal("toggle");


            } else {
                // We reached our target server, but it returned an error

            }
        };

        request.onerror = function () {
            // There was a connection error of some sort
        };

        request.send();

    }

</script>
</body>
</html>