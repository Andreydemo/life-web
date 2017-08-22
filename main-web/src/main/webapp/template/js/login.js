$(document).ready(function () {
    $(".form-wrapper .button.next").click(function () {
        var button = $(this);
        var currentSection = button.parents(".section");
        var currentSectionIndex = currentSection.index();
        var headerSection = $('.steps li').eq(currentSectionIndex);
        currentSection.removeClass("is-active").next().addClass("is-active");
        headerSection.removeClass("is-active").next().addClass("is-active");

        $(".form-wrapper").submit(function (e) {
            e.preventDefault();
        });

        if (currentSectionIndex === 3) {
            $(document).find(".form-wrapper .section").first().addClass("is-active");
            $(document).find(".steps li").first().addClass("is-active");
        }
    });
    $(".form-wrapper .button.back").click(function () {

        var button = $(this);
        var currentSection = button.parents(".section");
        var currentSectionIndex = currentSection.index();
        if (currentSectionIndex !== 0) {
            var headerSection = $('.steps li').eq(currentSectionIndex);
            currentSection.removeClass("is-active").prev().addClass("is-active");
            headerSection.removeClass("is-active").prev().addClass("is-active");

            $(".form-wrapper").submit(function (e) {
                e.preventDefault();
            });

            if (currentSectionIndex === 3) {
                $(document).find(".form-wrapper .section").first().addClass("is-active");
                $(document).find(".steps li").first().addClass("is-active");
            }
        }
    });
});
