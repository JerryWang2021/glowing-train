$(document).ready(function () {
    //Page load
    $('#mainInfoDiv').show();
    $('#akronInfoDiv').hide();
    $('#minneapolisInfoDiv').hide();
    $('#louisvilleInfoDiv').hide()
   
});

//Navigation Button Behavior
$('#akronButton').click(function(){
    $('#akronInfoDiv').show();
})

$('#minneapolisButton').click(function(){
    $('#minneapolisInfoDiv').show();
})

$('#louisvilleButton').click(function(){
    $('#louisvilleInfoDiv').show();
})

//show/Hide Weather Behavior
$('#akronWeatherButton').click(function(){
    $('#akronWeather').toggle();
})

$('#minneapolisWeatherButton').click(function(){
    $('#minneapolisWeather').toggle();
})

$('#louisvilleWeatherButton').click(function(){
    $('#louisvilleWeather').toggle();
})

//Table Row Behavior
$("tr").hover(
    // in callback
    function () {
        $(this).find("td").css("background-color", "WhiteSmoke");
    },
    // out callback
    function () {
        $(this).find("td").css("background-color", "");
    }
);



        

