package com.shaparapatah.chuckjoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shaparapatah.chuckjoke.databinding.FragmentChuckBinding
import kotlinx.android.synthetic.main.fragment_chuck.*

class ChuckFragment : Fragment() {

    private var _binding: FragmentChuckBinding? = null
    val binding: FragmentChuckBinding
        get() {
            return _binding!!
        }

    private val viewModel: ChuckViewModel by lazy {
        ViewModelProvider(this)[ChuckViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChuckBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.sendServerRequest()
    }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Error -> {
                toast(data.error.message)
            }
            is AppState.Loading -> {
                loadingLayout.visibility = View.VISIBLE
                mainFragmentRecyclerView.visibility = View.INVISIBLE

            }
            is AppState.Success -> {
                val serverResponseData = data.serverResponseData
                val joke = serverResponseData.joke
                binding.mainFragmentRecyclerView


            }
        }
    }

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            show()
        }
    }

    companion object {
        fun newInstance() = ChuckFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}