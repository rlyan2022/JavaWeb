Ext.namespace("CRM.custManage");
function showLkmEditWin() {
	var panel = new custLinkmanPanel();
	panel.edit();
}
function showLkmDelWin() {
	var panel = new custLinkmanPanel();
	panel.removeData();
}
var cstLinkmanStore = new Ext.data.JsonStore({
	url : 'customer.do?actionType=doFindLinkman',
	root : "data",
	totalProperty : "rowCount",
	baseParams : {
		lkmCustNo : null
	},
	remoteSort : true,
	fields : ["lkmId", "lkmCustNo", "lkmName", "lkmSex", "lkmPostion",
			"lkmTel", "lkmMobile", "lkmMemo", "operation"]
});
var cstLinkmanClom = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
	header : '姓名',
	sortable : true,
	dataIndex : 'lkmName',
	width : 120
}, {
	header : '性别',
	sortable : true,
	dataIndex : 'lkmSex',
	width : 115,
	renderer : function(value) {
		if (value == '男') {
			return '<img src="images/man.gif"/>' + value;
		} else {
			return '<img src="images/woman.gif"/>' + value;
		}
	}
}, {
	header : '职位',
	sortable : true,
	dataIndex : 'lkmPostion',
	width : 120
}, {
	header : '办公电话',
	sortable : true,
	dataIndex : 'lkmTel',
	width : 120
}, {
	header : '手机',
	sortable : true,
	dataIndex : 'lkmMobile',
	width : 120
}, {
	header : '备注',
	sortable : true,
	dataIndex : 'lkmMemo',
	width : 133
}, {
	header : '操作',
	dataIndex : 'operation',
	renderer : function() {
		return '<img src="images/bt_edit.gif" onclick="showLkmEditWin()"/>'
				+ '<img src="images/bt_del.gif" onclick="showLkmDelWin()">';
	}
}]);
var cstLinkmanGrid = new Ext.grid.GridPanel({
	store : cstLinkmanStore,
	cm : cstLinkmanClom,
	height : 300,
	stripeRows : true,
	pageSize : 10,
	tbar : [{
		text : '新建',
		id : 'add',
		iconCls : 'add',
		pressed : true,
		handler : function() {
			var panel = new custLinkmanPanel();
			panel.create('add');
		},
		scope : this
	}, '   '],
	bbar : new Ext.PagingToolbar({
		pageSize : 10,
		store : cstLinkmanStore,
		grid : cstLinkmanGrid,
		displayInfo : true,
		displayMsg : '显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});
CRM.custManage.custLinkman = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,
	gridViewConfig : {
		animate : true
	},
	refresh : function() {
		cstLinkmanStore.removeAll();
		cstLinkmanStore.reload();
	},
	initWin : function(width, height, title) {
		var win = new Ext.Window({
			width : width,
			height : height,
			buttonAlign : "center",
			title : title,
			modal : true,
			closeAction : "hide",
			resizable : false,
			plain : true,
			items : [this.fp],
			buttons : [{
				text : "保存",
				handler : this.save,
				tooltip : '点击该按钮将执行确认操作',
				scope : this
			}, {
				text : "清空",
				handler : this.reset,
				scope : this
			}, {
				text : "取消",
				id : 'cancel',
				handler : function() {
					this.closeWin('cancel');
				},
				scope : this
			}]
		});
		return win;
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
	create : function(show) {
		this.showWin(show);
		this.reset();
	},
	reset : function() {
		if (this.win)
			this.fp.form.reset();
	},
	closeWin : function(show) {
		if (this.win)
			this.win.close(show);
		this.win = null;
	},
	save : function() {
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : this.baseUrl
						+ '?actionType=doSaveorUpdateLinkman&lkmCustNo='
						+ custNo,
				method : 'POST',
				success : function(form, action) {
					Ext.Msg.alert("系统消息", action.result.msg, function() {
						this.closeWin();
						cstLinkmanStore.reload();
					}, this);
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg);
				},
				scope : this
			});
		}
	},

	search : function() {
		cstLinkmanStore.baseParams.schBranch = Ext.get('schBranch').getValue();
		cstLinkmanStore.baseParams.schName = Ext.get('schName').getValue();
		cstLinkmanStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
	},
	edit : function(show) {
		var record = cstLinkmanGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请选择要编辑的行!");
			return;
		}
		this.showWin(show);
		this.fp.form.loadRecord(record);
	},
	removeData : function() {
		var record = cstLinkmanGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请先选择要删除的行!");
			return;
		}
		Ext.MessageBox.confirm("确认删除", "确认删除所选数据？", function(button) {
			if (button == "yes") {
				Ext.Ajax.request({
					url : this.baseUrl + '?actionType=doDelLinkman',
					params : {
						lkmId : record.get("lkmId")
					},
					method : 'POST',
					success : function(response) {
						Ext.Msg.alert("提示信息", response.responseText,
								function() {
									cstLinkmanStore.reload();
								}, this);
					},
					scope : this
				});
			}
		}, this);
	},
	// 右键菜单
	showMenu : function(grid, index, e) {
		this.menu = new Ext.menu.Menu({
			items : [{
				id : 'add',
				text : '新建',
				iconCls : 'add',
				handler : function() {
					this.create(Ext.getBody());
				},
				scope : this
			}, {
				id : 'modify',
				text : '编辑',
				iconCls : 'modify',
				handler : function() {
					this.edit(Ext.getBody());
				},
				scope : this
			}, {
				id : 'del',
				text : '删除',
				iconCls : 'del',
				handler : this.removeData,
				scope : this
			}]
		});
		e.preventDefault();
		this.menu.showAt(e.getXY());
	},
	initComponent : function() {
		CRM.custManage.custLinkman.superclass.initComponent.call(this);
		this.add(cstLinkmanGrid);
//		cstLinkmanGrid.on("celldblclick", function() {
//			this.edit(Ext.getBody());
//		}, this);
//		cstLinkmanGrid.addListener("rowContextmenu", this.showMenu, this);
		// 加载数据
		cstLinkmanStore.baseParams.lkmCustNo = custNo;
		cstLinkmanStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
	}
});

custLinkmanPanel = Ext.extend(CRM.custManage.custLinkman, {
	id : 'custLinkman',
	baseUrl : 'customer.do',
	createForm : function() {
		var formPanel = new Ext.form.FormPanel({
			labelWidth : 60,
			frame : true,
			autoHeight : true,
			resizable : false,
			labelAlign : 'right',
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
							xtype : 'hidden',
							name : 'lkmId'
						}, {
							name : 'lkmName',
							fieldLabel : '姓名',
							allowBlank : false,
							blankText : '姓名为必填项'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							xtype : 'combo',
							name : 'lkmSex',
							fieldLabel : '性别',
							width : 125,
							store : new Ext.data.SimpleStore({
								fields : ['sex'],
								data : [['男'], ['女']]
							}),
							displayField : 'sex',
							mode : 'local',
							forceSelection : true,
							editable : false,
							triggerAction : 'all'
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
							name : 'lkmPostion',
							fieldLabel : '职位',
							allowBlank : false,
							blankText : '职位为心填项'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'lkmTel',
							fieldLabel : '办公电话',
							allowBlank : false,
							blankText : '办公电话为必填项'
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
							xtype : 'numberfield',
							name : 'lkmMobile',
							fieldLabel : '手机'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'lkmMemo',
							fieldLabel : '备注'
						}]
					}]
				}]
			}]
		});
		return formPanel;
	},
	createWin : function() {
		return this.initWin(450, 200, '联系人信息管理');
	},
	initComponent : function() {
		custLinkmanPanel.superclass.initComponent.call(this);
	}
});
