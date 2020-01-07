<#include "security.ftl">
<#import "login.ftl" as l>

<#macro navbar>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Lider</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">My posts</a>
            </li>
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">User list</a>
            </li>
            </#if>
        </ul>

        <div class="icontext mr-4" style="max-width: 300px;">
            <span class="icon icon-sm rounded-circle bg-light">
                <i class="fa fa-shopping-cart text-primary"></i>
                <span class="notify" id="counter">
                    <script>
                        printCounter();
                        function printCounter() {
                            document.getElementById("counter").textContent = getCookie("incart");
                        }
                        setInterval(printCounter,250);
                    </script>
                </span>
	        </span>
            <div class="text">
                <a href="/cart">Shopping cart</a>
            </div>
        </div> <!-- iconbox // -->

        <div class="navbar-text mr-3">${name}</div>
        <#-- Сделать кнопку logout показывающейся только после входа -->
        <@l.logout />
    </div>
</nav>

</#macro>