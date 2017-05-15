function payOutAddFn(){
	var payOutAddForm = new Ext.FormPanel({
		bodyStyle:"padding-left:230px",
		width:800,
		frame:true,
		labelAlign:"right",
		monitorValid:true,
		items:[
			new Ext.form.TextField({
				id:"payOutName",
				name:"payOutName",
				fieldLabel:"支出名称",
				minLength:2,
				minLengthText:"支出名称长度不能小于2个字符",
				maxLength:12,
				maxLengthText:"支出名称长度不能大于12个字符",
				allowBlank:false,
				blankText:"支出名称不能为空"

			}),
			new Ext.form.NumberField({
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
			}),
			new Ext.form.DateField({
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
		],
		buttons:[
			{text:"添加",formBind:true,handler:function(){
				payOutAddForm.getForm().submit({
					url:"addPayOut.action",
					waitMsg:"请稍等,正在添加!",
					success:function(form, action){
	                        Ext.MessageBox.alert("成功",action.result.msg);
	                    },
					failure:function(form, action){
	                        Ext.MessageBox.alert("失败",action.result.msg);
	                   }
				})
			}},
			{text:"重置",handler:function(){
				payOutAddForm.getForm().reset();
			}}
		]
	});

	if(!payOutAddPageIsOpen){
		var tabPage = tabPanel.add({
						title:"支出添加",
						height:150,
						closable:true,
						layout:"fit",
						items:[
							payOutAddForm
						],
						listeners:{
							beforedestroy:function(){
								payOutAddPageIsOpen = false;
							}
						}
					});
		tabPanel.setActiveTab(tabPage);
		payOutAddPageIsOpen = true;
	}
}