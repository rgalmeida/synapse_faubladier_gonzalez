/**
 * Ajax Treatments
 */

$(document).ready(function(){
	$("#race").change(function(){
		getClassesForRace($(this).val());
	});
	
	$("#classe").change(function(){
		getSpecsForClass($(this).val());
	});

});

//Appel AJAX pour les listes chainées
function getClassesForRace(idRace){
	if(idRace!=""){
		$.ajax({
			type: 'GET' ,
			url : '/synapse/user/classes/' + idRace,
			success : function(data){
				var classes = jQuery.parseJSON(data);
				var sHtml = '<select id="classe" name="clazz.id" class="select">';
				sHtml = sHtml + '<option value="0">Choisissez votre classe</option>';
				for(var i=0; i<classes.length; i++) {
					sHtml = sHtml + '<option value="'+classes[i].id+'">'+classes[i].name+'</option>';
				}
				sHtml = sHtml + '<select id="classe" name="clazz.id" class="select">';
				$("#field-classe").html(sHtml);
				$("#classe").change(function(){
					getSpecsForClass($(this).val());
				});
			}
		});
	}
}

//Appel AJAX pour les listes chainées
function getSpecsForClass(idClass){
	if(idClass!=""){
		$.ajax({
			type: 'GET' ,
			url : '/synapse/user/specs/' + idClass,
			success : function(data){
				var specs = jQuery.parseJSON(data);
				var sHtml = '<select id="specialization" name="spec.id" class="select">';
				sHtml = sHtml + '<option value="0">Choisissez votre spécialisation</option>';
				for(var i=0; i<specs.length; i++) {
					sHtml = sHtml + '<option value="'+specs[i].id+'">'+specs[i].name+'</option>';
				}
				sHtml = sHtml + '<select id="classe" name="clazz.id" class="select">';
				$("#field-specialisation").html(sHtml);
			}
		});
	}
}