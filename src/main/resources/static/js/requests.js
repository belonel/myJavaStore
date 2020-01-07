function postRequest(userId, productId) {

// Создаем экземпляр класса XMLHttpRequest
    const request = new XMLHttpRequest();

// Указываем путь до файла на сервере, который будет обрабатывать наш запрос
    const url = "/cart";

// Так же как и в GET составляем строку с данными, но уже без пути к файлу
    const params = "userId=" + userId+ "&productId=" + productId;

    /* Указываем что соединение	у нас будет POST, говорим что путь к файлу в переменной url, и что запрос у нас
    асинхронный, по умолчанию так и есть не стоит его указывать, еще есть 4-й параметр пароль авторизации, но этот
        параметр тоже необязателен.*/
    request.open("POST", url, true);

//В заголовке говорим что тип передаваемых данных закодирован.
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    request.addEventListener("readystatechange", () => {

        if(request.readyState === 4 && request.status === 200) {
            console.log(request.responseText);
        }
    });

//	Вот здесь мы и передаем строку с данными, которую формировали выше. И собственно выполняем запрос.
    request.send(params);
}