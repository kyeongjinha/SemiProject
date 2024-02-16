const optionButton = document.getElementById('OptionButton');
const popup = document.getElementById('popup');

optionButton.addEventListener('click', function () {
    popup.style.display = 'block';
});

// 팝업 내에서 취소 버튼을 눌렀을 때 팝업을 닫는 기능
const cancelOptionButton = document.getElementById('cancelOption');
cancelOptionButton.addEventListener('click', function () {
    popup.style.display = 'none';
});

// 팝업 내에서 변경 버튼을 눌렀을 때의 기능을 추가하려면 여기에 작성하세요
const changeOptionButton = document.getElementById('changeOption');
changeOptionButton.addEventListener('click', function () {
    // 변경 버튼을 눌렀을 때의 동작을 여기에 추가하세요
});

// ================================== 수량 조절 ======================================
let quantity = 1;
// 수량을 표시하는 span 요소를 가져옴
const quantityElement = document.getElementById('quantity');
updateQuantity(); // 페이지 로드 시 초기화된 값을 보여주도록 호출

// 증가 버튼 클릭 시
document.getElementById('increase').addEventListener('click', function () {
    if (quantity < 30) {
        quantity++;
        updateQuantity();
    }
});
// 감소 버튼 클릭 시
document.getElementById('decrease').addEventListener('click', function () {
    if (quantity > 1) {
        quantity--;
        updateQuantity();
    }
});
// 수량 업데이트 함수
function updateQuantity() {
    quantityElement.textContent = quantity;
    calculateTotalAmount(); // 수량이 업데이트될 때마다 결제 금액 다시 계산
}

// ============================= 선택 삭제 및 바로 구매 =============================---

// 선택 삭제 버튼 클릭 시 동작
// document.getElementById('deleteSelected').addEventListener('click', function () {
// 	// 여기에 선택 삭제하는 코드를 작성해주세요
// 	// 예시: 선택된 상품들을 삭제하는 함수 호출
// 	deleteSelectedItems();
// });

// // 바로 구매 버튼 클릭 시 동작
// document.getElementById('buyNow').addEventListener('click', function () {
// 	// 여기에 바로 구매하는 코드를 작성해주세요
// 	// 예시: 선택된 상품을 바로 구매하는 함수 호출
// 	buySelectedItems();
// });

// // 선택된 상품 삭제 함수
// function deleteSelectedItems() {
// 	// 선택된 상품들을 삭제하는 코드를 작성해주세요
// 	// 예시: 체크된 항목을 찾아 삭제하는 로직
// 	// document.querySelectorAll('input[type="checkbox"]:checked') 등 활용
// 	console.log('선택 삭제를 수행합니다.');
// }

// // 선택된 상품 바로 구매 함수
// function buySelectedItems() {
// 	// 선택된 상품을 바로 구매하는 코드를 작성해주세요
// 	// 예시: 선택된 상품을 장바구니에 추가하고 결제 페이지로 이동하는 로직
// 	console.log('바로 구매를 수행합니다.');
// }

// const totalAmountElement = document.createElement('span');
// totalAmountElement.id = 'totalAmount';
// document.getElementsByClassName('action-buttons')[0].appendChild(totalAmountElement);

// // 결제 금액을 표시하는 HTML 요소 추가
// const paymentInfoElement = document.createElement('div');
// paymentInfoElement.id = 'paymentInfo';
// paymentInfoElement.innerHTML = '<p>결제 금액: <span id="paymentAmount">0</span>원</p>';

// // 바로 구매 버튼 오른쪽에 위치하도록 변경
// const buyNowButton = document.getElementById('buyNow');
// buyNowButton.parentNode.insertBefore(paymentInfoElement, buyNowButton.nextSibling);
// // 결제 금액 계산 및 표시하는 함수
// function calculateTotalAmount() {

// 	const paymentAmountElement = document.getElementById('paymentAmount');
// 	const sellingPrice = document.querySelector('.tg-r2yh').textContent; // 판매가 요소 선택
// 	const parsedPrice = parseInt(sellingPrice.replace(/\D/g, ''), 10); // 판매가에서 숫자만 추출하여 변환
// 	const totalAmount = quantity * parsedPrice; // 수량과 가격을 곱하여 총 결제 금액 계산
// 	paymentAmountElement.textContent = totalAmount.toLocaleString(); // 결제 금액 표시

// 	updateQuantity(); // 이 부분을 추가하여 수량을 업데이트하는 함수를 호출합니다.
// }

// calculateTotalAmount(); // 페이지 로드 시 초기화된 값을 보여주도록 호출