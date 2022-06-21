import {sendData} from "./get-post.js";

var search = location.search.substr(1)
    .split('&')
    .reduce(function (res, a) {
        var t = a.split('=');
        res[decodeURIComponent(t[0])] = t.length == 1 ? null : decodeURIComponent(t[1]);
        return res;
    }, {});

sendData( "/categories/delete", JSON.stringify({'id': search.id}))
window.location = "/admin/category-admin.html";