Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = "Ext/resources/images/default/s.gif";
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget="qtip";
	
	var mainWindow = new Ext.Window({
		width:600,
		height:400,
		title:"商场VIP消费情况查询系统",
		closable:false,
		resizable:false, //设置是否可以改变大小
		draggable:false,  //设置是否可以拖动
		tbar:new Ext.Toolbar({
			height:25
		})
	});
	mainWindow.show();

	//商品信息管理菜单
	var commodityMenu = new Ext.menu.Menu({
		items:[
			{text:"商品信息录入",handler:commodityAdd},
			{text:"商品信息查询",handler:commodityQuery}
		]
	});

	//VIP信息管理菜单
	var vipMenu = new Ext.menu.Menu({
		items:[
			{text:"VIP信息录入",handler:vipAdd},
			{text:"VIP信息查询",handler:vipQuery},
			{text:"VIP消费信息录入",handler:consumeAdd},
			{text:"VIP消费信息查询",handler:consumeQuery}
		]
	});


	mainWindow.getTopToolbar().add(new Ext.SplitButton({
		text:"商品信息管理",
		menu:commodityMenu
	}));


	mainWindow.getTopToolbar().add(new Ext.SplitButton({
		text:"VIP信息管理",
		menu:vipMenu
	}));

	mainWindow.getTopToolbar().add(new Ext.Button({
		text:"系统维护"
	}));

	mainWindow.getTopToolbar().add(new Ext.Button({
		text:"帮助",
		handler:function showHelpWindow(){
			var myToolBar = new Ext.Toolbar({
				listeners:
						  {
						    'render':function(t)
						    {
						       t.getEl().child("table").wrap({tag:'center'});
						    }
						},
				items:[
					{text:"关闭",handler:function helpWindowClose(){
							helpWindow.close();
						}
					}
				]

			});

			var helpPanel = new Ext.Panel({
					height:283,
					html:"<center><font size='3'><B>本系统的主要功能</B></font></center>" +
							"<h2>◆	登录</h2>" +
							"本系统为商场VIP消费情况查询系统，具有一定的保密性。因此必须要有一定的权限才能使用本系统查询。要进行查询就必须先登录才能使用本系统。登录之后进入系统的主界面，在主界面可以选择用户想要执行的功能模块。" +
							"<h2>◆	商品信息录入</h2>" +
							"在维护本系统时，要不断更新系统的数据。其中商品的信息是在不断增加的，当新进商品时，要保证商品信息的同步更新。在本模块可将新进商品的信息录入保存在服务器的数据库中。" +
							"<h2>◆	商品信息查询</h2>" +
							"有时候想了解某种商品的详细信息。在本模块可以查询所想知道的商品的详细信息。",
					bbar:myToolBar
			});

			var helpWindow = new Ext.Window({
				width:400,
				height:300,
				items:helpPanel,
				resizable:false,
				draggable:false,
				modal:true
			});
			helpWindow.show();
		}
	}));

	mainWindow.getTopToolbar().add(new Ext.Button({
		text:"退出",
		handler:function pExit(){
			window.close();
		}
	}));


	//登录表单
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
						loginWindow.close();
					},
					failure:function(form, action){
						Ext.MessageBox.alert("失败",action.result.msg);
					}
				});
			}},
			{text:"重置",handler:function(){
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
		title:"用户登录",
		items:loginForm,
		resizable:false,
		draggable:false,
		closable:false,
		modal:true
	});
	loginWindow.show();
	
});