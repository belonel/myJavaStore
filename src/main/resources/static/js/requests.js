function postRequest(productId, url) {
    // var data = new FormData();
    // data.append('userId', userId);
    // data.append("_csrf", token);
    //
    // var xhr = new XMLHttpRequest();
    // xhr.open('POST', newurl, true);
    //
    // xhr.onload = function () {
    //     // do something to response
    //     console.log(this.responseText);
    // };
    // xhr.send(data);

// Создаем экземпляр класса XMLHttpRequest
    const request = new XMLHttpRequest();

// Указываем путь до файла на сервере, который будет обрабатывать наш запрос
    const newurl = "/" + url;

// Так же как и в GET составляем строку с данными, но уже без пути к файлу
    const params = "productId=" + productId;

    /* Указываем что соединение	у нас будет POST, говорим что путь к файлу в переменной url, и что запрос у нас
    асинхронный, по умолчанию так и есть не стоит его указывать, еще есть 4-й параметр пароль авторизации, но этот
        параметр тоже необязателен.*/
    request.open("POST", newurl, true);

//В заголовке говорим что тип передаваемых данных закодирован.
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    request.addEventListener("readystatechange", () => {

        if(request.readyState === 4 && request.status === 200) {
            console.log(request.responseText);
        }
    });

    console.log(productId);
    console.log(params);

//	Вот здесь мы и передаем строку с данными, которую формировали выше. И собственно выполняем запрос.
    request.send(params);
}

function sendDelete(event, name, value) {
    var xhttp = new XMLHttpRequest();
    var params = name + '=' + value;
    event.preventDefault();
    xhttp.open("DELETE", this.href, true);

    //Send the proper header information along with the request
    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhttp.onreadystatechange = function() {//Call a function when the state changes.
        if(xhttp.readyState == 4 && xhttp.status == 200) {
            alert(xhttp.responseText);
        }
    }

    xhttp.send(params);
}