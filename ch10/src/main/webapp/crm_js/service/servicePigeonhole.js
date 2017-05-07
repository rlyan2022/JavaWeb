Ext.namespace("CRM.serviceManage");

function showPigenholeWin() {
	var panel = new servicePigeonholePanel();
	panel.edit();
}

var cstServicePigeonholeStore = new Ext.data.JsonStore({
	id : "id",
	url : 'cstService.do?actionType=doList',
	root : "data",
	totalProperty : "rowCount",
	baseParams : {
		custName : null,
		svrTitle : null,
		svrType : null,
		svrCreateDate : null,
		svrStatus : '已归档'
	},
	remoteSort : true,
	fields : ["svrId", "svrType", "svrTitle", "svrCustNo", "svrCustName",
			"svrStatus", "svrRequest", "svrCreateBy", "svrCreateDate",
			"svrDueTo", "svrDueDate", "svrDeal", "svrDealBy", "svrDealDate",
			"svrResult", "svrSatisfy"]
});
var cstServicePigeonholeColm = new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer(), {
			header : '编号',
			sortable : true,
			dataIndex : 'svrId',
			width : 80
		}, {
			header : '客户',
			sortable : true,
			dataIndex : 'svrCustName',
			width : 110
		}, {
			header : '概要',
			sortable : true,
			dataIndex : 'svrTitle',
			width : 140
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
			format : 'Y年m月d日',
			dataIndex : 'svrCreateDate',
			width : 100
		}, {
			header : '状态',
			sortable : true,
			dataIndex : 'svrStatus',
			width : 100
		}, {
			header : '操作',
			dataIndex : 'option',
			renderer : function() {
				return '<img src="images/bt_detail.gif" title="查看" onclick ="showPigenholeWin()"/>';
			}
		}]);
var svrCreateDateStore = new Ext.data.JsonStore({
	url : 'cstService.do?actionType=doList',
	method : 'POST',
	root : 'data',
	baseParams : {
		svrStatus : '已归档'
	},
	totalProperty : "rowCount",
	fields : ['svrCreateDate']
});
// svrCreateDateStore.load({
// params : {
// start : 0,
// limit : 10
// }
// });
var cstServicePigeonholeGrid = new Ext.grid.GridPanel({
	store : cstServicePigeonholeStore,
	cm : cstServicePigeonholeColm,
	height : 300,
	// trackMouseOver : false,
	stripeRows : true,
	pageSize : 10,
	loadMask : true,
	tbar : [ new Ext.Toolbar.Fill(), '客户', {
		xtype : 'textfield',
		name : 'custName',
		width : 130
	}, '&nbsp;&nbsp;', '概要', {
		xtype : "textfield",
		name : 'svrTitle',
		width : 180
	}, '&nbsp;&nbsp;', '服务类型 ', { // 下拉框
		xtype : 'combo',

		store : new Ext.data.SimpleStore({
			fields : ['serviceType'],
			data : [['咨询'], ['建议'], ['投诉'], ['   ']]
		}),
		mode : 'local',
		displayField : 'serviceType',
		triggerAction : 'all',
		forceSelection : true,
		editable : true,// 是否可写
		name : 'svrType',
		// emptyText : '全部',// 默认值
		width : 110
	},'&nbsp;&nbsp;', '创建时间', {
		xtype : 'datefield',
		name : 'svrCreateDate',
		format : 'Y年m月d日',
		width : 110
	}, '&nbsp;&nbsp;', {
		text : '查询',
		iconCls : 'search',
		pressed : true,
		handler : function() {
			var panel = new servicePigeonholePanel();
			panel.search();
		},
		width : 150,
		scope : this
	}, '   '],
	bbar : new Ext.PagingToolbar({
		pageSize : 10,
		store : cstServicePigeonholeStore,
		grid : cstServicePigeonholeGrid,
		displayInfo : true,
		displayMsg : '当前显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});
CRM.serviceManage.servicePigeonhole = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,
	gridViewConfig : {
		animate : true
	},
	refresh : function() {
		cstServicePigeonholeStore.removeAll();
		cstServicePigeonholeStore.reload();
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
			items : [this.fp]
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
						cstServicePigeonholeStore.reload();
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
	search : function() {
		cstServicePigeonholeStore.baseParams.custName = Ext.get('custName')
				.getValue();
		cstServicePigeonholeStore.baseParams.svrTitle = Ext.get('svrTitle')
				.getValue();
		cstServicePigeonholeStore.baseParams.svrType = Ext.get('svrType')
				.getValue();
		cstServicePigeonholeStore.baseParams.svrCreateDate = Ext
				.get('svrCreateDate').getValue();
		cstServicePigeonholeStore.load({
			params : {
				start : 0,
				limit : 10,
				svrStatus : '已归档'
			}
		});
	},
	edit : function(show) {
		var record = cstServicePigeonholeGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请选择要编辑的行!");
			return;
		}
		var id = record.get("id");
		this.showWin(show);
		this.fp.form.loadRecord(record);
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
		CRM.serviceManage.servicePigeonhole.superclass.initComponent.call(this);
		// 双击显示界面
		cstServicePigeonholeGrid.on("celldblclick", function() {
			this.edit(Ext.getBody());
		}, this);
		cstServicePigeonholeGrid.addListener("rowContextmenu", this.showMenu,
				this);
		cstServicePigeonholeStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
		this.add(cstServicePigeonholeGrid);
	}
});
servicePigeonholePanel = Ext.extend(CRM.serviceManage.servicePigeonhole, {
	id : 'servicePigeonhole',
	baseUrl : 'cstService.do',
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
							disabled : true,// 是否可编辑
							name : 'svrId',
							fieldLabel : '编号'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							disabled : true,// 是否可编辑
							name : 'svrType',
							fieldLabel : '服务类型',
							allowBlank : false
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
							disabled : true,// 是否可编辑
							name : 'svrCustName',
							fieldLabel : '客户'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							disabled : true,// 是否可编辑
							name : 'svrStatus',
							fieldLabel : '状态',
							allowBlank : false,
							// blankText : '成功机率为必填项',
							minValue : 1
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
							disabled : true,// 是否可编辑
							name : 'svrCreateBy',
							fieldLabel : '创建人'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							disabled : true,// 是否可编辑
							xtype : 'numberfield',
							format : 'Y年m月d日',
							name : 'svrCreateDate',
							fieldLabel : '创建时间'
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
							disabled : true,// 是否可编辑
							name : 'svrDueTo',
							fieldLabel : '分配给'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{

							disabled : true,// 是否可编辑
							name : 'svrDueDate',
							fieldLabel : '分配时间',
							format : 'Y年m月d日'
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
							disabled : true,// 是否可编辑
							name : 'svrDealBy',
							fieldLabel : '处理人'
						// allowBlank : false
						// blankText : '创建人为必填项'
						}]
					}, {
						columnWidth : .5,
						layout : 'form',
						defaultType : 'textfield',
						items : [{
							disabled : true,// 是否可编辑
							name : 'svrDealDate',
							fieldLabel : '处理时间'
						// allowBlank : false
						// blankText : '创建时间为必填项'
						}]
					}]
				},{
					xtype : 'textarea',
					disabled : true ,//是否可编辑
					width : 375,
					name : 'svrRequest',
					fieldLabel : '服务请求'
				}, {
					xtype : 'textarea',
					disabled : true,// 是否可编辑
					width : 375,
					name : 'svrDeal',
					fieldLabel : '服务处理'
				}, {
					xtype : 'textarea',
					disabled : true,// 是否可编辑
					width : 375,
					name : 'svrResult',
					fieldLabel : '处理结果'
				}, {
					xtype : 'combo',
					disabled : true,// 是否可编辑
					name : 'svrSatisfy',
					fieldLabel : '满意度',
					width : 100,
					displayField : 'satisfy',
					mode : 'local',
					forceSelection : true,
					editable : false,
					triggerAction : 'all',
					allowBlank : false
				}]
			}]
		});
		return formPanel;
	},
	createWin : function() {
		return this.initWin(555, 420, '服务归档信息');
	},
	initComponent : function() {

		servicePigeonholePanel.superclass.initComponent.call(this);
	}
});
