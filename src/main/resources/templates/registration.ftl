<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as l>

<@common.page>
    Add new user
    ${message!}
    <@l.login "/registration" />
</@common.page>