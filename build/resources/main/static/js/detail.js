var galleryThumbs = new Swiper('.gallery-thumbs', {
    spaceBetween: 5,
    freeMode: true,
    watchSlidesVisibility: true,
    watchSlidesProgress: true,
    breakpoints: {
        0: {
            slidesPerView: 3,
        },
        992: {
            slidesPerView: 4,
        },
    }
});
var galleryTop = new Swiper('.gallery-top', {
    spaceBetween: 10,
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },
    thumbs: {
        swiper: galleryThumbs
    },
});
// change carousel item height
// gallery-top
let productCarouselTopWidth = $('.gallery-top').outerWidth();
$('.gallery-top').css('height', productCarouselTopWidth);

// gallery-thumbs
let productCarouselThumbsItemWith = $('.gallery-thumbs .swiper-slide').outerWidth();
$('.gallery-thumbs').css('height', productCarouselThumbsItemWith);

// activation zoom plugin
var $easyzoom = $('.easyzoom').easyZoom();



$(function() {
    var rateNumber = $('#rate-product').data("rate");
    $('#rate-product').val(rateNumber);
    $('#rate-product').barrating({
        theme: 'fontawesome-stars',
        initialRating: rateNumber
    });
});
/* chi tiet noi dung */
$('.more').readmore({
		speed: 300,
		collapsedHeight: 200,
		moreLink: '<a class="a1" href="#">Xem Thêm </a>',
		lessLink: '<a class="a1" href="#">Rút gọn</a>',
		heightMargin: 16
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



