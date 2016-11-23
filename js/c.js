/**
 * Created by zihaoli on 11/22/16.
 */
function c()
{
    var rating=document.getElementById("rating").value;
    if(0<=rating&&rating<=5)
        return true;
    else
    {
        alert('rating must be in 0-5'+rating);
        return false;
    }

}