/**
 * Created by zihaoli on 11/22/16.
 */
function a()
{

    var username=document.getElementById("username").value;
    var password=document.getElementById("password").value;
    var rePassword=document.getElementById("rePassword").value;

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
    else if(password.length==0||rePassword.length==0)
    {
        alert('password or repassword should not be blank');
        return false;
    }
    else if(password!=rePassword)
    {
        alert('password should equal to the rePassword');
        return false;
    }
    else
    {
        var count=0;
        if(/[0-9]/.test(password))
        {
            count++;
        }
        if(/[a-z]/.test(password))
        {
            count++;
        }
        if(/[A-Z]/.test(password))
        {
            count++;
        }
        else if(/[~!@#$%^&\*()_\+]/.test(password))
        {
            count++;
        }
        if(count>=3)
        {
            return true;
        }
        else
        {
            alert(count+'the password is too easy');
            return false;
        }
    }

}