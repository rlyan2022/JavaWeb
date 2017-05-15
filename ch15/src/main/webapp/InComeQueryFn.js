function inComeQueryFn(){
	if(!inComeQueryPageIsOpen) {
		var tabPage = tabPanel.add({
						title:"收入查询",
						height:600,
						closable:true,
						listeners:{
							beforedestroy:function(){
								inComeQueryPageIsOpen = false;
							}
						}
					});
		tabPanel.setActiveTab(tabPage);
		inComeQueryPageIsOpen = true;
	}
}