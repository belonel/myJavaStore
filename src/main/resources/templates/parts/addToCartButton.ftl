<#macro add productId href class>

<#--<#assign known = Session.SPRING_SECURITY_CONTEXT??>-->
<#--<#if known>-->
<#--    <#assign-->
<#--    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal-->
<#--    &lt;#&ndash; Можно использовать методы из класса User &ndash;&gt;-->
<#--    userId = user.getId()-->
<#--    isAdmin = user.isAdmin()-->
<#--    >-->
<#--<#else>-->
<#--    <#assign userId="-1">-->
<#--</#if>-->
    <div>
    <form action="${href}" method="post">
        <input type="hidden" name="productId" value="${productId}">
        <input type="submit" value="В Корзину"
               onclick="incrementsIncartCookie();" class="${class}">
    </form>
    <#nested>
    </div>
<#--    onclick="incrementsIncartCookie(); return false;"-->
</#macro>