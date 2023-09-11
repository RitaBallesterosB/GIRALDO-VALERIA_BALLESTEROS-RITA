document.addEventListener('DOMContentLoaded', function () {
    const buscarOdontologo = document.querySelector('#buscarOdontologoForm');
    const odontologoResultado = document.querySelector('#odontologoResultado');

    buscarOdontologo.addEventListener('submit', function (event) {
        event.preventDefault();

        const odontologoId = document.getElementById('id').value;

         const url = `/odontologos/${odontologoId}`;
         const settings = {
                    method: 'GET',
                    headers: {
                    'Content-Type': 'application/json'
                    }
                  }

        // Realizar una solicitud para buscar el Odontólogo por ID
        fetch(url,settings)
            .then(response => response.json())
            .then(data => {
             
            document.querySelector('#odontologoResultado').style.display = "block";
                    odontologoResultado.innerHTML = `
                        <h6>Información del Odontólogo</h6>
                        <p>ID: ${data.id}</p>
                        <p>Matrícula: ${data.matricula}</p>
                        <p>Nombre: ${data.nombre}</p>
                        <p>Apellido: ${data.apellido}</p>
                    `;
          
              
            })
            .catch(error => {
             document.querySelector('#odontologoResultado').style.display = "block";
                         odontologoResultado.innerHTML = "<p>Odontólogo no encontrado.</p>"
            });
    });
});
