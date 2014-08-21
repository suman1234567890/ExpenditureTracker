<!DOCTYPE html>
<html>

<head>
    <title>Home</title>
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0" />
    <meta http-equiv="Cache-Control" content="no-store" />
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
    <link rel="stylesheet" href="../css/styles.css" />
    <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
    <script src="../JS/Cookies.js"></script>
    <script src="../JS/Profile.js"></script>

</head>

<body>
    <div data-role="page" data-theme="b" id="demo-page" class="my-page" data-url="demo-page">
        <div data-role="header">
            <h1>Hicon Garden</h1>
        </div>
        <!-- /header -->
        <div role="main" class="ui-content">
            <div id="popup-outside-page" data-theme="a">
                <ul data-role="listview" data-inset="true">
                    <li>
                        <a href="#Groups" data-rel="popup">
                            <img src="../Assets/Group.png" class="ui-li-thumb">
                            <h2>Groups</h2>
                            <p>Available Members</p>
                            <p class="ui-li-aside">Group</p>
                        </a>
                    </li>
                    <li>
                        <a href="#Transaction" data-rel="popup">
                            <img src="../Assets/Transaction.png" class="ui-li-thumb">
                            <h2>Transaction</h2>
                            <p>Daily Expense of Flats</p>
                            <p class="ui-li-aside">Expense</p>
                        </a>
                    </li>
                </ul>
            </div>
          <div data-role="popup" id="Transaction" data-theme="d">
        <ul data-role="listview" data-inset="true" data-theme="d">
            <li data-role="divider" data-theme="e">Choose an action</li>
            <li><a href="#" onclick="window.location.href='View.jsp'" id="ViewExpense">View Expense</a></li>
            <li><a href="#" onclick="window.location.href='Expense.jsp'" id ="AddExpense">Add Expense</a></li>
            
        </ul>
        </div>
          <div data-role="popup" id="Groups" data-theme="d">
        <ul data-role="listview" data-inset="true"  data-theme="d">
            <li data-role="divider" data-theme="e">Choose an action</li>
            <li><a href="View.jsp" onclick="window.location.href='View.jsp?action=Group'">View Groups</a></li>
            <li><a href="#" onclick="window.location.href='Expense.jsp?action=Group'">Add Group</a></li>


        </ul>
        </div>
            
        </div>
        <!-- /content -->
        <div data-role="footer">
            <h4></h4>
        </div>
        <!-- /footer -->
    </div>
    <!-- /page -->
</body>

</html>