window.onload = function () {
    document.getElementById("success").hidden = true;
    document.getElementById("failure").hidden = true;
};
document.getElementById("uploadbtn").onclick = function () {

    var f = document.getElementById("file").value;
    if (f === "") {
        document.getElementById("result2").innerHTML = "Choose a file!";
        document.getElementById("success").hidden = true;
        document.getElementById("failure").hidden = false;
        return;
    }
    document.getElementById("failure").hidden = true;
    document.getElementById("success").hidden = false;
    document.getElementById("result1").innerHTML = "Processing...";

    var file = document.getElementById("file").files[0];
    var formData = new FormData();
    formData.append("program", file);

    var xhttp;
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhttp.open("POST", "Upload", true);
    xhttp.send(formData);
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var msg = document.getElementById("result1");
            msg.innerHTML = xhttp.responseText;
        }
    };
};