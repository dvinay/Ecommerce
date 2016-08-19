
$(document).ready(function() {
    $.ajax({url: "servlet/QueryGoods",
        success: function(data) {
            $("#box").html(data);
        }});


});

function detail(id) {
    window.open("servlet/QueryGood?id=" + id);

}