
function fnIsIos(){
    var userAgent = navigator.userAgent.toLowerCase();
    if((userAgent.search("iphone") > -1) || (userAgent.search("ipod") > -1) || (userAgent.search("ipad") > -1)){
        return true;
    }
    return false;
}

function fnOpen(url) {
    try{
        if(fnIsIos()){
            webkit.messageHandlers.open.postMessage(url);
        }else {
            window.app.open(url);
        }
    }catch(e){
        window.open(url, '_blank');
    }
}

function fnGetWalletId() {
    try {
        if(fnIsIos()){
            webkit.messageHandlers.getWalletAddress.postMessage('');
        }else {
            window.app.getWalletAddress();
        }
    }catch(e){

    }
}

function fnOpenAdMission(type) {
    try {
        if(fnIsIos()){
            webkit.messageHandlers.openAdMission.postMessage(type);
        }else {
            window.app.openAdMission(type);
        }
    }catch(e){
        console.log(e.toString());
        alert("리텀앱에서만 가용하능합니다.");
    }
}

function fnOpenMission(type, address, p) {
    try {
        if(fnIsIos()){
            webkit.messageHandlers.openMission.postMessage(type);
        }else {
            window.app.openMission(type);
        }
    }catch(e){
        if(fnIsIos()){
            alert("안드로이드 리텀앱에서만 사용가능합니다.");
        }else {
            location.href = "intent://main?type=mission&missionTypeCd=" + type + "&id=" + encodeURIComponent(p).replace(/[!'()]/g, escape).replace(/\*/g, "%2A") + "#Intent;scheme=returm;package=io.returm.android;end";
        }
    }
}

function fnOpenAdMission(type, address, p) {

    if(!address) {
        alert("지갑주소를 찾을 수 없습니다. returm.io로 이동합니다.");
        fnOpen("http://returm.io");
        return;
    }

    var param = {
        type : type,
        address : address
    }

    try {
        if(fnIsIos()){
            webkit.messageHandlers.openAdMission.postMessage(JSON.stringify(param));
        }else {
            window.app.openAdMission(JSON.stringify(param));
        }
    }catch(e){
        if(fnIsIos()){
            alert("안드로이드 리텀앱에서만 사용가능합니다.");
        }else {
            location.href = "intent://main?type=ad&id=" + encodeURIComponent(p).replace(/[!'()]/g, escape).replace(/\*/g, "%2A") + "#Intent;scheme=returm;package=io.returm.android;end";
        }
    }
}

function fnScrollTop() {
    var offset = $("body").offset();
    $('html, body').animate({scrollTop : offset.top}, 400);
}