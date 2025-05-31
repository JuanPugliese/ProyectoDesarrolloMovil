package com.project.fisiocare.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.project.fisiocare.R
import com.project.fisiocare.model.Historia
import java.io.File
import java.io.FileOutputStream
import java.text.NumberFormat
import java.util.Locale

class HistoriaAdapter(
    private var historias: List<Historia>,
    private val onModificar: (Historia) -> Unit,
    private val onEliminar: (Historia) -> Unit
) : RecyclerView.Adapter<HistoriaAdapter.HistoriaViewHolder>(){

    private var historiasFiltradas: List<Historia> = historias.toList()

    inner class HistoriaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombrePaciente: TextView = view.findViewById(R.id.nombrePacienteText)
        val valorCita: TextView = view.findViewById(R.id.valorCitaText)
        val tratamiento: TextView = view.findViewById(R.id.tratamiento)
        val fechaText: TextView = view.findViewById(R.id.fechaText)
        val horaText: TextView = view.findViewById(R.id.horaText)
        val btnModificar: Button = view.findViewById(R.id.btnModificar)
        val btnEliminar: Button = view.findViewById(R.id.btnEliminar)
        val btnDescargar: Button = view.findViewById(R.id.btnDescargar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoriaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.historia_tm, parent, false)
        return HistoriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoriaViewHolder, position: Int) {
        val historia = historiasFiltradas[position]
        val formatoPesos = NumberFormat.getCurrencyInstance(Locale("es", "CO"))
        formatoPesos.maximumFractionDigits = 0

        holder.nombrePaciente.text = historia.paciente
        holder.valorCita.text = formatoPesos.format(historia.valor)
        holder.tratamiento.text = historia.tratamiento
        holder.fechaText.text = historia.fecha
        holder.horaText.text = historia.hora

        holder.btnModificar.setOnClickListener {
            androidx.appcompat.app.AlertDialog.Builder(holder.itemView.context)
                .setTitle("Modificar historia")
                .setMessage("¿Desea modificar la historia clínica?")
                .setPositiveButton("OK") { _, _ -> onModificar(historia) }
                .show()
        }

        holder.btnEliminar.setOnClickListener {
            androidx.appcompat.app.AlertDialog.Builder(holder.itemView.context)
                .setTitle("Eliminar historia")
                .setMessage("¿Desea eliminar la historia clínica?")
                .setPositiveButton("OK") { _, _ -> onEliminar(historia) }
                .show()
        }

        holder.btnDescargar.setOnClickListener {
            generarPDF(context = holder.itemView.context, historia = historia)
        }
    }

    override fun getItemCount(): Int = historiasFiltradas.size

    fun updateData(newHistorias: List<Historia>) {
        println("🔄 Adapter recibió ${newHistorias.size} historias")
        historias = newHistorias
        historiasFiltradas = newHistorias.toList() // Asegúrate de hacer copia
        notifyDataSetChanged()

        // Debug: Verifica el primer elemento
        if (newHistorias.isNotEmpty()) {
            println("📌 Primer elemento: ${newHistorias[0].paciente}")
        }
    }
    fun filtrarPorNombre(query: String) {
        val filtro = query.trim()
        historiasFiltradas = if (filtro.isEmpty()) historias
        else historias.filter { it.paciente.contains(filtro, ignoreCase = true) }
        notifyDataSetChanged()
    }

    private fun generarPDF(context: Context, historia: Historia) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(300, 600, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas

        val paint = Paint()
        paint.textSize = 12f
        paint.color = Color.BLACK

        var y = 25
        canvas.drawText("Historia Clínica", 80f, y.toFloat(), paint)
        y += 25
        canvas.drawText("Paciente: ${historia.paciente}", 10f, y.toFloat(), paint)
        y += 20
        canvas.drawText("Profesional: ${historia.profesional}", 10f, y.toFloat(), paint)
        y += 20
        canvas.drawText("Tratamiento: ${historia.tratamiento}", 10f, y.toFloat(), paint)
        y += 20
        canvas.drawText("Valor: $${"%,.0f".format(historia.valor)}", 10f, y.toFloat(), paint)
        y += 20
        canvas.drawText("Fecha: ${historia.fecha}", 10f, y.toFloat(), paint)
        y += 20
        canvas.drawText("Hora: ${historia.hora}", 10f, y.toFloat(), paint)

        pdfDocument.finishPage(page)
        val fileName = "Historia_${historia.id}.pdf"
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName)

        try {
            pdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(context, "PDF generado en: ${file.absolutePath}", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Error al generar PDF: ${e.message}", Toast.LENGTH_SHORT).show()
        }
        pdfDocument.close()
    }
}