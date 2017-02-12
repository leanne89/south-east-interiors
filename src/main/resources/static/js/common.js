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

    // get the value of the top of each section
    var about = $('#about').offset().top - 101;
    var gallery = $('#gallery').offset().top - 101;
    var services = $('#services').offset().top - 101;
    var contact = $('#contact').offset().top - 101;
    var reviews = $('#reviews').offset().top - 101;

    // on scroll,
    $(window).on('scroll',function(){

        // we round here to reduce a little workload
        var stop = Math.round($(window).scrollTop());

        if (stop > reviews) {
            $("#navbar li.active").removeClass("active");
            $("#navbar li#nav-reviews").addClass("active");
        }
        else if (stop >= contact) {
            $("#navbar li.active").removeClass("active");
            $("#navbar li#nav-contact").addClass("active");
        }
        else if (stop >= services) {
           $("#navbar li.active").removeClass("active");
           $("#navbar li#nav-services").addClass("active");
        }
        else if (stop >= gallery) {
          $("#navbar li.active").removeClass("active");
          $("#navbar li#nav-gallery").addClass("active");
        }
        else if (stop >= about) {
            $("#navbar li.active").removeClass("active");
            $("#navbar li#nav-about").addClass("active");
        }
        else {
            $("#navbar li.active").removeClass("active");
            $("#navbar li#nav-home").addClass("active");
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