Ext.namespace("CRM.serviceManage");
var svrId1;
function showFeedbackWin() {
	var panel = new serviceFeedbackPanel();
	panel.create();
}
var feedbackStore = new Ext.data.JsonStore({
	url : 'cstService.do?actionType=doList',
	root : "data",
	totalProperty : "rowCount",
	baseParams : { // 参数...
		custName : null,
		svrTitle : null,
		svrType : null,
		svrCreateDate : null,
		svrStatus : '已处理'
	},
	remoteSort : true,
	fields : ["svrId", "svrType", "svrTitle", "svrCustNo", "svrCustName",
			"svrStatus", "svrRequest", "svrCreateBy", "svrCreateDate",
			"svrDueTo", "svrDueDate", "svrDeal", "svrDealBy", "svrDealDate",
			"svrResult", "svrSatisfy"]
});
var feedbackColm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
	header : '编号',
	sortable : true,
	dataIndex : 'svrId',
	width : 80
}, {
	header : '客户',
	sortable : true,
	dataIndex : 'svrCustName',
	width : 125
}, {
	header : '概要',
	sortable : true,
	dataIndex : 'svrTitle',
	width : 150
}, {
	header : '服务类型',
	sortable : true,
	dataIndex : 'svrType',
	width : 125
}, {
	header : '创建人',
	sortable : true,
	dataIndex : 'svrCreateBy',
	width : 125
}, {
	header : '创建时间',
	sortable : true,
	format : 'Y年m月d日',
	dataIndex : 'svrCreateDate',
	width : 125
}, {
	header : '操作',
	dataIndex : 'option',
	renderer : function() {
		return '<img src="images/bt_feedback.gif" title="服务处理" onclick="showFeedbackWin()"/>';
	}
}]);
var feedbackGrid = new Ext.grid.GridPanel({
	store : feedbackStore,
	cm : feedbackColm,
	height : 300,
	trackMouseOver : false,
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
		// emptyText : '全部',// 默认值
		width : 110
	}, '&nbsp;&nbsp;', '创建时间', {
		xtype : 'datefield',
		format : 'Y年m月d日',
		name : 'svrCreatDate',
		width : 110
	}, '&nbsp;&nbsp;', {
		text : '查询',
		iconCls : 'search',
		pressed : true,
		handler : function() {
			var panels = new serviceFeedbackPanel();
			panels.search();
		},
		width : 150,
		scope : this
	}, '   '],
	bbar : new Ext.PagingToolbar({
		pageSize : 10,
		store : feedbackStore,
		grid : feedbackGrid,
		displayInfo : true,
		displayMsg : '当前显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});

CRM.serviceManage.serviceFeedback = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,
	gridViewConfig : {
		animate : true
	},
	refresh : function() {
		feedbackStore.removeAll();
		feedbackStore.reload();
	},
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
		var record = feedbackGrid.getSelectionModel().getSelected();
		svrId1 = record.get('svrId');
		this.showWin();
		this.reset();
	},
	save : function() {
		var record = feedbackGrid.getSelectionModel().getSelected();
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : this.baseUrl + '?actionType=doFeedback',
				method : 'POST',
				params : {
					svrId : svrId1
				},
				success : function(form, action) {
					Ext.Msg.alert("系统消息", action.result.msg, function() {
						this.closeWin();
						feedbackStore.reload();
					}, this);
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg, this);
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
		feedbackStore.baseParams.custName = Ext.get('custName').getValue();
		feedbackStore.baseParams.svrTitle = Ext.get('svrTitle').getValue();
		feedbackStore.baseParams.svrType = Ext.get('svrType').getValue();
		feedbackStore.baseParams.svrCreatDate = Ext.get('svrCreatDate')
				.getValue();
		feedbackStore.load({
			params : {
				start : 0,
				limit : 10,
				svrStatus : '已处理'
			}
		});
	},
	edit : function(show) {
		var record = feedbackGrid.getSelectionModel().getSelected();
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
		CRM.serviceManage.serviceFeedback.superclass.initComponent.call(this);
		feedbackGrid.on("celldblclick", function() {
			this.create();
		}, this);
		feedbackGrid.addListener("rowContextmenu", this.showMenu, this);
		this.add(feedbackGrid);
		feedbackStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
	}
});
serviceFeedbackPanel = Ext.extend(CRM.serviceManage.serviceFeedback, {
	id : 'serviceFeedback',
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
				name : 'svrResult',
				fieldLabel : '处理结果'
			}, {
				xtype : 'combo',
				name : 'svrSatisfy',
				fieldLabel : '满意度',
				width : 300,
				store : new Ext.data.SimpleStore({
					fields : ['satisfy'],
					data : [['☆'], ['☆☆'], ['☆☆☆'], ['☆☆☆☆'], ['☆☆☆☆☆']]
				}),
				displayField : 'satisfy',
				mode : 'local',
				forceSelection : true,
				editable : false,
				triggerAction : 'all',
				allowBlank : false,
				blankText : '客户满意度为必填项',
				emptyText : '请选择客户满意度'
			}]
		});
	},
	createWin : function() {
		return this.initWin(450, '服务反馈');
	},
	initComponent : function() {
		serviceFeedbackPanel.superclass.initComponent.call(this);
	}
});
