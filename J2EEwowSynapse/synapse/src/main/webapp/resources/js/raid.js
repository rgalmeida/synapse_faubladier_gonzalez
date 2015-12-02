/**
 * Jquery actions for the raid plugin
 */

$(document).ready(function(){
	$.ajax({
		type: 'GET' ,
		url : '/synapse/raids/json/events/' + (new Date().getMonth() +1) + '/' + new Date().getFullYear(),
		success : function(data){
			if(data == null || data == "" || data == "]") {
				data = "[]";
			}
			$(".calendar").calendar({
				"width":800,
				"height":700,
				"selectRoute":"/synapse/raids/json/events/",
				"editEventRoute":"/synapse/raids/event/edit/",
				"createEventRoute":"#event-form",
				"userGroupField":"currentUserGroup",
				"copyEventRoute":"#event-form-copy",
				"deleteEventRoute":"#event-form-delete",
				"copyEventField":"eventToCopy",
				"dateField":"eventDate",
				"data":data
			});
		}
	});
	
	$("#raid").change(function(){
		$.ajax({
			type: 'GET' ,
			url : '/synapse/raids/json/event/acronym/' + $("#raid").val(),
			success : function(data){
				$("#eventHexagram").val(data);
			}
		});
	});
	
	$("#raid").change(function(){
		$.ajax({
			type: 'GET' ,
			url : '/synapse/raids/json/event/image/' + $("#raid").val(),
			success : function(data){
				if(data != null && data !="") {
					$("#event-image").html('<img src="/synapse'+data+'"/>');
				} else {
					$("#event-image").html("<img src='/synapse/resources/img/raid_door.jpg'/>");
				}
			}
		});
	});
	
});