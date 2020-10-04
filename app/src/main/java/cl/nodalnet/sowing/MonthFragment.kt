package cl.nodalnet.sowing

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SortedList
import cl.nodalnet.sowing.model.room.SowingItem
import cl.nodalnet.sowing.model.viewmodel.MyAdapter
import cl.nodalnet.sowing.model.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_month.*

class MonthFragment : Fragment(), MyAdapter.SeedNameTxt {
    lateinit var mViewModel: MyViewModel
    var mMonth: String? =null
    var mTitle: String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        arguments?.let {
            mMonth =it.getString("month","")
            mTitle =it.getString("title","")
            Log.d("Arroz mes",mMonth.toString())
            Log.d("Arroz titulo",mTitle.toString())
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
        var mGet:List<SowingItem> = emptyList()

        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        mViewModel.exposeLiveDataFromServer() .observe(viewLifecycleOwner, Observer {
            if (mMonth=="all") {
                mGet = it
            } else{
                mGet = mViewModel.getSeedFromMonth(mMonth.toString(), it)
            }
            mAdapter.updateListSeed(mGet)
            Log.d("Arroz ListSize",mGet.size.toString())
        })
    }


    override fun passData(mSeedNameTxt: String) {
        val mBundle = Bundle()
        mBundle.putString("seedName", mSeedNameTxt)
        mBundle.putString("month",mMonth)
       findNavController().navigate(R.id.action_monthFragment_to_SecondFragment, mBundle)
    }

}