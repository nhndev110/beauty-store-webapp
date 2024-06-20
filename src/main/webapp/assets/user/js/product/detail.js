(function () {
  'use strict';
  const $ = document.querySelector.bind(document);
  const $$ = document.querySelectorAll.bind(document);

  const inputQuantity = $(".input-quantity");

  $(".add-to-cart").onclick = (ev) => {
    const productId = +ev.target.dataset.productId;
    console.log(productId);

    fetch(`http://localhost:8080/api/v1/cart`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        productId: productId,
        quantity: +inputQuantity.value
      }),
    })
      .then((resp) => resp.json())
      .then((respData) => {
        if (respData.data) {
          const productQtyElement = $("#cart-quantity-navbar");
          if (respData.data.id === +productQtyElement.innerText) {
            productQtyElement.innerText = +productQtyElement.innerText + respData.data.quantity;
          }
          console.log(respData.data);
        }
      });
  }
})();