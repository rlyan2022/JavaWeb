Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = "../Ext/resources/images/default/s.gif";
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";
	
	var loginForm = new Ext.FormPanel({
		renderTo:"loginForm",
		id:"loginForm1",
		width:340,
		height:150,
		frame:true,
		labelAlign:"right",
		monitorValid:true,
		items:[
			new Ext.form.TextField({
				id:"username",
				name:"username",
				fieldLabel:"用户名",
				minLength:3,
				minLengthText:"用户名长度不能小于{0}",
				maxLength:12,
				maxLengthText:"用户名长度不能大于{0}",
				allowBlank:false,
				blankText:"用户名必须输入"
			}),
			new Ext.form.TextField({
				id:"password",
				name:"password",
				fieldLabel:"密码",
				inputType:"password",
				allowBlank:false,
				blankText:"密码必须输入"
			}),
			new Ext.form.TextField({
				id:"randCode",
				name:"randCode",
				width:70,
				fieldLabel:"验证码",
				allowBlank:false,
				blankText:"验证码必须输入"
			})
		],
		buttons:[
			{text:"登录",formBind:true,handler:function(){
				loginForm.getForm().submit({
					url:"login.action",
					waitMsg:"请稍等，系统正在进行登录!",
					success:function(form, action){
						Ext.MessageBox.alert("成功",action.result.msg);
						window.location.href="index.html";
					},
					failure:function(form, action){
						Ext.MessageBox.alert("失败",action.result.msg);
					}
				});
			}},
			{text:"重置",handler:function(){
//				loginForm.items.itemAt(0).setValue("");
//				loginForm.items.itemAt(1).setValue("");
				loginForm.form.reset();
			}}
		]
	});

	var rc = Ext.getDom("randCode");
	var rcp = Ext.get(rc.parentNode);
	rcp.createChild({tag: 'img', src: 'image.jsp',align:'absbottom'});


	var loginWindow = new Ext.Window({
		width:340,
		height:180,
		title:"个人理财管理系统用户登录",
		items:loginForm,
		resizable:false,
		draggable:false,
		closable:false
	});
	loginWindow.show();

});