window.addEventListener('load', function () {
    // Busca el formulario de modificación por su ID
    const modificarPacienteForm = document.getElementById("formulario-modificar-paciente");
    const response = document.querySelector('#response');

    // Agrega un evento de escucha para el envío del formulario
    modificarPacienteForm.addEventListener("submit", function (event) {
        event.preventDefault(); // Evita que el formulario se envíe de inmediato

        let pacienteId = document.querySelector('#paciente_id').value;

        // Crea un objeto con los datos a enviar al servidor
        const datos = {
            id: document.querySelector("#paciente_id").value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
            domicilio:{
                id: document.querySelector('#domicilio_id').value,
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value,
                }
        };

      //invocamos utilizando la función fetch la API  con el método PUT que modificará
            //el paciente que enviaremos en formato JSON
            const url = '/pacientes/actualizar';
            const settings = {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(datos)
            }
            fetch(url,settings)
              .then(response => response.json())

        })
     })

        //Es la funcion que se invoca cuando se hace click sobre el id de una paciente del listado
        //se encarga de llenar el formulario con los datos de la paciente
        //que se desea modificar
        function findBy(id) {
              const url = '/pacientes/actualizar'+"/"+id;
              const settings = {
                  method: 'GET'
              }
              fetch(url,settings)
              .then(response => response.json())
              .then(data => {
              let paciente = data;
                            document.querySelector('#paciente_id').value = paciente.paciente_id;
                            document.querySelector('#nombre').value = paciente.nombre;
                            document.querySelector('#apellido').value = paciente.apellido;
                            document.querySelector('#dni').value = paciente.dni;
                            document.querySelector('#fechaIngreso').value = paciente.fechaIngreso;

                            //el formulario por default esta oculto y al editar se habilita
                            document.querySelector('#response').style.display = "block";
                            response.innerHTML = '<p>Paciente modificado exitosamente</p>';


              }).catch(error => {
                  alert("Error: " + error);
              })
    }