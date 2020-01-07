<#import "navbar.ftl" as n>

<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <#--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />-->
        <title>Lider</title>

        <script src="/static/js/cookieScripts.js"></script>
        <link rel="stylesheet" href="/static/css/style.css">

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS from SWEATER template-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <!-- Bootstrap -->
        <link href="/static/css/bootstrap.css" rel="stylesheet" type="text/css"/>

        <!-- Font awesome 5 -->
        <link href="/static/fonts/fontawesome/css/all.min.css" type="text/css" rel="stylesheet">

        <!-- ui style -->
        <link href="/static/css/ui.css" rel="stylesheet" type="text/css"/>
        <link href="/static/css/responsive.css" rel="stylesheet" />
    </head>
    <body>
        <@n.navbar/> <#-- incart=0 -->
        <div class="container mt-5">
        <#nested>
        </div>
        <!-- Optional JavaScript -->

        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <!-- jQuery -->
        <script src="/static/js/jquery-2.0.0.min.js" type="text/javascript"></script>

        <!-- Bootstrap -->
        <script src="/static/js/bootstrap.bundle.min.js" type="text/javascript"></script>

        <!-- custom javascript -->
        <script src="/static/js/script.js" type="text/javascript"></script>
    </body>
    </html>
</#macro>