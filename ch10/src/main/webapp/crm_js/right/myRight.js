Ext.namespace("CRM.rightMan");
CRM.rightMan.myRight = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,
	initComponent : function() {
		CRM.rightMan.myRight.superclass.initComponent.call(this);
		this.store = new Ext.data.JsonStore({
			id : "id",
			url : 'myRight.do?actionType=dofindRightName&roleId=' + currentRole,
			root : "hasRight",
			// totalProperty : "rowCount",
			remoteSort : true,
			fields : this.storeMapping
		});
		this.grid = new Ext.grid.GridPanel({
			store : this.store,
			cm : this.cm,
			height : 300,
			stripeRows : true,
			pageSize : 10,
			bbar : new Ext.PagingToolbar({
				pageSize : 10,
				store : this.store,
				grid : this.grid,
				displayInfo : true,
				displayMsg : '显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
				emptyMsg : "没有记录"
			})
		});
		this.store.load({
			params : {
				start : 0,
				limit : 10
			}
		});
		this.add(this.grid);
	}
});
myRightPanel = Ext.extend(CRM.rightMan.myRight, {
	id : 'myRight',
	storeMapping : ["rightText"],
	initComponent : function() {
		this.cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
			header : '我的权限',
			sortable : true,
			dataIndex : 'rightText',
			align:'center',
			width:830
		}]);
		myRightPanel.superclass.initComponent.call(this);
	}
})
