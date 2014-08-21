

$(document).ready(function(){
  var token = getCookie('token');
  if(token == '' || token ==null){
     window.location.href = "Home.jsp";
  }
});

