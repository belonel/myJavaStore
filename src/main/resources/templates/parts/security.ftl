<#--Для определения переменных внутри Freemarker используем assign-->
<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
    <#-- возвращает boolean. SpringSecurity помещает контекст Freemarker в специальный объект, который позволяет оперировать контекстом SpringSecurity-->
    <#-- Если этот лбъект определен в контексте, значит можно работать с сеесией пользователя -->
>

<#if known>
    <#assign
        <#-- Значение будет содержать пользователся, описанного в базе данных -->
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        <#-- Можно использовать методы из класса User -->
        name = user.getUsername()
        isAdmin = user.isAdmin()
    >
<#else>
    <#assign
    name = "unknown"
    isAdmin = false
    >
</#if>
