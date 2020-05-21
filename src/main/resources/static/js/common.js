;
var apis = {
    getSystemConfigMap: {
        url:"/api/v1/system/config/map",
        type:"GET",
    },
    userLogin :{
        url:"/api/v1/user/login",
        type:"POST"
    }
};


function ajaxError(xhr) {
    console.log(xhr);
    layer.open({
        title: xhr.status,
        content: xhr.statusText,
        anim:6
    });
}