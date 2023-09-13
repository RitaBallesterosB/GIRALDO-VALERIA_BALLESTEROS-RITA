window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará del nuevo odontologo
    const formulario = document.getElementById('registroOdontologoForm');
    const odontologoRegistrado = document.querySelector('#odontologoRegistrado');

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

       //creamos un JSON que tendrá los datos del nuevo odontólogo
        const formData = {
            matricula: document.querySelector('#matricula').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,

        };
        //invocamos utilizando la función fetch la API  con el método POST que guardará
        //odontólogo que enviaremos en formato JSON
        const url = 'odontologos/registrar';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                 //Si no hay ningun error se muestra un mensaje diciendo que
                 //se agrego bien
                 document.querySelector('#odontologoRegistrado').style.display = "block";
                 odontologoRegistrado.innerHTML = '<p>Odontólogo registrado exitosamente</p>';
                 resetUploadForm();

            })
            .catch(error => {
             document.querySelector('#odontologoRegistrado').style.display = "block";
                    odontologoRegistrado.innerHTML = '<p>Odontólogo no se pudo registrar</p>' ;
             resetUploadForm();
            })
    });


    function resetUploadForm(){
        document.querySelector('#matricula').value = "";
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";

    }


})