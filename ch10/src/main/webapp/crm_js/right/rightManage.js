// 权限管理
Ext.namespace("CRM.rightManage");
// 编辑。。。。
function showEditWin() {
	var panel = new rightManagePanel();
	panel.edit();
}
// 删除。。。。
function showDelWin() {
	var panel = new rightManagePanel();
	panel.delData();
}
// 存储器。。。
var rightStort = new Ext.data.JsonStore({
	url : 'right.do?actionType=doList',
	root : 'data', // 数据源。。
	totalProperty : 'rowCount', // 总行数。。。
	remoteSort : true,
	fields : ["rightCode", "rightText", "rightUrl", "rightTip"]
});

var rightManageColm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
	header : '编号',
	sortable : true,
	dataIndex : 'rightCode',
	width : 220
}, {
	header : '权限',
	sortable : true,
	dataIndex : 'rightText',
	width : 240
}, {
	header : '访问路径',
	sortable : true,
	dataIndex : 'rightUrl',
	width : 220
}, {
	header : '操作',
	dataIndex : 'rightTip',
	width : 150,
	renderer : function() {
		return '<img src="images/bt_edit.gif" title="编辑" onclick="showEditWin()">'
				+ '&nbsp;&nbsp;'
				+ '<img src="images/bt_del.gif" title="删除" onclick="showDelWin()">';
	}
}]);
var rightManageGrid = new Ext.grid.GridPanel({
	store : rightStort,
	cm : rightManageColm,
	height : 300,
	stripeRows : true,
	pageSize : 15,
	tbar : [{}, {
		text : '添加',
		iconCls : 'add',
		pressed : false,
		handler : function() {
			var panel = new rightManagePanel();
			panel.create();
		}
	}],
	bbar : new Ext.PagingToolbar({
		pageSize : 15,
		store : rightStort,
		grid : rightManageGrid,
		displayInfo : true,
		displayMsg : '当前显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});
CRM.rightManage.right = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : 'fit',
	maskDisabled : false,
	girdViewConfig : {
		animate : true
	},
	initWin : function(width, title) {
		var win = new Ext.Window({
			width : width,
			autoHeight : true,
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
					this.closeWin();
				},
				scope : this
			}]
		});
		return win;
	},
	// 显示窗体。。。
	showWin : function() {
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
		this.win.show();
	},
	// 新建
	create : function() {
		this.showWin();
		this.reset();
	},
	// 重置
	reset : function() {
		if (this.win)
			this.fp.form.reset();
	},
	// 关闭窗体操作。。。
	closeWin : function() {
		if (this.win)
			this.win.close();
		this.win = null;
	},
	// 编辑
	edit : function() {
		var record = rightManageGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请选择要编辑的行!");
			return;
		}
		this.showWin();
		this.fp.form.loadRecord(record);
	},
	// 保存。。
	save : function() {
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : 'right.do?actionType=doSaveorUpdate',
				method : 'POST',
				success : function(form, action) {
					Ext.Msg.alert("系统消息", action.result.msg);
					this.closeWin();
					rightStort.reload();
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg);
				},
				scope : this
			});
		}
	},
	// 删除
	delData : function() {
		var record = rightManageGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请先选择要删除的行!");
			return;
		}
		Ext.MessageBox.confirm("确认删除", "确认删除所选数据？", function(button) {
			if (button == "yes") {
				Ext.Ajax.request({
					url : this.baseUrl + '?actionType=doDel',
					params : {
						rightCode : record.get("rightCode")
					},
					method : 'POST',
					success : function(response) {
						Ext.Msg.alert("提示信息", response.responseText,
								function() {
									rightStort.reload();
								}, this);
					},
					scope : this
				});
			}
		}, this);
	},
	initComponent : function() {
		CRM.rightManage.right.superclass.initComponent.call(this);
		rightStort.load({
			params : {
				start : 0,
				limit : 15
			}
		});
		this.add(rightManageGrid);
	}
});

// 权限面板。。。
rightManagePanel = new Ext.extend(CRM.rightManage.right, {
	id : 'right',
	baseUrl : 'right.do',
	createForm : function() {
		var formPanel = new Ext.form.FormPanel({
			labelWidth : 80,
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
					layout : 'form',
					border : false,
					defaultType : 'textfield',
					columnWidth : .5,
					items : [{
						xtype : 'hidden',
						name : 'rightCode'
					}, {
						name : 'rightText',
						fieldLabel : '权 限',
						allowBlank : false,
						blankText : '权限不能为空'
					}, {
						name : 'rightUrl',
						fieldLabel : '访问路径'
					}]
				}]
			}]
		});
		return formPanel;
	},
	createWin : function() {
		return this.initWin(300, '权限管理');
	},
	initComponent : function() {
		rightManagePanel.superclass.initComponent.call(this);
	}
});