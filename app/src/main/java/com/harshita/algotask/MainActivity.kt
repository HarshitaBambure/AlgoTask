package com.harshita.algotask

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.harshita.algotask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val adapter = NumberAdapter()
        binding.rvNumbers.adapter = adapter
        binding.rvNumbers.layoutManager = GridLayoutManager(this, 4)
        adapter.submitList(getFinalList((1..100).toList()))
        binding.btnShow.setOnClickListener {
            val selectedAlgo = binding.spAlgoname.selectedItem.toString()
            val list = when (selectedAlgo.trim()) {
                "Odd Number" -> {
                    getFinalList(
                        (1..100).filter { it % 2 != 0 }
                    )
                }

                "Even Number" -> {
                    getFinalList(
                        (1..100).filter { it % 2 == 0 }
                    )
                }

                "Prime Number" -> {
                    Log.e("selectedAlgo", "onCreate: $selectedAlgo")
                    getFinalList(
                        getPrimeNumber(100)
                    )
                }

                "Fibonacci Number" -> {
                    getFinalList(getFibonacciNumbersInRange(1, 100))
                }

                else -> {
                    getFinalList((1..100).toList())
                }
            }
            adapter.submitList(list)

        }
    }

    private fun getPrimeNumber(limit: Int): List<Int> {
        val sieve = BooleanArray(limit + 1) { true }
        sieve[0] = false // 0 is not a prime
        sieve[1] = false // 1 is not a prime

        for (i in 2..Math.sqrt(limit.toDouble()).toInt()) {
            if (sieve[i]) {
                for (j in i * i..limit step i) {
                    sieve[j] = false
                }
            }
        }

        // Collect prime numbers
        val primes = mutableListOf<Int>()
        sieve.forEachIndexed { index, isPrime ->
            if (isPrime) primes.add(index)
        }
        Log.e("getPrimeNumber", "getPrimeNumber:$primes ", )
        return primes
    }

    private fun getFinalList(numberToHighLight: List<Int>): List<AdapterModel> {
        return (1..100).toList()
            .map { com.harshita.algotask.AdapterModel(it, numberToHighLight.contains(it)) }
    }

    private fun getFibonacciNumbersInRange(start: Int, end: Int): List<Int> {
        val fibonacciNumbers = mutableListOf<Int>()
        var a = 0
        var b = 1

        while (b <= end) {
            if (b >= start) {
                fibonacciNumbers.add(b)
            }
            val temp = a + b
            a = b
            b = temp
        }

        return fibonacciNumbers
    }


}