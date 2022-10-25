function createProfileTempleteById(profileDetail)
{
    var rawProfileTemplet = document.getElementById("profileDetailByIdTemplet").innerHTML;
    
    var compiledProductTemplet = Handlebars.compile(rawProfileTemplet);
    
    var profileContainer = document.getElementById("profile");
    profileContainer.innerHTML = compiledProductTemplet(profileDetail);
}


var customerId = "";
var customerRoll = "";
function getCustomerById()
{
    var cookieArr = document.cookie.split(";");
    for(var i=0; i < cookieArr.length; i++)
    {
        var cookiePair = cookieArr[i].split("=");

        if("customerId" == cookiePair[0].trim())
        {
            customerId = cookiePair[1];
        }
        if("customerRoll" == cookiePair[0].trim())
        {
            customerRoll = cookiePair[1];
        }
    }

    fetch('http://localhost:8080/SuperMarketApplication/user/'+customerId)
    .then(response => response.json()).then(data =>{
        console.log(data);

        createProfileTempleteById(data);
    })
}

const profileUpdateForm = document.getElementById('profileUpdateForm');

profileUpdateForm.addEventListener('submit',function(e)
{
    e.preventDefault();
    var profileUpdateData = new FormData(e.target);
    var profileUpdateDataObject = Object.fromEntries(profileUpdateData);
    console.log(JSON.stringify(profileUpdateDataObject));

    let options = {
        method: 'PUT',
        headers:{
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(profileUpdateDataObject)
    }

    fetch('http://localhost:8080/SuperMarketApplication/reset/'+customerId,options)
    .then(response => response.text()).then(data =>
        {
            if(data == "Update successfully" && customerRoll == "admin")
            {
                alert("Profile updated");
                location.href = "adminView.html";
            }
            if(data == "Update successfully" && customerRoll == "Customer")
            {
                alert("Profile updated");
                location.href = "customerView.html";
            }
        })
})