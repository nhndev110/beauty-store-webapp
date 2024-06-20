'use strict'
const $ = document.querySelector.bind(document)
const $$ = document.querySelectorAll.bind(document)

const handleToast = ({ title = "", msg = "", status = "" }) => {
	let toastContainer = $('.toast-container')
	const mainEl = $('main')

	if (!toastContainer) {
		toastContainer = document.createElement('div')
		toastContainer.className = 'toast-container position-fixed end-0 p-3'
		toastContainer.style.top = '58px'
		mainEl.appendChild(toastContainer)
	}

	const toast = document.createElement('div')
	toast.className = 'toast bg-white animate__animated animate__fadeInRightBig animate__fast'
	toast.setAttribute('role', 'alert')
	toast.setAttribute('aria-live', 'assertive')
	toast.setAttribute('aria-atomic', 'true')
	toast.innerHTML = `
		<div class="toast-header text-primary">
			<i class="bi bi-check-circle-fill me-2" style="font-size: 20px;"></i>
			<span class="fw-bold me-auto">${title}</span>
			<button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
		</div>
		<div class="toast-body">
			${msg}
		</div>
	`
	toastContainer.appendChild(toast)

	const toastBs = bootstrap.Toast.getOrCreateInstance(toast)
	toastBs.show()

	toast.addEventListener('hidden.bs.toast', () => toast.remove())
}

export default handleToast