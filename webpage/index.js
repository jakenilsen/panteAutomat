//var url = "http://localhost:8080";

function insertCan() {
    const xmlHttp = new XMLHttpRequest();

    xmlHttp.open("POST", "http://localhost:8080/cans", false); // false for synchronous request
    xmlHttp.setRequestHeader("Access-Control-Allow-Origin", '*');
    xmlHttp.send(null);

    document.getElementById("welcomeText").innerHTML = xmlHttp.responseText;
    amountRecycled();
}

function insertBottle() {
    const xmlHttp = new XMLHttpRequest();

    xmlHttp.open("POST", "http://localhost:8080/bottles", false); // false for synchronous request
    xmlHttp.setRequestHeader("Access-Control-Allow-Origin", '*');
    xmlHttp.send(null);

    document.getElementById("welcomeText").innerHTML = xmlHttp.responseText;
    amountRecycled();
}

function amountRecycled() {
    const xmlHttp = new XMLHttpRequest();

    xmlHttp.open("GET", "http://localhost:8080/recycledamount", false); // false for synchronous request
    xmlHttp.setRequestHeader("Access-Control-Allow-Origin", '*');
    xmlHttp.send(null);

    document.getElementById("amountRecycled").innerHTML = xmlHttp.responseText;
}

function printVoucher() {
    const xmlHttp = new XMLHttpRequest();

    xmlHttp.open("GET", "http://localhost:8080/printvoucher", false); // false for synchronous request
    xmlHttp.setRequestHeader("Access-Control-Allow-Origin", '*');
    xmlHttp.send(null);

    document.getElementById("amountRecycled").innerHTML = xmlHttp.responseText;
}