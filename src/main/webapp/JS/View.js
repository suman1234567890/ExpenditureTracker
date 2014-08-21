function view(action) {
  var token = getCookie('token');
  var url ="";
  var userToken = getCookie('userNameToken');
  if(token == '' || token ==null){
     window.location.href = "Home.jsp";
  }
  if(action == 'Group'){
    url = "../services/Group/ListGroup";
  }
  else{
    url = "../services/Diary/ListEntry";
  }
  var groups = new Array();
  var user = new Array();
  
  $.ajax({
                url: url,
                data: generateViewType(token),
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
                   //alert(result);
                   if(action !='Group'){
                   var rows = result.getElementsByTagName("entry");
                    $.each(rows, function(i,data) {
                      var buyer = $(data.getElementsByTagName("Token")).text();   
                      var dateTem = new Date($(data.getElementsByTagName("Date")).text()); 
                      var actualDate = dateTem.getDate()+"-"+dateTem.getMonth()+"-"+dateTem.getFullYear();                
                      groups[$(data.getElementsByTagName("GroupId")).text()]=$(data.getElementsByTagName("GroupName")).text();
                      user[$(data.getElementsByTagName("Token")).text()]=$(data.getElementsByTagName("Token")).text();
                      
                      if(userToken==buyer){
                      $("#tbody").append("<tr><td>"+(i+1)+"</td><td>"+actualDate +"</td><td>"+$(data.getElementsByTagName("GroupId")).text()+"</td><td>"+ $(data.getElementsByTagName("Token")).text()+ "</td><td><a href='options.jsp?Id="+ $(data.getElementsByTagName("DiaryId")).text()+ "&token="+token+"' data-rel='dialog' data-transition='fade' data-inline='true'>"+ $(data.getElementsByTagName("Item")).text()+ "</a></td><td>"+$(data.getElementsByTagName("Price")).text()+"</td></tr>");
                      }
                      else{
                        $("#tbody").append("<tr><td>"+(i+1)+"</td><td>"+ actualDate+"</td><td>"+$(data.getElementsByTagName("GroupId")).text()+"</td><td>"+ $(data.getElementsByTagName("Token")).text()+ "</td><td>"+ $(data.getElementsByTagName("Item")).text()+ "</td><td>"+$(data.getElementsByTagName("Price")).text()+"</td></tr>");

                      }
                      
                    });
                    for (var i in groups) {
	                     $("#group").append("<option value='"+i+"'>"+groups[i]+"</option>");

                    }
                    for (var i in user) {
	                     $("#user").append("<option value='"+i+"'>"+user[i]+"</option>");

                    }
                    $('#table-custom-2'  ).table( "refresh" );
                    
                  }
                  else{
                    var rows = result.getElementsByTagName("entry");
                    $.each(rows, function(i,data) {
                      var buyer = $(data.getElementsByTagName("Token")).text(); 
                      var status = "deactive";
                      var html="<button type='button' onclick=\"changeStatus('active',"+$(data.getElementsByTagName("GroupId")).text()+")\">activate</button><button type='button' onclick=\"deleteStatus("+$(data.getElementsByTagName("GroupId")).text()+")\">Delete</button>";  
                       if($(data.getElementsByTagName("GroupDesc")).text()=="active"){
                         status = "active";
                         html="<button type='button' onclick=\"changeStatus('deactive',"+$(data.getElementsByTagName("GroupId")).text()+")\">deactivate</button><button type='button' onclick=\"deleteStatus("+$(data.getElementsByTagName("GroupId")).text()+")\">Delete</button>"; 
                       }               
                      
                      $("#tbody").append("<tr><td>"+(i+1)+"</td><td>"+$(data.getElementsByTagName("GroupName")).text() +"</td><td>"+status+"<td>"+html+"</td></tr>");
                      
                      
                    });
                    $('#table-custom-2'  ).table( "refresh" );
                  }

                },
                error: function (request, error) {
                    // This callback function will trigger on unsuccessful action               
                    alert('Network error has occurred please try again!');
                }
            });


}

function generateViewType(tokenValue){
    var token = element('Token', tokenValue);
    var xml = element('Diary',token);
    return constructXML(xml);
}
function generateViewType(tokenValue,accept){
    var token = element('Token', tokenValue)+element('Accept',accept);
    var xml = element('Diary',token);
    return constructXML(xml);
}

function generateEditType(tokenValue,status,groupId){
var token = element('Token', tokenValue)+element('GroupId',groupId)+element('Status',status);
    var xml = element('Group',token);
    return constructXML(xml);

}
function generateDeleteType(tokenValue,groupId){
var token = element('Token', tokenValue)+element('GroupId',groupId);
    var xml = element('Group',token);
    return constructXML(xml);

}
function changeStatus(status,groupId){
var token = getCookie('token');
  
  var userToken = getCookie('userNameToken');
 $.ajax({
                url: "../services/Group/EditGroup",
                data: generateEditType(token,status,groupId),
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
                location.reload();
                },
                 error: function (request, error) {
                    // This callback function will trigger on unsuccessful action               
                    alert('Network error has occurred please try again!');
                }
            });
  

}
function deleteStatus(groupId){
var token = getCookie('token');
  
  var userToken = getCookie('userNameToken');
 $.ajax({
                url: "../services/Group/DeleteGroup",
                data: generateDeleteType(token,groupId),
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
                location.reload();
                },
                 error: function (request, error) {
                    // This callback function will trigger on unsuccessful action               
                    alert('Network error has occurred please try again!');
                }
            });
  

}

function populateGroupDropdown() {
var token = getCookie('token');
    if (token == '' || token == null) {
        window.location.href = "Home.jsp";
    }

  $.ajax({
        url: '../services/Group/ListGroup',
        data: generateNewType( token),
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
            alert('Network error has occurred please try again!');
        }
    });
}
function generateNewType(tokenValue) {
    var xmlInclude = element('Token', tokenValue);
    var xml = element('Diary', xmlInclude);
    return constructXML(xml);
}
function reArrange(){
    
    var table = document.getElementById('table-custom-2');    
    var rowCount = table.rows.length;    
    for (var i = 1; i < rowCount ; i++) {
    var val = $('#group').val();
    var val1 = $('#user').val();    
         var row = $(table.rows.item(i).cells.item(2)).text();
         var row1 = $(table.rows.item(i).cells.item(3)).text();
         if(val=="SelectAll" ){
           val = row;
         }
         if(val1=="SelectAll"){
           val1=row1;
         }
         if(row==val && row1==val1){
           $(table.rows.item(i))[0].style.display = '';
         }
         else{
           $(table.rows.item(i))[0].style.display = 'none';
         }
    }
}