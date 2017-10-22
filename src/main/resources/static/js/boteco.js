$(function() {
	$('.js-currency').maskMoney({prefix:'R$ ', allowNegative: true, thousands:'.', decimal:',', affixesStay: false});
	$('.js-currency-porcento').maskMoney({allowNegative: true, decimal:',', affixesStay: false});
})
