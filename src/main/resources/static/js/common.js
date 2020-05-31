;
var apis = {
    getSystemConfigMap: {
        url: "/api/v1/system/config/map",
        type: "GET",
    },
    userLogin: {
        url: "/api/v1/system/user/login",
        type: "POST"
    },
    userList: {
        url: "/api/v1/system/user/list",
        type: "GET"
    },
    userMenu: {
        url: "/api/v1/system/user/menu",
        type: "GET"
    },
    listRole: {
        url: "/api/v1/system/role/list",
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

/**
 * 字符串格式化方法，支持{name}占位符 和 {0}占位符
 */
String.prototype.format = function () {
    if (arguments.length < 1) {
        return this;
    }

    var data = this, arg0 = arguments[0];
    if (arg0 instanceof Object) {
        for (var key in arg0) {
            data = data.replace(new RegExp("\\{" + key + "\\}", "g"), arg0[key]);
        }
    } else {
        for (var i = 0; i < arguments.length; i++) {
            data = data.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
        }
    }

    return data;
}
