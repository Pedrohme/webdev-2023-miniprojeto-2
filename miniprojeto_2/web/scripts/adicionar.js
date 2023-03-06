$(() => {
    const addLancheButton = $('.add-lanche');
  
    addLancheButton.on('click', addLanche);
  });
  
  const itensPedido = [];
  
  function getAdicionais() {
    return $.map($('#adicionais li'), function(n) {
      const ingrediente = {
        id: n.attributes[1].value,
        nome: n.attributes[0].value,
      };
      return ingrediente;
    });
  };
  
  function addLanche(event) {
    event.preventDefault();
  
    const lanche = {
      nome: $(this).data('nome'),
      ingredientes: $.map($(`[data-lanche="${$(this).data('id')}"]`), function(n) {
        const ingrediente = {
          id: n.attributes[1].value,
          nome: n.innerText.trim()
        };
        return ingrediente;
      })
    };
    
    itensPedido.push(lanche);
  
    getAdicionais()
  
    // TODO: transformar adicionais em li's e colocar na linha 56. não esquecer de adicionar classe específica no button. Depois fazer o mesmo para os ingredientes do lanche.
  
    $('#pedidos').append(`
    <li>
        <section class="card">
            <h3 class="card-title">${lanche.nome}</h3>
            <h4 class="card-subtitle">Adicionais:</h4>
            <ul>
                <li class="list-item">{Adicional 1}</li>
            </ul>
            <h4 class="card-subtitle">Removidos:</h4>
            <ul>
                <li class="list-item">{Ingrediente 1}</li>
            </ul>
            <article>
                <h4 class="card-subtitle">
                    Que tal subir o rating do seu lanche?
                </h4>
                <ul>
                    <li>
                        <p class="list-item">{Adicional 1}</p>
                        <button class="btn">Adicionar</button>
                    </li>
                </ul>
            </article>
            <article>
                <h4 class="card-subtitle">
                    Deseja remover alguma peça dessa posição?
                </h4>
                <ul>
                    <li>
                        <p class="list-item">{Ingrediente 1}</p>
                        <button class="btn">Remover</button>
                    </li>
                </ul>
            </article>
            <div class="center">
                <button class="btn">Excluir do pedido</button>
            </div>
        </section>
    </li>
    `)
  }
  