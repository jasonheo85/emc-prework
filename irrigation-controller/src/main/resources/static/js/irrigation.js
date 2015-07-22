$(document).ready(function() {
	$('#waterOrNotButton').click(function() {
	    var zipCode = $('#zipCode').val()
	    $.ajax({
	    	url: "/weather?zip=" + zipCode
	    }).then(function(data) {
	    	if(data.waterOrNot) $('#waterOrNot').html("Water Or Not: Yes")
	    	else                $('#waterOrNot').html("Water Or Not: No")
	    	$('#zip').html("Zip: " + data.zip)
	    	$('#prep').html("Precipitation: " + data.precipitation + "%")
	    	$('#message').html("Message: " + data.message)
	    });
	});
});