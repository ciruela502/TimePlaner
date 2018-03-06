package martakonik.timeplaner.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import martakonik.timeplaner.R
import martakonik.timeplaner.models.WorkDayPlain
import javax.inject.Inject

class DayPlainAdapter @Inject constructor(): RecyclerView.Adapter<DayPlainAdapter.DayViewHolder>(),
        AdapterModel, AdapterView {

    private var mDayList: ArrayList<WorkDayPlain> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.recycler_view_day_row, parent, false)
        return DayViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mDayList.size
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val dayPlain = mDayList[position]
        holder.textData.text = dayPlain.data
        holder.textStart.text = dayPlain.start
        holder.textEnd.text = dayPlain.end
    }

    override fun refresh() {
        notifyDataSetChanged()
    }

    override fun addData(dayPlains: List<WorkDayPlain>) {
        mDayList.addAll(dayPlains)
    }


    class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textData: TextView = itemView.findViewById(R.id.textViewData)
        var textStart: TextView = itemView.findViewById(R.id.textViewTimeStart)
        var textEnd: TextView = itemView.findViewById(R.id.textViewTimeEnd)
    }
}