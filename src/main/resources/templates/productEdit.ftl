<#import "parts/common.ftl" as common>
<#import "parts/addToCartButton.ftl" as addToCart>

<@common.page>
    <div class="card">
        <div class="row no-gutters">
            <aside class="col-md-6">
                <article class="gallery-wrap">
                    <div class="img-big-wrap">
                        <div>
                            <a href="#">
                                <#if product.imageFileName??>
                                    <img src="/img/${product.imageFileName}">
                                </#if>
                            </a>
                        </div>
                        <#--</div> <!-- slider-product.// &ndash;&gt;-->
                        <#--<div class="thumbs-wrap">-->
                        <#--    <a href="#" class="item-thumb"> <img src="bootstrap-ecommerce-html/images/items/12.jpg"></a>-->
                        <#--    <a href="#" class="item-thumb"> <img src="bootstrap-ecommerce-html/images/items/12-1.jpg"></a>-->
                        <#--    <a href="#" class="item-thumb"> <img src="bootstrap-ecommerce-html/images/items/12-2.jpg"></a>-->
                    </div> <!-- slider-nav.// -->
                </article> <!-- gallery-wrap .end// -->
            </aside>
            <main class="col-md-6 border-left">
                <article class="content-body">

                    <form action="/main/${product.id}/edit" method="post" class="form-group">
                        <div class="form-group">
                            <input type="text" name="productName" value="${product.name}" class="form-control">
                        </div>

                        <div class="form-group">
                            <input type="number" name="productCost" value="${product.cost}" class="form-control">
                        </div>

                        <div class="form-group">
                            <textarea name="productShortDescription" class="form-control">${product.shortDescription}</textarea>
                        </div>

                        <div class="form-group">
                            <textarea rows="10" name="productLongDescription" class="form-control">${product.longDescription}</textarea>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block">Сохранить изменения</button>
                        </div>
                    </form>

                    <a href="/main/${product.id}" class="btn btn-light btn-block">На страницу товара</a>

                </article> <!-- product-info-aside .// -->
            </main> <!-- col.// -->
        </div> <!-- row.// -->
    </div> <!-- card.// -->
</@common.page>