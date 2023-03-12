//var url = "http://localhost:8080";

function insertCan() {
    const xmlHttp = new XMLHttpRequest();

    xmlHttp.open("POST", "http://localhost:8080/cans", false); // false for synchronous request
    xmlHttp.setRequestHeader("Access-Control-Allow-Origin", '*');
    xmlHttp.setRequestHeader("objectType", document.activeElement.name);

    xmlHttp.send(null);

    // 202 ACCEPTED for successfully inserting a can
    if (xmlHttp.status == 202) {
        document.getElementById("welcomeText").innerHTML = xmlHttp.responseText;
    } else {
        document.getElementById("welcomeText").innerHTML = "Error: " + xmlHttp.responseText;
    }

    amountRecycled();
}

function insertBottle() {
    const xmlHttp = new XMLHttpRequest();

    xmlHttp.open("POST", "http://localhost:8080/bottles", false); // false for synchronous request
    xmlHttp.setRequestHeader("Access-Control-Allow-Origin", '*');
    xmlHttp.setRequestHeader("objectType", document.activeElement.name);
    xmlHttp.send(null);

    // 202 ACCEPTED for successfully inserting a bottle
    if (xmlHttp.status == 202) {
        document.getElementById("welcomeText").innerHTML = xmlHttp.responseText;
    } else {
        document.getElementById("welcomeText").innerHTML = "Error: " + xmlHttp.responseText;
    }

    amountRecycled();
}
function insertBasicObject() {
    const xmlHttp = new XMLHttpRequest();

    xmlHttp.open("POST", "http://localhost:8080/basicobject", false); // false for synchronous request
    xmlHttp.setRequestHeader("Access-Control-Allow-Origin", '*');
    xmlHttp.setRequestHeader("objectType", document.activeElement.name);
    xmlHttp.send(null);

    // 202 ACCEPTED for successfully inserting a bottle
    if (xmlHttp.status == 202) {
        document.getElementById("welcomeText").innerHTML = xmlHttp.responseText;
    } else {
        document.getElementById("welcomeText").innerHTML = "Error: " + xmlHttp.responseText;
    }

    amountRecycled();
}

function amountRecycled() {
    const xmlHttp = new XMLHttpRequest();

    xmlHttp.open("GET", "http://localhost:8080/recycledamount", false); // false for synchronous request
    xmlHttp.setRequestHeader("Access-Control-Allow-Origin", '*');
    xmlHttp.send(null);

    // 200 OK for successfully fetching amount recycled
    if (xmlHttp.status == 200) {
        document.getElementById("amountRecycled").innerHTML = xmlHttp.responseText;
    } else {
        document.getElementById("amountRecycled").innerHTML = "Error: " + xmlHttp.responseText;
    }
}

function printVoucher() {
    const xmlHttp = new XMLHttpRequest();

    xmlHttp.open("GET", "http://localhost:8080/printvoucher", false); // false for synchronous request
    xmlHttp.setRequestHeader("Access-Control-Allow-Origin", '*');
    xmlHttp.send(null);

    // 200 OK for successfully printing voucher
    if (xmlHttp.status == 200) {
        document.getElementById("amountRecycled").innerHTML = xmlHttp.responseText;
    } else {
        document.getElementById("amountRecycled").innerHTML = "Error: " + xmlHttp.responseText;
    }
}

    function showVoucherHistory() {
        const xmlHttp = new XMLHttpRequest();

        xmlHttp.open("GET", "http://localhost:8080/voucherhistory", false); // false for synchronous request
        xmlHttp.setRequestHeader("Access-Control-Allow-Origin", '*');
        xmlHttp.send(null);

        // 200 OK for successfully printing voucher
        if (xmlHttp.status == 200) {
            document.getElementById("voucherHistory").innerHTML = xmlHttp.responseText;
        } else {
            document.getElementById("voucherHistory").innerHTML = "Error: " + xmlHttp.responseText;
        }
}