function consumeQuery(){
	//创建数据类型
	var consume = new Ext.data.Record.create([
		{name:"consumeId",mapping:"consumeId"},
		{name:"vipId",mapping:"vip.vipId"},
		{name:"name",mapping:"name"},
		{name:"commodityId",mapping:"commodity.commodityId"},
		{name:"commodityName",mapping:"commodityName"},
		{name:"price",mapping:"price"},
		{name:"practicePrice",mapping:"practicePrice"}
	]);

	//存储器
	var store = new Ext.data.Store({
		url:"consumeQuery.action",
		reader:new Ext.data.JsonReader({
			id:"consumeId",
			root:"allConsume",
			totalProperty:"recordSize"
		},consume)
	});

	var gridPanel = new Ext.grid.GridPanel({
		width:585,
		height:375,
		frame:true,
		store:store,
		columns:[
			{header:"vipId",dataIndex:"vipId",sortable:true},
			{header:"姓名",dataIndex:"name",sortable:true},
			{header:"商品Id",dataIndex:"commodityId",sortable:true},
			{header:"商品Name",dataIndex:"commodityName",sortable:true},
			{header:"价格",dataIndex:"price",sortable:true},
			{header:"实际价格",dataIndex:"practicePrice",sortable:true}
		],
		autoExpandColumn:2,
		selModel:new Ext.grid.RowSelectionModel({singleSelect:true}),
		//分页控制条
		bbar:new Ext.PagingToolbar({
			pageSize:10,//每页显示多少条记录
			store:store,//数据源
			displayInfo:true,
			displayMsg:"当前显示第{0}条到{1}条记录，一共有{2}条记录",
			emptyMsg:"没有任何记录",
			firstText:"首页",
			prevText:"上一页",
			nextText:"下一页",
			lastText:"尾页",
			refreshText:"刷新"
		})
	});

	store.load({params:{start:0,limit:10}});

	//新建一个window
	consumeQueryWindow = new Ext.Window({
		width:600,
		height:400,
		title:"VIP消费信息查询",
		modal:true,
		resizable:false,
		items:gridPanel
	});
	consumeQueryWindow.show();
}