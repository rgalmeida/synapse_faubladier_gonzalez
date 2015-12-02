$(document).ready(function(){
	$(".forum-name").click(function(){
		$(this).next().next().slideToggle("normal");
		$(this).next().slideToggle("normal");
	});
});