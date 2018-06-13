'use strict';

(function($) {
	$.fn.getCursorPosition = function() {
		var input = this.get(0);
		if (!input) return; // No (input) element found
		if ('selectionStart' in input) {
			// Standard-compliant browsers
			return input.selectionStart;
		} else if (document.selection) {
			// IE
			input.focus();
			var sel = document.selection.createRange();
			var selLen = document.selection.createRange().text.length;
			sel.moveStart('character', -input.value.length);
			return sel.text.length - selLen;
		}
	}
})(jQuery);

function isMobile() {
	if( navigator.userAgent.match(/Android/i) ||
		navigator.userAgent.match(/webOS/i) ||
		navigator.userAgent.match(/iPhone/i) ||
		navigator.userAgent.match(/iPad/i) ||
		navigator.userAgent.match(/iPod/i) ||
		navigator.userAgent.match(/BlackBerry/i) ||
		navigator.userAgent.match(/Windows Phone/i)
	){
		return true;
	}else {
		return false;
	}
}

$(document).ready(function(){
	if (Math.random() < .5) {
		$('.reMsg').hide();
	} else {
		$('.msg').hide();
	}

	$('#notification-shape').hide();
	var notification = function (msg, routes) {
		if (Object.prototype.toString.call( msg ) !== '[object Array]') {
			classie.add($(this)[0], 'active');
			return;
		}
		$('#notification-shape').show();
		var svgshape = document.getElementById('notification-shape'),
			s = new Snap(svgshape.querySelector('svg')),
			path = s.select('path'),
			pathConfig = {
				from : path.attr('d'),
				to : svgshape.getAttribute('data-path-to')
			},
			bttn = document.getElementById('notification-trigger');

		classie.add(bttn, 'active');

		setTimeout(function() {

			path.animate({
				'path' : pathConfig.to
			}, 300, mina.easeinout);

			classie.remove(bttn, 'active');

			// create the notification
			var notification = new NotificationFx({
				wrapper : svgshape,
				message : '<p><span class="icon icon-mail"></span>'+msg[Math.floor(Math.random()*msg.length)]+'</p>',
				layout : 'other',
				effect : 'cornerexpand',
				type : 'notice', // notice, warning or error

				onClose : function() {
					bttn.disabled = false;

					setTimeout(function() {
						path.animate({
							'path' : pathConfig.from
						},
						300,
						mina.easeinout);

						$('#notification-shape').fadeOut();
					}, 200);
				}
			});
			notification.show();
		}, 1200);

		setTimeout(function(){
			if($('.ns-box-inner').text() !== msg[1]) {
				var route = routes[Math.floor(Math.random()*routes.length)];
				window.location.href = route;
			}
		}, 4000);
	};

	$('#facturaDatos').validationEngine('attach', {
		focusFirstField : false,
		promptPosition: 'inline',
		onValidationComplete: function(form, status) {
			if ( status ) {
				$('#tipoPersonaInfo').text($('#moral').is(':checked')?'Moral':'Fisica');
				$('#rfcInfo').text($('#rfc').val());
				$('#direccionInfo').text($('#direccion').val());
				$('#delegacionInfo').text($('#delegacion').val());				
				$('#estadoInfo').text($('#estado').val());
				$('#razonSocialInfo').text($('#razonSocial').val());
				$('#coloniaInfo').text($('#colonia').val());
				$('#codigoPostalInfo').text($('#codigoPostal').val());
//				$('#emailInfo').text($('#email').val());
				$('body').addClass('modal-open');
			}
		}
	});

	$('#continuar').click(function () {
		$('body').addClass('modal2-open');
	});

	$('#modalAccept').click(function () {
		$('body').removeClass('modal-open');
		altaDatosFiscales($('#rfc').val(),$('#direccion').val(),$('#delegacion').val(),$('#estado').val(),$('#razonSocial').val(),$('#colonia').val(),$('#codigoPostal').val());
	});

	$('#modalBack').click(function () {
		$('body').removeClass('modal-open');
	});

//	$('#facturas').validationEngine('attach', {
//		focusFirstField : false,
//		promptPosition: 'inline',
//		onValidationComplete: function(form, status) {
//			if ( status ) {
//				notification(['Procesando Solicitud Facturacion'], ['validaAccesoFacturacionElectronica.jsp?kordensucursalfinal=' + $("#consecutivo").val() + '&emailfinal=' + $("#email").val() + '&passwordfinal=' + $("#password").val() + '']);								
//			}
//		}
//	});
	
	$('.fileButton').click(notification);

	if (!isMobile()) {
		var native_width = 0;
		var native_height = 0;

		$('#codigoPostal').inputmask('99999',{placeholder:''});
		$('#consecutivo').inputmask('9999999',{placeholder:''});
		$('#password').inputmask('AAAA',{placeholder:''});
//		$('#email').inputmask(undefined,{placeholder:''});
		$('#razonSocial').inputmask('?{0,}', {
			definitions: {
				'?': {
					validator: '[a-zA-ZáéíóúÁÉÍÓÚ\\ 0-9\\-\\.@& \\u00F1]+',
					cardinality: 1,
					casing: 'upper'
				}
			}
		});
		$(':input')
			.not('#codigoPostal, #rfc, #consecutivo, #password, #razonSocial')
//			.not('#codigoPostal, #rfc, #consecutivo, #password, #email, #razonSocial')
			.inputmask('?{0,}', {
				definitions: {
					'?': {
						validator: '[A-Za-záéíóúÁÉÍÓÚ\\ 0-9\\-]+',
						cardinality: 1,
						casing: 'upper'
					}
				}
			});


		$('.magnify').mousemove( function(e) {

			if (!native_width && !native_height) {

				var image_object = new Image();
				image_object.src = $('.small').attr('src');
				native_width = image_object.width;
				native_height = image_object.height;
			}

			else {

				var magnify_offset = $(this).offset();
				var mx = e.pageX - magnify_offset.left;
				var my = e.pageY - magnify_offset.top;

				if (mx < $(this).width() && my < $(this).height() && mx > 0 && my > 0){
					$('.large').fadeIn(100);
				}

				else{
					$('.large').fadeOut(100);
				}

				if ($('.large').is(':visible')){

					var rx = Math.round(mx / $('.small').width() *
							native_width - $('.large').width() / 2) * -1;

					var ry = Math.round(my / $('.small').height() *
							native_height - $('.large').height() / 2) * -1;

					var bgp = rx + 'px ' + ry + 'px';
					var px = mx - $('.large').width() / 2;
					var py = my - $('.large').height() / 2;

					$('.large').css({
						left : px,
						top : py,
						backgroundPosition : bgp
					});

				}

			}
		});
		var $rfcInput = $('#rfc');
		$('#moral, #fisica').click(function () {
			initData();
			build();
			$rfcInput.focus();
		});

		initData();
		$rfcInput.bind('selectstart', function(){ return false; });

		$rfcInput.on('keydown', function (e) {
			var keyCode = e.keyCode,
				deleteKey = (keyCode == 46),
				backspaceKey = (keyCode == 8),
				moral = $('#moral').is(':checked'),
				position = $rfcInput.getCursorPosition(),
				data = $rfcInput.data(),
				val, sel, deletedText, removed, removedLength, piece;

			if (deleteKey || backspaceKey) {
				e.preventDefault();
				val = this.value;
				sel = getInputSelection(this);
				if (sel.length) {
					deletedText = val.slice(sel.start, sel.end);
				} else {
					deletedText = val.charAt(deleteKey ? sel.start : sel.start - 1);
				}

				sel.length = deletedText.length;
				if (deleteKey) {
					position++;
				}

				val = val.substring(0, val.length - 1);

				piece = val.substring(0,(moral?3:4));
				val = val.substring(moral?3:4);
				data.initials = checkInitials(piece);

				piece = val.substring(0,2);
				val = val.substring(2);
				data.year = checkYear(piece);

				piece = val.substring(0,2);
				val = val.substring(2);
				data.month = checkMonth(piece);

				piece = val.substring(0,2);
				val = val.substring(2);
				data.day = checkDay(piece, data.month, data.year);

				piece = val.substring(0,3);
				data.homoKey = checkHomoKey(val);

				validateData(data);
				$rfcInput.data(data);
				build();
			}
		});

		$rfcInput.on('keypress', function (e) {
			var position = $rfcInput.getCursorPosition(),
				isLast = $rfcInput.data('prev').length === position,
				moral = $('#moral').is(':checked'),
				charCode = e.charCode,
				current = $rfcInput.val().split(''),
				data = $rfcInput.data(),
				letter = '',
				initials ='',
				year = '',
				month = '',
				day = '',
				homoKey = '';

			e.preventDefault();

			if (isLast) {
				if (moral && position <= 2 || !moral && position <= 3 ) {
					data.initials = checkInitials(data.initials + fromCharCode(charCode));
				} else if (moral && position <= 4 || !moral && position <= 5 ) {
					data.year = checkYear(data.year + fromCharCode(charCode));
				} else if (moral && position <= 6 || !moral && position <= 7 ) {
					data.month = checkMonth(data.month + fromCharCode(charCode));
				} else if (moral && position <= 8 || !moral && position <= 9 ) {
					data.day = checkDay(data.day + fromCharCode(charCode), data.month, data.year);
				} else {
					data.homoKey = checkHomoKey(data.homoKey + fromCharCode(charCode));
				}
			}

			validateData(data);
			$rfcInput.data(data);
			build();
		});

		// $rfcInput.on('keyup click focus', function () {
		// 	var val = this.value;
		// 	this.value = '';
		// 	this.value = val;
		// })
	}
});

var A = 65, Z = 90, a = 97, z = 122, n0 = 48, n9 = 57;

var initData = function () {
	var $rfcInput = $('#rfc');
	$rfcInput.data('prev', '');
	$rfcInput.data('initials', '');
	$rfcInput.data('year', '');
	$rfcInput.data('month', '');
	$rfcInput.data('day', '');
	$rfcInput.data('homoKey', '');

	$rfcInput.data('deleted', {
		initials: -1,
		year: -1,
		month: -1,
		day: -1,
		homoKey: -1
	});

	$rfcInput.data('valid', {
		initials: false,
		year: false,
		month: false,
		day: false,
		homoKey: false
	});
};

var validateData = function (data) {
	var $rfcInput = $('#rfc'),
		moral = $('#moral').is(':checked'),
		errorPrompt = '';

	if (data.initials.length == (moral?3:4)) {
		data.valid.initials = true;
	} else {
		errorPrompt += '* El RFC debe iniciar con ' + (moral?3:4) +' letras<br>';
	}

	if (data.year.length == 2) {
		data.valid.year = true;
	} else {
		errorPrompt += '* El año debe incluir 2 digitos<br>';
	}

	if (data.month.length == 2) {
		data.valid.month = true;
	} else {
		errorPrompt += '*Se debe incluir un mes valido<br>';
	}

	if (data.day.length == 2) {
		data.valid.day = true;
	} else {
		errorPrompt += '* Se debe incluir un dia valido<br>';
	}

	if (data.homoKey.length == 3) {
		data.valid.homoKey = true;
	} else {
		errorPrompt += '* La homoclave deben ser 3 caracteres alfanumericos<br>';
	}

	if (errorPrompt === '') {
		$rfcInput.validationEngine('hide');
	} else {
		$rfcInput.validationEngine('showPrompt', errorPrompt);
	}
}

var build = function () {
	var $rfcInput = $('#rfc'),
		data = $rfcInput.data(),
		value = '';

	value += data.initials;
	value += data.year;
	value += data.month;
	value += data.day;
	value += data.homoKey;

	$rfcInput.val(value);
	$rfcInput.data('prev', value);

//	console.log(data);
}
var toUppercase = String.prototype.toUpperCase;
var fromCharCode = String.fromCharCode;

var daysInMonth = function (m, y) { // m is 0 indexed: 0-11
	m -= 1;
    switch (m) {
        case 1 :
            return (y % 4 == 0 && y % 100) || y % 400 == 0 ? 29 : 28;
        case 8 : case 3 : case 5 : case 10 :
            return 30;
        default :
            return 31
    }
};

var isValidDay = function (d, m, y) {
	d = parseInt(d);
	m = parseInt(m);
	y = parseInt(y);

    return m >= 0 && m < 13 && d > 0 && d <= daysInMonth(m, y);
};

var getInputSelection = function (input) {
	var start = 0, end = 0;
	input.focus();
	if (typeof input.selectionStart == "number" &&
		typeof input.selectionEnd == "number") {

		start = input.selectionStart;
		end = input.selectionEnd;
	} else if (document.selection && document.selection.createRange) {
		var range = document.selection.createRange();
		if (range) {
			var inputRange = input.createTextRange();
			var workingRange = inputRange.duplicate();
			var bookmark = range.getBookmark();
			inputRange.moveToBookmark(bookmark);
			workingRange.setEndPoint("EndToEnd", inputRange);
			end = workingRange.text.length;
			workingRange.setEndPoint("EndToStart", inputRange);
			start = workingRange.text.length;
		}
	}
	return {
		start: start,
		end: end,
		length: end - start
	};
};

var checkNumber = function (charCode) {
	if (charCode >= n0 && charCode <= n9) {
		return fromCharCode(charCode);
	}

	return false;
};

var checkLetter = function (charCode) {
	if (charCode >= a && charCode <= z || charCode >= A && charCode <= Z) {
		return toUppercase.apply(fromCharCode(charCode));
	}

	return false;
};

var checkLetterNumber = function (charCode) {
	if (charCode >= a && charCode <= z || charCode >= A && charCode <= Z || charCode >= n0 && charCode <= n9) {
		return toUppercase.apply(fromCharCode(charCode));
	}

	return false;
};

var checkHomoKey = function (homoKey) {
	var value = '', i;
	for (i = 0; i < homoKey.length; value += i <= 2 && checkLetterNumber(homoKey.charCodeAt(i)) || '', i++);
	return toUppercase.apply(value);
};

var checkInitials = function (initials) {
	var value = '', i;
	for (i = 0; i < initials.length; value += checkLetter(initials.charCodeAt(i)) || '', i++);
	return toUppercase.apply(value);
}

var checkYear = function (year) {
	if ( checkNumber(year.charCodeAt(0)) ) {
		if (year.length == 1) {
			return year;
		} else {
			if ( checkNumber(year.charCodeAt(1)) ) {
				return year;
			} else {
				return year[0];
			}
		}
	}

	return '';
};

var checkMonth = function (month) {
	if (month.length == 1) {
		if ([0,1].indexOf(parseInt(month[0])) !== -1) {
			return month;
		}
	} else {
		if (month[0] == 1) {
			if ([0,1,2].indexOf(parseInt(month[1])) !== -1) {
				return month;
			} else {
				return month[0];
			}
		} else if (month[0] == 0) {
			if (month.charCodeAt(1) >= n0 && month.charCodeAt(1) <= n9) {
				return month;
			} else {
				return month[0];
			}
		}
	}

	return '';
};

var checkDay = function (day, month, year) {
	if (day.length == 1) {
		if (month == '02' && [0,1,2].indexOf(parseInt(day[0])) !== -1) {
			return day;
		} else if (month !== '02' && [0,1,2,3].indexOf(parseInt(day[0])) !== -1) {
			return day;
		}
	} else {
		if ((month == '02' && [0,1,2].indexOf(parseInt(day[0])) !== -1) || (month !== '02' && [0,1,2,3].indexOf(parseInt(day[0])) !== -1)) {
			if (checkNumber(day.charCodeAt(1))) {
				if (isValidDay(day, month, year)) {
					return day;
				} else {
					return day[0];
				}
			} else {
				return day[0];
			}
		}
	}

	return '';
};