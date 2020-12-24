package com.a1androidhmw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.result_fragment.*

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentForAnswer : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.result_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null)
        tvResultText.text =
            if (arguments != null) "Result: ${arguments?.getDouble(pmFragmentParameter).toString()}"
            else ""

    }


    companion object {
        fun newInstance(number: Double = 0.0): FragmentForAnswer {
            val fragment = FragmentForAnswer()
            val bundle = Bundle()
            bundle.putDouble(pmFragmentParameter, number)
            fragment.arguments = bundle
            return fragment
        }
    }
}