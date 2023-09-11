document.addEventListener("DOMContentLoaded", function () {
    // Obtener una referencia a la tabla y el cuerpo de la tabla
    const table = document.querySelector("table");
    const tbody = table.querySelector("tbody");

    const url = '/odontologos';
          const settings = {
            method: 'GET'
          }

    // Realizar una solicitud  para obtener la lista de odontólogos
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            // Llenar la tabla con los datos de los odontólogos
            data.forEach(odontologo => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${odontologo.id}</td>
                    <td>${odontologo.matricula}</td>
                    <td>${odontologo.nombre}</td>
                    <td>${odontologo.apellido}</td>
                    <td><button class="btn btn-danger btn-eliminar" data-id="${odontologo.id}">Eliminar</button></td>
                `;

                tbody.appendChild(row);
            });

            // Agregar manejo de eventos para los botones "Eliminar"
            const botonesEliminar = document.querySelectorAll(".btn-eliminar");
            botonesEliminar.forEach(btn => {
                btn.addEventListener("click", function () {
                    const odontologoId = this.getAttribute("data-id");
                    eliminarOdontologo(odontologoId);
                });
            });
        })
        .catch(error => {
            console.error("Error al cargar los odontólogos:", error);
        });

    // Función para eliminar un odontólogo
    function eliminarOdontologo(id) {
        // Realizar una solicitud DELETE para eliminar el odontólogo
        fetch(`odontologos/eliminar/${id}`, {
            method: "DELETE"
        })
        .then(response => {
            if (response.status === 204) {
                // Eliminación exitosa, eliminar la fila de la tabla
                const filaAEliminar = document.querySelector(`tr[data-id="${id}"]`);
                filaAEliminar.remove();
            } else {
                console.error("Error al eliminar el odontólogo.");
            }
        })
        .catch(error => {
            console.error("Error al eliminar el odontólogo:", error);
        });
    }
});
