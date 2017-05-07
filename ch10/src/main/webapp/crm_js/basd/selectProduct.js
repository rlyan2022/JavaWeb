Ext.namespace("CRM.baseData");
CRM.baseData.selectProduct = Ext.extend(Ext.Panel, {
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
		this.store.baseParams.prod_name = Ext.get('prod_name').getValue();
		this.store.baseParams.prod_type = Ext.get('prod_type').getValue();
		this.store.baseParams.prodBatch = Ext.get('prodBatch').getValue();
		this.store.load({
			params : {
				start : 0,
				limit : 18
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
									this.store.reload();
								}, this);
					},
					scope : this
				});
			}
		}, this);
	},

	initComponent : function() {
		this.store = new Ext.data.JsonStore({
			id : "id",
			url : this.baseUrl + '?actionType=doList',
			root : "data",
			totalProperty : "rowCount",
			baseParams : {
				prod_name : null,
				prod_type : null,
				prodBatch : null
			},
			remoteSort : true,
			fields : this.storeMapping
		});

		this.store.paramNames.sort = "ASC";
		this.store.paramNames.dir = "name";
		this.cm.defaultSortable = true;

		CRM.baseData.selectProduct.superclass.initComponent.call(this);

		this.grid = new Ext.grid.GridPanel({
			store : this.store,
			cm : this.cm,
			height : 300,
			stripeRows : true,
			pageSize : 10,
			tbar : [new Ext.Toolbar.Fill(), '产品名称：', {
				xtype : 'textfield',
				name : 'prod_name',
				width : 150
			}, '&nbsp;&nbsp;', '型号：', {
				xtype : "textfield",
				name : 'prod_type',
				width : 150
			}, '&nbsp;&nbsp;', '批次：', {
				xtype : 'textfield',
				name : 'prodBatch',
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

// 面板。。。。
selectProductPanel = Ext.extend(CRM.baseData.selectProduct, {
	id : 'selectProduct',
	baseUrl : 'product.do',
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
							name : 'h',
							fieldLabel : '帮助'
						}]
					}]
				}]
			}]
		});
		return formPanel;
	},
	createWin : function() {
		return this.initWin(555, 420, '帮助');
	},
	// 面板显示肉容区。。。。
	storeMapping : ["prodId", "prodName", "prodType", "prodBatch", "prodUnit",
			"prodPrice", "prodMemo"],
	initComponent : function() {
		this.cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
			header : '编号',
			sortable : true,
			dataIndex : 'prodId',
			width : 85
		}, {
			header : '产品名称',
			sortable : true,
			dataIndex : 'prodName',
			width : 145
		}, {
			header : '型号',
			sortable : true,
			dataIndex : 'prodType',
			width : 135
		}, {
			header : '等级/批次',
			sortable : true,
			dataIndex : 'prodBatch',
			width : 135
		}, {
			header : '单位',
			sortable : true,
			dataIndex : 'prodUnit',
			width : 100
		}, {
			header : '单价(元)',
			sortable : true,
			dataIndex : 'prodPrice',
			width : 100
		}, {
			header : '备注',
			sortable : true,
			dataIndex : 'prodMemo',
			width : 130
		}]);
		selectProductPanel.superclass.initComponent.call(this);
	}
});
