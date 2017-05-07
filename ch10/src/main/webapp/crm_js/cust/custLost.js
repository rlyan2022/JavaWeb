Ext.namespace("CRM.custManage");
var lstId;
function showConfirmLostWin() {
	var panel = new custLostPanel();
	panel.confirmLost();
}
function showDefLostWin() {
	var panel = new custLostPanel();
	panel.DefLost();
}

var cstLostStore = new Ext.data.JsonStore({
	url : 'cstLost.do?actionType=doList',
	root : "data",
	totalProperty : "rowCount",
	baseParams : {
		lstCustName : null,
		lstCustManagerName : null,
		lstStatus : null
	},
	remoteSort : true,
	fields : ["lstId", "lstCustName", "lstCustManagerName", "lstLastOrderDate",
			"lstLostDate", "lstDelay", "lstStatus", "operation"]
});
var cstLostColm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
	header : '编号',
	sortable : true,
	dataIndex : 'lstId',
	width : 120
}, {
	header : '客户',
	sortable : true,
	dataIndex : 'lstCustName',
	width : 120
}, {
	header : '客户经理',
	sortable : true,
	dataIndex : 'lstCustManagerName',
	width : 120
}, {
	header : '上次下单时间',
	sortable : true,
	dataIndex : 'lstLastOrderDate',
	width : 120,
	renderer : function(value) {
		var date = new Date();
		date.setYear((value.year) + 1900);
		date.setMonth(value.month);
		date.setDate(value.date);
		return date.format("Y年m月d日");
	}
}, {
	header : '确认流失时间',
	sortable : true,
	dataIndex : 'lstLostDate',
	width : 120
// renderer : function(value) {
		// var date = new Date();
		// date.setYear((value.year) + 1900);
		// date.setMonth(value.month);
		// date.setDate(value.date);
		// return date.format("Y年m月d日");
		// }
		}, {
			header : '状态',
			sortable : true,
			dataIndex : 'lstStatus',
			width : 120
		}, {
			header : '操作',
			dataIndex : 'lstStatus',
			renderer : function(value) {
				var operation = '';
				if (value == "已经流失") {
					operation += '';
				} else {
					operation += '<img src="images/bt_confirm.gif" title="确认流失" onclick="showConfirmLostWin()"/>';
					operation += '<img src="images/bt_relay.gif" title="暂缓流失" onclick="showDefLostWin()"/>';
				}
				return operation;

			}
		}]);
var cstLostGrid = new Ext.grid.GridPanel({
	store : cstLostStore,
	cm : cstLostColm,
	height : 300,
	stripeRows : true,
	pageSize : 10,
	tbar : [new Ext.Toolbar.Fill(), '客户', {
		xtype : 'textfield',
		name : 'lstCustName',
		width : 180,
		scope : this
	}, '客户经理', {
		xtype : 'textfield',
		name : 'lstCustManagerName',
		width : 180,
		scope : this
	}, '状态', {
		xtype : 'combo',
		name : 'lstStatus',
		width : 120,
		store : new Ext.data.SimpleStore({
			fields : ['lst_status'],
			data : [['全部'], ['预警'], ['暂缓流失'], ['确认流失']]
		}),
		displayField : 'lstStatus',
		mode : 'local',
		forceSelection : true,
		editable : false,
		triggerAction : 'all'
	}, {
		text : '查询',
		id : 'search',
		iconCls : 'search',
		handler : function() {
			this.search();
		},
		scope : this
	}],
	bbar : new Ext.PagingToolbar({
		pageSize : 10,
		store : cstLostStore,
		grid : cstLostGrid,
		displayInfo : true,
		displayMsg : '显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});
CRM.custManage.custLost = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,
	gridViewConfig : {
		animate : true
	},
	refresh : function() {
		cstLostStore.removeAll();
		cstLostStore.reload();
	},
	// 初始化确认流失窗体
	initConfirmLostWin : function(width, height, title) {
		var confirmLostWin = new Ext.Window({
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
				handler : this.doConfirmLost,
				tooltip : '点击该按钮将执行确认操作',
				scope : this
			}, {
				text : "清空",
				handler : this.resetConfirmLost,
				scope : this
			}, {
				text : "取消",
				id : 'cancel',
				handler : function() {
					this.closeConfirmLostWin('cancel');
				},
				scope : this
			}]
		});
		return confirmLostWin;
	},
	// 打开确认流失窗体
	showConfirmLostWin : function() {
		if (!this.confirmLostWin) {
			if (!this.fp) {
				this.fp = this.createConfirmLostForm();
			}
			this.confirmLostWin = this.createConfirmLostWin();
			this.confirmLostWin.on("close", function() {
				this.confirmLostWin = null;
				this.fp = null;
			}, this);
		}
		this.confirmLostWin.show();
	},
	// 关闭确认流失窗体
	closeConfirmLostWin : function() {
		if (this.confirmLostWin)
			this.confirmLostWin.close();
		this.confirmLostWin = null;
	},
	// 加载确认流失窗体
	confirmLost : function() {
		var record = cstLostGrid.getSelectionModel().getSelected();
		lstId = record.get('lstId');
		if (!record) {
			Ext.Msg.alert("提示", "请选择要编辑的行!");
			return;
		}
		this.showConfirmLostWin();
		this.fp.form.loadRecord(record);
	},
	// 执行确认流失操作
	doConfirmLost : function() {
		var record = cstLostGrid.getSelectionModel().getSelected();
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : this.baseUrl + '?actionType=doDefLostorConfirmLost',
				method : 'POST',
				params : {
					lstId : lstId,
					type : 'confirmLost'
				},
				success : function(form, action) {
					Ext.Msg.alert("系统消息", action.result.msg, function() {
						this.closeConfirmLostWin();
						cstLostStore.reload();
					}, this);
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg);
				},
				scope : this
			});
		}
	},
	// 确认流失窗体内容重置
	resetDefLost : function() {
		if (this.win)
			this.fp.form.confirmLostWin();
	},
	// ////////////////////////////////////////////////////////////////////////////////////////
	// 初始化暂缓流失窗体
	initDefLostWin : function(width, height, title) {
		var defLostWin = new Ext.Window({
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
				handler : this.doDefLost,
				tooltip : '点击该按钮将执行确认操作',
				scope : this
			}, {
				text : "清空",
				handler : this.resetDefLost,
				scope : this
			}, {
				text : "取消",
				id : 'cancel',
				handler : function() {
					this.closeDefLostWin();
				},
				scope : this
			}]
		});
		return defLostWin;
	},
	// 打开暂缓流失窗体
	showDefLostWin : function() {
		if (!this.defLostWin) {
			if (!this.fp) {
				this.fp = this.createDefLostForm();
			}
			this.defLostWin = this.createDefLostWin();
			this.defLostWin.on("close", function() {
				this.defLostWin = null;
				this.fp = null;
			}, this);
		}
		this.defLostWin.show();
	},
	// 关闭暂缓流失窗体
	closeDefLostWin : function() {
		if (this.defLostWin)
			this.defLostWin.close();
		this.defLostWin = null;
	},
	// 加载暂缓流失窗体
	DefLost : function() {
		var record = cstLostGrid.getSelectionModel().getSelected();
		lstId = record.get('lstId');
		if (!record) {
			Ext.Msg.alert("提示", "请选择要编辑的行!");
			return;
		}
		this.showDefLostWin();
		this.fp.form.loadRecord(record);
	},
	// 执行暂缓流失操作
	doDefLost : function() {
		var record = cstLostGrid.getSelectionModel().getSelected();
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : this.baseUrl + '?actionType=doDefLostorConfirmLost',
				method : 'POST',
				params : {
					lstId : lstId,
					type : 'defLost'
				},
				success : function(form, action) {
					Ext.Msg.alert("系统消息", action.result.msg, function() {
						this.closeDefLostWin();
						cstLostStore.reload();
					}, this);
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg);
				},
				scope : this
			});
		}
	},
	// 暂缓流失窗体内容重置
	resetDefLost : function() {
		if (this.win)
			this.fp.form.confirmLostWin();
	},

	search : function() {
		cstLostStore.baseParams.schBranch = Ext.get('schBranch').getValue();
		cstLostStore.baseParams.schName = Ext.get('schName').getValue();
		cstLostStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
	},

	initComponent : function() {
		CRM.custManage.custLost.superclass.initComponent.call(this);
		cstLostGrid.addListener("rowContextmenu", this.showMenu, this);
		cstLostStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
		this.add(cstLostGrid);
	}
});

custLostPanel = Ext.extend(CRM.custManage.custLost, {
	id : 'custLostPanel',
	baseUrl : 'cstLost.do',
	createConfirmLostForm : function() {
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
					layout : 'column',
					border : false,
					items : [{
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'lstId',
							disabled : true,
							fieldLabel : '编号'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'lstCustName',
							disabled : true,
							fieldLabel : '客户'
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
							name : 'lstCustManagerName',
							disabled : true,
							fieldLabel : '客户经理'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'lstLast0rderDate',
							disabled : true,
							fieldLabel : '上次下单时间'
						}]
					}]
				}, {
					layout : 'column',
					border : false,
					items : [{
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							xtype : 'textarea',
							name : 'lstDelay',
							disabled : true,
							fieldLabel : '暂缓措施',
							width : 375,
							height : 100
						}]
					}]
				}]
			}, {
				xtype : 'fieldset',
				title : '流失原因',
				autoHeight : true,
				items : [{
					layout : 'fit',
					xtype : 'htmleditor',
					height : 125,
					width : 500,
					name : 'lstReason',
					hideLabel : true,
					allowBlank : false,
					blankText : '流失原因为必填项'
				}]
			}]
		});
		return formPanel;
	},
	createDefLostForm : function() {
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
					layout : 'column',
					border : false,
					items : [{
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'lstId',
							disabled : true,
							fieldLabel : '编号'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'lstCustName',
							disabled : true,
							fieldLabel : '客户'
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
							name : 'lstCustManagerName',
							disabled : true,
							fieldLabel : '客户经理'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'lstLast0rderDate',
							disabled : true,
							fieldLabel : '上次下单时间'
						}]
					}]
				}, {
					layout : 'column',
					border : false,
					items : [{
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							xtype : 'textarea',
							name : 'lstDelay',
							disabled : true,
							fieldLabel : '暂缓措施',
							width : 375,
							height : 100
						}]
					}]
				}]
			}, {
				xtype : 'fieldset',
				title : '追加暂缓措施',
				autoHeight : true,
				items : [{
					layout : 'fit',
					xtype : 'htmleditor',
					height : 125,
					width : 500,
					name : 'lstAddDelay',
					hideLabel : true,
					allowBlank : false,
					blankText : '追加暂缓措施为必填项'
				}]
			}]
		});
		return formPanel;
	},
	createConfirmLostWin : function() {
		return this.initConfirmLostWin(555, 480, '确认流失');
	},
	createDefLostWin : function() {
		return this.initDefLostWin(555, 480, '暂缓流失');
	},
	initComponent : function() {
		custLostPanel.superclass.initComponent.call(this);
	}
});
