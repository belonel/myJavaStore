<#import "parts/common.ftl" as common>

<@common.page>

    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <div>
                    <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by tag">
                </div>
                <button type="submit" class="btn btn-primary ml-2">
                    Search
                </button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseCreateForm" role="button" aria-expanded="false" aria-controls="collapseCreateForm">
        Add new Message
    </a>
    <div class="collapseCreateForm" id="collapseCreateForm">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" name="text" placeholder="Введите сообщение" />
                </div>

                <div class="form-group">
                    <input type="text" class="form-control" name="tag" placeholder="Тэг">
                </div>

                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile"><#--label к кнопке-->
                            Choose file
                        </label>
                    </div>
                </div>

                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">
                        Add
                    </button>
                </div>
            </form>
        </div>
    </div>

    <h5 class="mt-2">Список сообщений</h5>

    <div class="card-columns">
        <#list messages as message>
            <div class="card my-3"> <#--my-3 делает отступы сверху и снизу-->

                <#if message.filename??>
                    <img src="/img/${message.filename}" class="card-img-top">
                </#if>

                <div class="m-2">
                    <span>${message.text}</span>
                    <i>${message.tag}</i>
                </div>

                <div class="card-footer text-muted">
                    ${message.authorName}
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@common.page>