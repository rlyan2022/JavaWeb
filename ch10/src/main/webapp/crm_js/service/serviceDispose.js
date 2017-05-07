Ext.namespace("CRM.serviceManage");
var svrId;
function showDisposeWin() {
	var panel = new serviceDisposePanel();
	panel.create();
}

var cstServiceDisposeStore = new Ext.data.JsonStore({
	id : "id",
	url : 'cstService.do?actionType=doList',
	root : "data",
	totalProperty : "rowCount",
	baseParams : {
		custName : null,
		svrTitle : null,
		svrType : null,
		svrCreateDate : null,
		svrStatus : '已分配'
	},
	remoteSort : true,
	fields : ["svrId", "svrType", "svrTitle", "svrCustNo", "svrCustName",
			"svrStatus", "svrRequest", "svrCreateBy", "svrCreateDate",
			"svrDueTo", "svrDueDate", "svrDeal", "svrDealBy", "svrDealDate",
			"svrResult", "svrSatisfy"]
});

var cstServiceDisposeColm = new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer(), {
			header : '编号',
			sortable : true,
			dataIndex : 'svrId',
			width : 100
		}, {
			header : '客户',
			sortable : true,
			dataIndex : 'svrCustName',
			width : 150
		}, {
			header : '概要',
			sortable : true,
			dataIndex : 'svrTitle',
			width : 180
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
			header : '操作',
			dataIndex : 'option',
			renderer : function() {
				var option = '<img src="images/bt_deal.gif" title="处理" onclick="showDisposeWin()"/>';
				return option;
			}
		}]);
var cstServiceDisposeGrid = new Ext.grid.GridPanel({
	store : cstServiceDisposeStore,
	cm : cstServiceDisposeColm,
	height : 300,
	stripeRows : true,
	pageSize : 10,
	loadMask : true,
	tbar : [ new Ext.Toolbar.Fill(), '客户', {
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
			data : [['   '], ['咨询'], ['建议'], ['投诉']]
		}),
		mode : 'local',
		displayField : 'serviceType',
		triggerAction : 'all',
		forceSelection : true,
		editable : true,// 是否可写
		name : 'svrType',
		// emptyText : '全部',// 默认值
		width : 110
	}, '&nbsp;&nbsp;', '创建时间', {
		xtype : 'datefield',
		name : 'svrCreatDate',
		format : 'Y年m月d日',
		width : 110
	}, '&nbsp;&nbsp;', {
		text : '查询',
		iconCls : 'search',
		pressed : true,
		handler : function() {
			var panels = new serviceDisposePanel();
			panels.search();
		},
		width : 150,
		scope : this
	}, '   '],
	bbar : new Ext.PagingToolbar({
		pageSize : 10,
		store : cstServiceDisposeStore,
		grid : cstServiceDisposeGrid,
		displayInfo : true,
		displayMsg : '当前显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});
CRM.serviceManage.serviceDispose = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,
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
		var record = cstServiceDisposeGrid.getSelectionModel().getSelected();
		svrId = record.get('svrId');
		this.showWin();
		this.reset();
	},
	refresh : function() {
		cstServiceDisposeStore.removeAll();
		cstServiceDisposeStore.reload();
	},
	save : function() {
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : this.baseUrl + '?actionType=doDispose',
				method : 'POST',
				params : {
					svrId : svrId
				},
				success : function(form, action) {
					Ext.Msg.alert("系统消息", action.result.msg, function() {
						this.closeWin();
						cstServiceDisposeStore.reload();
					}, this);
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg);
				},
				scope : this
			});
		}
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
	search : function() {
		cstServiceDisposeStore.baseParams.custName = Ext.get('custName')
				.getValue();
		cstServiceDisposeStore.baseParams.svrTitle = Ext.get('svrTitle')
				.getValue();
		cstServiceDisposeStore.baseParams.svrType = Ext.get('svrType')
				.getValue();
		cstServiceDisposeStore.baseParams.svrCreatDate = Ext
				.get('svrCreatDate').getValue();
		cstServiceDisposeStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
	},
	// 右键菜单
	showMenu : function(grid, index, e) {
		this.menu = new Ext.menu.Menu({
			items : [{
				id : 'add',
				text : '指派',
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
			}]
		});
		e.preventDefault();
		this.menu.showAt(e.getXY());
	},
	initComponent : function() {
		CRM.serviceManage.serviceDispose.superclass.initComponent.call(this);
		cstServiceDisposeGrid.on("celld" + "blclick", function() {
			this.create();
		}, this);
		cstServiceDisposeGrid
				.addListener("rowContextmenu", this.showMenu, this);
		cstServiceDisposeStore.load({
			params : {
				start : 0,
				limit : 10,
				svrStatus : '已分配'
			}
		});
		this.add(cstServiceDisposeGrid);
	}
});

serviceDisposePanel = Ext.extend(CRM.serviceManage.serviceDispose, {
	id : 'serviceAllot',
	baseUrl : 'cstService.do',
	createForm : function() {
		return serviceCreatePanel = new Ext.form.FormPanel({
			labelWidth : 70,
			frame : true,
			labelAlign : 'right',
			defaultType : 'textfield',
			items : [{
				xtype : 'textarea',
				width : 300,
				name : 'svrDeal',
				fieldLabel : '服务处理'
			}, {
				name : 'svrDealBy',
				width : 300,
				fieldLabel : '处理人'
			}, {
				xtype : 'datefield',
				name : 'svrDealDate',
				width : 300,
				fieldLabel : '处理时间',
				format : 'Y年m月d日'
			}]
		});
	},
	createWin : function() {
		return this.initWin(450, 200, '服务处理');
	},
	initComponent : function() {

		serviceDisposePanel.superclass.initComponent.call(this);
	}
});
