nameSpace("tools");
tools.album = function(cfg) {
	this.init = function() {
		var data = [
				{
					url : "photo/park.jpg",
					title : "Thumbnail label",
					remark : "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magnaaliqua."
				},
				{
					url : "photo/bridge.jpg",
					title : "Thumbnail label",
					remark : "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magnaaliqua."
				},
				{
					url : "photo/tunnel.jpg",
					title : "Thumbnail label",
					remark : "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magnaaliqua."
				},
				{
					url : "photo/coast.jpg",
					title : "Thumbnail label",
					remark : "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magnaaliqua."
				},
				{
					url : "photo/rails.jpg",
					title : "Thumbnail label",
					remark : "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magnaaliqua."
				},
				{
					url : "photo/traffic.jpg",
					title : "Thumbnail label",
					remark : "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magnaaliqua."
				} ];

		var app = new Vue({
			el : '#app',
			data : {
				photos : data
			}
		});

		baguetteBox.run('.tz-gallery');
	}
};