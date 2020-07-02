$(document).ready(function() {
    /******************************
        BOTTOM SCROLL TOP BUTTON
     ******************************/

    // declare variable
    var scrollTop = $(".scrollTop");

    $(window).scroll(function() {
        // declare variable
        var topPos = $(this).scrollTop();

        // if user scrolls down - show scroll to top button
        if (topPos > 100) {
            $(scrollTop).css("opacity", "1");

        } else {
            $(scrollTop).css("opacity", "0");
        }

    }); // scroll END

    //Click event to scroll to top
    $(scrollTop).click(function() {
        $('html, body').animate({
            scrollTop: 0
        }, 800);
        return false;

    }); // click() scroll top EMD
    $(".owl-carousel").owlCarousel({
        loop: true,
        margin: 10,
        autoplay: true,
        nav: true,
        responsiveClass: true,
        responsive: {
            0: {
                items: 1,

            },
            600: {
                items: 1,

            },
            1000: {
                items: 1,


            }
        }
    });

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
        $('#example').barrating({
            theme: 'fontawesome-stars'
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






    /****************************/
    // // declare variable
    // var h1 = $("#h1").position();
    // var h2 = $("#h2").position();
    // var h3 = $("#h3").position();

    // $('.link1').click(function() {
    //   $('html, body').animate({
    //     scrollTop: h1.top
    //   }, 500);
    //   return false;

    // }); // left menu link2 click() scroll END

    // $('.link2').click(function() {
    //   $('html, body').animate({
    //     scrollTop: h2.top
    //   }, 500);
    //   return false;

    // }); // left menu link2 click() scroll END

    // $('.link3').click(function() {
    //   $('html, body').animate({
    //     scrollTop: h3.top
    //   }, 500);
    //   return false;

    // }); // left menu link3 click() scroll END

}); // ready() END