Ext.namespace("CRM.custMan");
CRM.custMan.custOrdersDetail = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,
	initComponent : function() {
		this.store = new Ext.data.JsonStore({
			id : "id",
			url : 'customer.do?actionType=doFindDetailList&oddOrderId='
					+ oddOrderId,
			root : "data",
			totalProperty : "rowCount",
			remoteSort : true,
			fields : this.storeMapping
		});

		CRM.custMan.custOrdersDetail.superclass.initComponent.call(this);

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

custOrdersDetailPanel = Ext.extend(CRM.custMan.custOrdersDetail, {
	id : 'custOrdersDetail',
	storeMapping : ["prodName", "oddCount", "oddUnit", "oddPrice", "prodTotal",],
	initComponent : function() {
		this.cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
			header : '商品',
			sortable : true,
			dataIndex : 'prodName',
			width : 200
		}, {
			header : '数量',
			sortable : true,
			dataIndex : 'oddCount',
			width : 150
		}, {
			header : '单位',
			sortable : true,
			dataIndex : 'oddUnit',
			width : 180
		}, {
			header : '单价(元)',
			sortable : true,
			dataIndex : 'oddPrice',
			width : 150
		}, {
			header : '金额(元)',
			dataIndex : 'prodTotal',
			width : 150
		}]);
		custOrdersDetailPanel.superclass.initComponent.call(this);
	}
});