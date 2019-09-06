package projetoitau.com.br.projetoitau.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list.view.*
import projetoitau.com.br.projetoitau.R
import projetoitau.com.br.projetoitau.model.Data

class ListaAdapter(
    private val context: Context
) : RecyclerView.Adapter<ListaAdapter.ViewHolder>() {

    private var lista: ArrayList<Data> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lista = lista[position]

        holder.let {
            it.bindView(lista)
        }
    }

    fun setList(listaSet: ArrayList<Data>) {
        lista = listaSet
    }

    fun addData(listaAdd: List<Data>) {
        this.lista.addAll(listaAdd)
    }

    lateinit var mClickListener: ClickListener

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    interface ClickListener {
        fun onClick(pos: Int, aView: View)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        override fun onClick(v: View) {
            mClickListener.onClick(adapterPosition, v)
        }

        init {
            itemView.setOnClickListener(this)
        }

        fun bindView(lista: Data) {
            val nome = itemView.tvNome
            val img = itemView.ImImage

            nome.text = lista.nome

            Glide.with(itemView.context)
                .load(lista.img)
                .circleCrop()
                .into(img)
        }
    }
}

