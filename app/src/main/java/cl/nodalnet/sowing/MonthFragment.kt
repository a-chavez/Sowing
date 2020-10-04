package cl.nodalnet.sowing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cl.nodalnet.sowing.model.viewmodel.MyAdapter
import cl.nodalnet.sowing.model.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_month.*

class MonthFragment : Fragment(), MyAdapter.SeedNameTxt {
    lateinit var mViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_month, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mRecyclerView = recyclerView2
        val mAdapter = MyAdapter(this)
        val mFullMonth:String = mViewModel.getCurrentMonth()[1]
        var mActualMonth:String = mViewModel.getCurrentMonth()[0]

        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        mViewModel.exposeLiveDataFromServer() .observe(viewLifecycleOwner, Observer {
            val mGet = mViewModel.getSeedFromMonth(mActualMonth,it)
            mAdapter.updateListSeed(mGet)
        })


    }


    override fun passData(mSeedNameTxt: String) {
        val mBundle = Bundle()
        mBundle.putString("seedName", mSeedNameTxt)
        //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, mBundle)
    }

}