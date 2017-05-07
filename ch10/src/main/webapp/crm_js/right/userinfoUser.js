// 用户管理
Ext.namespace("CRM.userinfoManage");
function showEditUserinfoWin() {
	var panel = new userinfoUserPanel();
	panel.edit();
}
function showDelUserinfoWin() {
	var panel = new userinfoUserPanel();
	panel.removeData();
}
// 存储器
var userinfoUserStore = new Ext.data.JsonStore({
	url : 'userinfo.do?actionType=doList',
	root : "data",// 数据源
	totalProperty : "rowCount",// 总行数
	// 参数
	baseParams : {
		usrName : null,
		roleId : null
	},
	remoteSort : true,
	fields : ["usrId", "usrName", "usrPassword", "usrRoleId", "usrFlag",
			"roleName", "rightText"]
});
var rightTextStore = new Ext.data.JsonStore({
	url : 'userinfo.do?actionType=doFindAllRight',
	method : 'POST',
	root : 'data',
	totalProperty : 'rowCount',
	fields : ['rightCode', 'rightText']
})
var userinfoUserColm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
	header : '用户编号',
	sortable : true,
	dataIndex : 'usrId',
	width : 167
}, {
	header : '用户名称',
	sortable : true,
	dataIndex : 'usrName',
	width : 167
}, {
	header : '角色名称',
	sortable : true,
	dataIndex : 'roleName',
	width : 167
}, {
	header : '权限名称',
	sortable : true,
	dataIndex : 'rightText',
	width : 167
}, {
	header : '操作',
	dataIndex : 'chcStatus',
	width : 160,
	renderer : function() {
		var operation = '';
		operation += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
		operation += '<img src="images/bt_edit.gif" title="编辑" onclick="showEditUserinfoWin()"/>'
		operation += '&nbsp;&nbsp;&nbsp;'
				+ '<img src="images/bt_del.gif" title="删除" onclick="showDelUserinfoWin()">';
		return operation;
	}
}]);

// 建立数据源，用来加载角色名称
var DateStore = new Ext.data.JsonStore({
	url : 'userinfo.do?actionType=doFindAllRole',
	method : 'POST',
	root : 'data',
	totalProperty : 'rowCount',
	fields : ['roleName', 'roleId']
});

// 表格
var userinfoUserGrid = new Ext.grid.GridPanel({
	store : userinfoUserStore,
	cm : userinfoUserColm,
	height : 300,
	stripeRows : true,
	pageSize : 15,
	loadMask : true,
	tbar : [{
		text : '添加',
		id : 'add',
		iconCls : 'add',
		pressed : false,
		handler : function() {
			var panel = new userinfoUserPanel();
			panel.create();
		},
		scope : this
	}, new Ext.Toolbar.Fill(), '用户名称', {
		xtype : 'textfield',
		name : 'usrName',
		width : 150
	}, '&nbsp;&nbsp;', '角色名称', {
		xtype : "combo",
		hiddenName : 'roleId',
		width : 150,
		store : DateStore,
		displayField : 'roleName',
		valueField : 'roleId',
		forceSelection : true,
		triggerAction : 'all',
		emptyText : '--请选择--',
		editable : true
	}, {
		text : '查询',
		iconCls : 'search',
		pressed : true,
		handler : function() {
			var panel = new userinfoUserPanel();
			panel.search();
		},
		scope : this
	}, '   '],
	bbar : new Ext.PagingToolbar({
		pageSize : 15,
		store : userinfoUserStore,
		grid : userinfoUserGrid,
		displayInfo : true,
		displayMsg : '当前显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});
// 自定义
CRM.userinfoManage.userinfoUser = Ext.extend(Ext.Panel, {
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
					this.closeWin();
				},
				scope : this
			}]
		});
		return win;
	},
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
	// 关闭
	closeWin : function() {
		if (this.win)
			this.win.close();
		this.win = null;
	},

	// 查询
	search : function() {
		userinfoUserStore.baseParams.usrName = Ext.get('usrName').getValue();
		userinfoUserStore.baseParams.roleId = Ext.get('roleId').getValue();
		userinfoUserStore.load({
			params : {
				start : 0,
				limit : 15
			}
		});
	},
	// 编辑
	edit : function() {
		var record = userinfoUserGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请选择要编辑的行!");
			return;
		}
		this.showWin();
		this.fp.form.loadRecord(record);
	},
	// 保存
	save : function() {
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : 'userinfo.do?actionType=doSaveorUpdate',
				method : 'POST',
				success : function(form, action) {
					Ext.Msg.alert("系统消息", action.result.msg, function() {
						this.closeWin();
						userinfoUserStore.reload();
					}, this);
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg);
				},
				scope : this
			});
		}
	},
	// 删除
	removeData : function() {
		var record = userinfoUserGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请先选择要删除的行!");
			return;
		}
		Ext.MessageBox.confirm("确认删除", "确认删除所选数据？", function(button) {
			if (button == "yes") {
				Ext.Ajax.request({
					url : this.baseUrl + '?actionType=doDel',
					params : {
						usrId : record.get("usrId")
					},
					method : 'POST',
					success : function(response) {
						Ext.Msg.alert("提示信息", response.responseText,
								function() {
									userinfoUserStore.reload();
								}, this);
					},
					scope : this
				});
			}
		}, this);
	},
	initComponent : function() {
		CRM.userinfoManage.userinfoUser.superclass.initComponent.call(this);
		// 右击表格事件
		userinfoUserGrid.addListener("rowContextmenu", this.showMenu, this);
		// 数据载
		userinfoUserStore.load({
			params : {
				start : 0,
				limit : 15
			}
		});
		DateStore.load();
		rightTextStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
		this.add(userinfoUserGrid);
	}
});

// 用户管理面板
userinfoUserPanel = Ext.extend(CRM.userinfoManage.userinfoUser, {
	id : 'userinfoUser',
	baseUrl : 'userinfo.do',
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
					columnWidth : .5,
					defaultType : 'textfield',
					items : [{
						name : 'usrName',
						fieldLabel : '用户名称',
						allowBlank : false,
						blankText : '用户名称为必填项'
					}, {
						name : 'usrPassword',
						inputType : 'password',
						fieldLabel : '用户密码',
						allowBlank : false,
						blankText : '用户密码为必填项'
					}, {
						xtype : 'hidden',
						name : 'usrId'
					}, {
						hiddenName : 'roleName',
						xtype : 'combo',
						fieldLabel : '角色名称',
						width : 125,
						allowBlank : false,
						blankText : '角色名称为必填项',
						store : DateStore,
						displayField : 'roleName',
						valueField : 'roleId',
						forceSelection : true,
						triggerAction : 'all',
						editable : false
					}, {
						name : 'usrFlag',
						fieldLabel : '标记',
						allowBlank : false,
						blankText : '标记为必填项',
						width : 125
					}]
				}]
			}]
		});
		return formPanel;
	},
	createWin : function() {
		return this.initWin(280, 220, '用户信息管理');
	},
	initComponent : function() {
		userinfoUserPanel.superclass.initComponent.call(this);
	}
});
