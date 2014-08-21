 <!DOCTYPE html>
<html>

<head>
    <title>Home</title>
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0" />
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
    <link rel="stylesheet" href="../css/styles.css" />
    <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
</head>
<body> 
  <div data-role="page">

  <div data-role="header">
    <h1>Options</h1>
  </div><!-- /header -->

  <div data-role="content">
    <fieldset class="ui-grid-a">
      <script type="text/javascript">
        function EditOption(){
          window.location.href='Expense.jsp?Id=<%=request.getParameter("Id")%>&action=Edit&token=<%=request.getParameter("token")%>';
        }
        function generateDeleteType(Id, token){
          var xmlInclude = element('token', token) + element('diaryId',Id) ;
            var xml = element('Diary', xmlInclude);
            return constructXML(xml);

        }
        function DeleteOption(){
        $.ajax({
        url: '../services/Diary/DeleteEntry',
        data: generateDeleteType(<%=request.getParameter("Id")%>, <%=request.getParameter("token")%>),
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
          if($(result.getElementsByTagName("Status")).text() == "true"){
            alert("Successfully Deleted");
            window.location.href="View.jsp"
          }
          else{
            alert("Unable to delete the entry");
          }
            

        },
        error: function (request, error) {
            // This callback function will trigger on unsuccessful action               
            alert('Network error has occurred please try again!');
            //window.location.href="Profile.jsp";
        }
        });

        }
      </script>
    <div class="ui-block-a"><a href='#' onclick="EditOption()" data-icon="edit" data-role="button" data-inline="true">Edit</a></div>
    <div class="ui-block-b"><a href="#" onclick="DeleteOption()" data-role="button" data-icon="delete" data-inline="true">Delete</a></div>
</fieldset>
  </div><!-- /content -->

  <div data-role="footer">
    <h4>Page Footer</h4>
  </div><!-- /footer -->
</div><!-- /page -->

</body>