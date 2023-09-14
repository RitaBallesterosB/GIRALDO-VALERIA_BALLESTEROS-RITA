window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará del nuevo paciente
    const formulario = document.getElementById('registroPacienteForm');
    const pacienteRegistrado = document.getElementById('pacienteRegistrado');

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

       //creamos un JSON que tendrá los datos del nuevo odontólogo
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
            domicilio:{
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value,
                }
        };
        //invocamos utilizando la función fetch la API con el método POST que guardará
        //el paciente que enviaremos en formato JSON
        const url = 'pacientes/registrar';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => {
                  if (!response.ok){
                        throw new Error ('Error en la solicitud'); }
                         return response.json();
                                          })
            .then(data => {
                 //Si no hay ningun error se muestra un mensaje diciendo que
                 //se agrego bien
                 document.querySelector('#pacienteRegistrado').style.display = "block";
                 pacienteRegistrado.innerHTML = '<p>Paciente registrado exitosamente</p>';
                 resetUploadForm();

            })
            .catch(error => {
             document.querySelector('#pacienteRegistrado').style.display = "block";
                    pacienteRegistrado.innerHTML = '<p>Paciente no se pudo registrar</p>' ;
             resetUploadForm();
            })
    });


    function resetUploadForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#dni').value = "";
        document.querySelector('#fechaIngreso').value = "";
        document.querySelector('#calle').value = "";
        document.querySelector('#numero').value = "";
        document.querySelector('#localidad').value = "";
        document.querySelector('#provincia').value = "";
    }


})