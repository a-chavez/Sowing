package cl.nodalnet.sowing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import cl.nodalnet.sowing.model.viewmodel.MyViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*

class Detail : Fragment() {

    lateinit var mViewModel: MyViewModel
    var mSeedNameTxt: String? =null
    var mMonth: String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        arguments?.let {
            mSeedNameTxt = it.getString("seedName","")
            mMonth = it.getString("month","")
        }

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSeedNameTxt?.let {
            mViewModel.getOneSeed(it).observe(viewLifecycleOwner, Observer {
                tvSeedName.setText(it.name.capitalize())
                Glide.with(this).load(it.urlImage).into(imgDetail)
            })
        }
        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            val mBundle = Bundle()
            mBundle.putString("month", mMonth)
            findNavController().navigate(R.id.action_SecondFragment_to_monthFragment,mBundle)
        }
    }
}