<html>
<head>
    <title>Add Expense</title>
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
    <link rel="stylesheet" href="../css/styles.css" />
    <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.js"></script>
    <script src="../JS/XMLGenerate.js"></script>
    <script src="../JS/Cookies.js"></script>
    <script src="../JS/Expense.js"></script>  
</head>
<body>
    <div data-role="page">
        <%@include file="LeftMenu.jspf" %><!-- /panel -->
        <!-- /panel -->
        <div data-role="header">
          <a href="Profile.jsp" data-role="button" data-icon="arrow-l"  class="ui-btn-left" >Back</a>
            <h1>Daily Expense</h1>
            <a href="#mypanel" data-icon="gear" class="ui-btn-right" data-position="right" data-display="overlay">Menu</a>
          <script type="text/javascript">
    $(document).ready(function(){
      <%if("Edit".equals(request.getParameter("action"))){ %>
      populate('<%=request.getParameter("Id")%>','<%=request.getParameter("token")%>');
      <%}%>
        });
  </script>
        </div>
        <!-- /header -->
        <div data-role="content">
          
              <%if(!"Group".equals(request.getParameter("action"))){ %>
                <form id="frmLogin" class="validate">
              <%if("Edit".equals(request.getParameter("action"))){ %>
                  <input type="hidden" id="action" name="action" value="Update" />
                  <input type="hidden" id="dairyId" name="dairyId" value='<%=request.getParameter("Id")%>' />
              <%}
              else
                 {%>
              <input type="hidden" id="action" name="action" value="Add" />
              <%}%>
                <div data-role="fieldcontain">
                    <label for="item"><em>* </em> Item:</label>
                    <input type="text" id="item" name="item" data-theme="d" placeholder="Enter Item" />
                </div>
                <div data-role="fieldcontain">
                    <label for="price"><em>* </em>Price:</label>
                    <input type="text" id="price" name="price" data-theme="d" placeholder="Enter Price" />
                </div>
                  <div data-role="fieldcontain" <%if("Edit".equals(request.getParameter("action"))){ %> style="display:none"<%}%>>
                    <label for="group">Group:</label>
                    <select name="group" id="group">
                        
                    </select>
                </div>
              <%}
              else{%>
                <form id="groupLogin" class="validate">
              <input type="hidden" id="action" name="action" value="Group" />
              <div data-role="fieldcontain">
                    <label for="Name"><em>* </em> Name:</label>
                    <input type="text" id="Name" name="Name" data-theme="d" placeholder="Enter Name" />
                </div>
                <div data-role="fieldcontain">
                    <label for="Desc"><em>* </em>Desc:</label>
                    <input type="text" id="Desc" name="Desc" data-theme="d" placeholder="Enter Description" />
                </div>
                
              <%}%>
                <fieldset class="ui-grid-b">
                    <div class="ui-block-a">
                      <%if(!"Edit".equals(request.getParameter("action"))){ %>
                        <button class="btnLogin" type="submit" data-theme="a">Add</button>
                      <%} else {%>
                      <button class="btnLogin" type="submit" data-theme="a">Update</button>
                      <%}%>
                    </div>
                  <div class="ui-block-b">
                        <button class="btnLogin"  onclick="window.location.href='View.jsp'" data-theme="a">Cancel</button>
                    </div>
                  <%if(!"Edit".equals(request.getParameter("action"))){ %>
                    <div class="ui-block-c">
                        <button class="btnLogin" type="reset" data-theme="a">Reset</button>
                    </div>
                  <%} %>
                    
                </fieldset>
            </form>
        </div>
        <!-- /content -->
        <div data-role="footer">
            <h4>Page Footer</h4>
        </div>
        <!-- /footer -->
    </div>
    <!-- /page -->
</body>

</html>