(function () {
    $('a[href^="#"]').on('click', function () {
        $(this.hash).velocity('scroll', {offset: -$('.header').outerHeight()});
    });
})();
