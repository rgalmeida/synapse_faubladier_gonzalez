/**
 * Calling the slider
 */

$(document).ready(function(){
	$('.slider').nivoSlider({
	    effect: 'fade',               // Specify sets like: 'fold,fade,sliceDown'
	    animSpeed: 1000,                 // Slide transition speed
	    pauseTime: 7000,                // How long each slide will show
	    startSlide: 0,                  // Set starting Slide (0 index)
	    directionNav: false,             // Next & Prev navigation
	    controlNav: true,               // 1,2,3... navigation
	    controlNavThumbs: false,        // Use thumbnails for Control Nav
	    pauseOnHover: true,             // Stop animation while hovering
	    manualAdvance: false,           // Force manual transitions
	    prevText: 'Prev',               // Prev directionNav text
	    nextText: 'Next',               // Next directionNav text
	    randomStart: false,             // Start on a random slide
	    beforeChange: function(){},     // Triggers before a slide transition
	    afterChange: function(){},      // Triggers after a slide transition
	    slideshowEnd: function(){},     // Triggers after all slides have been shown
	    lastSlide: function(){},        // Triggers when last slide is shown
	    afterLoad: function(){}         // Triggers when slider has loaded
	});
});
