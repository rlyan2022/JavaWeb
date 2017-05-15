function userAddFn(){
	
	Ext.form.VTypes.repetitionText="密码和确认密码不一致";

	var userAddForm = new Ext.FormPanel({
		bodyStyle:"padding-left:230px",
		width:800,
		frame:true,
		labelAlign:"right",
		monitorValid:true,
		items:[
			new Ext.form.TextField({
				id:"username",
				name:"username",
				fieldLabel:"用户名",
				minLength:3,
				minLengthText:"用户名长度不能小于3个字符",
				maxLength:12,
				maxLengthText:"用户名长度不能大于12个字符",
				allowBlank:false,
				blankText:"用户名不能为空"

			}),
			new Ext.form.TextField({
				id:"password",
				name:"password",
				fieldLabel:"密码",
				inputType:"password",
				minLength:3,
				minLengthText:"密码长度不能小于3个字符",
				maxLength:12,
				maxLengthText:"密码长度不能大于12个字符",
				allowBlank:false,
				blankText:"密码不能为空"
			}),
			new Ext.form.TextField({
				id:"repassword",
				name:"repassword",
				fieldLabel:"确认密码",
				inputType:"password",
				minLength:3,
				minLengthText:"确认密码长度不能小于3个字符",
				maxLength:12,
				maxLengthText:"确认密码长度不能大于12个字符",
				allowBlank:false,
				blankText:"确认密码不能为空",
				vtype:"repetition",
				repetition:{targetCmpId:"password"}
			})
		],
		buttons:[
			{text:"添加",formBind:true,handler:function(){
				userAddForm.getForm().submit({
					url:"userAdd.action",
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
				userAddForm.getForm().reset();
			}}
		]
	});

	if(!userAddPageIsOpen){
		var tabPage = tabPanel.add({
						title:"用户添加",
						height:150,
						layout:"fit",
						closable:true,
						items:[
							userAddForm
						],
						listeners:{
							beforedestroy:function(){
								userAddPageIsOpen = false;
							}
						}
			});
		tabPanel.setActiveTab(tabPage);
		//设置该页面已经打开
		userAddPageIsOpen = true;
	}
}