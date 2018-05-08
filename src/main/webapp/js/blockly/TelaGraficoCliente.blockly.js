window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.blockly = window.blockly.js.blockly || {};
window.blockly.js.blockly.TelaGraficoCliente = window.blockly.js.blockly.TelaGraficoCliente
		|| {};

/**
 * TelaGraficoCliente
 */
window.blockly.js.blockly.TelaGraficoCliente.atualizar = function() {
	this.cronapi.util.scheduleExecution(function() {
		this.cronapi.util
				.callServerBlocklyNoReturn('blockly.TelaGraficos:iniciar');
	}.bind(this), 0, 5, 'seconds');
}
