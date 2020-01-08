<#import "parts/common.ftl" as common>

<@common.page>
    <h1>Корзина</h1>

    <div class="row mt-5">
        <aside class="col-lg-9">
            <div class="card">

                <div class="table-responsive">

                    <table class="table table-borderless table-shopping-cart">
                        <thead class="text-muted">
                        <tr class="small text-uppercase">
                            <th scope="col">Товары</th>
                            <th scope="col" width="180">Количество</th>
                            <th scope="col" width="120">Цена</th>
                            <th scope="col" class="text-right d-none d-md-block" width="160"> </th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderdetails as item>
                        <tr>
                            <td>
                                <figure class="itemside align-items-center">
                                    <div class="aside">
                                        <#if item.getProductFileName()??>
                                            <img src="/img/${item.getProductFileName()}" class="img-sm">
                                        </#if>
                                        <#--<img src="bootstrap-ecommerce-html/images/items/1.jpg">-->
                                    </div>
                                    <figcaption class="info">
                                        <a href="#" class="title text-dark">${item.getProductName()}</a>
                                        <#-- Добавить поля автора и дату создания к товару. по product_id брать эту инфу. Например
                                        item.findProductById().createDate-->
                                        <p class="text-muted small"> Товар от  <br> Автор </p>
                                    </figcaption>
                                </figure>
                            </td>
                            <td>
                                <form action="/cart" method="post">
                                    <#--<div class="form-group" style="display: inline-block;">-->
                                    <input type="number" class="form-control" name="count" value="${item.quanity}"
                                           width="50px" style="width: 50%; display: inline-block;"/>
                                    <input type="hidden" name="productId" value="${item.getProduct().id}">
                                    <#--</div>-->
                                    <button class="btn btn-light" style="display: inline-block;">ОК</button>
                                </form>
                            </td>
                            <td>
                                <div class="price-wrap">
                                    <var class="price"> ₽ ${item.price *item.quanity}</var>
                                    <small class="text-muted"> ₽ ${item.price} / шт. </small>
                                </div> <!-- price-wrap .// -->
                            </td>
                            <td class="text-right d-none d-md-block">
                                <#-- LIKE BUTTON -->
                                <#--<a data-original-title="Save to Wishlist" title="" href="" class="btn btn-light" data-toggle="tooltip"> <i class="fa fa-heart"></i></a>-->
                                <a href="" class="btn btn-light"> Remove</a>
                            </td>
                        </tr>
                        </#list>

                        </tbody>
                    </table>

                </div> <!-- table-responsive.// -->

                <div class="card-body border-top">
                    <p class="icontext">
                        <#--<i class="icon text-success fa fa-truck"></i> -->
                        Товары придут на e-mail сразу после оплаты.
                    </p>
                </div> <!-- card-body.// -->

            </div> <!-- card.// -->

        </aside> <!-- col.// -->
        <aside class="col-lg-3">
<#-----------------------------------COUPON CARD-------------------------------------->
            <#--<div class="card mb-3">-->
            <#--    <div class="card-body">-->
            <#--        <form>-->
            <#--            <div class="form-group">-->
            <#--                <label>Have coupon?</label>-->
            <#--                <div class="input-group">-->
            <#--                    <input type="text" class="form-control" name="" placeholder="Coupon code">-->
            <#--                    <span class="input-group-append">-->
            <#--                        <button class="btn btn-primary">Apply</button>-->
            <#--                    </span>-->
            <#--                </div>-->
            <#--            </div>-->
            <#--        </form>-->
            <#--    </div> <!-- card-body.// &ndash;&gt;-->
            <#--</div> <!-- card.// &ndash;&gt;-->

            <div class="card">
                <div class="card-body">
                    <dl class="dlist-align">
                        <dt>Итоговая цена:</dt>
                        <dd class="text-right"> ₽${order.amount}</dd>
                    </dl>
                    <dl class="dlist-align">
                        <dt>Скидка:</dt>
                        <dd class="text-right text-danger">- ₽0</dd>
                    </dl>
                    <dl class="dlist-align">
                        <dt>ИТОГО:</dt>
                        <dd class="text-right text-dark b"><strong> ₽${order.amount}</strong></dd>
                    </dl>
                    <hr>
                    <p class="text-center mb-3">
                        <#--<img src="../../../../uploads/cookie.png" height="26">-->
                    </p>
                    <#--<a href="/checkout" class="btn btn-primary btn-block">-->
                    <#--    Оплатить заказ-->
                    <#--</a>-->

                    <form action="/checkout" method="post" class="btn-block" style="padding: 0;">
                        <input type="hidden" name="totalPrice" value="${order.amount}">
                        <button class="btn btn-primary btn-block">Оплатить заказ</button>
                    </form>
                    <a href="/main" class="btn btn-light btn-block">Продолжить покупки</a>
                </div> <!-- card-body.// -->
            </div> <!-- card.// -->

        </aside> <!-- col.// -->
    </div> <!-- row.// -->

</@common.page>