const popoverTriggerList = document.querySelectorAll('[data-bs-toggle="error-popover"]')
const popoverList = [...popoverTriggerList].map(popoverTriggerEl => {
	const popoverEl = new bootstrap.Popover(popoverTriggerEl, {
		trigger: 'focus',
		content: 'hello',
		customClass: 'error-popover',
		container: 'main',
		placement: 'right',
		offset: [0, 12]
	})

//	popoverEl.show()

	return popoverEl
})

console.log([...popoverTriggerList]);

[...popoverTriggerList].forEach((el) => {
	el.addEventListener('hide.bs.popover', () => {
		console.log("checked")
	})
})