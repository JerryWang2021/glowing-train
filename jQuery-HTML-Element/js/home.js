$(document).ready(function () {
    $("h1").css('text-align', 'center');
    $("h2").css('text-align', 'center');

    $('h1').removeClass("myBannerHeading").addClass("page-header");
    $('#yellowHeading').text("Yellow Team");

    $('.orange').css('background-color', 'orange');
    $('.blue').css('background-color', 'blue');
    $('.red').css('background-color', 'red');
    $('.yellow').css('background-color', 'yellow');

    
    $('#yellowTeamList').append('<li>Joseph Banks</li>');
    $('#yellowTeamList').append('<li>Simon Jones</li>');

    $('#oops').hide();
    $('#footer').empty();

    $('#footer').html("<p style='font: Courier; font-size: 24Px'>Zhi Wang jerrywang65@hotmail.com</p>");
    

    
});