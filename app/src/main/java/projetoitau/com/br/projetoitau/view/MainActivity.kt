package projetoitau.com.br.projetoitau.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import projetoitau.com.br.projetoitau.R
import projetoitau.com.br.projetoitau.adapter.ListaAdapter
import projetoitau.com.br.projetoitau.api.RetrofitClient
import projetoitau.com.br.projetoitau.model.Data
import projetoitau.com.br.projetoitau.model.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ListaAdapter
    private var loading = false
    private var tipoCheckBox = 0
    private var contaCorrente = ""
    private var contaPoupanca = ""
    private var saldo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Transferência"
        carregarLista()

        checkBoxCorrente.setOnCheckedChangeListener { _, isChecked ->
            tipoCheckBox = if (isChecked) {
                1
            } else {
                0
            }
            checkBox()
        }

        checkBoxPoupanca.setOnCheckedChangeListener { _, isChecked ->
            tipoCheckBox = if (isChecked) {
                2
            } else {
                0
            }
            checkBox()
        }

    }

    private fun checkBox() {

        when (tipoCheckBox) {
            0 -> {
                checkBoxCorrente.isEnabled = true
                checkBoxPoupanca.isEnabled = true
            }
            1 -> {
                checkBoxCorrente.isEnabled = true
                checkBoxPoupanca.isEnabled = false
                saldo = contaCorrente
            }
            2 -> {
                checkBoxCorrente.isEnabled = false
                checkBoxPoupanca.isEnabled = true
                saldo = contaPoupanca
            }
        }
    }

    private fun carregarLista() {
        val call = RetrofitClient().listaService().lista()
        progressBar.visibility = View.VISIBLE
        call.enqueue(object : Callback<Users> {
            override fun onResponse(
                call: Call<Users>?,
                response: Response<Users>?
            ) {
                response?.body()?.let {
                    loading = false
                    val lista: Users = it
                    configurarListagem(lista.contatos)
                    contaCorrente = lista.contaCorrente
                    contaPoupanca = lista.contaPoupanca

                    checkBoxCorrente.text = "conta corrente R$ $contaCorrente"
                    checkBoxPoupanca.text = "conta poupança R$ $contaPoupanca"
                    progressBar.visibility = View.INVISIBLE
                }
            }

            override fun onFailure(call: Call<Users>?, t: Throwable?) {
                loading = false
                alertDialog("Desculpe, não conseguimos concluir a requisição, deseja tentar novamente?", true)
            }
        })
    }

    private fun configurarListagem(lista: List<Data>) {

        val recyclerView = recyclerView_list
        adapter = ListaAdapter(this)

        recyclerView.adapter = adapter

        adapter.setList(lista as ArrayList<Data>)

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

        adapter.setOnItemClickListener(object : ListaAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {

                if (tipoCheckBox != 0) {
                    val intent = Intent(this@MainActivity, TransferirActivity::class.java);
                    intent.putExtra("saldo", saldo)
                    intent.putExtra("tipoConta", tipoCheckBox)
                    intent.putExtra("nome", lista[pos].nome)
                    intent.putExtra("img", lista[pos].img)
                    startActivity(intent)
                } else {
                    toast("Por favor, escolha uma conta")
                }
            }
        })

    }

    fun alertDialog(msg: String, tipo: Boolean) {

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Atenção")

        builder.setMessage(msg)

        if (tipo) {
            builder.setPositiveButton("Sim") { dialog, which ->
                val intent = Intent(this@MainActivity, MainActivity::class.java);
                startActivity(intent)
            }
            builder.setNegativeButton("Sair") { dialog, which ->
                finish()
            }
        } else {
            builder.setNegativeButton("Ok") { dialog, which ->
                finish()
            }
        }

        val dialog: AlertDialog = builder.create()

        dialog.show()
    }

    fun toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
