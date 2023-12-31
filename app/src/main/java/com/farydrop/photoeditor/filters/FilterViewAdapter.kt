package com.farydrop.photoeditor.filters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.farydrop.photoeditor.R
import ja.burhanrashid52.photoeditor.PhotoFilter
import java.io.InputStream
import java.lang.Exception

class FilterViewAdapter(private val mFilterListener: FilterListener):
RecyclerView.Adapter<FilterViewAdapter.ViewHolder>()
{
    private val mPairList: MutableList<Pair<String,PhotoFilter>> = ArrayList()

    inner class ViewHolder(var v:View):RecyclerView.ViewHolder(v) {
        var mImageFilterView: ImageView
        var mTxtImageFilterName: TextView
        init {
            mImageFilterView = v.findViewById(R.id.ivFilterView)
            mTxtImageFilterName = v.findViewById(R.id.tvFilterNaame)
            v.setOnClickListener{
                mFilterListener.onFilterSelected(mPairList[layoutPosition].second)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_filter,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mPairList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filterPair = mPairList[position]

        val fromAsset = getBitmapFromAsset(holder.v.context,filterPair.first)
        holder.mImageFilterView.setImageBitmap(fromAsset)
        holder.mTxtImageFilterName.text = filterPair.second.name.replace("_","")
    }

    private fun getBitmapFromAsset(context: Context?, first: String): Bitmap? {
        val assetManager = context!!.assets
        var istr: InputStream? = null
        return try {
            istr = assetManager.open(first)
            BitmapFactory.decodeStream(istr)
        }
        catch (e:Exception){
            e.printStackTrace()
            null
        }
    }

    private fun setupFilters(){
        mPairList.add(Pair("filters/original.png", PhotoFilter.NONE))
        mPairList.add(Pair("filters/auto_fix.png", PhotoFilter.AUTO_FIX))
        mPairList.add(Pair("filters/brightness.png", PhotoFilter.BRIGHTNESS))
        mPairList.add(Pair("filters/contrast.png", PhotoFilter.CONTRAST))
        mPairList.add(Pair("filters/documentary.png", PhotoFilter.DOCUMENTARY))
        mPairList.add(Pair("filters/dual_tone.png", PhotoFilter.DUE_TONE))
        mPairList.add(Pair("filters/fill_light.png", PhotoFilter.FILL_LIGHT))
        mPairList.add(Pair("filters/fish_eye.png", PhotoFilter.FISH_EYE))
        mPairList.add(Pair("filters/grain.png", PhotoFilter.GRAIN))
        mPairList.add(Pair("filters/gray_scale.png", PhotoFilter.GRAY_SCALE))
        mPairList.add(Pair("filters/lomish.png", PhotoFilter.LOMISH))
        mPairList.add(Pair("filters/negative.png", PhotoFilter.NEGATIVE))
        mPairList.add(Pair("filters/posterize.png", PhotoFilter.POSTERIZE))
        mPairList.add(Pair("filters/saturate.png", PhotoFilter.SATURATE))
        mPairList.add(Pair("filters/sepia.png", PhotoFilter.SEPIA))
        mPairList.add(Pair("filters/sharpen.png", PhotoFilter.SHARPEN))
        mPairList.add(Pair("filters/temprature.png", PhotoFilter.TEMPERATURE))
        mPairList.add(Pair("filters/tint.png", PhotoFilter.TINT))
        mPairList.add(Pair("filters/vignette.png", PhotoFilter.VIGNETTE))
        mPairList.add(Pair("filters/cross_process.png", PhotoFilter.CROSS_PROCESS))
        mPairList.add(Pair("filters/b_n_w.png", PhotoFilter.BLACK_WHITE))
        mPairList.add(Pair("filters/flip_vertical.png", PhotoFilter.FLIP_VERTICAL))
        mPairList.add(Pair("filters/rotate.png", PhotoFilter.ROTATE))
    }

    init {
        setupFilters()
    }
}