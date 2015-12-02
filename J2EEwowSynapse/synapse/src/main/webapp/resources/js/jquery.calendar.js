/**
 * 
 */

(function($) {
	$.fn.calendar=function(options) {
		// Default parameters
		
		var defaults = {
				"width": 800,
				"height": 700,
				"editEventRoute" : "/synapse/raids/event/edit/",
				"selectRoute" : "/synapse/raids/json/events/",
				"createEventRoute":"#event-form",
				"userGroupField":"currentUserGroup",
				"copyEventRoute":"#event-form-copy",
				"deleteEventRoute":"#event-form-delete",
				"copyEventField":"eventToCopy",
				"dateField":"eventDate",
				"dateStart" : new Date(),
				"data": [{"id":"1", "name":"SOO", "img":"/resources/img/soo.png", "date":"2014-11-29 14:00:00", "timeStart":"20:00", "timeStop":"00:00", "difficulty":"Mythic", "state":"<font color='red'>Inscriptions closes</font>"},
				         {"id":"1", "name":"Fonderie", "img":"/resources/img/soo.png", "date":"2014-11-29 16:00:00", "timeStart":"20:00", "timeStop":"00:00", "difficulty":"Mythic", "state":"<font color='green'>Inscriptions ouvertes</font>"},
				         {"id":"1", "name":"Cognefort", "img":"/resources/img/soo.png", "date":"2014-12-29 20:00:00", "timeStart":"20:00", "timeStop":"00:00", "difficulty":"Mythic", "state":"<font color='green'>Inscriptions ouvertes</font>"},
				         {"id":"1", "name":"Cognefort", "img":"/resources/img/soo.png", "date":"2014-12-30 20:00:00", "timeStart":"20:00", "timeStop":"00:00", "difficulty":"Mythic", "state":"<font color='green'>Inscriptions ouvertes</font>"}]
		};
		
		var days = ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"];
		
		var months = [{"name":"Janvier"},
		              {"name":"Février"},
		              {"name":"Mars"},
		              {"name":"Avril"},
		              {"name":"Mai"},
		              {"name":"Juin"},
		              {"name":"Juillet"},
		              {"name":"Août"},
		              {"name":"Septembre"},
		              {"name":"Octobre"},
		              {"name":"Novembre"},
		              {"name":"Décembre"}];

		var params=$.extend(defaults, options);
		var container = new Object();
		var dateToDisplay = params.dateStart;

		return this.each(function() {
			container = $(this);
			// Create the Html calendar container
			initializeHtmlContainer(container, params, days, months, dateToDisplay);
			putDaysInContainers(container, dateToDisplay);
			putDatasInContainers(container, params, dateToDisplay);
			
			// Bind the events
			bindSwitchMonth($(".JQcalendar_e_prevMonth"), $(".JQcalendar_e_nextMonth"));
		});
		
		/**
		 * Create html containers
		 * @param e : the plugin wrapper
		 * @param params : the plugin global parameters
		 * @param days : the litteral expression for days (in french here)
		 * @param months : the litteral expression for months (in french here)
		 * @param date : the default displayed date (today here)
		 */
		function initializeHtmlContainer(e, params, days, months, date) {
			var nbCase =1;
			var nbRowsMax = 6;
			var sHtml = "";
			sHtml = sHtml + "<div class='JQcalendar_header' width='"+params.width+"' align='center'>";
			sHtml = sHtml + "<font size='5' style='margin-right:10px'><span class='JQcalendar_e_prevMonth'>&lt;</span></font>";
			sHtml = sHtml + months[date.getMonth()].name + " " + date.getFullYear();
			sHtml = sHtml + "<font size='5' style='margin-left:10px'><span class='JQcalendar_e_nextMonth'>&gt;</span></font>";
			sHtml = sHtml + "</div>";
			sHtml = sHtml + "<table class='JQcalendar' width='"+params.width+"' height='"+params.height+"' align='center'>";
			sHtml = sHtml + "<thead>";
			sHtml = sHtml + "<tr>";
			for(var nbCols=0;nbCols<days.length;nbCols++) {
				sHtml = sHtml + "<th width='"+params.width/7+"'>"+days[nbCols]+"</th>";
			}
			sHtml = sHtml + "</tr>";
			sHtml = sHtml + "</thead>";
			sHtml = sHtml + "<tbody>";
			for(var nbRows=0;nbRows<nbRowsMax;nbRows++) {
				sHtml = sHtml + "<tr>";
				for(var nbCols=0;nbCols<7;nbCols++) {
					sHtml = sHtml + "<td id='case_"+nbCase+"' width='"+params.width/7+"' height='"+params.height/6+"'><span style='float:left;width:25%'>&nbsp;</span><div style='float:left;width:75%;text-align:right'>&nbsp</div><br/><br/></td>";
					nbCase++;
				}
				sHtml = sHtml + "</tr>";
			}
			sHtml = sHtml + "</tbody>";
			sHtml = sHtml + "</table>";
			e.html(sHtml);
		}
		
		/**
		 * Insert the days number in the right cases depending on the month
		 */
		function putDaysInContainers(e, _date) {
			var date = _date, y = date.getFullYear(), m = date.getMonth();
			var firstDay = new Date(y, m, 1);
			var nbDays = new Date(y, m+1, 0).getDate();
			var firstCase = firstDay.getDay() == 0 ? 7 : firstDay.getDay();
			var cptDays = 1;
			var day;
			var month;
			
			for(var nbCases=firstCase;nbCases<nbDays+firstCase;nbCases++) {
				// Put the number
				$("#case_"+nbCases+" span").html(cptDays);
				$("#case_"+nbCases+" span").attr("id", "day_" + cptDays);
				$("#case_"+nbCases+" div").attr("id", "action_" + cptDays);
				
				if(new Date(y, m, (cptDays+1)) >= new Date() && $("#"+params.userGroupField).val() < 3 && $("#"+params.userGroupField).val() > 0) {
					$("#case_"+nbCases+" div").append('<img src="/synapse/resources/img/add_event.png"/> <img src="/synapse/resources/img/copy_event.png"/> <img src="/synapse/resources/img/event_delete.png"/>');
				
					// Event creation
					$("#action_"+cptDays).children().first().addClass("fancybox");
					$("#action_"+cptDays).children().first().attr("href", params.createEventRoute);
					$("#action_"+cptDays).children().first().click(function(){
						// Determining the day
						if($(this).parent().parent().children().html().length <2) {
							day = "0" + $(this).parent().parent().children().html();
						} else {
							day = $(this).parent().parent().children().html();
						}
						// Determining the month
						if((m+1) < 10) {
							month = "0" + (m+1);
						} else {
							month = m+1;
						}
						$("#"+params.dateField).val(day + "/" + month + "/" + y);
					});
					
					// Event copy
					$("#action_"+cptDays).children().first().next().addClass("fancybox");
					$("#action_"+cptDays).children().first().next().attr("href", params.copyEventRoute);
					$("#action_"+cptDays).children().first().next().click(function(){

					});
					
					// Event delete
					$("#action_"+cptDays).children().first().next().next().addClass("fancybox");
					$("#action_"+cptDays).children().first().next().next().attr("href", params.deleteEventRoute);
				}
				$(".fancybox").fancybox();
				cptDays++;
			}
		}
		
		/**
		 * Use a JSON flux to put the datas into the containers
		 */
		function putDatasInContainers(e, params, date) {
			var events;
			var idCase;
			var hour;
			var year;
			var month;
			events = JSON.parse(params.data);
			for(var nbEvent=0;nbEvent<events.length;nbEvent++) {
				idCase = parseInt(events[nbEvent].date.split(" ")[0].split("-")[2]);
				year = parseInt(events[nbEvent].date.split(" ")[0].split("-")[0]);
				month = parseInt(events[nbEvent].date.split(" ")[0].split("-")[1])-1;
				if(month == date.getMonth() && year == date.getFullYear()) {
					$('#day_'+idCase).parent().append('<a href="'+params.editEventRoute + events[nbEvent].id+'">'
					+'<div id="'+events[nbEvent].id+'" class="JQcalendar_event" width="'+params.width/7+'">'
					+"<div class='JQcalendar_event_name'>"+events[nbEvent].name+"</div>"
					+"<div class='JQcalendar_event_hour'>"+events[nbEvent].timeStart+"</div>"
					+"<div class='JQcalendar_event_difficulty'>"+events[nbEvent].difficulty+"</div>"
					+"<div class='JQcalendar_event_state'><i>"+events[nbEvent].state+"</i></div>"
					+"</div></a>");
				}
			}
		}
		
		/**
		 * Bind (onclick) the events to play
		 */
		function bindSwitchMonth(e_previous, e_next) {
			e_previous.click(function(){
				dateToDisplay.setMonth(dateToDisplay.getMonth()-1);
				changeMonth(container, params, days, months, dateToDisplay);
			});
			
			e_next.click(function(){
				dateToDisplay.setMonth(dateToDisplay.getMonth()+1);
				changeMonth(container, params, days, months, dateToDisplay);
			});
		}
		
		/**
		 * Change the current month
		 */
		function changeMonth(container, params, days, months, date) {
			initializeHtmlContainer(container, params, days, months, date);
			putDaysInContainers(container, date);
			getData(date.getMonth()+1, date.getFullYear(), container, date);
			
		}
		
		/**
		 * Get fresh event data for the selected month
		 */
		function getData(month, year, container, date) {
			$.ajax({
				type: 'GET' ,
				url : params.selectRoute + month + '/' + year,
				success : function(_data){
					if(_data != null && _data != "" && _data != "]") {
						params.data = _data;
					}
					putDatasInContainers(container, params, date);
					// Bind the events
					bindSwitchMonth($(".JQcalendar_e_prevMonth"), $(".JQcalendar_e_nextMonth"));
				}
			});
		}
		
	};

})(jQuery);