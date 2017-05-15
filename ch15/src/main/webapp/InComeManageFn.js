function inComeManageFn(){
	if(!inComeManagePageIsOpen) {
		var tabPage = tabPanel.add({
						title:"收入管理",
						height:600,
						closable:true,
						listeners:{
							beforedestroy:function(){
								inComeManagePageIsOpen = false;
							}
						}
					});
		tabPanel.setActiveTab(tabPage);
		inComeManagePageIsOpen = true;
	}
}