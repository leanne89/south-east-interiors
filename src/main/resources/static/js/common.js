$(function() {
    $('#navbar li a').bind('click', function(event) {
        var $anchor = $(this);
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