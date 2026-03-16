import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class OtpActivity : AppCompatActivity() {

    lateinit var timer: CountDownTimer
    lateinit var tvResend: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        tvResend = findViewById(R.id.tvResend)

        startTimer()

        findViewById<Button>(R.id.btnVerify).setOnClickListener {

            val code =
                findViewById<EditText>(R.id.otp1).text.toString() +
                        findViewById<EditText>(R.id.otp2).text.toString() +
                        findViewById<EditText>(R.id.otp3).text.toString() +
                        findViewById<EditText>(R.id.otp4).text.toString() +
                        findViewById<EditText>(R.id.otp5).text.toString() +
                        findViewById<EditText>(R.id.otp6).text.toString()

            Toast.makeText(this, "Код: $code", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startTimer() {

        timer = object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val sec = millisUntilFinished / 1000
                tvResend.text = "Запросить новый код 00:${sec}"
            }

            override fun onFinish() {
                tvResend.text = "Отправить снова"
            }

        }.start()
    }
}