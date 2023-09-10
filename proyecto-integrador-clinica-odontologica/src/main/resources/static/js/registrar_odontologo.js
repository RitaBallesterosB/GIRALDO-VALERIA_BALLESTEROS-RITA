// Obtener una referencia al formulario HTML
const form = document.getElementById("registroOdontologoForm");

// Agregar un evento de escucha al formulario para manejar la presentación del formulario
form.addEventListener("submit", function (event) {
  event.preventDefault(); // Evitar que el formulario se envíe de manera predeterminada

  // Obtener los valores ingresados por el usuario desde el formulario
  const matricula = document.getElementById("matricula").value;
  const nombre = document.getElementById("nombre").value;
  const apellido = document.getElementById("apellido").value;

  // Crear un objeto con los datos del odontólogo
  const odontologo = {
    matricula: matricula,
    nombre: nombre,
    apellido: apellido,
  };

  // Realizar una solicitud HTTP POST al endpoint correspondiente
  fetch("/odontologos/registrar", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(odontologo), // Convertir el objeto a JSON
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error al registrar el odontólogo");
      }
      return response.json();
    })
    .then((data) => {
      // El odontólogo se ha registrado exitosamente, puedes mostrar un mensaje o redirigir a otra página si lo deseas
      console.log("Odontólogo registrado exitosamente:", data);
    })
    .catch((error) => {
      // Manejar errores aquí, por ejemplo, mostrar un mensaje de error al usuario
      console.error("Error al registrar el odontólogo:", error);
    });
});
