window.addEventListener('load', function () {
    // Obtener una referencia a la tabla y el cuerpo de la tabla
    const table = document.querySelector("table");
    const tbody = table.querySelector("tbody");

    const url = '/pacientes';
          const settings = {
            method: 'GET'
          }

    // Realizar una solicitud  para obtener la lista de pacientes
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            // Llenar la tabla con los datos de los pacientes
            data.forEach(paciente => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${paciente.id}</td>
                    <td>${paciente.nombre}</td>
                    <td>${paciente.apellido}</td>
                    <td>${paciente.dni}</td>
                    <td>${paciente.fechaIngreso}</td>
                    <td><button class="btn btn-danger btn-eliminar" data-id="${paciente.id}">Eliminar</button></td>
                `;

                tbody.appendChild(row);
            });

            // Agregar manejo de eventos para los botones "Eliminar"
            const botonesEliminar = document.querySelectorAll(".btn-eliminar");
            botonesEliminar.forEach(btn => {
                btn.addEventListener("click", function () {
                    const pacienteId = this.getAttribute("data-id");
                    eliminarPaciente(pacienteId);
                    window.location.reload();

                });
            });
        })
        .catch(error => {
            console.error("Error al cargar los pacientes:", error);
        });

    // Función para eliminar un paciente
    function eliminarPaciente(id) {
        // Realizar una solicitud DELETE para eliminar el paciente
        fetch(`pacientes/eliminar/${id}`, {
            method: "DELETE"
        })
        .then(response => {
            if (response.status === 204) {
                // Eliminación exitosa, eliminar la fila de la tabla
                const filaAEliminar = document.querySelector(`tr[data-id="${id}"]`);
                filaAEliminar.remove();
            }
        })
        .catch(error => {
            console.error("Error al eliminar el odontólogo:", error);
        });
    }
});