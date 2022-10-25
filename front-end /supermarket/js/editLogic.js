var productId = 0;
const urlParam = new URLSearchParams(window.location.search);
if(urlParam.has("productId"))
{
    // console.log(urlParam.get("productId"));
    productId = urlParam.get("productId");
}


function createProductTempleteById(singleProductDetail)
{
    var rawProductTemplet = document.getElementById("productDetailByIdTemplet").innerHTML;
    
    var compiledProductTemplet = Handlebars.compile(rawProductTemplet);
    
    var productContainer = document.getElementById("product");
    productContainer.innerHTML = compiledProductTemplet(singleProductDetail);
}

function getProductById()
{
    fetch('http://localhost:8080/SuperMarketApplication/product/'+productId)
    .then(response =>response.json()).then(data=>{

        console.log(data);

        createProductTempleteById(data);
    })

}

const editForm = document.getElementById("editForm");

function convertToJson(matrixArray)
{
    var jsonResult= {};

    for(var i=0; i<3; i++)
    {
        jsonResult[matrixArray[i][0]] = matrixArray[i][1];
    }
    return jsonResult;
}
editForm.addEventListener('submit',function(e)
{
    e.preventDefault();
    var editFormData = new FormData(e.target);


    editFormData = [...editFormData];
    // console.log(editFormData);
    //console.log(convertToJson(editFormData))



    const editFormDataObject = convertToJson(editFormData);
  
    let options = {
    method: 'PUT',
    headers:{
        'Content-Type': 'application/json;charset=utf-8'
    },
    body: JSON.stringify(editFormDataObject)
    }

    console.log(editFormDataObject);

    fetch('http://localhost:8080/SuperMarketApplication/product/'+productId,options)
    .then(response => response.text()).then(data =>
        {
            //console.log(data);
            if(data == "successfully updated")
            {
                alert("successfully updated");
                location.href = "adminView.html";
            }
            else{
                alert("something went wrong");
            }
        
        })

})


