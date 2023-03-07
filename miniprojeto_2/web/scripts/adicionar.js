$(() => {
	const addLancheButton = $('.add-lanche');
	addLancheButton.on('click', addLanche);

	const confirmarButton = $('#confirmar');
	confirmarButton.on('click', handleConfirm);
});

let itensPedido = [];
let indiceLanche = 0;

function getAdicionais() {
	return $.map($('#adicionais li'), function (n) {
		const ingrediente = {
			id: n.attributes[1].value,
			nome: n.attributes[0].value,
		};
		return ingrediente;
	});
}

function renderListaAdicionais(idLanche) {
	const adicionais = getAdicionais();
	const listaAdicionais = $(`#${idLanche} article .lista-adicionais`);
	$.each(adicionais, function (_index, value) {
		listaAdicionais.append(`
    <li>
      <p class="list-item"">${value.nome}</p>
      <button class="btn add-ingrediente" data-ingrediente="${value.id}" data-lanche-pedido="${idLanche}">Adicionar</button>
    </li>
    `);
	});

	$('.add-ingrediente').on('click', addIngrediente);
}

function addIngrediente(event) {
	event.preventDefault();

	const idLanche = event.target.attributes['data-lanche-pedido'].value;
	const idIngrediente = event.target.attributes['data-ingrediente'].value;
	const nomeIngrediente = event.target.parentElement.children[0].innerText;

	const ingrediente = {
		id: idIngrediente,
		nome: nomeIngrediente,
	};
	itensPedido[itensPedido.findIndex((item) => item.id === idLanche)].adicionados.push(ingrediente);
	renderNewItemListaAdicionados(ingrediente, idLanche);
}

function renderNewItemListaAdicionados(added, idLanche) {
	const listaAdicionados = $(`#${idLanche} .lista-adicionados`);
	listaAdicionados.append(`
    <li class="list-item" data-adicionado="${added.id}">${added.nome}</li>
  `);
}

function renderListaAdicionados(idLanche) {
	const listaAdicionados = $(`#${idLanche} .lista-adicionados`);
	listaAdicionados.empty();

	const adicionados = itensPedido[itensPedido.findIndex((item) => item.id === idLanche)].adicionados;
	$.each(adicionados, function (_index, value) {
		listaAdicionados.append(`
      <li class="list-item" data-removido="${value.id}">${value.nome}</li>
    `);
	});
}

function removeIngrediente(event) {
	event.preventDefault();
	const idLanche = event.target.attributes['data-lanche-pedido'].value;
	const idIngrediente = event.target.attributes['data-ingrediente'].value;

	const ingRemoviveis = $(`#${idLanche} article .lista-removiveis li`);

	const ingredientes = $.map(ingRemoviveis, function (n) {
		const ingrediente = {
			id: n.children[1].attributes['data-ingrediente'].value,
			nome: n.children[0].innerText,
		};
		return ingrediente;
	});

	const removed = ingredientes.find((ing) => ing.id === idIngrediente);
	const indice = itensPedido.findIndex((item) => item.id === idLanche);
	itensPedido[indice].removidos.push(removed);
	itensPedido[indice].ingredientes = [...itensPedido[indice].ingredientes.filter((ing) => ing.id !== idIngrediente)];
	renderNewItemListaRemovidos(removed, idLanche);

	const afterRemove = ingredientes.filter((ing) => ing.id !== idIngrediente);
	renderListaRemoviveis(afterRemove, idLanche);
}

function renderListaRemoviveis(ingredientes, idLanche) {
	const listaRemoviveis = $(`#${idLanche} article .lista-removiveis`);
	listaRemoviveis.empty();
	$.each(ingredientes, function (_index, value) {
		listaRemoviveis.append(`
    <li>
      <p class="list-item"">${value.nome}</p>
      <button class="btn del-ingrediente" data-ingrediente="${value.id}" data-lanche-pedido="${idLanche}">
        Remover
      </button>
    </li>
    `);
	});

	$('.del-ingrediente').on('click', removeIngrediente);
}

function renderNewItemListaRemovidos(removed, idLanche) {
	const listaRemovidos = $(`#${idLanche} .lista-removidos`);
	listaRemovidos.append(`
    <li class="list-item" data-removido="${removed.id}">${removed.nome}</li>
  `);
}

function renderListaRemovidos(idLanche) {
	const listaRemovidos = $(`#${idLanche} .lista-removidos`);
	listaRemovidos.empty();

	const removidos = itensPedido[itensPedido.findIndex((item) => item.id === idLanche)].removidos;
	$.each(removidos, function (_index, value) {
		listaRemovidos.append(`
      <li class="list-item" data-removido="${value.id}">${value.nome}</li>
    `);
	});
}

function addLanche(event) {
	event.preventDefault();

	const lanche = {
		id: `${$(this).data('id')}-${indiceLanche++}`,
		nome: $(this).data('nome'),
		ingredientes: $.map($(`[data-lanche="${$(this).data('id')}"]`), function (n) {
			const ingrediente = {
				id: n.attributes[1].value,
				nome: n.innerText.trim(),
			};
			return ingrediente;
		}),
		adicionados: [],
		removidos: [],
	};

	itensPedido.push(lanche);

	$('#pedidos').append(`
  <li>
      <section class="card" id="${lanche.id}">
          <h3 class="card-title">${lanche.nome}</h3>
          <h4 class="card-subtitle">Adicionados:</h4>
          <ul class="lista-adicionados">
          </ul>
          <h4 class="card-subtitle">Removidos:</h4>
          <ul class="lista-removidos">
          </ul>
          <article>
              <h4 class="card-subtitle">
                  Que tal subir o rating do seu lanche?
              </h4>
              <ul class="lista-adicionais">
              </ul>
          </article>
          <article>
              <h4 class="card-subtitle">
                  Deseja remover alguma peça dessa posição?
              </h4>
              <ul class="lista-removiveis">
              </ul>
          </article>
          <div class="center">
              <button class="btn" id="${lanche.id}-btn">Excluir do pedido</button>
          </div>
      </section>
  </li>
  `);

	renderListaAdicionais(lanche.id);
	renderListaRemoviveis(lanche.ingredientes, lanche.id);

	const removeLancheButton = $(`#${lanche.id}-btn`);
	removeLancheButton.on('click', removeLanche);

	const addIngredienteButton = $(`.add-ingrediente#${lanche.id}`);
	addIngredienteButton.on('click', addIngrediente);

	const removeIngredienteButton = $();
}

function removeLanche(event) {
	event.preventDefault();

	const idLanche = event.target.attributes[1].value.replace(/-btn/, ''); // O id do botão é idLanche-btn. Remove o -btn

	indiceLanche = 0;
	itensPedido = itensPedido
		.filter((item) => item.id !== idLanche) // Exclui o item da lista
		.map((item) => {
			return {
				id: `${item.id.split('-')[0]}-${indiceLanche++}`,
				nome: item.nome,
				ingredientes: item.ingredientes,
				adicionados: item.adicionados,
				removidos: item.removidos,
			};
		});

	renderPedidoAposRemove();
}

function renderPedidoAposRemove() {
	const pedidos = $('#pedidos');
	pedidos.empty();

	$.each(itensPedido, function (_index, lanche) {
		pedidos.append(`
    <li>
      <section class="card" id="${lanche.id}">
          <h3 class="card-title">${lanche.nome}</h3>
          <h4 class="card-subtitle">Adicionados:</h4>
          <ul class="lista-adicionados">
          </ul>
          <h4 class="card-subtitle">Removidos:</h4>
          <ul class="lista-removidos">
          </ul>
          <article>
              <h4 class="card-subtitle">
                  Que tal subir o rating do seu lanche?
              </h4>
              <ul class="lista-adicionais">
              </ul>
          </article>
          <article>
              <h4 class="card-subtitle">
                  Deseja remover alguma peça dessa posição?
              </h4>
              <ul class="lista-removiveis">
              </ul>
          </article>
          <div class="center">
              <button class="btn" id="${lanche.id}-btn">Excluir do pedido</button>
          </div>
      </section>
    </li>
    `);

		renderListaAdicionados(lanche.id);
		renderListaRemovidos(lanche.id);
		renderListaAdicionais(lanche.id);
		renderListaRemoviveis(lanche.ingredientes, lanche.id);

		const removeLancheButton = $(`#${lanche.id}-btn`);
		removeLancheButton.on('click', removeLanche);
	});
}

function handleConfirm(event) {
	event.preventDefault();

	const nome = $('#nome-cliente').val();
	const rua = $('#rua').val();
	const bairro = $('#bairro').val();
	const numero = $('#numero').val();

	if (nome.length === 0 || rua.length === 0 || bairro.length === 0 || numero.length === 0) {
		return;
	}

	itensPedido.forEach((item) => {
		item.id = parseInt(item.id[0]);
	});

	const pedidoDTO = {
		nome: nome,
		rua: rua,
		bairro: bairro,
		numero: numero,
		itens: itensPedido,
	};

	$.ajax({
		type: 'POST',
		url: 'PedidoServlet',
		data: JSON.stringify(pedidoDTO),
		processData: false,
		contentType: 'application/json',
		success: function () {
			window.location = 'index.html';
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			console.log(errorThrown);
		},
	});
}
