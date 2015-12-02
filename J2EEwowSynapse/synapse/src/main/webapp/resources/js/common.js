/**
 * 
 */

$(document).ready(function(){
	
	// Datepickers
	if($(".date").length > 0) {
		$(".date").datetimepicker({
			lang:'fr',
			timepicker:false,
			format:'d/m/Y',
			maxDate:0
		});
	}
	
	// DateAndTimePickers
	if($(".dateTime").length > 0) {
		$(".dateTime").datetimepicker({
			lang:'fr',
			timepicker:true,
			format:'d/m/Y H:i',
			maxDate:0
		});
	}
	
	// TimePickers
	if($(".time").length > 0) {
		$(".time").datetimepicker({
			lang:'fr',
			datepicker:false,
			timepicker:true,
			format:'H:i',
			maxDate:0
		});
	}
	
	// Fancyboxes
	if($(".fancybox").length > 0) {
		$(".fancybox").fancybox({
			openEffect:'fade',
			closeEffect:'fade',
			openSpeed:'slow',
			closeSpeed:'slow'
		});
	}

});
