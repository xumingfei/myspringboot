var hosts={
    "www":"main",
    "news":"sz",
    "comment":"pl",
    "world":"gj",
    "military":"js",
    "society":"sh",
    "finance":"cj",
    "firm":"qy",
    "tour":"travel",
    "acftu":"ghgz",
    "right":"wq",
    "theory":"lilun",
    "job":"jyycy",
    "character":"rw",
    "v":"bk",
    "photo":"th",
    "culture":"wh",
    "edu":"jy",
    "book":"ds",
    "ent":"yl",
    "sports":"ty",
    "nmg":"nmg",
    "wgt":"wgt",
    "media":"media",
    "helper":"helper",
    "green":"lspd",
    "city":"cspd",
    "build":"jspd",
    "art":"art",
    "zgtv":"ws",
    "hen":"hen",
    "neimg":"nmgx",
    "jx":"jxx",
    "jd":"jiaod",
    "hb":"heb",
    "tj":"tj",
    "xz":"xz",
    "gz":"gzx",
    "ah":"anh",
    "cxpt":"cxpt"
};
function getSiteId(){
    //鏍规嵁鍩熷悕鍒濆鍖栫珯鐐规暟鎹?
    var siteId="";
    try{
        var url=document.URL || '';
        var regx = /(\w+)\.workercn\.cn/;
        if(regx.test(url)){
            var execArry = regx.exec(url);
            if(execArry.length>=2){
                var host = execArry[1];
                var siteId=hosts[host];
                if(typeof siteId == "undefined"){
                    siteId=host;
                }
            }
        }
    }catch(e){}
    return siteId;
};
Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //鏈堜唤
        "d+" : this.getDate(),                    //鏃?
        "h+" : this.getHours(),                   //灏忔椂
        "m+" : this.getMinutes(),                 //鍒?
        "s+" : this.getSeconds(),                 //绉?
        "q+" : Math.floor((this.getMonth()+3)/3), //瀛ｅ害
        "S"  : this.getMilliseconds()             //姣
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
};
(function() {
    var params = {};
    params["_setAccount"]="zgw";
    params["type"]="1";
    params["site"]="";
    params["catalog"]="";
    params["info"]="";
    params["editor"]="zgw";
    params["pubtime"]="";

    // Document瀵硅薄鏁版嵁
    if (document) {
        params.domain = document.domain || '';
        params.url = document.URL || '';
        params.title = document.title || '';
        params.referrer = document.referrer || '';
    }
    // Window瀵硅薄鏁版嵁
    if (window && window.screen) {
        params.sh = window.screen.height || 0;
        params.sw = window.screen.width || 0;
        params.cd = window.screen.colorDepth || 0;
    }
    // navigator瀵硅薄鏁版嵁
    if (navigator) {
        params.lang = navigator.language || '';
        params.ce = navigator.cookieEnabled ? 1 : 0;
        params.je = navigator.javaEnabled ? 1 : 0;
        var f = "";
        if (navigator.plugins && navigator.plugins.length) {
            for (var ii = 0; ii < navigator.plugins.length; ii++) {
                if (navigator.plugins[ii].name.indexOf('Shockwave Flash') != -1) {
                    f = navigator.plugins[ii].description.split('Shockwave Flash ')[1];
                    break;
                }
            }
        } else if (window.ActiveXObject) {
            for (var ii = 10; ii >= 2; ii--) {
                try {
                    var fl = eval("new ActiveXObject('ShockwaveFlash.ShockwaveFlash." + ii + "');");
                    if (fl) {
                        f = ii + '.0';
                        break;
                    }
                } catch (e) {
                }
            }
        }
        params.fe = (f == "" ? 0 : 1);
        params.fv = f;
    }
    // Cookie鏀寔鎯呭喌

    // 瑙ｆ瀽_maq閰嶇疆
    if (typeof _maq == "undefined") {
        //do nothing
    } else {
        for ( var i in _maq) {
            var key = _maq[i][0];
            switch (key) {
                case '_setAccount':
                    params.account = _maq[i][1];
                    break;
                default:
                    params[key] = _maq[i][1];
                    break;
            }
        }
    }

    if(params["site"]==""){
        //濡傛灉椤甸潰娌℃湁璁剧疆绔欑偣ID锛屽垯浠庡煙鍚嶈幏鍙栫珯鐐笽D
        params["site"] = getSiteId();
    }
    if(params["pubtime"]==""){
        var dd=document.lastModified;
        var pubTime = new Date(dd).format("yyyy-MM-dd hh:mm:ss");
        params["pubtime"] = pubTime;
    }
    // 鎷兼帴鍙傛暟涓?
    var args = '';
    for ( var i in params) {
        if (args != '') {
            args += '&';
        }
        args += i + '=' + encodeURIComponent(params[i]);
    }

    // 閫氳繃Image瀵硅薄璇锋眰鍚庣鑴氭湰
    var img = new Image(1, 1);
    img.src = 'http://s2.hton.com.cn:61906/1.gif?' + args;
})();
/*
(function() {
            var ma = document.createElement("script");
            ma.type = "text/javascript";
            ma.async = true;
            ma.src = "//stat2.hton.com.cn:61906/s.js";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(ma, s);
})();
*/
