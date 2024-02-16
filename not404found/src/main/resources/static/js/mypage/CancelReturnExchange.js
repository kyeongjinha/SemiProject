logIn.addEventListener('click',function(){
    alert('로그인!');
});

coupon.addEventListener('click',function(){
    alert('쿠폰장수!')
});
mileage.addEventListener('click',function(){
    alert('마일리지!')
});
deposit.addEventListener('click',function(){
    alert('예치금!')
});

document.getElementById("달력그림").addEventListener("click", function() {
    alert("달력이 활성화되었습니다!");
});

document.getElementById("달력그림2").addEventListener("click", function() {
    alert("달력이 활성화되었습니다!");
});
document.getElementById("달력조회버튼").addEventListener("click", function() {
    alert("조회가 활성화되었습니다!");
});


document.addEventListener("DOMContentLoaded", function() {
    var squares = document.querySelectorAll('.square');
    var selectedSquare = null;

    function changeColor(square) {
        if (selectedSquare) {
            selectedSquare.classList.remove("clicked");
        }

        square.classList.add("clicked");
        selectedSquare = square;
    }

    squares.forEach(function(square) {
        square.addEventListener("click", function() {
            changeColor(square);
        });
    });
});