import {sendData} from "./get-post.js";

var search = location.search.substr(1)
    .split('&')
    .reduce(function (res, a) {
        var t = a.split('=');
        res[decodeURIComponent(t[0])] = t.length == 1 ? null : decodeURIComponent(t[1]);
        return res;
    }, {});

sendData( "/users/delete", JSON.stringify({'login': search.login}))
window.location.href = "/admin/manage-admin.html";
