Ext.namespace("CRM.custManange");
// 点击图标打开编辑窗体
function showAtvEditWin() {
	var panel = new custActivityPanel();
	panel.edit();
}
// 点击图标打开删除窗体
function showAtvDelWin() {
	var panel = new custActivityPanel();
	panel.removeData();
}
var cstActivityStore = new Ext.data.JsonStore({
	url : 'customer.do?actionType=dofindCstActivity',
	root : "data",
	totalProperty : "rowCount",
	baseParams : {
		atvCustNo : null
	},
	remoteSort : true,
	fields : ["atvId", "atvCustNo", "atvDate", "atvPlace", "atvTitle",
			"atvDesc", "atvRemark", "operation"]
});
var cstActivityColm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
	header : '时间',
	sortable : true,
	dataIndex : 'atvDate',
	width : 120
}, {
	header : '地点',
	sortable : true,
	dataIndex : 'atvPlace',
	width : 130
}, {
	header : '概要',
	sortable : true,
	dataIndex : 'atvTitle',
	width : 150
}, {
	header : '详细信息',
	sortable : true,
	dataIndex : 'atvDesc',
	width : 182
}, {
	header : '备注',
	sortable : true,
	dataIndex : 'atvRemark',
	width : 150
}, {
	header : '操作',
	dataIndex : 'operation',
	renderer : function() {
		return '<img src="images/bt_edit.gif" onclick="showAtvEditWin()"/>'
				+ '<img src="images/bt_del.gif" onclick="showAtvDelWin()"/>';
	}
}]);
var cstActivityGrid = new Ext.grid.GridPanel({
	store : cstActivityStore,
	cm : cstActivityColm,
	height : 300,
	stripeRows : true,
	pageSize : 10,
	tbar : [{
		text : '新建',
		id : 'add',
		iconCls : 'add',
		handler : function() {
			var panel = new custActivityPanel();
			panel.create('add');
		},
		scope : this
	}, '   '],
	bbar : new Ext.PagingToolbar({
		pageSize : 10,
		store : cstActivityStore,
		grid : cstActivityGrid,
		displayInfo : true,
		displayMsg : '显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});

CRM.custManange.custActivity = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,
	gridViewConfig : {
		animate : true
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
	save : function() {
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : this.baseUrl + '?actionType=doSaveorUpdateCstActivity&atvCustNo='
						+ custNo,
				method : 'POST',
				success : function(form, action) {
					Ext.Msg.alert("系统消息", action.result.msg, function() {
						this.closeWin();
						cstActivityStore.reload();
					}, this);
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg);
				},
				scope : this
			});
		}
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
	edit : function(show) {
		var record = cstActivityGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请选择要编辑的行!");
			return;
		}
		var id = record.get("id");
		this.showWin(show);
		this.fp.form.loadRecord(record);
	},
	removeData : function() {
		var record = cstActivityGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请先选择要删除的行!");
			return;
		}
		Ext.MessageBox.confirm("确认删除", "确认删除所选数据？", function(button) {
			if (button == "yes") {
				Ext.Ajax.request({
					url : this.baseUrl + '?actionType=doDelCstActivity',
					params : {
						atvId : record.get("atvId")
					},
					method : 'POST',
					success : function(response) {
						Ext.Msg.alert("提示信息", response.responseText,
								function() {
									cstActivityStore.reload();
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
		CRM.custManange.custActivity.superclass.initComponent.call(this);
		this.add(cstActivityGrid);
		// 加载数据
		cstActivityStore.baseParams.atvCustNo = custNo;
		cstActivityStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
		cstActivityGrid.on("celldblclick", function() {
			this.edit(Ext.getBody());
		}, this);
		cstActivityGrid.addListener("rowContextmenu", this.showMenu, this);

	}
});

custActivityPanel = Ext.extend(CRM.custManange.custActivity, {
	id : 'custActivity',
	baseUrl : 'customer.do',
	createForm : function() {
		var formPanel = new Ext.form.FormPanel({
			labelWidth : 70,
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
						xtype : 'hidden',
						name : 'atvId'
					}, {
						xtype : 'hidden',
						name : 'atvCustNo'
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							xtype : 'datefield',
							name : 'atvDate',
							fieldLabel : '时间',
							allowBlank : false,
							blankText : '时间为必填项',
							emptyText : '请选择时间',
							format : 'Y年m月d日',
							width : 125
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'atvPlace',
							fieldLabel : '地点',
							allowBlank : false,
							blankText : '地点为必填项'
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
							name : 'atvTitle',
							fieldLabel : '概要',
							allowBlank : false,
							blankText : '概要为心填项'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'atvRemark',
							fieldLabel : '备注'
						}]
					}]
				}]
			}, {
				xtype : 'fieldset',
				title : '详细信息',
				autoHeight : true,
				items : [{
					layout : 'column',
					border : false,
					items : [{
						xtype : 'htmleditor',
						fieldLabel : '详细信息',
						height : 125,
						width : 500,
						name : 'atvDesc'
					}]
				}]

			}]
		});
		return formPanel;
	},
	createWin : function() {
		return this.initWin(555, 340, '交往记录');
	},
	initComponent : function() {
		custActivityPanel.superclass.initComponent.call(this);
	}
});
