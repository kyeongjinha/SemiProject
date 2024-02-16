var dragElement = document.querySelector('.side_button')
var active = false;
var currentX;
var currentY;
var initialX;
var initialY;
var xOffset = 0;
var yOffset = 0;
dragElement.addEventListener("mousedown", dragStart, false);
window.addEventListener("mouseup", dragEnd, false);
window.addEventListener("mousemove", drag, false);
function dragStart(e) {
    initialX = e.clientX - xOffset;
    initialY = e.clientY - yOffset;

    if (e.target === dragElement) {
        active = true;
    }
}
function drag(e) {
    if (active) {
        e.preventDefault();

        currentX = e.clientX - initialX;
        currentY = e.clientY - initialY;
        xOffset = currentX;
        yOffset = currentY;
        setTranslate(currentX, currentY, dragElement);
    }
}
function dragEnd(e) {
    initialX = currentX;
    initialY = currentY;
    active = false;
}
function setTranslate(xPos, yPos, el) {
    el.style.transform = "translate3d(" + xPos + "px, " + yPos + "px, 0)";
}
var side_button = document.querySelector('.side_button_container');
var side_bar_menus = document.querySelector('.side_bar_menus');
side_button.addEventListener('click', function() {
    if (side_bar_menus.style.display === 'none' || side_bar_menus.style.display === '') {
        side_bar_menus.style.display = 'block'; // 먼저 display를 block으로 설정
        side_bar_menus.classList.remove('up_animation');
        side_bar_menus.classList.add('fallDown');
    } else {
        side_bar_menus.classList.remove('fallDown');
        side_bar_menus.classList.add('up_animation');
        side_bar_menus.addEventListener('animationend', function() {
            if (side_bar_menus.classList.contains('up_animation')) {
                side_bar_menus.style.display = 'none'; // 애니메이션이 끝나면 display를 none으로 설정
            }
        }, { once: true }); // 이벤트 핸들러는 한 번만 실행되도록 설정
    }
});