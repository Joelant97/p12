<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="layout" content="main"/>
    <title>Inicio</title>
</head>

<body>

<div class="dashboard-influence">
    <div class="container-fluid dashboard-content">
        <!-- ============================================================== -->
        <!-- pageheader  -->
        <!-- ============================================================== -->
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                <div class="page-header">
                    <h3 class="mb-2"> <g:message code="inicio"/> </h3>
                </div>
            </div>
        </div>

        <div class="row">

            <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 col-12">
                <div class="card border-3 border-top border-top-primary">
                    <a href="/contacto">
                        <div class="card-body">
                            <h5 class="text-muted"><g:message code="contactos"/></h5>

                            <div class="metric-value d-inline-block">
                                <h1 class="mb-1">${contactos}</h1>
                            </div>
                            %{--<div class="metric-label d-inline-block float-right text-success font-weight-bold">--}%
                            %{--<span class="icon-circle-small icon-box-xs text-success bg-success-light"><i class="fa fa-fw fa-arrow-up"></i></span><span class="ml-1">5.86%</span>--}%
                            %{--</div>--}%
                        </div>
                    </a>
                </div>
            </div>

            <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 col-12">
                <div class="card border-3 border-top border-top-primary">
                    <a href="/departamento">
                        <div class="card-body">
                            <h5 class="text-muted"><g:message code="departamentos"/></h5>

                            <div class="metric-value d-inline-block">
                                <h1 class="mb-1">${departamentos}</h1>
                            </div>
                            %{--<div class="metric-label d-inline-block float-right text-success font-weight-bold">--}%
                            %{--<span class="icon-circle-small icon-box-xs text-success bg-success-light"><i class="fa fa-fw fa-arrow-up"></i></span><span class="ml-1">5.86%</span>--}%
                            %{--</div>--}%
                        </div>
                    </a>
                </div>
            </div>


            <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 col-12">
                <div class="card border-3 border-top border-top-primary">
                    <a href="/categoria">
                        <div class="card-body">
                            <h5 class="text-muted"><g:message code="categorias"/></h5>

                            <div class="metric-value d-inline-block">
                                <h1 class="mb-1">${categorias}</h1>
                            </div>
                            %{--<div class="metric-label d-inline-block float-right text-success font-weight-bold">--}%
                            %{--<span class="icon-circle-small icon-box-xs text-success bg-success-light"><i class="fa fa-fw fa-arrow-up"></i></span><span class="ml-1">5.86%</span>--}%
                            %{--</div>--}%
                        </div>
                    </a>
                </div>
            </div>

        </div>

    </div>
</div>

<script>

</script>
</body>
</html>