(function () {
	"use strict";
	const input = document.querySelectorAll("form input.form-control");

	input.forEach((el) => {
		el.addEventListener("keyup", (ev) => {
			if (ev.target.getAttribute("class").includes("is-invalid")) {
				ev.target.setAttribute("class", "form-control");
			}
		});
	});
})()