
$(document).ready(function(){
	nicEditors.allTextAreas();
	var fieldsToCheck = new Array('newPostTitle', 'post-content');
	
	$("#forum-post-new-form").submit(function(e){
		if(!checkRequiredFields(fieldsToCheck)) {
			alert('Tous les champs sont obligatoires');
			e.preventDefault();
		}
	});

});

function checkRequiredFields(fields) {
	var validate = true;
	for(var i=0;i<fields.length;i++) {
		if($.trim($("#"+fields[i]).val()) == null || $.trim($("#"+fields[i]).val()) == '') {
			validate = false;
		}
	}
	return validate;
}