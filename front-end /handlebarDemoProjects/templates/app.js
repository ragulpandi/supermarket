var context = {
	"name":"Ritesh Kumar",
	"occupation" : "Developer"
}

var templateScript = Handlebars.templates.demo1(context);


document.getElementById("root").innerHTML = "hello";

document.write(templateScript);