/**
 * JS Page for the Videos JSP
 */

$(document).ready(function() {
	// Module de recherche vidéo
	$(".findSomeone").keyup(function () {
	    var filter = $(this).val(), count = 0;
	    $(".mix").each(function () {
	    	var name = $(this).data("name");
	        if (name.search(new RegExp(filter, "i")) < 0) {
        		$(this).hide(300);
	        } else {
	        	$(this).show(300);
	            count++;
	        }
	    });
	});
});
