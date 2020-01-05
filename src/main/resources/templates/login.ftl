<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as l>

<@common.page>
    <#--Login page-->
    <@l.login "/login" false /> <#--false, т.к. это не форма регистрации-->
</@common.page>