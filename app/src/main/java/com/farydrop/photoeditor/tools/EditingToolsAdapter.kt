package com.farydrop.photoeditor.tools

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.farydrop.photoeditor.R

class EditingToolsAdapter(private val mOnItemSelected: OnItemSelected): RecyclerView.Adapter<EditingToolsAdapter.ViewHolder>() {

    private val mToolList: MutableList<ToolModel> = ArrayList()

    internal inner class ToolModel(
        val mToolName: String,
        val mToolIcon: Int,
        toolType: ToolType
    ) {
        val mToolType: ToolType
        init {
            mToolType = toolType
        }
    }

    inner class ViewHolder(val v:View): RecyclerView.ViewHolder(v) {
        val imgToolIcon: ImageView
        val txtTool: TextView
        init {
            imgToolIcon = v.findViewById(R.id.ivToolsIcon)
            txtTool = v.findViewById(R.id.tvTool)
            v.setOnClickListener{
                mOnItemSelected.onToolSelected(
                    mToolList[layoutPosition].mToolType
                )
            }
        }
    }

    interface OnItemSelected {
        fun onToolSelected(toolType: ToolType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_editing_tools,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mToolList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mToolList[position]
        holder.txtTool.text = item.mToolName
        holder.imgToolIcon.setImageResource(item.mToolIcon)
    }

    init {
        mToolList.add(ToolModel("Brush", R.drawable.ic_brush, ToolType.BRUSH))
        mToolList.add(ToolModel("Text", R.drawable.ic_text, ToolType.TEXT))
        mToolList.add(ToolModel("Eraser", R.drawable.ic_eraser, ToolType.ERASER))
        mToolList.add(ToolModel("Filter", R.drawable.ic_filter, ToolType.FILTER))
        mToolList.add(ToolModel("Emoji", R.drawable.ic_emoji, ToolType.EMOJI))
        mToolList.add(ToolModel("Sticker", R.drawable.ic_sticker, ToolType.STICKER))
    }
}