function addToCartFunction(productIdDetails)
{

    var productId = productIdDetails.getAttribute('value');
    console.log("productId:"+productId);

    var orderQuantity = "";

    orderQuantity = document.getElementById(productId).value;

    console.log("orderQuantity:"+orderQuantity);
    if(orderQuantity <=0)
    {
        alert("Please enter the value greater than 0");
        location.href = "customerView.html";
    }

    var cookieArr = document.cookie.split(";");
    var customerId = "";

    for(var i=0; i<cookieArr.length; i++)
    {
        var cookiePair = cookieArr[i].split("=");
        if("customerId" == cookiePair[0].trim())
        {
            customerId = cookiePair[1];
        }
    }
    console.log("customerId:"+customerId);

    var bodyContent = {};
    bodyContent["productId"]=productId;
    bodyContent["customerId"] = customerId;
    bodyContent["orderQuantity"] = orderQuantity;

    console.log(bodyContent);

    let options = {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(bodyContent)
    }

    fetch('http://localhost:8080/SuperMarketApplication/cart',options)
    .then(response => response.text()).then(data =>
        {
            if(data == "successfully add to cart")
            {
                alert("successfully add to cart");
            }
            else{
                alert("something went wrong");
            }

            location.href = "customerView.html";
        })
    
}