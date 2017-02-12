$("#contact-us-form").validate({
    rules: {
        email: {
            required: {
                depends: function(element) {
                    return !$("#phone").val();
                }
            },
            email: true
        },
        subject: "required",
        message: "required"
    },
    messages: {
        email: {
          required: "Please provide either a phone number or email address to be contacted on.",
        }
    },
    submitHandler: function(form)
    {
        // Stop form from submitting normally
        event.preventDefault();

        // Get some values from elements on the page:
        var form = $( "#contact-us-form" );
        var url = form.attr( "action" );
        var result = $("#contact-result");
        var submit = $("#submit");

        submit.attr("disabled", true);

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
        });

        posting.fail(function( data )
        {
            errorContent = "<div class=\"error\" >Currently unable to send query. Please call 07860681642</div>";
            form.slideToggle(1000);
            result.append( errorContent ).slideToggle(1000);
        });
     }
});