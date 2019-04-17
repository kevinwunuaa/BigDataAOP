nameSpace("doc");
doc.docView = function(cfg) {
	this.swfPath = "upload/test.swf";
	this.init = function() {
		var fp = new FlexPaperViewer('FlexPaperViewer', 'viewerPlaceHolder', {
			config : {
				SwfFile : escape(this.swfPath),
				Scale : 0.6,
				ZoomTransition : 'easeOut',
				ZoomTime : 0.5,
				ZoomInterval : 0.2,
				FitPageOnLoad : true,
				FitWidthOnLoad : false,
				FullScreenAsMaxWindow : false,
				ProgressiveLoading : false,
				MinZoomSize : 0.2,
				MaxZoomSize : 5,
				SearchMatchAll : false,
				InitViewMode : 'SinglePage',

				ViewModeToolsVisible : true,
				ZoomToolsVisible : true,
				NavToolsVisible : true,
				CursorToolsVisible : true,
				SearchToolsVisible : true,

				localeChain : 'en_US'
			}
		});
	};
};