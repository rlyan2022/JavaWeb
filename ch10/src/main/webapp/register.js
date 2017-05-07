var registerForm = null;
var uN = new Ext.form.TextField({
	name : 'userName',
	width : 120,
	allowBlank : false,
	blankText : '用户名不能为空',
	fieldLabel : '用户名',
	// 检查用户名是否存在
	listeners : {
		'blur' : function() {
			Ext.Ajax.request({
				url : 'register.do?actionType=doRegister&userName='
						+ Ext.get('userName').getValue(),
				method : 'POST',
				success : function(request) {
					if (request.responseText == "此用户名已存在") {
						this.uN.markInvalid('此用户名已存在');
					}
				}
			});
		}
	}
})
registerWin = Ext.extend(Ext.Window, {
	title : '用户注册',
	width : 438,
	height : 330,
	collapsible : true,
	draggable : false,
	resizable : false,
	modal : true,
	defaults : {
		border : false
	},
	buttonAlign : 'center',

	createForm : function() {
		registerForm = new Ext.form.FormPanel({
			frame : true,
			labelWidth : 70,
			labelAlign : 'right',
			items : [{
				xtype : 'fieldset',
				title : '基本信息',
				autoHeight : true,
				items : [{
					layout : 'column',
					defaults : {
						border : false
					},
					items : [{
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						defaults : {
							width : 120
						},
						items : [{
							xtype : "hidden",
							name : "id"
						}, uN]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						defaults : {
							width : 104
						},
						items : [{
							inputType : 'password',
							fieldLabel : '密&nbsp;&nbsp;码',
							name : 'password'
						}]
					}]
				}, {
					layout : 'column',
					defaults : {
						border : false
					},
					items : [{
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						defaults : {
							width : 104
						},
						items : [{
							xtype : 'combo',
							fieldLabel : '性别',
							name : 'sex',
							hiddenname : 'sex',
							store : new Ext.data.SimpleStore({
								fields : ['returnValue', 'displayText'],
								data : [['男', '男'], ['女', '女']]
							}),
							returnField : 'returnValue',
							displayField : 'displayText',
							mode : 'local',
							forceSelection : true,
							editable : false,
							triggerAction : 'all',
							emptyText : '请选择性别',
							width : 120
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						dafaultType : 'textfield',
						items : [{
							xtype : 'datefield',
							fieldLabel : '出生日期',
							name : 'brithday',
							emptyText : '请选择日期',
							blankText : '请选择日期',
							format : 'Y年m月d日',
							width : 105
						}]
					}]
				}, {
					layout : 'form',
					defaultType : 'textfield',
					items : [{
						width : 300,
						name : "email",
						fieldLabel : '电子邮箱',
						vtype : 'email',
						allowBlank : false,
						blankText : '电子邮箱不能为空'
					}]
				}]
			}, {
				xtype : 'fieldset',
				title : '简介',
				autoHeight : true,
				items : [{
					xtype : "textarea",
					width : 380,
					height : 80,
					name : "remark",
					hideLabel : true
				}]
			}]
		});
		return registerForm;
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
					Ext.Msg.alert('警告', action.result.data);
				}
			});
		}
	},
	initComponent : function() {
		registerWin.superclass.initComponent.call(this);
		this.fp = this.createForm();
		this.add(this.fp);
		this.addButton('提交', this.login, this);
		this.addButton('重置', function() {
			this.fp.form.reset();
		}, this);
	}
});