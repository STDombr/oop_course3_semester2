$(document).ready(function(){
    $(document).on('click','.plus',function(){
        var count = parseInt($('.count').val());
        $('.price').val(parseFloat($('.price').val()) + parseFloat($('.price').val()) / count);
        $('.price').change();
        $('.count').val(count + 1);
    });
    $(document).on('click','.minus',function(){
        if (parseInt($('.count').val()) > 1) {
            $('.price').val(parseFloat($('.price').val()) - parseFloat($('.price').val()) / parseInt($('.count').val()));
            $('.count').val(parseInt($('.count').val()) - 1 );
        }
    });
});