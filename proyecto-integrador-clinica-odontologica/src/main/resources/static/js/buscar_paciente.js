window.addEventListener('load', function () {
    const buscarPaciente = document.querySelector('#buscarPacienteForm');
    const pacienteResultado = document.querySelector('#pacienteResultado');

    buscarPaciente.addEventListener('submit', function (event) {
        event.preventDefault();

        const pacienteId = document.getElementById('id').value;

         const url = `/pacientes/${pacienteId}`;
         const settings = {
                    method: 'GET',
                    headers: {
                    'Content-Type': 'application/json'
                    }
                  }

        // Realizar una solicitud para buscar el paciente por ID
        fetch(url,settings)
            .then(response => response.json())
            .then(data => {

            document.querySelector('#pacienteResultado').style.display = "block";
                    pacienteResultado.innerHTML = `
                        <h6>Información del paciente</h6>
                        <p>ID: ${data.id}</p>
                        <p>Nombre: ${data.nombre}</p>
                        <p>Apellido: ${data.apellido}</p>
                        <p>Dni: ${data.dni}</p>
                        <p>Fecha de ingreso: ${data.fechaIngreso}</p>
                    `;


            })
            .catch(error => {
             document.querySelector('#pacienteResultado').style.display = "block";
                         pacienteResultado.innerHTML = "<p>paciente no encontrado.</p>"
            });
    });
});