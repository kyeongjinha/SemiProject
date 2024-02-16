if(window.location.href==="http://localhost:8404/admin/dashboard"){
    document.querySelector('.side_bar1').style.backgroundColor = '#4b4c5b';
    document.querySelector('.side_bar1').style.fontWeight = 'bold';
} else if(window.location.href==="http://localhost:8404/admin/order"){
    document.querySelector('.side_bar2').style.backgroundColor = '#4b4c5b';
    document.querySelector('.side_bar2').style.fontWeight = 'bold';
} else if(window.location.href==="http://localhost:8404/admin/product"){
    document.querySelector('.side_bar3').style.backgroundColor = '#4b4c5b';
    document.querySelector('.side_bar3').style.fontWeight = 'bold';
} else if(window.location.href==="http://localhost:8404/admin/notice"){
    document.querySelector('.side_bar4').style.backgroundColor = '#4b4c5b';
    document.querySelector('.side_bar4').style.fontWeight = 'bold';
} else if(window.location.href==="http://localhost:8404/admin/statistics"){
    document.querySelector('.side_bar5').style.backgroundColor = '#4b4c5b';
    document.querySelector('.side_bar5').style.fontWeight = 'bold';
} else if(window.location.href==="http://localhost:8404/admin/member"){
    document.querySelector('.side_bar6').style.backgroundColor = '#4b4c5b';
    document.querySelector('.side_bar6').style.fontWeight = 'bold';
} else if(window.location.href==="http://localhost:8404/admin/event"){
    document.querySelector('.side_bar7').style.backgroundColor = '#4b4c5b';
    document.querySelector('.side_bar7').style.fontWeight = 'bold';
} else if(window.location.href==="http://localhost:8404/admin/coupon"){
    document.querySelector('.side_bar8').style.backgroundColor = '#4b4c5b';
    document.querySelector('.side_bar8').style.fontWeight = 'bold';
}

function go_dashboard(){
    window.location.href = "/admin/dashboard";
}
function go_product(){
    window.location.href = "/admin/product";
}
function go_order(){
    window.location.href = "/admin/order"
}
function go_statistics(){
    window.location.href = "/admin/statistics"
}
function go_member(){
    window.location.href = "/admin/member"
}
function go_notice(){
    window.location.href = "/admin/notice"
}
function go_event(){
    window.location.href = "/admin/event"
}
function go_coupon(){
    window.location.href = "/admin/coupon"
}
function goHome(){
    window.location.href = "/"
}
