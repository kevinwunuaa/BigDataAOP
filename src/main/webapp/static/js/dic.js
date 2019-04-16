var appContext = window.top.appContext;

// 读取xml字典
function dicItems(dicId) {
	if (appContext.dicMap[dicId]) {
		return appContext.dicMap[dicId];
	}
	var dicPath = "../static/xml/" + dicId + ".xml";
	var items = [];
	$.ajax({
		type : "get",
		url : dicPath,// 这里通过设置url属性来获取xml
		async : false,
		dataType : "xml",
		timeout : 1000, // 设定超时
		cache : false, // 禁用缓存
		success : function(xml) {// 这里是解析xml
			/*
			 * $(xml).find("item").each(function(i){ var id =
			 * $(this).attr("key"); var text = $(this).attr("text"); var node =
			 * {id : id,text : text}; items.push(node); });
			 */
			$(xml).find("dic").each(function(i) {
				var roots = $(this).children("item");
				for (var i = 0; i < roots.length; i++) {
					var n = roots[i];
					var node = getNode(n);
					items.push(node);
				}
			});
		}
	});

	appContext.dicMap[dicId] = items;
	return items;
}


function getNode(item) {
	var node = {
		id : $(item).attr("key"),
		text : $(item).attr("text")
	};
	var children = $(item).children("item");
	if (children == null || children.length <= 0) {
		return node;
	}

	node.state = "closed";
	node.children = [];

	for (var i = 0; i < children.length; i++) {
		var n1 = children[i];
		var node1 = getNode(n1);
		node.children.push(node1);
	}

	return node;
}

//获取机构字典
function getOrganTree() {
	var dicId = "organTree";
	if (appContext.dicMap[dicId]) {
		return appContext.dicMap[dicId];
	}
	var items = null;
	$.ajax({
		type : "get",
		url : "orgTree.action",
		async : false,
		dataType : "json",
		timeout : 1000, // 设定超时
		cache : false, // 禁用缓存
		success : function(data) {
			items = data;
		}
	});

	appContext.dicMap[dicId] = items;
	return items;
}

function getRemoteData(remoteUrl,params,cacheId,isCache){
	if(isCache){
		if(!appContext.remoteData){
			appContext.remoteData = {};
		}
		if (appContext.remoteData[cacheId]) {
			return appContext.remoteData[cacheId];
		}
	}
	
	var result = null;
	$.ajax({
		type : "get",
		url : remoteUrl,
		data : params,
		async : false,
		dataType : "json",
		timeout : 1000, // 设定超时
		cache : false, // 禁用缓存
		success : function(data) {
			result = data;
		}
	});
	
	if(isCache){
		appContext.remoteData[cacheId] = result;
	}
	return result;
}

// 字典格式化
function fmtDic(dicId, key) {
	var items = dicItems(dicId);
	return dicText(items, key);
}

// 获取字典文本
function dicText(items, key) {
	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		if (item.id == key) {
			return item.text;
		}
		if (item.children && item.children.length > 0) {
			var dt =  dicText(item.children, key);
			if(dt != key){
				return dt;
			}
		}
	}
	return key;
}
