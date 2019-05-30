// function takeaction() {
//     document.cookie = "id=adewfg";
//     document.cookie = "name=Tom";
//     window.location = "/logedin";
// }


function load() {
    formateddate = formatday(calculatedayssince())
    document.getElementById("date").innerHTML = "Third Riche, "+formateddate+" day of a 1000 years"

}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
  }

function calculatedayssince() {
    var date1 = new Date('March 5, 2019 00:00:00');
    // Sun Dec 17 1995 03:24:00 GMT...

    var date2 = new Date();
    // Sun Dec 17 1995 03:24:00 GMT...

    diff = date2 - date1
    dayssince = Math.floor(diff/86400000)

    return dayssince
}

function formatday(num) {
    lastdigit = num.toLocaleString().slice(-1,)

    suffix = {"1":"st","2":"nd","3":"rd"}
    s = suffix[lastdigit]
    if (s != undefined) {
        return num.toLocaleString() + s
    }else {
        return num.toLocaleString() + "th"
    }
}


// Taken from dep.html
var queueFunc = null;

function responseHandler(ev1) {
    var response = JSON.parse(ev1.target.response);
    switch (response.action) {
        case "request_signature":
            endsign(response.payload);
            break;
        case "error":
            alert("error: " + response.payload);
            break;
        case "auth_success":
            alert("logged in: " + response.payload);
            //enable sign||remove+download button
            break;
        case "sign_success":
            alert("signed for: " + response.payload);
            //enable remove+download button
            break;
    }
    if (queueFunc != null) {
        var func = queueFunc;
        queueFunc = null;
        func();
    }
}

//this is an auth function, it will return if user has or hasnt signed, so we can choose between states, will do a bit later.
//you will get that param from responsehandler auth_success
function getcertificate(func) {
    queueFunc = func;
    window.hwcrypto.getCertificate({lang: 'en'}).then(function(certificate) {
        window.certificate = certificate;
        var xhr = new XMLHttpRequest;
        xhr.onload = responseHandler;
        xhr.open("POST", "/auth", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send("cert=" + window.certificate.hex);
    });
}

//when user clicks the sign button
function startsign() {
    if  (window.certificate === undefined) {
        getcertificate(startsign);
    } else {
        var xhr = new XMLHttpRequest;
        xhr.onload = responseHandler;
        xhr.open("POST", "/sign/start", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send("cert=" + window.certificate.hex);
    }
}

//called from callbacks, signing second phase
function endsign(data) {
    window.hwcrypto.sign(window.certificate, {type: 'SHA-256', hex: data}, {lang: 'en'}).then(function(signature) {
        var xhr = new XMLHttpRequest;
        xhr.onload = responseHandler;
        xhr.open("POST", "/sign/finish", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send("signature="+signature.hex);
    });
}

//to download a signed file, should only be visible in signed state
function loadfile() {
    if  (window.certificate === undefined) {
        getcertificate(loadfile);
    } else {
        window.open("/get", "_blank");
    }
}