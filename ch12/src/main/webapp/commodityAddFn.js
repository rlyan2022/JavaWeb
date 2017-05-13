function commodityAdd() {
	//创建表单
	var formPanel = new Ext.form.FormPanel({
		bodyStyle:"padding-left:50px",
		width:400,
		frame:true,
		labelAlign:"right",
		monitorValid:true,
		items:[
			new Ext.form.TextField({
				id:"commodityName",
				name:"commodityName",
				fieldLabel:"商品名称",
				minLength:2,
				minLengthText:"商品名称长度不能小于2个字符",
				maxLength:12,
				maxLengthText:"商品名称长度不能大于12个字符",
				allowBlank:false,
				blankText:"商品名称不能为空"

			}),
			new Ext.form.NumberField({
				id:"price",
				name:"price",
				fieldLabel:"商品价格",
				allowNegative : false,//不允许输入负数
				nanText :'请输入有效的整数',//无效数字提示
				allowDecimals : true,//允许输入小数
				maxValue : 10000000,//最大值
				minValue : 0,//最小值
				minText:"商品价格不能小于{0}元",
				maxText:"商品价格不能大于{0}元",
				allowBlank:false,
				blankText:"商品价格不能为空"
			}),
			new Ext.form.NumberField({
				id:"agio",
				name:"agio",
				fieldLabel:"商品折扣",
				allowNegative : false,//不允许输入负数
				nanText :'请输入有效的整数',//无效数字提示
				allowDecimals : true,//允许输入小数
				maxValue : 1,//最大值
				minValue : 0,//最小值
				minText:"商品折扣不能小于{0}",
				maxText:"商品折扣不能大于{0}",
				allowBlank:false,
				blankText:"商品折扣不能为空"
			})
		],buttons:[
			{text:"添加",formBind:true,handler:function(){
				formPanel.getForm().submit({
					url:"commodityAdd.action",
					waitMsg:"请稍等,正在添加!",
					success:function(form, action){
	                        Ext.MessageBox.alert("成功",action.result.msg);
	                        formPanel.getForm().reset();
	                 },
					failure:function(form, action){
	                        Ext.MessageBox.alert("失败",action.result.msg);
	                }
				})

			}},
			{text:"重置",handler:function(){
				formPanel.getForm().reset();
			}}
		]
	});
	var commodityAddWindow = new Ext.Window({
		width:400,
		height:150,
		title:"商品信息录入",
		modal:true,
		items:formPanel,
		resizable:false
	});
	
	commodityAddWindow.show();
}