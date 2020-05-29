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
    addBook: {
        url: "/api/v1/book",
        type: "POST",
    },
    delBook: {
        url: "/api/v1/book",
        type: "DELETE",
    },
    bookList: {
        url: "/api/v1/book/list",
        type: "GET"
    },
    addBookType: {
        url: "/api/v1/book/type",
        type: "POST"
    },
    listBookType: {
        url: "/api/v1/book/type/list",
        type: "GET",
    },
    lendBook: {
        url: "/api/v1/book/lend",
        type: "POST",
    },
    listBookLend: {
        url: "/api/v1/book/lend/list",
        type: "GET",
    },
    addDocumentation: {
        url: "/api/v1/documentation",
        type: "POST",
    },
    documentationList: {
        url: "/api/v1/documentation/list",
        type: "GET"
    },
};

var pages = {
    bookForm: "/book/form"
}


function ajaxSetup(layui) {
    var $ = layui.$;
    var layer = layui.layer;

    $.ajaxSetup({
        async: true,
        headers: {},
        success: function (res) {
            if ("00000" === res.error) {
                this.successExecute(res);
            } else if ("A0230" === res.error) {
                layer.open({
                    title: res.error,
                    content: res.message,
                    anim: 6,
                    end: function () {
                        top.location.href = "/user/login";
                    },
                });
            } else {
                layer.open({
                    title: res.error,
                    content: res.message,
                    anim: 6
                });
            }
        },
        error: function (xhr) {
            layer.open({
                title: xhr.status,
                content: xhr.statusText,
                anim: 6
            })
        },
        // 自定义方法
        successExecute: function (res) {
        },
    })
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
