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
                                        <p class="text-muted small"> Товар создан: ${item.getProduct().creationDate} <br> Автор: ${item.getProduct().getAuthor().username}</p>
                                    </figcaption>
                                </figure>
                            </td>
                            <td>
<#-------IS UPDATE COUNT REQUEST-------------------------------------------------------------------------------->
                                <form action="/cart" method="post">
                                    <input type="hidden" name="isUpdateRequest" value="true">
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
<#---------------------------------------------------------------------------------------------------------->
                                <#--REMOVE button with DELETE REEQUEST-->
                                <#--<a href="/cart" class="btn btn-light" onclick="sendDelete(event, 'productName', ${item.getProduct().id})"> Remove</a>-->
<#-------IS DELETE PRODUCT REQUEST-------------------------------------------------------------------------------->
                                <form action="/cart" method="post">
                                    <input type="hidden" name="productId" value="${item.getProduct().id}">
                                    <input type="hidden" name="isDeleteRequest" value="true">
                                    <button class="btn btn-light" style="display: inline-block;">Remove</button>
                                </form>
                            </td>
                        </tr>

                        <#else>
                            <tr style="height: 100px;">
                                <td class="py-5">В Корзине пока нет ни одного товара.</td>
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

<#--            <div class="card">-->
<#--                <div class="card-body">-->
<#--                    <dl class="dlist-align">-->
<#--                        <dt>Итоговая цена:</dt>-->
<#--                        <dd class="text-right"> ₽${order.amount}</dd>-->
<#--                    </dl>-->
<#--                    <dl class="dlist-align">-->
<#--                        <dt>Скидка:</dt>-->
<#--                        <dd class="text-right text-danger">- ₽0</dd>-->
<#--                    </dl>-->
<#--                    <dl class="dlist-align">-->
<#--                        <dt>ИТОГО:</dt>-->
<#--                        <dd class="text-right text-dark b"><strong> ₽${order.amount}</strong></dd>-->
<#--                    </dl>-->
<#--                    <hr>-->
<#--                    <p class="text-center mb-3">-->
<#--                        &lt;#&ndash;<img src="../../../../uploads/cookie.png" height="26">&ndash;&gt;-->
<#--                    </p>-->

<#--                    <form action="/checkout" method="post" class="btn-block" style="padding: 0;">-->
<#--                        <input type="hidden" name="totalPrice" value="${order.amount}">-->
<#--                        <button class="btn btn-primary btn-block">Оплатить заказ</button>-->
<#--                    </form>-->
<#--                    <a href="/main" class="btn btn-light btn-block">Продолжить покупки</a>-->
<#--                </div> <!-- card-body.// &ndash;&gt;-->
<#--            </div> <!-- card.// &ndash;&gt;-->

            <div class="card mb-3">
                <div class="card-body">
<#-------IS SET EMAIL REQUEST-------------------------------------------------------------------------------->
                    <form action="/cart" method="post">
                        <input type="hidden" name="isSetEmailRequest" value="true">
                        <div class="form-group">
                            <label>E-mail для отправки</label>
                            <div class="input-group">
                                <input type="email" class="form-control" name="email" value="${order.customerEmail!}">
                                <span class="input-group-append">
                                    <button class="btn btn-primary">Да</button>
                                </span>
                            </div>
                        </div>
                        <p>Вы можете отправить себе, или указать e-mail друга :)</p>
                    </form>
                </div> <!-- card-body.// -->
            </div> <!-- card.// -->

            <div class="card">
                <div class="card-body">
                    <dl class="dlist-align">
                        <dt>У вас на счету есть</dt>
                        <dd class="text-right text-dark b">${user.money} койнов</dd>
                    </dl>
                    <dl class="dlist-align">
                        <dt>Цена всех товаров</dt>
                        <dd class="text-right">${order.amount} койнов</dd>
                    </dl>
                    <#if isNotEnoughCoins??><#--Если есть сообщение об ошибке-->
                        <div class="alert alert-danger" role="alert">
                            Недостаточно средств.
                        </div>
                    <#else>
                        <dl class="dlist-align">
                            <dt>Останется после оплаты</dt>
                            <dd class="text-right"><strong> ${user.money - order.amount} койнов</strong></dd>
                        </dl>
                        <hr>
                        <p class="text-center mb-3">

                            <#--<img src="../../../../uploads/cookie.png" height="26">-->
                        </p>
<#-------IS CHECKOUT REQUEST-------------------------------------------------------------------------------->
                        <form action="/cart" method="post" class="btn-block" style="padding: 0;">
                            <input type="hidden" name="orderId" value="${order.orderId}">
                            <input type="hidden" name="isCheckoutRequest" value="true">
                            <button class="btn btn-primary btn-block">Отправить</button>
                        </form>
                    </#if>

                    <a href="/main" class="btn btn-light btn-block">Продолжить покупки</a>
                </div> <!-- card-body.// -->
            </div> <!-- card.// -->

        </aside> <!-- col.// -->
    </div> <!-- row.// -->

</@common.page>