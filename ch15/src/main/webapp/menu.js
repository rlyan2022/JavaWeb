Ext.BLANK_IMAGE_URL = "Ext/resources/images/default/s.gif";
	//一级导航
	var root = new Ext.tree.TreeNode({
		id:"root",
		text:"根节点"
	});

	var quanxian;
	DWREngine.setAsync(false);
	Quanxian.getQuanxian(load);
	DWREngine.setAsync(true);
	function load(data) {
		quanxian = data;
	}

	if(quanxian == 1) {
		//用户管理
		var menuUserManager = new Ext.tree.TreeNode({
			id:"MenuUserManager",
			text:"用户管理",
			expanded:true
		});

		//用户添加
		var userAddMenu = new Ext.tree.TreeNode({
			id:"userAddMenu",
			text:"用户添加",
			listeners:{
				"click":userAddFn
			}
		});

		//用户管理
		var userManageMenu = new Ext.tree.TreeNode({
			id:"userManageMenu",
			text:"用户管理",
			listeners:{
				"click":userManageFn
			}
		});

		//用户查询
		var userQueryMenu = new Ext.tree.TreeNode({
			id:"userQueryMenu",
			text:"用户查询",
			listeners:{
				"click":userQueryFn
			}
		});
		menuUserManager.appendChild(userAddMenu);
		menuUserManager.appendChild(userManageMenu);
		menuUserManager.appendChild(userQueryMenu);

		root.appendChild(menuUserManager);
	}

	//支出管理
	var menuPayOut = new Ext.tree.TreeNode({
		id:"menuPayOut",
		text:"支出管理",
		expanded:true
	});

	var payOutAddMenu = new Ext.tree.TreeNode({
		id:"payOutAddMenu",
		text:"支出添加",
		listeners:{
			"click":payOutAddFn
		}
	});

	var payOutManageMenu = new Ext.tree.TreeNode({
		id:"payOutManageMenu",
		text:"支出管理",
		listeners:{
			"click":payOutManageFn
		}
	});

	var payOutQueryMenu = new Ext.tree.TreeNode({
		id:"payOutQueryMenu",
		text:"支出查询",
		listeners:{
			"click":payOutQueryFn
		}
	});
	menuPayOut.appendChild(payOutAddMenu);
	menuPayOut.appendChild(payOutManageMenu);
	menuPayOut.appendChild(payOutQueryMenu);

	//收入管理
	var menuIncome = new Ext.tree.TreeNode({
		id:"menuIncome",
		text:"收入管理",
		expanded:true
	});

	var incomeAddMenu = new Ext.tree.TreeNode({
		id:"incomeAddMenu",
		text:"收入添加",
		listeners:{
			"click":inComeAddFn
		}
	});

	var incomeManageMenu = new Ext.tree.TreeNode({
		id:"incomeManageMenu",
		text:"收入管理",
		listeners:{
			"click":inComeManageFn
		}
	});

	var incomeQueryMenu = new Ext.tree.TreeNode({
		id:"incomeQueryMenu",
		text:"收入查询",
		listeners:{
			"click":inComeQueryFn
		}
	});
	menuIncome.appendChild(incomeAddMenu);
	menuIncome.appendChild(incomeManageMenu);
	menuIncome.appendChild(incomeQueryMenu);


	root.appendChild(menuPayOut);
	root.appendChild(menuIncome);

	var menu = new Ext.tree.TreePanel({
		border:false,
		rootVisible:false,
		root:root
	});


	//添加布尔类型的变量，用来判断该页面是否打开
	var userAddPageIsOpen = false;
	var userManagePageIsOpen = false;
	var userQueryPageIsOpen = false;
	var payOutAddPageIsOpen = false;
	var payOutManagePageIsOpen = false;
	var payOutQueryPageIsOpen = false;
	var inComeAddPageIsOpen = false;
	var inComeManagePageIsOpen = false;
	var inComeQueryPageIsOpen = false;