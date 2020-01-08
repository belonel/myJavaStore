<#import "parts/common.ftl" as common>

<@common.page>
    <h5>Your order:</h5>
    <#list orderdetails as item>
        Товар: ${item.getProductName()}
        Цена: ${item.price}
        Количество: ${item.quanity}
        Количество: ${item.quanity}
    </#list>

    Итого: ${order.amount}
</@common.page>