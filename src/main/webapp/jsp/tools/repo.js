nameSpace("tools");
tools.repo = function(cfg) {
	this.init = function() {
		var tree = [ {
			icon : "glyphicon glyphicon-book",
			text : "Parent 1",
			color : "#FFFFFF",
			backColor : "#82B4C1",
			nodes : [ {
				text : "Child 1",
				icon : "glyphicon glyphicon-book",
				color : "#FFFFFF",
				backColor : "#82B4C1",
				nodes : [ {
					color : "#FFFFFF",
					backColor : "#82B4C1",
					icon : "glyphicon glyphicon-file",
					text : "Grandchild 1"
				}, {
					color : "#FFFFFF",
					backColor : "#82B4C1",
					icon : "glyphicon glyphicon-file",
					text : "Grandchild 2"
				} ]
			}, {
				color : "#FFFFFF",
				backColor : "#82B4C1",
				icon : "glyphicon glyphicon-file",
				text : "Child 2"
			} ]
		}, {
			color : "#FFFFFF",
			backColor : "#82B4C1",
			icon : "glyphicon glyphicon-file",
			text : "Parent 2"
		}, {
			color : "#FFFFFF",
			backColor : "#82B4C1",
			icon : "glyphicon glyphicon-file",
			text : "Parent 3"
		}, {
			color : "#FFFFFF",
			backColor : "#82B4C1",
			icon : "glyphicon glyphicon-file",
			text : "Parent 4"
		}, {
			color : "#FFFFFF",
			backColor : "#82B4C1",
			icon : "glyphicon glyphicon-file",
			text : "Parent 5"
		} ];

		$('#tree').treeview({
			data : tree
		});

		$('#tb_departments').bootstrapTable({
			url : 'config/listUsers.action', // 请求后台的URL（*）
			method : 'get', // 请求方式（*）
			// toolbar : '#toolbar', // 工具按钮用哪个容器
			striped : true, // 是否显示行间隔色
			resizable: true,
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sortable : false, // 是否启用排序
			sortOrder : "asc", // 排序方式
			queryParams : { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
				pageSize : 20, // 页面大小
				pageNo : 1, // 页码
				orgCode : "0001"
			},// 传递参数（*）
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			//search : true, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : true,
			//showColumns : true, // 是否显示所有的列
			//showRefresh : true, // 是否显示刷新按钮
			//minimumCountColumns : 2, // 最少允许的列数
			clickToSelect : true, // 是否启用点击选中行
			height : 300, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId : "ID", // 每一行的唯一标识，一般为主键列
			//showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			//cardView : false, // 是否显示详细视图
			//detailView : false, // 是否显示父子表
			columns : [ {
				checkbox : true
			}, {
				field : 'username',
				title : '用户名'
			}, {
				field : 'personname',
				title : '姓名'
			}, {
				field : 'gender',
				title : '性别'
			}, {
				field : 'remark',
				title : '描述'
			}, ]
		});
	}

};
