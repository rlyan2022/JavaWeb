Ext.namespace("CRM.custManage");
var oddOrderId;
function showOrdersDetailPanel() {
	var panel = new custOrdersPanel();
	panel.showOrdersDetailPanel();
}
var ordersStore = new Ext.data.JsonStore({
	url : 'customer.do?actionType=doFindOrders',
	root : "data",
	totalProperty : "rowCount",
	baseParams : {
		odrCustomer : null
	},
	remoteSort : true,
	fields : ["odrId", "odrCustomer", "odrDate", "odrAddr", "odrStatus",
			"operation"]
});
var ordersClom = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
	header : '订单编号',
	sortable : true,
	dataIndex : 'odrId',
	width : 150
}, {
	header : '日期',
	sortable : true,
	dataIndex : 'odrDate',
	width : 180,
	format : 'YYYY年mm月dd日'
}, {
	header : '送货地址',
	sortable : true,
	dataIndex : 'odrAddr',
	width : 205
}, {
	header : '状态',
	sortable : true,
	dataIndex : 'odrStatus',
	width : 180,
	renderer : function(value) {
		if (value == 1) {
			return '新创建'
		} else if (value == 5) {
			return '已发货'
		} else {
			return '已回款'
		}
	}
}, {
	header : '操作',
	dataIndex : 'operation',
	width : 115,
	renderer : function() {
		return '<img src="images/bt_detail.gif" onclick="showOrdersDetailPanel()"/>';
	}
}]);

var ordersGrid = new Ext.grid.GridPanel({
	store : ordersStore,
	cm : ordersClom,
	height : 300,
	stripeRows : true,
	pageSize : 10,

	bbar : new Ext.PagingToolbar({
		pageSize : 10,
		store : ordersStore,
		grid : ordersGrid,
		displayInfo : true,
		displayMsg : '显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});
CRM.custManage.custOrders = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,
	gridViewConfig : {
		animate : true
	},
	refresh : function() {
		ordersStore.removeAll();
		ordersStore.reload();
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
		var id = this.fp.form.findField("id").getValue();
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : this.baseUrl + '?actionType=doUpdate&id=' + id,
				method : 'POST',
				success : function(form, action) {
					Ext.Msg.alert("系统消息", action.result.data, function() {
						this.closeWin();
						ordersStore.reload();
					}, this);
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.data);
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
		var record = ordersGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请选择要编辑的行!");
			return;
		}
		this.showWin(show);
		this.fp.form.loadRecord(record);
	},
	removeData : function() {
		var record = ordersGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请先选择要删除的行!");
			return;
		}
		Ext.MessageBox.confirm("确认删除", "确认删除所选数据？", function(button) {
			if (button == "yes") {
				Ext.Ajax.request({
					url : this.baseUrl + '?actionType=doDel',
					params : {
						id : record.get("id"),
						name : record.get('name'),
						sex : record.get('sex'),
						branch : record.get('branch'),
						salary : record.get('salary'),
						telephone : record.get('telephone'),
						address : record.get('address'),
						remark : record.get('remark')
					},
					method : 'POST',
					success : function(response) {
						Ext.Msg.alert("提示信息", response.responseText,
								function() {
									ordersStore.reload();
								}, this);
					},
					scope : this
				});
			}
		}, this);
	},
	// 打开订单明细面板
	showOrdersDetailPanel : function() {
		var record = ordersGrid.getSelectionModel().getSelected();
		oddOrderId = record.get('odrId');
		var p = new custOrdersDetailPanel();
		p.id = 'ordersDetail';
		p.title = '订单明细';
		var n = main.add(p);
		main.setActiveTab(n);
	},
	initComponent : function() {
		CRM.custManage.custOrders.superclass.initComponent.call(this);
		this.add(ordersGrid);
		ordersGrid.addListener("rowContextmenu", this.showMenu, this);
		// 加载数据
		ordersStore.baseParams.odrCustomer = custName;
		ordersStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});

	}
});

custOrdersPanel = Ext.extend(CRM.custManage.custOrders, {
	id : 'custOrders',
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
							xtype : 'datefield',
							name : 'atvDate',
							fieldLabel : '时间',
							allowBlank : false,
							blankText : '时间为必填项',
							emptyText : '请选择时间',
							format : 'Y年m月d日'
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
							name : 'atv_remark',
							fieldLabel : '备注'
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
							xtype : 'htmleditor',
							name : 'atv_desc',
							fieldLabel : '详细信息'
						}]
					}]
				}]
			}]
		});
		return formPanel;
	},
	createWin : function() {
		return this.initWin(555, 490, '交往记录');
	},
	initComponent : function() {

		custOrdersPanel.superclass.initComponent.call(this);
	}
});