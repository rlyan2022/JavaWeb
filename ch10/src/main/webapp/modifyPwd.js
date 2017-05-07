var isSubmit = true;
var oldP = new Ext.form.TextField({
	name : 'oldPwd',
	inputType : 'password',
	allowBlank : false,
	blankText : '请输入旧密码',
	fieldLabel : '旧密码',
	// 检查用户名是否存在
	listeners : {
		'blur' : function() {
			Ext.Ajax.request({
				url : 'login.do?actionType=doValidPwd&oldPwd='
						+ Ext.get('oldPwd').getValue(),
				method : 'POST',
				success : function(request) {
					if (request.responseText == "输入的旧密码不正确") {
						this.oldP.markInvalid('输入的旧密码不正确');
						isSubmit = false;
					} else {
						isSubmit = true;
					}
				}
			});
		}
	}
});
modifyWin = Ext.extend(Ext.Window, {
	title : '修改密码',
	width : 300,
	autoHeight : true,
	frame : true,
	resizable : false,
	modal : true,
	closeAction : 'hide',
	buttonAlign : 'center',
	createModifyPanel : function() {
		return new Ext.form.FormPanel({
			frame : true,
			defaultType : 'textfield',
			items : [{
				fieldLabel : '旧密码',
				name : 'oldPwd',
				inputType : 'password',
				allowBlank : false,
				blankText : '旧密码不能为空'
			}, {
				fieldLabel : '新密码',
				name : 'newPwd',
				inputType : 'password',
				allowBlank : false,
				blankText : '请输入新密码'
			}, {
				fieldLabel : '确认新密码',
				name : 'reNewPwd',
				allowBlank : false,
				inputType : 'password',
				blankText : '请确认新密码'
			}]
		});
	},
	modify : function() {
		// if (isSubmit == false) {
		// return;
		// }
		if (this.fp.form.isValid()) {
			if (this.fp.form.findField("newPwd").getValue() != this.fp.form
					.findField("reNewPwd").getValue()) {
				Ext.Msg.alert('警告', '两次密码输入不一致');
				return;
			}
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : 'login.do?actionType=doUpdatePwd',
				method : 'POST',
				success : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg, function() {
							// closeModifyWin();
						});
					// var closeWin = new modifyWin();
					// closeWin.close();
				},
				failure : function(form, action) {
					Ext.Msg.alert('错误', action.result.msg);
				}
			});
		}
	},
	initComponent : function() {
		modifyWin.superclass.initComponent.call(this);
		this.fp = this.createModifyPanel();
		this.add(this.fp);
		this.addButton('确定', this.modify, this);
		this.addButton('重置', function() {
			this.fp.form.reset();
		}, this);
	}
});