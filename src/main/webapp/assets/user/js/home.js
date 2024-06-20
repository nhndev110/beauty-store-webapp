import handleToast from './modules/toast.js';

(function () {
	'use strict';
	const $ = document.querySelector.bind(document);
	const $$ = document.querySelectorAll.bind(document);

	const toastRegisterSuccess = document.getElementById('toastRegisterSuccess');
	if (toastRegisterSuccess) {
		const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastRegisterSuccess);
		toastBootstrap.show();
	}
	
//	fetch(`http://localhost:8080/nhndev-beauty/api/v1/categories`, {
//		method: 'PUT',
//		headers: {
//			'Content-Type': 'application/json',
//			'Authorization': `hello`
//		},
//		body: JSON.stringify({
//			name: "Trang Điểm Mặt",
//			thumbnail: "trang-diem-mat.jpg"
//		})
//	})
//		.then((resp) => resp.json())
//		.then(data => console.log(data));

//	 Handle add to cart
//	[...$$('.add-to-cart')].forEach((el) => {
//		el.onclick = (ev) => {
//			const productID = ev.target.dataset.productId; // get id
//			const cartIconEl = ev.target.previousElementSibling;
//			const cartIconElContent = cartIconEl.innerHTML; // get content of btn cart icon
//
//			cartIconEl.innerHTML = `
//				<div class="spinner-border spinner-border-sm" role="status">
//					<span class="visually-hidden">Loading...</span>
//				</div>
//			`;
//
//			fetch(`/nhndev-beauty/cart/add?product_id=${productID}`, {
//				method: 'GET',
//				cache: 'no-cache',
//				headers: {
//					'Content-Type': 'application/json',
//				},
//			})
//				.then((resp) => resp.json())
//				.then((respData) => {
//					if (respData.redirect) {
//						localStorage.setItem('errMsg', 'Please login!');
//						location.href = respData.redirect;
//						return;
//					}
//
//					if (respData.status === 'success') {
//						if (!respData.isProductInCart) {
//							const cartQuantityEl = $('#cart-quantity-navbar');
//							const cartQuantity = parseInt(cartQuantityEl.innerHTML);
//
//							cartQuantityEl.innerHTML = cartQuantity + 1;
//						}
//
//						cartIconEl.innerHTML = cartIconElContent;
//					}
//				})
//				.catch((err) => console.error(err));
//		}
//	})
})();