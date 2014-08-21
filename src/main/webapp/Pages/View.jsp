<!DOCTYPE html>
<html>
<head>
  <title>View Expense</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">
   <meta http-equiv="Cache-Control" content="no-store" />
  <link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
  <script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
  <script src="../JS/XMLGenerate.js"></script>
      <script src="../JS/Cookies.js"></script>
  <script src="../JS/View.js"></script>
</head>
<body>
  <style>
    .view-list thead th,
.view-list tbody tr:last-child {
    border-bottom: 1px solid #d6d6d6; 
    border-bottom: 1px solid rgba(0,0,0,.1);
}
.view-list tbody th,
.view-list tbody td {
    border-bottom: 1px solid #e6e6e6; 
    border-bottom: 1px solid rgba(0,0,0,.05);
}
.view-list tbody tr:last-child th,
.view-list tbody tr:last-child td {
    border-bottom: 0;
}
.view-list tbody tr:nth-child(odd) td,
.view-list tbody tr:nth-child(odd) th {
    background-color: #eeeeee;
    background-color: rgba(0,0,0,.04);
}
    </style>

<div data-role="page">
  <%@include file="LeftMenu.jspf" %>
  <div data-role="header">
   <a href="Profile.jsp" data-role="button" data-icon="arrow-l"  class="ui-btn-left" >Back</a>
            <h1>ViewExpense</h1>
            <a href="#mypanel" data-icon="gear" class="ui-btn-right" data-position="right" data-display="overlay">Menu</a>
  </div>

  <div data-role="content">
    <%if(!"Group".equals(request.getParameter("action"))){ %>
    <script type="text/javascript">
    $(document).ready(function () {
        view("Entry");
    });
    </script>
    
    <select id="group" onchange="reArrange()">
      <option value="SelectAll" selected="true">Select All</option>
      
    </select>
    <select id="user" onchange="reArrange()">
      <option value="SelectAll" selected="true">Select All</option>      
    </select>
    
    <%
        }
          else{
    %>
    <script type="text/javascript">
               $(document).ready(function () {
                 view("Group");
               });
             </script>
    <%}%>
    <table data-role="table" data-mode="columntoggle" id="table-custom-2"  class="view-list ui-responsive" style="display:table;" >
         <thead>
           <tr class="ui-bar-d">
             <%if(!"Group".equals(request.getParameter("action"))){ %>
             
             <th>Rank</th>
             <th>Date</th>
             <th>GroupId</th>
             <th>Name</th>
             <th>Item</th>
             <th>Amount</th>   
             <%}
              else{%> 
             
             <th></th>
             <th>Name</th>
             <th>Status</th> 
             <th>Button</th>
             
             <%}%>
           </tr>
         </thead>
         <tbody id = "tbody">           
         </tbody>
       </table>
  </div>

  <div data-role="footer">
    <h4>Page Footer</h4>
  </div>
</div>
  
 

</body>
</html>