function vipQuery(){
	//记录类型
	var vip = new Ext.data.Record.create([
		{name:"vipId",mapping:"vipId"},
		{name:"name",mapping:"name"},
		{name:"age",mapping:"age"},
		{name:"profession",mapping:"profession"},
		{name:"joinTime",mapping:"joinTime",type:"date",dateFormat:"Y-m-dTH:i:s"}
	]);

	//存储器
	var store = new Ext.data.Store({
		url:"vipQuery.action",
		reader:new Ext.data.JsonReader({
			id:"vipId",
			root:"allVip",
			totalProperty:"recordSize"
		},vip)
	});

	var gridPanel = new Ext.grid.GridPanel({
		width:585,
		height:375,
		frame:true,
		store:store,
		columns:[
			{header:"VIPID",dataIndex:"vipId",sortable:true},
			{header:"姓名",dataIndex:"name",sortable:true},
			{header:"年龄",dataIndex:"age",sortable:true},
			{header:"职业",dataIndex:"profession",sortable:true},
			{header:"入会时间",dataIndex:"joinTime",sortable:true,renderer:new Ext.util.Format.dateRenderer("Y年m月d日")}
		],
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

	var vipQueryWindow = new Ext.Window({
		width:600,
		height:400,
		title:"VIP信息查询",
		modal:true,
		resizable:false,
		items:gridPanel
	});	
	
	vipQueryWindow.show();
};