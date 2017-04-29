
FCKConfig.ToolbarSets["myToolbar"] = [		//自定义工具栏
	['Source','-','Preview'],
	['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	'/',
	['Bold','Italic','Underline','StrikeThrough','-','Subscript','Superscript'],
	['OrderedList','UnorderedList','-','Outdent','Indent','Blockquote','CreateDiv'],
	['JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'],
	['Image','Table','Rule','Smiley','SpecialChar','PageBreak'],
	'/',
	['Style','FontFormat','FontName','FontSize'],
	['TextColor','BGColor'],
	['FitWindow','ShowBlocks']		// No comma for the last row.
] ;


FCKConfig.FontNames	= '宋体;黑体;方正舒体;方正姚体;仿宋;华文行楷;楷体' ;//自定义字体


FCKConfig.FontSizes		= '5px;10px;15px;20px;25px;30px;35px;40px;45px' ;//自定义字体大小


FCKConfig.SmileyPath	= FCKConfig.BasePath + 'images/smiley/qq/' ;//表情图片存放地址

FCKConfig.SmileyImages	= ['001.gif','002.gif','003.gif','004.gif',//表情图片列表
							'005.gif','006.gif','007.gif',
							'008.gif','009.jpg','010.jpg',
							'011.gif','012.gif','013.gif','014.gif',
							'015.gif','016.gif','017.jpg'
							] ;

FCKConfig.SmileyColumns = 4 ;//设定表情图片显示列数

FCKConfig.SmileyWindowWidth	= 320 ;//设置表情图片的宽
FCKConfig.SmileyWindowHeight	= 110 ;//设置表情图片的高				