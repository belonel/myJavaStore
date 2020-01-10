<#macro login path isRegisterForm>
    <form action="${path}" method="post">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name :</label>
            <div class="col-sm-6">
                <input type="text" required name="username" class="form-control" placeholder="Nick name" />
            </div>
        </div>

        <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="email" required name="email" class="form-control" placeholder="some@some.com" />
            </div>
        </div>
        </#if>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" required name="password" class="form-control" placeholder="Password" />
            </div>
        </div>

<#--        <input type="hidden" name="_csrf" value="${_csrf.token}" />-->

        <div class="form-group row">
            <div class="col-sm-2"></div>
            <button class="btn btn-primary col-sm-2 ml-3 mr-3" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
        </div>
    </form>
    <#if !isRegisterForm><a href="/registration">Add new user</a></#if> <#--Показать ссылку, если это не форма регистрации-->
</#macro>

<#macro logout>
    <form action="/logout" method="post">
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>