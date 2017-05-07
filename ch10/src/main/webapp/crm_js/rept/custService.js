Ext.namespace("CRM.custService");
CRM.custService.custService = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,
	gridViewConfig : {
		animate : true
	},
	search : function() {
		this.store.baseParams.svrDate = Ext.get('svrDate').getValue();
		this.store.load({
			params : {
				start : 0,
				limit : 10
			}
		});
	},
	initComponent : function() {
		this.store = new Ext.data.JsonStore({
			url : this.baseUrl + '?actionType=doFindService',
			root : "data",
			totalProperty : "rowCount",
			baseParams : {
				svrDate : null
			},
			remoteSort : true,
			fields : this.storeMapping
		});

		CRM.custService.custService.superclass.initComponent.call(this);
		this.grid = new Ext.grid.GridPanel({
			store : this.store,
			cm : this.cm,
			height : 300,
			stripeRows : true,
			pageSize : 10,
			loadMask : true,
			tbar : [new Ext.Toolbar.Fill(), '年份:', {
				xtype : 'combo',
				name : 'svrDate',
				store : new Ext.data.JsonStore({
					url : 'report.do?actionType=doFindSvrDate',
					root : 'data',
					method : 'POST',
					fields : ['svrDate']
				}),
				displayField : 'svrDate',
				forceSelection : true,
				triggerAction : 'all',
				pageSize : 10,
				editable : false,
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
		this.store.load({
			params : {
				start : 0,
				limit : 10
			}
		});
		this.add(this.grid);
	}
});
custServicePanel = Ext.extend(CRM.custService.custService, {
	id : 'custService',
	baseUrl : 'report.do',
	storeMapping : ["svrType", "svrCount"],
	initComponent : function() {
		this.cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
			header : '条目',
			sortable : true,
			dataIndex : 'svrType',
			width : 415
		}, {
			header : '服务数量',
			sortable : true,
			dataIndex : 'svrCount',
			width : 415
		}]);
		custServicePanel.superclass.initComponent.call(this);
	}
});
