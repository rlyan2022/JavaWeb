// 销售机会管理
Ext.namespace("CRM.saleManage");
var salChance_chcId;
// 点击指派图标打开指派窗口
function showAssignChcWin() {
	if (currentRole == '2') {
		var panel = new saleChancePanel();
		panel.assign();
	} else {
		Ext.Msg.alert("警告", "您无权进行指派");
	}
}
function showEditChcWin() {
	var panel = new saleChancePanel();
	panel.edit();
}
function showDelChcWin() {
	var panel = new saleChancePanel();
	panel.removeData();
}
var customerNameStore = new Ext.data.JsonStore({
	url : 'sale.do?actionType=doFindAllCustomer',
	root : 'data',
	totalProperty : 'rowCount',
	fields : ['custName']
});
// 存储器
var salChanceStore = new Ext.data.JsonStore({
	id : "id",
	url : 'sale.do?actionType=doList',
	root : "data",// 数据源
	totalProperty : "rowCount",// 总行数
	// 参数
	baseParams : {
		chcCustName : null,
		chcTitle : null,
		chcLinkman : null
	},
	remoteSort : true,
	fields : ["chcId", "chcSource", "chcCustName", "chcTitle", "chcRate",
			"chcLinkman", "chcTel", "chcDesc", "chcCreateBy", "chcCreateDate",
			"chcDueTo", "chcDueDate", "chcStatus"]
});

var salChanceColm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
	header : '编号',
	sortable : true,
	dataIndex : 'chcId',
	width : 122
}, {
	header : '客户名称',
	sortable : true,
	dataIndex : 'chcCustName',
	width : 122
}, {
	header : '概要',
	sortable : true,
	dataIndex : 'chcTitle',
	width : 122
}, {
	header : '联系人',
	sortable : true,
	dataIndex : 'chcLinkman',
	width : 122
}, {
	header : '联系人电话',
	sortable : true,
	dataIndex : 'chcTel',
	width : 122
}, {
	header : '创建时间',
	sortable : true,
	dataIndex : 'chcCreateDate',
	width : 122
}, {
	header : '操作',
	dataIndex : 'chcStatus',
	renderer : function(value) {
		var operation = '';
		if (value == 1) {
			operation += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			operation += '<img src="images/bt_linkman.gif" title="指派" onclick="showAssignChcWin()"/>';
			operation += '&nbsp;&nbsp;&nbsp;'
					+ '<img src="images/bt_edit.gif" title="编辑" onclick="showEditChcWin()"/>';
			operation += '&nbsp;&nbsp;&nbsp;'
					+ '<img src="images/bt_del.gif" title="删除" onclick="showDelChcWin()">';
			return operation;
		}
	}
}]);
// 表格
var salChanceGrid = new Ext.grid.GridPanel({
	store : salChanceStore,
	cm : salChanceColm,
	height : 300,
	stripeRows : true,
	pageSize : 15,
	tbar : [{
		text : '新建',
		id : 'add',
		iconCls : 'add',
		pressed : false,
		handler : function() {
			var panel = new saleChancePanel();
			panel.create();
		},
		scope : this
	}, new Ext.Toolbar.Fill(), '客户名称', {
		xtype : 'textfield',
		name : 'chcCustName',
		width : 150
	}, '&nbsp;&nbsp;', '概要', {
		xtype : "textfield",
		name : 'chcTitle',
		width : 150
	}, '&nbsp;&nbsp;', '联系人', {
		xtype : 'textfield',
		name : 'chcLinkman',
		width : 150
	}, {
		text : '查询',
		iconCls : 'search',
		pressed : true,
		handler : function() {
			var panel = new saleChancePanel();
			panel.search();
		},
		scope : this
	}, '   '],
	bbar : new Ext.PagingToolbar({
		pageSize : 15,
		store : salChanceStore,
		grid : salChanceGrid,
		displayInfo : true,
		displayMsg : '当前显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});
// 自定义
CRM.saleManage.saleChance = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,
	// 初始化新建或编辑窗体
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
	// 初始化指派窗体
	initAssignWin : function(width, height, title) {
		var assignWin = new Ext.Window({
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
				handler : this.doAssign,
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
		return assignWin;
	},
	// 打开新建或编辑窗体
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
	create : function() {
		customerNameStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
		this.showWin();
		this.reset();
	},

	// 关闭新建或编辑窗口
	closeWin : function() {
		if (this.win)
			this.win.close();
		this.win = null;
	},
	// 关闭指派窗口
	closeAssignWin : function() {
		if (this.assignWin) {
			this.assignWin.close();
			this.assignWin = null;
		}
	},
	// 重置
	reset : function() {
		if (this.win)
			this.fp.form.reset();
	},
	// 加载编辑内容
	edit : function() {
		var record = salChanceGrid.getSelectionModel().getSelected();
		// if (!record) {
		// Ext.Msg.alert("提示", "请选择要编辑的行!");
		// return;
		// }
		this.showWin();
		this.fp.form.loadRecord(record);
	},
	// 保存
	save : function() {
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : 'sale.do?actionType=doSaveorUpdate',
				method : 'POST',
				params : {
					chcCreateBy : currentUser
				},
				success : function(form, action) {
					Ext.Msg.alert("系统消息", action.result.msg, function() {
						this.closeWin();
						salChanceStore.reload();
					}, this);
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg);
				},
				scope : this
			});
		}
	},
	// 加载指派窗体
	assign : function() {
		var record = salChanceGrid.getSelectionModel().getSelected();
		salChance_chcId = record.get('chcId');
		if (!record) {
			Ext.Msg.alert("提示", "请选择要指派的行!");
			return;
		}
		// 显示指派窗口
		if (!this.assignWin) {
			if (!this.fp) {
				this.fp = this.createAssignForm();
			}
			this.assignWin = this.createAssignWin();
			this.assignWin.on("close", function() {
				this.assignWin = null;
				this.fp = null;
			}, this);
		}
		this.assignWin.show();

	},
	// 进行指派操作
	doAssign : function() {
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : 'sale.do?actionType=doAssign',
				method : 'POST',
				params : {
					chcId : salChance_chcId
				},
				success : function(form, action) {
					Ext.Msg.alert("系统消息", action.result.msg, function() {
						this.closeAssignWin();
						salChanceStore.reload();
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
		var record = salChanceGrid.getSelectionModel().getSelected();
		// if (!record) {
		// Ext.Msg.alert("提示", "请先选择要删除的行!");
		// return;
		// }
		Ext.MessageBox.confirm("确认删除", "确认删除所选数据？", function(button) {
			if (button == "yes") {
				Ext.Ajax.request({
					url : this.baseUrl + '?actionType=doDel',
					params : {
						chcId : record.get("chcId")
					},
					method : 'POST',
					success : function(response) {
						Ext.Msg.alert("系统消息", response.responseText,
								function() {
									salChanceStore.reload();
								}, this);
					},
					scope : this
				});
			}
		}, this);
	},
	// 查询
	search : function() {
		salChanceStore.baseParams.chcCustName = Ext.get('chcCustName')
				.getValue();
		salChanceStore.baseParams.chcTitle = Ext.get('chcTitle').getValue();
		salChanceStore.baseParams.chcLinkman = Ext.get('chcLinkman').getValue();
		salChanceStore.load({
			params : {
				start : 0,
				limit : 15
			}
		});
	},
	initComponent : function() {

		CRM.saleManage.saleChance.superclass.initComponent.call(this);
		// 数据载
		salChanceStore.load({
			params : {
				start : 0,
				limit : 15
			}
		});
		this.add(salChanceGrid);
	}
});

// 销售机会管理面板
saleChancePanel = Ext.extend(CRM.saleManage.saleChance, {
	id : 'saleChance',
	baseUrl : 'sale.do',
	createAssignForm : function() {
		return assignForm = new Ext.form.FormPanel({
			labelWidth : 70,
			frame : true,
			autoHeight : true,
			resizable : false,
			labelAlign : 'right',
			defaultType : 'textfield',
			items : [{
				xtype : 'combo',
				name : 'chcDueTo',
				width : 125,
				fieldLabel : '指派给',
				store : new Ext.data.JsonStore({
					url : 'sale.do?actionType=doFindAllCstManager',
					root : 'data',
					totalProperty : 'rowCount',
					fields : ['manName']
				}),
				displayField : 'manName',
				pageSize : 10,
				forceSelection : true,
				triggerAction : 'all',
				editable : false,
				allowBlank : false,
				blankText : '请输入被指派人的姓名'
			}, {
				xtype : 'datefield',
				name : 'chcDueDate',
				fieldLabel : '指派时间',
				format : 'Y年m月d日',
				allowBlank : false,
				blankText : '指派时间为必填项',
				width : 125
			}]
		});
	},
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
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							xtype : 'combo',
							name : 'chcCustName',
							fieldLabel : '客户名称',
							width : 200,
							store : customerNameStore,
							displayField : 'custName',
							forceSelection : true,
							triggerAction : 'all',
							editable : false,
							pageSize : 10,
							allowBlank : false,
							blankText : '客户名称为必填项'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							xtype : 'numberfield',
							name : 'chcRate',
							fieldLabel : '成功机率',
							allowBlank : false,
							blankText : '成功机率为必填项',
							width:200,
							maxValue : 100,
							minValue : 1
						}]
					}, {
						xtype : 'hidden',
						name : 'chcStatus'
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							xtype : 'hidden',
							name : 'chcId'
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
							name : 'chcLinkman',
							width:200,
							fieldLabel : '联系人'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							xtype : 'textfield',
							name : 'chcTel',
							width:200,
							fieldLabel : '联系人电话'
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
							name : 'chcSource',
							fieldLabel : '机会来源',
							width:200
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcTitle',
							fieldLabel : '概要',
							allowBlank : false,
							width:200,
							blankText : '概要为必填项'
						}]
					}]
				}]
			}, {
				xtype : 'fieldset',
				title : '机会描述',
				autoHeight : true,
				items : [{
					layout : 'fit',
					xtype : 'htmleditor',
					height : 125,
					width : 560,
					name : 'chcDesc',
					hideLabel : true,
					allowBlank : false,
					blankText : '创建人为必填项'
				}]
			}]
		});
		return formPanel;
	},
	createAssignWin : function() {
		return this.initAssignWin(300, 140, '营销机会指派');
	},
	createWin : function() {
		return this.initWin(620, 360, '销售机会管理');
	},
	initComponent : function() {
		saleChancePanel.superclass.initComponent.call(this);
	}
});