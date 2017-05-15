function payOutQueryFn(){
	
	//记录类型
	var payOut = Ext.data.Record.create([
		{name:"id",mapping:"id"},
		{name:"payOutName",mapping:"payOutName"},
		{name:"payOutMoney",mapping:"payOutMoney"},
		{name:"payOutDate",mapping:"payOutDate",type:"date",dateFormat:"Y-m-dTH:i:s"}
	]);

	//存储器
	var store = new Ext.data.Store({
		url:"queryPayOut.action",
		reader:new Ext.data.JsonReader({
			root:"allPayOut",
			id:"id",
			totalProperty:"recordSize"
		},payOut)
	});


	//查询表单
	var queryForm = new Ext.FormPanel({
		labelAlign:"right",
		baseCls: 'x-plain',
		layout:"column",
		items:[
			new Ext.form.Label({
				text:"查询条件:"
			}),
			new Ext.form.ComboBox({
				store:new Ext.data.SimpleStore({
							fields:["queryL","queryV"],
							data:[
								["支出名称","payOutName"],
								["支出金额","payOutMoney"]
							]
				}),
				triggerAction:"all",
				selectOnFocus:true,
				typeAhead : true,
				displayField:"queryL",
				hiddenName:"queryCondition",
				valueField:"queryV",
				mode:"local",
				readOnly: true
			}),
			new Ext.form.Label({
				text:"查询值"
			}),
			new Ext.form.TextField({
				id:"queryValue",
				name:"queryValue"
			})
		]
	})
	var gridPanel = new Ext.grid.GridPanel({
		width:300,
		height:200,
		frame:true,
		store:store,
		columns:[
			{header:"支出名称",dataIndex:"payOutName",sortable:true
			},
			{header:"支出金额",dataIndex:"payOutMoney",sortable:true
			},
			{header:"支出日期",dataIndex:"payOutDate",sortable:true,renderer:new Ext.util.Format.dateRenderer("Y年m月d日")
			}
		],
		autoExpandColumn:2,
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
		}),
		selModel:new Ext.grid.RowSelectionModel({singleSelect:false}),
		tbar:[
			queryForm,
			{text:"查询",handler:function(){
				store.load({
					params:
						{
							start:0, limit:10,
							queryCondition:Ext.get("queryCondition").dom.value, //查询条件
							queryValue:Ext.get("queryValue").dom.value   //查询值
						}});
			}}
		]
	});

	store.load({params:{start:0, limit:10}});
	if(!payOutQueryPageIsOpen) {
		var tabPage = tabPanel.add({
						title:"支出查询",
						height:400,
						closable:true,
						layout:"fit",
						items:[
							gridPanel
						],
						listeners:{
							beforedestroy:function(){
								payOutQueryPageIsOpen = false;
							}
						}
					});
		tabPanel.setActiveTab(tabPage);
		payOutQueryPageIsOpen = true;
	}
}