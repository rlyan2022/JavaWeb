Ext.namespace("CRM.baseData");

function onpedele() {
	var dele = new dataManagePanel();
	dele.removeData();
}
function onpesava() {
	var panel = new dataManagePanel();
	panel.edit();
}

var dataManStore = new Ext.data.JsonStore({
	id : "id",
	url : 'basdict.do?actionType=doList',
	root : "data",
	totalProperty : "rowCount",
	baseParams : {
		dict_type : null,
		dict_item : null,
		dict_value : null
	},
	remoteSort : true,
	fields : ["dictId", "dictType", "dictItem", "dictValue", "dictIsEditable",
			"option"]
});

var dataManColm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
	header : '编号',
	sortable : true,
	dataIndex : 'dictId',
	width : 140
}, {
	header : '类别',
	sortable : true,
	dataIndex : 'dictType',
	width : 150
}, {
	header : '条目',
	sortable : true,
	dataIndex : 'dictItem',
	width : 150
}, {
	header : '值',
	sortable : true,
	dataIndex : 'dictValue',
	width : 142
}, {
	header : '是否可编辑',
	sortable : true,
	dataIndex : 'dictIsEditable',
	width : 150
}, {
	header : '操作',
	dataIndex : 'dictIsEditable',
	renderer : function(value) {
		var operation = '';
		if (value == '是') {
			operation += '<img src="images/bt_edit.gif" onclick="onpesava()" />';
			operation += '<img src="images/bt_del.gif " onclick="onpedele()"/>';
		}
		return operation;
	}
}]);
var dataManGrid = new Ext.grid.GridPanel({
	store : dataManStore,
	cm : dataManColm,
	height : 300,
	stripeRows : true,
	pageSize : 10,
	tbar : [ {
		text : '新建',
		id : 'add',
		iconCls : 'add',
		pressed : false,
		handler : function() {
			var panel = new dataManagePanel();
			panel.create();
		},
		scope : this
	}, new Ext.Toolbar.Fill(), '类别：', {
		xtype : 'textfield',
		name : 'dict_type',
		width : 150
	}, '&nbsp;&nbsp;', '条目：', {
		xtype : "textfield",
		name : 'dict_item',
		width : 150
	}, '&nbsp;&nbsp;', '值：', {
		xtype : 'textfield',
		name : 'dict_value',
		width : 150
	}, {
		text : '查询',
		iconCls : 'search',
		pressed : true,
		handler : function() {
			var panel = new dataManagePanel();
			panel.search();
		},
		scope : this
	}, '   '],
	bbar : new Ext.PagingToolbar({
		pageSize : 10,
		store : dataManStore,
		grid : dataManGrid,
		displayInfo : true,
		displayMsg : '当前显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});

CRM.baseData.dataManage = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,
	gridViewConfig : {
		animate : true
	},
	refresh : function() {
		dataManStore.removeAll();
		dataManStore.reload();
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

	search : function() {
		;
		dataManStore.baseParams.dict_type = Ext.get('dict_type').getValue();

		dataManStore.baseParams.dict_item = Ext.get('dict_item').getValue();
		dataManStore.baseParams.dict_value = Ext.get('dict_value').getValue();

		dataManStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
	},
	//编辑操作...
	edit : function() {
		var record = dataManGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请选择要编辑的行!");
			return;
		}
		var id = record.get("id");
		this.showWin();
		this.fp.form.loadRecord(record);
	},
	//保存操作....
	save : function() {
		var id = this.fp.form.findField("dictId").getValue();
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : this.baseUrl + '?actionType=doSaveandUpdate',
				method : 'POST',
				success : function(form, action) {
					Ext.Msg.alert("系统消息", action.result.msg, function() {
						this.closeWin();
						dataManStore.reload();
					}, this);
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg);
				},
				scope : this
			});
		}
	},
	// 删除操作。。。。
	removeData : function() {
		var record = dataManGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请先选择要删除的行!");
			return;
		}
		Ext.MessageBox.confirm("确认删除", "确认删除所选数据？", function(button) {
			if (button == "yes") {
				Ext.Ajax.request({
					url : this.baseUrl + '?actionType=doDelete',
					params : {
						dictId : record.get("dictId")
					},
					method : 'POST',
					success : function(response) {
						Ext.Msg.alert("提示信息", response.responseText,
								function() {
									dataManStore.reload();
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

		CRM.baseData.dataManage.superclass.initComponent.call(this);

		dataManGrid.on("celldblclick", function() {
			this.edit();
		}, this);
		
//		右击操作
		dataManGrid.addListener("rowContextmenu", this.showMenu, this);
		dataManStore.load({
			params : {
				start : 0,
				limit : 10
			}
		});
		this.add(dataManGrid);
	}
});

// 内容面板。。。。。
dataManagePanel = Ext.extend(CRM.baseData.dataManage, {
	id : 'dataManage',
	baseUrl : 'basdict.do',
	// 新建条目表单。。。。
	createForm : function() {
		var formPanel = new Ext.form.FormPanel({
			labelWidth : 70,
			frame : true,
			autoHeight : true,
			resizable : false,
			labelAlign : 'right',
			defaultType : 'textfield',
			items : [{
				xtype : 'hidden',
				name : 'dictId'
			}, {
				name : 'dictType',
				fieldLabel : '类别',
				allowBlank : false,
				blankText : '类别为必填项'
			}, {
				name : 'dictItem',
				fieldLabel : '条目',
				allowBlank : false,
				blankText : '条目为必填项'
			}, {
				name : 'dictValue',
				fieldLabel : '值',
				allowBlank : false,
				blankText : '值为必填项'
			}, {
				xtype : 'combo',
				fieldLabel : '是否可编辑',
				name : 'dictIsEditable',
				hiddenname : 'sex',
				store : new Ext.data.SimpleStore({
					fields : ['returnValue', 'displayText'],
					data : [['是', '是'], ['否', '否']]
				}),
				returnField : 'returnValue',
				displayField : 'displayText',
				mode : 'local',
				forceSelection : true,
				editable : false,
				triggerAction : 'all',
				emptyText : '请选择',
				width : 125
			}]
		});
		return formPanel;
	},
	createWin : function() {

		return this.initWin(300, 215, '新建数据字典条目');

	},

	// 面板内容显示。。。。。

	initComponent : function() {

		dataManagePanel.superclass.initComponent.call(this);
	}
});
