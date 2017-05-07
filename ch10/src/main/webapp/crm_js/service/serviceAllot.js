Ext.namespace("CRM.serviceManage");
var svrId2;
function showDelCstSvrWin() {
	var panel = new serviceAllotPanel();
	panel.removeData();

}

// 分配给...
function showAllotOpen() {
	var panelo = new serviceAllotPanel();
	panelo.create();
}

cstServiceAllotStore = new Ext.data.JsonStore({
	url : 'cstService.do?actionType=doList',
	root : "data",
	totalProperty : "rowCount",
	baseParams : {
		custName : null,
		svrTitle : null,
		svrType : null,
		svrCreateDate : null,
		fieldLabel : '分配人',
		svrStatus : '新创建'
	},
	remoteSort : true,
	fields : ["svrId", "svrType", "svrTitle", "svrCustNo", "svrCustName",
			"svrStatus", "svrRequest", "svrCreateBy", "svrCreateDate",
			"svrDueTo", "svrDueDate", "svrDeal", "svrDealBy", "svrDealDate",
			"svrResult", "svrSatisfy"]
});
cstServiceAllotColm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
	header : '编号',
	dataIndex : 'svrId',
	width : 80
}, {
	header : '客户',
	sortable : true,
	dataIndex : 'svrCustName',
	width : 160
}, {
	header : '概要',
	sortable : true,
	dataIndex : 'svrTitle',
	width : 190
}, {
	header : '服务类型',
	sortable : true,
	dataIndex : 'svrType',
	width : 100
}, {
	header : '创建人',
	sortable : true,
	dataIndex : 'svrCreateBy',
	width : 100
}, {
	header : '创建时间',
	sortable : true,
	dataIndex : 'svrCreateDate',
	width : 100
}, {
	header : '分配给',
	sortable : true,
	dataIndex : 'svrStatus',
	width : 50,
	renderer : function(value) {
		var operation = '';
		if (value == '新创建') {
			operation += '<img src="images/application_go.gif" onclick="showAllotOpen()"/>';
		}
		return operation;
	}
}, {
	header : '操作',
	dataIndex : 'svrStatus',
	width : 50,
	renderer : function(value) {
		var operation = '';
		operation += '<img src="images/bt_del.gif" title="删除" onclick="showDelCstSvrWin()"/>';

		return operation;
	}
}]);
var cstServiceAllotGrid = new Ext.grid.GridPanel({
	store : cstServiceAllotStore,
	cm : cstServiceAllotColm,
	height : 300,
	stripeRows : true,
	pageSize : 10,
	loadMask : true,
	tbar : [new Ext.Toolbar.Fill(), '客户', {
		xtype : 'textfield',
		name : 'custName',
		width : 150
	}, '&nbsp;&nbsp;', '概要', {
		xtype : "textfield",
		name : 'svrTitle',
		width : 180
	}, '&nbsp;&nbsp;', '服务类型 ', { // 下拉框
		xtype : 'combo',
		store : new Ext.data.SimpleStore({
			fields : ['serviceType'],
			data : [['咨询'], ['建议'], ['投诉']]
		}),
		mode : 'local',
		displayField : 'serviceType',
		triggerAction : 'all',
		forceSelection : true,
		editable : true,// 是否可写
		name : 'svrType',
		width : 110
	}, '&nbsp;&nbsp;', '创建时间', {
		xtype : 'datefield',
		name : 'svrCreateDate',
		format : 'Y年m月d日',
		width : 110
	}, '&nbsp;&nbsp;', {
		text : '查询',
		iconCls : 'search',
		pressed : true,
		handler : function() {
			var panel = new serviceAllotPanel();
			panel.search();
		},
		width : 150,
		scope : this
	}, '   '],
	bbar : new Ext.PagingToolbar({
		pageSize : 10,
		store : cstServiceAllotStore,
		grid : cstServiceAllotGrid,
		displayInfo : true,
		displayMsg : '当前显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});

CRM.serviceManage.serviceAllot = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,
	refresh : function() {
		cstServiceAllotStore.removeAll();
		cstServiceAllotStore.reload();
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
	reset : function() {
		if (this.win)
			this.fp.form.reset();
	},
	closeWin : function(show) {
		if (this.win)
			this.win.close(show);
		this.win = null;
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
		var record = cstServiceAllotGrid.getSelectionModel().getSelected();
		svrId2 = record.get('svrId');
		this.showWin();
		this.reset();
	},
	save : function() {
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : this.baseUrl + '?actionType=doAllot',
				method : 'POST',
				params : {
					svrId : svrId2
				},
				success : function(form, action) {
					Ext.Msg.alert("系统消息", action.result.msg, function() {
						this.closeWin();
						cstServiceAllotStore.load();
					}, this);
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统消息',action.result.msg);
				},
				scope : this
			});
		}
	},
	search : function() {
		cstServiceAllotStore.baseParams.svrTitle = Ext.get('svrTitle')
				.getValue();
		cstServiceAllotStore.baseParams.custName = Ext.get('custName')
				.getValue();
		cstServiceAllotStore.baseParams.svrType = Ext.get('svrType').getValue();
		cstServiceAllotStore.baseParams.svrCreateDate = Ext
				.get('svrCreateDate').getValue();
		cstServiceAllotStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
	},
	removeData : function() {
		var record = cstServiceAllotGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请先选择要删除的行!");
			return;
		}
		Ext.MessageBox.confirm("确认删除", "确认删除所选数据？", function(button) {
			if (button == "yes") {
				Ext.Ajax.request({
					url : this.baseUrl + '?actionType=doDel',
					params : {
						svrId : record.get("svrId")
					},
					method : 'POST',
					success : function(response) {
						Ext.Msg.alert("提示信息", response.responseText,
								function() {
									cstServiceAllotStore.reload();
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
				text : '指派',
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

		CRM.serviceManage.serviceAllot.superclass.initComponent.call(this);

		// 双击显示界面
		cstServiceAllotGrid.on("celldblclick", function() {
			this.edit(Ext.getBody());
		}, this);
		cstServiceAllotGrid.addListener("rowContextmenu", this.showMenu, this);
		cstServiceAllotStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
		this.add(cstServiceAllotGrid);
	}
});

serviceAllotPanel = Ext.extend(CRM.serviceManage.serviceAllot, {
	id : 'serviceAllot',
	baseUrl : 'cstService.do',
	createForm : function() {
		return formPanel = new Ext.form.FormPanel({
			labelWidth : 70,
			frame : true,
			autoHeight : true,
			resizable : false,
			labelAlign : 'right',
			defaultType : 'textfield',
			items : [{
				xtype : 'hidden',
				name : 'svrType'
			}, {
				xtype : 'hidden',
				name : 'svrTitle'
			}, {
				xtype : 'hidden',
				name : 'svrCustNo'
			}, {
				xtype : 'hidden',
				name : 'svrStatus'
			}, {
				xtype : 'hidden',
				name : 'svrRequest'
			}, {
				xtype : 'hidden',
				name : 'svrCreateBy'
			}, {
				xtype : 'hidden',
				name : 'svrCreateDate'
			}, {
				xtype : 'hidden',
				name : 'svrDeal'
			}, {
				xtype : 'hidden',
				name : 'svrDealBy'
			}, {
				xtype : 'hidden',
				name : 'svrDealDate'
			}, {
				xtype : 'hidden',
				name : 'svrResult'
			}, {
				xtype : 'hidden',
				name : 'svrSatisfy'
			}, {
				xtype : 'combo',
				name : 'svrDueTo',
				width : 125,
				fieldLabel : '分配给',
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
				allowBlank : false
					// blankText : '请输入被指派人的姓名'
					// fieldLabel : '分配给',
					// name : 'svrDueTo'
					}]
		});
		return formPanel;
	},
	createWin : function() {
		return this.initWin(300, 105, '服务分配');
	},
	initComponent : function() {

		serviceAllotPanel.superclass.initComponent.call(this);
	}
});
