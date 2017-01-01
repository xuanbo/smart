(function ($) {
    $(function () {
       $('#login-form-link').on('click', function (e) {
           e.preventDefault();
           $("#login-form").delay(100).fadeIn(100);
           $("#register-form").fadeOut(100);
           $('#register-form-link').removeClass('active');
           $(this).addClass('active');
       });
       
       $('#register-form-link').on('click', function (e) {
           e.preventDefault();
           $("#register-form").delay(100).fadeIn(100);
           $("#login-form").fadeOut(100);
           $('#login-form-link').removeClass('active');
           $(this).addClass('active');
       });

       $('#login-submit').on('click', function (e) {
           e.preventDefault();
           var username = $('#username').val();
           var password = $('#password').val();
           var remember = $('#remember')[0].checked;
           var user = {
               username: username,
               password: password,
               rememberMe: remember
           };
           $.ajax({
               url: '/login',
               method: 'POST',
               contentType: 'application/json',
               dataType: 'json',
               data: JSON.stringify(user)
           }).then(function (response) {
               console.log(response);
           }, function (response) {
               console.log(response);
           });
       });
    });
})(jQuery);
