function createProductListTemplet(listOfProducts)
{
    var rawProductListTemplet = document.getElementById("productTemplet").innerHTML;
    
    var compiledProductListTemplet = Handlebars.compile(rawProductListTemplet);
    
    var productContainer = document.getElementById("listProduct");
    productContainer.innerHTML = compiledProductListTemplet(listOfProducts);
}


var listOfProducts = "";

function getListOfProducts()
{
    fetch('http://localhost:8080/SuperMarketApplication/product')
    .then(response =>response.json()).then(data=>{
        //console.log(data[0]);
        listOfProducts = data;
        console.log(listOfProducts);

        createProductListTemplet(listOfProducts);
        //localStorage.setItem("listOfProducts", data);

    })
}