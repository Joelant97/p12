<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Graficos</title>

    <asset:javascript src="echarts.min.js"/>
</head>

<body>

<div class="container-fluid dashboard-content">

    <div class="row">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12  align-items-stretch">
            <div class="card">
                <h5 class="card-header"> <g:message code="contactos.categoria"/> </h5>

                <div class="card-body">
                    <div id="categorias" class="d-flex align-items-md-stretch" style="width:100%; height:200%;"></div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12  align-items-stretch">
            <div class="card">
                <h5 class="card-header"> <g:message code="contactos.departamento"/> </h5>
                <div class="card-body">
                    <div id="departamentos" class="d-flex align-items-stretch" style="width:100%; height:200%;"></div>
                </div>
            </div>
        </div>
    </div>

</div>


<script>

    let categorias, departamentos;
    $(document).ready(function () {

        $.ajax({
            type: 'GET',
            url: "${g.createLink(controller:'contacto',action:'reportes')}",
            success: function (response, textStatus) {

                let categorias = [], departamentos = [];
                response.categorias.forEach(function (uno) {
                    categorias.push({
                        name: uno[0],
                        value: uno[1]
                    })

                });

                response.departamentos.forEach(function (dos) {
                    departamentos.push({
                        name: dos[0],
                        value: dos[1]
                    });
                });

                console.log(categorias);
                console.log(departamentos);

                crearGraficoCategorias(categorias);
                crearGraficoDepartamentos(departamentos)

            }
        });


        window.addEventListener('resize', function (event) {
            categorias.resize();
            departamentos.resize();
        });

    });

    function crearGraficoCategorias(data) {

        let option = {
            title: {
                // text: 'Eficiencia'
            },
            legend: {
                show: true,
                type: 'scroll',
                // orient: 'vertical',
                // left: '90%',
                // top: 20,
                // bottom: 20,
            },
            tooltip: {
                trigger: 'item',
                // formatter: "{b} : {c}min "
            },

            series: {
                type: 'pie',
                radius: ['50%', '70%'],
                data: data,
                roseType: 'radius',
                animationType: 'scale',
                color: ["#0074D9", "#FF4136", "#2ECC40", "#FF851B", "#7FDBFF", "#B10DC9", "#FFDC00", "#001f3f", "#39CCCC", "#01FF70", "#85144b", "#F012BE", "#3D9970", "#111111", "#AAAAAA"]
            },
            textStyle: {
                color: '#000000'
            }

        };


        categorias = echarts.init(document.getElementById("categorias"));
        categorias.setOption(option);
    }

    function crearGraficoDepartamentos(data) {

        let option = {
            title: {
                // text: 'Eficiencia'
            },
            legend: {
                show: true,
                type: 'scroll',
                // orient: 'vertical',
                // left: '90%',
                // top: 20,
                // bottom: 20,
            },
            tooltip: {
                trigger: 'item',
                // formatter: "{b} : {c}min "
            },

            series: {
                type: 'pie',
                radius: ['50%', '70%'],
                data: data,
                roseType: 'radius',
                animationType: 'scale',
                color: ["#0074D9", "#FF4136", "#2ECC40", "#FF851B", "#7FDBFF", "#B10DC9", "#FFDC00", "#001f3f", "#39CCCC", "#01FF70", "#85144b", "#F012BE", "#3D9970", "#111111", "#AAAAAA"]
            },
            textStyle: {
                color: '#000000'
            }

        };


        departamentos = echarts.init(document.getElementById("departamentos"));
        departamentos.setOption(option);
    }

</script>
</body>
</html>
