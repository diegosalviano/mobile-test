package projetoitau.com.br.projetoitau.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_transferir.*
import projetoitau.com.br.projetoitau.R

class TransferirActivity : AppCompatActivity() {

    var nome: String? = null
    var tipoConta: String? = "0"
    var saldo: String? = null
    var img: String? = null
    var valor: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transferir)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        nome = intent.getStringExtra("nome")
        saldo = intent.getStringExtra("saldo")
        tipoConta = intent.getStringExtra("tipoConta")
        img = intent.getStringExtra("img")
        title = "Transferência entre contas"

        tvNome.text = nome

        tvDeConta.text = "saldo disponível R$ $saldo"


        Glide.with(this)
            .load(img)
            .circleCrop()
            .into(ImImage)

        btn1.setOnClickListener {
            setEditText(1)
        }
        btn5.setOnClickListener {
            setEditText(5)
        }
        btn10.setOnClickListener {
            setEditText(10)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun setEditText(total: Int) {
        if (edValor.text.isNotEmpty()) {
            valor = edValor.text.toString().toFloat()
            valor = valor.plus(total)
            tvDeConta.text = "saldo disponível R$ $saldo"
            edValor.setText(valor.toString())
        }
    }
}
