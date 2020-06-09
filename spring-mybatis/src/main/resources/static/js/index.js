let index = {
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
        window.location.href = "/delete?id="+id;

    }
}