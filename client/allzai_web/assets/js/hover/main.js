
function init_pic_hover() {
	$('.general_pic_hover').each(function() {
		if(!$(this).hasClass('initialized')) {
			$(this).append('<span class="hover"><span class="icon"></span></span>');
			$(this).addClass('initialized');
		}
		
		var no_fx = $(this).hasClass('no_fx');
		
		$(this).bind('mouseenter',function(e){
			var icon = $(this).find('.icon');
			var overlay = $(this).find('.hover');
			
			if(no_fx) {
				if(!ie8) {
					overlay.show().css('opacity', '0');
					overlay.stop(true).delay(100).animate(
						{
							opacity : 1
						}, 200
					);
				}
				else {
					overlay.css('display', 'block');
				}
			}
			else {
				overlay.show();
				
				var w = $(this).width();
				var h = $(this).height();
				var x = (e.pageX - $(this).offset().left - (w/2)) * ( w > h ? (h/w) : 1 );
				var y = (e.pageY - $(this).offset().top  - (h/2)) * ( h > w ? (w/h) : 1 );
				var direction = Math.round((((Math.atan2(y, x) * (180 / Math.PI)) + 180 ) / 90 ) + 3 )  % 4;
				
				
				/** do your animations here **/ 
				switch(direction) {
					case 0:
						/** animations from the TOP **/
						icon.css({
							'left' : '0px',
							'top' : '-100%',
							'right' : 'auto',
							'bottom' : 'auto'
						});
						icon.stop(true).delay(300).animate({
							top : 0
						}, 300);
					break;
					case 1:
						/** animations from the RIGHT **/
						icon.css({
							'left' : '100%',
							'top' : '0',
							'right' : 'auto',
							'bottom' : 'auto'
						});
						icon.stop(true).delay(300).animate({
							left : 0
						}, 300);
					break;
					case 2:
						/** animations from the BOTTOM **/
						icon.css({
							'left' : '0px',
							'top' : 'auto',
							'right' : 'auto',
							'bottom' : '-100%'
						});
						icon.stop(true).delay(300).animate({
							bottom : 0
						}, 300);
					break;
					case 3:
						/** animations from the LEFT **/
						icon.css({
							'left' : 'auto',
							'top' : '0',
							'right' : '100%',
							'bottom' : 'auto'
						});
						icon.stop(true).delay(300).animate({
							right : 0
						}, 300);
					break;
				}
			}
		});
		
		$(this).bind('mouseleave',function(e){
			var icon = $(this).find('.icon');
			var overlay = $(this).find('.hover');
			
			if(no_fx) {
				if(!ie8) {
					overlay.stop(true).animate(
						{
							opacity : 0
						}, 200
					);
				}
				else {
					overlay.css('display', 'none');
				}
			}
			else {
				var w = $(this).width();
				var h = $(this).height();
				var x = (e.pageX - $(this).offset().left - (w/2)) * ( w > h ? (h/w) : 1 );
				var y = (e.pageY - $(this).offset().top  - (h/2)) * ( h > w ? (w/h) : 1 );
				var direction = Math.round((((Math.atan2(y, x) * (180 / Math.PI)) + 180 ) / 90 ) + 3 )  % 4;
				
				
				/** do your animations here **/ 
				switch(direction) {
					case 0:
						/** animations from the TOP **/
						icon.css({
							'left' : '0px',
							'top' : '0px',
							'right' : 'auto',
							'bottom' : 'auto'
						});
						icon.stop(true).animate({
							top : -h
						}, 300, function () {overlay.hide()});
					break;
					case 1:
						/** animations from the RIGHT **/
						icon.css({
							'left' : 'auto',
							'top' : '0px',
							'right' : '0px',
							'bottom' : 'auto'
						});
						icon.stop(true).animate({
							right : -w
						}, 300, function () {overlay.hide()});
					break;
					case 2:
						/** animations from the BOTTOM **/
						icon.css({
							'left' : '0px',
							'top' : 'auto',
							'right' : 'auto',
							'bottom' : '0px'
						});
						icon.stop(true).animate({
							bottom : -h
						}, 300, function () {overlay.hide()});
					break;
					case 3:
						/** animations from the LEFT **/
						icon.css({
							'left' : '0px',
							'top' : '0px',
							'right' : 'auto',
							'bottom' : 'auto'
						});
						icon.stop(true).animate({
							left : -w
						}, 300, function () {overlay.hide()});
					break;
				}
			}
		});

	});
}

$(document).ready(function() {
	init_pic_hover();
});
