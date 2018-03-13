package martakonik.timeplaner.ui.history.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import martakonik.timeplaner.R
import martakonik.timeplaner.domain.models.WorkDayPlain
import javax.inject.Inject

class DayPlainAdapter @Inject constructor() : RecyclerView.Adapter<DayPlainAdapter.DayViewHolder>(),
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
        holder.model = mDayList[position]
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

        var model: WorkDayPlain? = null
            set(value) {
                field = model
                value?.also {
                    textData.text = value.data
                    textStart.text = value.start
                    textEnd.text = value.end
                }
            }


    }
}