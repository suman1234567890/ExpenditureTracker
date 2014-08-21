function populate(Id, token) {

    $.ajax({
        url: '../services/Diary/ListSingleEntry',
        data: generateAddType(Id, token),
        type: 'post',
        async: 'true',
        contentType: 'application/xml',
        beforeSend: function () {
            // This callback function will trigger before data is sent
            $.mobile.showPageLoadingMsg(true); // This will show ajax spinner
        },
        complete: function () {
            // This callback function will trigger on data sent/received complete
            $.mobile.hidePageLoadingMsg(); // This will hide ajax spinner
        },
        success: function (result) {
            
            $('#item').val($(result.getElementsByTagName("Item")).text());
            $('#price').val($(result.getElementsByTagName("Price")).text());

        },
        error: function (request, error) {
            // This callback function will trigger on unsuccessful action               
            alert('Network error has occurred please try again!');
            //window.location.href="Profile.jsp";
        }
    });

}
$(document).ready(function () {
    var token = getCookie('token');
    if (token == '' || token == null) {
        window.location.href = "Home.jsp";
    }
    $.ajax({
        url: '../services/Group/ListGroup',
        data: generateViewGroupType(token,"True"),
        type: 'post',
        async: 'true',
        contentType: 'application/xml',
        beforeSend: function () {
            // This callback function will trigger before data is sent
            $.mobile.showPageLoadingMsg(true); // This will show ajax spinner
        },
        complete: function () {
            // This callback function will trigger on data sent/received complete
            $.mobile.hidePageLoadingMsg(); // This will hide ajax spinner
        },
        success: function (result) {
            
           
                   var rows = result.getElementsByTagName("entry");
                    $.each(rows, function(i,data) {
                     
                      $("#group").append("<option value='"+$(data.getElementsByTagName("GroupId")).text()+"'>"+$(data.getElementsByTagName("GroupName")).text()+"</option>");
                      
                      
                    });
                    

        },
        error: function (request, error) {
            // This callback function will trigger on unsuccessful action               
            alert('Network error has occurred please try again!');
            //window.location.href="Profile.jsp";
        }
    });

    $("#groupLogin").validate({
        rules: {
            Name: {
                required: true
            },
            Desc: {
                required: true
            }
        },
        messages: {
            Name: {
                required: "Please enter Item description."
            },
            Desc: {
                required: "Please enter Price."
            }
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent().prev());
        },
        submitHandler: function (form) {
        var url = "";
        var  data = "";
           
            if($('#action').val()=='Group'){
               url="../services/Group/AddGroup";
               data = generateGroupAddType(token);
               
            }
            $.ajax({
                url: url,
                data: data,
                type: 'post',
                async: 'true',
                contentType: 'application/xml',
                beforeSend: function () {
                    // This callback function will trigger before data is sent
                    $.mobile.showPageLoadingMsg(true); // This will show ajax spinner
                },
                complete: function () {
                    // This callback function will trigger on data sent/received complete
                    $.mobile.hidePageLoadingMsg(); // This will hide ajax spinner
                },
                success: function (result) {
                    var id = $(result).find("DiaryId");
                    if (id == null || id == "") {
                        alert('Unable to add The Entry');
                    } else {
                        alert("Successfully Added the Entry");
                    }
                    if ($('#action').val()=='Add') {

             
            }
            if($('#action').val()=='Update'){
              window.location.jsp="View.jsp";
                             
            }
                    
                },
                error: function (request, error) {
                    // This callback function will trigger on unsuccessful action               
                    alert('Network error has occurred please try again!');
                    //window.location.href="Profile.jsp";
                }
            });
        }
    });

    $("#frmLogin").validate({
        rules: {
            item: {
                required: true
            },
            price: {
                required: true
            }
        },
        messages: {
            item: {
                required: "Please enter Item description."
            },
            price: {
                required: "Please enter Price."
            }
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent().prev());
        },
        submitHandler: function (form) {
        var url = "";
        var  data = "";
            if ($('#action').val()=='Add') {

              url="../services/Diary/AddEntry";
              data = generateNewType(token);
            }
            if($('#action').val()=='Update'){
               url="../services/Diary/EditEntry";
               data = generateEditType(token,$('#dairyId').val(),$('#item').val(),$('#price').val());
               
            }
            if($('#action').val()=='Group'){
               url="../services/Group/AddGroup";
               data = generateGroupAddType(token);
               
            }
            $.ajax({
                url: url,
                data: data,
                type: 'post',
                async: 'true',
                contentType: 'application/xml',
                beforeSend: function () {
                    // This callback function will trigger before data is sent
                    $.mobile.showPageLoadingMsg(true); // This will show ajax spinner
                },
                complete: function () {
                    // This callback function will trigger on data sent/received complete
                    $.mobile.hidePageLoadingMsg(); // This will hide ajax spinner
                },
                success: function (result) {
                    var id = $(result).find("DiaryId");
                    if (id == null || id == "") {
                        alert('Unable to add The Entry');
                    } else {
                        alert("Successfully Added the Entry");
                    }
                    if ($('#action').val()=='Add') {

             
            }
            if($('#action').val()=='Update'){
              window.location.jsp="View.jsp";
                             
            }
                    
                },
                error: function (request, error) {
                    // This callback function will trigger on unsuccessful action               
                    alert('Network error has occurred please try again!');
                    //window.location.href="Profile.jsp";
                }
            });
        }
    });

});
function generateAddType(dairyId, tokenValue) {
    var xmlInclude = element('token', tokenValue) + element('dairyId', dairyId);
    var xml = element('Diary', xmlInclude);
    return constructXML(xml);
}
function generateViewGroupType(tokenValue,access) {
    var xmlInclude = element('Token', tokenValue) + element('Filter', access);
    var xml = element('Diary', xmlInclude);
    return constructXML(xml);
}
function generateNewType(tokenValue) {
    var xmlInclude = element('Item', $('#item').val())+element('Price', $('#price').val())+element('Token', tokenValue)+element('Date', new Date())+element('GroupId', $('#group').val()) ;
    var xml = element('Diary', xmlInclude);
    return constructXML(xml);
}
function generateEditType(tokenValue,dairyId,item,price) {
    var xmlInclude = element('token', tokenValue) + element('Id', dairyId)+ element('Item', item)+ element('Price', price);
    var xml = element('Diary', xmlInclude);
    return constructXML(xml);
}
function generateGroupAddType(token){
  var xmlInclude = element('Name', $('#Name').val())+element('Desc', $('#Desc').val())+element('Token', token);
    var xml = element('Group', xmlInclude);
    return constructXML(xml);

}