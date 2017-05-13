function consumeAdd(){
	//创建表单
	var formPanel = new Ext.form.FormPanel({
		bodyStyle:"padding-left:50px",
		width:400,
		frame:true,
		labelAlign:"right",
		monitorValid:true,
		items:[
			new Ext.form.NumberField({
				id:"vipId",
				name:"vipId",
				fieldLabel:"vip编号",
				allowNegative : false,//不允许输入负数
				allowDecimals : false,//不允许输入小树
				nanText :'请输入有效的整数',//无效数字提示
				allowBlank:false,
				blankText:"vipId不能为空",
				listeners:{
					"blur":function(field){
						var name = field.ownerCt.findByType("textfield")[0];
						var vipIdValue = field.value;
						if(vipIdValue != undefined) {
							Ext.Ajax.request({
								url:"getVip.action",
								method:"post",
								params:{
									vipId:vipIdValue
								},
								callback:function(options,success,response) {
									var jsonStr = Ext.util.JSON.decode(response.responseText);
									if(jsonStr.success) {
										name.setValue(jsonStr.vip.name);
									}else {
										Ext.MessageBox.alert("失败",jsonStr.msg);
										name.setValue("");
									}
								}
							})
						}
					}
				}
			}),
			new Ext.form.TextField({
				id:"name",
				name:"name",
				fieldLabel:"姓名",
				minLength:2,
				minLengthText:"姓名长度不能小于2个字符",
				maxLength:12,
				maxLengthText:"姓名长度不能大于12个字符",
				allowBlank:false,
				blankText:"姓名不能为空",
				readOnly:true

			}),
			new Ext.form.NumberField({
				id:"commodityId",
				name:"commodityId",
				fieldLabel:"商品编号",
				allowNegative : false,//不允许输入负数
				allowDecimals : false,//不允许输入小树
				nanText :'请输入有效的整数',//无效数字提示
				allowBlank:false,
				blankText:"commodityId不能为空",
				listeners:{
					"blur":function(field){
						commodityIdValue = field.value
						var commodityName = field.ownerCt.findByType("textfield")[1];
						var price = field.ownerCt.findByType("numberfield")[2];
						var practicePrice = field.ownerCt.findByType("numberfield")[3];
						if(commodityIdValue != undefined) {
							Ext.Ajax.request({
								url:"getCommodity.action",
								method:"post",
								params:{
									commodityId:commodityIdValue
								},
								callback:function(options,success,response) {
									var jsonStr = Ext.util.JSON.decode(response.responseText);
									if(jsonStr.success) {
										commodityName.setValue(jsonStr.commodity.commodityName);
										price.setValue(jsonStr.commodity.price);
										practicePrice.setValue(jsonStr.commodity.price * jsonStr.commodity.agio );
									}else {
										Ext.MessageBox.alert("失败",jsonStr.msg);
										commodityName.setValue("");
										price.setValue("");
										practicePrice.setValue("");
									}
								}
							})
						}

					}
				}
			}),
			new Ext.form.TextField({
				id:"commodityName",
				name:"commodityName",
				fieldLabel:"商品名称",
				minLength:2,
				minLengthText:"商品名称长度不能小于2个字符",
				maxLength:12,
				maxLengthText:"商品名称长度不能大于12个字符",
				allowBlank:false,
				blankText:"商品名称不能为空",
				readOnly:true

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
				blankText:"商品价格不能为空",
				readOnly:true
			}),new Ext.form.NumberField({
				id:"practicePrice",
				name:"practicePrice",
				fieldLabel:"商品实际价格",
				allowNegative : false,//不允许输入负数
				nanText :'请输入有效的整数',//无效数字提示
				allowDecimals : true,//允许输入小数
				maxValue : 10000000,//最大值
				minValue : 0,//最小值
				minText:"商品实际价格不能小于{0}元",
				maxText:"商品实际价格不能大于{0}元",
				allowBlank:false,
				blankText:"商品实际价格不能为空",
				readOnly:true
			})
		],buttons:[
			{text:"添加",formBind:true,handler:function(){
				formPanel.getForm().submit({
					url:"consumeAdd.action",
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

	var consumeAddWindow = new Ext.Window({
		width:400,
		height:230,
		title:"VIP消费信息录入",
		resizable:false,
		modal:true,
		items:formPanel
	});	
	consumeAddWindow.show();
}