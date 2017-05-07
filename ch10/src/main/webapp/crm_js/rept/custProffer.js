Ext.namespace("CRM.custProffer");

var DateStore = new Ext.data.JsonStore({
	url : 'report.do?actionType=doFindCustProffer',
	method : 'POST',
	root : 'data',
	totalProperty : 'rowCount',
	fields : ['odrDate']
});
CRM.custProffer.custProffer = Ext.extend(Ext.Panel, {
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

		this.store.baseParams.odrCustomer = Ext.get('odrCustomer').getValue();
		this.store.baseParams.odrDate = Ext.get('odrDate').getValue();
		this.store.load({
			params : {
				start : 0,
				limit : 10
			}
		});
	},
	edit : function(show) {
		var record = this.grid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请选择要编辑的行!");
			return;
		}
		var id = record.get("id");
		this.showWin(show);
		this.fp.form.loadRecord(record);
	},
	removeData : function() {
		var record = this.grid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请先选择要删除的行!");
			return;
		}

	},
	// 右键菜单
	showMenu : function(grid, index, e) {
		e.preventDefault();
		this.menu.showAt(e.getXY());
	},
	initComponent : function() {
		this.store = new Ext.data.JsonStore({
			id : "id",
			url : this.baseUrl + '?actionType=doFindCustProffer',
			root : "data",
			totalProperty : "rowCount",
			baseParams : {
				odrCustomer : null,
				odrDate : null
			},
			remoteSort : true,
			fields : this.storeMapping
		});

		CRM.custProffer.custProffer.superclass.initComponent.call(this);

		this.grid = new Ext.grid.GridPanel({
			store : this.store,
			cm : this.cm,
			height : 300,
			stripeRows : true,
			pageSize : 10,
			tbar : [ new Ext.Toolbar.Fill(), '客户名称:', {
				xtype : 'textfield',
				name : 'odrCustomer',
				width : 200
			}, '&nbsp;&nbsp;', '年份：', {
				xtype : "combo",
				name : 'odrDate',

				store : DateStore,
				displayField : 'odrDate',
				forceSelection : true,
				triggerAction : 'all',
				editable : true,
				width : 200

			},

			'&nbsp;&nbsp;', {
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
		this.grid.on("celldblclick", function() {
			this.edit(Ext.getBody());
		}, this);
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
custProfferPanel = Ext.extend(CRM.custProffer.custProffer, {
	id : 'custProffer',
	baseUrl : 'report.do',

	createWin : function() {
		return this.initWin(555, 420, '统计报表');
	},

	storeMapping : ["odrCustomer", "adrTotal"],
	initComponent : function() {
		this.cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
			header : '客户名称',
			sortable : true,
			dataIndex : 'odrCustomer',
			width : 415
		}, {
			header : '定单金额(元)',
			sortable : true,
			dataIndex : 'adrTotal',
			width : 415
		}]);
		custProfferPanel.superclass.initComponent.call(this);
	}
});
