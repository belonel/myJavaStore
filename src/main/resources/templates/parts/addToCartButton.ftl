<#macro add productId>

<#assign known = Session.SPRING_SECURITY_CONTEXT??>
<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    <#-- Можно использовать методы из класса User -->
    userId = user.getId()
    isAdmin = user.isAdmin()
    >
<#else>
    <#assign userId="-1">
</#if>

<a href="#" class="btn btn-primary"
   onclick="setCookie('incart',
           (parseInt(getCookie('incart'), 10) + 1).toString(),
           {/*secure: false, 'max-age': 216000*/}
           );
           postRequest(${userId}, ${productId})
           return false;"
>
    <#nested>
</a>

</#macro>