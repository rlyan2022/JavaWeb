Ext.namespace("CRM.reptManage");
CRM.reptManage.custLose = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,
	gridViewConfig : {
		animate : true
	},
	refresh : function() {
		this.store.removeAll();
		this.store.reload();
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
						this.store.reload();
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
		this.store.baseParams.custName = Ext.get('custName').getValue();
		this.store.baseParams.lstCustManagerName = Ext
				.get('lstCustManagerName').getValue();
		this.store.load({
			params : {
				start : 0,
				limit : 10
			}
		});
	},

	// 右键菜单
	showMenu : function(grid, index, e) {
		e.preventDefault();
		this.menu.showAt(e.getXY());
	},
	initComponent : function() {
		this.store = new Ext.data.JsonStore({
			id : "id",
			url : this.baseUrl + '?actionType=doListLose',
			root : "data",
			totalProperty : "rowCount",
			baseParams : {
				custName : null,
				lstCustManagerName : null
			},
			remoteSort : true,
			fields : this.storeMapping
		});

		CRM.reptManage.custLose.superclass.initComponent.call(this);

		this.grid = new Ext.grid.GridPanel({
			store : this.store,
			cm : this.cm,
			height : 300,
			stripeRows : true,
			pageSize : 10,
			tbar : [ new Ext.Toolbar.Fill(), '客户名称:', {
				xtype : 'textfield',
				name : 'custName',
				width : 150
			}, '&nbsp;&nbsp;', '客户经理：', {
				xtype : "textfield",
				name : 'lstCustManagerName',
				width : 150
			}, '&nbsp;&nbsp;', {
				text : '查询',
				iconCls : 'search',
				pressed : true,
				handler : this.search,
				scope : this
			}, '   '],
			bbar : new Ext.PagingToolbar({
				pageSize : 10,
				store : this.store,
				grid : this.grid,
				displayInfo : true,
				displayMsg : '当前显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
				emptyMsg : "没有记录"
			})
		});
		this.grid.addListener("rowContextmenu", this.showMenu, this);
		this.store.load({
			params : {
				start : 0,
				limit : 10
			}
		});
		this.add(this.grid);
	}
});
custLosePanel = Ext.extend(CRM.reptManage.custLose, {
	id : 'custLose',
	baseUrl : 'report.do',
	createWin : function() {
		return this.initWin(555, 420, '统计报表');
	},
	storeMapping : ["lstId", "custName", "lstCustManagerName",
			"lstLastOrderDate", "lstLostDate", "lstDelay", "lstReason",
			"lstStatus"],
	initComponent : function() {
		this.cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
			header : '编号',
			sortable : true,
			dataIndex : 'lstId',
			width : 166
		}, {
			header : '年份',
			sortable : true,
			dataIndex : 'lstLostDate',
			format : 'Y-m-d',
			width : 166,
			renderer : function(value) {
				var date = new Date();
				date.setYear((value.year) + 1900);
				date.setMonth(value.month);
				date.setDate(value.date);
				return date.format("Y年m月d日");
			}
		}, {
			header : '客户名称',
			sortable : true,
			dataIndex : 'custName',
			width : 167
		}, {
			header : '客户经理',
			sortable : true,
			dataIndex : 'lstCustManagerName',
			width : 167
		}, {
			header : '流失原因',
			sortable : true,
			dataIndex : 'lstReason',
			width : 167
		}]);
		custLosePanel.superclass.initComponent.call(this);
	}
});
