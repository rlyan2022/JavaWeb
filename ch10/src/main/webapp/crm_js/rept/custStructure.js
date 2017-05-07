Ext.namespace("CRM.custStructure");

CRM.custStructure.custStructure = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : "fit",
	maskDisabled : false,
	initComponent : function() {
		CRM.custStructure.custStructure.superclass.initComponent.call(this);
		this.levelStore = new Ext.data.JsonStore({
			url : this.baseUrl + '?actionType=doFindCustStructure',
			root : "data",
			totalProperty : "rowCount",
			baseParams : {
				type : '客户等级'
			},
			remoteSort : true,
			fields : ["custLevel", "number"]
		});
		this.satisfyStore = new Ext.data.JsonStore({
			url : this.baseUrl + '?actionType=doFindCustStructure',
			root : "data",
			totalProperty : "rowCount",
			baseParams : {
				type : '客户满意度'
			},
			remoteSort : true,
			fields : ["custSatisfy", "number"]
		});
		this.creditStore = new Ext.data.JsonStore({
			url : this.baseUrl + '?actionType=doFindCustStructure',
			root : "data",
			totalProperty : "rowCount",
			baseParams : {
				type : '客户信用度'
			},
			remoteSort : true,
			fields : ["custCredit", "number"]
		});
		this.levelStore.load();
		this.satisfyStore.load();
		this.creditStore.load();
	}
});
custLevelPanel = Ext.extend(CRM.custStructure.custStructure, {
	id : 'custLevel',
	baseUrl : 'report.do',
	initComponent : function() {
		custLevelPanel.superclass.initComponent.call(this);
		var custLevelGrid = new Ext.grid.GridPanel({
			width : 860,
			autoHeight : true,
			trackMouseOver : false,
			store : this.levelStore,
			cm : new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
				header : '客户等级',
				sortable : true,
				dataIndex : 'custLevel',
				width : 415,
				renderer : function(value) {
					var level = '';
					if (value == '普通客户') {
						level = '<font color="darkgray">' + value + '</font>';
					} else if (value == '重点开发客户') {
						level = '<font color="olive">' + value + '</font>';
					} else if (value == '大客户') {
						level = '<font color="purple">' + value + '</font>';
					} else if (value == '合作伙伴') {
						level = '<font color="slate">' + value + '</font>';
					} else {
						level = '<font color="red">' + value + '</font>';
					}
					return level;
				}
			}, {
				header : '客户数量',
				sortable : true,
				dataIndex : 'number',
				width : 415
			}]),
			stripeRows : true,
			pageSize : 10,
			loadMask : true,
			tbar : ['客户等级', new Ext.Toolbar.Fill()]
		});
		var custSatisfyGrid = new Ext.grid.GridPanel({
			width : 860,
			autoHeight : true,
			trackMouseOver : false,
			store : this.satisfyStore,
			cm : new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
				header : '客户满意度',
				sortable : true,
				dataIndex : 'custSatisfy',
				width : 415,
				renderer : function(value) {
					var satisfy = '';
					if (value == 1) {
						satisfy = '<font color="darkgray">☆</font>';
					} else if (value == 2) {
						satisfy = '<font color="olive">☆☆</font>';
					} else if (value == 3) {
						satisfy = '<font color="purple">☆☆☆</font>';
					} else if (value == 4) {
						satisfy = '<font color="slate">☆☆☆☆</font>';
					} else {
						satisfy = '<font color="red">☆☆☆☆☆</font>';
					}
					return satisfy;
				}
			}, {
				header : '客户数量',
				sortable : true,
				dataIndex : 'number',
				width : 415
			}]),
			stripeRows : true,
			pageSize : 10,
			loadMask : true,
			tbar : ['客户满意度', new Ext.Toolbar.Fill()]
		});
		var custCreditGrid = new Ext.grid.GridPanel({
			width : 860,
			autoHeight : true,
			trackMouseOver : false,
			store : this.creditStore,
			cm : new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
				header : '客户信用度',
				sortable : true,
				dataIndex : 'custCredit',
				width : 415,
				renderer : function(value) {
					var satisfy = '';
					if (value == 1) {
						satisfy = '<font color="darkgray">☆</font>';
					} else if (value == 2) {
						satisfy = '<font color="olive">☆☆</font>';
					} else if (value == 3) {
						satisfy = '<font color="purple">☆☆☆</font>';
					} else if (value == 4) {
						satisfy = '<font color="slate">☆☆☆☆</font>';
					} else {
						satisfy = '<font color="red">☆☆☆☆☆</font>';
					}
					return satisfy;
				}
			}, {
				header : '客户数量',
				sortable : true,
				dataIndex : 'number',
				width : 415
			}]),
			stripeRows : true,
			pageSize : 10,
			loadMask : true,
			tbar : ['客户信用度', new Ext.Toolbar.Fill()]
		});
		this.add(custLevelGrid);
		this.add(custSatisfyGrid);
		this.add(custCreditGrid);

	}
});
