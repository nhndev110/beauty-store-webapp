(function () {
	'use strict';
	const $ = document.querySelector.bind(document);
	const $$ = document.querySelectorAll.bind(document);

	const errMsg = localStorage.getItem('errMsg');
	if (errMsg) {
		$('.alert-err-login').classList.replace('d-none', 'd-flex');
		$('.alert-err-login .alert-content').innerText = errMsg;
		localStorage.removeItem('errMsg');
	}
})();