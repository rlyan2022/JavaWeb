Ext.namespace("CRM.custManage");
var custNo;
var custName;
// 点击图标打开联系人面板
function openCstLinkmanPanel() {
	var panel = new custInfoPanel();
	panel.showPanel('cstLinkman', '联系人', custLinkmanPanel);
}
// 点击图标打开客户交往记录面板
function openCstActivityPanel() {
	var panel = new custInfoPanel();
	panel.showPanel('cstActivity', '客户交往记录', custActivityPanel);
}
// 点击图标打开历史订单面板
function showCustOrdersPanel() {
	var panel = new custInfoPanel();
	panel.showPanel('orders', '历史订单', custOrdersPanel);
}
// 点击图标打开编辑窗体
function showCustEditWin() {
	var panel = new custInfoPanel();
	panel.edit();
}
// 点击图标打开删除窗体
function showCustDelWin() {
	var panel = new custInfoPanel();
	panel.removeData();
}

var custInfoStore = new Ext.data.JsonStore({
	id : "id",
	url : 'customer.do?actionType=doList',
	root : "data",
	totalProperty : "rowCount",
	baseParams : {
		custNo : null,
		custName : null,
		custManName : null,
		custLevel : null,
		custRegion : null
	},
	remoteSort : true,
	fields : ["custNo", "custName", "custRegion", "custManagerName",
			"custLevel", "custSatisfy", "custCredit", "custAddr", "custZip",
			"custTel", "custFax", "custWebsite", "custLicenceNo",
			"custChieftain", "custBankroll", "custTurnover", "custBank",
			"custBankAccount", "custLocalTaxNo", "custNationalTaxNo",
			"operation"]
});
var custInfColm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
	header : '客户编号',
	sortable : true,
	dataIndex : 'custNo',
	width : 120
}, {
	header : '名称',
	sortable : true,
	dataIndex : 'custName',
	width : 193
}, {
	header : '地区',
	sortable : true,
	dataIndex : 'custRegion',
	width : 150
}, {
	header : '客户经理',
	sortable : true,
	dataIndex : 'custManagerName',
	width : 150
}, {
	header : '客户等级',
	sortable : true,
	dataIndex : 'custLevel',
	width : 120
}, {
	header : '操作',
	dataIndex : 'operation',
	renderer : function() {
		var operation = "<img src='images/bt_edit.gif' title='编辑' onclick='showCustEditWin()'/>";
		operation += "<img src='images/bt_linkman.gif' title='联系人' onclick='openCstLinkmanPanel()'/>";
		operation += "<img src='images/bt_acti.gif' title='交往记录' onclick='openCstActivityPanel()'/>";
		operation += "<img src='images/bt_orders.gif' title='历史订单' onclick='showCustOrdersPanel()'/>";
		operation += "<img src='images/bt_del.gif' title='删除' onclick='showCustDelWin()'/>";
		return operation;
	}
}]);
var custInfoGrid = new Ext.grid.GridPanel({
	store : custInfoStore,
	cm : custInfColm,
	height : 300,
	stripeRows : true,
	pageSize : 10,
	tbar : [ {
		text : '新建',
		id : 'add',
		pressed : false,
		iconCls : 'add',
		handler : function() {
			var win = new custInfoPanel();
			win.create();
		},
		scope : this
	}, '-', '客户编号', {
		xtype : 'textfield',
		name : 'custNo',
		width : 100,
		scope : this
	}, '名称', {
		xtype : 'textfield',
		name : 'custName',
		width : 130,
		scope : this
	}, '客户经理', {
		xtype : 'combo',
		name : 'manName',
		fieldLabel : '客户经理',
		width : 100,
		store : new Ext.data.JsonStore({
			url : 'sale.do?actionType=doFindAllCstManager',
			root : 'data',
			totalProperty : 'rowCount',
			fields : ['manName']
		}),
		displayField : 'manName',
		pageSize : 10,
		forceSelection : true,
		triggerAction : 'all'
	}, '客户等级', {
		xtype : 'combo',
		name : 'custLevel',
		fieldLabel : '客户等级',
		store : new Ext.data.SimpleStore({
			fields : ['level'],
			data : [['全部'], ['战略合作伙伴'], ['合作伙伴'], ['大客户'], ['重点开发客户'], ['普通客户']]
		}),
		displayField : 'level',
		mode : 'local',
		forceSelection : true,
		editable : false,
		triggerAction : 'all',
		width : 90,
		scope : this
	}, '地区', {
		xtype : 'combo',
		name : 'custRegion',
		fieldLabel : '地区',
		store : new Ext.data.SimpleStore({
			fields : ['region'],
			data : [['北京'], ['华北'], ['中南'], ['东北'], ['西部']]
		}),
		displayField : 'region',
		mode : 'local',
		forceSelection : true,
		editable : false,
		triggerAction : 'all',
		width : 90,
		scope : this
	}, '&nbsp;&nbsp;&nbsp;&nbsp;', {
		text : '查询',
		iconCls : 'search',
		pressed : true,
		handler : function() {
			var panel = new custInfoPanel();
			panel.search();
		},
		scope : this
	}],
	bbar : new Ext.PagingToolbar({
		pageSize : 16,
		store : custInfoStore,
		grid : custInfoGrid,
		displayInfo : true,
		displayMsg : '显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});

CRM.custManage.custInfo = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,
	gridViewConfig : {
		animate : true
	},
	refresh : function() {
		store.removeAll();
		store.reload();
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
		this.showWin();
		this.reset();
	},

	reset : function() {
		if (this.win)
			this.fp.form.reset();
	},
	closeWin : function() {
		if (this.win)
			this.win.close();
		this.win = null;
	},
	// 按条件查询客户信息
	search : function() {
		custInfoStore.baseParams.custNo = Ext.get('custNo').getValue();
		custInfoStore.baseParams.custName = Ext.get('custName').getValue();
		custInfoStore.baseParams.custManName = Ext.get('manName').getValue();
		custInfoStore.baseParams.custLevel = Ext.get('custLevel').getValue();
		custInfoStore.baseParams.custRegion = Ext.get('custRegion').getValue();
		custInfoStore.load({
			params : {
				start : 0,
				limit : 16
			}
		});
	},
	// 编辑客户信息
	edit : function() {
		var record = custInfoGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请选择要编辑的行!");
			return;
		}
		this.showWin();
		this.fp.form.loadRecord(record);
	},
	// 保存新增或修改的客户信息
	save : function() {
		var id = this.fp.form.findField("custNo").getValue();
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : this.baseUrl + '?actionType=doSaveorUpdate',
				method : 'POST',
				success : function(form, action) {
					Ext.Msg.alert("系统消息", action.result.msg, function() {
						this.closeWin();
						custInfoStore.reload();
					}, this);
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg);
				},
				scope : this
			});
		}
	},
	// 删除客户信息
	removeData : function() {
		var record = custInfoGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请先选择要删除的行!");
			return;
		}
		Ext.MessageBox.confirm("确认删除", "确认删除所选数据？", function(button) {
			if (button == "yes") {
				Ext.Ajax.request({
					url : this.baseUrl + '?actionType=doDel',
					params : {
						custNo : record.get("custNo")
					},
					method : 'POST',
					success : function(response) {
						Ext.Msg.alert("提示信息", response.responseText,
								function() {
									custInfoStore.reload();
								}, this);
					},
					scope : this
				});
			}
		}, this);
	},
	// 打开面板
	showPanel : function(id, title, panel) {
		var record = custInfoGrid.getSelectionModel().getSelected();
		custNo = record.get('custNo');
		custName = record.get('custName');
		var p = new panel();
		p.id = id;
		p.title = title;
		var n = main.add(p);
		main.setActiveTab(n);
	},
	// 右键菜单
	showMenu : function(grid, index, e) {
		this.menu = new Ext.menu.Menu({
			items : [{
				id : 'add',
				text : '新建',
				iconCls : 'add',
				handler : function() {
					this.create();
				},
				scope : this
			}, {
				id : 'modify',
				text : '编辑',
				iconCls : 'modify',
				handler : function() {
					this.edit();
				},
				scope : this
			}, {
				id : 'del',
				text : '删除',
				iconCls : 'del',
				handler : this.removeData,
				scope : this
			}, {
				id : 'linkman',
				text : '联系人',
				icon : 'images/bt_linkman.gif',
				handler : function() {
					this.showPanel('cstLinkman', '联系人', custLinkmanPanel);
				},
				scope : this
			}, {
				id : 'orders',
				text : '历史订单',
				icon : 'images/bt_orders.gif',
				handler : function() {
					this.showPanel('orders', '历史订单', custOrdersPanel);
				},
				scope : this
			}, {
				id : 'activity',
				text : '客户交往记录',
				icon : 'images/bt_acti.gif',
				handler : function() {
					this.showPanel('cstActivity', '客户交往记录', custActivityPanel);
				},
				scope : this
			}]
		});
		e.preventDefault();
		this.menu.showAt(e.getXY());
	},
	initComponent : function() {
		CRM.custManage.custInfo.superclass.initComponent.call(this);
		custInfoStore.load({
			params : {
				start : 0,
				limit :16
			}
		});
		this.add(custInfoGrid);
		custInfoGrid.on("celldblclick", function() {
			this.edit();
		}, this);
		custInfoGrid.addListener("rowContextmenu", this.showMenu, this);
	}
});
custInfoPanel = Ext.extend(CRM.custManage.custInfo, {
	id : 'custInfo',
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
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'custNo',
							fieldLabel : '客户编号'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'custName',
							fieldLabel : '名称',
							editable : false,
							allowBlank : false,
							blankText : '名称为必填项'
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
							xtype : 'combo',
							name : 'custRegion',
							fieldLabel : '地区',
							width : 125,
							store : new Ext.data.SimpleStore({
								fields : ['custRegion'],
								data : [['北京'], ['华北'], ['中南'], ['东北'], ['西部']]
							}),
							displayField : 'custRegion',
							mode : 'local',
							forceSelection : true,
							editable : false,
							triggerAction : 'all',
							allowBlank : false,
							BlankText : '地区为必填项',
							emptyText : '请选择地区'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							xtype : 'combo',
							hiddenName : 'custManagerName',
							fieldLabel : '客户经理',
							width : 125,
							store : new Ext.data.JsonStore({
								url : 'customer.do?actionType=doFindAllManager',
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
							blankText : '客户经理为必填项'
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
							xtype : 'combo',
							name : 'custLevel',
							width : 125,
							fieldLabel : '客户等级',
							store : new Ext.data.SimpleStore({
								fields : ['cstLevel'],
								data : [['普通客户'], ['重点开发客户'], ['大客户'],
										['合作伙伴'], ['战略合作伙伴']]
							}),
							displayField : 'cstLevel',
							mode : 'local',
							forceSelection : true,
							editable : false,
							triggerAction : 'all',
							allowBlank : false,
							BlankText : '客户等级为必填项',
							emptyText : '请选择客户等级'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							xtype : 'combo',
							name : 'custSatisfy',
							width : 125,
							fieldLabel : '客户满意度',
							store : new Ext.data.SimpleStore({
								fields : ['cstSatisfy'],
								data : [['☆'], ['☆☆'], ['☆☆☆'], ['☆☆☆☆'],
										['☆☆☆☆☆']]
							}),
							displayField : 'cstSatisfy',
							mode : 'local',
							forceSelection : true,
							editable : false,
							triggerAction : 'all',
							allowBlank : false,
							blankText : '客户满意度为必填项',
							emptyText : '请选择客户满意度'
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
							xtype : 'combo',
							name : 'custCredit',
							width : 125,
							fieldLabel : '客户信用度',
							store : new Ext.data.SimpleStore({
								fields : ['cstCredit'],
								data : [['☆'], ['☆☆'], ['☆☆☆'], ['☆☆☆☆'],
										['☆☆☆☆☆']]
							}),
							displayField : 'cstCredit',
							mode : 'local',
							forceSelection : true,
							editable : false,
							triggerAction : 'all',
							allowBlank : false,
							blankText : '客户信用度为必填项',
							emptyText : '请选择客户信息度'
						}]
					}]
				}]
			}, {
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
							name : 'custAddr',
							fieldLabel : '地址',
							allowBlank : false,
							blankText : '地址为必填项'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							xtype : 'numberfield',
							name : 'custZip',
							fieldLabel : '邮政编码',
							editable : false,
							allowBlank : false,
							blankText : '邮政编码为必填项'
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
							name : 'custTel',
							fieldLabel : '电话',
							allowBlank : false,
							BlankText : '电话为必填项'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'custFax',
							fieldLabel : '传真',
							allowBlank : false,
							blankText : '传真为必填项'
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
							name : 'custWebsite',
							fieldLabel : '网址',
							allowBlank : false,
							BlankText : '网址为必填项'
						}]
					}]
				}]
			}, {
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
							name : 'custLicenceNo',
							fieldLabel : '营业执照注册号'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'custChieftain',
							fieldLabel : '法人',
							allowBlank : false,
							blankText : '法人为必填项'
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
							name : 'custBankroll',
							fieldLabel : '注册资金（万元）'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'custTurnover',
							fieldLabel : '年营业额'
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
							name : 'custBank',
							fieldLabel : '开户银行',
							allowBlank : false,
							BlankText : '开户银行为必填项'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							xtype : 'numberfield',
							name : 'custBankAccount',
							fieldLabel : '银行账号',
							allowBlank : false,
							BlankText : '银行账号为必填项'
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
							name : 'custLocalTaxNo',
							fieldLabel : '地税登记号'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							name : 'custNationalTaxNo',
							fieldLabel : '国税登记号'
						}]
					}]
				}]
			}]
		});
		return formPanel;
	},
	createWin : function() {
		return this.initWin(555, 490, '客户信息管理');
	},
	initComponent : function() {
		custInfoPanel.superclass.initComponent.call(this);
	}
});
