<#import "parts/common.ftl" as common>
<#import "parts/product_card.ftl" as p>
<#import "parts/addToCartButton.ftl" as addToCart>

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

                    <p>${product.shortDescription}</p>
                    <#--<p>The largest Apple Watch display yet. Electrical heart sensor. Re-engineered Digital Crown with haptic feedback. Entirely familiar, yet completely redesigned, Apple Watch Series 4 resets the standard for what a watch can be.</p>-->

                </article> <!-- col.// -->
                <aside class="col-sm-3">
                    <div class="price-wrap mt-2">
                        <span class="price h5"> ₽ ${product.cost} </span>
                        <del class="price-old"> ₽ ${product.cost + 190} </del>
                    </div> <!-- info-price-detail // -->

                    <p class="small text-success"> Бесплатная доставка </p>
                    <br>
                    <p>
                        <@addToCart.add "${product.id}" "/main" "btn btn-outline-primary md-2"> <#-- id href class-->

                        </@addToCart.add>
                        <a href="/main/${product.id}" class="btn btn-light"> Подробнее  </a>
                    </p>
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