$(() => {
    window.setInterval(() => {
        getPedidosAndUpdateAjax();
    }, 1000 * 10);
});

function getPedidosAndUpdateAjax() {
    $.ajax({
        type: 'GET', url: 'PedidoAjaxServlet', success: function (data, textStatus, jqXHR) {
            $('main').html(data);
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(errorThrown);
        },
    });
};