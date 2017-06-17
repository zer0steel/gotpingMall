(function(cssClass) {
	$('.form-control.input').each(function() {
		let label = $(this).data('label');
		let $div = createTag(label);
		$(this).replaceWith($div);
		$div.find('div.inputArea').append($(this));
	});
	
	function createTag(label) {
		return $('<div />').addClass('form-group').append(
				$('<label />').addClass('control-label ' + cssClass.label).text(
						label), $('<div />').addClass('inputArea ' + cssClass.div).append())
	}
}(typeof CSS_CLASS !== 'undefined' ? CSS_CLASS : {}));

function showErr(err) {
	let errWindow = window.open('', '', 'width=600, height=600');
	errWindow.document.querySelector('body').innerHTML = err.responseText;
}

function filter(list, predicate) {
	let newList = [];
	for(let i = 0, len = list.length; i < len; i++) {
		if(predicate(list[i]))
			newList.push(list[i]);
	}
	return newList;
}

function map(list, iteratee) {
	let newList = [];
	for(let i = 0, len = list.length; i < len; i++) {
		newList.push(iteratee(list[i]));
	}
	return newList;
}