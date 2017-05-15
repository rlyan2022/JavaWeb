
function payOutManageFn(){
	
	//记录类型
	var payOut = Ext.data.Record.create([
		{name:"id",mapping:"id"},
		{name:"payOutName",mapping:"payOutName"},
		{name:"payOutMoney",mapping:"payOutMoney"},
		{name:"payOutDate",mapping:"payOutDate",type:"date",dateFormat:"Y-m-dTH:i:s"}
	]);

	//存储器
	var store = new Ext.data.Store({
		url:"getPayOut.action",
		reader:new Ext.data.JsonReader({
			root:"allPayOut",
			id:"id",
			totalProperty:"recordSize"
		},payOut)
	});

	var sm = new Ext.grid.CheckboxSelectionModel();
	var gridPanel = new Ext.grid.EditorGridPanel({
		width:300,
		height:200,
		frame:true,
		store:store,
		columns:[
			sm,
			{header:"支出名称",dataIndex:"payOutName",sortable:true,
				editor:new Ext.form.TextField({
					id:"payOutName",
					name:"payOutName",
					fieldLabel:"支出名称",
					minLength:2,
					minLengthText:"支出名称长度不能小于2个字符",
					maxLength:12,
					maxLengthText:"支出名称长度不能大于12个字符",
					allowBlank:false,
					blankText:"支出名称不能为空"
				})
			},
			{header:"支出金额",dataIndex:"payOutMoney",sortable:true,
				editor:new Ext.form.NumberField({
					id:"payOutMoney",
					name:"payOutMoney",
					fieldLabel:"支出金额",
					allowNegative : false,//不允许输入负数
					nanText :'请输入有效的整数',//无效数字提示
					allowDecimals : true,//允许输入小数
					maxValue : 10000,//最大值
					minValue : 0,//最小值
					minText:"支出金额不能小于{0}元",
					maxText:"支出金额不能大于{0}元"
				})
			},
			{header:"支出日期",dataIndex:"payOutDate",sortable:true,renderer:new Ext.util.Format.dateRenderer("Y年m月d日"),
				editor:new Ext.form.DateField({
				id:"payOutDate",
				name:"payOutDate",
				width:130,
				fieldLabel:"支出日期",
				maxValue:"12/31/2009",
				minValue:"01/01/2009",
				maxText:"支出日期不能大于{0}",
				minText:"支出日期不能小于{0}",
				format:"Y年m月d日"
			})
			}
		],
		autoExpandColumn:3,
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
		sm:sm,
		tbar:[
			{
				text:"删除",
				handler:function(){
					//获得选择的行，可以是多行
	 				var rows = gridPanel.getSelectionModel().getSelections();
	 				//如果没有选择任何行，则提示警告西悉尼
	 				if(rows.length == 0) {
	 					Ext.MessageBox.alert("警告","请选择一行数据进行删除");
	 				} else {
	 					//弹出确定提示框
	 					Ext.MessageBox.confirm("提示框","是否确定要进行删除!",function(btn){
	 						//只有在确认的情况下，才进行删除
	 						if(btn == "yes") {
	 							//首先将第一个id赋值给ids。
	 							var ids = rows[0].id;
	 							//如果还存在多行，则都赋值给ids
	 							for(var i = 1; i < rows.length; i++) {
	 								ids = ids + "," + rows[i].id;
	 							}
	 							//发送异步请求
	 							Ext.Ajax.request({
	 								url:"deletePayOut.action",//请求地址
	 								params:{id:ids},      //参数，id
	 								success:function(result){  //与服务器交互成功
	 									var jsonStr = Ext.util.JSON.decode(result.responseText)
	 									Ext.MessageBox.alert("成功",jsonStr.msg);
	 								},
	 								failure:function(result){ //与服务器交互不成功
	 									var jsonStr = Ext.util.JSON.decode(result.responseText)
	 									Ext.MessageBox.alert("失败",jsonStr.msg);
	 								}
	 							});

	 							//进行删除操作
	 							for(var i = 0; i < rows.length; i++) {
	 								store.remove(rows[i]);
	 							}
	 						}
	 					});
	 				}
				}
			}
		]
	});

	store.load({params:{start:0, limit:10}});
	//给EditorGridPanel添加afteredit事件处理代码
	gridPanel.on("afteredit",function(obj){
		//必须知道的信息
		//1.支出信息的id，方便服务器来查询该id对应的支出记录
		var id = obj.record.get("id");

		//2.修改的哪个字段
		var field = obj.field;

		//3.修改后的值是多少
		var value = obj.value;

		if(field == "payOutDate") {
			value = Ext.util.Format.date(value,"Y年m月d日");
		}

		//发送异步请求
		Ext.Ajax.request({
			//1.请求的地址
			url:"updatePayOut.action",
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

	if(!payOutManagePageIsOpen){
		var tabPage = tabPanel.add({
						title:"支出管理",
						height:400,
						closable:true,
						layout:"fit",
						items:[
							gridPanel
						],
						listeners:{
							beforedestroy:function(){
								payOutManagePageIsOpen = false;
							}
						}
					});
		tabPanel.setActiveTab(tabPage);
		payOutManagePageIsOpen = true;
	}
}