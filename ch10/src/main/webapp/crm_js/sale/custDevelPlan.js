Ext.namespace("CRM.saleManage");
var chcId; // 全局变量，用来获取销售机会编号
// 显示制定开发计划窗体。。
function showCreatePlanWin() {
	var panel = new custdevelPlanPanel();
	panel.edit();
};
// 显示执行开发计划的窗体。。。。
function showExePlanWin() {
	if (currentRole == '2') {
		var panel = new custdevelPlanPanel();
		panel.exePlan();
	} else {
		Ext.Msg.alert("警告", "您无权执行开发计划");
	}

};
// 显示查看窗体。。。。
function showSeeWin() {
	var panel = new custdevelPlanPanel();
	panel.seePlan();
};
// 点击开发成功按钮事件。。。
function showSuccess() {
	if (currentRole == '3') {
		var panel = new custdevelPlanPanel();
		panel.success();
	} else {
		Ext.Msg.alert("警告", "您无权进行此操作");
	}
};

// 销售机会的Store。。。。。。
var custDevelPlanStore = new Ext.data.JsonStore({
	id : "id",
	url : 'plan.do?actionType=doList',
	root : "data",
	totalProperty : "rowCount",
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

// 计划项的store。。。。
var planStore = new Ext.data.JsonStore({
	url : 'plan.do?actionType=doFindTodo',
	root : "data",
	baseParams : {
		chcId : null
	},
	totalProperty : "rowCount",
	remoteSort : true,
	fields : ["plaId", "plaChcId", "plaDate", "plaTodo", "plaResult",
			"operation"]
});

// 显示销售机会信息的表格。。。
var custDevelPlanClom = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
	header : '编号',
	sortable : true,
	dataIndex : 'chcId',
	width : 90
}, {
	header : '客户名称',
	sortable : true,
	dataIndex : 'chcCustName',
	width : 105
}, {
	header : '概要',
	sortable : true,
	dataIndex : 'chcTitle',
	width : 105
}, {
	header : '联系人',
	sortable : true,
	dataIndex : 'chcLinkman',
	width : 105
}, {
	header : '联系人电话',
	sortable : true,
	dataIndex : 'chcTel',
	width : 105
}, {
	header : '创建时间',
	sortable : true,
	dataIndex : 'chcCreateDate',
	width : 105
}, {
	header : '状态',
	sortable : true,
	dataIndex : 'chcStatus',
	width : 105,
	renderer : function(value) {
		if (value == 1) {
			return '未开发'
		}
		if (value == 2) {
			return '开发中'
		}
		if (value == 3 || value == 4) {
			return '已归档'
		}
	}
}, {
	header : '操作',
	dataIndex : 'chcStatus',
	renderer : function(value) {
		var operation = '';
		if (value == 3 || value == 4) {
			operation += '<img src="images/bt_detail.gif" title="查看" onclick="showSeeWin()"/>';
		} else {
			operation += '<img src="images/bt_plan.gif" title="制定计划" onclick="showCreatePlanWin()"/>';
			operation += '<img src="images/bt_feedback.gif" title="执行计划" onclick="showExePlanWin()"/>';
			operation += '<img src="images/bt_yes.gif" title="开发成功" onclick="showSuccess()"/>';
		}
		return operation;
	}
}]);

// 查询及状态栏。。。。
var custDevelPlanGrid = new Ext.grid.GridPanel({
	store : custDevelPlanStore,
	cm : custDevelPlanClom,
	height : 300,
	stripeRows : true,
	pageSize : 10,
	tbar : [new Ext.Toolbar.Fill(), '客户名称', {
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
			var panel = new custdevelPlanPanel();
			panel.search();
		},
		scope : this
	}, '   '],
	bbar : new Ext.PagingToolbar({
		pageSize : 10,
		store : custDevelPlanStore,
		grid : custDevelPlanGrid,
		displayInfo : true,
		displayMsg : '当前显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});

// 显示计划项的表格。。。。
var planGrid = new Ext.grid.GridPanel({
	store : planStore,
	cm : new Ext.grid.ColumnModel([{
		header : '编号',
		dataIndex : 'plaId',
		width : 40
	}, {
		header : '日期',
		sortable : true,
		dataIndex : 'plaDate',
		width : 100
	}, {
		header : '计划项',
		sortable : true,
		dataIndex : 'plaTodo',
		width : 250
	}, {
		header : '操作',
		dataIndex : 'operation',
		renderer : function() {
			return '<img src="images/bt_edit.gif" title="编辑" onclick="showPlanEditWin()"/>'
					+ '<img src="images/bt_del.gif" title="删除" onclick="del()"/>';
		}
	}]),
	autoHeight : true,
	width : 440,
	stripeRows : true,
	pageSize : 10,
	bbar : new Ext.PagingToolbar({
		pageSize : 3,
		store : planStore,
		displayInfo : true,
		displayMsg : '显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});
planGrid.on('celldblclick', showPlanEditWin, this);

// 显示执行结果的表格。。。。
var exePlanGrid = new Ext.grid.GridPanel({
	store : planStore,
	cm : new Ext.grid.ColumnModel([{
		header : '编号',
		dataIndex : 'plaId',
		width : 40
	}, {
		header : '日期',
		sortable : true,
		dataIndex : 'plaDate',
		width : 100
	}, {
		header : '计划项',
		sortable : true,
		dataIndex : 'plaTodo',
		width : 130
	}, {
		header : '执行效果',
		dataIndex : 'plaResult',
		width : 170,
		renderer : function(value) {
			if (value == "") {
				return '<img src="images/bt_feedback.gif" onclick="showPlaResultWin()"/>';
			} else {
				return value;
			}
		}
	}]),
	autoHeight : true,
	width : 440,
	stripeRows : true,
	pageSize : 10,
	loadMask : true,
	bbar : new Ext.PagingToolbar({
		pageSize : 3,
		store : planStore,
		displayInfo : true,
		displayMsg : '显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});
var plaResultWin;
// 执行效果的窗体。。。
planResultWin = Ext.extend(Ext.Window, {
	title : '执行效果',
	width : 530,
	height : 230,
	modal : true,
	buttonAlign : 'center',
	createForm : function() {
		return new Ext.form.FormPanel({
			textAlign : 'left',
			defaultType : 'htmleditor',
			frame : true,
			items : [{
				name : 'plaResult',
				hideLabel : true,
				width : 520,
				// value : record.get('plaResult'),
				allowBlank : false,
				blankText : '执行效果不能为空'
			}]
		});
	},
	// 保存执行效果的操作。。。
	save : function() {
		var record = exePlanGrid.getSelectionModel().getSelected();
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : 'plan.do?actionType=doSaveResult',
				params : {
					plaId : record.get('plaId')
				},
				success : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg, function() {
						plaResultWin.close();
						planStore.reload();
					})
				},
				failure : function(form, action) {
					form.reset();
					Ext.Msg.alert('警告', action.result.msg);
				}
			});
		}
	},
	initComponent : function() {
		planResultWin.superclass.initComponent.call(this);
		this.fp = this.createForm();
		this.add(this.fp);
		this.addButton('保存', this.save, this);
	}
});
// 执行效果。。。。
function showPlaResultWin() {
	plaResultWin = new planResultWin();
	plaResultWin.show();
}
// 显示开发计划信息的表格。。。。
var showPlanGrid = new Ext.grid.GridPanel({
	store : planStore,
	cm : new Ext.grid.ColumnModel([{
		header : '日期',
		sortable : true,
		dataIndex : 'plaDate',
		width : 120
	}, {
		header : '计划项',
		sortable : true,
		dataIndex : 'plaTodo',
		width : 160
	}, {
		header : '执行效果',
		sortable : true,
		dataIndex : 'plaResult',
		width : 160
	}]),
	autoHeight : true,
	width : 440,
	stripeRows : true,
	pageSize : 10,
	bbar : new Ext.PagingToolbar({
		pageSize : 3,
		store : planStore,
		displayInfo : true,
		displayMsg : '显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});

var win;
// 修改计划项的窗体。。。
planWin = Ext.extend(Ext.Window, {
	title : '修改计划项',
	width : 530,
	height : 230,
	modal : true,
	buttonAlign : 'center',
	createForm : function() {
		var record = planGrid.getSelectionModel().getSelected();
		return new Ext.form.FormPanel({
			textAlign : 'left',
			defaultType : 'htmleditor',
			frame : true,
			items : [{
				name : 'plaTodo',
				hideLabel : true,
				width : 520,
				value : record.get('plaTodo'),
				allowBlank : false,
				blankText : '计划项不能为空'
			}]
		});
	},
	// 保存计划项的操作。。。
	save : function() {
		var record = planGrid.getSelectionModel().getSelected();
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : 'plan.do?actionType=doSaveorUpdate',
				params : {
					chcId : chcId,
					plaId : record.get('plaId'),
					plaDate : record.get('plaDate')
				},
				success : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg, function() {
						win.close();
						planStore.reload();
					})
				},
				failure : function(form, action) {
					form.reset();
					Ext.Msg.alert('警告', action.result.msg);
				}
			});
		}
	},
	initComponent : function() {
		planWin.superclass.initComponent.call(this);
		this.fp = this.createForm();
		this.add(this.fp);
		this.addButton('保存', this.save, this);
	}
});
// 修改计划项。。。。
function showPlanEditWin() {
	win = new planWin();
	win.show();
}

// 删除计划项。。。
function del() {
	var panel = new custdevelPlanPanel();
	panel.del();
}

CRM.saleManage.custDevelPlan = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,
	gridViewConfig : {
		animate : true
	},
	// 刷新。。。。
	refresh : function() {
		custDevelPlanStore.removeAll();
		custDevelPlanStore.reload();
	},
	// 制定开发计划窗体中的操作。。。
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
				handler : this.savePlan,
				tooltip : '点击该按钮将执行确认操作',
				scope : this
			}]
		});
		return win;
	},
	// 执行开发计划中的操作。。。
	initExePlanWin : function(width, title) {
		var win = new Ext.Window({
			width : width,
			autoHeight : true,
			buttonAlign : 'center',
			title : title,
			modal : true,
			closeAction : 'hide',
			resizable : false,
			plain : true,
			items : [this.fp],
			buttons : [{
				text : '开发成功',
				handler : this.success,
				tooltip : '点击该按钮此计划将开发成功',
				scope : this
			}, {
				text : '终止开发',
				handler : this.failure,
				tooltip : '点击此按钮此计划将开发失败',
				scope : this
			}]
		});
		return win;
	},
	// 查看窗体中的操作。。。。
	initSeeWin : function(width, title) {
		var win = new Ext.Window({
			width : width,
			autoHeight : true,
			title : title,
			modal : true,
			closeAction : 'hide',
			resizable : false,
			plain : true,
			items : [this.fp]
		});
		return win;
	},
	// 显示制定开发计划的窗体。。。
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
	// 关闭窗体操作。。。
	closeWin : function(show) {
		if (this.win)
			this.win.close(show);
		this.win = null;
	},
	// 显示执行开发计划的窗体。。。
	showExePlanWin : function() {
		if (!this.win) {
			if (!this.fp) {
				this.fp = this.createExePlanForm();
			}
			this.win = this.createExePlanWin();
			this.win.on("close", function() {
				this.win = null;
				this.fp = null;
			}, this);
		}
		this.win.show();
	},
	// 显示查看窗体。。。
	showSeeWin : function() {
		if (!this.win) {
			if (!this.fp) {
				this.fp = this.createSeeForm();
			}
			this.win = this.createSeeWin();
			this.win.on("close", function() {
				this.win = null;
				this.fp = null;
			}, this);
		}
		this.win.show();
	},
	createExeWin : function() {
		this.showExePlanWin();
		this.reset();
	},
	create : function() {
		this.showWin();
		this.reset();
	},
	createSee : function() {
		this.showSeeWin();
		this.reset();
	},
	// 执行计划时开发成功操作。。。
	success : function() {
		var record = exePlanGrid.getSelectionModel().getSelected();
		Ext.MessageBox.confirm("确认", "确认开发成功？", function(button) {
			if (button == "yes") {
				Ext.Ajax.request({
					url : this.baseUrl + '?actionType=doSuccessorFailure',
					params : {
						chcId : chcId,
						type : 'success'
					},
					method : 'POST',
					success : function(response) {
						Ext.Msg.alert("提示信息", response.responseText,
								function() {
									this.closeWin();
									custDevelPlanStore.reload();
								}, this);
					},
					scope : this
				});
			}
		}, this);
	},
	// 执行开发计划时开发失败操作。。。
	failure : function() {
		var record = exePlanGrid.getSelectionModel().getSelected();
		Ext.MessageBox.confirm("确认", "确认终止开发？", function(button) {
			if (button == "yes") {
				Ext.Ajax.request({
					url : this.baseUrl + '?actionType=doSuccessorFailure',
					params : {
						chcId : chcId,
						type : 'failure'
					},
					method : 'POST',
					success : function(response) {
						Ext.Msg.alert("提示信息", response.responseText,
								function() {
									this.closeWin();
									custDevelPlanStore.reload();
								}, this);
					},
					scope : this
				});
			}
		}, this);
	},
	// 重置。。。
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
	// 主面板的查询操作。。。。
	search : function() {
		custDevelPlanStore.baseParams.chcCustName = Ext.get('chcCustName')
				.getValue();
		custDevelPlanStore.baseParams.chcTitle = Ext.get('chcTitle').getValue();
		custDevelPlanStore.baseParams.chcLinkman = Ext.get('chcLinkman')
				.getValue();
		custDevelPlanStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
	},
	// 制定开发计划。。。。
	edit : function() {
		var record = custDevelPlanGrid.getSelectionModel().getSelected();
		chcId = record.get('chcId');
		if (!record) {
			Ext.Msg.alert("提示", "请选择要编辑的行!");
			return;
		}
		this.showWin();
		this.fp.form.loadRecord(record);
		planStore.baseParams.chcId = chcId;
		planStore.load({
			params : {
				start : 0,
				limit : 3
			}
		});
	},
	// 添加计划项时的保存操作。。。
	savePlan : function() {
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存。。。。',
				url : 'plan.do?actionType=doSaveorUpdate',
				params : {
					chcId : chcId
				},
				success : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg, function() {
						planStore.reload();
					})
				},
				failure : function(form, action) {
					form.reset();
					Ext.Msg.alert('警告', action.result.msg);
				}
			})
		}
	},
	// 执行开发计划。。。。
	exePlan : function() {
		var record = custDevelPlanGrid.getSelectionModel().getSelected();
		chcId = record.get('chcId');
		if (!record) {
			Ext.Msg.alert("提示", "请选择要编辑的行!");
			return;
		}
		this.showExePlanWin();
		this.fp.form.loadRecord(record);
		planStore.baseParams.chcId = chcId;
		planStore.load({
			params : {
				start : 0,
				limit : 3
			}
		});
	},
	// 计划开发结束后查看操作。。。。
	seePlan : function() {
		var record = custDevelPlanGrid.getSelectionModel().getSelected();
		chcId = record.get('chcId');
		plaResult = record.get('plaResult');
		if (!record) {
			Ext.Msg.alert("提示", "请选择要编辑的行!");
			return;
		}
		this.showSeeWin();
		this.fp.form.loadRecord(record);
		planStore.baseParams.chcId = chcId;
		planStore.baseParams.plaResult = plaResult;
		planStore.load({
			params : {
				start : 0,
				limit : 3
			}
		});
	},
	// 删除操作。。。。。
	del : function() {
		var record = planGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请先选择要删除的行!");
			return;
		}
		Ext.MessageBox.confirm("确认删除", "确认删除所选数据？", function(button) {
			if (button == "yes") {
				Ext.Ajax.request({
					url : this.baseUrl + '?actionType=doDel',
					params : {
						plaId : record.get("plaId")
					},
					method : 'POST',
					success : function(response) {
						Ext.Msg.alert("提示信息", response.responseText,
								function() {
									planStore.reload();
								}, this);
					},
					scope : this
				});
			}
		}, this);
	},
	// 主面板的合成。。。。
	initComponent : function() {
		CRM.saleManage.custDevelPlan.superclass.initComponent.call(this);
		this.add(custDevelPlanGrid);
		custDevelPlanStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
	}
});

custdevelPlanPanel = Ext.extend(CRM.saleManage.custDevelPlan, {
	id : 'custdevelPlan',
	baseUrl : 'plan.do',
	// 执行开发计划的窗体。。。。
	createExePlanForm : function() {
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
					border : true,
					items : [{
						xtype : 'hidden',
						name : 'plaId'
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcId',
							fieldLabel : '编号',
							disabled : true
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcCustName',
							fieldLabel : '客户名称',
							disabled : true
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
							disabled : true
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcRate',
							fieldLabel : '成功机率',
							minValue : 1,
							disabled : true
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
							fieldLabel : '联系人',
							disabled : true
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							xtype : 'numberfield',
							name : 'chcTel',
							fieldLabel : '联系人电话',
							disabled : true
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
							name : 'chcCreateBy',
							fieldLabel : '创建人',
							disabled : true
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcCreateDate',
							fieldLabel : '创建时间',
							disabled : true
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
							name : 'chcDueTo',
							fieldLabel : '指派给',
							disabled : true
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcDueDate',
							fieldLabel : '指派时间',
							disabled : true
						}]
					}]
				}, {
					layout : 'column',
					border : false,
					items : [{
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcTitle',
							fieldLabel : '概要',
							width : 350,
							disabled : true
						}]
					}]
				}, {
					layout : 'column',
					border : false,
					items : [{
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcDesc',
							fieldLabel : '机会描述',
							width : 350,
							disabled : true
						}]
					}]
				}]
			}, {
				xtype : 'fieldset',
				title : '   ',
				autoHeight : true,
				items : [{
					layout : 'column',
					border : false,
					items : [exePlanGrid]
				}]
			}]
		});
		return formPanel;
	},
	// 制定开发计划的窗体。。。。
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
					border : true,
					items : [{
						xtype : 'hidden',
						name : 'plaId'
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcId',
							fieldLabel : '编号',
							disabled : true
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcCustName',
							fieldLabel : '客户名称',
							disabled : true
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
							disabled : true
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcRate',
							fieldLabel : '成功机率',
							minValue : 1,
							disabled : true
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
							fieldLabel : '联系人',
							disabled : true
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							xtype : 'numberfield',
							name : 'chcTel',
							fieldLabel : '联系人电话',
							disabled : true
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
							name : 'chcCreateBy',
							fieldLabel : '创建人',
							disabled : true
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcCreateDate',
							fieldLabel : '创建时间',
							disabled : true
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
							name : 'chcDueTo',
							fieldLabel : '指派给',
							disabled : true
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcDueDate',
							fieldLabel : '指派时间',
							disabled : true
						}]
					}]
				}, {
					layout : 'column',
					border : false,
					items : [{
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcTitle',
							fieldLabel : '概要',
							width : 350,
							disabled : true
						}]
					}]
				}, {
					layout : 'column',
					border : false,
					items : [{
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcDesc',
							fieldLabel : '机会描述',
							width : 350,
							disabled : true
						}]
					}]
				}]
			}, {
				xtype : 'fieldset',
				title : '   ',
				autoHeight : true,
				items : [{
					layout : 'column',
					border : false,
					items : [planGrid]
				}, {}, {
					layout : 'column',
					border : false,
					items : [{
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'plaTodo',
							fieldLabel : '添加计划项',
							width : 350,
							labelWidth : 100,
							allowBlank : false,
							blankText : '计划项不能为空'
						}]
					}]
				}]
			}]
		});
		return formPanel;
	},
	// 查看窗体。。。。
	createSeeForm : function() {
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
					border : true,
					items : [{
						xtype : 'hidden',
						name : 'plaId'
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcId',
							fieldLabel : '编号',
							disabled : true
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcCustName',
							fieldLabel : '客户名称',
							disabled : true
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
							disabled : true
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcRate',
							fieldLabel : '成功机率',
							minValue : 1,
							disabled : true
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
							fieldLabel : '联系人',
							disabled : true
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							xtype : 'numberfield',
							name : 'chcTel',
							fieldLabel : '联系人电话',
							disabled : true
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
							name : 'chcCreateBy',
							fieldLabel : '创建人',
							disabled : true
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcCreateDate',
							fieldLabel : '创建时间',
							disabled : true
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
							name : 'chcDueTo',
							fieldLabel : '指派给',
							disabled : true
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcDueDate',
							fieldLabel : '指派时间',
							disabled : true
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
							name : 'chcTitle',
							fieldLabel : '概要',
							disabled : true
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcStatus',
							fieldLabel : '状态',
							renderer : function(value) {
								var option = '';
								if (value == 3) {
									option += '开发成功'
								}
								if (value == 4) {
									option += '开发失败'
								}
								return option;
							},
							disabled : true
						}]
					}]
				}, {
					layout : 'column',
					border : false,
					items : [{
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'chcDesc',
							fieldLabel : '机会描述',
							disabled : true,
							width : 350
						}]
					}]
				}]
			}, {
				xtype : 'fieldset',
				title : '   ',
				autoHeight : true,
				items : [{
					layout : 'column',
					border : false,
					items : [showPlanGrid]
				}]
			}]
		});
		return formPanel;
	},
	createWin : function() {
		return this.initWin(500, '制定开发计划');
	},
	createExePlanWin : function() {
		return this.initExePlanWin(500, '执行开发计划');
	},
	createSeeWin : function() {
		return this.initSeeWin(500, '查看');
	},
	initComponent : function() {
		custdevelPlanPanel.superclass.initComponent.call(this);
	}
});