function takeaction() {
    document.cookie = "id=adewfg";
    document.cookie = "name=Tom";
    window.location = "/logedin";
}

function takeactionlogedin() {
    name = getCookie("name")
    id = getCookie("id")

    // boiler plate test
    window.hwcrypto.getCertificate({lang: 'en'}).then(function(certificate) {
        // Do something with the certificate, like prepare the hash to be signed
        var hash = calculateSHA256SignatureHashAsHexForCertificate(certificate.hex);
        // Now sign the hash
        window.hwcrypto.sign(certificate, {type: 'SHA-256', hex: hash}, {lang: 'en'}).then(function(signature) {
           // Do something with the signature
           storeSignature(signature.hex);
        }, function(error) {
           // Handle the error. `error.message` is one of the described error mnemonics
           console.log("Signing failed: " + error.message);
        });
     });
}

function load() {
    formateddate = formatday(calculatedayssince())
    document.getElementById("date").innerHTML = "Third Riche, "+formateddate+" day of a 1000 years"

}

function loadlogedin() {
    load()
    name = getCookie("name")
    document.getElementById("headline").childNodes[0].innerHTML = "Welcome: " + name + "<br> You have not had your voice heard."

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
