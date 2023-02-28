let carrito = [];
const divisa = '$';
const DOMitems = document.querySelector('#items');
const DOMcarrito = document.querySelector('#carrito');
const DOMtotal = document.querySelector('#total');
const DOMbotonVaciar = document.querySelector('#boton-vaciar');

/**
 * Evento para añadir un producto al carrito de la compra
 */
function addProductToCar(elem) {
	let arrayValues = elem.value.split("-");
	let product = {
		'id' : arrayValues[0],
		'name' : arrayValues[1],
		'price': parseInt(arrayValues[2])
	};
    carrito.push(product);
    elem.value = '';
    renderizarCarrito();
}

/**
 * Dibuja todos los productos guardados en el carrito
 */
function renderizarCarrito() {
	let totalCarrito = 0;
    // Vaciamos todo el html
    DOMcarrito.textContent = '';
    // Quitamos los duplicados
    const carritoSinDuplicados = [...new Set(carrito.map(x => x.id))];
    // Generamos los Nodos a partir de carrito
    carritoSinDuplicados.forEach((item) => {
        // Obtenemos el item que necesitamos de la variable base de datos
        const miItem = carrito.filter((itemBaseDatos) => {
            // ¿Coincide las id? Solo puede existir un caso
            return itemBaseDatos.id === item;
        });
        // Cuenta el número de veces que se repite el producto
        const numeroUnidadesItem = carrito.reduce((total, itemCurrent) => {
            // ¿Coincide las id? Incremento el contador, en caso contrario no mantengo
            return itemCurrent.id === item ? total += 1 : total;
        }, 0);
        // Creamos el nodo del item del carrito
        const miNodo = document.createElement('div');
        miNodo.style.textAlign="right";
        miNodo.textContent = `${numeroUnidadesItem} x ${miItem[0].name} - ${divisa}${miItem[0].price}`;
        totalCarrito = totalCarrito + (numeroUnidadesItem * miItem[0].price);
        // Boton de borrar
        const miBoton = document.createElement('button');
        miBoton.classList.add('btn');
        miBoton.innerHTML = '<i class="ni ni-fat-remove"></i>';
        miBoton.style.marginLeft = '1px';
        miBoton.dataset.item = item;
        miBoton.addEventListener('click', borrarItemCarrito);
        // Mezclamos nodos
       // miNodo.appendChild(miBoton);
        DOMcarrito.appendChild(miNodo);
    });
    // Renderizamos el precio total en el HTML
    DOMtotal.textContent = totalCarrito;
    document.querySelector('#listProducts').value = carrito.map(x => x.id).join();
}

/**
 * Evento para borrar un elemento del carrito
 */
function borrarItemCarrito(evento) {
    // Obtenemos el producto ID que hay en el boton pulsado
    const id = evento.target.dataset.item;
    // Borramos todos los productos
    carrito = carrito.filter((carritoId) => {
        return carritoId.id !== id;
    });
    // volvemos a renderizar
    renderizarCarrito();
}

/**
 * Varia el carrito y vuelve a dibujarlo
 */
function vaciarCarrito(evento) {
    // Limpiamos los productos guardados
    carrito = [];
    // Renderizamos los cambios
    renderizarCarrito();
    evento.stopPropagation();
}

// Eventos
DOMbotonVaciar.addEventListener('click', vaciarCarrito);

// Inicio
renderizarCarrito();