var s = {
	
	start: function() {
		s.ieDetect();
		s.resizeCols();
		s.setFont();
		s.textScaler();
	},
	
	ieDetect: function() {
		s.IEVersion = false;
		var rv = -1;
		if ( navigator.appName == 'Microsoft Internet Explorer' ) {
			var ua = navigator.userAgent;
			var re  = new RegExp( "MSIE ([0-9]{1,}[\.0-9]{0,})" );
			if ( re.exec( ua ) != null )
			s.IEVersion = parseFloat( RegExp.$1 );
		}
	},
	
	resizeCols: function() {
		if( s.IEVersion != 6 ) {
			var col_left = $( 'col_left' ).getStyle( 'height' ).toInt();
			var col_right = $( 'col_right' ).getStyle( 'height' ).toInt();
			if( col_left > col_right ) {
				$( 'col_right' ).setStyle( 'height', ( ( col_left + 40 ) / 10 ) + 'em' );
			}
			if( col_right > col_left )
				$( 'col_left' ).setStyle( 'height', ( col_right / 10 ) + 'em' );
		}
	},
	
	setFont: function() {
		if( Cookie.read( 'semfont' ) ) {
			var size = Cookie.read( 'semfont' );
			$( 'wrapper' ).setStyle( 'font-size', size + 'px' );
			s.fontSize = size.toFloat();
		}
		else
			s.fontSize = 10;
	},
	
	textScaler: function() {
		var begin = ( s.fontSize == 10 ) ? 0 : ( s.fontSize - 10 ) * 10;
		new Slider( $( 'scale_area' ), $( 'scale_knob' ), {
			steps: 100,
			onChange: function( pos ) {
				var size = ( ( pos.toInt() / 10 ) + 10 );
				$( 'wrapper' ).setStyle( 'font-size', size + 'px' );
			},
			onComplete: function( pos ) {
				var size = ( ( pos.toInt() / 10 ) + 10 );
				if( Cookie.read( 'semfont' ) )
					Cookie.dispose( 'semfont' );
				Cookie.write( 'semfont', size, { path: '/', duration: 28 } );
				s.fontSize = size;
			}
		} ).set( begin );
	}
}

window.addEvent( 'domready', s.start );
