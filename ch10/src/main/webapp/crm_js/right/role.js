Ext.namespace("CRM.roleLook");
var roleId1;
function showUpdateRoleWin() {
	var panel = new rolePanel();
	panel.edit();
}
function showDelRoleWin() {
	var panel = new rolePanel();
	panel.removeData();
}
// 找出所有的权限
var allRightStore = new Ext.data.Store({
	proxy : new Ext.data.HttpProxy({
		url : "role.do?actionType=dofindAllRightName"
	}),
	reader : new Ext.data.JsonReader({
		root : "allRight"
	}, ["rightCode", "rightText"])
})
// 找出该角色有的权限
var hasRightStore = new Ext.data.Store({
	proxy : new Ext.data.HttpProxy({
		url : "role.do?actionType=dofindRightName"
	}),
	reader : new Ext.data.JsonReader({
		root : "hasRight"
	}, ["rightCode", "rightText"])
});
// 找出该用户没有的权限
var notRightStore = new Ext.data.Store({
	proxy : new Ext.data.HttpProxy({
		url : "role.do?actionType=dofindRightName"
	}),
	reader : new Ext.data.JsonReader({
		root : "hasNotRight"
	}, ["rightCode", "rightText"])
});

var roleStore = new Ext.data.JsonStore({
	url : 'role.do?actionType=doList',
	root : "data",
	totalProperty : "rowCount",
	baseParams : {
		roleName : null
	},
	remoteSort : true,
	fields : ["roleId", "roleName", "roleDesc", "roleFlag", "operation"]
});
var roleColm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
	header : '编号',
	sortable : true,
	dataIndex : 'roleId',
	width : 207
}, {
	header : '角色名称',
	sortable : true,
	dataIndex : 'roleName',
	width : 207
}, {
	header : '角色权限',
	sortable : true,
	dataIndex : 'roleDesc',
	width : 350
}, {
	header : '操作',
	width : 70,
	renderer : function() {
		var operation = '&nbsp;&nbsp;&nbsp;'
				+ '<img src="images/bt_del.gif" title="删除" onclick="showDelRoleWin()">';
		return operation;
	}
}]);
var roleGrid = new Ext.grid.GridPanel({
	store : roleStore,
	cm : roleColm,
	height : 300,
	stripeRows : true,
	pageSize : 10,
	tbar : [{
		text : '新建角色',
		pressed : true,
		handler : function() {
			var panel = new rolePanel();
			panel.createAddW();
		},
		scope : this
	}, '-', {
		text : '分配权限',
		pressed : true,
		handler : function() {
			var panel = new rolePanel();
			panel.edit();

		}
	}, new Ext.Toolbar.Fill(), '角色名称', {
		xtype : 'textfield',
		name : 'roleName',
		width : 150
	}, '&nbsp;&nbsp;', {
		text : '查询',
		iconCls : 'search',
		pressed : true,
		handler : function() {
			var panel = new rolePanel();
			panel.search();
		},
		scope : this
	}, '   '],
	bbar : new Ext.PagingToolbar({
		pageSize : 10,
		store : roleStore,
		grid : roleGrid,
		displayInfo : true,
		displayMsg : '当前显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});
CRM.roleLook.role = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,

	initAddWin : function(width, title) {
		var addWin = new Ext.Window({
			width : width,
			autoHeight : true,
			buttonAlign : "center",
			title : title,
			modal : true,
			closeAction : "hide",
			plain : true,
			items : [this.fp],
			buttons : [{
				text : "保存",
				handler : this.save,
				tooltip : '点击该按钮将执行确认操作',
				scope : this
			}, {
				text : "清空",
				handler : this.resetAddWin,
				scope : this
			}, {
				text : "取消",
				id : 'cancel',
				handler : function() {
					this.closeAddWin();
				},
				scope : this
			}]
		});
		return addWin;
	},
	// 打开新建或编辑窗体
	showAddWin : function() {
		if (!this.addWin) {
			if (!this.fp) {
				this.fp = this.createAddForm();
			}
			this.addWin = this.createAddWin();
			this.addWin.on("close", function() {
				this.addWin = null;
				this.fp = null;
			}, this);
		}
		this.addWin.show();
	},
	// 重置
	resetAddWin : function() {
		if (this.addWin)
			this.fp.form.reset();
	},
	createAddW : function() {
		this.showAddWin();
		this.resetAddWin();
		allRightStore.load();
	},
	// 关闭当前窗口
	closeAddWin : function() {
		if (this.addWin)
			this.addWin.close();
		this.addWin = null;
	},
	// 保存数据
	save : function() {
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				url : this.baseUrl + '?actionType=doSave',
				method : 'POST',
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				success : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg, function() {
						this.closeAddWin();
						roleStore.reload();
					}, this)
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg);
				},
				scope : this
			});
		}
	},
	// ////////////////////编辑窗口\\\\\\\\\\\\\\\\\\\\\\\\\\\
	initEditWin : function(width, title) {
		var editWin = new Ext.Window({
			width : width,
			autoHeight : true,
			buttonAlign : "center",
			title : title,
			modal : true,
			closeAction : "hide",
			plain : true,
			items : [this.fp],
			buttons : [{
				text : "保存",
				handler : this.update,
				tooltip : '点击该按钮将执行确认操作',
				scope : this
			}, {
				text : "清空",
				handler : this.resetEditWin,
				scope : this
			}, {
				text : "取消",
				id : 'cancel',
				handler : function() {
					this.closeEditWin();
				},
				scope : this
			}]
		});
		return editWin;
	},
	// 打开新建或编辑窗体
	showEditWin : function() {
		if (!this.editWin) {
			if (!this.fp) {
				this.fp = this.createEditForm();
			}
			this.editWin = this.createEditWin();
			this.editWin.on("close", function() {
				this.editWin = null;
				this.fp = null;
			}, this);
		}
		this.editWin.show();
	},
	createEditWin : function() {
		allRightStore.load();
		this.showEditWin();
		this.reset();
	},
	// 重置
	resetEditWin : function() {
		if (this.editWin)
			this.fp.form.reset();
	},

	// 关闭当前窗口
	closeEditWin : function() {
		if (this.editWin)
			this.editWin.close();
		this.editWin = null;
	},
	// 修改数据
	edit : function() {
		var record = roleGrid.getSelectionModel().getSelected();
		if (record == null) {
			Ext.Msg.alert("警告", "请先选择要分配的角色");
			return;
		}
		roleId1 = record.get('roleId');
		notRightStore.load({
			params : {
				roleId : roleId1
			}
		});
		hasRightStore.load({
			params : {
				roleId : roleId1
			}
		});
		this.showEditWin();
		this.fp.form.loadRecord(record);
	},
	// 保存数据
	update : function() {
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				url : this.baseUrl + '?actionType=doUpdate',
				method : 'POST',
				params : {
					roleId : roleId1
				},
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				success : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg, function() {
						this.closeEditWin();
						roleStore.reload();
					}, this)
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg);
				},
				scope : this
			});
		}
	},

	search : function() {
		roleStore.baseParams.roleName = Ext.get('roleName').getValue();
		roleStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
	},
	// 删除
	removeData : function() {
		var record = roleGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请先选择要删除的行!");
			return;
		}
		Ext.MessageBox.confirm("确认删除", "确认删除所选数据？", function(button) {
			if (button == "yes") {
				Ext.Ajax.request({
					url : this.baseUrl + '?actionType=doDel',
					params : {
						roleId : record.get("roleId")
					},
					method : 'POST',
					success : function(response) {
						Ext.Msg.alert("系统消息", response.responseText,
								function() {
									roleStore.reload();
								}, this);
					},
					scope : this
				});
			}
		}, this);
	},

	initComponent : function() {
		CRM.roleLook.role.superclass.initComponent.call(this);
		roleStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
		this.add(roleGrid);
	}
});

var rolePanel = Ext.extend(CRM.roleLook.role, {
	id : 'roleLook',
	baseUrl : 'role.do',
	initComponent : function() {
		rolePanel.superclass.initComponent.call(this);
	},
	createAddForm : function() {
		var formPanel = new Ext.form.FormPanel({
			labelWidth : 70,
			frame : true,
			autoHeight : true,
			resizable : false,
			labelAlign : 'right',
			defaultType : 'textfield',
			items : [{
				name : 'roleName',
				width : 300,
				fieldLabel : '角色名称'
			}, {
				xtype : 'textarea',
				name : 'roleDesc',
				width : 300,
				fieldLabel : '角色描述'
			}]
		});
		return formPanel;
	},
	createAddWin : function() {
		return this.initAddWin(450, '新增角色');
	},
	createEditForm : function() {
		var formPanel = new Ext.form.FormPanel({
			labelWidth : 70,
			frame : true,
			autoHeight : true,
			resizable : false,
			labelAlign : 'right',
			defaultType : 'textfield',
			items : [{
				xtype : "itemselector",
				name : "rightSelector",
				// fieldLabel : "分配权限",
				hideLabel : true,
				fromStore : notRightStore,
				toStore : hasRightStore,
				msWidth : 200,
				autoScroll : true,
				msHeight : 250,
				displayField : "rightText",
				valueField : "rightCode",
				switchToFrom : false,
				toLegend : '已有权限',
				fromLegend : '可选权限'
			}]
		});
		return formPanel;
	},
	createEditWin : function() {
		return this.initEditWin(450, '分配权限');
	}
});
