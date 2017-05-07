Ext.QuickTips.init();
LoginWindow = Ext.extend(Ext.Window, {
	renderTo : 'loginWin',
	title : '系统登录',
	width : 265,
	height : 160,
	closable : false,
	draggable : false,
	resizable : false,
	defaults : {
		border : false
	},
	buttonAlign : 'center',
	createFormPanel : function() {
		return new Ext.form.FormPanel({
			bodyStyle : 'padding-top:6px',
			defaultType : 'textfield',
			labelAlign : 'right',
			labelWidth : 55,
			labelPad : 0,
			frame : true,
			defaults : {
				allowBlank : false,
				width : 158
			},
			items : [{
				cls : 'user',
				name : 'userName',
				fieldLabel : '帐号',
				blankText : '帐号不能为空'
			}, {
				cls : 'key',
				name : 'password',
				fieldLabel : '密码',
				blankText : '密码不能为空',
				inputType : 'password'
			},{
				id:"randCode",
				name:"randCode",
				width:70,
				fieldLabel:"验证码",
				allowBlank:false,
				blankText:"验证码必须输入"
			}]
		});
	},
	login : function() {
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在登录......',
				url : 'login.do?actionType=doLogin',
				success : function(form, action) {
					window.location.href = 'main.jsp';
				},
				failure : function(form, action) {
					form.reset();
					Ext.Msg.alert('警告', action.result.msg);
				}
			});
		}
	},
	initComponent : function() {
		LoginWindow.superclass.initComponent.call(this);
		this.fp = this.createFormPanel();
		this.add(this.fp);
		this.addButton('登录', this.login, this);
		this.addButton('重置', function() {
			this.fp.form.reset();
		}, this);
	}
});

Ext.onReady(function() {
	var win = new LoginWindow();
	var rc = Ext.getDom("randCode");
	var rcp = Ext.get(rc.parentNode);
	rcp.createChild({tag: 'img', src: 'image.jsp',align:'absbottom'});
	win.show();
	setTimeout(function() {
		Ext.get('loading-mask').fadeOut({
			remove : true
		});
	}, 300);
});