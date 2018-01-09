$(function() {

    $("span.buy-btn").click(function (e) {

        e.preventDefault();

        //var url = "/order/add/";
        var id = $(this).data("id");
        //console.log("url: " + url);
        console.log("id: " + id);
        $.ajax({
            type: "GET",
            url: "/order/add/" + id,
            //data: { id: id},
            success: function(data){
                //update cart data
            }
        });

    });
});