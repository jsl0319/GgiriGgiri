window.addEventListener("load", (e) => {

	const main = document.querySelector(".main");
	const addBtn = main.querySelector(".addBtn");
	const tbody = main.querySelector(".tbody");

	// 추가 버튼 이벤트
	addBtn.onclick = () => {

		let tr = `<tr>
					  <td></td>
	                  <td>
	                    <input type="text" name="newNames">
	                  </td>
	                  <td></td>
				  </tr>`;

		tbody.insertAdjacentHTML('afterbegin', tr);

	};
	
	// 수정 이벤트 발생 시
	tbody.addEventListener("change", function(e) {
		if (e.target.name != "newNames" && e.target.name != "del") {
			e.target.name = "changedNames";
			e.target.previousElementSibling.name = "changedIds";
		}
	})

});