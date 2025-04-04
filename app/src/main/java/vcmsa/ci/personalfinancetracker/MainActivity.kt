package vcmsa.ci.personalfinancetracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etIncome: EditText
    private lateinit var etExpenses: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvTotalIncome: TextView
    private lateinit var tvTotalExpenses: TextView
    private lateinit var tvBalance: TextView
    private lateinit var tvFeedback: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views
        etIncome = findViewById(R.id.etIncome)
        etExpenses = findViewById(R.id.etExpenses)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvTotalIncome = findViewById(R.id.tvTotalIncome)
        tvTotalExpenses = findViewById(R.id.tvTotalExpenses)
        tvBalance = findViewById(R.id.tvBalance)
        tvFeedback = findViewById(R.id.tvFeedback)

        // Button Click Listener
        btnCalculate.setOnClickListener {
            calculateFinance()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculateFinance() {
        // Get user inputs
        val income = etIncome.text.toString().toDoubleOrNull() ?: 0.0
        val expenses = etExpenses.text.toString().toDoubleOrNull() ?: 0.0

        // Calculate balance
        val balance = income - expenses

        // Display Total Income and Expenses
        tvTotalIncome.text = "Total Income: $$income"
        tvTotalExpenses.text = "Total Expenses: $$expenses"
        tvBalance.text = "Balance: $$balance"

        // Feedback Logic
        if (balance > 0) {
            tvFeedback.text = "Feedback: You are saving money!"
            tvFeedback.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
        } else if (balance < 0) {
            tvFeedback.text = "Feedback: You are overspending!"
            tvFeedback.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        } else {
            tvFeedback.text = "Feedback: Your balance is zero. Try to save!"
            tvFeedback.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray))
        }

        // Category Expenses Percentage Logic (Optional for categorizing expenses)
        val percentage = if (income > 0) (expenses / income) * 100 else 0.0

        if (percentage > 50) {
            tvFeedback.append("\nYou are spending more than 50% of your income on expenses!")
            tvFeedback.setTextColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
        }
    }
}