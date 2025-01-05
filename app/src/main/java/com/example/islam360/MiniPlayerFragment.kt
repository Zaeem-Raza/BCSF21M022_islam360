import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.example.islam360.R
import com.example.islam360.dataAccess.DbHelper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MiniPlayerFragment : BottomSheetDialogFragment() {

    private lateinit var btnClose: ImageButton
    private lateinit var btnPlayPause: ImageButton
    private lateinit var seekBar: SeekBar
    private lateinit var tvCurrentTime: TextView
    private lateinit var tvTotalTime: TextView

    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false

    private val handler = Handler(Looper.getMainLooper())
    private val updateSeekBarRunnable = object : Runnable {
        override fun run() {
            mediaPlayer?.let { player ->
                if (isPlaying) {
                    seekBar.progress = player.currentPosition
                    tvCurrentTime.text = formatTime(player.currentPosition)
                    handler.postDelayed(this, 1000)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mini_player, container, false)

        btnClose = view.findViewById(R.id.btnClose)
        btnPlayPause = view.findViewById(R.id.btnPlayPause)
        seekBar = view.findViewById(R.id.audioSeekBar)
        tvCurrentTime = view.findViewById(R.id.tvCurrentTime)
        tvTotalTime = view.findViewById(R.id.tvTotalTime)

        btnClose.setOnClickListener { closePlayer() }
        btnPlayPause.setOnClickListener { togglePlayPause() }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(progress)
                    tvCurrentTime.text = formatTime(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                handler.removeCallbacks(updateSeekBarRunnable)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer?.seekTo(seekBar?.progress ?: 0)
                if (isPlaying) {
                    handler.post(updateSeekBarRunnable)
                }
            }
        })

        return view
    }

    fun playAudio(surahId: Int, ayahId: Int) {
        Log.d("surah id and aya id", surahId.toString())
        Log.d("surah id and aya id", ayahId.toString())
        val audioUrl = "https://everyayah.com/data/AbdulSamad_64kbps_QuranExplorer.Com/${"%03d".format(surahId)}${"%03d".format(ayahId)}.mp3"
        Log.d("AudioURL", audioUrl)

        streamAudio(audioUrl)
    }


    private fun streamAudio(url: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(url)
                    prepareAsync()
                    setOnPreparedListener {
                        start()
                        this@MiniPlayerFragment.isPlaying = true
                        btnPlayPause.setImageResource(R.drawable.baseline_pause_circle_24)
                        tvTotalTime.text = formatTime(duration)
                        seekBar.max = duration
                        seekBar.progress = currentPosition
                        handler.post(updateSeekBarRunnable)
                    }
                }
                mediaPlayer?.setOnErrorListener { _, what, extra ->
                    //Toast.makeText(context, "Error playing audio: $what, $extra", Toast.LENGTH_SHORT).show()
                    true
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Failed to stream audio", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun togglePlayPause() {
        mediaPlayer?.let {
            if (isPlaying) {
                it.pause()
                isPlaying = false
                btnPlayPause.setImageResource(R.drawable.baseline_play_circle_24)
                handler.removeCallbacks(updateSeekBarRunnable)
            } else {
                it.start()
                isPlaying = true
                btnPlayPause.setImageResource(R.drawable.baseline_pause_circle_24)
                handler.post(updateSeekBarRunnable)
            }
        }
    }

    private fun closePlayer() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
        }
        mediaPlayer = null
        isPlaying = false
        handler.removeCallbacks(updateSeekBarRunnable)
        dismiss()
    }

    private fun formatTime(milliseconds: Int): String {
        val minutes = milliseconds / 60000
        val seconds = (milliseconds % 60000) / 1000
        return String.format("%02d:%02d", minutes, seconds)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        closePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        closePlayer()
    }
}
