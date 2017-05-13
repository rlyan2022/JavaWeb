function vipAdd(){
	//创建录入表单
	var formPanel = new Ext.FormPanel({
		bodyStyle:"padding-left:50px",
		width:400,
		frame:true,
		labelAlign:"right",
		monitorValid:true,
		items:[
			new Ext.form.TextField({
				id:"name",
				name:"name",
				fieldLabel:"姓名",
				minLength:2,
				minLengthText:"姓名长度不能小于2个字符",
				maxLength:12,
				maxLengthText:"姓名长度不能大于12个字符",
				allowBlank:false,
				blankText:"姓名不能为空"
			}),
			new Ext.form.NumberField({
				id:"age",
				name:"age",
				fieldLabel:"年龄",
				allowNegative : false,//不允许输入负数
				allowDecimals : false,//不允许输入小树
				nanText :'请输入有效的整数',//无效数字提示
				maxValue : 100,//最大值
				minValue : 1,//最小值
				minText:"年龄不能小于{0}岁",
				maxText:"年龄不能大于{0}岁",
				allowBlank:false,
				blankText:"年龄不能为空"
			}),
			new Ext.form.TextField({
				id:"profession",
				name:"profession",
				fieldLabel:"职业",
				minLength:2,
				minLengthText:"职业不能小于2个字符",
				maxLength:12,
				maxLengthText:"职业不能大于12个字符",
				allowBlank:false,
				blankText:"职业不能为空"

			}),
			new Ext.form.DateField({
				id:"joinTime",
				name:"joinTime",
				width:130,
				fieldLabel:"入会时间",
				maxValue:"12/31/2010",
				minValue:"01/01/2001",
				maxText:"支出日期不能大于{0}",
				minText:"支出日期不能小于{0}",
				format:"Y年m月d日",
				allowBlank:false,
				blankText:"入会时间不能为空"
			})
		],buttons:[
			{text:"添加",formBind:true,handler:function(){
					formPanel.getForm().submit({
					url:"vipAdd.action",
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


	//创建一个window
	var vipAddWindow = new Ext.Window({
		width:400,
		height:180,
		title:"VIP信息录入",
		modal:true,
		resizable:false,
		items:formPanel
	});
	
	vipAddWindow.show();
}