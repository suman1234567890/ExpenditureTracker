
$(window).ready(function () {
 var token = getCookie('token');
 if(token != ""){
  window.location.href="Profile.jsp" ;
  return;
  }

    $("#frmLogin").validate({
        rules: {
            password: {
                required: true
            },
            email: {
                required: true
            }
        },
        messages: {
            password: {
                required: "Please enter your Password."
            },
            email: {
                required: "Please enter your Email."
            }
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent().prev());
        },
        submitHandler: function (form) {
            $.ajax({
                url: '../services/registration/SignIn',
                data: generateLoginType(),
                type: 'post',
                async: 'true',
                contentType: 'application/xml',
                beforeSend: function () {
                    // This callback function will trigger before data is sent
                    $.mobile.showPageLoadingMsg(true); // This will show ajax spinner
                   //// xhr.overrideMimeType( "application/xml" );
                },
                complete: function () {
                    // This callback function will trigger on data sent/received complete
                    $.mobile.hidePageLoadingMsg(); // This will hide ajax spinner
                },
                success: function (result) {
                      var userName = $(result).find("UserName");
                      var token = $(userName).text();
                      if(token == null || token == ""){
                        alert('Login Unsuccessful');
                      }
                      else{
                        setCookie('token',token,1);
                        setCookie('userNameToken', $('#email').val(),1);
                        window.location.href = "Profile.jsp";
                      }
                },
                error: function (request, error) {
                    // This callback function will trigger on unsuccessful action               
                    alert('Network error has occurred please try again!');
                }
            });
        }
    });
});

function generateLoginType(){
    userName = element('UserName', $('#email').val());
    passWord = element('PassWord', $('#password').val());
    var xml = element("Login",userName + passWord);
    return constructXML(xml);
}

