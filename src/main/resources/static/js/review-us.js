$("#review-form").validate({
    rules: {
        name: "required",
        town: "required",
        county: "required",
        testimonial: "required"
    },
    submitHandler: function(form)
    {
        // Stop form from submitting normally
        event.preventDefault();

        // Get some values from elements on the page:
        var form = $( "#review-form" );
        var url = form.attr( "action" );
        var result = $("#review-result");
        var submit = $("input[type=submit]",form);
        var loader = $("#review-loader");

        submit.attr("disabled", true);
        loader.show();

        // Send the data using post
        var posting = $.post
        (
            url,
            form.serialize()
        );

        // Put the results in a div
        posting.done(function( data )
        {
            form.slideToggle(1000);
            result.append( $(data) ).slideToggle(1000);
            loader.hide();
        });

        posting.fail(function( data )
        {
            errorContent = "<div class=\"error\" >Currently unable to send review. Please try again later</div>";
            form.slideToggle(1000);
            result.append( errorContent ).slideToggle(1000);
            loader.hide();
        });
     }
});