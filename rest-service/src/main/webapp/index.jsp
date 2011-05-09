<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creating accounts</title>
    </head>
    <body>
        <form action="/Accounts/rest/accounts" method="POST">
            Select project:
            <select name="project">
                <option value="ONE">ONE</option>
                <option value="TWO">TWO</option>
                <option value="THREE">THREE</option>
            </select><br />
            Entry name:
            <input type="text" name="entryname" /><br />
            Url:
            <input type="text" name="url" /><br />
            Username:
            <input type="text" name="username" /><br />
            Password:
            <input type="password" name="password" />
            <input type="password" name="password2" /><br />
            Tag:
            <input type="text" name="tag" /><br />
            <input type="submit" value="Create Account" />
        </form>
    </body>

</html>
