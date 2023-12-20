package com.farydrop.photoeditor

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.farydrop.photoeditor.App.Companion.photoApp
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ja.burhanrashid52.photoeditor.PhotoEditor

class EmojiBSFragment : BottomSheetDialogFragment() {

    private var mEmojiListener: EmojiListener? = null

    interface EmojiListener {
        fun onEmojiClick(emojiUnicode: String?)
    }

    private val mBottomSheetBehaviorCallback: BottomSheetBehavior.BottomSheetCallback = object :
        BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss()
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}

    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val contentView = View.inflate(context, R.layout.fragment_bottom_sticker_emoji_dialog, null)
        dialog.setContentView(contentView)
        val params: CoordinatorLayout.LayoutParams = (contentView.parent as View).layoutParams as LayoutParams
        val behavior = params.behavior
        if (behavior != null && behavior is BottomSheetBehavior){
            (behavior as BottomSheetBehavior)
                .setBottomSheetCallback(mBottomSheetBehaviorCallback)
        }
        (contentView.parent as View)
            .setBackgroundColor(resources.getColor(android.R.color.transparent))

        val rvEmoji: RecyclerView = contentView.findViewById(R.id.rvEmoji)
        var gridLayoutManager = GridLayoutManager(activity,5)
        rvEmoji.layoutManager = gridLayoutManager
        val emojiAdapter: EmojiAdapter = EmojiAdapter()
        rvEmoji.adapter = emojiAdapter
    }
    fun setEmojiListener(emojiListener: EmojiListener?){
        mEmojiListener = emojiListener
    }

    inner class EmojiAdapter: RecyclerView.Adapter<EmojiAdapter.ViewHolder?>(){

        //var emojiList: ArrayList<String> = PhotoEditor.getEmojis(requireContext())

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_emoji,parent,false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: EmojiAdapter.ViewHolder, position: Int) {
            holder.txtEmoji.text = emojisList[position]
        }

        override fun getItemCount(): Int = emojisList.size

        inner class ViewHolder(val v: View): RecyclerView.ViewHolder(v){
            val txtEmoji: TextView
            init {
                txtEmoji = v.findViewById(R.id.tvEmoji)
                v.setOnClickListener {
                    if (mEmojiListener!=null) {
                        mEmojiListener!!.onEmojiClick(emojisList[layoutPosition])
                    }
                    dismiss()
                }
            }
        }
    }

    companion object {
        private var emojisList = getEmojis(photoApp)

        /**
         * Provide the list of emoji in form of unicode string
         *
         * @param context context
         * @return list of emoji unicode
         */
        fun getEmojis(context: Context?): ArrayList<String> {
            val convertedEmojiList = ArrayList<String>()
            val emojiList = context!!.resources.getStringArray(R.array.photo_editor_emoji)
            for (emojiUnicode in emojiList) {
                convertedEmojiList.add(convertEmoji(emojiUnicode))
            }
            return convertedEmojiList
        }

        private fun convertEmoji(emoji: String): String {
            return try {
                val convertEmojiToInt = emoji.substring(2).toInt(16)
                String(Character.toChars(convertEmojiToInt))
            } catch (e: NumberFormatException) {
                ""
            }
        }
    }

}