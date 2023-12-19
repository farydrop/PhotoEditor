package com.farydrop.photoeditor

import android.annotation.SuppressLint
import android.app.Dialog
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            TODO("Not yet implemented")
        }

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
        var gridLayoutManager = GridLayoutManager(activity.5)
        rvEmoji.layoutManager = gridLayoutManager
        val emojiAdapter: EmojiAdapter = EmojiAdapter()
        rvEmoji.adapter = emojiAdapter
    }
    fun setEmojiListener(emojiListener: EmojiListener){
        mEmojiListener = emojiListener
    }

    inner class EmojiAdapter: RecyclerView.Adapter<EmojiAdapter.ViewHolder?>(){

        var emojiList: ArrayList<String> = PhotoEditor.getEmojis(requireContext())

        inner class ViewHolder(val v: View): RecyclerView.ViewHolder(v){

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiAdapter.ViewHolder {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: EmojiAdapter.ViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

    }

}