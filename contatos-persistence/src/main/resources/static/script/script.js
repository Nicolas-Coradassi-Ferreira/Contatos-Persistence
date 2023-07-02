

function mostrarTelefones(idContato) {
    var divContato = document.getElementById(idContato);
    var inputTelefonesContato = divContato.querySelector("#telefonesContato" + idContato);
    var telefonesContato = inputTelefonesContato.value.split(";");

    var ulTelefones = divContato.querySelector("#telefones" + idContato);
    ulTelefones.classList.add("card");
    ulTelefones.innerHTML = '';

    telefonesContato.forEach((telefone) => {

        var novoItemUlTelefones = document.createElement("li");
        novoItemUlTelefones.textContent = telefone;
        novoItemUlTelefones.style.margin = '3px';
        ulTelefones.appendChild(novoItemUlTelefones);
    });
}

function adicionarTelefone() {

    var regexTelefone = /^(\d{9}|\d{12}|\d{14})$/;

    var inputTelefone = document.getElementById('telefone');
    var telefone = inputTelefone.value;
    var formGroupTelefone = inputTelefone.parentElement.parentElement;

    if (telefone.match(regexTelefone)) {

        var ulTelefones = document.getElementById('telefones');
        ulTelefones.classList.add("card", "mt-3");

        var telefoneFormatado = formatarTelefone(telefone);

        var novoItemUlTelefones = document.createElement("li");
        novoItemUlTelefones.textContent = telefoneFormatado;
        novoItemUlTelefones.style.margin = '3px';

        var botaoRemoverTelefone = document.createElement("button");
        botaoRemoverTelefone.classList.add("btn", "btn-danger");
        botaoRemoverTelefone.textContent = "-";
        botaoRemoverTelefone.style.marginLeft = '10px';
        botaoRemoverTelefone.addEventListener("click", function () {
            var liTelefone = botaoRemoverTelefone.parentElement;
            liTelefone.remove();
        });
        novoItemUlTelefones.appendChild(botaoRemoverTelefone);

        ulTelefones.appendChild(novoItemUlTelefones);

        inputTelefone.value = "";

        var divErro = formGroupTelefone.querySelector("#divErro");
        if (divErro) {
            inputTelefone.classList.remove("is-invalid");
            divErro.remove();
        }

    } else {
        inputTelefone.classList.add("is-invalid");

        var divErro = formGroupTelefone.querySelector("#divErro");
        if (!divErro) {
            divErro = document.createElement("div");
            divErro.setAttribute("id", "divErro");
            divErro.style.fontSize = '14px';
            divErro.style.color = '#dc3545';
            divErro.textContent = "Apenas dígitos numéricos, sendo 9 ou 12 (com DDD) ou 14 (com código do país + DDD) dígitos!";

            formGroupTelefone.appendChild(divErro);
        }
    }
}

function onSubmitFormContato() {
    var ulTelefones = document.getElementById("telefones");

    if (ulTelefones.childElementCount == 0) {
        alert("Informe pelo menos 1 telefone para cadastrar ou atualizar o contato!");
        inserirDataNascimentoForm();
        return false;
    }

    itensUlTelefones = ulTelefones.getElementsByTagName("li");
    var telefones = [];

    for (var i = 0; i < itensUlTelefones.length; i++) {
        var telefone = itensUlTelefones[i].textContent;
        telefones.push(telefone.slice(0, telefone.length - 1));
    }

    var inputTelefonesContato = document.getElementById("telefonesContato");
    inputTelefonesContato.value = telefones.join(";");

    return true;
}

function whenDocumentLoaded() {
    var inputTelefonesContato = document.getElementById("telefonesContato");
    var telefonesContato = inputTelefonesContato.value.split(";");

    if (telefonesContato[0] != '') {
        var ulTelefones = document.getElementById("telefones");
        ulTelefones.classList.add("card", "mt-3");
        telefonesContato.forEach((telefone) => {

            var novoItemUlTelefones = document.createElement("li");
            novoItemUlTelefones.textContent = telefone;
            novoItemUlTelefones.style.margin = '3px';

            var botaoRemoverTelefone = document.createElement("button");
            botaoRemoverTelefone.classList.add("btn", "btn-danger");
            botaoRemoverTelefone.textContent = "-";
            botaoRemoverTelefone.style.marginLeft = '10px';
            botaoRemoverTelefone.addEventListener("click", function () {
                var liTelefone = botaoRemoverTelefone.parentElement;
                liTelefone.remove();
            });
            novoItemUlTelefones.appendChild(botaoRemoverTelefone);

            ulTelefones.appendChild(novoItemUlTelefones);
        });
    }

    inserirDataNascimentoForm();
}

function formatarTelefone(telefone) {
    switch (telefone.length) {
        case 9:
            return telefone.slice(0, 5) + '-' + telefone.slice(5, 9);

        case 12:
            return '(' + telefone.slice(0, 3) + ') ' + telefone.slice(3, 8) + '-' + telefone.slice(8, 12);

        case 14:
            return '+' + telefone.slice(0, 2) + ' (' + telefone.slice(2, 5) + ') ' + telefone.slice(5, 10) + '-' + telefone.slice(10, 14);

        default:
            return telefone;
    }
}

function inserirDataNascimentoForm() {
    var fieldDataNascimento = document.getElementById("dataNascimento");
    var dataNascimentoFormatoCorreto = document.getElementById("dataNascimentoFormatoCorreto").value;
    fieldDataNascimento.value = dataNascimentoFormatoCorreto;
}