function commodityQuery(){
	
	//创建记录类型
	var commodity = new Ext.data.Record.create([
		{name:"commodityId",mapping:"commodityId"},
		{name:"commodityName",mapping:"commodityName"},
		{name:"price",mapping:"price"},
		{name:"agio",mapping:"agio"}
	]);

	//存储器
	var store = new Ext.data.Store({
		url:"commodityQuery.action",  //地址
		reader:new Ext.data.JsonReader({
			root:"allCommodity",
			id:"commodityId",
			totalProperty:"recordeSize"
		},commodity)
	});

	var gridPanel = new Ext.grid.GridPanel({
		width:586,
		height:375,
		frame:true,
		store:store,
		columns:[
			{header:"商品Id",dateIndex:"commodityId",sortable:true},
			{header:"商品名称",dateIndex:"commodityName",sortable:true},
			{header:"价格",dateIndex:"price",sortable:true},
			{header:"折扣",dateIndex:"agio",sortable:true}
		],
		autoExpandColumn:2,
		selModel:new Ext.grid.RowSelectionModel({singleSelect:true}),
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
	store.load({params:{start:0, limit:10}});

	//新建一个window
	var commodityQueryWindow = new Ext.Window({
		width:600,
		height:400,
		title:"商品信息查询",
		resizable:false,
		modal:true,
		items:gridPanel
	});
	
	commodityQueryWindow.show();
};