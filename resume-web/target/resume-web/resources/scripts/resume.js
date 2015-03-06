/**
 * resume.js - For retreiving individual parts of resume
 */

$(function() {
	function getData(property, toString) {
		$.ajax({
			url : $("body").data("page-context") + '/resume/' + $("body").data("resume-id") + '/' + property,
			accept : 'application/json',
			context : $("#" + property),
		}).done(function(data) {
			$(this).html("");
			$(this).append("<ul>");
			for ( var i = 0; i < data.length; i++ ) {
				$(this).append("<li>" + toString(data[i]) + "</li>");
			}
			$(this).append("</ul>");
		});
	}
	
	getData('degrees', function(data) {
		var template = '<span class="institution">$institution$</span> - <span class="study">$degree$ in $areaOfStudy$</span>, $month$/$year$';
		var date = new Date(data.graduation);
		return template.replace("$institution$", data.institution).replace("$degree$", data.degree).replace("$areaOfStudy$", data.areaOfStudy)
			.replace("$month$", 1 + date.getMonth()).replace("$year$", 1900 + date.getYear());
	});
	
	getData('skills', function(data) {
		var template = '<span class="skill">$skill$</span> <span class="years">($years$ years)</span>';
		return template.replace("$skill$", data.skill).replace('$years$', data.years);
	});
	
	getData('jobs', function(data) {
		var template = '<table><tr><td class="company"><ul><li class="name">$company$</li><li class="position">$position$</li><li class="dates">$startMonth$/$startYear$ - $endMonth$/$endYear$</li></ul></td>';
		var startDate = new Date(data.startDate);
		var endDate = new Date(data.endDate);
		var responsibilities = '<td class="responsibilities"><ul>';
		for ( var i = 0; i < data.responsibilities.length; i++ ) {
			var duty = data.responsibilities[i];
			responsibilities += "<li>" + duty.description + "</li>";
		}
		responsibilities += "</ul></td></tr></table>";
		return template.replace("$company$", data.company).replace("$position$", data.position).replace("$startMonth$", 1 + startDate.getMonth())
			.replace("$startYear$", 1900 + startDate.getYear()).replace("$endMonth$", 1 + endDate.getMonth()).replace("$endYear$", 1900 + endDate.getYear())
		 + responsibilities;
	});
});