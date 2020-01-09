<#import "parts/common.ftl" as common>
<#import "parts/product_card.ftl" as p>
<#import "parts/addToCartButton.ftl" as addToCart>

<@common.page>

    <h2 class="my-3">Каталог товаров</h2>
<#-----------------------SEARCH------------------------------->
    <div class="row">
        <div class="">
            <div class="card" style="display: inline-block;">
                <div class="card-body">
                    <h5 class="card-title">Поиск</h5>
                    <form method="get" action="/main" class="form-inline">
                        <div class="input-group">
                            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by name">
                            <span class="input-group-append">
                                <button class="btn btn-primary"> <i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </form>
                </div>
            </div>
        </div>

<#-----------------------FILTER----------------------------->
        <div class="">
            <div class="card ml-3" style="display: inline-block;">
                <div class="card-body">
                    <h5 class="card-title">Показать только</h5>
                    <div class="row">
                        <form action="/main" method="get">
                            <input type="hidden" name="onlyMyProducts" value="true">
                            <button class="btn btn-light mr-2">Мои товары</button>
                        </form>
                        <form action="/main" method="get">
                            <input type="hidden" name="onlyMyProducts" value="false">
                            <button class="btn btn-light mr-2">Все</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="col"></div>
    </div>


<#--    <a class="btn btn-primary" data-toggle="collapse" href="#collapseCreateForm" role="button" aria-expanded="false" aria-controls="collapseCreateForm">-->
<#--        Add new Message-->
<#--    </a>-->
    <h5 class="my-3 ml-1">Добавить новый товар</h5>
<#--    <div class="collapseCreateForm" id="collapseCreateForm">-->
    <div>
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">

                <div class="form-group">
                    <input type="text" class="form-control" name="name" placeholder="Введите наименование" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="shortDescr" placeholder="Короткое описание для страницы каталога" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="longDescr" placeholder="Развернутое описание для страницы товара" />
                </div>
                <div class="form-group">
                    <input type="number" class="form-control" name="cost" placeholder="Цена" />
                </div>
                <#--                <div class="form-group">-->
                <#--                    <input type="text" class="form-control" name="text" placeholder="Введите сообщение" />-->
                <#--                </div>-->

                <#--                <div class="form-group">-->
                <#--                    <input type="text" class="form-control" name="tag" placeholder="Тэг">-->
                <#--                </div>-->

                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile"><#--label к кнопке-->
                            Choose file
                        </label>
                    </div>
                </div>

<#--                <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
                <input type="hidden" name="isAddProductRequest" value="true" />

                <div class="form-group">
                    <button type="submit" class="btn btn-primary">
                        Add
                    </button>
                </div>
            </form>
        </div>
    </div>

    <h5 class="mt-2">Список сообщений</h5>

<#--    <div class="card-columns">-->
<#--        <#list messages as message>-->
<#--            <div class="card my-3"> &lt;#&ndash; my-3 делает отступы сверху и снизу&ndash;&gt;-->

<#--                <#if message.filename??>-->
<#--                    <img src="/img/${message.filename}" class="card-img-top">-->
<#--                </#if>-->

<#--                <div class="m-2">-->
<#--                    <span>${message.text}</span>-->
<#--                    <i>${message.tag}</i>-->
<#--                </div>-->

<#--                <div class="card-footer text-muted">-->
<#--                    ${message.authorName}-->
<#--                </div>-->
<#--            </div>-->
<#--        <#else>-->
<#--            No message-->
<#--        </#list>-->
<#--    </div>-->

<#--    <@p.product />-->
    <#list products as product>
    <article class="card card-product-list my-3">
        <div class="card-body">
            <div class="row">
                <aside class="col-sm-3">
                    <#if product.imageFileName??>
                        <a href="/main/${product.id}" class="img-wrap"><img src="/img/${product.imageFileName}"></a>
                    </#if>
                </aside> <!-- col.// -->
                <article class="col-sm-6">
                    <#--<a href="#" class="title mt-2 h5">${message.text}</a>-->
                    <a href="/main/${product.id}" class="title mt-2 h5">${product.name}</a>

                    <div class="rating-wrap mb-3">
                        <#--    <ul class="rating-stars">-->
                        <#--        <li style="width:80%" class="stars-active">-->
                        <#--            <i class="fa fa-star"></i> <i class="fa fa-star"></i>-->
                        <#--            <i class="fa fa-star"></i> <i class="fa fa-star"></i>-->
                        <#--            <i class="fa fa-star"></i>-->
                        <#--        </li>-->
                        <#--        <li>-->
                        <#--            <i class="fa fa-star"></i> <i class="fa fa-star"></i>-->
                        <#--            <i class="fa fa-star"></i> <i class="fa fa-star"></i>-->
                        <#--            <i class="fa fa-star"></i>-->
                        <#--        </li>-->
                        <#--    </ul>-->
                        <small class="label-rating text-muted">10 reviews</small>
                        <small class="label-rating text-success">
                            <i class="fa fa-clipboard-check"></i> 117 orders </small>
                    </div> <!-- rating-wrap.// -->
<#--------------------------------------DESCRIPTION--------------------------------------->
                    <p>${product.shortDescription}</p>
<#--------------------------------------EDIT/DELETE BUTTONS--------------------------------------->
                    <br>
                    <#if product.getAuthor().id == user.id>
                        <a href="/main/${product.id}/edit" class="btn btn-light">Edit</a>

                        <form action="/main" method="post" class="btn btn-light" style="padding: 0;">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="hidden" name="isDeleteRequest" value="true">
                            <button class="btn btn-block">Delete</button>
                        </form>
                    </#if>

                </article> <!-- col.// -->
                <aside class="col-sm-3">
                    <div class="price-wrap mt-2">
                        <span class="price h5"> ₽ ${product.cost} </span>
                        <del class="price-old"> ₽ ${product.cost + 190} </del>
                    </div> <!-- info-price-detail // -->

                    <p class="small text-success"> Электронный товар </p>
                    <br>
                    <div>
                        <@addToCart.add "${product.id}" "/main" "btn btn-primary"> <#-- id href class-->
                        </@addToCart.add>
                        <a href="/main/${product.id}" class="btn btn-light"> Подробнее  </a>
                    </div>
                    <br>
                    <#--                <a href="#" class="small"><i class="fa fa-heart"></i> Add to wishlist</a>-->
                </aside> <!-- col.// -->
            </div> <!-- row.// -->
        </div> <!-- card-body .// -->
    </article>
    <#else>
        No products
    </#list>
</@common.page>