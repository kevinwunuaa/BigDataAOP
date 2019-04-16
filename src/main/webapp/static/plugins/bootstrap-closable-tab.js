var closableTab = {
	//frame加载完成后设置父容器的高度，使iframe页面与父页面无缝对接
	frameLoad:function (iframe){
			
			try { 
				var viewHeight = window.innerHeight || document.documentElement.clientHeight || document.body.offsetHeight; 
				var headHeight = $("header").height();
				var tabHeight = $(".custom-nav-tabs").height();
				var iframeHeight = viewHeight - headHeight - tabHeight - 5;
				var bHeight = iframe.contentDocument.body.scrollHeight;
				var dHeight = iframe.contentDocument.documentElement.scrollHeight;
				var height = Math.max(bHeight, dHeight);
				height = bHeight;
				height = height > iframeHeight ? iframeHeight : height;
				//alert("viewHeight=" + viewHeight + ",headHeight=" + headHeight + ",tabHeight=" + tabHeight + ",iframeHeight=" + height)
				$(iframe).parent().height(height);
			} catch (ex) {
			}
		},

    //添加tab
	addTab:function(tabItem){ //tabItem = {id,name,url,closable}

		var id = "tab_seed_" + tabItem.id;
		var container ="tab_container_" + tabItem.id;
		var frame = "tab_frame_" + tabItem.id;

		$("li[id^=tab_seed_]").removeClass("active");
		$("div[id^=tab_container_]").removeClass("active");

		if(!$('#'+id)[0]){
			var li_tab = '<li role="presentation" class="" id="'+id+'"><a href="#'+container+'"  role="tab" data-toggle="tab" style="position: relative;padding:2px 20px 2px 15px">'+tabItem.name;
			if(tabItem.closable){
				li_tab = li_tab + '<i class="glyphicon glyphicon-remove small" tabclose="'+id+'" style="position: absolute;right:4px;top: 4px;"  onclick="closableTab.closeTab(this)"></i></a></li> ';
			}else{
				li_tab = li_tab + '</a></li>';
			}
		
		 	var tabpanel = '<div role="tabpanel" class="tab-pane" id="'+container+'" style="width: 100%;">'+
	    					  '<iframe src="'+tabItem.url+'" id="' + frame + '" frameborder="0" style="overflow-x: scroll; overflow-y: scroll;width:100%;height: 100%"  onload="closableTab.frameLoad(this)"></iframe>'+
	    				   '</div>';


			$('.custom-nav-tabs').append(li_tab);
			$('.custom-tab-content').append(tabpanel);
		}
		$("#"+id).addClass("active");
		$("#"+container).addClass("active");
	},

	//关闭tab
	closeTab:function(item){
		var val = $(item).attr('tabclose');
		var containerId = "tab_container_"+val.substring(9);
   	    
   	    if($('#'+containerId).hasClass('active')){
   	    	$('#'+val).prev().addClass('active');
   	    	$('#'+containerId).prev().addClass('active');
   	    }


		$("#"+val).remove();
		$("#"+containerId).remove();
	}
}