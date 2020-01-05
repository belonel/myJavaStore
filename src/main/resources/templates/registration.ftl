<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as l>

<@common.page>
    Add new user
    ${message!} <#--Отображает сообщение об ошиюке, если она есть-->
    <@l.login "/registration" true /> <#--true, т.к. это форма регистрации-->
</@common.page>