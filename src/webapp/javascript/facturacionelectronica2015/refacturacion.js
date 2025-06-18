function isMobile() { 
	if( navigator.userAgent.match(/Android/i)
		|| navigator.userAgent.match(/webOS/i)
		|| navigator.userAgent.match(/iPhone/i)
		|| navigator.userAgent.match(/iPad/i)
		|| navigator.userAgent.match(/iPod/i)
		|| navigator.userAgent.match(/BlackBerry/i)
		|| navigator.userAgent.match(/Windows Phone/i)
	){
		return true;
	}else {
		return false;
	}
};

$(document).ready(function(){
	$("#notification-shape").hide();
	var notification = function (msg, route) {
		if (Object.prototype.toString.call( msg ) !== '[object Array]') {
			classie.add($(this)[0], 'active');
			return;
		}
		$("#notification-shape").show();
		var svgshape = document.getElementById('notification-shape'),
			s = Snap(svgshape.querySelector('svg')),
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

						$("#notification-shape").fadeOut();
					}, 200);
				}
			});
			notification.show();
		}, 1200);

		this.disabled = true;

		setTimeout(function(){
			if($(".ns-box-inner").text() !== msg[1]) {
				window.location.href = route;
			}
		}, 4000);
	}

	$("#facturaDatos").validationEngine('attach', {
		focusFirstField : false,
		promptPosition: 'inline',
		onValidationComplete: function(form, status) {
			if ( status ) {
				$("#tipoPersonaInfo").text($("#moral").is(":checked")?'Moral':'F�sica');
				$("#rfcInfo").text($("#rfc").val());
				$("#direccionInfo").text($("#direccion").val());
				$("#delegacionInfo").text($("#delegacion").val());
//				$("#estadoInfo").text($("#estado").children().eq($("#estado").val()).text());
				$("#razonSocialInfo").text($("#razonSocial").val());
				$("#coloniaInfo").text($("#colonia").val());
				$("#codigoPostalInfo").text($("#codigoPostal").val());
				$("#emailInfo").text($("#email").val());
				$("body").addClass("modal-open");
			}
		}
	});

	$("#continuar").click(function () {
		$("body").addClass("modal2-open");
	})

	$("#modalAccept").click(function () {
		$("body").removeClass("modal-open");
		notification(["Los datos se han enviado correctamente"], 'http://www.zudulio.com/olab2/refacturacion-paso4.html');
	})

	$("#modalBack").click(function () {
		$("body").removeClass("modal-open");
	})

	if (!isMobile()) {
		$("#codigoPostal").inputmask("99999",{placeholder:""});
		$("#rfc").inputmask("A{3,4}9{6}#{3}",{placeholder:""});
		$("#consecutivo").inputmask("9999999",{placeholder:""});
		$("#password").inputmask("AAAA",{placeholder:""});
		$("#email").inputmask(undefined,{placeholder:""});
		$("#razonSocial").inputmask("?{0,}", {
			definitions: {
				"?": {
					validator: "[A-Za-z����������\ 0-9\-\.@&]+",
					cardinality: 1,
					casing: "upper"
				}
			}
		});
		$(":input")
			.not("#codigoPostal, #rfc, #consecutivo, #password, #email, #razonSocial")
			.inputmask("?{0,}", {
				definitions: {
					"?": {
						validator: "[A-Za-z����������\ 0-9\-]+",
						cardinality: 1,
						casing: "upper"
					}
				}
			});
	}

	$("#facturas").validationEngine('attach', {
		focusFirstField : false,
		promptPosition: 'inline',
		onValidationComplete: function(form, status) {
			if ( status ) {
				notification(["Tu n&uacute;mero de &oacute;rden es: 29381"], 'http://www.zudulio.com/olab2/refacturacion-paso2.html');
			}
		}
	});

	$(".fileButton").click(notification);

	if (!isMobile()){
		var native_width = 0;
		var native_height = 0;

		$(".magnify").mousemove( function(e) {

			if (!native_width && !native_height) {

				var image_object = new Image();
				image_object.src = $(".small").attr("src");
				native_width = image_object.width;
				native_height = image_object.height;
			}

			else {

				var magnify_offset = $(this).offset();
				var mx = e.pageX - magnify_offset.left;
				var my = e.pageY - magnify_offset.top;

				if (mx < $(this).width() && my < $(this).height() && mx > 0 && my > 0){
					$(".large").fadeIn(100);
				}

				else{
					$(".large").fadeOut(100);
				}

				if ($(".large").is(":visible")){

					var rx = Math.round(mx
							/ $(".small").width()
							* native_width
							- $(".large").width() / 2)
							* -1;

					var ry = Math.round(my
							/ $(".small").height()
							* native_height
							- $(".large").height() / 2)
							* -1;

					var bgp = rx + "px " + ry + "px";
					var px = mx - $(".large").width() / 2;
					var py = my - $(".large").height() / 2;

					$(".large").css({
						left : px,
						top : py,
						backgroundPosition : bgp
					});

				}

			}
		});
	}
});