import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {

            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val phone = binding.etPhone.text.toString()
            val password = binding.etPassword.text.toString()

            if (name.isBlank()||email.isBlank()|| phone.isBlank() || password.isBlank()) {

            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }

            if (!binding.checkTerms.isChecked) {
                Toast.makeText(this, "Примите условия", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Регистрация успешна", Toast.LENGTH_SHORT).show()
        }
    }
}