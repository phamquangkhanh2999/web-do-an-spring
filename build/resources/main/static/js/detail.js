// Main/Product image slider for product page


$('#detail .main-img-slider').slick({
    slidesToShow: 1,
    slidesToScroll: 1,
    infinite: true,
    arrows: true,
    fade: true,
    autoplay: true,
    autoplaySpeed: 4000,
    speed: 300,
    lazyLoad: 'ondemand',
    asNavFor: '.thumb-nav',
    prevArrow: '<div class="slick-prev"><i class="i-prev"></i><span class="sr-only sr-only-focusable">Previous</span></div>',
    nextArrow: '<div class="slick-next"><i class="i-next"></i><span class="sr-only sr-only-focusable">Next</span></div>'
});
// Thumbnail/alternates slider for product page
$('.thumb-nav').slick({
    slidesToShow: 4,
    slidesToScroll: 1,
    infinite: true,
    centerPadding: '0px',
    asNavFor: '.main-img-slider',
    dots: false,
    centerMode: false,
    draggable: true,
    speed: 200,
    focusOnSelect: true,
    prevArrow: '<div class="slick-prev"><i class="i-prev"></i><span class="sr-only sr-only-focusable">Previous</span></div>',
    nextArrow: '<div class="slick-next"><i class="i-next"></i><span class="sr-only sr-only-focusable">Next</span></div>'
});


//keeps thumbnails active when changing main image, via mouse/touch drag/swipe
$('.main-img-slider').on('afterChange', function(event, slick, currentSlide, nextSlide) {
    //remove all active class
    $('.thumb-nav .slick-slide').removeClass('slick-current');
    //set active class for current slide
    $('.thumb-nav .slick-slide:not(.slick-cloned)').eq(currentSlide).addClass('slick-current');
});

$(function() {
    var rateNumber = $('#rate-product').data("rate");
    $('#rate-product').val(rateNumber);
    $('#rate-product').barrating({
        theme: 'fontawesome-stars',
        initialRating: rateNumber
    });
});
/* chi tiet noi dung */
$("#loadMore").on("click", function() {
    $(".overlay").toggleClass("display-none");

    $("#loadMore").toggleClass("read-less");
    if ($("#loadMore").hasClass("read-less")) {
        $("#loadMore").text("Read less");
    } else {
        $("#loadMore").text("Read more");
    }

    $(".moreParagraphs").slideToggle(500);
});

$('.btn-rate').on("click",function () {
    dataRate = {};
    var star =  $("#rate-product").val();
    var userName = $("#dropdownMenuButton").text().trim();
    var productId =  $(this).data("product-id");
    dataRate.star = star;
    dataRate.productId = productId;
    dataRate.userName = userName;
    var linkPost = "/api/rate/update";
    axios.post(linkPost, dataRate).then(function(res){
        if(res.data.success) {
            location.reload();
        } else {
            swal(
                'Fail',
                res.data.message,
                'error'
            ).then(function() {
                location.reload();
            });
        }
    }, function(err){

        swal(
            'Error',
            'Fail',
            'error'
        );
    });

});
$(".item-mau").each(function () {
    var color1 =  $(this).text();
    var color2 =  $(this).data("color");
    if(color1 === color2){
        $(this).addClass("active");
    }
})



