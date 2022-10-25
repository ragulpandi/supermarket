const signUpForm = document.getElementById("signUpForm");

signUpForm.addEventListener('submit',function(e)
{
    e.preventDefault();
    const signUpFormData = new FormData(e.target);
    const signUpFormDataObject = Object.fromEntries(signUpFormData);

    console.log(JSON.stringify(signUpFormDataObject));

    let options = {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(signUpFormDataObject)
    }

    fetch('http://localhost:8080/SuperMarketApplication/signup',options)
    .then(response => response.text()).then(data =>
        {
            //console.log(data);
            if(data == "successfully account created")
            {
                alert("Account created");
                location.href = "login.html";
            }

            if(data == "userName already exist")
            {
                alert("userName already exist");
            }
        })
}) 