function userQueryFn(){
	//记录类型
	var user = Ext.data.Record.create([
		{name:"id",mapping:"id"},
		{name:"username",mapping:"username"},
		{name:"password",mapping:"password"}
	]);

	//存储器
	var store = new Ext.data.Store({
		url:"queryUser.action",
		reader:new Ext.data.JsonReader({
			root:"allUser",
			id:"id",
			totalProperty:"recordSize"
		},user)
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
								["id","id"],
								["用户名","username"],
								["密码","password"]
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

	var gridPanel = new Ext.grid.EditorGridPanel({
		width:300,
		height:200,
		frame:true,
		store:store,
		columns:[
			{header:"id",dataIndex:"id",sortable:true},
			{header:"用户名",dataIndex:"username",sortable:true,
				editor:new Ext.form.TextField({
					minLength:3,
					minLengthText:"用户名长度不能小于3个字符",
					maxLength:12,
					maxLengthText:"用户名长度不能大于12个字符",
					allowBlank:false,
					blankText:"用户名不能为空"
				})
			},
			{header:"密码",dataIndex:"password",sortable:true,
				editor:new Ext.form.TextField({
					minLength:3,
					minLengthText:"密码长度不能小于3个字符",
					maxLength:12,
					maxLengthText:"密码长度不能大于12个字符",
					allowBlank:false,
					blankText:"密码不能为空"
				})
			}
		],
		autoExpandColumn:1,
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
							queryCondition:Ext.get("queryCondition").dom.value,
							queryValue:Ext.get("queryValue").dom.value
						}});
			}}
		]
	});

	store.load({params:{start:0, limit:10}});

	//给EditorGridPanel添加afteredit事件处理代码
	gridPanel.on("afteredit",function(obj){
		//必须知道的信息
		//1.用户信息的id，方便服务器来查询该id对应的用户记录
		var id = obj.record.get("id");

		//2.修改的哪个字段
		var field = obj.field;

		//3.修改后的值是多少
		var value = obj.value;

		//发送异步请求
		Ext.Ajax.request({
			//1.请求的地址
			url:"updateUser.action",
			//2.请求的方式
			method:"post",
			//3.请求参数
			params:{
				id:id,
				field:field,
				value:value
			},
			callback:function(options,success,response) {
				var jsonStr = Ext.util.JSON.decode(response.responseText);
				if(jsonStr.success) {
					obj.record.commit();
				}else {
					Ext.MessageBox.alert("失败",jsonStr.msg);
					obj.record.reject();
				}
			}


		});
	});
	if(!userQueryPageIsOpen){
		var tabPage = tabPanel.add({
						title:"用户查询",
						height:400,
						closable:true,
						layout:"fit",
						items:[
							gridPanel
						],
						listeners:{
							beforedestroy:function(){
								userQueryPageIsOpen = false;
							}
						}
					});
		tabPanel.setActiveTab(tabPage);
		userQueryPageIsOpen= true;
	}
}