
Ext.namespace("CRM.baseData");
CRM.baseData.selectStorage = Ext.extend(Ext.Panel, {
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
				text : "是",
				handler : this.save,
				tooltip : '点击该按钮将执行确认操作',
				scope : this
			}, {
				text : "否",
				handler : this.reset,
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
	
	search : function() {
		this.store.baseParams.prodName = Ext.get('prodName').getValue();
		this.store.baseParams.stkWarehouse = Ext.get('stkWarehouse').getValue();
		this.store.load({
			params : {
				start : 0,
				limit : 18
			}
		});
	},
	initComponent : function() {
		this.store = new Ext.data.JsonStore({
			id : "id",
			url : this.baseUrl + '?actionType=doList',
			root : "data",
			totalProperty : "rowCount",
			baseParams : {
				prodName : null,
				stkWarehouse : null
			},
			remoteSort : true,
			fields : this.storeMapping
		});

		CRM.baseData.selectStorage.superclass.initComponent.call(this);

		this.grid = new Ext.grid.GridPanel({
			store : this.store,
			cm : this.cm,
			height : 300,
			stripeRows : true,
			pageSize : 10,
			tbar : [ new Ext.Toolbar.Fill(), '产品名称：', {
				xtype : 'textfield',
				name : 'prodName',
				width : 150
			}, '&nbsp;&nbsp;', '所在仓库：', {
				xtype : "textfield",
				name : 'stkWarehouse',
				width : 150
			}, {
				text : '查询',
				iconCls : 'search',
				pressed : true,
				handler : this.search,
				scope : this
			}, '   '],
			bbar : new Ext.PagingToolbar({
				pageSize : 18,
				store : this.store,
				grid : this.grid,
				displayInfo : true,
				displayMsg : '当前显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
				emptyMsg : "没有记录"
			})
		});
		this.store.load({
			params : {
				start : 0,
				limit : 18
			}
		});
		this.add(this.grid);
	}
});
//主面板。。。。。
selectStoragePanel = Ext.extend(CRM.baseData.selectStorage, {
	id : 'selectStorage',
	baseUrl:'storage.do',
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
				title : '',
				
				autoHeight : true,
				layout:'form',
				html:"帮助"
				
			}]
		});
		return formPanel;
	},
	createWin : function() {
		return this.initWin(300, 200, '帮助');
	},
	storeMapping : ["stkId", "name", "stkWarehouse", "stkWare",
			"stkCount", "stkMemo"],
	initComponent : function() {
		this.cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
			header : '序号',
			sortable : true,
			dataIndex : 'stkId',
			width:85
		}, {
			header : '产品名称',
			sortable : true,
			dataIndex : 'name',
			width:200
		}, {
			header : '仓库',
			sortable : true,
			dataIndex : 'stkWarehouse',
			width:150
		}, {
			header : '货位',
			sortable : true,
			dataIndex : 'stkWare',
			width:140
		}, {
			header : '件数',
			sortable : true,
			dataIndex : 'stkCount',
			width:130
		}, {
			header : '备注',
			sortable : true,
			dataIndex : 'stkMemo',
			width:125
		}]);
		selectStoragePanel.superclass.initComponent.call(this);
	}
});
