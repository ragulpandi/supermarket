const loginForm = document.getElementById("loginForm");
 console.log(loginForm);

loginForm.addEventListener('submit', function(e){
    e.preventDefault();
    const myLoginFormData = new FormData(e.target);
    const loginFormDataObj = Object.fromEntries(myLoginFormData.entries());

    var userName = loginFormDataObj.userName;
    var userPassword = loginFormDataObj.userPassword;

    fetch('http://localhost:8080/SuperMarketApplication/login?userName='+userName+'&userPassword='+userPassword)
    .then(response =>response.json()).then(data=>{
        document.cookie = "customerRoll="+data.customerRoll;
        document.cookie = "customerId="+data.customerId;
        
        if(data.customerRoll == "Customer")
        {
            document.cookie = "customerName="+userName;
            location.href ="customerView.html";
        }
        if(data.customerRoll == "admin")
        {
            document.cookie = "customerName="+userName;
            location.href = "adminView.html";
        }
        
        if(data.customerRoll == null)
        {
            alert("invalid login details")
            location.href = "#login";
        }
    })

})


