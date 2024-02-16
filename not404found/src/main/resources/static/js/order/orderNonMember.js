function handleDeliveryRequestChange(value) {
    var customDeliveryRequest = document.getElementById("customDeliveryRequest");
    if (value === "6") {
        customDeliveryRequest.style.display = "block"; /* 선택지가 '직접 입력'일 때 보이도록 설정 */
    } else {
        customDeliveryRequest.style.display = "none"; /* 다른 선택지를 선택하면 숨김 */
    }
}