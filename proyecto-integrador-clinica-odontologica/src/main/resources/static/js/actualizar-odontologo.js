// se cargan la pagina cuando se ha construido el árbol del DOM
document.addEventListener("DOMContentLoaded", function () {
    // Busca el formulario de modificación por su ID
    const modificarOdontologoForm = document.getElementById("formulario-modificar-odontologo");

    // Agrega un evento de escucha para el envío del formulario
    modificarOdontologoForm.addEventListener("submit", function (event) {
        event.preventDefault(); // Evita que el formulario se envíe de inmediato

        let odontologoId = document.querySelector('#odontologo_id').value;



        // Crea un objeto con los datos a enviar al servidor
        const datos = {
            id: document.querySelector("#odontologo_id").value,
            matricula: document.querySelector("#matricula").value ,
            nombre: document.querySelector("#nombre").value,
            apellido: document.querySelector("#apellido").value,
        };

      //invocamos utilizando la función fetch la API peliculas con el método PUT que modificará
            //el odontologo que enviaremos en formato JSON
            const url = '/odontologos/actualizar';
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

        //Es la funcion que se invoca cuando se hace click sobre el id de una odontologo del listado
        //se encarga de llenar el formulario con los datos de la odontologo
        //que se desea modificar
        function findBy(id) {
              const url = '/odontologos/actualizar'+"/"+id;
              const settings = {
                  method: 'GET'
              }
              fetch(url,settings)
              .then(response => response.json())
              .then(data => {
              let odontologo = data;
                            document.querySelector('#odontologo_id').value = odontologo.odontologo_id;
                            document.querySelector('#matricula').value = odontologo.matricula;
                            document.querySelector('#nombre').value = odontologo.nombre;
                            document.querySelector('#apellido').value = odontologo.apellidos;
                            //el formulario por default esta oculto y al editar se habilita
                            document.querySelector('#div_odontologo_updating').style.display = "block";



              }).catch(error => {
                  alert("Error: " + error);
              })
    }
