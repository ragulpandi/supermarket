function createCartListTemplet(listOfCartProduct)
{
    var rawCartListTemplet = document.getElementById("cartTemplet").innerHTML;

    var compiledCartListTemplet = Handlebars.compile(rawCartListTemplet);

    var cartContainer = document.getElementById("listCart");
    cartContainer.innerHTML = compiledCartListTemplet(listOfCartProduct);
}




var listOfCartProduct = "";
var customerId = "";
var customerName = "";
var sum ="";
function getCartList(URL,st)
{
    var cookieArr = document.cookie.split(";");
    for(var i=0; i < cookieArr.length; i++)
    {
        var cookiePair = cookieArr[i].split("=");

        if("customerId" == cookiePair[0].trim())
        {
            customerId = cookiePair[1];
        }
        if("customerName" == cookiePair[0].trim())
        {
            customerName = cookiePair[1];
        }
    }
       
    fetch('http:'+URL+customerId)
    .then(response => response.json())
    .then(data =>{
        listOfCartProduct = data;
        
        //console.log(sum);
        console.log(listOfCartProduct)

        if(st=='billpage')
        {
            data = data.billList;
            sum = 0;
            for(var i=0;i<data.length;i++){
                sum += data[i].productAmount;
            }
            document.getElementById("userName").innerHTML = customerName;
            document.getElementById("total").innerHTML = sum;
        }

        createCartListTemplet(listOfCartProduct);

        
        //localStorage.setItem("listOfCartProduct", listOfCartProduct)
        
    })
}



