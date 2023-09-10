document.addEventListener('DOMContentLoaded', function () {
    const buscarOdontologoForm = document.getElementById('buscarOdontologoForm');
    const odontologoResultado = document.getElementById('odontologoResultado');

    buscarOdontologoForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const odontologoId = document.getElementById('odontologoId').value;

        // Realizar una solicitud AJAX para buscar el Odontólogo por ID
        fetch(`/ruta_del_endpoint/${odontologoId}`)
            .then(response => response.json())
            .then(data => {
                if (data) {
                    // Mostrar la información del Odontólogo encontrado
                    odontologoResultado.innerHTML = `
                        <h2>Información del Odontólogo</h2>
                        <p>ID: ${data.id}</p>
                        <p>Matrícula: ${data.matricula}</p>
                        <p>Nombre: ${data.nombre}</p>
                        <p>Apellido: ${data.apellido}</p>
                        <!-- Agrega aquí otros campos de información si es necesario -->
                    `;
                } else {
                    // Mostrar un mensaje si no se encuentra el Odontólogo
                    odontologoResultado.innerHTML = '<p>Odontólogo no encontrado.</p>';
                }
            })
            .catch(error => console.error('Error:', error));
    });
});
