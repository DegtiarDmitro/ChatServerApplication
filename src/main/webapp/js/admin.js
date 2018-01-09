$(function() {

    $("a.delete-item").click(function (e) {

        e.preventDefault();

        if(confirm("Do you really want to delete")){
            var url = $(this).attr("href");
            var id = $(this).data("id");
            $.ajax({
                type: "POST",
                url: url,
                data: { id: id},
                success: function(data){
                    location.reload();
                }
            });
        }

    });
});