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
    nav:true,
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

  /*************************************
  //   LEFT MENU SMOOTH SCROLL ANIMATION
  //  *************************************/

    $(".rateYo").each( function() {
        var rating = $(this).attr("data-rate");

        $(this).rateYo(
            {
                rating: rating,
                fullStar: true,
                readOnly: true
            }
        );
    });

});
