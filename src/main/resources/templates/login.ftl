<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as l>

<@common.page>
    <h3 class="my-4">Вход</h3>
    ${message?ifExists}
    <@l.login "/login" false /> <#--false, т.к. это не форма регистрации-->
</@common.page>