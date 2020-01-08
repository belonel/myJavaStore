<#import "parts/common.ftl" as common>

<@common.page>
    <h5>Your order:</h5>
    <ol>
    <#list orderdetails as item>
        <li>
        Товар: ${item.getProductName()} <br>
        Цена: ${item.price} <br>
        Количество: ${item.quanity} <br>
        </li>
    </#list>
    <ol>
    Итого: ${order.amount}
</@common.page>