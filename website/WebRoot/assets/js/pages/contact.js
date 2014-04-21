var Contact = function () {

    return {
        
        //Map
        initMap: function () {
			var map;
			$(document).ready(function(){
			  map = new GMaps({
				div: '#map',
				lat: -121.533313,
				lng: -31.190046
			  });
			   var marker = map.addMarker({
		            lat: -121.533313,
					lng: -31.190046,
		            title: 'huntun, Inc.'
		        });
			});
        }

    };
}();