import handleToast from './modules/toast.js';

(function () {
	'use strict';
	const $ = document.querySelector.bind(document);
	const $$ = document.querySelectorAll.bind(document);

	// Handle delete cart item
	[...$$('.btn-remove-cart-item')].forEach((el) => {
		el.onclick = (ev) => {
			const productId = el.dataset.productId;

			fetch(`/nhndev-beauty/cart/delete?product_id=${productId}`, {
				method: 'GET',
				cache: 'no-cache',
				headers: {
					'Content-Type': 'application/json',
				},
			})
				.then((resp) => resp.json())
				.then((respData) => {
					if (respData.status === 'success') {
						// remove element
						el.closest('.row').classList.add('animate__animated', 'animate__fadeOut', 'animate__faster');

						el.closest('.row').addEventListener('animationend', () => {
							el.closest('.row').remove();
						});

						if (respData.isEmptyCart) {
							$('#exist-cart').remove();
							$('#empty-cart').classList.replace('d-none', 'd-flex');
						} else {
							const subtotal = respData.subtotal;
							if (subtotal) {
								$('#subtotal-amount').innerText = subtotal;
							}

							const total = respData.total;
							if (total) {
								$('#total-amount').innerText = total;
							}

							const vat = respData.vat;
							if (vat) {
								$('#vat-amount').innerText = vat;
							}
						}

						$('#cart-quantity-navbar').innerText =
							parseInt($('#cart-quantity-navbar').innerText) - 1;
					}
				})
				.catch((err) => console.error(err));
		}
	});

	// Handle update quantity cart item
	[...$$('.btn-update-cart-item')].forEach((el) => {
		el.onclick = (ev) => {
			const productId = el.dataset.productId;
			const type = el.dataset.updateType;
			const inputQtyCartItemNode = el.parentElement.querySelector('input[type=number]');

			if (type === 'desc' && inputQtyCartItemNode.value - 1 <= 0) {
				handleToast({
					title: 'Thông Báo',
					msg: 'Số lượng không được nhỏ hơn 1 !',
					status: 'error',
				});
				return;
			}

			fetch(`/nhndev-beauty/cart/update?product_id=${productId}&type=${type}`, {
				method: 'GET',
				cache: 'no-cache',
				headers: {
					'Content-Type': 'application/json',
				},
			})
				.then(resp => resp.json())
				.then(respData => {
					(type === 'asc') && inputQtyCartItemNode.stepUp();
					(type === 'desc') && inputQtyCartItemNode.stepDown();

					const subtotal = respData.subtotal;
					if (subtotal) {
						$('#subtotal-amount').innerText = subtotal;
					}

					const total = respData.total;
					if (total) {
						$('#total-amount').innerText = total;
					}

					const vat = respData.vat;
					if (vat) {
						$('#vat-amount').innerText = vat;
					}
				})
				.catch((err) => console.error(err));
		}
	});
})();