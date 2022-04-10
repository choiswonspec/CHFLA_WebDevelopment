
// 메인 로고 클릭 이벤트
$("#header-menu-img-area").on("click", function(){
    location.href = "index.html";
});

// 메인 사진 멘트 페이드인 이벤트
$(".main-img-content1").css("display","none");
$(".main-img-content2").css("display","none");
$(".main-img-content1").fadeIn(2000);
$(".main-img-content2").fadeIn(3500);


