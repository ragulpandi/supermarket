function deleteFunction(productDetailsTable, URL)
{
    
    var productId = productDetailsTable.getAttribute('value');
    console.log(productDetailsTable.getAttribute('value'));

    console.log('http:'+URL+productId);

    // fetch('http://localhost:8080/SuperMarketApplication/product/'+productId, {method: 'DELETE'})
    // .then(response =>response.text()).then(data=>{
        
    //     console.log(data);
    //     location.href="adminView.html";
    // })

    fetch('http:'+URL+productId, {method: 'DELETE'})
    .then(response =>response.text()).then(data=>{
        
        //console.log(data);
        if(data == "successfully deleted")
            location.href="adminView.html";
        else if(data == "successfully removed from cart")
            location.href="cart.html";
    })
}




function logout()
{
    document.cookie="customerId=null;max-age=0";
    document.cookie="customerRoll=null;max-age=0";  
    document.cookie="customerName=null;max-age=0";
    location.href = "login.html";
}