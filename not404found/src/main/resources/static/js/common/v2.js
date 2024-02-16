function sample6_execDaumPostcode() {
    //이걸 부를 html 파일에
    //<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    //<script src="../../static/js/common/v2.js"></script>
    //이거 두 줄 넣어주세요!!!
    new daum.Postcode({
        oncomplete: function(data) {
            var addrDetail = prompt("상세 주소를 입력하세요");
            var userInput = prompt("배송지 이름을 입력하세요","집");
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                //document.getElementById("sample6_extraAddress").value = extraAddr;

            } else {
                //document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            // document.getElementById('sample6_postcode').value = data.zonecode;
            // document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            //document.getElementById("sample6_detailAddress").focus();
            insertAddress(extraAddr,data.zonecode,addr,userInput,addrDetail)
        }
    }).open();
}

function insertAddress(extraAddr, zonecode, addr, name,addrDetail){
    address = addr + extraAddr;
    if(addrDetail){
        const data = {
            //extraAddr : extraAddr,  //사우동, 김포사우아이파크 : 주소
            //addr : addr,            //경기도 김포시 사우로 51 : 도로명 주소
            address : address,      // 경기도 김포시 사우로 51 사우동, 김포사우아이파크
            zonecode : zonecode,    //10111 : 우편번호
            name : name,             //집 : 주소 이름
            addrDetail : addrDetail //105동 402호
        }
        console.log(data)
        fetch("/myPage/insertAddress",{
            method: "POST",
            headers:{
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response =>{
                return response.text();
            })
            .then(data => {
                alert(data)
            })
            .catch((error) =>{
                alert(error);
            });
    } else {
        alert("안됏");
    }

}