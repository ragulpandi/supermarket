const addNewItemForm = document.getElementById("addItemsForm");
//console.log(addNewItemForm);

addNewItemForm.addEventListener('submit', function(e)
{
    e.preventDefault();
    const addNewItemData = new FormData(e.target);
    const addNewItemDataObject = Object.fromEntries(addNewItemData);

    console.log(JSON.stringify(addNewItemDataObject));

    let options = {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(addNewItemDataObject)
    }

    fetch('http://localhost:8080/SuperMarketApplication/product',options)
    .then(response => response.text()).then(data=>{
        console.log(data);

        if(data == "successfully added")
        {
            alert("Successfully Added");
            location.href = "adminView.html";
        }
        return data;
    })


})

