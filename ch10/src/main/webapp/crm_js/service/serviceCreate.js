serviceCreateWin = Ext.extend(Ext.Window, {
	title : '服务创建',
	modal : true,
	width : 555,
	height : 350,
	resizable : false,
	plain : false,
	layout : 'form',
	footer : false,
	draggable : false,
	border : false,
	bodyBorder : false,
	buttonAlign : 'center',
	createForm : function() {
		return serviceCreatePanel = new Ext.form.FormPanel({
			labelWidth : 70,
			frame : true,
			labelAlign : 'center',
			defaultType : 'textfield',
			items : [{
				xtype : 'fieldset',
				title : '  ',
				autoHeight : true,
				items : [{
					layout : 'column',
					border : false,
					items : [{
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							xtype : 'combo',
							name : 'svrCustName',
							fieldLabel : '客户',
							width : 150,
							store : new Ext.data.JsonStore({
								url : 'customer.do?actionType=doList',
								root : 'data',
								fields : ['custName']
							}),
							displayField : 'custName',
							forceSelection : true,
							triggerAction : 'all',
							editable : false,
							allowBlank : false,
							blankText : '客户为必填项'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							xtype : 'combo',
							name : 'svrType',
							fieldLabel : '服务类型',
							width : 150,
							store : new Ext.data.SimpleStore({
								fields : ['type'],
								data : [['咨询'], ['投诉'], ['建议']]
							}),
							displayField : 'type',
							mode : 'local',
							forceSelection : true,
							editable : false,
							triggerAction : 'all',
							allowBlank : false,
							BlankText : '服务类型为必填项',
							emptyText : '请选择地区'
						}]
					}]
				}, {
					layout : 'column',
					border : false,
					items : [{
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'svrStatus',
							fieldLabel : '状态',
							value : '新创建',
							width : 150
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'svrTitle',
							fieldLabel : '概要',
							width : 150,
							allowBlank : false,
							blankText : '概要为必填项'
						}]
					}]
				}]
			}, {
				xtype : 'fieldset',
				title : '服务请求',
				autoHeight : true,
				items : [{
					layout : 'fit',
					xtype : 'htmleditor',
					height : 125,
					width : 500,
					name : 'svrRequest',
					hideLabel : true,
					allowBlank : false,
					blankText : '服务请求为必填项'
				}]
			}]
		});
	},
	save : function() {
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				url : 'cstService.do?actionType=doSave',
				method : 'POST',
				params :{
					svrCreateBy : currentUser
				},
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				success : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg, function() {
					}, this);
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg);
				}
			})
		}
	},
	showWin : function(show) {
		if (!this.win) {
			if (!this.fp) {
				this.fp = this.createForm();
			}
			this.win = this.createWin();
			this.win.on("close", function() {
				this.win = null;
				this.fp = null;
			}, this);
		}
		this.win.show(show);
	},
	initComponent : function() {
		serviceCreateWin.superclass.initComponent.call(this);
		this.fp = this.createForm();
		this.add(this.fp);
		this.addButton('提交', this.save, this);
		this.addButton('重置', function() {
			this.fp.form.reset();
		}, this);
	}
})
