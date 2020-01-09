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
<#--    <div>-->

    <form action="${href}" method="post" class="btn" style="padding: 0;">
        <input type="hidden" name="productId" value="${productId}">
        <input type="hidden" name="isAddToCartRequest" value="true">
        <input type="submit" value="В корзину"
               onclick="incrementsIncartCookie();" class="${class}">
    </form>
    <#nested>
<#--    <p style="padding: 3px;"></p>-->
<#--    </div>-->
<#--    onclick="incrementsIncartCookie(); return false;"-->
</#macro>