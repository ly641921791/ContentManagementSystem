;
var apis = {
    getSystemConfigMap: {
        url: "/api/v1/system/config/map",
        type: "GET",
    },
    userRegister: {
        url: "/api/v1/system/user/register",
        type: "POST"
    },
    userLogin: {
        url: "/api/v1/system/user/login",
        type: "POST"
    },
    addUser: {
        url: "/api/v1/system/user",
        type: "POST"
    },
    delUser: {
        url: "/api/v1/system/user",
        type: "DELETE",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        parseData: JSON.stringify
    },
    modUser: {
        url: "/api/v1/system/user",
        type: "PUT",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        parseData: JSON.stringify
    },
    userList: {
        url: "/api/v1/system/user/list",
        type: "GET"
    },
    userMenu: {
        url: "/api/v1/system/user/menu",
        type: "GET"
    },
    setUserRole: {
        url: "/api/v1/system/user/{id}/role",
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        parseData: JSON.stringify
    },
    getUserRole: {
        url: "/api/v1/system/user/{id}/role",
        type: "GET"
    },
    setRolePermission: {
        url: "/api/v1/system/role/{id}/permission",
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        parseData: JSON.stringify
    },
    getRolePermission: {
        url: "/api/v1/system/role/{id}/permission",
        type: "GET"
    },
    addRole: {
        url: "/api/v1/system/role",
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        parseData: JSON.stringify
    },
    delRole: {
        url: "/api/v1/system/role",
        type: "DELETE",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        parseData: JSON.stringify
    },
    modRole: {
        url: "/api/v1/system/role",
        type: "PUT",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        parseData: JSON.stringify
    },
    listRole: {
        url: "/api/v1/system/role/list",
        type: "GET"
    },
    listPermission: {
        url: "/api/v1/system/permission/list",
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
    modBook: {
        url: "/api/v1/book",
        type: "PUT",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        parseData: JSON.stringify
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
    modLendBook: {
        url: "/api/v1/book/lend",
        type: "PUT",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        parseData: JSON.stringify
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
if (String.prototype.format) {
    console.warn("String.prototype.format has been defined.");
}
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

/**
 * Array.equals
 */
if (Array.prototype.equals) {
    console.warn("Overriding existing Array.prototype.equals. Possible causes: New API defines the method, there's a framework conflict or you've got double inclusions in your code.");
}
Array.prototype.equals = function (array) {
    // if the other array is a falsy value, return
    if (!array)
        return false;

    // compare lengths - can save a lot of time
    if (this.length !== array.length)
        return false;

    for (var i = 0, l = this.length; i < l; i++) {
        // Check if we have nested arrays
        if (this[i] instanceof Array && array[i] instanceof Array) {
            // recurse into the nested arrays
            if (!this[i].equals(array[i]))
                return false;
        } else if (this[i] !== array[i]) {
            // Warning - two different object instances will never be equal: {x:20} != {x:20}
            return false;
        }
    }
    return true;
}
// Hide method from for-in loops
Object.defineProperty(Array.prototype, "equals", {enumerable: false});


/**
 * layui.tree 组件存在bug，返回的是父子节点嵌套结构
 * 通过该方法获得全部被选中节点的id
 */
function getCheckedTreeNodeId(checkData) {
    var ids = [];
    if (checkData.length > 0) {
        checkData.forEach(data => {
            ids.push(data.id);
            ids.push.apply(ids, getCheckedTreeNodeId(data.children));
        });
    }
    ids.sort();
    return ids;
}


function eventExecute(eventExecutor, event, args) {
    var method = eventExecutor[event];
    if (method instanceof Function) {
        method.call(this, args);
    } else {
        layer.open({
            title: '暂不支持',
            content: '该功能暂不支持，请联系开发人员',
        });
    }
}
