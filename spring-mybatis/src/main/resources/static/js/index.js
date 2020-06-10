var index = {
    logout : function(){
        window.location.href = "/logout";
    },
    add : function() {
        window.location.href="/add";
    },
    edit : function (id) {
        window.location.href = "/edit?id="+id;
    },
    del : function (id) {
        if(window.confirm("确定删除?")){
            window.location.href = "/delete?id="+id;
        }
    },
    personList : function () {
        window.location.href = "/person/findByPage";
    },
    index : function () {
        window.location.href = '/index';
    }
}