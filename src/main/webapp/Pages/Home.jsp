<!DOCTYPE html>
<html>

<head>
    <title>Hicon Garden</title>
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0" />
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
    <link rel="stylesheet" href="../css/styles.css" />
    <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.js"></script>
    <script src="../JS/Cookies.js"></script>
    <script src="../JS/XMLGenerate.js"></script>
    <script src="../JS/index.js"></script>
</head>

<body>
    <div data-role="page" id="login">
        <div data-role="header">
            <h1>Hicon Garden</h1>
        </div>
        <div data-role="content">
            <form id="frmLogin" class="validate">
                <div data-role="fieldcontain">
                    <label for="email"><em>* </em> Email:</label>
                    <input type="text" id="email" name="email" data-theme="d" placeholder="Enter First Name" />
                </div>
                <div data-role="fieldcontain">
                    <label for="password"><em>* </em>Password:</label>
                    <input type="password" id="password" name="password" data-theme="d" placeholder="Enter First Name" />
                </div>
                <div class="ui-body ui-body-b">
                    <button class="btnLogin" type="submit" data-theme="a">Login</button>
                </div>
            </form>
        </div>
        <div data-theme="a" data-role="footer" data-position="fixed"></div>
    </div>
</body>

</html>