$(function() {
    $("#navbar li a").bind("click", function(event) {
        var $anchor = $(this);

        $("#navbar li.active").removeClass("active");
        $anchor.parent().addClass("active");

        if($($anchor.attr('href')).offset())
        {
            $('html, body').stop().animate({
                scrollTop: $($anchor.attr('href')).offset().top
            }, 1000);
        }
        else
        {
            $('html, body').stop().animate({
                scrollTop: 0
            }, 1000);
        }
    });
});

$(function(){
    $(".toggle").bind("click", function(event){
        var parent = $(this);
        var childId = parent.data("toggle");
        var child = document.getElementById(childId);
        $(child).slideToggle(500);
    });
});