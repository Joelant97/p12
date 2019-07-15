<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Departamentos</title>
</head>

<body>

<div class="dashboard-finance">
    <div class="container-fluid dashboard-content">

        <div class="row">

            <div class="col-xl-12 col-xl-6 col-lg-6 col-md-12 col-md-6 col-sm-12 col-12">
                <div class="card">
                    <h5 class="card-header"> <g:message code="lista.departamentos"/> </h5>

                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th scope="col"> <g:message code="nombre"/> </th>
                                    <th scope="col" style="text-align: center;"> <g:message code="acciones"/> </th>
                                </tr>
                                </thead>
                                <tbody>
                                <g:each in="${departamentos}" var="departamento">
                                    <tr>
                                        <td>${departamento.nombre}</td>

                                        <td align="center">
                                            <button class="btn btn-rounded btn-danger" onclick="eliminar(${departamento.id})"><i
                                                    class="fa fa-minus-square"></i> <g:message code="eliminar"/> </button>
                                            <button class="btn btn-rounded btn-primary"
                                                    onclick="editar(${departamento.id})"><i
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
                <h5 class="modal-title"> <g:message code="editar.departamento"/> </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <g:form action="update" controller="departamento"  method="PUT">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombre" class="col-form-label"> <g:message code="nombre"/> </label>
                        <input id="nombre" type="text" class="form-control" name="nombre" required>
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
        request.open('DELETE', '/departamento/delete/?id='+id, true);
        request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        request.send();

    }

    function editar(id) {

        let request = new XMLHttpRequest();
        request.open('GET', '/departamento/editar/?id=' + id, true);

        request.onload = function () {
            if (request.status >= 200 && request.status < 400) {
                // Success!
                let resp = JSON.parse(request.responseText);

                // console.log(resp);
                document.getElementById('id').value = resp.id;
                document.getElementById('nombre').value = resp.nombre;

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