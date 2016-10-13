$(function() {
    resizeHW();
    $(window).resize(function() {
        resizeHW();
    });
});

function resizeHW() {
    var winH = $(window).height(); // 屏幕高度
    var winW = $(window).width(); // 屏幕宽度
    var headerH = 100; // header高度
    var footerH = 100; // footer高度
    var menuW = 250; // 菜单宽度
    var navTabsH=40; // tabs高度
    $("#body").css({
        "width" : winW,
        "height" : winH
    });

    $("#header").css({
        "background-color" : '#888877',
        "width" : winW,
        "height" : headerH
    });

    $("#main").css({
        "height" : winH - (headerH + footerH),
        "width" : winW
    });

    $(".left").css({
        "overflow-y" : "scroll",
        "float" : "left",
        "height" : winH - (headerH + footerH),
        "width" : menuW
    });

    $(".right").css({
        "float" : "left",
        "height" : winH - (headerH + footerH),
        "width" : winW - menuW
    });

    $(".tab-content").css({
        "float" : "left",
        "height" : winH - (headerH + footerH) - navTabsH,
        "width" : winW - menuW
    });

    $("#footer").css({
        "background-color" : '#888877',
        "height" : footerH,
        "width" : winW
    });
}
