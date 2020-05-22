;
var apis = {
    getSystemConfigMap: {
        url: "/api/v1/system/config/map",
        type: "GET",
    },
    userLogin: {
        url: "/api/v1/user/login",
        type: "POST"
    },
    userList: {
        url: "/api/v1/user/list",
        type: "GET"
    },
    userMenu: {
        url: "/api/v1/user/menu",
        type: "GET"
    },
};

function ajaxSuccessCheck(res) {
    if ("00000" === res.error) return true;
    layer.open({
        title: res.error,
        content: res.message,
        anim: 6
    });
    return false;
}

function ajaxError(xhr) {
    console.log(xhr);
    layer.open({
        title: xhr.status,
        content: xhr.statusText,
        anim: 6
    });
}


String.format = function () {
    if (arguments.length < 1) return "";
    if (arguments.length === 1) return arguments[0];
    var s = arguments[0];
    for (var i = 0; i < arguments.length - 1; i++) {
        var reg = new RegExp("\\{" + i + "\\}", "gm");
        s = s.replace(reg, arguments[i + 1]);
    }
    return s;
}
