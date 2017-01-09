$( "#contact-us-form" ).submit(function( event ) {

    // Stop form from submitting normally
    event.preventDefault();

    // Get some values from elements on the page:
    var $form = $( this ),
      url = $form.attr( "action" );

    // Send the data using post
    var posting = $.post(
            url,
            $( "#contact-us-form" ).serialize()
     );

    // Put the results in a div
    posting.done(function( data )
    {
      var content = $( data ).find( "#content" );
      $( "#result" ).empty().append( content );
    });
});