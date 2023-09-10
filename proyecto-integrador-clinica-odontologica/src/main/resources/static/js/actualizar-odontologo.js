// Espera a que se cargue el documento
document.addEventListener("DOMContentLoaded", function () {
    // Busca el formulario de modificación por su ID
    const modificarOdontologoForm = document.getElementById("modificarOdontologoForm");

    // Agrega un evento de escucha para el envío del formulario
    modificarOdontologoForm.addEventListener("submit", function (event) {
        event.preventDefault(); // Evita que el formulario se envíe de inmediato

        // Obtiene el ID del odontólogo a modificar
        const odontologoId = document.getElementById("odontologoId").value;

        // Obtiene los valores ingresados en el formulario
        const matricula = document.getElementById("matricula").value;
        const nombre = document.getElementById("nombre").value;
        const apellido = document.getElementById("apellido").value;

        // Crea un objeto con los datos a enviar al servidor
        const datos = {
            matricula: matricula,
            nombre: nombre,
            apellido: apellido
        };

        // Realiza una solicitud AJAX para modificar el odontólogo
        fetch(`odontologos/actualizar`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(datos)
        })
        .then(response => {
            if (response.ok) {
                return response.json(); // Si la respuesta es exitosa, se espera una respuesta JSON
            } else {
                throw new Error("Error al modificar el odontólogo");
            }
        })
        .then(data => {
            alert("Odontólogo modificado exitosamente");
           
        })
        .catch(error => {
            console.error("Error en la solicitud AJAX:", error);
            alert("Error al modificar el odontólogo");
        });
    });
});
