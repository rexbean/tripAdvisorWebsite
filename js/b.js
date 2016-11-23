/**
 * Created by zihaoli on 11/22/16.
 */
function b()
{
    var username=document.getElementById("username").value;
    var password=document.getElementById("password").value;
    //var btn=document.getElementById("btn");
    if(username==undefined||username==null)
    {
        alert('username must not be null');
        return false;
    }
    else if(username.replace(/(^s*)|(s*$)/g, "").length ==0)
    {
        alert('username must not be blank');
        return false;
    }
    else if(password.length==0)
    {
        alert('password should not be blank');
        return false;
    }
    else
    {
        return true;
    }

}