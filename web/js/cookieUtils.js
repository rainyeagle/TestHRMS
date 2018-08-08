/**
 * 设置javascript的cookie
 *
 * @param cookieName  cookie键
 * @param cookieValue cookie值
 * @param minutes  cookie过期时间（分钟）
 */
function setCookie(cookieName, cookieValue, minutes) {
    var exdate = new Date();
    //设置cookie过期时间
    exdate.setMinutes(exdate.getMinutes() + minutes);
    document.cookie = cookieName + "=" + encodeURIComponent(cookieValue) + ((minutes == null) ? "" : ";expires=" + exdate.toUTCString());
}

function getCookie(cookieName) {
    var strCookie = document.cookie;//获取cookie字符串
    var arrCookie = strCookie.split(";");//分割
    //遍历匹配
    for (var i = 0; i < arrCookie.length; i++) {
        var arr = arrCookie[i].split("=");
        if (arr[0] === cookieName) {
            return arr[1];
        }
    }
    return "";
}

function showCookie() {
    alert(document.cookie);
}

function clearCookies() {

}