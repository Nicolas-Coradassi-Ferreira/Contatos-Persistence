

function mostrarTelefones(idContato) {
    let divContato = document.getElementById(idContato);
    let inputTelefonesContato = divContato.querySelector("#telefonesContato" + idContato);
    let telefonesContato = inputTelefonesContato.value.split(";");

    let ulTelefones = divContato.querySelector("#telefones" + idContato);
    ulTelefones.classList.add("card");
    ulTelefones.innerHTML = '';

    telefonesContato.forEach((telefone) => {

        let novoItemUlTelefones = document.createElement("li");
        novoItemUlTelefones.textContent = telefone;
        novoItemUlTelefones.style.margin = '3px';
        ulTelefones.appendChild(novoItemUlTelefones);
    });
}

function adicionarTelefone() {

    let regexTelefone = /^(\d{9}|\d{12}|\d{14})$/;

    let inputTelefone = document.getElementById('telefone');
    let telefone = inputTelefone.value;
    let formGroupTelefone = inputTelefone.parentElement.parentElement;

    if (telefone.match(regexTelefone)) {

        let ulTelefones = document.getElementById('telefones');
        ulTelefones.classList.add("card", "mt-3");

        let telefoneFormatado = formatarTelefone(telefone);

        let novoItemUlTelefones = document.createElement("li");
        novoItemUlTelefones.textContent = telefoneFormatado;
        novoItemUlTelefones.style.margin = '3px';

        let botaoRemoverTelefone = document.createElement("button");
        botaoRemoverTelefone.classList.add("btn", "btn-danger");
        botaoRemoverTelefone.textContent = "-";
        botaoRemoverTelefone.style.marginLeft = '10px';
        botaoRemoverTelefone.addEventListener("click", function () {
            let liTelefone = botaoRemoverTelefone.parentElement;
            liTelefone.remove();
        });
        novoItemUlTelefones.appendChild(botaoRemoverTelefone);

        ulTelefones.appendChild(novoItemUlTelefones);

        inputTelefone.value = "";

        let divErro = formGroupTelefone.querySelector("#divErro");
        if (divErro) {
            inputTelefone.classList.remove("is-invalid");
            divErro.remove();
        }

    } else {
        inputTelefone.classList.add("is-invalid");

        let divErro = formGroupTelefone.querySelector("#divErro");
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
    let ulTelefones = document.getElementById("telefones");

    if (ulTelefones.childElementCount == 0) {
        alert("Informe pelo menos 1 telefone para cadastrar ou atualizar o contato!");
        inserirDataNascimentoForm();
        return false;
    }

    itensUlTelefones = ulTelefones.getElementsByTagName("li");
    let telefones = [];

    for (let i = 0; i < itensUlTelefones.length; i++) {
        let telefone = itensUlTelefones[i].textContent;
        telefones.push(telefone.slice(0, telefone.length - 1));
    }

    let inputTelefonesContato = document.getElementById("telefonesContato");
    inputTelefonesContato.value = telefones.join(";");

    return true;
}

function onSubmitExcluirContato(){
    if (confirm("Tem certeza que deseja excluir este contato?")){
        return true;
    }
    return false;
}

function onSubmitFormNovoUsuario(){
    let inputSenha = document.getElementById("inputSenha");
    let inputSenhaConfirmada = document.getElementById("inputSenhaConfirmada");

    if (inputSenha.value == inputSenhaConfirmada.value){
        return true;
    }

    inputSenha.classList.add("is-invalid");
    inputSenhaConfirmada.classList.add("is-invalid");
    let divErro = document.getElementById("divErro");
    if (divErro) {
        divErro.remove();
    }
    return false;
}

function whenDocumentLoaded() {
    let inputTelefonesContato = document.getElementById("telefonesContato");
    let telefonesContato = inputTelefonesContato.value.split(";");

    if (telefonesContato[0] != '') {
        let ulTelefones = document.getElementById("telefones");
        ulTelefones.classList.add("card", "mt-3");
        telefonesContato.forEach((telefone) => {

            let novoItemUlTelefones = document.createElement("li");
            novoItemUlTelefones.textContent = telefone;
            novoItemUlTelefones.style.margin = '3px';

            let botaoRemoverTelefone = document.createElement("button");
            botaoRemoverTelefone.classList.add("btn", "btn-danger");
            botaoRemoverTelefone.textContent = "-";
            botaoRemoverTelefone.style.marginLeft = '10px';
            botaoRemoverTelefone.addEventListener("click", function () {
                let liTelefone = botaoRemoverTelefone.parentElement;
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
    let fieldDataNascimento = document.getElementById("dataNascimento");
    let dataNascimentoFormatoCorreto = document.getElementById("dataNascimentoFormatoCorreto").value;
    fieldDataNascimento.value = dataNascimentoFormatoCorreto;
}