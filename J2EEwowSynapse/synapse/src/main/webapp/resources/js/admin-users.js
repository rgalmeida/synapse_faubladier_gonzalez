/**
 * Admin.jsp
 */

$(document).ready(function(){
	$('#admin-users').dynatable({
		features: {
			recordCount: false,
		    perPageSelect: false
		  }
	});
});