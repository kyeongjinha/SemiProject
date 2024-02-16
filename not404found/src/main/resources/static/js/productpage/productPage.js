// 선택된 옵션에 따라 배경색 변경
document.addEventListener('DOMContentLoaded', function () {
    // 선택된 옵션을 표시할 HTML 요소들 가져오기
    var optionDisplay1 = document.getElementById('selectedOption1');
    var optionDisplay2 = document.getElementById('selectedOption2');

    // 옵션 선택 요소 가져오기
    var optionChoice1 = document.querySelector('.option_choice1_1');
    var optionChoice2 = document.querySelector('.option_choice2_1');

    // 옵션 선택 시 배경색 변경하는 함수
    function changeBackgroundColor() {
        var selectedOption1 = optionChoice1.options[optionChoice1.selectedIndex].text;
        var selectedOption2 = optionChoice2.options[optionChoice2.selectedIndex].text;



        // 배경색을 변경할 요소 가져오기
        var selectedOptionsDiv = document.querySelector('.selectedOptions');


        // 선택한 옵션에 따라 배경색 변경
        if (selectedOption1 !== '디자인' || selectedOption2 !== '색상') {
            selectedOptionsDiv.classList.add('selected');
        } else {
            selectedOptionsDiv.classList.remove('selected');
        }
        optionDisplay1.textContent = selectedOption1;
        optionDisplay2.textContent = selectedOption2;
    }

    // 배경색을 원하는 색상으로 설정
    //     selectedOptionsDiv.style.backgroundColor ='rgb(240 240 240)';

    //      // 요소의 높이를 설정
    //     selectedOptionsDiv.style.height = '55px';
    // } else {
    //     // 디폴트 배경색 또는 다른 처리를 하고 싶은 경우에 설정
    //     selectedOptionsDiv.style.backgroundColor = 'white';
    //     }
    // }

    // 옵션 선택 시 이벤트 핸들러 연결
    optionChoice1.addEventListener('change', changeBackgroundColor);
    optionChoice2.addEventListener('change', changeBackgroundColor);
});

// ================================== 상품 script ================================

var selectElement = document.querySelector('.option_choice1_1');
var selectElement2 = document.querySelector('.option_choice2_1');

var selectedOptionsElement = document.querySelector('.selectedOptions');

selectElement.addEventListener('change', updateSelectedOptions);
selectElement2.addEventListener('change', updateSelectedOptions);

function updateSelectedOptions() {
    var selectedOption1 = selectElement.options[selectElement.selectedIndex].text;
    var selectedOption2 = selectElement2.options[selectElement2.selectedIndex].text;

    selectedOptionsElement.innerHTML = `<p>${selectedOption1}</p><p>${selectedOption2}</p>`;
}

// 각 썸네일 이미지와 큰 이미지에 대한 참조 가져오기

const thumbnail1 = document.getElementById('thumbnail1');
const thumbnail2 = document.getElementById('thumbnail2');
const thumbnail3 = document.getElementById('thumbnail3');
const thumbnail4 = document.getElementById('thumbnail4');
const thumbnail5 = document.getElementById('thumbnail5');
const largeImage = document.getElementById('largeImage');

thumbnail1.addEventListener('mouseover', () => {
    largeImage.src = thumbnail1.src;
});
thumbnail1.addEventListener('mouseout', () => {
    largeImage.src = thumbnail1.src;
});
thumbnail2.addEventListener('mouseover', () => {
    largeImage.src = thumbnail2.src;
});
thumbnail2.addEventListener('mouseout', () => {
    largeImage.src = thumbnail1.src;
});
thumbnail3.addEventListener('mouseover', () => {
    largeImage.src = thumbnail3.src;
});
thumbnail3.addEventListener('mouseout', () => {
    largeImage.src = thumbnail1.src;
});
thumbnail4.addEventListener('mouseover', () => {
    largeImage.src = thumbnail4.src;
});
thumbnail4.addEventListener('mouseout', () => {
    largeImage.src = thumbnail1.src;
});
thumbnail5.addEventListener('mouseover', () => {
    largeImage.src = thumbnail5.src;
});
thumbnail5.addEventListener('mouseout', () => {
    largeImage.src = thumbnail1.src;
});

const productOptions = document.getElementById('option_choice1_1');
const quantityElement = document.getElementById('quantity');
const totalPriceElement = document.getElementById('totalPrice');

// 수량 증가 함수
function increaseQuantity() {
    let quantity = parseInt(quantityElement.textContent);
    quantity++;
    quantityElement.textContent = quantity.toString();
    //updateTotalPrice();
}

// 수량 감소 함수
function decreaseQuantity() {
    let quantity = parseInt(quantityElement.textContent);
    if (quantity > 1) {
        quantity--;
        quantityElement.textContent = quantity.toString();
        //updateTotalPrice();
    }
}

// 수정된 updateTotalPrice() 함수
// function updateTotalPrice() {
//     const selectedOption = productOptions.value; // productOptions로 올바른 ID를 사용합니다.
//     const quantity = parseInt(quantityElement.textContent);
//     let price = 0;
//
//     switch (selectedOption) {
//         case '0':
//             price = 0;
//             break;
//         case '1':
//             price = 2000;
//             break;
//         case '2':
//             price = 3000;
//             break;
//         case '3':
//             price = 6000;
//             break;
//         // 다른 옵션에 대한 가격 추가
//     }
//
//     const totalPrice = price * quantity;
//     totalPriceElement.textContent = totalPrice;
// }
// 초기 총 구매 가격 계산
//updateTotalPrice();