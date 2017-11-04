$(function() {
	$('.js-currency').maskMoney({
		prefix : 'R$ ',
		allowNegative : true,
		thousands : '.',
		decimal : ',',
		affixesStay : false
	});
	$('.js-currency-porcento').maskMoney({
		allowNegative : true,
		decimal : ',',
		affixesStay : false
	});
	$("input[class*='cpf']").inputmask({
		mask : [ '999.999.999-99' ],
		keepStatic : true
	});
	$("input[id*='cnpj']").inputmask({
		mask : [ '99.999.999/9999-99' ],
		keepStatic : true
	});

	// responsável pela rotina de adicionar o produto na comanda

	$('.js-add-produto').on('click', function(event) {
		event.preventDefault();

		var codigoComanda = $("#codigoComanda").attr("comandaCodigo");
		var codigoProduto = $("#produtos").val();
		var linkComanda = $("#js-url").attr("href");
		var linknovo = linkComanda + "=" + codigoProduto.toString();

		$('href').append(linknovo);

		var response = $.ajax({
			url : linknovo,
			type : 'PUT'
		});

		response.done(function(e) {
			$("#tabela-produtos").load(" #tabela-produtos");
			$("#debito-comanda").load(" #debito-comanda");
			$("#credito-comanda").load(" #credito-comanda");

			if (e == "credito insuficiente")
				alert(e)
			if (e == "Comanda cancelada") {
				alert(e)
			}
		});

		response.fail(function(e) {
			console.log(e);
		});
	});
	

	/*
	 * responsável por disabilitar os componentes da comanda quando a mesma for
	 * CANCELADA
	 * 
	 */
	var statusComanda = $('#status').val();
	if (statusComanda == 'CANCELADA') {
		$(".credito-habilidato").prop("disabled", true);
		$(".cliente-habilitado").prop("disabled", true);
		$(".observacao-habilitado").prop("disabled", true);
		$(".mesa-habilitada").prop("disabled", true);
		$(".produtos-habilitado").prop("disabled", true);
	}

	/*
	 * Respónsavel por creditar a comanda
	 */
	$('.add-credito-comanda').on(
			'click',
			function(event) {
				event.preventDefault();

				var codigoComanda = $("#codigoDaComanda").val();
				var valorCredito = $(".credito-comanda-modal").val();
				var link = $(".add-credito-comanda").attr("href");
				var linknovo = link + "=" + codigoComanda + "&credito="
						+ mascaraValor(valorCredito);

				var response = $.ajax({
					url : linknovo,
					type : 'PUT'
				});

				response.done(function(e) {
					$("#credito-comanda").load(" #credito-comanda");
					$(".credito-comanda-modal").val("");
					$(".modal-credito-comanda").modal('toggle');
				});

				response.fail(function(e) {
					alert("Erro ao creditar comanda. ");
				});
			});

	function mascaraValor(valor) {
		valor = valor.toString().replace(/\D/g, "");
		valor = valor.toString().replace(/(\d)(\d{2})$/, "$1.$2");
		return valor
	}
});
