package projetoitau.com.br.projetoitau.model


data class Users(

    val contaCorrente: String,
    val contaPoupanca: String,
    val contatos: List<Data>
)

data class Data(

    val nome: String,
    val saldo: String,
    val img: String
)

